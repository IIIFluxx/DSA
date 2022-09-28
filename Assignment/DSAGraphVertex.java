/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 13/05/2020
**  Purpose: Implementation of graph vertex class as part of a graph ADT.
**  Reference: This class was adapted from my submission for DSA Practical 06.
** --------------------------------------------------------------------------
*/

public class DSAGraphVertex 
{
    /* -- Private classfields -- */
    private String label; // Key
    private Object value; // Value associated with the vertex
    private boolean visited; 

    public DSAGraphVertex(Object inValue, String inLabel)
    {
        /* label = inValue.toString(); */
        label = inLabel;
        value = inValue;
        visited = false;
    }

    // Getters
    public String getLabel()
    {
        return label;
    }


    public Object getValue()
    {
        return value;
    }

    public boolean getVisited()
    {
        return visited; 
    }


    public boolean equals(DSAGraphVertex inVertex)
    {
        return (value.equals(inVertex.value));
    }
    


    //MUTATOR setVisited IMPORTS NONE EXPORTS NONE

    public void setVisited()
    {
        visited = true;
    } 

    //MUTATOR clearVisited IMPORTS NONE EXPORTS NONE
    public void clearVisited()
    {
        visited = false;
    } 

    public String toString()
    {
        String str;
        str = value.toString();
        return str;
    }


}
    