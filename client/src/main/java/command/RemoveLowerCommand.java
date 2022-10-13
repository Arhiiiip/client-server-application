package command;

import request.RRHandler;

public class RemoveLowerCommand extends CommandAbstract {

    public RemoveLowerCommand(boolean isArgument, RRHandler rrHandler) {
        super("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный", isArgument,rrHandler);
    }
}
