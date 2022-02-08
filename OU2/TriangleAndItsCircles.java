import java.util.*;

public class TriangleAndItsCircles
{
    public static void main(String[] args)
    {
        System.out.println("TRIANGLES AND THEIR CIRCLES\n");

        int input = 0;

        while(input != 7)
        {
            System.out.println("Enter 1 to calculate a triangle's area");
            System.out.println("Enter 2 to calculate a triangle's perimeter");
            System.out.println("Enter 3 to calculate a triangle's hypothenuse");
            System.out.println("Enter 4 to calculate one of a triangle's bisector");
            System.out.println("Enter 5 to calculate a triangle's inradius");
            System.out.println("Enter 6 to calculate a triangle's circumradius");
            System.out.println("Enter 7 to exit program");

            Scanner in = new Scanner(System.in);
            in.useLocale(Locale.US);

            System.out.print("\nInput: ");
            input = in.nextInt();

            double a, b, c, ab;

            switch(input)
            {
                case 1:
                    System.out.println("\nEnter 1 to calculate using base and height");
                    System.out.println("Enter 2 to calculate using two sides and the angle inbetween");

                    System.out.print("\nInput: ");
                    int secondInput = in.nextInt();

                    switch(secondInput)
                    {
                        case 1:
                            System.out.print("\nProvide magnitude of base: ");
                            a = in.nextDouble();

                            System.out.print("Provide magnitude of height: ");
                            b = in.nextDouble();

                            System.out.println("\nArea: " + Triangle.areaBaseHeight(a, b));
                            System.out.println("\nRestarting! \n");

                            break;
                        
                        case 2:
                            System.out.print("\nProvide magnitude of first side: ");
                            a = in.nextDouble();

                            System.out.print("Provide magnitude of second side: ");
                            b = in.nextDouble();

                            System.out.print("Provide angle between sides: ");
                            ab = in.nextDouble();

                            System.out.println("\nArea: " + Triangle.areaAngleSides(ab, a, b));
                            System.out.println("\nRestarting! \n");

                            break;
                    }

                    break;
                
                case 2:
                    System.out.print("Provide magnitude of first side: ");
                    a = in.nextDouble();

                    System.out.print("Provide magnitude of second side: ");
                    b = in.nextDouble();

                    System.out.print("Provide magnitude of third side: ");
                    c = in.nextDouble();

                    System.out.println("\nPerimeter: " + Triangle.perimeterSides(a, b, c));
                    System.out.println("\nRestarting! \n");

                    break;

                case 3:
                    System.out.print("Provide magnitude of first side: ");
                    a = in.nextDouble();

                    System.out.print("Provide magnitude of second side: ");
                    b = in.nextDouble();

                    System.out.println("\nHypothenuse: " + Triangle.hypothenuseSides(a, b));
                    System.out.println("\nRestarting! \n");

                    break;

                case 4:
                    System.out.print("Provide magnitude of first side: ");
                    a = in.nextDouble();

                    System.out.print("Provide magnitude of second side: ");
                    b = in.nextDouble();

                    System.out.print("Provide angle between sides: ");
                    ab = in.nextDouble();

                    System.out.println("\nBisector: " + Triangle.bisectorAngleSides(ab, a, b));
                    System.out.println("\nRestarting! \n");

                    break;
                    
                case 5:
                    System.out.print("Provide magnitude of first side: ");
                    a = in.nextDouble();

                    System.out.print("Provide magnitude of second side: ");
                    b = in.nextDouble();

                    System.out.print("Provide magnitude of third side: ");
                    c = in.nextDouble();

                    System.out.println("\nInradius: " + Triangle.inradiusSides(a, b, c));
                    System.out.println("\nRestarting! \n");

                    break;
                
                case 6:
                    System.out.print("Provide magnitude of first side: ");
                    a = in.nextDouble();

                    System.out.print("Provide magnitude of second side: ");
                    b = in.nextDouble();

                    System.out.print("Provide magnitude of third side: ");
                    c = in.nextDouble();

                    System.out.println("\nCircumradius: " + Triangle.circumradiusSides(a, b, c));
                    System.out.println("\nRestarting! \n");

                    break;
            }
        }
    }
}
