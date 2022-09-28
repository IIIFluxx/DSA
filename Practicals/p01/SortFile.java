import java.util.*;
import java.io.*;

public class SortFile
{
    public static void main(String[] args)
    {
        String[] strArr = new String[7000]; //Array for file
        int[] array = new int[7000]; //Array of numbers from data
        int[] sortedArr; //Array to store sorted data

        String filename = "RandomNames7000.csv";
        
        readFile(strArr, filename);

        for(int ii = 0; ii < array.length; ii++)
        {
            // Splits by comma into <num><name>
            array[ii] = Integer.parseInt( strArr[ii].split(",")[0]);
            // Takes integer value from [0] 
            // Array now contains values from file.
        }

        //Bubble sort
        sortedArr = new int[7000]; // Array to store sorted data
        sortedArr = bubbleSort(array.clone()); //Sorts unsorted data into array
        saveFile(sortedArr, "bubbleSort.txt"); //Saves sorted data to file

        //Selection sort
        sortedArr = new int[7000]; // Store
        sortedArr = selectionSort(array.clone()); // Sort
        saveFile(sortedArr, "selectionSort.txt"); // Save

        //Insertion sort
        sortedArr = new int[7000]; //Store
        sortedArr = insertionSort(array.clone()); //Sort
        saveFile(sortedArr, "insertionSort.txt"); //Save
    }


    public static int[] bubbleSort(int[] A)
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
                    A[i+1] = temp;
                    sorted = false;
                }
            }
            pass = pass + 1;
        } while (!sorted);
        return A;
    } //bubbleSort() ^^^^

    // selection sort
    public static int[] selectionSort(int[] A)
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
        return A;
       
    }// selectionSort() ^^^^

    // insertion sort
    public static int[] insertionSort(int[] A)
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
            return A;
        }
        return A;
    }

    // ---------- File IO -------------

    public static void readFile(String[] A, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            do
            {
                A[lineNum] = bufRdr.readLine();
                lineNum++;
            }
            while((lineNum < A.length) && 
                (A[lineNum - 1] != null));

            bufRdr.close();
        }
        catch(IOException e)
        {
            System.out.println( "Error occured when reading file: " + e.getMessage());
        }
    }







    public static void saveFile(int[] A, String filename)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            for(int ii = 0; ii < A.length; ii++)
            {
                pw.println(A[ii]);
            }
            System.out.println(filename + "made. \n");

            pw.close();
        }
        catch(IOException e)
        {
            System.out.println("Error occured when writing file: " + e.getMessage());
        }
    }


}