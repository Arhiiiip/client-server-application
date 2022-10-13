package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.Map;

public class HelpCommand extends CommandAbstract {
    private Map<String, CommandAbstract> commands;

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public HelpCommand(MovieFactory movieFactory, Map<String, CommandAbstract> commands, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.commands = commands;
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        rrHandler.res("");
    }
}

