import DBManager.ImportCollection;
import utility.CollectionManager;
import utility.MovieFactory;
import utility.Run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

/**
 * Lab_5
 *
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {

    private static final String DB_USERNAME = "s336947";
//    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:postgresql://localhost:8080/studs";

    public static void main(String[] args) throws IOException, SQLException {
        Scanner scannerr = new Scanner(System.in);
        final String DB_PASSWORD = scannerr.nextLine();
        final int PORT = 8090;
        scannerr.close();
        ServerSocket serverSocket = new ServerSocket(PORT);

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        ImportCollection importCollection = new ImportCollection(connection);
        CollectionManager collectionManager = new CollectionManager(importCollection.getCollectionFromDB(), LocalDateTime.now(), LocalDateTime.now(), 0);
        HashSet hashSetId = importCollection.getHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager, connection);
//        UpdateDB updateDB = new UpdateDB(connection);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> SaveToDB.saveAndExit(collectionManager.getMoviesLinkedHashSet())));
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new MyFormatter());
        Logger logger = Logger.getLogger("Accepter");
        Logger loggerE = Logger.getLogger("Executor");
        Logger loggerR = Logger.getLogger("Request");
        loggerE.setUseParentHandlers(false);
        loggerE.addHandler(consoleHandler);
        loggerR.setUseParentHandlers(false);
        loggerR.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()/3);
        while (true) {
            Socket clientConnect = serverSocket.accept();
//            Logger logger = Logger.getLogger(LoggingJul.class.getName());
            logger.info("Request from " + clientConnect.getPort() + " in " + Thread.currentThread().getId());
            executor.execute(new Run(clientConnect, movieFactory, loggerE, loggerR));
        }
    }
    public static class MyFormatter extends Formatter {
        @Override
        public String format(LogRecord record){
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }
}