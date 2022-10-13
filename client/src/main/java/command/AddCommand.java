package command;

import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

/**
 * Класс команды add
 * Которая добавляет элемент в коллекцию
 */

public class AddCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AddCommand(boolean isArgument, RRHandler rrHandler) {
        super("add", "Add new element in collection", isArgument,rrHandler);
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
