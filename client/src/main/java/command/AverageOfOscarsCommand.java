package command;

import request.RRHandler;

import java.io.IOException;

public class AverageOfOscarsCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AverageOfOscarsCommand(boolean isArgument, RRHandler rrHandler) {
        super("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции", isArgument,rrHandler);
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
