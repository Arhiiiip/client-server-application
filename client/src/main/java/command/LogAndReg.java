package command;

import request.RRHandler;
import utility.Reader;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class LogAndReg {

    public Map<String, CommandAbstract> commandsR;
    public Reader reader;
    public SocketChannel client;
    RRHandler rrHandler;


    public LogAndReg(RRHandler rrHandler, Reader reader) {
        commandsR = new HashMap<>();
        this.reader = reader;
        this.rrHandler = rrHandler;
        initCommands();
    }

    public LogAndReg(){
        commandsR = new HashMap<>();
        initCommands();
    }

    public void initCommands() {
        commandsR.put("login", new Login("login", " - for extrance", false, rrHandler, reader));
        commandsR.put("register", new Registration("register", " - for registration", false, rrHandler, reader));
    }

    public boolean execute(String command, String arg) throws IOException {
        String[] parts = command.split(" ");
        if (commandsR.containsKey(parts[0])) {
            if (parts.length == 2) {
                System.out.println("No argument required");
                return false;
            } else if (parts.length == 1){
                return commandsR.get(parts[0]).executeR(arg);
            } else {
                System.out.println("The command definitely does not require so many arguments");
                return false;
            }
        } else {
            System.out.println("Такой команды нет");
            return false;
        }
    }

    public boolean validCommand(String commandVal){
        if (commandsR.containsKey(commandVal.trim())){
            return true;
        }else{
            return false;
        }
    }
}
