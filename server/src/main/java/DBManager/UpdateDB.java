package DBManager;

import data.Movie;

import java.sql.Connection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class UpdateDB {

    public Connection connection;

    public UpdateDB(Connection connection) {
        this.connection = connection;
    }

    public void update(LinkedHashSet<Movie> moviesLinkedHashSet, HashSet hashSetId){
    }
}
