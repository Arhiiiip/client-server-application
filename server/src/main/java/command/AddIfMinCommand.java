package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;
import utility.Validator;

public class AddIfMinCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AddIfMinCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        Movie movieForAdd = arg.getMovie();
        movieForAdd.setId(Validator.autoCreatAndCheckId(movieFactory.getHashSetId()));
        int oscarsCountFromUser = movieForAdd.getOscarsCount();
        int minOscarsCount = Integer.MAX_VALUE;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getOscarsCount() < minOscarsCount) {
                minOscarsCount = movie.getOscarsCount();
            }
        }
        if (oscarsCountFromUser < minOscarsCount) {
            movieFactory.getCollectionForWork().add(movieForAdd);
            movieFactory.getCollectionManager().setDateUpdate();
            rrHandler.res("Элемент добавлен");
        } else {
            rrHandler.res("Таких элементов нет");
        }
    }
}
