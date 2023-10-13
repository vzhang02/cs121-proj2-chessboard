import java.io.*;

public class Chess {
    public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Chess layout moves");
		}
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
		
		fillBoard(args);
		readMoves(args);

		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
    }

	// checks that location is valid
	private static void validLoc(String loc) {
		if (loc.length() != 2) {
			throw new IllegalArgumentException("incorrect loc length");
		}
		int row = Coord.xcoord(loc);
        int col = Coord.ycoord(loc);
        if ((row > 7 || row < 0) || (col > 7 || col < 0)) {
			throw new IllegalArgumentException("incorrect loc format");
        }
	}

	// checks that piece is valid
	private static void validPiece(String piece) {
		if (piece.length() != 2) {
			throw new IllegalArgumentException("incorrrect piece length");
		}
		//System.out.println(piece);

		char color = piece.charAt(0);
		char pType = piece.charAt(1);
		if (color != 'b' && color != 'w') { // check color
			//System.out.println("wrong color");
			throw new IllegalArgumentException("incorrect piece color");
		}
	
		// check piece type
		if (pType != 'k' && pType != 'q' && pType != 'n' && pType != 'b' && pType != 'r' && pType != 'p') {
			//System.out.println("wrong piece type");
			throw new IllegalArgumentException("incorrect piece type");
		}
	}

	private static void fillBoard(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String line;
			while ((line = br.readLine()) != null) { // iterate through file
				if (line.charAt(0) == '#' || line == "") { // skip if comment
					continue; 
				} 
				if (!line.contains("=")) {
					throw new IllegalArgumentException("incorrect layout line format: no =");
				}
				String[] values = line.split("="); // get location and piece
				if (values.length != 2) {
					throw new IllegalArgumentException("too many ="); 
				}
				validLoc(values[0]);
				validPiece(values[1]); 
				Piece p = Piece.createPiece(values[1]);
				Board.theBoard().addPiece(p, values[0]);
			}
			br.close();
		} catch (IOException c) {
		}
	}
	private static void readMoves(String [] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == '#' || line == "") { // skip if comment
					continue; 
				}
				if (!line.contains("-")) {
					throw new IllegalArgumentException("incorrect moves line format: no -");
				}
				String[] values = line.split("-"); // get location and piece
				if (values.length != 2) {
					throw new IllegalArgumentException("too many '-"); 
				}
				validLoc(values[0]);
				validLoc(values[1]);
				Board.theBoard().movePiece(values[0], values[1]);

			}
			br.close();
		} catch (IOException c) {
		}
		
	}
}
