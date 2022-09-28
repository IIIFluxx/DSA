/**
 * DSA Final Assessment Question 2 - DSAGraph.java
 * Name : Bharath Sukesh
 * ID   : 19982634
 * Ref  : Parts of this class was adapted from my submission for DSA Practical 06 and my DSA Assignment
 **/
import java.util.*;

public class DSAGraph 
{
    private DSALinkedList vertices;
    private int vertexCount;
    private int edgeCount;

    public DSAGraph() 
    {
        vertices = new DSALinkedList();
        vertexCount = 0;
        edgeCount = 0;
    }

    public void addVertex(String label, Object value) 
    {
        GraphVertex vertex = new GraphVertex(label, value); 
        if (!(hasVertex(label))) 
        {
            vertices.insertLast(vertex);
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2)
    {
        GraphVertex v1, v2; 
        
        v1 = getVertex(label1); 
        v2 = getVertex(label2);   

        v1.addEdge(v2); 

        edgeCount++;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for (Object v : vertices) 
        {
		   GraphVertex vg = (GraphVertex) v;
           if (vg.getLabel().equals(label))
			   has = true;
        }
        return has;
    }

    public GraphVertex getVertex(String label) 
    {
        GraphVertex theVertex = null;
        for (Object v : vertices) 
        {
		   GraphVertex vg = (GraphVertex) v;
           if (vg.getLabel().equals(label))
			   theVertex = vg;
        }
		return theVertex;    
	}


    // ADDED METHODS.
    // Reference: Used previously in practicals.
    
    public void displayAsList() 
    {
        GraphVertex currVertex; 
        GraphVertex vertex2;
        String outputStr;
        Iterator verticesIterator = vertices.iterator();
        Iterator iter2;

        System.out.println("Adjacency List display");

        while(verticesIterator.hasNext()) // Iterate through our verticies list
        {
            outputStr = " ";

            currVertex = (GraphVertex)(verticesIterator.next());
            // Add label to outputStr and separate the label name by its elements by '|'
            outputStr = (currVertex.getLabel() + " > ");
            
            //Iterate through adjacency list of current vertex.
            iter2 = currVertex.getAdjacent().iterator();

            while(iter2.hasNext())
            {
                vertex2 = (GraphVertex)(iter2.next());
                outputStr = outputStr + (vertex2.getLabel());
                if(iter2.hasNext())
                {
                   outputStr += ", ";
                }
            }
            System.out.println(outputStr);
        }
    }
    // ---------- Added methods necessary for displayAsMatrix implementation(). -------------
	
    public boolean isAdjacent(GraphVertex v1, GraphVertex v2)
    {
        boolean adjacent = false;
        for(Object o : (v1.getAdjacent()))
        {
            if(((GraphVertex)o).equals(v2))
            {
                adjacent = true;
            }
        }
        return adjacent; 
    }

    // --------- displayAsMatrix() ---------------

    public void displayAsMatrix()
    {
        int numVert = vertexCount;
        int[][] matrix;
        int index = 0;
        GraphVertex[] arr;
        arr = new GraphVertex[numVert];
        matrix = new int[numVert][numVert];

        // Populate array 
        for( Object o : vertices)
        {
            arr[index] = (GraphVertex)o;
            index++;
        }

        // Populate matrix
        
        for(int i = 0; i<numVert; i++)
        {
            for(int j=0;j<numVert;j++)
            {
                if(isAdjacent(arr[i], arr[j]))
                {
                    matrix[i][j] = 1;
                }
                else
                {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Print matrix
        System.out.print("  "); // Top left
        for(Object o : vertices) 
        {
            // System.out.print("  " + ((GraphVertex)o).getLabel()); // Top rows (e.g. Graph 1: A -> G)
            String pad = String.format("%0$1s", ((GraphVertex)o).getLabel());
            System.out.print("  " + pad); // Top rows (e.g. Graph 1: A -> G)
        }
        System.out.print("\n");
        for(int ii=0;ii<numVert;ii++)
        {
            System.out.print("++++"); /* Divider between top-axis & grid */
        }

        for(int i = 0; i < numVert; i++)
        {
            //System.out.print("\n" + arr[i].getLabel() + " |"); /* LHS-Axis Titles (A-> G) */
            String padded = String.format("%0$1s|", arr[i].getLabel());
            System.out.print("\n" + padded); 
            for(int j = 0; j < numVert; j++)
            {
                // System.out.print("  " + matrix[i][j]);
                String paddedd = String.format("%0$1s", matrix[i][j]);
                System.out.print("  " + paddedd); // | is the column divider/separates the columns 
                                                        // matrix[i][j] is the adjacency matrix value at that point (ith row, jth col) ~ either 1 or 0
            }
        }
        System.out.println(); // Bottom right
    }


    
}  
