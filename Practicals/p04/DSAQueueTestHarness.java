import java.util.*;
/* Needs to be a harness for the subclasses, which will test this class */
public class DSAQueueTestHarness
{
    public static void main(String[] args)
	{
		try
		{
			
			/* Stress-testing a Shuffling Queue */
			
            DSAQueue testSQ = new DSAQueue();
            Object[] testArr1 = new Object[4];
			System.out.println("Testing shuffling queue...\n");
			testSQ.enqueue(5);
			testSQ.enqueue(25);
			testSQ.enqueue(75);
			System.out.println("Front element in queue is: " + testSQ.peek());
			// System.out.println("Queue size is : " + testSQ.getCount());
			testSQ.dequeue();
			System.out.println("Front element in queue is: " + testSQ.peek());
			// System.out.println("Queue size is : " + testSQ.getCount());		
			/* Edge cases, surpassing CAPACITY, dequeue/requeue operation order etc. */
			
			
		/*	DSAQueue testCQ = new DSACircularQueue();
            Object[] testArr2 = new Object[4];
			System.out.println("--------------------------------");
			System.out.println("Testing circular queue...\n");
			testCQ.enqueue(5);
			testCQ.enqueue(25);
			testCQ.enqueue(75);
			System.out.println("Front element in queue is: " + testCQ.peek());
			// System.out.println("Queue size is : " + testCQ.getCount());
			testCQ.dequeue();
			System.out.println("Front element in queue is: " + testCQ.peek());
			//System.out.println("Queue size is : " + testCQ.getCount());*/	
		}
		catch(IllegalArgumentException e)
        {
            //Displaying first errors in the tests.
            System.out.println(e.getMessage());
        }
    }
}
