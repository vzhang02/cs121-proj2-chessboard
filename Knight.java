import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) { 
        this.color = c;
    }
    // implement appropriate methods

    public String toString() {
        if (color() == Color.BLACK) {
            return "bn";
        } 
        return "wn";
    }

    public List<String> moves(Board b, String loc) {
        List<String> m = new ArrayList<>(); 
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);
        
        if (row == 0) { 
            if (col == 0) { 
                addMove(b, m, row+2, col+1);
                addMove(b, m, row+1, col+2);
            } else if (col == 1) { 
                upper(b, m, row, col);
                addMove(b, m, row+1, col+2);
            } else if (col == 6) {
                upper(b, m, row, col);
                addMove(b, m, row+1, col-2);
            } else if (col == 7) {
                addMove(b, m, row+1, col-2);
                addMove(b, m, row+2, col-1);
            } else {
                upper(b, m, row, col);
                addMove(b, m, row+1, col+2);
                addMove(b, m, row+1, col-2);
            }
        } else if (row == 1) {
            if (col == 0) {
                addMove(b, m, row+2, col+1);
                right(b, m, row, col);
            } else if (col == 1) {
                upper(b, m, row, col);
                right(b, m, row, col);
            } else if (col == 6) {
                upper(b, m, row, col);
                left(b, m, row, col);
            } else if (col == 7) {
                left(b, m, row, col);
                addMove(b, m, row+2, col-1);
            } else {
                upper(b, m, row, col);
                left(b, m, row, col);
                right(b, m, row, col);
            }
        } else if (row == 6) {
            if (col == 0) {
                right(b, m, row, col);
                addMove(b, m, row-2, col+1);
            } else if (col == 1) {
                lower(b, m, row, col);
                right(b, m, row, col);
            } else if (col == 6) {
                lower(b, m, row, col);
                left(b, m, row, col);
            } else if (col == 7) {
                left(b, m, row, col);
                addMove(b, m, row-2, col-1);
            } else {
                lower(b, m, row, col);
                left(b, m, row, col);
                right(b, m, row, col);
            }
        }  else if (row == 7) {
            if (col == 0) {
                addMove(b, m, row-1, col+2);
                addMove(b, m, row-2, col+1);
            } else if (col == 1) {
                lower(b, m, row, col);
                addMove(b, m, row-1, col+2);
            } else if (col == 6) {
                lower(b, m, row, col);
                addMove(b, m, row-1, col-2);
            } else if (col == 7) {
                addMove(b, m, row-1, col-2);
                addMove(b, m, row-2, col-1);
            } else {
                lower(b, m, row, col);
                addMove(b, m, row-1, col-2);
                addMove(b, m, row-1, col+2);
            }
        }
        else if (col == 0) {
            right(b, m, row, col);
            addMove(b, m, row-2, col+1);
            addMove(b, m, row+2, col+1);
        } else if (col == 1) {
            right(b, m, row, col);
            upper(b, m, row, col);
            lower(b, m, row, col);
        } else if (col == 6) {
            left(b, m, row, col);
            upper(b, m, row, col);
            lower(b, m, row, col);
        } else if (col == 7) {
            left(b, m, row, col);
            addMove(b, m, row+2, col-1);
            addMove(b, m, row-2, col-1);
        } else {
            upper(b, m, row, col);
            lower(b, m, row, col);
            left(b, m, row, col);
            right(b, m, row, col);
        }
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

    private void upper(Board b, List<String> m, int row, int col) {
        addMove(b, m, row+2, col+1);
        addMove(b, m, row+2, col-1);
    }

    private void lower(Board b, List<String> m, int row, int col) {
        addMove(b, m, row-2, col+1);
        addMove(b, m, row-2, col-1);
    }

    private void right(Board b, List<String> m, int row, int col) {
        addMove(b, m, row+1, col+2);
        addMove(b, m, row-1, col+2);
    }

    private void left(Board b, List<String> m, int row, int col) {
        addMove(b, m, row+1, col-2);
        addMove(b, m, row-1, col-2);
    }

}