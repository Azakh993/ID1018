public class Polyline 
{ 
    private Point[] corners; 
    private String colour = "black"; 
    private int width = 1; 
 
    public Polyline() 
    { 
        this.corners = new Point[0]; 
    } 
 
    public Polyline(Point[] corners) 
    { 
        this.corners = new Point[corners.length];
        
        for(int i = 0; i < corners.length; i++) 
            this.corners[i] = new Point(corners[i]); 
    }

    public String toString()
    {
        String str = "";

        if(corners.length == 0)
            return str;
        
        else
        {
            str += "{[";
            
            for(Point p : corners)
                str += p;

            str += "], " + colour + ", " + width + "}";

            return str;
        }
    } 
 
    public Point[] getCorners()
    {
        Point[] c = new Point[corners.length];

        for(int i = 0; i < corners.length; i++)
            // c[i] = corners[i];
            c[i] = new Point(corners[i]);          // changed according to feedback
        
        return c;
    }
 
    public String getColour()
    {
        return colour;
    }
 
    public int getWidth()
    {
        return width;
    } 

    public void setColour(String colour)
    {
        this.colour = colour;
    } 
 
    public void setWidth(int width)
    {
        this.width = width;
    }
 
    public double length()
    {
        double l = 0;
        
        for(int i = 0; i < corners.length - 1; i++)
        {
            double dx2 = Math.pow(this.corners[i].getX() - this.corners[i+1].getX(), 2);
            double dy2 = Math.pow(this.corners[i].getY() - this.corners[i+1].getY(), 2);
            l += Math.sqrt(dx2 + dy2);
        }

        return l;
    } 
 
    public void addTo(Point corners) 
    { 
        Point[] h = new Point[this.corners.length + 1]; 
        int i = 0; 
        
        for(i = 0; i < this.corners.length; i++) 
            h[i] = this.corners[i]; 

        h[i] = new Point(corners); 
 
        this.corners = h; 
    } 
 
    public void addInFrontOf(Point corners, String cornerName)
    {
        Point[] h = new Point[this.corners.length + 1];
        int i = 0;
        
        for(i = 0; i < this.corners.length; i++)
        {   
            if(this.corners[i].getName().equals(cornerName))
            {
                h[i + 1] = this.corners[i]; 
                h[i] = new Point(corners);
                i++;

                for(int j = i; j < this.corners.length; j++)
                    h[j + 1] = this.corners[j]; 

                break;
            }

            h[i] = this.corners[i];
        }

        this.corners = h; 
    } 
 
    public void remove(String cornerName)
    {
        Point[] h = new Point[this.corners.length - 1];
        
        for(int i = 0; i < this.corners.length - 1; i++)
        {            
            if(this.corners[i].getName().equals(cornerName))
            {
                h[i] = this.corners[i + 1]; 

                for(int j = i; j < this.corners.length - 1; j++)
                    h[j] = this.corners[j + 1];

                break;
            }

            h[i] = this.corners[i];
        }

        this.corners = h; 
    }


    public class PolylineIterator
    {
        private int current = -1;

        public PolylineIterator()
        {
            if(Polyline.this.corners.length > 0)
                current = 0;
        }

        public boolean doesCornerExist()
        {
            return current != -1;
        }
        
        public Point corner() throws java.util.NoSuchElementException
        {
            if(!this.doesCornerExist())
                throw new java.util.NoSuchElementException(
                                        "End of iteration");

            Point corner = Polyline.this.corners[current];

            return corner;
        }

        public void moveForward()
        {
            if(current >= 0 && current < Polyline.this.corners.length - 1)
                current++;
                
            else
                current = -1;
        }
    }
    
}