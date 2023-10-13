import java.util.*;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    // tests creating each type of piece
    public static void test0() {
        // register factories
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        // test each piece case
        Piece p = Piece.createPiece("bp");
        System.out.println("Black pawn: " + p.toString());

        Piece p1 = Piece.createPiece("wp");
        System.out.println("White pawn: " + p1.toString());

        Piece p2 = Piece.createPiece("br");
        System.out.println("Black rook: " + p2.toString());

        Piece p3 = Piece.createPiece("wr");
        System.out.println("White rook: " + p3.toString());

        Piece p4 = Piece.createPiece("bb");
        System.out.println("Black bishop: " + p4.toString());

        Piece p5 = Piece.createPiece("wb");
        System.out.println("White bishop: " + p5.toString());

        Piece p6 = Piece.createPiece("bn");
        System.out.println("Black knight: " + p6.toString());

        Piece p7 = Piece.createPiece("wn");
        System.out.println("White knight: " + p7.toString());

        Piece p8 = Piece.createPiece("bq");
        System.out.println("Black queen: " + p8.toString());
       
        Piece p9 = Piece.createPiece("wq");
        System.out.println("White queen: " + p9.toString());

        Piece p10 = Piece.createPiece("bk");
        System.out.println("Black king: " + p10.toString());
       
        Piece p11 = Piece.createPiece("wk");
        System.out.println("White king: " + p11.toString());
    }

    // tests adding piece to board
    public static void test1() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("bp");
        b.addPiece(p, "a3");
        assert b.getPiece("a3") == p;
        b.clear();
    }
    
    // tests adding pieces to already occupied square
    public static void test2() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("bp");
        b.addPiece(p, "a3");
        try {
            b.addPiece(p, "a3");
        } catch (Exception e) {
            System.out.println("piece already in location"); 
        }
        assert b.getPiece("a3") == p;
        b.clear();
    }

    // tests clearing board and adding piece again
    public static void test3() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("bp");
        b.addPiece(p, "a3");
        b.clear();
        b.addPiece(p, "a3");
        assert b.getPiece("a3") == p;
        b.clear();
    }

    // tests moves of king on corners
    public static void test4() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece p = Piece.createPiece("bk");
        b.addPiece(Piece.createPiece("wp"), "b1");
        // test bottom left corner
        List<String> moves = p.moves(b, "a1");
        assert moves.contains("b1"); // can capture wpawn, so included
        assert moves.contains("b2"); // index to diagonal right
        assert moves.contains("a2"); // index below

        // test can't move when piece of same color is there
        b.addPiece(Piece.createPiece("bp"), "a2");
        moves = p.moves(b, "a1");
        assert moves.contains("b2"); // can capture wpawn, so included
        assert moves.contains("b1"); // index to diagonal right
        assert !moves.contains("a2"); // black pawn there, so can't move

        // tests bottom right corner 
        moves = p.moves(b, "h1");
        assert moves.contains("g1"); // index to left
        assert moves.contains("g2"); // index diagonal left
        assert moves.contains("h2"); // index above

        // test upper right corner
        moves = p.moves(b, "h8");
        assert moves.contains("g8"); // index to lefrt
        assert moves.contains("g7"); // index to diagonal right
        assert moves.contains("h7"); // index below

        // tests top left corner 
        moves = p.moves(b, "a8");
        assert moves.contains("b8"); // index to lefrt
        assert moves.contains("a7"); // index to diagonal right
        assert moves.contains("b7"); // index below

        b.clear(); 
    }


    // tests moves of king on border (not corners)
    public static void test5() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece p = Piece.createPiece("bk");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // test left col
        List<String> moves = p.moves(b, "a3");
        assert moves.contains("b2"); // can capture wpawn, so included
        assert moves.contains("b3"); // index to diagonal right
        assert moves.contains("b4"); // index to diagonal right
        assert moves.contains("a2"); // index below
        assert moves.contains("a4"); // index below

        // test can't move when piece of same color is there
        b.addPiece(Piece.createPiece("bp"), "a2");
        moves = p.moves(b, "a3");
        assert moves.contains("b2"); // can capture wpawn, so included
        assert moves.contains("b3"); // index to diagonal right
        assert moves.contains("b4"); // index to diagonal right
        assert !moves.contains("a2"); // index below
        assert moves.contains("a4"); // index below

        // tests right col 
        moves = p.moves(b, "h3");
        assert moves.contains("g2"); // can capture wpawn, so included
        assert moves.contains("g3"); // index to diagonal right
        assert moves.contains("g4"); // index to diagonal right
        assert moves.contains("h2"); // index below
        assert moves.contains("h4"); // index below

        // test top row
        moves = p.moves(b, "d8");
        assert moves.contains("c8"); // can capture wpawn, so included
        assert moves.contains("e8"); // index to diagonal right
        assert moves.contains("c7"); // index to diagonal right
        assert moves.contains("d7"); // index below
        assert moves.contains("e7"); // index below

        // test bottom row
        moves = p.moves(b, "d1");
        assert moves.contains("c1"); // can capture wpawn, so included
        assert moves.contains("e1"); // index to diagonal right
        assert moves.contains("c2"); // index to diagonal right
        assert moves.contains("d2"); // index below
        assert moves.contains("e2"); // index below
        
        b.clear(); 
    }

    // tests piece in center of board
    public static void test6() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece p = Piece.createPiece("bk");
        b.addPiece(Piece.createPiece("wp"), "b1");
        List<String> moves = p.moves(b, "b2");

        assert moves.contains("a3");
        assert moves.contains("b3");
        assert moves.contains("c3");
        assert moves.contains("a1");
        assert moves.contains("b1");
        assert moves.contains("c1");
        assert moves.contains("a2");
        assert moves.contains("c2");

        b.clear(); 
    }

    // test move of queen on corners
    public static void test7() {
        Board b = Board.theBoard();
        Piece.registerPiece(new QueenFactory());
        Piece p = Piece.createPiece("bq");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "a2");
        List<String> moves = p.moves(b, "a1");
        assert !moves.contains("a2");
        assert !moves.contains("a3");
        assert moves.contains("b2");
        assert moves.contains("c3");
        assert moves.contains("d4");
        assert moves.contains("e5");
        assert moves.contains("f6");
        assert moves.contains("g7");
        assert moves.contains("h8");
        assert moves.contains("b1");
        assert moves.contains("c1");
        assert moves.contains("d1");
        assert moves.contains("e1");
        assert moves.contains("f1");
        assert moves.contains("g1");
        assert moves.contains("h1");

        // bottom right corner
        moves = p.moves(b, "h1");
        assert moves.contains("h2");
        assert moves.contains("h3");
        assert moves.contains("h4");
        assert moves.contains("h5");
        assert moves.contains("h6");
        assert moves.contains("h7");
        assert moves.contains("h8");
        assert moves.contains("a8");
        assert moves.contains("b7");
        assert moves.contains("c6");
        assert moves.contains("d5");
        assert moves.contains("e4");
        assert moves.contains("f3");
        assert moves.contains("g2");
        assert moves.contains("a1");
        assert moves.contains("b1");
        assert moves.contains("c1");
        assert moves.contains("d1");
        assert moves.contains("e1");
        assert moves.contains("f1");
        assert moves.contains("g1");
        
        // top left corner
        moves = p.moves(b, "a8");
        assert !moves.contains("a2");
        assert moves.contains("a3");
        assert moves.contains("a4");
        assert moves.contains("a5");
        assert moves.contains("a6");
        assert moves.contains("a7");
        assert moves.contains("h1");
        assert moves.contains("b7");
        assert moves.contains("c6");
        assert moves.contains("d5");
        assert moves.contains("e4");
        assert moves.contains("f3");
        assert moves.contains("g2");
        assert moves.contains("h8");
        assert moves.contains("b8");
        assert moves.contains("c8");
        assert moves.contains("d8");
        assert moves.contains("e8");
        assert moves.contains("f8");
        assert moves.contains("g8");

        // top right corner
        moves = p.moves(b, "h8");
        assert moves.contains("h2");
        assert moves.contains("h3");
        assert moves.contains("h4");
        assert moves.contains("h5");
        assert moves.contains("h6");
        assert moves.contains("h7");
        assert moves.contains("h1");
        assert moves.contains("a1");
        assert moves.contains("g7");
        assert moves.contains("f6");
        assert moves.contains("e5");
        assert moves.contains("d4");
        assert moves.contains("c3");
        assert moves.contains("b2");
        assert moves.contains("a8");
        assert moves.contains("b8");
        assert moves.contains("c8");
        assert moves.contains("d8");
        assert moves.contains("e8");
        assert moves.contains("f8");
        assert moves.contains("g8");

        b.clear(); 
    }

    // test moves of queen for center of board
    public static void test8() {
        Board b = Board.theBoard();
        Piece.registerPiece(new QueenFactory());
        Piece p = Piece.createPiece("bq");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "a2");
        List<String> moves = p.moves(b, "b2");

        assert moves.contains("b1");
        assert moves.contains("b3");
        assert moves.contains("b4");
        assert moves.contains("b5");
        assert moves.contains("b6");
        assert moves.contains("b7");
        assert moves.contains("b8");
        assert !moves.contains("a2"); // bpawn here
        assert moves.contains("c2");
        assert moves.contains("d2");
        assert moves.contains("e2");
        assert moves.contains("f2");
        assert moves.contains("g2");
        assert moves.contains("h2");
        assert moves.contains("h8");
        assert moves.contains("g7");
        assert moves.contains("f6");
        assert moves.contains("e5");
        assert moves.contains("d4");
        assert moves.contains("c3");
        assert !moves.contains("b2"); // location of queen
        assert moves.contains("a1");
        assert moves.contains("a3");
        assert moves.contains("c1");
        
        b.clear();
    }

    // test moves for knight corner cases
    public static void test9() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "b3");
        List<String> moves = p.moves(b, "a1");
        assert !moves.contains("b3");
        assert moves.contains("c2");

        moves = p.moves(b, "h1");
        assert moves.contains("g3");
        assert moves.contains("f2");

        moves = p.moves(b, "a8");
        assert moves.contains("c7");
        assert moves.contains("b6");

        moves = p.moves(b, "h8");
        assert moves.contains("f7");
        assert moves.contains("g6");
        b.clear();
    }

    // test moves for knight bottom left
    public static void test10() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "b4");
        List<String> moves = p.moves(b, "a2");
        assert !moves.contains("b4");
        assert moves.contains("c3");
        assert moves.contains("c1");

        moves = p.moves(b, "b2");
        assert moves.contains("a4");
        assert moves.contains("c4");
        assert moves.contains("d3");
        assert moves.contains("d1");

        moves = p.moves(b, "b1");
        assert moves.contains("a3");
        assert moves.contains("c3");
        assert moves.contains("d2");

        b.clear();
    }

    // test moves for knight bottom lright
    public static void test11() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "e2");
        List<String> moves = p.moves(b, "g1");
        assert !moves.contains("e2");
        assert moves.contains("f3");
        assert moves.contains("h3");

        moves = p.moves(b, "g2");
        assert moves.contains("f4");
        assert moves.contains("h4");
        assert moves.contains("e3");
        assert moves.contains("e1");

        moves = p.moves(b, "h2");
        assert moves.contains("f3");
        assert moves.contains("f1");
        assert moves.contains("g4");

        b.clear();
    }

    // test moves for knight top left
    public static void test12() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "b4");
        List<String> moves = p.moves(b, "a7");
        assert !moves.contains("b4");
        assert moves.contains("c8");
        assert moves.contains("c6");

        moves = p.moves(b, "b7");
        assert moves.contains("a5");
        assert moves.contains("c5");
        assert moves.contains("d8");
        assert moves.contains("d6");

        moves = p.moves(b, "b8");
        assert moves.contains("a6");
        assert moves.contains("c6");
        assert moves.contains("d7");

        b.clear();
    }

    // test moves for knight top right
    public static void test13() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "e7");
        List<String> moves = p.moves(b, "g8");
        assert !moves.contains("e7");
        assert moves.contains("f6");
        assert moves.contains("h6");

        moves = p.moves(b, "g7");
        assert moves.contains("f5");
        assert moves.contains("h5");
        assert moves.contains("e8");
        assert moves.contains("e6");

        moves = p.moves(b, "h7");
        assert moves.contains("f8");
        assert moves.contains("f6");
        assert moves.contains("g5");

        b.clear();
    }

    // test moves for knight bottom and top rows
    public static void test14() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "c8");
        List<String> moves = p.moves(b, "e7");
        assert !moves.contains("c8");
        assert moves.contains("c6");
        assert moves.contains("d5");
        assert moves.contains("f5");
        assert moves.contains("g8");
        assert moves.contains("g6");

        moves = p.moves(b, "e8");
        assert moves.contains("c7");
        assert moves.contains("g7");
        assert moves.contains("d6");
        assert moves.contains("f6");

        moves = p.moves(b, "e1");
        assert moves.contains("c2");
        assert moves.contains("g2");
        assert moves.contains("d3");
        assert moves.contains("f3");

        moves = p.moves(b, "e2");
        assert moves.contains("c3");
        assert moves.contains("c1");
        assert moves.contains("d4");
        assert moves.contains("f4");
        assert moves.contains("g3");
        assert moves.contains("g1");

        b.clear();
    }


    // test moves for knight left and right rows
    public static void test15() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "a7");
        List<String> moves = p.moves(b, "b5");
        assert !moves.contains("a7");
        assert moves.contains("c7");
        assert moves.contains("a3");
        assert moves.contains("c3");
        assert moves.contains("d6");
        assert moves.contains("d4");

        moves = p.moves(b, "a5");
        assert moves.contains("c4");
        assert moves.contains("c6");
        assert moves.contains("b7");
        assert moves.contains("b3");

        moves = p.moves(b, "h5");
        assert moves.contains("f6");
        assert moves.contains("g7");
        assert moves.contains("g3");
        assert moves.contains("f4");

        moves = p.moves(b, "g5");
        assert moves.contains("f3");
        assert moves.contains("h3");
        assert moves.contains("f7");
        assert moves.contains("h7");
        assert moves.contains("e6");
        assert moves.contains("e4");

        b.clear();
    }

    // test moves for knight gen case
    public static void test16() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KnightFactory());
        Piece p = Piece.createPiece("bn");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "c7");
        List<String> moves = p.moves(b, "d5");
        assert !moves.contains("c7");
        assert moves.contains("e7");
        assert moves.contains("b6");
        assert moves.contains("b4");
        assert moves.contains("f4");
        assert moves.contains("f6");
        assert moves.contains("c3");
        assert moves.contains("e3");
        b.clear();
    }

    //tests bishop moves for corners
    public static void test17() {
        Board b = Board.theBoard();
        Piece.registerPiece(new BishopFactory());
        Piece p = Piece.createPiece("bb");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        //bottom left corner
        b.addPiece(Piece.createPiece("bp"), "g7");
        List<String> moves = p.moves(b, "a1");
        assert moves.contains("b2");
        assert moves.contains("c3");
        assert moves.contains("d4");
        assert moves.contains("e5");
        assert moves.contains("f6");
        assert !moves.contains("g7");
        assert !moves.contains("h8");

        // top left corner
        moves = p.moves(b, "a8");
        assert moves.contains("b7");
        assert moves.contains("c6");
        assert moves.contains("d5");
        assert moves.contains("e4");
        assert moves.contains("f3");
        assert moves.contains("g2");
        assert moves.contains("h1");

        // bottom right corner
        moves = p.moves(b, "h1");
        assert moves.contains("a8");
        assert moves.contains("b7");
        assert moves.contains("c6");
        assert moves.contains("d5");
        assert moves.contains("e4");
        assert moves.contains("f3");
        assert moves.contains("g2");
        
        // top right corner
        moves = p.moves(b, "h8");
        assert !moves.contains("a1");
        assert !moves.contains("g7");
        assert !moves.contains("f6");
        assert !moves.contains("e5");
        assert !moves.contains("d4");
        assert !moves.contains("c3");
        assert !moves.contains("b2");

        b.clear();
    }

    // tests bishop moves gen case
    public static void test18() {
        Board b = Board.theBoard();
        Piece.registerPiece(new BishopFactory());
        Piece p = Piece.createPiece("bb");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        //bottom left corner
        b.addPiece(Piece.createPiece("bp"), "f7");
        List<String> moves = p.moves(b, "d5");
        assert moves.contains("a8");
        assert moves.contains("b7");
        assert moves.contains("c6");
        assert moves.contains("e4");
        assert moves.contains("f3");
        assert moves.contains("g2");
        assert moves.contains("h1");

        assert !moves.contains("g8");
        assert !moves.contains("f7");
        assert moves.contains("e6");

        assert moves.contains("c4");
        assert moves.contains("b3");
        assert moves.contains("a2");

        b.clear();
    }

     // test move of rook on corners
     public static void test19() {
        Board b = Board.theBoard();
        Piece.registerPiece(new RookFactory());
        Piece p = Piece.createPiece("br");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "a2");
        List<String> moves = p.moves(b, "a1");
        assert !moves.contains("a2");
        assert !moves.contains("a3");
        assert moves.contains("b1");
        assert moves.contains("c1");
        assert moves.contains("d1");
        assert moves.contains("e1");
        assert moves.contains("f1");
        assert moves.contains("g1");
        assert moves.contains("h1");

        // bottom right corner
        moves = p.moves(b, "h1");
        assert moves.contains("h2");
        assert moves.contains("h3");
        assert moves.contains("h4");
        assert moves.contains("h5");
        assert moves.contains("h6");
        assert moves.contains("h7");
        assert moves.contains("h8");
        assert moves.contains("a1");
        assert moves.contains("b1");
        assert moves.contains("c1");
        assert moves.contains("d1");
        assert moves.contains("e1");
        assert moves.contains("f1");
        assert moves.contains("g1");
        
        // top left corner
        moves = p.moves(b, "a8");
        assert !moves.contains("a2");
        assert moves.contains("a3");
        assert moves.contains("a4");
        assert moves.contains("a5");
        assert moves.contains("a6");
        assert moves.contains("a7");       
        assert moves.contains("h8");
        assert moves.contains("b8");
        assert moves.contains("c8");
        assert moves.contains("d8");
        assert moves.contains("e8");
        assert moves.contains("f8");
        assert moves.contains("g8");

        // top right corner
        moves = p.moves(b, "h8");
        assert moves.contains("h2");
        assert moves.contains("h3");
        assert moves.contains("h4");
        assert moves.contains("h5");
        assert moves.contains("h6");
        assert moves.contains("h7");
        assert moves.contains("h1");
        assert moves.contains("a8");
        assert moves.contains("b8");
        assert moves.contains("c8");
        assert moves.contains("d8");
        assert moves.contains("e8");
        assert moves.contains("f8");
        assert moves.contains("g8");

        b.clear(); 
    }

    // tests rook moves in center
    // test moves of queen for center of board
    public static void test20() {
        Board b = Board.theBoard();
        Piece.registerPiece(new RookFactory());
        Piece p = Piece.createPiece("br");
        b.addPiece(Piece.createPiece("wp"), "b1");
        
        // bottom left corner
        b.addPiece(Piece.createPiece("bp"), "a2");
        List<String> moves = p.moves(b, "b2");

        assert moves.contains("b1");
        assert moves.contains("b3");
        assert moves.contains("b4");
        assert moves.contains("b5");
        assert moves.contains("b6");
        assert moves.contains("b7");
        assert moves.contains("b8");
        assert !moves.contains("a2"); // bpawn here
        assert moves.contains("c2");
        assert moves.contains("d2");
        assert moves.contains("e2");
        assert moves.contains("f2");
        assert moves.contains("g2");
        assert moves.contains("h2");
        
        b.clear();
    }

    // testing black pawn all cases
    public static void test21() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("bp");
        b.addPiece(Piece.createPiece("wp"), "c6");
        b.addPiece(Piece.createPiece("bp"), "d5");
        
        // center homerow case with wp
        List<String> moves = p.moves(b, "b7");
        assert moves.contains("b6");
        assert moves.contains("b5");
        assert moves.contains("c6");
        assert !moves.contains("a6");

        // homerow leftmost col case
        b.addPiece(Piece.createPiece("wp"), "b6");
        moves = p.moves(b, "a7");
        assert moves.contains("a6");
        assert moves.contains("a5");
        assert moves.contains("b6");

        // center case
        moves = p.moves(b, "e5");
        assert moves.contains("e4");
        assert !moves.contains("e3");
        assert !moves.contains("d4");
        assert !moves.contains("f4");
        
        // homerow rightmost col
        moves = p.moves(b, "h7");
        assert moves.contains("h6");
        assert moves.contains("h5");
        assert !moves.contains("g6");
        
        b.clear();
    }

    // testing white pawn all cases
    public static void test22() {
        Board b = Board.theBoard();
        Piece.registerPiece(new PawnFactory());
        Piece p = Piece.createPiece("wp");
        b.addPiece(Piece.createPiece("bp"), "c3");
        
        // homerow center case
        List<String> moves = p.moves(b, "b2");
        assert moves.contains("b3");
        assert moves.contains("b4");
        assert moves.contains("c3");
        assert !moves.contains("a3");

        // homerow leftmost col
        b.addPiece(Piece.createPiece("bp"), "b3");
        moves = p.moves(b, "a2");
        assert moves.contains("a3");
        assert moves.contains("a4");
        assert moves.contains("b3");

        // center case
        moves = p.moves(b, "e5");
        assert moves.contains("e6");
        assert !moves.contains("e7");
        assert !moves.contains("d6");
        assert !moves.contains("f6");

        // homerow rightmost col
        moves = p.moves(b, "h2");
        assert moves.contains("h3");
        assert moves.contains("h4");
        assert !moves.contains("g3");
        
        b.clear();
    }

    // adds all black pieces to board and moving 2 bpawns and king to previous
    // bpawn square
    public static void test23() {
        Board b = Board.theBoard();
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());

        Piece bpawn = Piece.createPiece("bp");
        Piece bknight = Piece.createPiece("bn");
        Piece brook = Piece.createPiece("br");
        Piece bbishop = Piece.createPiece("bb");
        Piece bking = Piece.createPiece("bk");
        Piece bqueen = Piece.createPiece("bq");

        b.addPiece(bpawn, "a7");
        b.addPiece(bpawn, "b7");
        b.addPiece(bpawn, "c7");
        b.addPiece(bpawn, "d7");
        b.addPiece(bpawn, "e7");
        b.addPiece(bpawn, "f7");
        b.addPiece(bpawn, "g7");
        b.addPiece(bpawn, "h7");
        b.addPiece(brook, "a8");
        b.addPiece(brook, "h8");
        b.addPiece(bknight, "b8");
        b.addPiece(bknight, "g8");
        b.addPiece(bbishop, "c8");
        b.addPiece(bbishop, "f8");
        b.addPiece(bking, "d8");
        b.addPiece(bqueen, "e8");

        b.movePiece("a7", "a5");
        b.movePiece("d7", "d5");
        b.movePiece("d8", "d7");

        assert b.getPiece("a5") == bpawn;
        assert b.getPiece("d5") == bpawn;
        assert b.getPiece("d7") == bking;
    }

    public static void test24() {
        System.out.println("Final board:");
	    Board.theBoard().iterate(new BoardPrinter());
    }
    
    public static void main(String[] args) {
	    //test0(); 
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
        test12();
        test13();
        test14();
        test15();
        test16();
        test17();
        test18();
        test19();
        test20();
        test21();
        test22();
        test23();
        test24();

    }

}