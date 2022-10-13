package command;

import request.RRHandler;

public class InfoCommand extends CommandAbstract {

    public InfoCommand(boolean isArgument, RRHandler rrHandler) {
        super("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", isArgument,rrHandler);
    }
}
