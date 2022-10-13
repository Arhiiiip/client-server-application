package command;

import data.Movie;
import data.MovieGenre;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.Arrays;

import static data.MovieGenre.*;

public class CountGreaterGenreCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public CountGreaterGenreCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long countDrama = movieFactory.getCollectionForWork().stream().map(Movie::getGenre).filter(DRAMA::equals).count();
        long countWestern = movieFactory.getCollectionForWork().stream().map(Movie::getGenre).filter(TRAGEDY::equals).count();
        long countTragedy = movieFactory.getCollectionForWork().stream().map(Movie::getGenre).filter(WESTERN::equals).count();
        long countScienceFiction = movieFactory.getCollectionForWork().stream().map(Movie::getGenre).filter(SCIENCE_FICTION::equals).count();

        MovieGenre argUser = MovieGenre.valueOf(arg.getArg());

        long countUserGenre = movieFactory.getCollectionForWork().stream().map(Movie::getGenre).filter(argUser::equals).count();

        long[] arr = {countDrama, countTragedy, countWestern, countScienceFiction};

        long countMore = Arrays.stream(arr).filter(value -> countUserGenre < value).count();

        String result = String.valueOf(countMore);
        rrHandler.res(result);
    }
}
