import java.util.*;
public abstract class DSAQueue
{
	
	/* Class constants */
	private static final int CAPACITY = 100; 
	private static final int maxCapacity = 150;
	
	/* Private class fields */
	protected Object[] queue;
	protected int count;
	
	
	
	/* Default constructor  - set default size of array/queue and count to 0 (i.e. new queue is empty) */
	public DSAQueue()
	{
		queue = new Object[CAPACITY];
		count = 0;
	}
	
	/* Alternate constructor */
	public DSAQueue(Object[] queue, int count)
	{
		queue = new Object[maxCapacity]; /* User defined size -- if using in any methods, make it a classfield */
		count = 0;
	}
	
	/* Getter - returns value of count */
	public int getCount()
	{
		return count;
	}
	
	public Object[] getQueue()
	{
		return queue;
	}	
	
	public boolean isEmpty()
	{
		boolean empty = false;
		if(count == 0)
		{
			empty = true;		/* Better to just call getCount()? Not sure which would use more memory/be better to do */
		}
		return empty;
	}
	
	public boolean isFull()
	{
		boolean full = false;
		if(count == CAPACITY) // or count = queue.length?
		{
			full = true;
		}
		return full;
	}
	

	
	/* Abstract methods */
	public abstract void enqueue(Object value); /* add an item to the back of the queue */

	public abstract Object dequeue(); /* take away an item from the front of the queue */
	
    public abstract Object peek(); /*check the front item without altering the queue ~ getter */
}
