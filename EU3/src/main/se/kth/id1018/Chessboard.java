package main.se.kth.id1018;

/**
 * Represents a chessboard. Consists of 8 x 8 fields, of which rows are referred to via the letters a-h and columns via
 * the numbers 1-8. Each field is a container for chess pieces and the valid paths it may take during a turn. The chess
 * pieces can either be black or white, and either a Pawn, a Knight, a Bishop, a Queen or a King.
 */
class Chessboard {
	static final int NUMBER_OF_ROWS = 8;
	static final int NUMBER_OF_COLUMNS = 8;
	static final int FIRST_ROW = 'a';
	static final int FIRST_COLUMN = 1;
	private final Field[][] fields;

	/**
	 * Instantiates class. Initializes the fields of the chess board and assigns each field its alphanumerical
	 * reference.
	 */
	Chessboard() {
		fields = new Field[ NUMBER_OF_ROWS ][ NUMBER_OF_COLUMNS ];
		char row;
		byte column;
		for ( int r = 0; r < NUMBER_OF_ROWS; r++ ) {
			row = ( char ) ( FIRST_ROW + r );
			column = FIRST_COLUMN;
			for ( int c = 0; c < NUMBER_OF_COLUMNS; c++ ) {
				fields[ r ][ c ] = new Field( row, column );
				column++;
			}
		}
	}

	/**
	 * Outputs whether a field is valid or not, based on the provided row and column reference.
	 * @param row    is the row reference (a-h)
	 * @param column is the column reference (1-8)
	 * @return true if the field is valid and false if it is not.
	 */
	boolean isValidField( char row, byte column ) {
		if ( row >= FIRST_ROW && row < FIRST_ROW + NUMBER_OF_ROWS ) {
			return column >= FIRST_COLUMN && column <= NUMBER_OF_COLUMNS;
		}

		return false;
	}

	/**
	 * Outputs a representation of the chess board.
	 * @return an illustration of the chess board, along with any chess piece placed on it, and any activated markings.
	 */
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();

		char rowRef = FIRST_ROW;
		byte columnRef = NUMBER_OF_COLUMNS;

		for ( int column = NUMBER_OF_COLUMNS; column >= 0; column-- ) {
			for ( int row = -1; row < NUMBER_OF_ROWS; row++ ) {
				strBuilder.append( "\t" );

				if ( column == NUMBER_OF_COLUMNS && row >= 0 ) {
					strBuilder.append( rowRef++ );
				}

				if ( row == -1 && column < NUMBER_OF_COLUMNS ) {
					strBuilder.append( columnRef-- );
				}

				if ( row >= 0 && column < NUMBER_OF_COLUMNS ) {
					strBuilder.append( fields[ row ][ column ] );
				}

				if ( row == NUMBER_OF_ROWS - 1 ) {
					strBuilder.append( "\n" );
				}
			}
		}

		return strBuilder.toString();
	}

	/**
	 * Represents a field on the chessboard, which may have a chess piece placed on it, or might be marked.
	 */
	static class Field {
		private final char row;
		private final byte column;
		private ChessPiece piece = null;
		private boolean marked = false;

		/**
		 * Creates a new instance of a field on the chess board.
		 * @param row    is the row in which the field is positioned.
		 * @param column is the column in which the field is positioned.
		 */
		Field( char row, byte column ) {
			this.row = row;
			this.column = column;
		}

		/**
		 * Places a chess piece on the field.
		 * @param piece is the chess piece to place.
		 */
		void put( ChessPiece piece ) {
			this.piece = piece;
		}

		/**
		 * Removes the chess piece from the field.
		 */
		void take() {
			this.piece = null;
		}

		/**
		 * Marks the current field.
		 */
		void mark() {
			this.marked = true;
		}

		/**
		 * Unmarks the current field.
		 */
		void unmark() {
			this.marked = false;
		}

		/**
		 * Outputs a string representation of the field
		 * @return the state of the field; if a piece is currently placed on it, the piece representation; if the field
		 * is marked, a string representing a marked field; if the filed is unmarked, a string representing an unmarked
		 * field.
		 */
		public String toString() {
			String s = ( marked ) ? "xx" : "--";
			return ( piece == null ) ? s : piece.toString();
		}
	}

	/**
	 * Represents a chess piece, which can be either white or black, and either king, queen, rook, bishop, knight or
	 * pawn. Can also be placed either on the game board, or off board.
	 */
	abstract class ChessPiece {
		private final char color;
		private final char name;
		protected char row = 0;
		protected byte column = -1;

		protected ChessPiece( char color, char name ) {
			this.color = color;
			this.name = name;
		}

		/**
		 * Outputs a representation of the chess piece.
		 * @return the string representation, consisting of the color and type of the chess piece
		 */
		public String toString() {
			return "" + color + name;
		}

		/**
		 * Outputs whether the chess piece is on the board or not.
		 * @return true if the piece is on the board, and false if it is not.
		 */
		boolean isOnBoard() {
			return Chessboard.this.isValidField( row, column );
		}

		/**
		 * Moves the chess piece to the given field reference (row and column), provided the field is valid.
		 * @param row    is the row reference (a-h).
		 * @param column is the column reference (1-8).
		 * @throws NotValidFieldException is the exception thrown when the provided field reference is invalid.
		 */
		void moveTo( char row, byte column ) throws NotValidFieldException {
			if ( !Chessboard.this.isValidField( row, column ) ) {
				throw new NotValidFieldException( "Bad field: " + row + column );
			}

			this.row = row;
			this.column = column;
			int r = row - FIRST_ROW;
			int c = column - FIRST_COLUMN;
			Chessboard.this.fields[ r ][ c ].put( this );
		}

		/**
		 * Removes the chess piece from the chess board.
		 */
		void moveOut() {
			Chessboard.this.fields[ row - FIRST_ROW ][ column - FIRST_COLUMN ].take();
		}

		/**
		 * Marks the fields that a chess piece can move to, based on its movement capabilities and position on the
		 * chess
		 * board.
		 */
		abstract void markReachableFields();

		/**
		 * Unmarks the fields previously marked by the chess piece.
		 */
		abstract void unmarkReachableFields();
	}

	/**
	 * Represents the 'Pawn' chess piece.
	 */
	class Pawn extends ChessPiece {
		Pawn( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the pawn, based on its current position on the board.
		 */
		void markReachableFields() {
			byte col = ( byte ) ( column + 1 );
			if ( Chessboard.this.isValidField( row, col ) ) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[ r ][ c ].mark();
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			byte col = ( byte ) ( column + 1 );
			if ( Chessboard.this.isValidField( row, col ) ) {
				int r = row - FIRST_ROW;
				int c = col - FIRST_COLUMN;
				Chessboard.this.fields[ r ][ c ].unmark();
			}
		}
	}

	/**
	 * Represents the movement capabilities of a rook chess piece.
	 */
	class Rook extends ChessPiece {
		Rook( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the rook, based on its current position on the board.
		 */
		void markReachableFields() {
			for ( int ref = -NUMBER_OF_ROWS; ref < NUMBER_OF_ROWS; ref++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), column ) ) {
					Chessboard.this.fields[ ( char ) ( row + ref - FIRST_ROW ) ][ column - FIRST_COLUMN ].mark();
				}

				if ( Chessboard.this.isValidField( row, ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ ( char ) ( row - FIRST_ROW ) ][ column + ref - FIRST_COLUMN ].mark();
				}
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			for ( int ref = -NUMBER_OF_ROWS; ref < NUMBER_OF_ROWS; ref++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), column ) ) {
					Chessboard.this.fields[ ( char ) ( row + ref - FIRST_ROW ) ][ column - FIRST_COLUMN ].unmark();
				}

				if ( Chessboard.this.isValidField( row, ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ ( char ) ( row - FIRST_ROW ) ][ column + ref - FIRST_COLUMN ].unmark();
				}
			}
		}
	}

	/**
	 * Represents the movement capabilities of a knight chess piece.
	 */
	class Knight extends ChessPiece {
		private static final int POTENTIAL_MOVES = 8;
		private static final int[] POTENTIAL_ROW_MOVEMENT = { -2, -2, -1, -1, 1, 1, 2, 2 };
		private static final byte[] POTENTIAL_COLUMN_MOVEMENT = { -1, 1, -2, 2, -2, 2, -1, 1 };

		Knight( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the knight, based on its current position on the board.
		 */
		void markReachableFields() {
			for ( int i = 0; i < POTENTIAL_MOVES; i++ ) {
				char rowRef = ( char ) ( this.row + POTENTIAL_ROW_MOVEMENT[ i ] );
				byte columnRef = ( byte ) ( this.column + POTENTIAL_COLUMN_MOVEMENT[ i ] );

				if ( Chessboard.this.isValidField( rowRef, columnRef ) ) {
					Chessboard.this.fields[ rowRef - FIRST_ROW ][ columnRef - FIRST_COLUMN ].mark();
				}
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			for ( int i = 0; i < POTENTIAL_MOVES; i++ ) {
				char rowRef = ( char ) ( this.row + POTENTIAL_ROW_MOVEMENT[ i ] );
				byte columnRef = ( byte ) ( this.column + POTENTIAL_COLUMN_MOVEMENT[ i ] );

				if ( Chessboard.this.isValidField( rowRef, columnRef ) ) {
					Chessboard.this.fields[ rowRef - FIRST_ROW ][ columnRef - FIRST_COLUMN ].unmark();
				}
			}
		}
	}

	/**
	 * Represents the movement capabilities of a bishop chess piece.
	 */
	class Bishop extends ChessPiece {
		Bishop( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the bishop, based on its current position on the board.
		 */
		void markReachableFields() {
			for ( int i = 1; i < NUMBER_OF_ROWS; i++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + i ), ( byte ) ( column + i ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW + i ][ column - FIRST_COLUMN + i ].mark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row + i ), ( byte ) ( column - i ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW + i ][ column - FIRST_COLUMN - i ].mark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row - i ), ( byte ) ( column + i ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW - i ][ column - FIRST_COLUMN + i ].mark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row - i ), ( byte ) ( column - i ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW - i ][ column - FIRST_COLUMN - i ].mark();
				}
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			for ( int ref = 1; ref < NUMBER_OF_ROWS; ref++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN + ref ].unmark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column - ref ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN - ref ].unmark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN + ref ].unmark();
				}

				if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column - ref ) ) ) {
					Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN - ref ].unmark();
				}
			}
		}
	}

	/**
	 * Represents the movement capabilities of a queen chess piece.
	 */
	class Queen extends ChessPiece {
		Queen( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the queen, based on its current position on the board.
		 */
		void markReachableFields() {
			for ( int ref = -NUMBER_OF_ROWS; ref < NUMBER_OF_ROWS; ref++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), column ) ) {
					Chessboard.this.fields[ ( char ) ( row + ref - FIRST_ROW ) ][ column - FIRST_COLUMN ].mark();
				}

				if ( Chessboard.this.isValidField( row, ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ ( char ) ( row - FIRST_ROW ) ][ column + ref - FIRST_COLUMN ].mark();
				}

				if ( ref > 0 ) {
					if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column + ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN + ref ].mark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column - ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN - ref ].mark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column + ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN + ref ].mark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column - ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN - ref ].mark();
					}
				}
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			for ( int ref = -NUMBER_OF_ROWS; ref < NUMBER_OF_ROWS; ref++ ) {
				if ( Chessboard.this.isValidField( ( char ) ( row + ref ), column ) ) {
					Chessboard.this.fields[ ( char ) ( row + ref - FIRST_ROW ) ][ column - FIRST_COLUMN ].unmark();
				}

				if ( Chessboard.this.isValidField( row, ( byte ) ( column + ref ) ) ) {
					Chessboard.this.fields[ ( char ) ( row - FIRST_ROW ) ][ column + ref - FIRST_COLUMN ].unmark();
				}

				if ( ref > 0 ) {
					if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column + ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN + ref ].unmark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row + ref ), ( byte ) ( column - ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW + ref ][ column - FIRST_COLUMN - ref ].unmark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column + ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN + ref ].unmark();
					}

					if ( Chessboard.this.isValidField( ( char ) ( row - ref ), ( byte ) ( column - ref ) ) ) {
						Chessboard.this.fields[ row - FIRST_ROW - ref ][ column - FIRST_COLUMN - ref ].unmark();
					}
				}
			}
		}
	}

	/**
	 * Represents the movement capabilities of a king chess piece.
	 */
	class King extends ChessPiece {
		private static final byte[] RELATIVE_POSITIONS = { 1, 0, -1 };

		King( char color, char name ) {
			super( color, name );
		}

		/**
		 * Marks fields reachable by the king, based on the kings current position on the board.
		 */
		void markReachableFields() {
			for ( byte relativePosition : RELATIVE_POSITIONS ) {
				char rowRef = ( char ) ( row + relativePosition );

				for ( byte position : RELATIVE_POSITIONS ) {
					byte columnRef = ( byte ) ( column + position );

					if ( Chessboard.this.isValidField( rowRef, columnRef ) ) {
						Chessboard.this.fields[ rowRef - FIRST_ROW ][ columnRef - FIRST_COLUMN ].mark();
					}
				}
			}
		}

		/**
		 * Unmarks marked fields.
		 */
		void unmarkReachableFields() {
			for ( byte relativePosition : RELATIVE_POSITIONS ) {
				char rowRef = ( char ) ( this.row + relativePosition );

				for ( byte position : RELATIVE_POSITIONS ) {
					byte columnRef = ( byte ) ( this.column + position );

					if ( Chessboard.this.isValidField( rowRef, columnRef ) ) {
						Chessboard.this.fields[ rowRef - FIRST_ROW ][ columnRef - FIRST_COLUMN ].unmark();
					}
				}
			}
		}
	}
}