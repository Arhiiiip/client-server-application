package utility;

import java.util.HashSet;

public class Validator {

    public static Reader reader;

    public Validator(Reader reader) {
        Validator.reader = reader;
    }

    public static Reader getReader() {
        return reader;
    }

    public static void setReader(Reader reader) {
        Validator.reader = reader;
    }

    public static String validatorName() {
        System.out.print("Введите имя:");
        return reader.read();
    }

    public static long autoCreatAndCheckId(HashSet collection) {
        for (long i = 0, id = 1; i < (collection.size() + 1); i++, id++) {
            if (!collection.contains(id)) {
                collection.add(id);
                return id;
            }
        }
        return 0;
    }
}
