import java.util.*;
import java.io.*;
public class DSAQueue implements Iterable, Serializable
{
	
	/* Private class fields */
	private DSALinkedList list;


	/* Default constructor  - set default size of array/queue and count to 0 (i.e. new queue is empty) */
	public DSAQueue()
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
	
	public void enqueue(Object value) /* add an item to the back of the list */
	{
		list.insertLast(value);
	}

	/* Conversely, to dequeue() use a combination of peekFirst() and removeFirst() to
	access the first element and remove it. In other words, organise the DSALinkedList
	‘backwards’ so that you can take from index 0 rather than having to first determine
	the size of the list.*/ 	

	public Object dequeue() /* take away an item from the front of the queue */
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
		return list.removeFirst();
	}


	public Object peek() /*check the front item without altering the queue ~ getter */
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
			return list.peekFirst();
	}

	public Iterator iterator() 
	{
		return list.iterator();
	}

	public void printQueue()
	{
		list.printList();
	}

}
