package main.se.kth.id1018;

/**
 * Thrown when an attempt is made to move a chess piece to an invalid field.
 */
class NotValidFieldException extends Exception {
	/**
	 * Creates a new instance with an error message, which specifies which field references resulted in the throwing of
	 * the exception.
	 * @param invalidField is the string containing the row and column references to the invalid field.
	 */
	NotValidFieldException( String invalidField ) {
		super( invalidField );
	}
}
