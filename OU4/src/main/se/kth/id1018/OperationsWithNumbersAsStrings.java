package main.se.kth.id1018;

import java.util.Scanner;

import static java.lang.System.out;

class OperationsWithNumbersAsStrings {
	public static void main(String[] args) {
		out.println("\nOPERATIONS WITH INTEGERS AS STRINGS\n");
		out.println("Do you want to (1) add or (2) subtract two integers?");
		Scanner in = new Scanner(System.in);
		System.out.print("\nInput: ");
		int input = 0;
		input = in.nextInt();
		// input two integers
		out.println("Input the integers:");
		String num1 = in.next();
		String num2 = in.next();
		out.println();
		in.close();
		if (input == 1) {
			String sum = add(num1, num2);
			display(num1, num2, sum, '+');
		}
		if (input == 2) {
			String diff = subtract(num1, num2);
			display(num1, num2, diff, '-');
		}
	}

	public static String add(String num1, String num2) {
		int len1 = num1.length() - 1;
		int len2 = num2.length() - 1;
		int lenMax = Math.max(len1, len2) + 1;
		int carry = 0;
		int sum = 0;
		StringBuilder sbSum = new StringBuilder(lenMax);
		for (int i = 0; i < lenMax + 1; i++) {
			int val1 = (len1 >= i) ? Character.getNumericValue(num1.charAt(len1 - i)) : 0;
			int val2 = (len2 >= i) ? Character.getNumericValue(num2.charAt(len2 - i)) : 0;
			sum = val1 + val2 + carry;
			carry = 0;
			if (sum >= 10) {
				sum -= 10;
				carry += 1;
			}
			sbSum.insert(0, sum);
		}
		while (sbSum.charAt(0) == '0' && sbSum.length() > 0)
			sbSum.deleteCharAt(0);
		String str = sbSum.toString();
		return str;
	}

	public static void display(String num1, String num2, String result, char operator) {
		int len1 = num1.length();
		int len2 = num2.length();
		int len = result.length();
		int maxLen = Math.max(Math.max(len1, len2), len);
		num1 = setLen(num1, maxLen - len1);
		num2 = setLen(num2, maxLen - len2);
		result = setLen(result, maxLen - len);
		out.println("  " + num1);
		out.println("" + operator + " " + num2);
		for (int i = 0; i <= maxLen + 2; i++)
			out.print("-");
		out.println();
		out.println("  " + result + "\n");
	}

	public static String subtract(String num1, String num2) {
		int len1 = num1.length() - 1;
		int len2 = num2.length() - 1;
		int lenMax = Math.max(len1, len2) + 1;
		int borrow = 0;
		int diff = 0;
		StringBuilder sbDiff = new StringBuilder(lenMax);
		for (int i = 0; i < lenMax + 1; i++) {
			int val1 = (len1 >= i) ? Character.getNumericValue(num1.charAt(len1 - i)) : 0;
			int val2 = (len2 >= i) ? Character.getNumericValue(num2.charAt(len2 - i)) : 0;
			diff = (val1 - borrow) - val2;
			if (diff < 0) {
				diff += 10;
				borrow = 1;
			} else
				borrow = 0;
			sbDiff.insert(0, diff);
		}
		while (sbDiff.charAt(0) == '0' && sbDiff.length() > 0)
			sbDiff.deleteCharAt(0);
		String str = sbDiff.toString();
		return str;
	}

	public static String setLen(String s, int numOfSpaces) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i <= numOfSpaces; i++)
			sb.insert(0, " ");
		return sb.toString();
	}
}