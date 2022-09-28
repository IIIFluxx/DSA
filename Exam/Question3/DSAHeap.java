/**
 * DSA Final Assessment Question 3 - HeapTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 *
 **/
 
public class DSAHeap
{

	public class DSAHeapEntry
	{
		private int priority;
		private Object value;

		public DSAHeapEntry(int priority, Object value)
		{
			this.priority = priority;
			this.value = value;
		}

		public int getPriority()
		{
			return priority;
		}

		public Object getValue()
		{
			return value;
		}

	}

	private DSAHeapEntry[] heap;
	private int count;
	private int MAXSIZE = 10;
	
	
	public DSAHeap()
	{
		heap = new DSAHeapEntry[MAXSIZE];
		count = 0;	
	}

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
		if(count == MAXSIZE)
		{
			full = true;
		}
		return full;
	}


	public void add(int priority, Object value)
	{
		// Change: Checking if Heap is full
		if(isFull())
		{
			throw new IllegalArgumentException("Heap is full. Cannot add anything else");
		}
		DSAHeapEntry entry = new DSAHeapEntry(priority, value);
		heap[count] = entry;
		count++;
		trickleUp(count-1);
	}


	public Object remove() 
	{
		if(isEmpty()) // Change: Checking if Heap is empty
		{
			throw new IllegalArgumentException("Heap is empty. Cannot remove anything else");
		}
		Object retValue;
		DSAHeapEntry entry = heap[0];
		retValue = entry.getValue();
		heap[0] = heap[count-1];
		heap[count-1] = null;
		count--;
		trickleDown(0);
		return retValue;
	}


	private void trickleDown(int index)
	{
		int leftIdx = index * 2 + 1;
	   	int rightIdx = leftIdx + 1;
	   	int largeIdx;
	   	DSAHeapEntry temp;

	   	if(leftIdx < count)
		{
			largeIdx = leftIdx;			
			if (rightIdx < count)
			{
				// Flipped < sign to >, to change the way priority works. 
		   		if (heap[leftIdx].getPriority() > heap[rightIdx].getPriority())
				{
					largeIdx = rightIdx;
				}
			}
			// Flipped > sign to <, to change the way priority works. 
			if (heap[largeIdx].getPriority() < heap[index].getPriority())
			{
            	temp = heap[largeIdx];
              	heap[largeIdx] = heap[index];
              	heap[index] = temp;
				trickleDown(largeIdx);
			}
	   	}
	}
	
	private void trickleUp(int index)
	{
		int parentIndex;
		DSAHeapEntry temp;

		parentIndex = (index-1)/2;

		if (index > 0 )
		{
			// Flipped > sign to <, to change the way priority works. 
			if (heap[index].getPriority() < heap[parentIndex].getPriority())
			{
				temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
				trickleUp(parentIndex);
			}
		}
		
	}


}