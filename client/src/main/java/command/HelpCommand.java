package command;

import request.RRHandler;

import java.io.IOException;
import java.util.Map;

public class HelpCommand extends CommandAbstract{

    private final transient Map<String, CommandAbstract> commands;
    RRHandler rrHandler;

    public HelpCommand(Map<String, CommandAbstract> commands, boolean isArgument, RRHandler rrHandler) {
        super("help", "Вывести справку по доступным командам", isArgument,rrHandler);
        this.commands = commands;
    }

    @Override
    public void execute(String arg) {
        for (Map.Entry<String, CommandAbstract> entry : commands.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        try {
            rrHandler.req(this.getName(), arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
