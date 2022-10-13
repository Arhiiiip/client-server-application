package utility;

import data.Color;
import data.Country;
import data.MovieGenre;
import data.MpaaRating;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class Validator {

    public static Reader reader;

    public Validator(Reader reader) {
        this.reader = reader;
    }

    public static Reader getReader() {
        return reader;
    }

    public static void setReader(Reader reader) {
        Validator.reader = reader;
    }

    public static String validatorName() {
        System.out.println("Введите имя:");
        return reader.read();
    }

    public static Integer validatorCoordinateX() {
        int x = 317;
        try{
        while (x > 316) {
            System.out.println("Введите координату Х, она не должна быть больше 316:");
            x = Integer.parseInt(reader.read());
            if (x > 316) {
                System.out.println("X не может быть больше 316");
            }
        }
    } catch (NumberFormatException exception) {
        System.out.println("Значение должно быть целочисленное, типа Integer");
        return validatorCoordinateX();
    }
        return x;
    }

    public static Integer validatorCoordinateY() {
        System.out.println("Введите координату Y:");
        String y = reader.read();
        Integer y2;
        try {
            y2 = Integer.parseInt(y);
            return y2;
        } catch (NumberFormatException exception) {
            System.out.println("Значение должно быть целочисленное, типа Integer");
            return validatorCoordinateY();
        }
    }

    public static MovieGenre validatorMovieGenre() {
        System.out.println("Введите жанр фильма(WESTERN, DRAMA, TRAGEDY, SCIENCE_FICTION):");
        String line = reader.read();
        if (MovieGenre.isIncludeElement(line)) {
            return MovieGenre.valueOf(line.toUpperCase());
        } else {
            System.out.println("Такого жанра нет");
            return validatorMovieGenre();
        }
    }

    public static Color validatorColor() {
        System.out.println("Введите цвет глаз(RED, GREEN, BLACK, BLUE, ORANGE)");
        String line = reader.read();
        if (Color.isIncludeElement(line)) {
            return Color.valueOf(line.toUpperCase());
        } else {
            System.out.println("Такого жанра нет");
            return validatorColor();
        }
    }


    public static Country validatorCountry() {
        System.out.println("Введите страну:(RUSSIA, GERMANY, FRANCE)");
        String line = reader.read();
        if (Country.isIncludeElement(line)) {
            return Country.valueOf(line.toUpperCase());
        } else {
            System.out.println("Такой жанра нет");
            return validatorCountry();
        }
    }

    public static MpaaRating validatorMpaaRating() {
        System.out.println("Введите MpaaRating(G, PG, PG_13, R, NC_17):");
        String line = reader.read();
        if (MpaaRating.isIncludeElement(line)) {
            return MpaaRating.valueOf(line.toUpperCase());
        } else {
            System.out.println("Такого рейтинга нет");
            return validatorMpaaRating();
        }
    }

    public static Float validatorWeight() {
        try {
            System.out.println("Введите высоту:");
            String line = reader.read();
            if (Float.parseFloat(line) > 0) {
                return Float.parseFloat(line);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Неверно введены данные. Высота не должна быть словом или быть меньше 0.");
            return validatorWeight();
        }
        return null;
    }

    public static Integer validatorLocationX() {
        System.out.println("Введите координату локации Х:");
        String x = reader.read();
        Integer x2 = null;
        try {
            x2 = Integer.parseInt(x);
            return x2;
        } catch (NumberFormatException exception) {
            System.out.println("Значение должно быть целочисленное, типа Integer");
            return validatorLocationX();
        }
    }

    public static Float validatorLocationY() {
        System.out.println("Введите координату локации Y:");
        String y = reader.read();
        Float y2 = null;
        try {
            y2 = Float.parseFloat(y);
            return y2;
        } catch (NumberFormatException exception) {
            System.out.println("Значение должно быть целочисленное, типа Integer");
            return validatorLocationY();
        }
    }

    public static long autoCreatAndCheckId(HashSet collection) {
//        System.out.println("Введите айди, а нет идите нахуй, айди сам вводится.");
        for (long i = 0, id = 1; i < (collection.size() + 1); i++, id++) {
            if (!collection.contains(id)) {
                collection.add(id);
                return id;
            }
        }
        return 0;
    }

    public static LocalDateTime autoCreatAndCheckDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        LocalDateTime date = LocalDateTime.now();
        CharSequence dateFormate = date.format(formatter);
        return LocalDateTime.parse(dateFormate, formatter);
    }

    public static Integer validatorOscarsCount() {
        System.out.println("Введите количество оскаров:");
        String count = reader.read();
        Integer countOscars = null;
        try {
            countOscars = Integer.parseInt(count);
            return countOscars;
        } catch (NumberFormatException exception) {
            System.out.println("Значение должно быть целочисленное, типа Integer");
            return validatorOscarsCount();
        }
    }
}
