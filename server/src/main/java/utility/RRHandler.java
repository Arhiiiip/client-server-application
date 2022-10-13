package utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class RRHandler{

    static Socket socket;
    static Logger logger;

    public RRHandler(Socket socket, Logger logger) {
        RRHandler.socket = socket;
        RRHandler.logger = logger;
    }

//    static ExecutorService executor = new ThreadPoolExecutor(10, Runtime.getRuntime().availableProcessors()/3, 15L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//      static ExecutorService executor = Executors.newCachedThreadPool();

    public static void res(String result) {
        Thread thread = new Thread(new SendingAnswer(result));
        thread.start();
        thread.interrupt();
    }

    public static void res(boolean result) {
        Thread thread = new Thread(new SendingAnswerB(result));
        thread.start();
        thread.interrupt();
    }

    private static class SendingAnswer implements Runnable{

        String result;

        public SendingAnswer(String result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);
                objOut.writeObject(new ObjectForServer(result));
                objOut.flush();
                byteArrayOutputStream.writeTo(outputStream);
                byteArrayOutputStream.reset();
                logger.info("Response to port " + socket.getPort() + " in " + Thread.currentThread().getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static class SendingAnswerB implements Runnable{

        boolean result;

        public SendingAnswerB(boolean result) {
            this.result = result;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);
                objOut.writeObject(new ObjectForServer(result));
                objOut.flush();
                byteArrayOutputStream.writeTo(outputStream);
                byteArrayOutputStream.reset();
                logger.info("Response var boolean to port " + socket.getPort() + " in " + Thread.currentThread().getId());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static class MyFormatter extends Formatter{
        @Override
        public String format(LogRecord record){
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        RRHandler.socket = socket;
    }
}


