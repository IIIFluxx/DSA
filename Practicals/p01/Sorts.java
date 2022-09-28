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
    } //bubbleSort() ^^^^

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
       
    }// selectionSort() ^^^^

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
    
    }// insertionSort() ^^^^

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts class
