package command;

import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

public class AddIfMaxCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AddIfMaxCommand(boolean isArgument, RRHandler rrHandler) {
        super("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции",isArgument,rrHandler);
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
