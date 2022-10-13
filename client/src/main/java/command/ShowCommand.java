package command;

import request.RRHandler;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(boolean isArgument, RRHandler rrHandler) {
        super("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении", isArgument,rrHandler);
    }
}
