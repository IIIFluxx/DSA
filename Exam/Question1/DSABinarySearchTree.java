/**
 * DSA Final Assessment Question 1 - DSABinarySearchTree.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 * Ref  : Parts of this class was adapted from my submission for DSA Practical 05 and my DSA Assignment.
 **/
import java.util.*;

public class DSABinarySearchTree {   
	// Inner class TreeNode
	private class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int inVal)
		{
			value = inVal;
			left = null;
			right = null;
		}
	}
	// Class BinaryTree
	private TreeNode root;
	
	public DSABinarySearchTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new TreeNode(val);
		}
		else
		{
			root = insertRec(val, root);
		}
	}

	public boolean isEmpty()
	{
		return root == null;
	}


	
	private TreeNode insertRec(int inVal, TreeNode cur)
	{
		if (cur == null)
		{
			cur = new TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.value)
			{
				cur.left = insertRec(inVal, cur.left);
			}
			else	
			{
				cur.right = insertRec(inVal, cur.right);
			}
		}
		return cur;
	}
	// ADDED METHODS FOR TREE TRAVERSAL. 
	// Reference: Used previously in practicals.


	public DSAQueue inOrder()
    {
        DSAQueue queue = new DSAQueue();
        inorderTraversal(root, queue);
        return queue;
	}
	
	private void inorderTraversal(TreeNode cur, DSAQueue q)
    {
        if(cur != null)
        {
			// Recurse left
            inorderTraversal(cur.left,q);
			// Enqueue the root/cur
			q.enqueue(cur.value);
			// Recurse right
            inorderTraversal(cur.right,q);
        }
	}
	
	public void displayTree()
    {
        DSAQueue queue = new DSAQueue();
        String s = "";

        inorderTraversal(root,queue);

		//System.out.println("Inorder traversal: \n \n");

		Iterator iter2 = queue.iterator();
        while(iter2.hasNext())
        {
            s = s + iter2.next() + " ";
        }

		System.out.println(s);

		s = " ";
	}



	// Get leaf node count -----------------



	public int countLeafValues()
    {
		int nodeCount = 0;
        return getLeafCountRec(root, nodeCount); 
    }


	private int getLeafCountRec(TreeNode node, int nodeCount)
    { 
		if (node != null)
		{
			if (node.left == null && node.right == null)
			{
				nodeCount++;
			}
			else
			{
				nodeCount = getLeafCountRec(node.left, nodeCount) + getLeafCountRec(node.right,nodeCount);
			}
		}
		
		return nodeCount;
    } 


}
