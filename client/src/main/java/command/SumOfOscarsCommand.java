package command;

import request.RRHandler;

public class SumOfOscarsCommand extends CommandAbstract {

    public SumOfOscarsCommand(boolean isArgument, RRHandler rrHandler) {
        super("sum_of_oscars_count", "Вывести сумму значений поля oscarsCount для всех элементов коллекции", isArgument, rrHandler);
    }
}