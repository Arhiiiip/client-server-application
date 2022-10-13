package command;

import utility.ObjectForServer;
import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

public class UpdateIdCommand extends CommandAbstract {

    public UpdateIdCommand(boolean isArgument, RRHandler rrHandler) {
        super("update_id", "Обновить значение элемента коллекции, id которого равен заданному", isArgument,rrHandler);
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
            ObjectForServer response = rrHandler.res();
            if(response.isAnswerB()){
                Movie movie = MovieFactory.GetMovieFromConsole();
                rrHandler.reqOb(this.getName(), movie);
            } else {
                System.out.println("Not this id");
                throw new RuntimeException();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
