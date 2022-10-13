package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClearCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public ClearCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        if (!(movieFactory.getCollectionForWork().size() == 0)) {
            movieFactory.getCollectionForWork().clear();
            movieFactory.getCollectionManager().setDateUpdate();
            String login = arg.getLogin();
            try {
                String reqPR = "select director from \"Movie\" where user = ?";
                PreparedStatement ptPR = movieFactory.getConnection().prepareStatement(reqPR);
                ptPR.setString(1, login);
                ptPR.execute();
                ResultSet rs = ptPR.getResultSet();
                if (rs.next()){
                    Integer idP = rs.getInt(1);
                    String reqDP = "delete from \"Person\" where id = ?";
                    PreparedStatement ptDP = movieFactory.getConnection().prepareStatement(reqDP);
                    ptDP.setInt(1, idP);
                    ptDP.execute();

                    String req = "delete from \"Movie\" where user = ?";
                    PreparedStatement pt = movieFactory.getConnection().prepareStatement(req);
                    pt.setString(1, login);
                    pt.execute();
                }else{
                    System.out.println("You cant do it");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            rrHandler.res("Collection is empty");
        }
        rrHandler.res("Collection was clear");
    }
}
