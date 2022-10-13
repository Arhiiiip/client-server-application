package command;

import request.RRHandler;

public class CountGreaterGenreCommand extends CommandAbstract {

    public CountGreaterGenreCommand(boolean isArgument, RRHandler rrHandler) {
        super("count_greater_than_genre", "Вывести количество элементов, значение поля genre которых больше заданного", isArgument,rrHandler);
    }
}
