package command;

import utility.ObjectForServer;
import utility.MovieFactory;
import request.RRHandler;
import utility.Reader;
import utility.Validator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ExecuteScriptCommand extends CommandAbstract {

    Invoker invoker;
    MovieFactory movieFactory;
    private RuntimeException RuntimeException = new RuntimeException();
    HashMap<String, CommandAbstract> commands;
    HashSet<String> files;
    RRHandler rrHandler;


    public ExecuteScriptCommand(HashMap<String, CommandAbstract> commands, Invoker invoker, HashSet<String> files, boolean isArgument, RRHandler rrHandler) {
        super("execute_script", "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", isArgument,rrHandler);
        this.commands = commands;
        this.invoker = invoker;
        this.files = files;
    }

    @Override
    public void execute(String arg) {
        if (files.contains(arg)) {
            System.out.println("Сycleeeeeeeeeeeee");
            return;
        }
        files.add(arg);
        Scanner scannerForFile;
        try {
            scannerForFile = new Scanner(new File(arg));
            Scanner prevScanner = Reader.scanner;
            Reader readerForFile = new Reader(scannerForFile);
            while (scannerForFile.hasNextLine()) {
                boolean start;
                try {
                    Validator.setReader(readerForFile);
                    invoker.execute(readerForFile.read());
                    start = true;
                } catch (RuntimeException e) {
                    start = false;
                }
                if (start) {
                    ObjectForServer response = rrHandler.res();
                    System.out.println(response.toString());
                }
            }
            files.remove(arg);
            scannerForFile.close();
            Reader.setScanner(prevScanner);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        try {
            rrHandler.getSocketChannel().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
