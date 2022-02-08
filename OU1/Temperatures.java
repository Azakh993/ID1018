import java.util.*;

class Temperatures
{
public static void main(String[] args)
    {
        System.out.println("TEMPERATURE\n");

        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        System.out.print("Number of weeks: ");
        int weeks = in.nextInt();
        System.out.print("Number of measurements per week: ");
        int measurementsPerWeek = in.nextInt();

        double[] [] tData = new double [weeks + 1][measurementsPerWeek + 1];

        for(int week = 1; week <= weeks; week++)
        {
            System.out.println("Temperature - Week " + week + ":");

            for(int j = 1; j <= measurementsPerWeek; j++)
                tData[week][j] = in.nextDouble();
        }

        System.out.println("\nTemperatures: ");
        
        for(int week = 1; week <= weeks; week++)
        {
            System.out.print("Week " + week + ": \t");
            
            for(int measurement = 1; measurement <= measurementsPerWeek; measurement++)
                System.out.print(tData[week][measurement] + "\t");

            System.out.println();
        }

        double[] minWeekT = new double[weeks + 1];
        double[] maxWeekT = new double[weeks + 1];
        double[] sumWeekT = new double[weeks + 1];
        double[] avgWeekT = new double[weeks + 1];

        // Student's code starts here
        // Code block below fills the four arrays above with data
        for(int i = 1; i <= weeks; i++)
        {
            // Assignment of initial measurement values per week
            minWeekT[i] = tData[i][1];
            maxWeekT[i] = tData[i][1];

            for(int j = 1; j <= measurementsPerWeek; j++)
            {
                // If tData measurement for the week is smaller than initial value,
                // replace it with the tData measurement
                if(tData[i][j] < minWeekT[i])
                    minWeekT[i] = tData[i][j];

                // If tData measurement for the week is larger than initial value,
                // replace it with the tData measurement
                if(tData[i][j] > maxWeekT[i])
                    maxWeekT[i] = tData[i][j];
                
                // Sum each measurement of the week to the index number of the week
                sumWeekT[i] += tData[i][j];
            }

            // Calculate the average of the measurements from the sum and number of
            // measurements conducted per week
            avgWeekT[i] = sumWeekT[i] / measurementsPerWeek;
        }


        System.out.print("\nWeek number\t" + "Lowest Temp\t" + "Highest Temp\t");
        System.out.print("Sum Temp\t" + "Average Temp\n");

        // Print contents of arrays
        for(int i = 1; i <= weeks; i++)
        {
            System.out.print("Week " + i + ": \t");
            System.out.print(minWeekT[i] + "\t\t");
            System.out.print(maxWeekT[i] + "\t\t");
            System.out.print(sumWeekT[i] + "\t\t");
            System.out.print(avgWeekT[i] + "\n");
        }

        // Data block for sorting the measurements in minWeekT and maxWeekT        
        for (int i = 1; i <= weeks; i++)
            for (int j = 1; j <= weeks - i; j++)
            {
                // Compares minWeekT[j+1] and minWeekT[j]
                // If minweekT[j] is larger than minweekT[j+1], they are swapped
                if (minWeekT[j] > minWeekT[j+1])
                {

                    double tempMinWeekT = minWeekT[j];
                    minWeekT[j] = minWeekT[j + 1];
                    minWeekT[j + 1] = tempMinWeekT;
                }

                // Compares minWeekT[j+1] and minWeekT[j]
                // If maxweekT[j] is smaller than maxweekT[j+1], they are swapped
                if (maxWeekT[j] < maxWeekT[j+1])
                {

                    double tempMaxWeekT = maxWeekT[j];
                    maxWeekT[j] = maxWeekT[j + 1];
                    maxWeekT[j + 1] = tempMaxWeekT;
                }
            }

            sumWeekT[1] = 0;

            // Sums all the measurements in tData
            for (int i = 1; i <= weeks; i++)
                for (int j = 1; j <= measurementsPerWeek; j++)
                    sumWeekT[1] += tData[i][j];
            
        // Student's code ends here

        double lowestTemp = minWeekT[1];
        double highestTemp = maxWeekT[1];
        double sumOfTemp = sumWeekT[1];
        double averageTemp = 0;
        
        // Student's code resumes here
        averageTemp = sumOfTemp / (weeks * measurementsPerWeek);

        System.out.println("\nLowest measured temp:\t" + lowestTemp);
        System.out.println("Highest measured temp:\t" + highestTemp);
        System.out.println("Average of temp data:\t" + averageTemp);

        // Student's code ends here

        in.close();
    }
}