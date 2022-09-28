import java.util.*;
/* Needs to be a harness for the subclasses, which will test this class */
public class DSAQueueTestHarness
{
    public static void main(String[] args)
	{
		String export = "";		
		/* Stress-testing a Queue */
		
		DSAQueue<Integer> q = new DSAQueue<Integer>();

		System.out.println("Testing shuffling queue...\n");
		// Fill
		for(int ii = 0; ii < 5; ii++)
		{
			q.add(ii);
		}
		
		// Iterate through
		System.out.println("\nTesting iterator()");
		System.out.print("Elements: ");
		for(int i : q)
		{
			System.out.printf(i + "\t");
		}
		System.out.println("\nExpected: 4\t3\t2\t1\t0\n");


		// Peek/Remove
		System.out.println("Testing peek and remove");
		q.peek();
		if(q.size() == 5)
		{
			export += "Peek works";
		}
		else
		{
			export += "Peek does not work";
		}
		export += " (Expected 5. Actual: " + q.size() + "). \n";

		q.remove();
        if( q.size() == 4 )
        {
            export += "Remove works";
        }
        else
        {
            export += "Remove doesn't work";
        }
        export += " (Expected 4. Actual " + q.size() + "). \n";
		System.out.println(export + "\n" );
		
		// Testing isEmpty
		System.out.println("Testing isEmpty()");
		while(!q.isEmpty())
        {
            System.out.println( "Removing value " + q.size());
            q.remove();
        }
        System.out.println("It should be empty now. \n");

        System.out.println("Attempting to remove another:");
        try
        {
            q.remove();
            System.out.println( "No exception thrown - unexpected");
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Exception thrown - expected. \n");
        }

		System.out.println("Done! \n");

    }
}
