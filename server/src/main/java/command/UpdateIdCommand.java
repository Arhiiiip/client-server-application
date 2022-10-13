package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateIdCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public UpdateIdCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.parseLong(arg.getArg());

        List<Movie> mov = movieFactory.getCollectionForWork().stream().filter(value -> value.getId() == idFromUser).collect(Collectors.toList());
        try {
            String req = "select filmname from \"Movie\" where (\"user\" = ? and num = ?)";
            PreparedStatement pt = movieFactory.getConnection().prepareStatement(req);
            pt.setString(1, arg.getLogin());
            pt.setLong(2, idFromUser);
            pt.execute();
            ResultSet rs = pt.getResultSet();

            if (rs.next()) {
                RRHandler.res(true);
                try {
                    InputStream stream;
                    stream = RRHandler.getSocket().getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                    ObjectForServer command = (ObjectForServer) objectInputStream.readObject();
                    Movie movieForChange = command.getMovie();
                    movieForChange.setId(idFromUser);

                    try {
                        String reqPR = "select director from \"Movie\" where num = ?";
                        PreparedStatement ptPR = movieFactory.getConnection().prepareStatement(reqPR);
                        ptPR.setLong(1, idFromUser);
                        ptPR.execute();
                        ResultSet resultSet = ptPR.getResultSet();
                        if (resultSet.next()) {
                            int person = resultSet.getInt(1);
                            String reqP = "update \"Person\" set name = ?, weight = ?, eyecolor = ?, nationality = ?, location_name = ?, location_x = ?, location_y = ? where id = ?";
                            PreparedStatement ptP = movieFactory.getConnection().prepareStatement(reqP);
                            ptP.setString(1, movieForChange.getDirector().getName());
                            ptP.setDouble(2, movieForChange.getDirector().getWeight());
                            ptP.setInt(3, movieForChange.getDirector().getEyeColor().ordinal());
                            ptP.setInt(4, movieForChange.getDirector().getNationality().ordinal());
                            ptP.setString(5, movieForChange.getDirector().getLocation().getName());
                            ptP.setInt(6, movieForChange.getDirector().getLocation().getX());
                            ptP.setDouble(7, movieForChange.getDirector().getLocation().getY());
                            ptP.setInt(8, person);
                            ptP.execute();
                            String reqM = "update \"Movie\" set filmname = ?, coordinates_x = ?, coordinates_y = ?, date = ?, genre = ?, mpaarating = ?, director = ?, oscarscount = ? where num = ?";
                            PreparedStatement ptM = movieFactory.getConnection().prepareStatement(reqM);
                            ptM.setLong(1, movieForChange.getId());
                            ptM.setString(1, movieForChange.getName());
                            ptM.setInt(2, movieForChange.getCoordinates().getX());
                            ptM.setInt(3, movieForChange.getCoordinates().getY());
                            ptM.setString(4, movieForChange.getCreationDate().toString());
                            ptM.setInt(5, movieForChange.getGenre().ordinal());
                            ptM.setInt(6, movieForChange.getMpaaRating().ordinal());
                            ptM.setInt(7, person);
                            ptM.setInt(8, movieForChange.getOscarsCount());
                            ptM.setLong(9, idFromUser);
                            ptM.execute();

                            movieFactory.getCollectionForWork().remove(mov.get(0));
                            movieFactory.getCollectionManager().setDateUpdate();
                            movieFactory.getCollectionForWork().add(movieForChange);

                            RRHandler.res("Element was updated");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("OChKo");
                        RRHandler.res("Element dead");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                RRHandler.res(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            RRHandler.res(false);
        }
    }
}
