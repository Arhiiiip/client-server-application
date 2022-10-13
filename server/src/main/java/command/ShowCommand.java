package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public ShowCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String finalResult = "";
        List<String> listForShow = movieFactory.getCollectionForWork().stream().sorted(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.compareTo(o2);
            }
        }).map(value -> value.toString()).collect(Collectors.toList());

        for(String str: listForShow){
            finalResult += str;
        }

        rrHandler.res(finalResult);
    }
}
