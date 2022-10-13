import command.Invoker;
import command.LogAndReg;
import command.Receiver;
import request.RRHandler;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.Reader;
import utility.Validator;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Lab_5
 *
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {
    private static SocketChannel clientSocket; //сокет для общения


    public static void main(String[] args) throws IOException {

        boolean isLoging = false;

        String loginUser = "";
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Reader reader = new Reader(scanner);
                Validator.setReader(reader);
                Receiver receiver = new Receiver();

                while (!isLoging) {

                    System.out.println("Do you want to register or login?");
                    String entrance = reader.read();
                    LogAndReg lAR = new LogAndReg();
                    if (!(lAR.validCommand(entrance))){
                        System.out.println("fucked up, look at the commands and write them correctly...please, imbecile!");
                        continue;
                    }
                    System.out.println("Enter your login:");
                    String login = reader.read();
                    System.out.println("Enter your password:");
                    String password = reader.read();
                    String arg = login + " " + password;
                    connect();
                    RRHandler rrHandler = new RRHandler(clientSocket);
                    LogAndReg logAndReg = new LogAndReg(rrHandler, reader);
                    if (logAndReg.execute(entrance, arg)) {
                        isLoging = true;
                        loginUser = login;
                    }
                }

                MovieFactory movieFactory = new MovieFactory(loginUser);

                boolean start;
                System.out.println("Enter command:");
                String commandUser = reader.read().trim();
                connect();
                RRHandler rrHandler = new RRHandler(clientSocket);
                Invoker invoker = new Invoker(receiver, rrHandler);
                rrHandler.setLogin(loginUser);
                try {
                    invoker.execute(commandUser);
                    start = true;
                } catch (RuntimeException e) {
                    start = false;
                }
                if (start) {
                    ObjectForServer response = rrHandler.res();
                    System.out.println(response.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }

    }

    private static void connect() throws IOException {
        boolean connect = false;
        int i = 0;
        while (!connect && i < 5) {
            try {
                clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8090));
                clientSocket.configureBlocking(false);
                connect = true;
            } catch (ConnectException e) {
                System.out.println("The server is down or busy...");
                i += 1;
            }
        }
        if (i == 5) {
            System.out.println("Not connection...");
            System.exit(0);
        }
    }
}