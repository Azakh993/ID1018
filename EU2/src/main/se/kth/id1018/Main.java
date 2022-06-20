package main.se.kth.id1018;

import java.util.Arrays;

import static main.se.kth.id1018.ExchangeSort.sort;

public class Main {
	public static void main( String[] args ) {
		int[] sequence = { 5, 4, 3, 2, 1 };

		System.out.println( Arrays.toString( sequence ) );
		int elements = sequence.length - 1;
		sort( elements, sequence );
		System.out.println( Arrays.toString( sequence ) );
	}
}
