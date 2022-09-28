import java.util.*;
public class DSAStack
{
	/* Class constants */
	public static final int CAPACITY = 100;
	
	/* Private class fields */
	private Object[] stack;
	private int count;
	
	/* Default constructor  - set default size of array/stack and count to 0 (i.e. new stack is empty) */
	public DSAStack()
	{
		stack = new Object[CAPACITY];
		count = 0;
	}
	
	/* Alternate constructor */
	public DSAStack(int maxCapacity)
	{
		stack = new Object[maxCapacity];
		count = 0;
	}
	
	/* Getter - returns value of count */
	public int getCount()
	{
		return count;
	}
	
	public boolean isEmpty()
	{
		boolean empty = false;
		if(count == 0)
		{
			empty = true;
		}
		return empty;
	}
	
	public boolean isFull()
	{
		boolean full = false;
		if(count == stack.length)
		{
			full = true;
		}
		return full;
	}
	
	public void push (Object value)
	{
		if(isFull())
		{
			throw new IllegalArgumentException("Stack is full! ");
		}
		else
		{
			System.out.println("Pushing " + value + " to the stack \n ");
			stack[count] = value;
			count++;
			System.out.println("  " + value + " pushed.");
		}
	}

	public Object pop() /* Remove the top-most item from the stack*/
	{
		Object topVal;
		System.out.println("Popping off element: " + top());
		topVal = top();
		count --;
		return topVal;
	}
	
	
	public Object top()  /*check the front item without altering the queue */ 
	{
		Object topVal;
		if(isEmpty())
		{
			throw new IllegalArgumentException("Stack is empty! ");
		}
		else
		{
		topVal = stack[count-1];
		}				
		return topVal;
	}
	
	 public Object peekN(int n) // return item at index n
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
	}
}
