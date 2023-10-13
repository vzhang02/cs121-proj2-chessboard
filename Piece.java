import java.util.*;

abstract public class Piece {
    
    protected Color color; 
    private static HashMap<Character, PieceFactory> pieces = new HashMap<>();

    public static void registerPiece(PieceFactory pf) {
        pieces.put(pf.symbol(), pf);
    }

    public static Piece createPiece(String name) {
        if (name.charAt(0) != 'b' && name.charAt(0) != 'w') {
            throw new IllegalArgumentException("invalid piece color");
        }
        if (name.charAt(0) == 'b') {
            return (pieces.get(name.charAt(1))).create(Color.BLACK);
        }
        return (pieces.get(name.charAt(1))).create(Color.WHITE);
    }

    public Color color() {
	    return color; 
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);
}