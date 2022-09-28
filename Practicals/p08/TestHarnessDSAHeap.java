import java.util.*;
import java.io.*;

public class TestHarnessDSAHeap 
{
    public static void main(String[] args)
    {
        DSAHeap heap = new DSAHeap(7100);
        spacer();

        System.out.println("Loading array from RandomNames7000.csv\n");
        fill(heap);
        spacer();
        System.out.println("Testing add(), remove(), getCount()");
        test(heap);
        spacer();
        System.out.println("Testing heapSort()");
        testSort(heap);
        spacer();
        System.out.println("End. \n");
        spacer();
    }

    public static void fill(DSAHeap heap)
    {
        FileIOHeap.loadCSV(heap, "RandomNames7000.csv");
        System.out.println("\t ~~Load Successful~~ \t");
    }

    public static void test(DSAHeap heap) // Tests add(), remove(), getCount().
    {
        System.out.println("Testing add() \n");
        System.out.println("Initial number of heap entries: " + heap.getCount() + " (Expected: 7000)");
        System.out.println("Adding entry - 99999999");
        heap.add(99999999, "Test Entry");
        System.out.println("Number of heap entries post-add: " + heap.getCount() + " (Expected: 7001)");
        subspacer();
        System.out.println("Testing remove() \n");
        System.out.println("Removed entity: " + heap.remove() + " (Expected: 'Test Entry')");
        System.out.println("Number of heap entries post-remove: " + heap.getCount() + " (Expected: 7000)");
    }

    public static void testSort(DSAHeap heap)
    {
        FileInputStream fileStrm;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        Object[][] arr = new Object[7000][2];
        Object[][] arr2 = new Object[15][2];
        Object[] sortedArray;
        String line;
        int idx = 0;
        int temp;
        
        // Usual File IO stuff
        try
        {
            fileStrm = new FileInputStream("RandomNames7000.csv");
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line != null)
            {
                Integer integer = Integer.valueOf(line.split(",")[0]);
                arr[idx][0] = integer;
                arr[idx][1] = line.split(",")[1];
                idx++;
                line = bufRdr.readLine();
            }

            bufRdr.close();
        }
        catch(IOException e)
        {
            System.out.println("IO Exception: Error in file \n");
            e.printStackTrace();
        }
        sortedArray = heap.heapSort(arr);
        // Display the first 5 elements before any further sorting is performed.
        System.out.println("\nFirst 5 elements:" );
        for(int ii = 0; ii < 5; ii++)
        {
            System.out.println(sortedArray[ii].toString());
        }

        // Display the last 5 elements before any further sorting is performed.
        System.out.println("\nLast 5 elements:" );
        for(int ii = 6995; ii < 7000; ii++)
        {
            System.out.println(sortedArray[ii].toString());
        }

        subspacer();
        System.out.println("\nSorting a smaller, randomly generated data set ");

        for(int ii = 0; ii < 15; ii++)
        {
            temp = (int)(Math.random() * 10 + 1 );
            arr2[ii][0] = temp;
            arr2[ii][1] = temp;
        }

        System.out.println("\nBefore sorting:");
        for(int ii = 0; ii < 15; ii++)
        {
            System.out.printf("%02d  ", ii);
            for(int jj = 0; jj < (int)arr2[ii][0]; jj++)
            {
                System.out.print("-");
            }
            System.out.println();
        }

        sortedArray = heap.heapSort(arr2);

        System.out.println("\nAfter sorting:");
        for(int ii = 0; ii < 15; ii++)
        {
            System.out.printf("%02d  ", ii);
            for(int jj = 0; jj < Integer.parseInt((sortedArray[ii].toString()).split(",")[0]); jj++) // Second condition = length of the array essentially.
            {
                System.out.print("-");
            }
            System.out.println();
        }
    } 


    public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }

    public static void subspacer()
    {
        System.out.println("\n ----------------------- \n");
    }
}