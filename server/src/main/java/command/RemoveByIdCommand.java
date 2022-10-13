package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveByIdCommand extends CommandAbstract {

    private final MovieFactory movieFactory;
    RRHandler rrHandler;

    public RemoveByIdCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.parseLong(arg.getArg());
        String loginUser = arg.getLogin();


        List<Movie> colMovieForDel = movieFactory.getCollectionForWork().stream().filter(value -> value.getId() == idFromUser).collect(Collectors.toList());

        if (colMovieForDel.size() == 1) {
            Movie movieForDel = colMovieForDel.get(0);
            String movieUser = movieForDel.getUser();
            if (loginUser.equals(movieUser)) {
                movieFactory.getCollectionForWork().remove(movieForDel);
                movieFactory.getCollectionManager().setDateUpdate();
                RRHandler.res("Element with ID" + idFromUser + "was delete");
            }else{
                RRHandler.res("Это не твоё");
            }
        } else {
            RRHandler.res("Such an idea to send your mother away, HE IS NOT THERE");
        }
    }
}
