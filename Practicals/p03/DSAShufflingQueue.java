import java.util.*;
public class DSAShufflingQueue extends DSAQueue
{
	
	/* Class constants */
	private static final int CAPACITY = 100; 

	/* Private class fields */
	private int front;
	private int rear;

	
	/* Default constructor  - set default size of array/queue and count to 0 (i.e. new queue is empty) */
	public DSAShufflingQueue()
	{
		super();
		front = 0;
		rear = -1;
	}
	
	/* Alternate constructor */
	public DSAShufflingQueue(Object[] queue, int count)  
	{
		super(queue,count);
		front = 0;
		rear = -1;
	}
	
	// Getter - returns value of count
	public int getCount()
	{
		return super.getCount();
	}
	
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
	
	public boolean isFull()
	{
		return super.isFull();
	}							
	
	
	@Override
	public void enqueue(Object value) /* add an item to the back of the queue */
	{
		if (isFull())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
		else
		{
			System.out.println("Inserting " + value + " to the back of the queue \n ");
			rear = (rear + 1) % CAPACITY; /*Make space at the back of the queue */
			queue[rear] = value; /*Insert item at the back of the queue */
			count = count + 1; /* Increase queue size by 1 */
			System.out.println(" " + value + " enqueued.");
	    }
	}

	@Override
	public Object dequeue() /* take away an item from the front of the queue */
	{		
		Object frontVal = peek();
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
		System.out.println("Removing " + queue[front] + " from the front of the queue \n ");
		front = (front+1) % CAPACITY;
		count = count - 1; /* delete item from array by making val = 0 -- not necessary, but can do if I want */
        return frontVal;
    }
	
	@Override
    public Object peek() /*check the front item without altering the queue */
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("Program overflowed \n Program terminated");
		}
			return queue[front];
	}
}
