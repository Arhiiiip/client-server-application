package data;

import java.io.Serializable;

public enum MpaaRating implements Serializable {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    public static boolean isIncludeElement(String aMpaaRating) {
        for (MpaaRating current : MpaaRating.values()) {
            if (current.equals(aMpaaRating.toUpperCase())) return true;
        }
        return false;
    }

    public boolean equals(String arg) {
        MpaaRating mpaaRating = this;
        String genreForEquals = String.valueOf(mpaaRating);
        return (genreForEquals.equals(arg));
    }
}
