/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 25/05/2020
**  Purpose: Basic test harness to isolate graph methods for testing.
**  Reference: This class was adapted from my submission for DSA Practical 06.
** --------------------------------------------------------------------------
*/

import java.util.*;
import java.io.*;

public class UnitTestDSAGraph
{
    public static void main(String [] args)
    {
        DSAGraph graph1 = new DSAGraph();
        DSALinkedList adj = new DSALinkedList();
        Iterator adjIter = adj.iterator();
        //---------------------------------------------------------------------------

        try
        {
            // Fill graph
            System.out.println("Loading graph from TestDataDSAGraph.txt \n");
            graph1 = loadFile("TestDataDSAGraph.txt");
            
            
            //https://csacademy.com/app/graph_editor/ to visualize
            System.out.println("Adjacency list for Graph: \n");
            graph1.displayAsList();
            
            // https://i.imgur.com/GRV5i3K.png

            // Comment out ↓
            System.out.println("\nExpected list:\n" +
            "A |  B  E  D  C\nB |  A  E\nE |  A  B  F  G\n" + 
            "D |  A  C  F\nC |  A  D\nF |  D  E  G\nG |  E  F");
   
            // https://i.imgur.com/O9DljFj.png


            spacer();

            // Test hasVertex -- find() operation.
            System.out.println("Testing hasVertex() \n");

            System.out.println("Testing for vertex A: " + graph1.hasVertex("A")
            + " (Expected: true)");

            System.out.println("Testing for vertex Z: " + graph1.hasVertex("Z")
            + " (Expected: false)");
            spacer();

            // Test getVertexCount
            System.out.println("Testing getVertexCount() \n");

            System.out.println("Graph Vertex count: " + graph1.getVertexCount() +
            " (Expected: 7)");

            spacer();

            // Test getEdgeCount
            System.out.println("Testing getEdgeCount() \n");
            
            System.out.println("Graph Edge count: " + graph1.getEdgeCount() +
            " (Expected: 10)");

            spacer();

            // Test getVertex

            System.out.println("Testing getVertex() \n");
            System.out.println("Testing for Vertex A.. \n");
            System.out.println("Found with label: " + graph1.getVertex("A") +
            "\n(Expected: A) \n");

            System.out.println("Now testing for a vertex not present in graph:");
            try
            {
                graph1.getVertex("Z");
                System.out.println("Z found - no exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected \n");
            }
            spacer();

            // Test getAdjacent

            System.out.println("Testing getAdjacent() \n");
            
            System.out.print("Testing adjacency of vertex B:\n\t");
            adj = graph1.getAdjacent("B");

            for(Object o : adj)
        {
            System.out.print( o + "  " );
        }

        

            System.out.println("\n \n Expected: \n \t A  E");
            System.out.println("\n");

            System.out.println("Testing for adjacency of vertex that does not exist");
            try
            {
                graph1.getAdjacent("Z");
                System.out.println("Z found - no exception  - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected \n");
            }
            spacer();

            // Test isAdjacent

            System.out.println("Testing isAdjacent() \n");
            System.out.println("Testing for adjacent verticies - A & E): " +
            graph1.isAdjacent("A", "E") + " (Expected: true) \n");

            System.out.println("Testing for non-adj verticies - A & F): " +
            graph1.isAdjacent("A", "F") + " (Expected: false) \n");

            spacer();

            // Test addVertex
            System.out.println("Testing addVertex() \n");
            
            // Idea: Check for non-existent vertex, add vertex, check again 
            System.out.println("Testing for vertex P: " + graph1.hasVertex("P")
            + " (Expected: false)");
            graph1.displayAsList();

            // Add vertex P and check again
            System.out.println("Adding vertex P to graph. \n");

            graph1.addVertex("P", "P");
            System.out.println("Testing for vertex P: " + graph1.hasVertex("P")
            + " (Expected: true)");
            graph1.displayAsList();

            spacer();
            

            // Test addEdge

            System.out.println("Testing addEdge() \n");

            // Idea: Check for non-existent edge, add edge, check again 
            System.out.println("Testing for adjacency between A & P: " + graph1.isAdjacent("A", "P")
            + " (Expected: false)");
            graph1.displayAsList();

            // Add vertex P and check again
            System.out.println("Adding edge between A & P. \n");

            graph1.addEdge("A","P",0);
            System.out.println("Testing for adjacency between A & P: " + graph1.isAdjacent("A", "P")
            + " (Expected: true)");
            graph1.displayAsList();

            System.out.println("Attempting to add edge to non-existent vertices. \n");
            try
            {
                graph1.addEdge("V", "X",0);
                System.out.println("Edge added - no exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected \n");
            }

            spacer();

            // Test removal
            System.out.println("Testing removeEdge()... \n");
            System.out.println("Removing edge between A & P. \n");
            graph1.removeEdge("A", "P");
            graph1.displayAsList();

            System.out.println("Testing removeVertex() \n");
            System.out.println("Removing vertex P. \n");
            graph1.removeVertex("P");
            graph1.displayAsList();

            System.out.println("Testing removeVertex() with edges present \n");
            System.out.println("Removing vertex G. \n");
            graph1.removeVertex("G");
            graph1.displayAsList();
            System.out.println("All of G's edges are also deleted. :)");
            System.out.println("Done. \n");
        }
        catch(Exception e)
        {
            System.out.println("Exception thrown - unknown cause. \n");
        }
    }


    // Load in a Graph from file.
    public static DSAGraph loadFile(String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray;
        DSAGraph graph = new DSAGraph();
        

        try 
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            while (line != null)
			{
                lineArray = line.split(" ");
                if (lineArray.length != 2)
                {
                    throw new IllegalArgumentException("Invalid file format");
                }
                
                // If adding a *new* vertex to the graph - 1st vertex
                if(!graph.hasVertex(lineArray[0]))
                {
                    graph.addVertex(lineArray[0], lineArray[0]);
                }
                // If adding a *new* vertex to the graph - 2nd vertex
                if(!graph.hasVertex(lineArray[1]))
                {
                    graph.addVertex(lineArray[1], lineArray[1]);
                }
                // Adding an edge between these two vertices
                graph.addEdge(lineArray[0], lineArray[1], 1);

                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch (IOException e) 
		{
			if (fileStrm != null) 
			{
                try 
                {
                    fileStrm.close(); 
                } 
                catch (IOException ex2) 
                {  
                    throw new IllegalArgumentException("Error in file reading, file cannot be closed");
                }
			}
		    System.out.println("Error in file processing: " + e.getMessage());
        }
        
        // File reading completed if at this stage. Processing next.

        return graph;

    }

    public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }
}

