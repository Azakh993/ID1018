package main.se.kth.id1018;

import java.util.Locale;
import java.util.Scanner;

class Temperatures {
	public static void main(String[] args) {
		System.out.println("TEMPERATURE\n");
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		System.out.print("Number of weeks: ");
		int weeks = in.nextInt();
		System.out.print("Number of measurements per week: ");
		int measurementsPerWeek = in.nextInt();
		double[][] tData = new double[weeks + 1][measurementsPerWeek + 1];
		for (int week = 1; week <= weeks; week++) {
			System.out.println("Temperature - Week " + week + ":");
			for (int j = 1; j <= measurementsPerWeek; j++)
				tData[week][j] = in.nextDouble();
		}
		System.out.println("\nTemperatures: ");
		for (int week = 1; week <= weeks; week++) {
			System.out.print("Week " + week + ": \t");
			for (int measurement = 1; measurement <= measurementsPerWeek; measurement++)
				System.out.print(tData[week][measurement] + "\t");
			System.out.println();
		}
		double[] minWeekT = new double[weeks + 1];
		double[] maxWeekT = new double[weeks + 1];
		double[] sumWeekT = new double[weeks + 1];
		double[] avgWeekT = new double[weeks + 1];
		for (int i = 1; i <= weeks; i++) {
			minWeekT[i] = tData[i][1];
			maxWeekT[i] = tData[i][1];
			for (int j = 1; j <= measurementsPerWeek; j++) {
				if (tData[i][j] < minWeekT[i])
					minWeekT[i] = tData[i][j];
				if (tData[i][j] > maxWeekT[i])
					maxWeekT[i] = tData[i][j];
				sumWeekT[i] += tData[i][j];
			}
			avgWeekT[i] = sumWeekT[i] / measurementsPerWeek;
		}
		System.out.print("\nWeek number\t" + "Lowest Temp\t" + "Highest Temp\t");
		System.out.print("Sum Temp\t" + "Average Temp\n");
		for (int i = 1; i <= weeks; i++) {
			System.out.print("Week " + i + ": \t");
			System.out.print(minWeekT[i] + "\t\t");
			System.out.print(maxWeekT[i] + "\t\t");
			System.out.print(sumWeekT[i] + "\t\t");
			System.out.print(avgWeekT[i] + "\n");
		}
		for (int i = 1; i <= weeks; i++)
			for (int j = 1; j <= weeks - i; j++) {
				if (minWeekT[j] > minWeekT[j + 1]) {
					double tempMinWeekT = minWeekT[j];
					minWeekT[j] = minWeekT[j + 1];
					minWeekT[j + 1] = tempMinWeekT;
				}
				if (maxWeekT[j] < maxWeekT[j + 1]) {
					double tempMaxWeekT = maxWeekT[j];
					maxWeekT[j] = maxWeekT[j + 1];
					maxWeekT[j + 1] = tempMaxWeekT;
				}
			}
		sumWeekT[1] = 0;
		for (int i = 1; i <= weeks; i++)
			for (int j = 1; j <= measurementsPerWeek; j++)
				sumWeekT[1] += tData[i][j];
		double lowestTemp = minWeekT[1];
		double highestTemp = maxWeekT[1];
		double sumOfTemp = sumWeekT[1];
		double averageTemp = 0;
		averageTemp = sumOfTemp / (weeks * measurementsPerWeek);
		System.out.println("\nLowest measured temp:\t" + lowestTemp);
		System.out.println("Highest measured temp:\t" + highestTemp);
		System.out.println("Average of temp data:\t" + averageTemp);
		in.close();
	}
}
