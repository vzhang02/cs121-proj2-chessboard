import java.util.*;

public class King extends Piece {
    public King(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        if (color() == Color.BLACK) {
            return "bk";
        } 
        return "wk";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);
        if (row == 0) { // checks if first row
            if (col == 0) { // bottom-left index
                addMove(b, m, row+1, col);
                addMove(b, m, row+1, col+1);
                addMove(b, m, row, col+1);
                return m;
            } else if (col == 7) { // bottom-right index
                addMove(b, m, row+1, col);
                addMove(b, m, row+1, col-1);
                addMove(b, m, row, col-1);
                return m;
            } else {
                addRowAdjacent(b, m, row, col);
                addRowAbove(b, m, row, col);
                return m;
            }
        } else if (row == 7) { // checks if last row
            if (col == 0) { // top-left index
                addMove(b, m, row, col+1);
                addMove(b, m, row-1, col);
                addMove(b, m, row-1, col+1);
                return m;
            } else if (col == 7) { // top-right index
                addMove(b, m, row, col-1);
                addMove(b, m, row-1, col);
                addMove(b, m, row-1, col-1);
                return m;
            } else {
                addRowAdjacent(b, m, row, col);
                addRowBelow(b, m, row, col);
                return m;
            }
        } else {
            if (col == 0) { // along leftmost col
                addColAdjacent(b, m, row, col);
                addColRight(b, m, row, col);
                return m;
            } else if (col == 7) { // along rightmost col
                addColAdjacent(b, m, row, col);
                addColLeft(b, m, row, col);
                return m;
            }
        }
        addRowBelow(b, m, row, col);
        addRowAdjacent(b, m, row, col);
        addRowAbove(b, m, row, col);
        return m;
    }

    private void addMove(Board b, List<String> m, int row, int col) {
        // get piece from prospective loc
        String loc = Coord.loc(row, col);
        Piece p = b.getPiece(loc);
        //check if empty space or if piece there is opposite color
        if (p == null || (p != null && p.color() != this.color)) {
            m.add(loc);
        }
    }
    // adds left and right indices in the same row
    private void addRowAdjacent (Board b, List<String> m, int row, int col) {
        addMove(b, m, row, col-1);
        addMove(b, m, row, col+1);
    }

    // adds all indices in the row below
    private void addRowBelow(Board b, List<String> m, int row, int col) {
        addMove(b, m, row-1, col-1);
        addMove(b, m, row-1, col);
        addMove(b, m, row-1, col+1);
    }

    // adds all indices in the row above
    private void addRowAbove(Board b, List<String> m, int row, int col) {
        addMove(b, m, row+1, col-1);
        addMove(b, m, row+1, col);
        addMove(b, m, row+1, col+1);
    }

    // adds top and bottom indices in same col
    private void addColAdjacent (Board b, List<String> m, int row, int col) {
        addMove(b, m, row+1, col);
        addMove(b, m, row-1, col);
    }

    // adds all indices in the left col
    private void addColLeft(Board b, List<String> m, int row, int col) {
        addMove(b, m, row-1, col-1);
        addMove(b, m, row, col-1);
        addMove(b, m, row+1, col-1);
    }

    // adds all indices in the right col
    private void addColRight(Board b, List<String> m, int row, int col) {
        addMove(b, m, row-1, col+1);
        addMove(b, m, row, col+1);
        addMove(b, m, row+1, col+1);
    }

}