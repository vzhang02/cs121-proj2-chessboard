import java.util.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private static Board theBoard; 
    private List<BoardListener> listeners = new ArrayList<>(); 
    private Board() { }
    
    public static Board theBoard() {
        if (theBoard == null) {
            theBoard = new Board();
        }
        return theBoard;
    }
    
    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {
        validLoc(loc);
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);
        return pieces[row][col];
    }

    public void addPiece(Piece p, String loc) {
        validLoc(loc);
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);

        if (pieces[row][col] != null) { // check if piece already exists at loc
            throw new ArrayStoreException("piece already in loc");
        } 
        pieces[row][col] = p;
    }

    public void movePiece(String from, String to) {
        validLoc(to);
        Piece attacker = getPiece(from);
        if (attacker == null) {
            throw new IllegalArgumentException("no piece at loc");
        }
        if (attacker.moves(this, from).contains(to)) {
            Piece capture = getPiece(to);
            for (BoardListener bl : listeners) {
                bl.onMove(from, to, attacker);
                if (capture != null) {
                    bl.onCapture(attacker, capture);
                }
            }
            pieces[Coord.xcoord(to)][Coord.ycoord(to)] = null;
            addPiece(attacker, to);
            pieces[Coord.xcoord(from)][Coord.ycoord(from)] = null;
        }
    }

    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    public void registerListener(BoardListener bl) {
        listeners.add(bl);
    }

    public void removeListener(BoardListener bl) {
        listeners.remove(bl);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    public void iterate(BoardInternalIterator bi) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String loc = Coord.loc(i, j);
                bi.visit(loc, getPiece(loc));
            }
        }
    }

    private void validLoc(String loc) {
        int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);
        if ((row > 7 || row < 0) || (col > 7 || col < 0)) {
            throw new ArrayIndexOutOfBoundsException("invalid loc");
        }
    }
}