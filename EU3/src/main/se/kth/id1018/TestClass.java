package main.se.kth.id1018;

import java.util.Random;

public class TestClass {
	public static void main( String[] args ) throws NotValidFieldException {
		testProgram();
	}

	static void testProgram() throws NotValidFieldException {
		Chessboard chessBoard = new Chessboard();
		Random random = new Random();
		char randomRowRef;
		byte randomColumnRef;

		Chessboard.ChessPiece[] pieces = new Chessboard.ChessPiece[ 6 ];
		pieces[ 0 ] = chessBoard.new Pawn( 'w', 'P' );
		pieces[ 1 ] = chessBoard.new Rook( 'b', 'R' );
		pieces[ 2 ] = chessBoard.new Knight( 'w', 'N' );
		pieces[ 3 ] = chessBoard.new Bishop( 'w', 'B' );
		pieces[ 4 ] = chessBoard.new Queen( 'w', 'Q' );
		pieces[ 5 ] = chessBoard.new King( 'b', 'K' );

		for ( Chessboard.ChessPiece piece : pieces ) {
			piece.moveTo( ( char ) 'e', ( byte ) 5 );

			if ( piece.isOnBoard() ) {
				System.out.println( chessBoard + "\n" );
				piece.markReachableFields();
				System.out.println( chessBoard + "\n" );
				piece.unmarkReachableFields();
				piece.moveOut();
			}
		}

		char incorrectRowRef = 'x';
		byte correctColumnRef = 1;
		try {
			pieces[ 0 ].moveTo( incorrectRowRef, correctColumnRef );
		}
		catch ( NotValidFieldException exception ) {
			System.out.println( "The provided field reference was invalid, Try again." );
		}
	}
}
