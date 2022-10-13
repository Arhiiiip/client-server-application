package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public SaveCommand(MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String link = movieFactory.getCollectionManager().getLink();
        PrintWriter fileOut;
        try {
            fileOut = new PrintWriter(link);
            fileOut.println("<movies>");
            PrintWriter finalFileOut = fileOut;
            movieFactory.getCollectionForWork().forEach((value) -> finalFileOut.println("<movie>" + "\n" +
                    "<id>" + value.getId() + "</id>" + "\n" +
                    "<name>" + value.getName() + "</name>" + "\n" +
                    "<coordinates>" + "\n" + "<coordinate_x>" + value.getCoordinates().getX() + "</coordinate_x>" + "\n" + "<coordinate_y>" + value.getCoordinates().getY() + "</coordinate_y>" + "\n" + "</coordinates>" + "\n" +
                    "<creationDate>" + value.getCreationDate() + "</creationDate>" + "\n" +
                    "<oscarsCount>" + value.getOscarsCount() + "</oscarsCount>" + "\n" +
                    "<genre>" + value.getGenre() + "</genre>" + "\n" +
                    "<mpaaRating>" + value.getMpaaRating() + "</mpaaRating>" + "\n" +
                    "<director>" + "\n" +
                    "<name>" + value.getDirector().getName() + "</name>" + "\n" +
                    "<weight>" + value.getDirector().getWeight() + "</weight>" + "\n" +
                    "<eyecolor>" + value.getDirector().getEyeColor() + "</eyecolor>" + "\n" +
                    "<nationality>" + value.getDirector().getNationality() + "</nationality>" + "\n" +
                    "<location>" + "\n" +
                    "<loc_x>" + value.getDirector().getLocation().getX() + "</loc_x>" + "\n" +
                    "<loc_y>" + value.getDirector().getLocation().getY() + "</loc_y>" + "\n" +
                    "<name>" + value.getDirector().getLocation().getName() + "</name>" + "\n" +
                    "</location>" + "\n" + "</director>" + "\n" + "</movie>" + "\n"
            ));
            fileOut.println("</movies>");
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не достаточно прав для сохранения.");
        }
//            for (Movie value : movieFactory.getCollectionForWork()) {
//                fileOut.println("<movie>" + "\n" +
//                        "<id>" + value.getId() + "</id>" + "\n" +
//                        "<name>" + value.getName() + "</name>" + "\n" +
//                        "<coordinates>" + "\n" + "<coordinate_x>" + value.getCoordinates().getX() + "</coordinate_x>" + "\n" + "<coordinate_y>" + value.getCoordinates().getY() + "</coordinate_y>" + "\n" + "</coordinates>" + "\n" +
//                        "<creationDate>" + value.getCreationDate() + "</creationDate>" + "\n" +
//                        "<oscarsCount>" + value.getOscarsCount() + "</oscarsCount>" + "\n" +
//                        "<genre>" + value.getGenre() + "</genre>" + "\n" +
//                        "<mpaaRating>" + value.getMpaaRating() + "</mpaaRating>" + "\n" +
//                        "<director>" + "\n" +
//                        "<name>" + value.getDirector().getName() + "</name>" + "\n" +
//                        "<weight>" + value.getDirector().getWeight() + "</weight>" + "\n" +
//                        "<eyecolor>" + value.getDirector().getEyeColor() + "</eyecolor>" + "\n" +
//                        "<nationality>" + value.getDirector().getNationality() + "</nationality>" + "\n" +
//                        "<location>" + "\n" +
//                        "<loc_x>" + value.getDirector().getLocation().getX() + "</loc_x>" + "\n" +
//                        "<loc_y>" + value.getDirector().getLocation().getY() + "</loc_y>" + "\n" +
//                        "<name>" + value.getDirector().getLocation().getName() + "</name>" + "\n" +
//                        "</location>" + "\n" + "</director>" + "\n" + "</movie>" + "\n"
//                );
//            }
    }
}
