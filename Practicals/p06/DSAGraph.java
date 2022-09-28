import java.util.*;
public class DSAGraph 
{
    /* ------------------ DSAGraphVertex ------------------ */
    private class DSAGraphVertex
    {
        /* -- Private classfields -- */
        private String label;
        private DSALinkedList links;
        private boolean visited;

        private DSAGraphVertex(String inLabel)
        {
            label = inLabel;
            links = new DSALinkedList();
            visited = false;
        }

        //ACCESSOR getLabel IMPORTS NONE EXPORTS label
        private String getLabel()
        {
            return label;
        }
        //ACCESSOR getAdjacent IMPORTS NONE EXPORTS vertexList
        private DSALinkedList getAdjacent()
        {
            return links;
        }
        //ACCESSOR getVisited IMPORTS NONE EXPORTS Boolean
        private boolean getVisited()
        {
            return visited;
        }

        //MUTATOR addEdge IMPORTS vertex EXPORTS NONE
        private void addEdge(DSAGraphVertex inVertex)
        {
            links.insertLast(inVertex);
        } 
        //MUTATOR setVisited IMPORTS NONE EXPORTS NONE \\ for searching (later) \\

        private void setVisited()
        {
            visited = true;
        }
        //MUTATOR clearVisited IMPORTS NONE EXPORTS NONE
        private void clearVisited()
        {
            visited = false;
        }
        //ACCESSOR toString IMPORTS NONE EXPORTS string
        public String toString()
        {
            return label;
        }
        // Equals method or compareTo?

    }

    /* ------------------ DSAGraph ------------------ */

    /* -- Private classfields -- */
    private DSALinkedList vertices;

    public DSAGraph()
    {
        vertices = new DSALinkedList();
    }


    // MUTATOR addVertex IMPORTS label [, value] EXPORTS NONE
    public void addVertex(String inLabel)
    {
        if(hasVertex(inLabel))
        {
            throw new IllegalArgumentException("Vertex within graph already. \n");
        }
        vertices.insertLast(new DSAGraphVertex(inLabel));
    }

    // MUTATOR addEdge IMPORTS label1, label2 EXPORTS NONE
    public void addEdge(String label1, String label2)
    {
        DSAGraphVertex v1,v2;
        v1 = getVertex(label1);
        v2 = getVertex(label2);
        try
        {
            v1.addEdge(v2);
            v2.addEdge(v1);
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Edge already within graph");
        }
    }

    //ACCESSOR hasVertex IMPORTS label EXPORTS boolean
    public boolean hasVertex(String inLabel)
    {
        boolean foundV = false;
        DSAGraphVertex curr = null;
        Iterator iter = vertices.iterator();

        /* Unsure how to check every vertex <<<<<<<>>>>>>> */
        while(iter.hasNext())
        {
            curr = (DSAGraphVertex)(iter.next());
            if(curr.getLabel().equals(inLabel))
            {
                foundV = true;
            }
        }
        return foundV;
        // Check if vertex is present in vertices.
    }

    //ACCESSOR getVertexCount IMPORTS NONE EXPORTS int
    public int getVertexCount()
    {
        int count = 0;
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            iter.next();
            count++;
        }   
        return count;
    }

    //ACCESSOR getEdgeCount IMPORTS NONE EXPORTS int
    /*public int getEdgeCount()
    {
        int edgeCount = 0; // Total number of edges - to be returned by getEdgeCount 
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
                DSAGraphVertex vertex = (DSAGraphVertex)iter.next();
                edgeCount = vertex.getEdgeCount()+edgeCount;
        }
        return edgeCount;
    }*/

    public int getEdgeCount()
    {
        int edgeCount = 0;
        for(Object obj : vertices)
        {
            for(Object obj2 : ((DSAGraphVertex)obj).getAdjacent())
            {
                // Increment for every element in the adjacency list for
                // every induvidual vertex
                edgeCount++;
            }
        }
        return (edgeCount/2);
    }

    //ACCESSOR getVertex IMPORTS label EXPORTS vertex 
    public DSAGraphVertex getVertex(String inLabel)
    {
        DSAGraphVertex vertexExport = null;
        Iterator iter = vertices.iterator();
        
        if(!hasVertex(inLabel))
        {
            throw new NoSuchElementException("Vertex " + inLabel + " not found! \n");
        }
        else
        {
            while(iter.hasNext())
            {
                DSAGraphVertex vertex = (DSAGraphVertex)(iter.next());
                if(vertex.getLabel().equals(inLabel))
                {
                    vertexExport = vertex; 
                }
            }
        }
        
        return vertexExport;
    }
    
    //ACCESSOR getAdjacent IMPORTS label EXPORTS vertexList -- Implements getAdjacent from Vertex class
    public DSALinkedList getAdjacent(String inLabel)
    {
        return getVertex(inLabel).getAdjacent(); // Retrieves a list of adjacent verticies and returns it. 
    }    
    //ACCESSOR isAdjacent IMPORTS label1, label2 EXPORTS boolean

    public boolean isAdjacent(String label1, String label2)
    {
        String label;
        boolean adjacent = false;
        DSAGraphVertex curr;
        DSALinkedList list = getAdjacent(label1);
        Iterator iter = list.iterator();

        while(iter.hasNext())
        {
            curr = (DSAGraphVertex)(iter.next());
            label = (curr.getLabel());
            if(label.equals(label2))
            {
                adjacent = true;
            }           
        }
        return adjacent; 
    }

    //ACCESSOR isAdjacent IMPORTS vertex1, vertex2 EXPORTS boolean

    public boolean isAdjacent(DSAGraphVertex v1, DSAGraphVertex v2)
    {
        boolean adjacent = false;
        for(Object o : (v1.getAdjacent()))
        {
            if(((DSAGraphVertex)o).equals(v2))
            {
                adjacent = true;
            }
        }
        return adjacent; 
    }



    //ACCESSOR displayAsList IMPORTS NONE EXPORTS NONE

    public void displayAsList() 
    {
        DSAGraphVertex currVertex; 
        DSAGraphVertex vertex2;
        String outputStr;
        Iterator verticesIterator = vertices.iterator();
        Iterator iter2;

        while(verticesIterator.hasNext()) // Iterate through our verticies list
        {
            outputStr = " ";

            currVertex = (DSAGraphVertex)(verticesIterator.next());
            // Add label to outputStr and separate the label name by its elements by '|'
            outputStr = (currVertex.getLabel() + " | ");
            
            //Iterate through adjacency list of current vertex.
            iter2 = currVertex.getAdjacent().iterator();

            while(iter2.hasNext())
            {
                vertex2 = (DSAGraphVertex)(iter2.next());
                outputStr = outputStr + (vertex2.getLabel());
                if(iter2.hasNext())
                {
                   outputStr += ", ";
                }
            }
            System.out.println(outputStr);
        }
    }
    

    //ACCESSOR displayAsMatrix IMPORTS NONE EXPORTS NONE

    public void displayAsMatrix()
    {
        int numVert = getVertexCount();
        int[][] matrix;
        int index = 0;
        DSAGraphVertex[] arr;
        arr = new DSAGraphVertex[numVert];
        matrix = new int[numVert][numVert];

        // Populate array 
        for( Object o : vertices)
        {
            arr[index] = (DSAGraphVertex)o;
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
        System.out.print(" "); // Top left
        for(Object o : vertices) 
        {
            System.out.print(" | " + ((DSAGraphVertex)o).getLabel()); // Top rows (e.g. Graph 1: A -> G)
        }

        for(int i = 0; i < numVert; i++)
        {
            System.out.print("\n" + arr[i].getLabel()); /* LHS-Axis Titles (A-> G) */
            for(int j = 0; j < numVert; j++)
            {
                System.out.print(" | " + matrix[i][j]); // | is the column divider/separates the columns 
                                                        // matrix[i][j] is the adjacency matrix value at that point (ith row, jth col) ~ either 1 or 0
            }
        }
        System.out.println(); // Bottom right
    }

    // ====================== Traversals =======================

        // Allows us to use clearVisited in DSAGraph.java
        private void clearVisited()
        {
            for( Object o : vertices )
            {
                // Mark all verticies as not visited
                ((DSAGraphVertex)o).clearVisited();
            }
        }

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


    }


}
