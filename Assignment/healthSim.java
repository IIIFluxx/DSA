/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: This file contains the main method 
**              & handles command-line arguments for the program to run. 
**              Also performs any last minute exception handling.
** --------------------------------------------------------------------------
*/

public class healthSim
{
    public static void main(String[] args)
    {
        try
        {
            if(args.length == 0) // No command line arguments : provides usage information 
            {
                System.out.println("\nhealthSim - Spread of disease simulator\nOptions:\n\n    Interactive environment\n    Run with -i \n \n    Simulation mode\n" 
                +  "    Run with -s <network_file> <edge_file> <trans_rate> <recov_rate> <death_rate> <int_code>");
                System.out.println("\n   Example: java healthSim -s RandomNames30.csv RandomEdges30.csv 0.05 0.001 0.5 0");
            }
            else if(args[0].equals("-i")) // if Interactive mode is chosen
            {
                if (args.length == 1)
                {
                    System.out.println("Running interactive mode!\n");
                    UI.InteractiveMode();
                }
                else
                {

                }

            }
            else if(args[0].equals("-s")) // if Simulation mode is chosen
            {
                try
                {
                    System.out.println("Running simulation mode!\n");
                    UI.SimulationMode(args[1], args[2], Double.parseDouble(args[3]),
                    Double.parseDouble(args[4]), Double.parseDouble(args[5]), Integer.parseInt(args[6]));
                }
                catch(IllegalStateException e)
                {
                    System.out.println(e.getMessage());
                }
                catch (NumberFormatException n)
                {
                    System.out.printf("Incorrect Usage.%nUsage information:%n3rd, 4th & 5th parameter (Rates)"
                    + " must be numbers. %n");
                }
            }
            else
            {
                System.out.printf("Incorrect Usage.%nUsage information:%n-i: interactive mode"
                + "%n-s: simulation mode (usage: healthSim -s <network_file> <edge_file> <trans_rate> <recov_rate> <death_rate> <int_code>%n");
            }
        }
		// Last resort exception handling in the case of critical errors.
        catch(Exception e)
        {
            System.out.println( "A critical error occured: " + e.getMessage());
        }
    }
}