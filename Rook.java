import java.util.*;

public class Rook extends Piece {
    public Rook(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        if (color() == Color.BLACK) {
            return "br";
        } 
        return "wr";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);

        DFS(b, m, row, col-1, "l"); // search left
        DFS(b, m, row, col+1, "r"); // searh right
        DFS(b, m, row+1, col, "u"); // search up
        DFS(b, m, row-1, col, "d"); // search down
        
        return m;
    }

    private void DFS(Board b, List<String> m, int row, int col, String d) {
        
        boolean added;
        try {
            added = addMove(b, m, row, col);
        } catch (Exception e) { return; }

        if (added) {
            switch (d) {
                case "l":
                    if (col != 0) { DFS(b, m, row, col-1, d); }
                    break;
                case "r":
                    if (col != 7) { DFS(b, m, row, col+1, d); }
                    break;
                case "u":
                    if (row != 7) { DFS(b, m, row+1, col, d); }
                    break;
                case "d":
                    if (row != 0) { DFS(b, m, row-1, col, d); }
                    break;
            }
        }
    }
 
    private boolean addMove(Board b, List<String> m, int row, int col) {
        // get piece from prospective loc
        String loc = Coord.loc(row, col);
        Piece p = b.getPiece(loc);
        //check if empty space or if piece there is opposite color
        if (p == null || (p != null && p.color() != this.color)) {
            m.add(loc);
            return true;
        }
        return false;
    }

}