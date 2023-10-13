import java.util.*;

public class Pawn extends Piece {
    public Pawn(Color c) { 
        this.color = c;
    }

    public String toString() {
        if (color() == Color.BLACK) {
            return "bp";
        } 
        return "wp";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);

        if (this.color == Color.BLACK) {
            if (row == 6) { addMove(b, m, row-2, col, 'f'); }
            addMove(b, m, row-1, col, 'f');
            addMove(b, m, row-1, col-1, 'd');
            addMove(b, m, row-1, col+1, 'd');
        } else if (this.color == Color.WHITE) {
            if (row == 1) { addMove(b, m, row+2, col, 'f'); }
            addMove(b, m, row+1, col, 'f');
            addMove(b, m, row+1, col-1, 'd');
            addMove(b, m, row+1, col+1, 'd');
        }
        
        return m;
    }

    private void addMove(Board b, List<String> m, int row, int col, char c) {
        // get piece from prospective loc
        String loc = Coord.loc(row, col);
        Piece p;
        try {
            p = b.getPiece(loc);
        } catch (Exception e) { return; }

        //check if empty space or if piece there is opposite color
        if (c == 'f') {
            if (p == null) {
                m.add(loc);
            }
        }
        if (c == 'd') {
            if (p != null && p.color() != this.color) {
                m.add(loc);
            }
        }
    }

}