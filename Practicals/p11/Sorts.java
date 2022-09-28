/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
        int pass = 0;
        int temp = 0;
        boolean sorted = false;
        int n = A.length;
        do
        {
            sorted = true;
            for (int i=0;i<=(n-1-pass)-1;i++)
            {
                if(A[i] > A[i+1])
                {
                    temp = A[i];
                    A[i] = A[i+1];
                    sorted = false;
                }
            }
            pass = pass + 1;
        } while (!sorted);
    } 

    
    // selection sort
    public static void selectionSort(int[] A)
    {
        int y = A.length;
        int minIdx = 0;
        int temp = 0;
        for (int nn = 0;nn<y-1;nn++)
        {
            minIdx = nn;
            for (int jj = nn+1;jj<=y-1;jj++)
            {
                if(A[jj] < A[minIdx])
                {
                    minIdx = jj;
                }
            }
            temp = A[minIdx];
            A[minIdx] = A[nn];
            A[nn] = temp;
        
        }
       
    }

    // insertion sort
    public static void insertionSort(int[] A)
    {
        int l = A.length;
        int temp = 0;
        int ii = 0;
        for (int nn = 1; nn<=l-1;nn++)
        {
            ii = nn;
            temp = A[ii];
            while((ii>0) && (A[ii-1] > temp))
            {
                A[ii] = A[ii-1];
                ii = ii - 1;
            }
            A[ii] = temp;
        }
        
    }

    // ---------------------------------------------

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
        mergeSortRecurse(A, 0, A.length - 1); // Array A, leftIdx 0, rightIdx length - 1
    }

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        int midIdx;
        if(leftIdx < rightIdx) // Splits the array into sub-arrays until we have a single element
        {
            midIdx = (leftIdx + rightIdx) / 2;
            mergeSortRecurse(A,leftIdx,midIdx); // Left part of the array until the middle
            mergeSortRecurse(A, midIdx+1, rightIdx); // From the middle to the right side of the array
            merge(A, leftIdx, midIdx, rightIdx); // Merge left & right sub-arrays
        }
    }

    //merge()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        int[] tempArr = new int[rightIdx - leftIdx + 1]; // Takes in the smallest value in each of the sub-arrays (sorted order)
        int i = leftIdx; // Index for the 'front' of the left-subarray.
        int j = midIdx + 1; // Index for the 'front' of the right-subarray
        int k = 0; // Keeps track of where we are in the temp array. 

        while(i<=midIdx && j<=rightIdx) // Checks whether we have completed going through one or the other of the subarrays
        { // while(left is finished && right is finished)
            if(A[i] <= A[j]) // Using <= for a stable sort.
            {
                tempArr[k] = A[i]; // Take from the left subarrray.
                i++;
            }
            else
            {
                tempArr[k] = A[j]; // Take from the right subarray
                j++;
            }
            k++;
        }
        for(int ii=i;i<=midIdx;i++)// Flush remainder from left sub-array (Flush out what's left in the subarray) 
        {
            tempArr[k] = A[i]; // NOTE: Goes to midIdx inclusively
            k+=1;
        }
        for(int jj=j;j<=rightIdx;j++) // Flush remainder from right sub-array (Flush out what's left in the subarray)
        {
            tempArr[k]=A[j]; // NOTE: Goes to rightIdx inclusively
            k+=1;
        }
        for(int kk=leftIdx;kk<=rightIdx;kk++) //  Copies the now-sorted tempArr back to the actual array
        {
            A[kk] = tempArr[kk-leftIdx]; //  Uses kk - leftIdx to align tempArr indexing to zero
        }
    }

    // ---------------------------------------------

    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, A.length - 1);	
    }

    //quickSortRecurse()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        int pivotIdx, newPivotIdx;
        if(rightIdx > leftIdx) //  Check that the array is > one element in size
        {
            pivotIdx = leftIdx; // Pivot selection strategy: left element (not recommended practically)
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);
            quickSortRecurse(A, leftIdx, newPivotIdx-1); // Sort left partition
            quickSortRecurse(A, newPivotIdx+1, rightIdx); // Sort right partition
        }
        //  Base case: array is 1 element or smaller in size – already sorted (doesn't enter the if statement)
    }

    // ---------------------------------------------

    public static void QuickSortMedian3(int[] A)
    {
        quickSortMedian3Recurse(A, 0, A.length - 1);
    }

    //quickSortMedian3Recurse()
    private static void quickSortMedian3Recurse(int[] A, int leftIdx, int rightIdx)
    {
        int pivotIdx, newPivotIdx;
        if(rightIdx > leftIdx) //  Check that the array is > one element in size
        {
            pivotIdx = getMedian(A, leftIdx,rightIdx); // Pivot selection strategy: median element (recommended practically)
            newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);
            quickSortRecurse(A, leftIdx, newPivotIdx-1); // Sort left partition
            quickSortRecurse(A, newPivotIdx+1, rightIdx); // Sort right partition
        }
        //  Base case: array is 1 element or smaller in size – already sorted (doesn't enter the if statement)
    }

    private static int getMedian(int[] A, int left,int right)
    {
        int centre = (left+right)/2;
         
        if(A[left] > A[centre])
        {
            //swap leftIdx & centre
            swap(A,left,centre);
        }
         
        if(A[left] > A[right])
        {
            // swap leftIdx & rightIdx
            swap(A,left,right);
        }
            
         
        if(A[centre] > A[right])
        {
            // swap centre & rightIdx
            swap(A,centre,right);
        }

        // Otherwise swap centre & rightIdx -- put pivot on the right
        swap(A,centre,right); // -1 from right?
        return A[right]; // returns median value. -1 from right?
    }

    public static void swap(int[] A, int left, int right)
    {
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }

    // ---------------------------------------------
    public static void threeWayQuickSort(int [] A) /* Using algorithm from https://www.tutorialspoint.com/3-way-quicksort-dutch-national-flag as a reference for the following function. */
    {
        threeWayQuickSortRecurse(A, 0, A.length - 1);
    }

    public static void threeWayQuickSortRecurse(int [] A, int leftIdx, int rightIdx)
    {
        int ii = 0, jj=0;

        if(leftIdx <= rightIdx) // 1 or 0 elements
        {
            threeWayPartition(A,leftIdx,rightIdx,ii,jj);
            quickSortRecurse(A, leftIdx, ii);
            quickSortRecurse(A, jj, rightIdx);
        }
    }


    // ---------------------------------------------
    // Partitioning 


    // 3-way partitioning

    public static void threeWayPartition(int[] A, int leftIdx, int rightIdx, int ii, int jj)
    {
        if(rightIdx - leftIdx <= 1)
        {
            if(A[rightIdx] < A[leftIdx])
            {
                swap(A, rightIdx, leftIdx);
            }
            ii = leftIdx;
            jj = rightIdx;
        }
        else
        {
            int mid = leftIdx;
            int pivotIdx = A[rightIdx];
            while(mid <= rightIdx)
            {
                if(A[mid] < pivotIdx)
                {
                    leftIdx++;
                    mid++;
                    swap(A,leftIdx, mid);
                    
                }
                else if (A[mid]==pivotIdx)
                {
                    mid++;
                }
                else if (A[mid]>pivotIdx)
                {
                    swap(A,mid,rightIdx);
                    rightIdx--;
                }
            }
            ii = leftIdx-1;
            jj = mid;
        }
    }


    // Regular quickSort Partitioning
    //doPartitioning
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
        int pivotVal, currIdx, temp, newPivotIdx = 0;

        // Swap the pivotVal with the right-most element
        pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx]; 
        A[rightIdx] = pivotVal;
        //--------------------

        // Find all values that are smaller than the pivot
        // and transfer them to the left-hand-side of the array
        currIdx = leftIdx;

        for(int ii = leftIdx; ii<= rightIdx-1;ii++)
        {
            if(A[ii] < pivotVal) // Find the next value that should go on the left
            {
                // Swap i'th and currIdx element (LHS)
                temp = A[ii];
                A[ii] = A[currIdx];
                A[currIdx] = temp;
                currIdx++;
            }
        }
        newPivotIdx = currIdx;
        A[rightIdx] = A[newPivotIdx]; // Put the pivot into its rightful place (the value at  
        A[newPivotIdx] = pivotVal; // [newPivotIdx] is >= pivotVal, so it can be put to the end)
        
        return newPivotIdx;
    }


// ---------------------------------------------
    // shell sort

    public static void shellSort(int[] A) // Using algorithm from https://www.geeksforgeeks.org/shellsort/ as a reference for the following function.
    {
        int n = A.length; // Max length of A

        int temp; // Temporary variable to store A[i]
        int kk; // Loop index reference

        // Start with a big gap, then reduce the gap 
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do gapped insertion sort for this 'h value', keep adding elements till entire array is gap sorted. 
            for (int ii = gap; ii < n; ii += 1)
            {
                // Shift elements that were gap sorted earlier until a[ii] is found.
                temp = A[ii];
                kk = ii;

                for(int j = ii; j >= gap && A[j - gap] > temp; j -= gap)
                {
                    A[j] = A[j - gap];
                    kk = j - gap;
                }

                A[kk] = temp;
            }
        }
    }


    /*  – Given array input[]
        – Create Count[] and Result[]
        – Fill Count[] with the count of each key in input[]
        – Update Count[] to store the sum of the previous
        counts
        • This will give us the position for each group of keys  */
    // counting sort
    public static void countingSort(int[] A) //  Using algorithm from https://www.geeksforgeeks.org/counting-sort/ & lecture slides as a reference for the following function.
    {
        int[] count = new int[A.length + 1];
        int[] result = new int[A.length + 1];


        // 1st pass for filling up count[]
        for(int i = 0; i < A.length; i++)
            count[A[i]]++;

        // 2nd pass that modifies count[]
        for(int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        // Create result[], going backwards (to make it stable)

        for(int i = A.length - 1; i >= 0; i--)
            result[--count[A[i]]] = A[i];

            
        // Copy the output array - result[] into A[]
        for( int i = 0; i < A.length; i++ )
        {
            A[i] = result[i]; // A[] is now in sorted order.
        }
    }

    public static void radixSort(int[] A) // Using algorithm from https://www.growingwiththeweb.com/sorting/radix-sort-lsd/ & lecture slides as a reference for the following function.
    {
        int exponent, radix;
        int minVal, maxVal;

        // Find min and max values
        minVal = A[0];
        maxVal = A[0];

        // --------------------
        for(int ii = 1; ii < A.length; ii++ )
        {
            if(A[ii] < minVal)
                minVal = A[ii];
            else if(A[ii] > maxVal)
                maxVal = A[ii];
        }
        // Counting sort on each exponent/digit (starting at the least significant digit)
        radix = 10;
            // Numbers are base 10
        exponent = 1;
            // Start at digit one because it's LSD
        while((maxVal - minVal) / exponent >= 1 )
        {
            countingSort(A);
            exponent *= radix;
        }
    }

}//end Sorts class
