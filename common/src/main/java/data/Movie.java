package data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс Movie, объекты которого и хранятся в коллекции
 */

public class Movie implements Serializable, Comparable<Movie> {

    /** id - Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
     * Значение этого поля должно генерироваться автоматически */
    private long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле может быть null
    private String user;

    public Movie(long id, String name, Coordinates coordinates, LocalDateTime creationDate, int oscarsCount,
                 MovieGenre genre, MpaaRating mpaaRating, Person director, String user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.director = director;
        this.user = user;
    }

    public Movie(long aId) {
        this.id = aId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setOscarsCount(int oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getOscarsCount() {
        return oscarsCount;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public Person getDirector() {
        return director;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "\n" + "id:" + id + "\n" + name + "\n" + coordinates.toString() + "\n" + "Дата создания: " + creationDate
                + "\nКоличество оскаров: " + oscarsCount + "\nЖанр: " + genre + "\nMpaaRating: " + mpaaRating
                + "\nDirector: " + director.toString() + "\n" + "User:" + user + "\n";
    }

    @Override
    public int compareTo(Movie o) {
        return ((this.getCoordinates().getX() - o.getCoordinates().getY()) + (this.getCoordinates().getX() - o.getCoordinates().getY()));
    }


}
