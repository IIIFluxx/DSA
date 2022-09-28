/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: This file handles all File IO surrounding this program 
**              - both reading and saving.
** --------------------------------------------------------------------------
*/

import java.io.*;
import java.util.*;

public class FileIO 
{
    // Saves a network in a format identical to reading in network files
    public static void saveNetwork(String filename, Network net)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        // State of the network graph as strings
        String s = " ";
        DSALinkedList vertices = new DSALinkedList();
        DSALinkedList adjacency = new DSALinkedList();
        net.export(vertices, adjacency);
        Iterator iter = vertices.iterator();
        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);
            while(iter.hasNext())
            {
                s = iter.next() + "\n";
                pw.print(s); // Print each vertex
            }
            pw.close();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error within network: " + e.getMessage());
        }
        catch(IOException e) // Same code as OOPD. 
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
        }
    }

    // Saves a network's connections in a format identical to reading in edge files
    public static void saveEdges(String filename, Network net)
    {
        // Objects for File IO
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        // State of the network graph as strings
        DSALinkedList vertices = new DSALinkedList();
        DSALinkedList adjacency = new DSALinkedList();

        net.export(vertices, adjacency);

        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);
            for(Object o : adjacency)
            { // Print each edge
                pw.println((String)o);
            }
            pw.close();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println( "Error within network: " + e.getMessage());
        }
        catch(IOException e) // Same code as OOPD. 
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
        }
    }

    // Loads a network graph from file
    public static void loadNetwork(String filename, Network net)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArr;
        String tempAge, tempName;
        
        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line != null)
            {
                lineArr = line.split(",");
                if(lineArr.length == 2) // New node/person
                {   
                    tempName = lineArr[0];
                    tempAge = lineArr[1];
                    net.newPerson(tempName, tempAge);
                }
                else
                {
                    throw new IllegalArgumentException("Invalid line - loadNetwork(). \n");
                }
                line = bufRdr.readLine();
            }
            bufRdr.close();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error present within network file: " + e.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: Could not locate network file!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
        }
    }

    // Loads a network graph connections from file
    public static void loadEdges(String filename, Network net)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArr;
        String v1,v2, typeStr;
        int type;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line != null)
            {
                lineArr = line.split(",");
                if(lineArr.length == 3) // New edge
                {
                    v1 = lineArr[0];
                    v2 = lineArr[1];
                    typeStr = lineArr[2];
                    type = Integer.valueOf(typeStr);
                    net.newEdge(v1,v2,type);
                }
                else
                {
                    throw new IllegalArgumentException("Invalid line - loadEdges(). \n");
                }
                line = bufRdr.readLine();
            }

            bufRdr.close();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error present within edge file: " + e.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: Could not locate network edges file!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
        }
    }

    // Appends simulation state and statistics for each “timestep” to a file
    public static void appendStats(String filename, String statistics)
    {
        FileWriter fw = null;
        PrintWriter pw;

        try
        {
            fw = new FileWriter(filename, true);
            pw = new PrintWriter(fw);
            pw.println(statistics);
            pw.close();
        }
        catch(IOException e)
        {   
            if(fw != null)
            {
                try { fw.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
        }
    }
}