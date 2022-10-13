package utility;


import data.Movie;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MovieFactory {
    private Long id;
    HashSet hashSetId;
    LinkedHashSet<Movie> collectionForWork;
    CollectionManager collectionManager;
    Connection connection;


    public MovieFactory(HashSet hashSetId, CollectionManager collectionManager, Connection connection) {
        this.hashSetId = hashSetId;
        this.collectionManager = collectionManager;
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Long getId() {
        return id;
    }

    public Collection<Movie> getCollectionForWork() {
        return collectionManager.getMoviesLinkedHashSet();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashSet getHashSetId() {
        return hashSetId;
    }

    public void setHashSetId(HashSet hashSetId) {
        this.hashSetId = hashSetId;
    }

    public void setCollectionForWork(LinkedHashSet<Movie> collectionForWork) {
        this.collectionForWork = collectionForWork;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}