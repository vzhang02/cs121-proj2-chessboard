import java.util.*;

public class Bishop extends Piece {
    public Bishop(Color c) { 
        this.color = c;
    }

    public String toString() {
        if (color() == Color.BLACK) {
            return "bb";
        } 
        return "wb";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);

        DFS(b, m, row+1, col-1, "lu"); // search left
        DFS(b, m, row-1, col-1, "ld"); // searh right
        DFS(b, m, row+1, col+1, "ru"); // search up
        DFS(b, m, row-1, col+1, "rd"); // search down
        
        return m;
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

    private void DFS(Board b, List<String> m, int row, int col, String d) {
        
        boolean added;
        try {
            added = addMove(b, m, row, col);
        } catch (Exception e) { return; }

        if (added) {
            switch (d) {
                case "lu":
                    if (row != 7 && col != 0) { DFS(b, m, row+1, col-1, d); }
                    break;
                case "ru":
                    if (row != 7 && col != 7) { DFS(b, m, row+1, col+1, d); }
                    break;
                case "ld": 
                    if (row != 0 && col != 0) { DFS(b, m, row-1, col-1, d); }
                    break;
                case "rd":
                    if (row != 0 && col != 7) { DFS(b, m, row-1, col+1, d); }
                    break;
            }
        }
    }

}