package main.se.kth.id1018;

public class EffectiveMin {
	public static int effectiveMin( int[] element ) throws IllegalArgumentException {

		if ( element.length == 0 ) {
			throw new IllegalArgumentException( "tom samling" );
		}

		int minCandidate = element[ 0 ];
		for ( int i = 1; i < element.length; i++ ) {
			minCandidate = element[ i ] < minCandidate ? element[ i ] : minCandidate;
		}

		return minCandidate;
	}
}
