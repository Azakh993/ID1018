package main.se.kth.id1018;

public class ExchangeSort {
	public static void sort( int n, int[] x ) {
		int t;
		int i = 0;
		while ( i < n ) {
			int j = i + 1;
			while ( j <= n ) {
				if ( x[ j ] < x[ i ] ) {
					t = x[ j ];
					x[ j ] = x[ i ];
					x[ i ] = t;
				}
				j++;
			}
			i++;
		}
	}
}
