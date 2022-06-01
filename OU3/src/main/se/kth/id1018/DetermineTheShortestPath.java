package main.se.kth.id1018;

class DetermineTheShortestPath {
	public static double path(double[] a, double[][] b, double[] c) {
		int z2 = stations(a, b, c)[1];
		int z3 = stations(a, b, c)[2];
		double shortestPath = a[z2] + b[z2][z3] + c[z3];
		return shortestPath;
	}

	public static int[] stations(double[] a, double[][] b, double[] c) {
		double shortestPath = a[1] + b[1][1] + c[1];
		int[] stationsOnPath = new int[3];
		for (int i = 1; i < a.length; i++)
			for (int j = 1; j < c.length; j++)
				if (a[i] + b[i][j] + c[j] < shortestPath) {
					shortestPath = a[i] + b[i][j] + c[j];
					stationsOnPath[1] = i;
					stationsOnPath[2] = j;
				}
		return stationsOnPath;
	}
}

