import java.util.*;

public class DSAQueue<T> extends AbstractQueue<T>
{
	
	/* Private class fields */
	private LinkedList<T> list;


	/* Default constructor  - set default size of array/queue and count to 0 (i.e. new queue is empty) */
	public DSAQueue()
	{
		list = new LinkedList<T>();
	}

	public int size()
    {
        return list.size();
    }

	// The new enqueue()
    public boolean offer(T inValue)
    {
        list.addLast(inValue);
        return true;
    }

	public String toString()
    {
        return list.toString();
    } 

	// The new dequeue()
	public T poll()
    {
        return (T)list.pollFirst();
    }


    public T peek()
    {
        return (T)list.peekFirst();
    }

    public Iterator<T> iterator()
    {
        return list.descendingIterator();
    }

}
