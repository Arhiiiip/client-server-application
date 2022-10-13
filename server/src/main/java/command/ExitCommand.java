package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class ExitCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public ExitCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        System.exit(0);
    }
}
