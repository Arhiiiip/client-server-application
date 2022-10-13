package utility;

import data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class FileWorker {

    HashSet hashSetId = new HashSet();
    String link;

    public FileWorker(String link) {
        this.link = link;
    }

    public LinkedHashSet parse() throws IOException, SAXException, ParserConfigurationException {

        File file = new File(link);
        try {
            LinkedHashSet<Movie> collectionFromFile = new LinkedHashSet<>();
            long tempId = 0;
            String tempMovieName = null;
            Coordinates tempCoordinates = null;
            java.time.LocalDateTime tempCreationDate = null;
            int tempOscarsCount = 0;
            MovieGenre tempGenre = null;
            MpaaRating tempMpaaRating = null;
            Person tempDirector = null;
            int tempCoordinatesX = 0;
            Integer tempCoordinatesY = null;
            String tempPersonName = null;
            Float tempWeight = null;
            Color tempEyeColor = null;
            Country tempNationality = null;
            Location tempLocation = null;
            Integer tempLocationX = null;
            Float tempLocationY = null;
            String tempLocationName = null;
            int countErrorElement = 0;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList movieNodeList = document.getElementsByTagName("movie");

            for (int temp = 0; temp < movieNodeList.getLength(); temp++) {
                Node node = movieNodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    try {
                        tempId = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    tempMovieName = element.getElementsByTagName("name").item(0).getTextContent();

                    NodeList nodeListCoordinates = element.getElementsByTagName("coordinates");
                    Element elemnetCoordinates = (Element) nodeListCoordinates.item(0);

                    try {
                        tempCoordinatesX = Integer.parseInt(elemnetCoordinates.getElementsByTagName("coordinate_x").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempCoordinatesY = Integer.parseInt(elemnetCoordinates.getElementsByTagName("coordinate_y").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        LocalDateTime date = LocalDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent(), formatter);
                        tempCreationDate = date;
                    } catch (DateTimeParseException exception) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempOscarsCount = Integer.parseInt(element.getElementsByTagName("oscarsCount").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempGenre = MovieGenre.valueOf(element.getElementsByTagName("genre").item(0).getTextContent());
                    } catch (Exception exception) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempMpaaRating = MpaaRating.valueOf(element.getElementsByTagName("mpaaRating").item(0).getTextContent());
                    } catch (Exception exception) {
                        countErrorElement += 1;
                        continue;
                    }

                    NodeList nodeListPerson = element.getElementsByTagName("director");
                    Element elementPerson = (Element) nodeListPerson.item(0);

                    tempPersonName = elementPerson.getElementsByTagName("name").item(0).getTextContent();

                    try {
                        tempWeight = Float.parseFloat(element.getElementsByTagName("weight").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempEyeColor = Color.valueOf(element.getElementsByTagName("eyecolor").item(0).getTextContent());
                    } catch (Exception exception) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempNationality = Country.valueOf(element.getElementsByTagName("nationality").item(0).getTextContent());
                    } catch (Exception exception) {
                        countErrorElement += 1;
                        continue;
                    }

                    NodeList nodeListLocation = elementPerson.getElementsByTagName("location");
                    Element elementLocation = (Element) nodeListLocation.item(0);

                    tempLocationName = elementLocation.getElementsByTagName("name").item(0).getTextContent();

                    try {
                        tempLocationX = Integer.parseInt(elementLocation.getElementsByTagName("loc_x").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    try {
                        tempLocationY = Float.parseFloat(elementLocation.getElementsByTagName("loc_y").item(0).getTextContent());
                    } catch (NumberFormatException exeption) {
                        countErrorElement += 1;
                        continue;
                    }

                    collectionFromFile.add(new Movie(tempId, tempMovieName, new Coordinates(tempCoordinatesX, tempCoordinatesY), tempCreationDate,
                            tempOscarsCount, tempGenre, tempMpaaRating, new Person(tempPersonName, tempWeight,
                            tempEyeColor, tempNationality, new Location(tempLocationX, tempLocationY, tempLocationName)), "user"));

                    hashSetId.add(tempId);
                }
            }
            if (countErrorElement != 0) {
                System.out.println("Количество проблемных элементов: " + countErrorElement);
            }
            return collectionFromFile;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не может быть считан, недостаточно прав.\n" +
                    "Я не буду работать");
            System.exit(0);
            return null;
        }
    }

    public void saveAndExit(LinkedHashSet<Movie> collection) {
        PrintWriter fileOut = null;
        try {
            fileOut = new PrintWriter(link);
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
        } catch (FileNotFoundException e) {
            System.out.println("Не достаточно прав для сохранения.");
        }
        System.exit(0);
    }

    public HashSet takeHashSetId() {
        return hashSetId;
    }
}

