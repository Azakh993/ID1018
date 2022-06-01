package main.se.kth.id1018;

import java.util.Locale;
import java.util.Scanner;

class ShortestPath {
	public static void main(String[] args) {
		System.out.println("\nTHE SHORTEST PATH BETWEEN Z1 AND Z4");
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		System.out.print("\nNumber of stations in Z2: ");
		int stationsInZ2 = in.nextInt();
		System.out.print("\nNumber of stations in Z3: ");
		int stationsInZ3 = in.nextInt();
		double[] distToZ2 = new double[stationsInZ2 + 1];
		double[][] distZ2ToZ3 = new double[stationsInZ2 + 1][stationsInZ3 + 1];
		double[] distToZ3 = new double[stationsInZ3 + 1];
		for (int i = 1; i <= stationsInZ2; i++) {
			System.out.print("Distance from X to U" + i + ": ");
			distToZ2[i] = in.nextDouble();
		}
		for (int i = 1; i <= stationsInZ2; i++)
			for (int j = 1; j <= stationsInZ3; j++) {
				System.out.print("Distance from U" + i + " to V" + j + ": ");
				distZ2ToZ3[i][j] = in.nextDouble();
			}
		for (int i = 1; i <= stationsInZ3; i++) {
			System.out.print("Distance from V" + i + " to Y: ");
			distToZ3[i] = in.nextDouble();
		}
		int[] stationsOnPath = new int[3];
		stationsOnPath = DetermineTheShortestPath.stations(distToZ2, distZ2ToZ3, distToZ3);
		System.out.print("\nStations in the shortest path: X, U" + stationsOnPath[1]);
		System.out.println(", V" + stationsOnPath[2] + " and Y");
		double shortestPath = DetermineTheShortestPath.path(distToZ2, distZ2ToZ3, distToZ3);
		System.out.println("The shortest distance is: " + shortestPath);
		System.out.println();
		in.close();
	}
}