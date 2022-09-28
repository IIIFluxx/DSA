import java.util.*;

public class DSAStackTestHarness
{
    public static void main(String[] args)
	{
		boolean full, empty;
		try
		{
            DSAStack testS = new DSAStack();
            
            Object[] testArr = new Object[4];
			
			/* push items onto our stack */
			testS.push(3);
			testS.push(5);
			testS.push(7);
			testS.push(9);
			testS.push(4);
			testS.push(6);
			System.out.println("Front element in stack is: " + testS.top());
			// System.out.println("Stack size is : " + testS.getCount());
			testS.pop();
			System.out.println("Front element in stack is: " + testS.top());
			// System.out.println("Stack size is : " + testS.getCount());
		
			System.out.printf("Is our stack empty: %s \n" , testS.isEmpty()); /* Elliot said we can use printf so I was curious C: */
			/* Commence testing out the different methods 
				run methods, use printf statements */ 
			System.out.println("--------------------------------");	
			
		}
		catch(IllegalArgumentException e)
        {
            //Displaying first errors in the tests.
            System.out.println(e.getMessage());
        }
    }
}
