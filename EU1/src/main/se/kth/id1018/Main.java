package main.se.kth.id1018;

import static main.se.kth.id1018.EffectiveMin.effectiveMin;
import static main.se.kth.id1018.OriginalMin.min;

public class Main {
	public static void main( String[] args ) {
		int[] sixteenElementsArray = { 15, 12, 14, 13, 16, 11, 10, 3, 8, 5, 6, 7, 4, 9, 2, 1 };
		int[] nineteenElementsArray = { 15, 12, 14, 13, 16, 11, 18, 10, 3, 8, 5, 6, 7, 17, 4, 9, 1, 2, 19 };

		min( sixteenElementsArray );
		// min( nineteenElementsArray );
		// correctedMin( sixteenElementsArray );
		// correctedMin( nineteenElementsArray );
		System.out.println( min( sixteenElementsArray ) );
		// System.out.println( min( nineteenElementsArray ) );
		System.out.println( effectiveMin( sixteenElementsArray ) );
		System.out.println( effectiveMin( nineteenElementsArray ) );
	}
}
