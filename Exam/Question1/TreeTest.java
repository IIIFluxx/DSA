/**
 * DSA Final Assessment Question 1 - TreeTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 * REF	: Used UnitTestedLinkedList.java given to students in Practical 04, as a reference/inspiration to build this class.
 **/

import java.util.*;
import java.io.*;
public class TreeTest
{
	public static void main(String args[])
	{
		// Variable declarations
        int numPassed = 0;
		int numTests = 0;
		int leafCount = 0;
		DSABinarySearchTree tree = null;
		DSABinarySearchTree emptyTree = new DSABinarySearchTree();
		DSAQueue queue = new DSAQueue();
		//---------------------------------------------------------------------------
		spacer();
		// TEST 1 : CONSTRUCTOR
		System.out.println("TEST 1: Testing Tree constructor by creating a new DSABinarySearchTree.\n");
		try
		{
			numTests++;
			tree = new DSABinarySearchTree();
			System.out.print("Checking if created by using isEmpty(): ");
			if (tree.isEmpty() == false)
			{
				throw new PracExamException("Tree must still be null.");
			}
			numPassed++;
			System.out.println("PASSED");
		}
		catch(Exception e)
		{
			System.out.println("FAILED"); 
		}

		spacer();

		// TEST 2 : INSERT
		System.out.println("TEST 2: Testing insert() function.\n");

		try
		{
			numTests++;
			System.out.println("Tree is currently empty. Populating tree now. \n");
			
			for( int i = 0; i < 10; i++ )
			{
				tree.insert(i * 10); 
            }

			System.out.println("Tree populated. Shouldn't be empty now. Checking using isEmpty function. \n");
			if (tree.isEmpty() == true)
			{
				throw new PracExamException("Tree is still empty - insert operation did not work.");
			}
			
			System.out.println("isEmpty returns FALSE, therefore tree is NOT empty -- insert works.");
			
			// Show insertion by displaying tree
			System.out.println("Displaying tree...\n");
			tree.displayTree();

			numPassed++;
			System.out.println("PASSED");
		}	
		catch(Exception e)
		{
			System.out.println("FAILED");
		}

		spacer(); 
		// TEST 3 : ISEMPTY
		System.out.println("TEST 3: Testing isEmpty() function by checking if empty tree is empty.\n");
		try
		{
			numTests++;
			if(emptyTree.isEmpty() == false)
			{
				throw new PracExamException("Empty tree is somehow not empty - isEmpty does not work.");
			}

			// Show empty tree by displaying tree
			System.out.println("Displaying tree... (should be empty).\n");
			tree.displayTree();

			numPassed++;
			System.out.println("PASSED");
		}
		catch(Exception e)
		{
			System.out.println("FAILED");
		}


		spacer();

		// TEST 4 : IN-ORDER TRAVERSAL

		System.out.println("TEST 4: Testing inorder traversal functions. \n");

		try
		{
			numTests++;
			queue = tree.inOrder();

			// For-each loop that goes through the queue and prints out its contents.
			// Expected to be the same as a in-order traversal of the Tree. 
			for(Object o : queue)
			{
				System.out.print(o + " ");
			}
			System.out.println("\n");
			numPassed++;
			System.out.println("PASSED");
		}
		catch(Exception e)
		{
			System.out.println("FAILED");
		}

		spacer();

		// TEST 5 : COUNT LEAF VALUES
		
		
		try
		{
			numTests++;
			System.out.println("TEST 5: Testing leafCount()....\n");

			System.out.println("Number of leaf nodes: " + tree.countLeafValues() + " (Expected: 1)\n");
			if(tree.countLeafValues() != 1)
			{
				throw new PracExamException("Leaf count is not 1, error in getLeafCount().\n");
			}
			System.out.println("Adding 85 & 95 to make total leaf count 2");
			tree.insert(85);
			tree.insert(95);

			System.out.println("Number of leaf nodes: " + tree.countLeafValues() + " (Expected: 2)\n");
			if(tree.countLeafValues() != 2)
			{
				throw new PracExamException("Leaf count is not 2, error in getLeafCount().\n");
			}
			numPassed++;
			System.out.println("PASSED");
		}
		catch(PracExamException e)
		{
			System.out.println("FAILED: " + e.getMessage());
		}

		
		spacer();

	
		//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber of tests PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (double)numPassed/numTests*100 + "%\n");
    }
	//---------------------------------------------------------------------------

	public static void spacer()
	{
		System.out.println("==========================================================");
	}
}



/* Notes:

	- We need to thoroughly test ALL functionality within the given tree class.
		1. constructor
		2. insert()
		3. isEmpty()
		4. display()
		5. Traversal method (in-order) 
		6. countLeafValues();
	
	---------------
	Testing - be thorough.
	Your test code should document or print the purpose of each test case,
	and give good coverage of scenarios. You should give a score at the end to
	indicate tests passed out of the total tested.	
	
*/