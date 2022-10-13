package utility;


import data.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovieFactory implements Serializable {

    static String login;

    public MovieFactory(String login) {
        this.login = login;
    }

    public static Movie GetMovieFromConsole() {
        String movieName;
        Coordinates coordinates;
        int oscarsCount;
        MovieGenre genre;
        MpaaRating mpaaRating;
        Person director;
        int coordinatesX;
        Integer coordinatesY;
        String personName;
        Float weight;
        Color eyeColor;
        Country nationality;
        Location location;
        Integer locationX;
        Float locationY;
        String locationName;

        movieName = Validator.validatorName();
        coordinatesX = Validator.validatorCoordinateX();
        coordinatesY = Validator.validatorCoordinateY();
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        LocalDateTime creationDate = Validator.autoCreatAndCheckDate();
        oscarsCount = Validator.validatorOscarsCount();
        genre = Validator.validatorMovieGenre();
        mpaaRating = Validator.validatorMpaaRating();
        personName = Validator.validatorName();
        weight = Validator.validatorWeight();
        eyeColor = Validator.validatorColor();
        nationality = Validator.validatorCountry();
        locationName = Validator.validatorName();
        locationX = Validator.validatorLocationX();
        locationY = Validator.validatorLocationY();
        location = new Location(locationX, locationY, locationName);
        director = new Person(personName, weight, eyeColor, nationality, location);
        return new Movie(0, movieName, coordinates, creationDate, oscarsCount, genre, mpaaRating, director, login);

    }
}
