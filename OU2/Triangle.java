
class Triangle
{   
    // Calculates a triangles area using base and height
    public static double areaBaseHeight(double a, double b)
    {
        double area = a * b / 2;
        return area;
    }
    
    // Calculates a triangles area using two sides and the angle inbetween
    public static double areaAngleSides(double ab, double a, double b)
    {
        double d = (ab * Math.PI) / 180;
        double area = a * b * Math.sin(d) / 2;
        return area;
    }

    // Calculates a triangles perimeter using all three sides
    public static double perimeterSides(double a, double b, double c)
    {
        double perimeter = a + b + c;
        return perimeter;
    }

    // Calculates a triangles hypothenuse using the two shorter sides
    public static double hypothenuseSides(double a, double b)
    {
        double hypothenuse = Math.sqrt(a * a + b * b);
        return hypothenuse;
    }

    // Calculates one of a triangles bisectors using two sides and the angle inbetween
    public static double bisectorAngleSides(double ab, double a, double b)
    {
        double d = (ab * Math.PI) / 180;
        double bisector = 2 * a * b * Math.cos(d / 2) / (a + b);
        return bisector;
    }

    // Calculates an inradius using all three sides of a triangle
    public static double inradiusSides(double a, double b, double c)
    {
        double s = (a + b + c) / 2;
        double inradius = Math.sqrt((s - a) * (s - b) * (s - c) / s);
        return inradius;
    }

    // Calculates a circumradius using all three sides of a triangle
    public static double circumradiusSides(double a, double b, double c)
    {
        double s = (a + b + c) / 2;
        double numerator = a * b * c;
        double denominator = 4 * Math.sqrt(s * (s - a) * (s - b) * (s - c));
        double circumradius = numerator / denominator;
        return circumradius;
    }
}
