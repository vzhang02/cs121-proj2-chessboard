/*
 *  Coord.java
 *  by: Valerie Zhang
 *
 *  The Coord class contains a list of methods to transform a string with a 
 *  chess board location into xy coordinates
 * 
 */

public class Coord {
    // gets row in int form
    public static int xcoord(String loc) {
        return loc.charAt(1) - '1';
    }
    
    // get col in int form
    public static int ycoord(String loc) {
        return loc.charAt(0) - 'a';
    }

    // get row in char form
    public static char row(int xcoord) {
        return (char) (xcoord + '1');
    }

    // get col in char form
    public static char col(int ycoord) {
        return (char) (ycoord + 'a');
    }

    // get loc in string form
    public static String loc(int xcoord, int ycoord) {
        return String.valueOf(col(ycoord) + String.valueOf(row(xcoord)));
    }
}
