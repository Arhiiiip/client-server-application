package command;

import request.RRHandler;

import java.io.IOException;

public class ClearCommand extends CommandAbstract {

    RRHandler rrHandler;

    public ClearCommand(boolean isArgument, RRHandler rrHandler) {
        super("clear", "Очистить коллекцию", isArgument,rrHandler);
    }
}
