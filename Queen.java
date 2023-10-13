import java.util.*;

public class Queen extends Piece {
    public Queen(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        if (color() == Color.BLACK) {
            return "bq";
        } 
        return "wq";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);

        DFS(b, m, row, col-1, "l"); // search left
        DFS(b, m, row, col+1, "r"); // searh right
        DFS(b, m, row+1, col, "u"); // search up
        DFS(b, m, row-1, col, "d"); // search down
        DFS(b, m, row+1, col-1, "dul"); // search top left
        DFS(b, m, row+1, col+1, "dur"); // search top right
        DFS(b, m, row-1, col-1, "ddl"); // search bottom left
        DFS(b, m, row-1, col+1, "ddr"); // serarch bottom right
        
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
                case "dul":
                    if (row != 7 && col != 0) { DFS(b, m, row+1, col-1, d); }
                    break;
                case "dur":
                    if (row != 7 && col != 7) { DFS(b, m, row+1, col+1, d); }
                    break;
                case "ddl": 
                    if (row != 0 && col != 0) { DFS(b, m, row-1, col-1, d); }
                    break;
                case "ddr":
                    if (row != 0 && col != 7) { DFS(b, m, row-1, col+1, d); }
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