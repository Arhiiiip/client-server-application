package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;
import utility.Validator;

/**
 * Класс команды add
 * Которая добавляет элемент в коллекцию
 */

public class AddCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AddCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }


    /**
     * Iсполнение команды add
     * @param arg
     */
    @Override
    public void execute(ObjectForServer arg) {
        Movie movie = arg.getMovie();
        movie.setId(Validator.autoCreatAndCheckId(movieFactory.getHashSetId()));
        movieFactory.getCollectionForWork().add(movie);
        movieFactory.getCollectionManager().setDateUpdate();
        rrHandler.res("Element was added");
    }
}
