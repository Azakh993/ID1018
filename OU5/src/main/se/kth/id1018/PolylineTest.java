package main.se.kth.id1018;

import java.io.PrintWriter;

class PolylineTest {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out, true);
		Point p1 = new Point("A", 1, 2);
		Point p2 = new Point("B", 4, 2);
		Point p3 = new Point("C", 3, 1);
		Point p4 = new Point("D", 1, 1);
		Point p5 = new Point("E", 4, 4);
		Polyline pl1 = new Polyline();
		pl1 = new Polyline(new Point[]{p1, p3, p4});
		out.println("Initial polyline data: " + pl1 + "\n");
		pl1.setColour("Blue");
		out.println("Polyline colour after setColour('Blue'): " + pl1.getColour() + "\n");
		pl1.setWidth(3);
		out.println("Polyline width after setWidth(3): " + pl1.getWidth() + "\n");
		out.println("Polyline length based on included corners: " + pl1.length() + "\n");
		pl1.addTo(p5);
		pl1.addInFrontOf(p2, "A");
		pl1.remove("C");
		out.println("Corners of Polyline after adding two corners and removing one corner: ");
		for (Point p : pl1.getCorners())
			out.println(p);
		Polyline.PolylineIterator pi = pl1.new PolylineIterator();
		System.out.println("\nPrint of each corner of polyline using PolylineIterator:");
		while (pi.doesCornerExist()) {
			System.out.println(pi.corner());
			pi.moveForward();
		}
	}
} 