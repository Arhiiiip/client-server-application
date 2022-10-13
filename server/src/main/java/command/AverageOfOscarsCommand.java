package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class AverageOfOscarsCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AverageOfOscarsCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        try {
            int oscarsCount = movieFactory.getCollectionForWork().stream().map(Movie::getOscarsCount).reduce(0, Integer::sum);

            int res = oscarsCount / movieFactory.getCollectionForWork().size();
            String result;
            result = String.valueOf(res);
            rrHandler.res(result);
        }catch (ArithmeticException e){
            rrHandler.res("The collection is empty, But it is not divisible by zero if you did not study at school.\n" +
                    "If you're going to be smart, we'll turn you off and ban you)");
        }
    }
}
