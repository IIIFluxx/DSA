/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: Implementation of a FILO queue ADT.
**  Reference: This class was adapted from my submission for DSA Practical 04.
** --------------------------------------------------------------------------
*/
 
import java.util.*;
import java.io.*;
public class DSAQueue implements Iterable, Serializable
{
	
	/* Private class fields */
	private DSALinkedList list;


	/* Default constructor - Constructs a new queue */
	// Exports memory adress of a new DSAQueue
	public DSAQueue()
	{
		list = new DSALinkedList();
	}

	// Iterates through the LL and returns the number of elements found within the queue
	public int getCount()
    {
        int count = 0;

        for(Object o : this)
        {
            count++;
        }
        return count;
    }
	
	// Returns boolean on whether queue is empty or not
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
	
	// Adds an item to the back of the queue
	public void enqueue(Object value) 
	{
		list.insertLast(value);
	}

	// Returns the front element and removes it from the queue

	public Object dequeue()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
		return list.removeFirst();
	}

	// Checks the front item without altering the queue ~ like a getter 
	public Object peek() 
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
			return list.peekFirst();
	}

	// Returns an Iterator object for the queue
	public Iterator iterator() 
	{
		return list.iterator();
	}

	// Prints out the contents of a queue
	public void printQueue()
	{
		list.printList();
	}

}
