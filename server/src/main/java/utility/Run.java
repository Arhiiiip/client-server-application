package utility;

import command.Invoker;
import command.Receiver;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Run implements Runnable {


    Socket clientConnect;
    MovieFactory movieFactory;
    Logger logger;
    Logger loggerR;


    ExecutorService executor = Executors.newCachedThreadPool();

    public Run(Socket clientConnect, MovieFactory movieFactory, Logger logger, Logger loggerR) {
        this.clientConnect = clientConnect;
        this.movieFactory = movieFactory;
        this.logger = logger;
        this.loggerR = loggerR;
    }

    @Override
    public void run(){
        Receiver receiver = new Receiver();
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(scanner);
        Validator.setReader(reader);
        RRHandler rrHandler = new RRHandler(clientConnect, loggerR);
        String client = clientConnect.toString().substring(23, 33);
        try {
            ObjectForServer command;
            try {
                InputStream stream = rrHandler.getSocket().getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                command = (ObjectForServer) objectInputStream.readObject();
                if (command != null) {
                    logger.info("Execute request from " + clientConnect.getPort() + " in " + Thread.currentThread().getId());
                    executor.execute(new Invoker(receiver, movieFactory, command, rrHandler));
                }
            } catch (SocketException e) {
                logger.info("Client " + client + " was exit");
                clientConnect.close();
            }
        } catch (Exception e) {
            logger.info(client + " enter unreal command, fuck him...");
        }
    }

    public static class MyFormatter extends Formatter {
        @Override
        public String format(LogRecord record){
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }
}
