/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 25/05/2020
**  Purpose: Basic test harness to isolate queue methods for testing.
**  Reference: This class was adapted from my submission for DSA Practical 04.
** --------------------------------------------------------------------------
*/
 
import java.util.*;
public class UnitTestDSAQueue 
{
    public static void main( String[] args )
    {
        /* Stress-testing a Queue */
        DSAQueue q = new DSAQueue();
        String output = new String();

        spacer();
        System.out.println("Populating queue...\n");
        //Populating Queue
        for(int ii = 0; ii < 5; ii++)
        {
            q.enqueue(ii);
        }
        spacer();
        // Test peek()/dequeue()
        System.out.println("Testing peek() & dequeue()...\n");


        q.peek();
        if(q.getCount() == 5)
        {
            output += "Peek works as expected";
        }
        else
        {
            output += "Peek does not work as expected";
        }
        output += " (Expected " + 5 + ". Actual: " + q.getCount() + "). ";

        q.dequeue();
        if(q.getCount() == 4)
        {
            output += "Dequeue works as expected";
        }
        else
        {
            output += "Dequeue does not work as expected";
        }
        output += " (Expected: " + 4 + ". Actual: " + q.getCount() + "). ";
        System.out.println(output + "\n");

        

        spacer(); 

        // Empty Test
        System.out.println("Testing empty queue");
        while(!q.isEmpty())
        {
            System.out.println("Dequeuing value " + q.peek());
            q.dequeue();
        }
        System.out.println("Queue empty now. Attempting to remove once more...");
        try
        {
            q.dequeue();
            System.out.println("No exception thrown - unexpected");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Exception thrown - expected");
        }
        System.out.println("isEmpty() boolean: '" + q.isEmpty() + "'. (Expected: true). ");
        System.out.println("Done. \n");
        spacer();
    }


    public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }
}

