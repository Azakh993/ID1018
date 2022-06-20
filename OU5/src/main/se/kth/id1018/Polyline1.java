package main.se.kth.id1018;

public class Polyline1 {
	private Point[] corners;
	private String colour = "black";
	private int width = 1;

	public Polyline1()
	{
		this.corners = new Point[ 0 ];
	}

	public Polyline1( Point[] corners )
	{
		this.corners = corners;
	}

	public String toString()
	{
		String str = "";
		if ( corners.length == 0 )
		{
			return str;
		}
		else
		{
			str += "{[";
			for ( Point p : corners )
			{
				str += p;
			}
			str += "], " + this.colour + ", " + this.width + "}";
			return str;
		}
	}

	public Point[] getCorners()
	{
		return this.corners;
	}

	public String getColour()
	{
		return this.colour;
	}

	public void setColour( String colour )
	{
		this.colour = colour;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth( int width )
	{
		this.width = width;
	}

	public double length()
	{
		double l = 0;
		for ( int i = 0; i < this.corners.length - 1; i++ )
		{
			double dx2 = Math.pow( this.corners[ i ].getX() - this.corners[ i + 1 ].getX(), 2 );
			double dy2 = Math.pow( this.corners[ i ].getY() - this.corners[ i + 1 ].getY(), 2 );
			l += Math.sqrt( dx2 + dy2 );
		}
		return l;
	}

	public void addTo( Point corners )
	{
		Point[] h = new Point[ this.corners.length + 1 ];
		int i = 0;
		for ( i = 0; i < this.corners.length; i++ )
		{
			h[ i ] = this.corners[ i ];
		}
		h[ i ] = corners;
		this.corners = h;
	}

	public void addInFrontOf( Point corners, String cornerName )
	{
		Point[] h = new Point[ this.corners.length + 1 ];
		int i = 0;
		for ( i = 0; i < this.corners.length; i++ )
		{
			if ( this.corners[ i ].getName() == cornerName )
			{
				h[ i + 1 ] = this.corners[ i ];
				h[ i ] = corners;
				i++;
				for ( int j = i; j < this.corners.length; j++ )
				{
					h[ j + 1 ] = this.corners[ j ];
				}
				break;
			}
			h[ i ] = this.corners[ i ];
		}
		this.corners = h;
	}

	public void remove( String cornerName )
	{
		Point[] h = new Point[ this.corners.length - 1 ];
		for ( int i = 0; i < this.corners.length - 1; i++ )
		{
			if ( this.corners[ i ].getName() == cornerName )
			{
				h[ i ] = this.corners[ i + 1 ];
				for ( int j = i; j < this.corners.length - 1; j++ )
				{
					h[ j ] = this.corners[ j + 1 ];
				}
				break;
			}
			h[ i ] = this.corners[ i ];
		}
		this.corners = h;
	}
}
