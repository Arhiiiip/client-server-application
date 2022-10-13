package DBManager;

import data.Movie;

import java.io.PrintWriter;
import java.util.LinkedHashSet;

public class SaveToDB {

    public static void saveAndExit(LinkedHashSet<Movie> collection) {
        PrintWriter fileOut = null;
            fileOut.println("<movies>");
            for (Movie movie : collection) {
                fileOut.println("<movie>");
                fileOut.println("<id>" + movie.getId() + "</id>");
                fileOut.println("<name>" + movie.getName() + "</name>");
                fileOut.println("<coordinates>" + "\n" + "<coordinate_x>" + movie.getCoordinates().getX() + "</coordinate_x>" + "\n" + "<coordinate_y>" + movie.getCoordinates().getY() + "</coordinate_y>" + "\n" + "</coordinates>");
                fileOut.println("<creationDate>" + movie.getCreationDate() + "</creationDate>");
                fileOut.println("<oscarsCount>" + movie.getOscarsCount() + "</oscarsCount>");
                fileOut.println("<genre>" + movie.getGenre() + "</genre>");
                fileOut.println("<mpaaRating>" + movie.getMpaaRating() + "</mpaaRating>");
                fileOut.print("<director>" + "\n" +
                        "<name>" + movie.getDirector().getName() + "</name>" + "\n" +
                        "<weight>" + movie.getDirector().getWeight() + "</weight>" + "\n" +
                        "<eyecolor>" + movie.getDirector().getEyeColor() + "</eyecolor>" + "\n" +
                        "<nationality>" + movie.getDirector().getNationality() + "</nationality>" + "\n" +
                        "<location>" + "\n" +
                        "<loc_x>" + movie.getDirector().getLocation().getX() + "</loc_x>" + "\n" +
                        "<loc_y>" + movie.getDirector().getLocation().getY() + "</loc_y>" + "\n" +
                        "<name>" + movie.getDirector().getLocation().getName() + "</name>" + "\n" +
                        "</location>" + "\n" + "</director>" + "\n" + "</movie>" + "\n"
                );
            }
            fileOut.println("</movies>");
            fileOut.close();
        System.exit(0);
    }

}
