/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: This file is where the population network is created.
**  It contains common functionality used in both simulation 
**  and interactive mode.
** --------------------------------------------------------------------------
*/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.Random;

public class Network 
{

    /* ------------------ Person ------------------ */
    public class Person
    {
        //  Private classfields
        private String name;
        private String ID;
        private String age;
        private int status; // -1 for Rec, 0 for Susc, 1 for Infected, 2 for Dead.
        private int time;
        private int infection_rate;
        
        //  Alternate constructor
        public Person(String inName, String inAge, String inID)
        {
            name = inName;
            ID = inID;
            age = inAge;
            status = 0; // -1 for Rec, 0 for Susc, 1 for Infected, 2 for Dead.
            time = -1;
        }

		public boolean equals(Person inPerson)
        {
            return name == inPerson.name;
        }

        public int getStatus()
        {
            return status;
        }

        public int getTime()
        {
            return time;
        }
        

        public int getRate()
        {
            return infection_rate;
        }

        public void setStatus(int inStatus)
        {
            status = inStatus;
        }
        public void setTime(int inTime)
        {
            time = inTime;
        }

        public String toString()
        {
            return name + "," + age;
        }
    }


    /* ------------------ Network ------------------ */

    // Private classfields
    private DSAGraph graph;
    private DSALinkedList infections;
    private double trans_rate, recov_rate, death_rate;
    private int timestep;
    private String patientZero;
    private String logfileName;
	private boolean interactive;
    


    public Network(boolean inInteractive)
    {
        graph = new DSAGraph();
        infections = new DSALinkedList();
        trans_rate = 0.0;
        recov_rate = 0.0;
        death_rate = 0.0;
        timestep = 0;
        patientZero = "";
        logfileName = makelogfileName();
		interactive = inInteractive;
    }

    // Setters
    public void setTransRate(double inRate) 
    {
        if(inRate >= 0 && inRate <= 1)
        {
            trans_rate = inRate;
        // System.out.println("\n Rate: " + trans_rate + "\n"); //Test print
        }
        else
        {
            throw new IllegalArgumentException("Rates were not changed. Please enter a transmission rate between 0 and 1 please.\n");
        }
    }

    public void setRecRate(double inRate) 
    {
        if(inRate >= 0 && inRate <= 1)
        {
            recov_rate = inRate;
            //System.out.println("\n Rate: " + recov_rate + "\n"); //Test print
        }
        else
        {
            throw new IllegalArgumentException("Rates were not changed. Please enter a recovery rate between 0 and 1 please.\n");
        }
    }
    
    public void setDeathRate(double inRate) 
    {
        if(inRate >= 0 && inRate <= 1)
        {
            death_rate = inRate;
            //System.out.println("\n Rate: " + death_rate + "\n"); //Test print
        }
        else
        {
            throw new IllegalArgumentException("Rates were not changed. Please enter a death rate between 0 and 1 please.\n");
        }
    }

    public void setPatientZero(String inName)
    {
        if(!graph.hasVertex(inName))
        {
            throw new IllegalArgumentException("Person cannot be found within the network.");
        }
        patientZero = inName;
    }


    // Network getter
    public DSAGraph getGraph()
    {
        return graph;
    }

    public int getTimestep()
    {
        return timestep;
    }

    public String getLogFileName()
    {
        return logfileName;
    }
    //-----------------------
    //-----------------------
    
    // FUNCTIONALITY

    public void newPerson(String inName, String inAge)
    {
        graph.addVertex(new Person(inName, inAge, generateKey()),inName);
    }

    public void removePerson(String inName)
    {
        graph.removeVertex(inName);
    }

    public void newEdge(String p1, String p2, int inType)
    {
        graph.addEdge(p1,p2, inType);
    }

    public void removeEdge(String p1, String p2)
    {
        graph.removeEdge(p1,p2);
    }

    
    private String makelogfileName()
    {
        SimpleDateFormat logformat = new SimpleDateFormat("dd-MM_HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        return ("healthSimLog_" + logformat.format(date) + ".txt");
    }
    
    private String generateKey()
    {
        String key;
        key = String.valueOf(graph.getVertexCount() + 1);
        return key;
    }


    public void export(DSALinkedList exportVertices, DSALinkedList exportAdjacency)
    {
        graph.export(exportVertices, exportAdjacency);
    }


    public void displayGraph() // Shows all connections between nodes (edges between nodes)
    {
        System.out.println(
            "Adjacency List of Network. ' A | B ' == B is connected to A" );
        graph.displayAsList();
    }

    public String displayHealthCategories()
    {
        String output;
        
        output = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Susceptible ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
                + getName(0) + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Infected ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
                + getName(1) + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Recovered ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
                + getName(-1) + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Dead ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
                + getName(2);
        return output;
    }

    public String displayPopulationStatistics()
    {
        String result = "";
        String line = "";
        String data = "";
        //System.out.println("t: timestep s:susceptible i:infected r:recover p:pass away\n t    [ s   i   r   p ]\n");
        for(int ii=0;ii<getTimestep();ii++)
        {
            for(int jj=0;jj<4;jj++)
            {
                line = infections.toString() + "     ";
            }
            data = ii + "   " + line + "\n";
            line = "";
        }
        result = "t: Current timestep s: Susceptible i: Infected r: Recover p: Pass away\n t      [ s   i   r   p ]\n"
        + "--------------------------------\n"
        + data;
        return result;
    }

    


    public void displayPersonStats(String inName)
    {
        Person inPerson = (Person)graph.getValue(inName);
        System.out.println("Statistics for " + inPerson.name + ":" + 
            "\nAge: " + inPerson.age +
            "\nID: " + inPerson.ID +
            "\nHealth status: " + inPerson.status +
            "\nTimestep: " + inPerson.time);
        System.out.println("\nKey for health status:\n -1 = Recovered \n  0 = Susceptible \n  1 = Infected \n  2 = Dead");
    }


    public String getName(int status)
    {
        String output = "";
        DSALinkedList vertexList = graph.getVertices();
        Iterator iter = vertexList.iterator();

        while(iter.hasNext())
        {
            DSAGraphVertex vertex = (DSAGraphVertex)iter.next();
            Person person = (Person)vertex.getValue();
            if(person.getStatus() == status)
            {
                output = output + "\n" + person.name + "\n";
            }
        }
        return output;
    }


    public void newInfection(String inName)
    {
        Person inPerson = (Person)graph.getValue(inName);
        inPerson.status = 1;
        setPatientZero(inName);
    }

    // ---------------------- Timestep ----------------------

    // Make data object for storing in LL

    public int getCount(int status)
    {
        int count;
        count = 0;
       
        DSALinkedList vertexList = graph.getVertices();
        Iterator iter = vertexList.iterator();
        while(iter.hasNext())
        {
            DSAGraphVertex vertex = (DSAGraphVertex)iter.next();
            Person person = (Person)vertex.getValue();
            if(person.getStatus() == status)
            {
                count++;
            }
        }
        return count;
    }


    /* Sample code containing the SIR model was given to students. This was used as basis for this method. */
    public String update() // Algorithm is based on the SIR model. 
    {
        int count = graph.getVertexCount(); // Population count (unused)
        int sick,recover,death;
        double temp;
        String output;
        death = 0;
        int infectedCount = getCount(1); // Number of infected people.
        int susceptibleCount = getCount(0); // Number of susceptible people.
        //System.out.println("b4 inf: " + infectedCount); -- Test print statement.
        //System.out.println("b4 sus " + susceptibleCount); -- Test print statement.

        // SIR Formula: new_infected = trans_rate * Scur * Icur; Same as below
        temp = trans_rate * (double)susceptibleCount * (double)infectedCount; // Gets the number of infected.

        sick = (int)temp; // Number of infected as an integer


        // SIR Formula: new_recovered = recov_rate * Icur; Same as below
        temp = recov_rate * (double)infectedCount;

        // System.out.println("temp after recov*ic: " + temp); -- Test print statement.

        recover = (int)temp; // Number of recovered as an integer
        
        // Debugging statement.

        if(0 < temp && temp < 1) // If new_recovered is between 0 and 1
        {
            recover = infectedCount; 
        }

        temp = temp * death_rate; // Number of recovered * death_rate

        // System.out.println("DR: " + death_rate); -- Test print statement.
        // System.out.println("b4 death: " + death); -- Test print statement.
        // System.out.println("temp: " + temp); // -- Test print statement.
        death = (int)temp;
        // System.out.println("af death " + death); -- Test print statement.
        //System.out.println("af inf: " + infectedCount); -- Test print statement.
        //System.out.println("af sus " + susceptibleCount); -- Test print statement.

        // Part of the output per timestep.
        output = getTimestep() + ": Susceptible: " + susceptibleCount + " Infected: " + infectedCount + " Total deaths:  " + getCount(2) + " Total recovered: " + getCount(-1);
        
        // Timestep in this output ^ will be before the next timestep's calculations have been made. 
        // Output prints out timestep 1 THEN timestep 2 is calculated already.
        
        //System.out.println("getCount(0): " + getCount(0)); -- Test print statement.
        //System.out.println("getCount(1): " + getCount(1)); -- Test print statement.
        //System.out.println("sick: " + sick); -- Test print statement.

        if(getCount(0) > 0 && sick > 0) // If number of people Susceptible > 0 AND number of people Infected > 0
        {
            infectionMain(sick, patientZero); // Runs method that handles infection.
        }
        

        if(getCount(1) >= recover) // If number of people Recovered >= recover
        {
            recover(recover,death,patientZero); // Runs method that handles recovery.
        }
        
        // Storing statistics - to be saved.
        
        infections.insertLast(getCount(0));
        infections.insertLast(getCount(1));
        infections.insertLast(getCount(-1));
        infections.insertLast(getCount(2));
        timestep++; // Iterates to the next timestep.
        
        //System.out.println("Timestep: " + timestep); -- Test print statement.

        return output;
    }

    private void infectionMain(int sick, String label) // Based on the algorithm for Breadth-first-search.
    {
        boolean finished = false;

        //System.out.println("Getsick: " + sick); -- Test print statement.
        //System.out.println("getCount(0): " + getCount(0)); -- Test print statement.

        while(sick > 0 && getCount(0) != 0)
        {
            //System.out.println("getsick: " + sick); -- Test print statement.

            // Set all vertices as unvisited.
            graph.clearVisited();

            DSALinkedList links = new DSALinkedList();
            DSAQueue queue = new DSAQueue();
            
            // Get the patient zero's vertex
            DSAGraphVertex vertex = (DSAGraphVertex)graph.getVertex(label);
            vertex.setVisited();
            queue.enqueue(vertex);

            while(!queue.isEmpty() && finished == false)
            {
                vertex = (DSAGraphVertex)queue.dequeue();
                links = graph.getAdjacent(vertex.getLabel()); // Gets those adjacent to our patient zero

                if(!links.isEmpty())
                {
                    Iterator iter = links.iterator();
                    
                    while(iter.hasNext())
                    {
                        DSAGraphVertex newVertex = (DSAGraphVertex)iter.next();
                        while(!newVertex.getVisited()) // Loop until we set the adj vertex as visited
                        {
                            // System.out.println("Label: " + newVertex.getLabel()); -- Test print statement.
                            if(getPersonStatus(newVertex) == 0) 
                            {
                                if(sortingHat(newVertex)) 
                                {
                                    /* System.out.println("sortingHat() = true"); 
                                            -- Test print statement that only outputs when boolean returns true i.e. when infection occurs */
                                    sick--;
                                    // System.out.println("sick: " + sick); -- Test print statement. Stuck here
                                    if(sick == 0)
                                    {
                                        finished = true;
                                    }
                                }
                            }
                            newVertex.setVisited(); // Set as visited. Exit while loop after enqueuing the vertex.
                            queue.enqueue(newVertex);
                        }
                    }
                }
            }
        }
    }

    /* Much like the sorting hat in Harry Potter, this method decides the fate of a person's future -
        of a susceptible person adjacent to an infected person. */

    private boolean sortingHat(DSAGraphVertex inVertex)
    {
        int rate; // Person's rate
        boolean infected; // Function return
        Random rng = new Random(); // Random number generator
        Person person = (Person)inVertex.getValue(); // Person we deal with. 

        rate = person.getRate(); // Rate of that person.

        if(rate < rng.nextInt(4)) // If they're unlucky, they enter the loop to be infected.
        {
            // System.out.println("Inside if"); -- Test print statement.
            infected = true;
            person.setStatus(1);
            person.setTime(timestep);
        }
        else // They're safe!
        {
            // System.out.println("Inside else"); -- Test print statement.
            infected = false;
        }
        
        return infected;
    }


    private void recover(int recover, int death, String label) // Based on the algorithm for Breadth-first-search.
    {
        boolean finished = false;
        int count = 0;
        // Recover is > 0 & infNum != 0 -- enters while loop.
        while(recover > 0 && getCount(1) != 0)
        {
            count++;
            if(count > 10) // Program flaw.
            {
                recover = 0;
            }

            graph.clearVisited(); // Set all vertices as unvisited.
            DSALinkedList links = new DSALinkedList();
            DSAQueue queue = new DSAQueue();

            // Get the patient zero's vertex
            DSAGraphVertex vertex = (DSAGraphVertex)graph.getVertex(label);

            vertex.setVisited();
            queue.enqueue(vertex);

            while(!queue.isEmpty() && finished == false)
            {
                vertex = (DSAGraphVertex)queue.dequeue();
                links = graph.getAdjacent(vertex.getLabel()); // Gets those adjacent to our patient zero
                if(!links.isEmpty())
                {
                    Iterator iter = links.iterator();

                    while(iter.hasNext())
                    {
                        DSAGraphVertex newVertex = (DSAGraphVertex)iter.next();
                        while(!newVertex.getVisited()) // Loop until we set the adj vertex as visited
                        {
                            Person person = (Person)vertex.getValue();

                            /* Test print ------ If infected, print
                            if(person.getStatus() == 1)
                            {
                                System.out.println(getCount(1) + " inf || recov: " + recover); 
                            }
                            else
                            {
                                Test print ------ If not infected, view inf and recov
                                System.out.println(getCount(1) + " inf || susc person " + recover);
                            } */


                            if((recover+1) == getCount(1) && person.getStatus() == 1) 
                            // If recover + 1 == num infected & person is infected
                            {
                                person.setTime(timestep);
                                person.setStatus(-1); // Set as recovered.
                                recover--;
                            }
                            else if(person.getStatus() == 1 && person.getTime() < timestep)
                            {
                                person.setTime(timestep); 
                                // -1 for Rec, 0 for Susc, 1 for Infected, 2 for Dead.
                                if(death != 0)
                                {
                                    person.setStatus(2); // Set Person as dead
                                    death--;
                                }
                                else
                                {
                                    person.setStatus(-1); // Set Person as recovered.
                                }
                                recover--;
                                if(recover <= 0)
                                {
                                    finished = true;
                                }
                            }
                            newVertex.setVisited();// Set as visited. Exit while loop after enqueuing the vertex.
                            queue.enqueue(newVertex);
                        }   
                    }
                }
            }
        }
    }

    //-----------

    private int getPersonStatus(DSAGraphVertex inVertex)
    {
        Person person = (Person)inVertex.getValue();
        return person.status;
    }


    /* ------------------ Interventions ------------------ */
    // Delete edges when an intervention is put in place.

    /* Algorithm:
    - Iterate over the graph's edges
    - If edge type = X
    - delete edge using getFrom & getTo
    - modify infection_rate
    */

    public void applyIntervention(int intervention)
    {
        Iterator iter = graph.getEdges().iterator();

        switch (intervention)
        {
            case 1: // Schools are shut
            case 2: // Travel is restricted
            case 3: // Restaurants & Bars are closed.
            while(iter.hasNext())
            {
                graph.removeEdgeType(intervention);
                iter.next();     
            }
            break;
        }

        switch(intervention)
        {
            case 1: // Schools are shut
                // Modify transmission rate
                setTransRate(trans_rate-0.0025);
                System.out.println("Transmission rate changed to: " + trans_rate);
                break;

            case 2: // Travel is restricted
                // Modify transmission rate
                setTransRate(trans_rate-0.00005);
                System.out.println("Transmission rate changed to: " + trans_rate);
                break;
            case 3: // Restaurants & Bars are closed.
                // Modify transmission rate
                setTransRate(trans_rate-0.0005);
                System.out.println("Transmission rate changed to: " + trans_rate);
                break;
            default:
                System.out.println("No intervention chosen. Transmission rate has not been altered from default. \n");
                System.out.println("Default transmission rate: " + trans_rate + "\n");
        }
    }

}