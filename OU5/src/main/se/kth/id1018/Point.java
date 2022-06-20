package main.se.kth.id1018;

public class Point {
	String coordinateName;
	int xCoordinate, yCoordinate;

	Point( String name, int x, int y )
	{
		coordinateName = name;
		xCoordinate = x;
		yCoordinate = y;
	}

	public Point( Point p )
	{
		coordinateName = p.coordinateName;
		xCoordinate = p.xCoordinate;
		yCoordinate = p.yCoordinate;
	}

	public String toString()
	{
		String str = "(" + coordinateName + " " + xCoordinate + " " + yCoordinate + ")";
		return str;
	}

	public String getName()
	{
		return coordinateName;
	}

	public double distance( Point p2 )
	{
		double diffX2 = Math.pow( p2.getX() - xCoordinate, 2 );
		double diffY2 = Math.pow( p2.getY() - yCoordinate, 2 );
		double dist = Math.sqrt( diffX2 + diffY2 );
		return dist;
	}

	public int getX()
	{
		return xCoordinate;
	}

	public int getY()
	{
		return yCoordinate;
	}

	public int setX( int x )
	{
		xCoordinate = x;
		return xCoordinate;
	}

	public int setY( int y )
	{
		yCoordinate = y;
		return yCoordinate;
	}
}