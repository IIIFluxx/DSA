/**
 * DSA Final Assessment Question 3 - HeapTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 *
 **/
 
public class HeapTest
{
	public static void main(String args[])
	{
		DSAHeap testHeap = new DSAHeap();
		DSAHeap emptyHeap = new DSAHeap();


		spacer();
		System.out.println("Testing add() normally. \n");
		for (int i=0; i<10; i++)
		{
			testHeap.add(i, i+100);
			System.out.println("Added "+i);
		}
		spacer();
		System.out.println("Testing add() exception by adding to a full heap. \n");

		try
		{
			testHeap.add(11, 11);
			System.out.println("No exception found - unexpected. \n");
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Exception thrown - expected");
			System.out.println(e.getMessage());
		}

		spacer();

		int temp;
		for (int i=0; i<10; i++)
		{
			temp = (Integer)testHeap.remove();
			System.out.println(temp);
		}

		spacer();
		System.out.println("Testing remove() exception by removing from an empty heap. \n");
		try
		{
			emptyHeap.remove();
			System.out.println("No exception found - unexpected. \n");
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Exception thrown - expected");
			System.out.println(e.getMessage());
		}
		spacer();

	}

	public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
	}
	
	/* COMMENTS
	
	Since the min-heap and max-heap are similar implementations with a difference lying in 
	the priority being flipped around, it doesn't affect the harness. We can insert and remove in any
	order, and the heap will order by priority. 
	
	As for the changes in the heap class itself, the changes lie in how the heap 'prioritises' each value's priority.
	A min heap at the root node has the highest priority with the MINIMUM value, whereas in a max heap it's the MAXIMUM value 
	with the highest priority. Also in a min heap, the value of each node is greater than or equal to the parent node. 
	This is represented by the output of the Heap showing in Ascending order for the Min heap, instead of the descending order 
	that was displayed initially in the max heap (in the original implementation of the code).


	The exception handling for the add() and remove() method is simply checking if the heap is full before adding, because
	you can't add to a full heap (you can't add to an array out of its bounds) unless you resize the heap.
	
	Similarly for removing, we check that it's not empty currently before attempting to remove an Object
	out of the heap and modify the heap. You can't remove if there's nothing to remove. 

*/
}