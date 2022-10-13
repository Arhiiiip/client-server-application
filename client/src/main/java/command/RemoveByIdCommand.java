package command;

import request.RRHandler;

public class RemoveByIdCommand extends CommandAbstract {

    public RemoveByIdCommand(boolean isArgument, RRHandler rrHandler) {
        super("remove_by_id", "Удалить элемент из коллекции по его id", isArgument,rrHandler);
    }
}
