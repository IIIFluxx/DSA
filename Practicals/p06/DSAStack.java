import java.util.*;
public class DSAStack implements Iterable
{

	
	/* Private class fields */
	private DSALinkedList list;
	
	/* Default constructor  - set default size of array/stack and count to 0 (i.e. new stack is empty) */
	public DSAStack()
	{
		list = new DSALinkedList();
	}
	
	public boolean isEmpty()
	{
		Object value;
		boolean empty = false;
		try 
		{
			value = list.peekFirst();
			empty =  false;
		} catch (IllegalArgumentException e)
		{
			empty = true;
		}
		return empty;
	}
		
	public void push (Object value) /* add an item to the back of the list */
	{
		list.insertLast(value);
	}

	public Object pop() /* Remove the top-most item from the stack*/
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
		return list.removeFirst();
	}
	
	
	public Object top()  /*check the front item without altering the queue */ 
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
			return list.peekLast();
	}
	
	/* public Object peekN(int n) // return item at index n
	{ 
		return stack[n]; 
	}

	
	 public void displayStack()
	{
		System.out.println("Stack (bottom-->top): ");
		for (int ii = 0;ii<count;ii++)
		{
			System.out.println(peekN(ii));
			System.out.println(' ');
		}
		System.out.println("");
	}*/

	public Iterator iterator() 
	{
		return list.iterator();
	}

}
