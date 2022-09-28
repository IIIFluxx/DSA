/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: User Interface for healthSim program.
** --------------------------------------------------------------------------
*/

import java.util.*;


public class UI 
{
    //Runs the interactive mode of healthSim
    public static void InteractiveMode()
    {
        System.out.println("Interactive mode is run");
        boolean menuLoop = true;
        String stats = "";
        int choice, interventionChoice;
        Network net = new Network(true);

        while(menuLoop == true)
        {
            System.out.println("\n\n" +
                "(1)  Load network \n" +
                "(2)  Set rates \n" +
                "(3)  Add a person(node) \n" +
                "(4)  Remove a person(node) \n" +
                "(5)  Add an edge \n" +
                "(6)  Remove an edge \n" +
                "(7)  Add a new infection \n" +
                "(8)  Display network \n" +
                "(9)  Update (run a timestep) \n" +
                "(10) Display population stats \n" +
                "(11) Display stats on a person \n" +
                "(12) Display health categories \n" +
                "(13) Save network \n" +
                "(14) Set interventions \n " +
                "(0)  Exit");
            choice = scInt("Select option"); // Retrieves a valid integer input.
            System.out.println("\n");
            /* -------- Based on user menu input --------- */
            switch(choice)
            {
                case 1: // Load network
                    System.out.println("Loading network...\n");
                    FileIO.loadNetwork(scString("Enter a filename for the network"), net);
                    FileIO.loadEdges(scString("Enter a filename for the edges"), net);
                    break;

                case 2: // Set rates
                    System.out.println("Setting rates...\n");
                    net.setDeathRate(scDouble("Enter the death rate (0-1)")); 
                    net.setTransRate(scDouble("Enter the transmission rate (0-1)")); 
                    net.setRecRate(scDouble("Enter the recovery rate (0-1)"));
                    break;

                case 3: // Add a node/person
                    System.out.println("Adding a node...\n");
                    net.newPerson(scString("Enter person name"), scString("Enter age"));
                    break;

                case 4: // Removing a node/person
                    System.out.println("Removing a node...\n");
                    net.removePerson(scString("Enter person name"));
                    break;

                case 5: // Add an edge
                    System.out.println("Adding an edge...\n");
                    net.newEdge(scString("Enter the visitor's ID"), scString("Enter the visited's ID"),
                    scInt("Enter type of connection \n 1 - At school \t 2 - At work \t 3 - At friends "));
                    break;

                case 6: // Remove an edge
                    System.out.println("Removing an edge...\n");
                    net.removeEdge(scString("Enter the visitor's ID"), scString("Enter the visited's ID"));
                    break;

                case 7: // Add a new infection
                    System.out.println("Adding a new infection...\n");
                    net.newInfection(scString("Enter person's name"));
                    break;

                case 8: // Display network.
                    System.out.println("Displaying network...\n");
                    net.displayGraph();
                    break;

                case 9: // Update(run timestep)
                    System.out.println("Updating(running a timestep)...\n");
                    System.out.println(net.update());
                    break;
                    
                case 10: // Display population stats
                    System.out.println("Displaying population stats...\n");
                    System.out.println(net.displayPopulationStatistics());
                    break;

                case 11: // Display stats on a person
                    System.out.println("Displaying stats on a person...\n");
                    net.displayPersonStats(scString("Enter person's name"));
                    break;

                case 12: // Display health categories
                    System.out.println("Displaying health categories...\n");
                    spacer();
                    System.out.println(net.displayHealthCategories());
                    spacer();
                    break;
                case 13: // Save network
                    System.out.println("Saving network...\n");
                    FileIO.saveNetwork(scString("Enter a filename for saving network"), net);
                    FileIO.saveEdges(scString("Enter a filename for saving connections"), net);
                    break;
                case 14: // Set interventions
                    System.out.println("Choose an intervention to implement (if any)\n " +
                                        "(1) - Shut down all local schools. \n" +
                                        "(2) - Restrict travel. \n" + 
                                        "(3) - Close all restaurants & bars. \n" +
                                        "(0) - Set no interventions. \n");
                    do
                    {
                        interventionChoice = scInt("Select a valid option (0-3)");
                    } while(interventionChoice < 0 || interventionChoice > 3);
                    
                    System.out.println("\n");
                    /* -------- Based on *valid* user menu input --------- */
                    net.applyIntervention(interventionChoice);
                    spacer();
                    break;
                case 0:
                    menuLoop = false;
                    // Save to a log file
                    stats = net.displayPopulationStatistics();
                    FileIO.appendStats(net.getLogFileName(), stats);
                    break;

                default: // If invalid menu option selected.
                    System.out.println("Invalid choice '" + choice + "'");
            }
        }
    }

    // E.g. usage: java healthSim -s RandomNames30.csv RandomEdges30.csv 0.025 0.0001 0.5 0
    

    //Runs the simulation behaviour of healthSim
    public static void SimulationMode(String netfile, String edgefile, double trans_rate, double recov_rate, double death_rate, int int_code)
    {
        Network net = new Network(false); // false == NOT interactive mode (therefore Simulation mode).
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        String stats = "";
        // Set recovery rates.
        net.setRecRate(recov_rate);
        net.setTransRate(trans_rate);
        net.setDeathRate(death_rate);

        // Load in the files now.
        FileIO.loadNetwork(netfile, net);
        FileIO.loadEdges(edgefile, net);

        // Apply interventions now that graph is loaded in. 
        net.applyIntervention(int_code);
        
        net.newInfection(scString("Enter patient zero's name"));

        do
        {
            System.out.println("Update: Y (1) or N (0) ");
            choice = sc.nextInt();
            if(choice == 1)
            {
                System.out.println("\n");
                System.out.println(net.update());
                System.out.println("\n");
            }
        } while(choice != 0);
        stats = net.displayPopulationStatistics();
        FileIO.appendStats(net.getLogFileName(), stats);
    }

    /* ------------------ User Input Methods ------------------ */
    private static int scInt(String msg)
    {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String input;
        int export = 0;
        while(!valid)
        {
            System.out.print(msg + ": ");
            input = sc.nextLine();
            try
            {
                export = Integer.parseInt(input);
                valid = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Input : '" + input + "' is invalid");
            }
        }
        return export;
    }
     
    private static double scDouble(String msg)
    {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String input;
        double export = 0.0;

        while(!valid)
        {
            System.out.print(msg + ": ");
            input = sc.nextLine();
            try
            {
                export = Double.parseDouble(input);
                valid = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Input : '" + input + "' is invalid");
            }
        }
        return export;
    }
    
    private static String scString(String msg)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg + ": ");
        return sc.nextLine();
    }

    /* Method for better user interface aesthetics */
    private static void spacer()
    {
        System.out.println("\n========================================================\n");
    }
}

