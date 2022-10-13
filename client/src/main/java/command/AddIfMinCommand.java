package command;

import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

public class AddIfMinCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AddIfMinCommand(boolean isArgument, RRHandler rrHandler) {
        super("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", isArgument,rrHandler);
    }

    @Override
    public void execute(String arg) {
        Movie movie = MovieFactory.GetMovieFromConsole();
        try {
            rrHandler.reqOb(this.getName(), movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
