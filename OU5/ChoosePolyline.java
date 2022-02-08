import java.util.*;

class ChoosePolyline
{
    public static final Random rand = new Random();
    public static final int QUANTITY_POLYLINES = 10;

    public static void main (String[] args)
    {
        Polyline[] polylines = new Polyline[QUANTITY_POLYLINES];

        for(int i = 0; i < QUANTITY_POLYLINES; i++)
            polylines[i] = randPolyline();

        for(int i = 0; i < QUANTITY_POLYLINES; i++)
            System.out.println(polylines[i].toString());


        Polyline shortestYellow = null;
        double shortestLength = Double.POSITIVE_INFINITY;

        for(int i = 0; i < QUANTITY_POLYLINES; i++)
        {
            if(polylines[i].getColour().equals("yellow") && polylines[i].length() < shortestLength)
            {
                shortestYellow = polylines[i];
                shortestLength = shortestYellow.length();
            }
        }

        if(shortestLength != Double.POSITIVE_INFINITY)
        {
            System.out.println("\nShortest yellow polyline: " + shortestYellow);
            System.out.println("Length: " + shortestLength);
        }

        else
            System.out.println("\nNo yellow lines found!");
    }

    public static Point randPoint()
    {
        String n = "" + (char) (65 + rand.nextInt (26));
        int x = rand.nextInt (11);
        int y = rand.nextInt (11);
        
        return new Point (n, x, y);
    }

    public static Polyline randPolyline()
    {
        Polyline polyline = new Polyline();
        int numberOfCorners = 2 + rand.nextInt (7);
        int numberOfSelCorners = 0;
        boolean[] selName = new boolean[26];

        Point selPoint = null;
        char selChar = 0;

        while (numberOfSelCorners < numberOfCorners)
        {
            selPoint = randPoint();
            selChar = selPoint.getName().charAt(0);

            if(!selName[selChar - 65])
            {
                selName[selChar - 65] = true;
                polyline.addTo(selPoint);
                numberOfSelCorners++;
            }
        }

        int randColour = rand.nextInt(3);
        
        switch(randColour)
        {
            case 0:
                polyline.setColour("blue");
                break;
            
            case 1:
                polyline.setColour("red");
                break;
            
            case 2:
                polyline.setColour("yellow");
                break;
        }

        return polyline;
    }

}
