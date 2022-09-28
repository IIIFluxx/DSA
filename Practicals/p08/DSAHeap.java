public class DSAHeap 
{
    /* ------------------ DSAHeapEntry  ------------------ */
    
    private class DSAHeapEntry
    {
        
        /* Classfields */
        private int priority;
        private Object value;

        /* Alternate Constructor */
        public DSAHeapEntry(int inPriority, Object inValue)
        {
            priority = inPriority;
            value = inValue;
        }

        public String toString()
        {
            return (priority + "," + value);
        }
    }

    
    /* ------------------ DSAHeap  ------------------ */

    /* Classfields */
    private DSAHeapEntry[] heap;
    private int count;

    /* Alternate Constructor */
    public DSAHeap(int max)
    {
        heap = new DSAHeapEntry[max];
        count = 0;
    }

    public void add(int priority, Object value)
    {
        heap[count] = new DSAHeapEntry(priority, value);
        trickleUp(count);
        count++;
    }

    public Object remove()
    {
        Object outputObj = heap[0].value;
        count--;

        heap[0] = heap[count];
        heap[count] = null;
        trickleDown(0);
        return outputObj;
    }

    public int getCount()
    {
        return count;
    }

    public DSAHeapEntry[] heapSort(Object[][] heapArray) // Imported array = random. Exported array = sorted.
    {
        DSAHeapEntry temp;
        count = heapArray.length;
        heap = new DSAHeapEntry[count];

        if(heapArray[0][0] instanceof Integer)
        {
            // Import is 2D array of priority ints and values
            for(int ii=0;ii<count;ii++)
            {
                heap[ii] = new DSAHeapEntry((int)heapArray[ii][0], heapArray[ii][1]);
            }
        }
        else
        {
            throw new IllegalArgumentException("Heap sort import must be a 2D array of priority integers and Object values");
        }  

        // Heapify arr
        for(int ii = (count/2)-1; ii>=0;ii--)
        {
            trickleDown(ii);
        }

        // Sorting.

        /* IMPORT array, numItems   EXPORT sortedArray  ASSERTION: imported array will be random, exported will  be the same array sorted  
        heapify <- array, numItems
        for ii = numItems-1 downto 1 //0th item will be sorted
            swap <- array, 0, ii
            trickleDown <- heapArray, 0, (ii) //ii is numItems--  */

        for(int ii = count-1;ii>=1;ii--)
        {
            temp = heap[0];
            heap[0] = heap[ii];
            heap[ii] = temp;
            count = ii;
            trickleDown(0);
        }

        count = heapArray.length;
        return heap;
    }



    /* 
    Assertion: IF cur NOT root AND cur > parent THEN Swap cur with parent, then try again
    
    parentIdx = (curIdx-1)/2
    IF curIdx > 0 THEN
        IF heapArr[curIdx] > heapArr[parentIdx] THEN
            temp = heapArr[parentIdx]
            heapArr[parentIdx] = heapArr[curIdx]
            heapArr[curIdx] = temp
            trickleUp <- heapArray, parentIdx  */

    private void trickleUp(int curIdx)
    {
        int parentIdx = getParentIdx(curIdx);
        DSAHeapEntry temp;
        if((curIdx > 0) && heap[curIdx].priority > heap[parentIdx].priority)
        {
            temp = heap[parentIdx];
            heap[parentIdx] = heap[curIdx];
            heap[curIdx] = temp;

            trickleUp(parentIdx);
        }
    }
    /*  IMPORT heapArray, curIdx, numItems EXPORT heapArray

        IF lChildIdx < numItems //is a left child
            largeIdx = lChildIdx
            IF rChildIdx < numItems //is a right child
                IF heapArr[lChildIdx] < heapArr[rChildIdx]
                    largeIdx = rChildIdx //find largest child -- 
                IF heapArr[largeIdx] > heapArr[curIdx]
                    swap <- heapArr, largeIdx, curIdx
                    trickleDown <- heapArray, largeIdx, numItems
  */
    private void trickleDown(int curIdx)
    {
        int leftIdx = getLeftChildIdx(curIdx);
        int rightIdx = getRightChildIdx(curIdx);
        int largeIdx;
        DSAHeapEntry temp;

        if(leftIdx < count) // i.e. left child is bigger
        {
            largeIdx = leftIdx;
            if(rightIdx < count) // i.e. right child is bigger
            {
                if(heap[leftIdx].priority < heap[rightIdx].priority)
                {
                    largeIdx = rightIdx;
                }
            }   
            if(heap[largeIdx].priority < heap[curIdx].priority)
            {
                temp = heap[largeIdx];
                heap[largeIdx] = heap[curIdx];
                heap[curIdx] = temp;
                // Swaps current index with the larger child.

                trickleDown(largeIdx);
            }
        }
    }

    private int getParentIdx(int curIdx)
    {
        return (curIdx - 1) / 2;
    }

    private int getRightChildIdx(int curIdx)
    {
        return (curIdx * 2) + 2;
    }

    private int getLeftChildIdx(int curIdx)
    {
        return (curIdx * 2) + 1;
    }
    
}