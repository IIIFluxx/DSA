/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: Implementation of a bidirectional graph ADT.
**  Reference: This class was adapted from my submission for DSA Practical 06
** --------------------------------------------------------------------------
*/

import java.util.*;

public class DSAGraph implements Iterable
{
    /* ------------------ DSAGraphEdge ------------------ */
    private class DSAGraphEdge 
    {
    /* -- Private classfields -- */
    private String label;
    private DSAGraphVertex from;
    private DSAGraphVertex to;
    private int type;
    
    // Alternate constructor --- CONSTRUCTOR DSAGraphEdge IMPORTS fromVertex, toVertex, [inLabel, inValue]
    private DSAGraphEdge(DSAGraphVertex inFrom, DSAGraphVertex inTo, int inType)
    {
        label = inFrom.getLabel() + "," + inTo.getLabel() + "," + inType;
        from = inFrom;
        to = inTo;
        type = inType;
    }

    // ACCESSOR getLabel IMPORTS NONE EXPORTS label
    public String getLabel()
    {
        return label;
    }

    // ACCESSOR getFrom IMPORTS NONE EXPORTS vertex
    public DSAGraphVertex getFrom()
    {
        return from;
    }

    // ACCESSOR getTo IMPORTS NONE EXPORTS vertex
    public DSAGraphVertex getTo()
    {
        return to;
    }

    // ACCESSOR getType IMPORTS NONE EXPORTS type
    public int getType()
    {
        return type;
    }

    public void setType(int inType)
    {
        type = inType;
    }

    
    //ACCESSOR toString IMPORTS NONE EXPORTS string
    public String toString()
    {
        String str;
        str = from.getLabel() + "," + to.getLabel() + "," + type;
        return str;
    }

}


    /* ------------------ DSAGraph ------------------ */

    /* -- Private classfields -- */
    private DSALinkedList vertices;
    private DSALinkedList edges;

    /* Default constructor */
    // Exports memory adress of a new DSAGraph
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    public DSALinkedList getVertices()
    {
        return vertices;
    }

    

    public DSALinkedList getEdges()
    {
        return edges;
    }
   

    // MUTATOR addVertex IMPORTS label, inObj EXPORTS NONE
    // Adds a vertex to the graph specified by label import
    public void addVertex(Object inObj, String label)
    {
        // Make new vertex
        DSAGraphVertex inVertex = new DSAGraphVertex(inObj, label);
        if(hasVertex(label)) // Checks if already pre-existing by comparing label
        {
            throw new IllegalArgumentException("Vertex within graph already. \n");
        }
        // Not a duplicate, therefore can add.
        vertices.insertLast(inVertex);
    }

   /*  // if IMPORT is an Object
    public void addVertex(Object inObj)
    {
        addVertex(new DSAGraphVertex(inObj));
    } */



    // MUTATOR addEdge IMPORTS label1, label2, inType EXPORTS NONE
    // Adds an edge between two vertices in the graph specified by label import
    public void addEdge(String l1, String l2, int inType) 
    {
        DSAGraphVertex v1 = getVertex(l1);
        DSAGraphVertex v2 = getVertex(l2);
        DSAGraphEdge edge;

        if(isAdjacent(l1,l2))
        {
            throw new IllegalArgumentException("Edge already within graph");
        }
        // Not a duplicate, therefore can add. Calls DSAGraphEdge constructor to make an edge
        edge = new DSAGraphEdge(v1, v2, inType);
        edges.insertLast(edge);
    }




    //ACCESSOR hasVertex IMPORTS label EXPORTS boolean
    // Check if vertex is present in vertices (String import)
    public boolean hasVertex(String inLabel)
    {
        boolean foundV = false;

        for(Object o : vertices)
        {
            if(((DSAGraphVertex)o).getLabel().equals(inLabel))
            {
                foundV = true;
            }
        }
        return foundV;
    }

    //ACCESSOR hasVertex IMPORTS inVertex EXPORTS boolean
    // Check if vertex is present in vertices (DSAGraphVertex import)
    public boolean hasVertex(DSAGraphVertex inVertex)
    {
        boolean foundV = false;

        for(Object o : vertices)
        {
            if(((DSAGraphVertex)o).equals(inVertex))
            {
                foundV = true;
            }
        }

        return foundV;
    }

    //ACCESSOR getVertexCount IMPORTS NONE EXPORTS int
    // Returns the number of vertices present within the graph
    public int getVertexCount()
    {
        int count = 0;

        for(Object o : vertices)
        {
            count++;
        }
        return count;
    }

    // Returns the number of edges present within the graph
    public int getEdgeCount() 
    {
        int count = 0;

        for(Object o : edges)
        {
            count++;
        }
        return count;
    }



    //ACCESSOR getVertex IMPORTS label EXPORTS vertex 
    // Returns a specific vertex to wherever the method is called.
    public DSAGraphVertex getVertex(String inLabel)
    {
        DSAGraphVertex vertexExport = null;
        
        if(!hasVertex(inLabel))
        {
            throw new NoSuchElementException("Vertex " + inLabel + " not found! \n");
        }

        for(Object o : vertices)
        {
            if(((DSAGraphVertex)o).getLabel().equals(inLabel))
            {
                vertexExport = (DSAGraphVertex)o;
            }
        }
        
        return vertexExport;
    }


    //ACCESSOR getAdjacent IMPORTS label EXPORTS adjList 
    // Retrieves a linked list of vertices adjacent to a specific vertex and returns it.
    public DSALinkedList getAdjacent(String inLabel)
    {
        DSALinkedList adjList = new DSALinkedList();
        if(hasVertex(inLabel))
        {
            Iterator iter = edges.iterator();
            while(iter.hasNext())
            {
                DSAGraphEdge edge = (DSAGraphEdge)iter.next();
                // If there's an adj edge (in either direction, add to LL)
                if(edge.getFrom().getLabel().equals(inLabel))
                {
                    adjList.insertLast(edge.getTo());
                }
                if(edge.getTo().getLabel().equals(inLabel))
                {
                    adjList.insertLast(edge.getFrom());
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Vertex not found.");
        }
        
        return adjList; // Retrieves a list of adjacent verticies and returns it. 
    }



    //ACCESSOR isAdjacent IMPORTS label1, label2 EXPORTS boolean
    // Returns a boolean condition on whether two verticies (from given labels) are adjacent.
    public boolean isAdjacent(String label1, String label2)
    {
        String lblFrom,lblTo;
        boolean adjacent = false;
        Iterator iter = edges.iterator();
        while(iter.hasNext() && adjacent == false)
        {
            DSAGraphEdge edge = (DSAGraphEdge)iter.next();
            lblFrom = edge.getFrom().getLabel();
            lblTo = edge.getTo().getLabel();
            if((lblFrom.equals(label1) && lblTo.equals(label2)) || (lblFrom.equals(label2) && lblTo.equals(label1)))
            {
                adjacent = true;
            }
        }
        return adjacent; 
    }

    
    

    //ACCESSOR displayAsList IMPORTS NONE EXPORTS NONE
    // Displays graph as an adjacency list
    public void displayAsList() 
    {
        String label, adjacent, export;
        export = "";
        Iterator iter = vertices.iterator();
        
        while(iter.hasNext())
        {
            DSAGraphVertex V = (DSAGraphVertex)iter.next();
            DSALinkedList adjList = getAdjacent(V.getLabel());
            label = V.getLabel();

            adjacent = "";
            Iterator iterList = adjList.iterator();
            while(iterList.hasNext())
            {
                DSAGraphVertex adj = (DSAGraphVertex)iterList.next();
                adjacent = adjacent + " " + adj.getLabel();
                if(iterList.hasNext())
                {
                    adjacent = adjacent + ", ";
                }
            }
            export = export + "\n" + label + " | " + adjacent;
        }
        System.out.println(export);
    }
    

    //ACCESSOR getValue IMPORT inLabel EXPORTS Object
    // Retrives a value (Object) contained within a vertex.
    public Object getValue(String inLabel)
    {
        return getVertex(inLabel).getValue();
    }
    
    
    public int getEdgeTypeCount(int inType)
    {
        int count = 0;
        Iterator iter = edges.iterator();
        while(iter.hasNext())
        {
            DSAGraphEdge edge = (DSAGraphEdge)iter.next();
            if(inType == edge.getType())
            {
                count++;
            }
        }
        return count;
    }

    // Removes all edges in a graph of a specific type (specified in import)
    public void removeEdgeType(int inType)
    {
        String v1,v2;
        Iterator iter = edges.iterator();
        while(iter.hasNext())
        {
            DSAGraphEdge edge = (DSAGraphEdge)iter.next();
            if(inType == edge.getType())
            {
                v1 = edge.getFrom().getLabel();
                v2 = edge.getTo().getLabel();
                removeEdge(v1, v2);
            }
        }
    }


    
    
    // Removes a vertex within the graph specified by label import
    public void removeVertex(String inLabel)
    {
        if(!hasVertex(inLabel))
        {
            throw new IllegalArgumentException("Vertex not present in graph. \n");
        }
        else
        {
            Iterator iter = edges.iterator();
            while(iter.hasNext())
            {
                DSAGraphEdge edge = (DSAGraphEdge)iter.next();
                if(edge.getFrom().getLabel().equals(inLabel))
                {
                    edges.removeNode(edge);
                }
                if(edge.getTo().getLabel().equals(inLabel))
                {
                    edges.removeNode(edge);
                }
            }
        }
        vertices.removeNode(getVertex(inLabel));
    }


    // Removes an edge within the graph specified by label import
    public void removeEdge(String v1, String v2)
    {

        String lblFrom,lblTo;
        boolean foundE = false;
        if(!isAdjacent(v1, v2))
        {
            throw new IllegalArgumentException("Vertices are not adjacent");
        }
        else
        {
            Iterator iter = edges.iterator();
            while(iter.hasNext() && !foundE)
            {
                DSAGraphEdge edge = (DSAGraphEdge)iter.next();
                lblFrom = edge.getFrom().getLabel();
                lblTo = edge.getTo().getLabel();
                /* System.out.println("lblFrom: " + lblFrom);
                System.out.println("lblTo: " + lblTo); */

                if((lblFrom.equals(v1) && lblTo.equals(v2)) || (lblFrom.equals(v2) && lblTo.equals(v1)))
                {
                    foundE = true;
                    edges.removeNode(edge);
                    System.out.println("Edge removed.");
                }
            }
        }
    }

    
    // Returns an Iterator object for the graph's vertices
    public Iterator iterator()
    {
        return vertices.iterator();
    }

    /*  Exports graph state to two linked lists; 
        one containing all verticies, the other containing all edges*/

    public void export(DSALinkedList exportVertices, DSALinkedList exportAdjacency)
    {
        for(Object o : vertices)
        {
            exportVertices.insertLast(((DSAGraphVertex)o).getValue());
        }

        for(Object o2 : edges)
        {
            exportAdjacency.insertLast(((DSAGraphEdge)o2).getLabel());
        }
    }
        
    
    // Allows us to use clearVisited in DSAGraph.java - sets visited to false.
    public void clearVisited()
    {
        for(Object o : vertices)
        {
            // Mark all verticies as not visited
            ((DSAGraphVertex)o).clearVisited();
        }
    }


    // ====================== Traversals (commented out) =======================

    /*
        

    public String depthFirstSearch()
    {
        // Set all verticies to unvisited using clearVisited in the Graph class (which calls the clearVisited in DSAGraphVertex inner class)
        clearVisited();
        return DFSRec((DSAGraphVertex)vertices.peekFirst());
    }

    private String DFSRec(DSAGraphVertex inVertex)
    {
        String outputStr = " ";

        DSAGraphVertex subVertex; // Current vertex adjacent to imported vertex
        
        inVertex.setVisited(); // Iterate thru adj vertices

        for(Object o : inVertex.getAdjacent())
        {
            // Find an adjacent vertex that is not visited
            subVertex = (DSAGraphVertex)o;
            if(!subVertex.getVisited())
            {
                outputStr += ("(" + inVertex.toString() + ", " + 
                    subVertex.toString() + ")  " + 
                    DFSRec(subVertex));
            }
        }

        return outputStr;
    }   


    public String breadthFirstSearch()
    {
        DSAQueue queue = new DSAQueue(); // Queue used for BFS
        clearVisited(); // Sets all existing vertices to unvisited. 

        queue.enqueue(vertices.peekFirst()); // Enqueue's the first vertex.
        ((DSAGraphVertex)vertices.peekFirst()).setVisited(); // Sets the first vertex to visited. 

        return BFSRec(queue); // Calls BFSRec which does all the traversal processing.
    }


    private String BFSRec(DSAQueue q)
    {
        String outputStr = " ";

        DSAGraphVertex inVertex = (DSAGraphVertex)q.dequeue(); // Dequeue's the CURRENT vertex
        DSAGraphVertex subVertex;
        
        for(Object o : inVertex.getAdjacent())
        {
            subVertex = (DSAGraphVertex)o;

            if(!subVertex.getVisited())
            {
                q.enqueue(subVertex);
                subVertex.setVisited();


                outputStr += ("(" + inVertex.toString() + ", " + 
                subVertex.toString() + ")  ");
            }
        }

        if(!q.isEmpty()) // Run until empty
        {
            outputStr += BFSRec(q);
        }

        return outputStr;
    }*/

    // ================== displayAsMatrix (commented out) ==================
    

    /* 
    public void displayAsMatrix()
    {
        int numVert = getVertexCount();
        int[][] matrix;
        int index = 0;
        DSAGraphVertex[] arr;
        arr = new DSAGraphVertex[numVert];
        matrix = new int[numVert][numVert];

        // Fill array 
        for(Object o : vertices)
        {
            arr[index] = (DSAGraphVertex)o;
            index++;
        }

        // Fill matrix
        
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
        System.out.print(" "); // Top left
        for(Object o : vertices)
        {
            System.out.print(" | " + ((DSAGraphVertex)o).getLabel()); // Top rows (e.g. A -> G)
        }

        for(int i = 0; i < numVert; i++)
        {
            System.out.print("\n" + arr[i].getLabel()); // LHS-Axis Col Titles (A-> G)
            for(int j = 0; j < numVert; j++)
            {
                System.out.print(" | " + matrix[i][j]); // | is the column divider/separates the columns 
                                                        // matrix[i][j] is the adjacency matrix value at that point (ith row, jth col) ~ either 1 or 0
            }
        }
        System.out.println(); // Bottom right
    } */
}
