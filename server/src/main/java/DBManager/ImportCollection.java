package DBManager;

import data.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class ImportCollection {

    Connection connection;
    HashSet hashSetId = new HashSet();

    public ImportCollection(Connection connection) {
        this.connection = connection;
    }

    public LinkedHashSet<Movie> getCollectionFromDB() throws SQLException {
//        Statement statement = connection.createStatement();
//        String SQL_IMPORT_COLLECTION = "select * from Movie order by id desc";
        String getOneRow = "select num, filmname, coordinates_x, coordinates_y, date, genre, mpaarating, name, weight, eyecolor, nationality, location_name, location_x, location_y, oscarscount, \"user\" from \"Movie\" as M inner join \"Person\" P on P.id = M.director";
        PreparedStatement ps = connection.prepareStatement(getOneRow);
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        LinkedHashSet<Movie> collection = new LinkedHashSet<>();

        while (resultSet.next()){
            Movie movie = new Movie(1, "name", new Coordinates(1,2),LocalDateTime.now(), 1, MovieGenre.DRAMA, MpaaRating.G,
                    new Person("name", 1.5F,Color.BLUE, Country.RUSSIA, new Location(1, 2F, "name")), "user");
            Person person = new Person("name", 1.5F,Color.BLUE, Country.RUSSIA, new Location(1, 2F, "name"));
            movie.setId(resultSet.getInt(1));
            movie.setName(resultSet.getString(2));
            movie.setCoordinates(new Coordinates(resultSet.getInt(3), resultSet.getInt(4)));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            movie.setCreationDate(LocalDateTime.parse(resultSet.getString(5), formatter));
            movie.setGenre(MovieGenre.values()[resultSet.getInt(6)]);
            movie.setMpaaRating(MpaaRating.values()[resultSet.getInt(7)]);
            person.setName(resultSet.getString(8));
            person.setWeight(resultSet.getFloat(9));
            person.setEyeColor(Color.values()[resultSet.getInt(10)]);
            person.setNationality(Country.values()[resultSet.getInt(11)]);
            person.setLocation(new Location(resultSet.getInt(13), resultSet.getFloat(14), resultSet.getString(12)));
            movie.setOscarsCount(resultSet.getInt(15));
            movie.setDirector(person);
            movie.setUser(resultSet.getString(16));
            hashSetId.add(movie.getId());
            collection.add(movie);
        }
    return  collection;
    }

    public HashSet getHashSetId() {
        return hashSetId;
    }
}
