import java.util.*;

import java.io.*;
/* Used LaFore textbook as reference 
    Test: Insert, find, delete, (traverse, height)
*/

public class TestHarness 
{
    public static void main(String [] args)
    {
        String min, max,search;
        min = " ";
        max = " ";
        int height;
        double balance;
        Scanner sc = new Scanner (System.in);
        DSATree testTree = new DSATree();
        testTree.insert("D", 'D');
        testTree.insert("B", 'B');
        testTree.insert("A", 'A');
        testTree.insert("C", 'C');
        testTree.insert("F", 'F');
        testTree.insert("E", 'E');
        testTree.insert("G", 'G');
        testTree.displayTree();
        System.out.println("Deleting D \n");
        //testTree.delete("D");
        testTree.displayTree();
        System.out.println("Enter a key to search for: \n");
        search = sc.nextLine();
        testTree.find(search);
        min = testTree.minIter();
        System.out.println("Min: " + min);
        max = testTree.maxIter();
        System.out.println("Max: " + max);
        height = testTree.height();
        System.out.println("Height is: " + height);
        testBalance(testTree);

    }

    

}