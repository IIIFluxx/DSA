import java.util.*;
import java.io.*;
public class DSATree implements Serializable
{
    private class TreeNode implements Serializable
    {
        /* Class fields */
        private String key;
        private Object value;
        private TreeNode leftChild;
        private TreeNode rightChild;
        
        public TreeNode(String inKey, Object inVal)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key cannot be null");
            }
            key = inKey;
            value = inVal;
            rightChild = leftChild = null;
        }

        public String getKey()
        {
            return key;
        }
        public Object getValue()
        {
            return value;
        }
        public TreeNode getLeft()
        {
            return leftChild;
        }
        public TreeNode getRight()
        {
            return rightChild;
        }

        public void setLeft(TreeNode newLeft)
        {
            leftChild = newLeft;
        }

        public void setRight(TreeNode newRight)
        {   
            rightChild = newRight;
        }
    } // End private inner class



    /* Private class fields */
    private TreeNode root;
    /* Default constructor */
    public DSATree()
    {
        root = null;
    }

    /* Wrapper methods that call the private recursive methods within them */

    public Object find(String key)
    {
        Object value;
        value = findRec(key, root);
        return value; 
    }
    public void insert(String key,Object value)
    {
        root = insertRec(key,value,root);
    }

    public void delete(String key)
    {
        root = deleteRec(key,root);
    }


    public int height()
    {
        return heightRec(root);
    }

    public double balance()
    {
        int difference, leftHt, rightHt, rootHt; 
        double balance;
        if(root == null)
        {
            balance = 1;
        }
        else
        {
            leftHt = heightRec(root.getLeft());
            rightHt = heightRec(root.getRight());
            rootHt = height();
            difference = Math.abs(leftHt - rightHt);
            balance = 1 - ((double)difference)/((double)rootHt);
            
        }
        return balance;
        /*
        iLeftHt = heightRec(curNode.getLeft());
        iRightHt = heightRec(curNode.getRight());

        balance = (iLeftHt/iRightHt) * 100;
        return balance;
        */
        

    }


    public String minIter() // finds min
    {
        TreeNode cur;
        cur = root;
        String minKey;
        while(cur.getLeft() != null)
        {
            cur = cur.getLeft();
        }
        minKey = cur.getKey();
        return minKey;
    }

    public String maxIter() // finds max
    {
        TreeNode cur;
        cur = root;
        String maxKey;
        while(cur.getRight() != null)
        {
            cur = cur.getRight();
        }
        maxKey = cur.getKey();
        return maxKey;
    }

    public DSAQueue inOrder()
    {
        DSAQueue queue = new DSAQueue();
        inorderTraversal(root, queue);
        return queue;
    }

    public DSAQueue preOrder() 
    {
        DSAQueue queue = new DSAQueue();
        preorderTraversal(root, queue);
        return queue;
    }

    public DSAQueue postOrder() 
    {
        DSAQueue queue = new DSAQueue();
        postorderTraversal(root, queue);
        return queue;
    }


    public void displayTree()
    {
        DSAQueue queue1 = new DSAQueue();
        DSAQueue queue2 = new DSAQueue();
        DSAQueue queue3 = new DSAQueue();
        String s = "";
        /* --------------------------- */
        inorderTraversal(root,queue2);
        System.out.println("Inorder traversal: \n \n");
        Iterator iter2 = queue2.iterator();
        while(iter2.hasNext())
        {
            s = s + iter2.next() + " ";
        }
        System.out.println(s);
        s = " ";

        
        postorderTraversal(root,queue1);
        System.out.println("Post-order traversal: \n \n");
        Iterator iter1 = queue1.iterator();
        while(iter1.hasNext())
        {
            s = s + iter1.next() + " ";
        }
        System.out.println(s);
        s = " ";

        
        
        preorderTraversal(root,queue3);
        System.out.println("Preorder traversal: \n \n");
        Iterator iter3 = queue3.iterator();
        while(iter3.hasNext())
        {
            s = s + iter3.next() + " ";
        }
        System.out.println(s);
        s = " ";


    }



    /* Private methods with recursive implementations */
    
    private Object findRec(String key, TreeNode cur)
    {
        Object value = null;
        if(cur == null)
        {
            throw new NoSuchElementException("Key " + key + " not found \n");
        }
        else if(key.equals(cur.getKey()))
        {
            value = cur.getValue();
            System.out.println("Key " + key + " found");
        }
        else if(key.compareTo(cur.getKey()) < 0)
        {
            value = findRec(key,cur.getLeft());
        }
        else
        {
            value = findRec(key,cur.getRight());
        }
        return value;
    }

    // insert val recursive method
    
    private TreeNode insertRec(String key, Object value, TreeNode cur) // parent node = updateNode, child node = newNode.
    {
        TreeNode updateNode = null;
        updateNode = cur;
        
        if(cur == null)
        {
            TreeNode newNode = new TreeNode(key,value);
            updateNode = newNode;
        }
        else if(key.compareTo(cur.getKey()) == 0)
        {
            throw new IllegalArgumentException("Key within tree already \n");
        }
        else if(key.compareTo(cur.getKey()) < 0)
        {
            cur.setLeft(insertRec(key,value,cur.getLeft()));
        }
        else
        {
            cur.setRight(insertRec(key,value,cur.getRight()));
        }
        return updateNode;
    }

    private TreeNode deleteRec(String key, TreeNode cur)
    {
        TreeNode updateNode = cur; // parent node = current node.
        if(cur == null)
        {
            throw new NoSuchElementException("Key " + key + " not found \n");
        }
        else if(key.equals(cur.getKey()))
        {
            updateNode = deleteNode(key,cur);
        }
        else if(key.compareTo(cur.getKey()) < 0)
        {
            cur.setLeft(deleteRec(key,cur.getLeft()));
        }
        else
        {
            cur.setRight(deleteRec(key,cur.getRight()));
        }
        return updateNode;
    }

    private TreeNode deleteNode(String key,TreeNode delNode)
    {
        TreeNode updateNode = null;

        if((delNode.getLeft() == null) && (delNode.getRight() == null)) // No children
        {
            updateNode = null;
        }

        else if((delNode.getLeft() != null) && (delNode.getRight() == null)) // One child - left 
        {
            updateNode = delNode.getLeft();
        }
        else if((delNode.getLeft() == null) && (delNode.getRight() != null))
        {
            updateNode = delNode.getRight();
        }
        else // Two children - both left & right.
        {
            updateNode = promoteSuccessor(delNode.getRight());
            if(updateNode != delNode.getRight()) // no cycles
            {
                updateNode.setRight(delNode.getRight()); // update Right
            }
            updateNode.setLeft(delNode.getLeft());
        }
        return updateNode;
    }

    // Assertion: successor will be the left most child of the right subtree
    private TreeNode promoteSuccessor(TreeNode cur)
    {
        TreeNode successor;
        successor = cur;
        if(cur.getLeft() != null)
        {
            successor = promoteSuccessor(cur.getLeft());
            if(successor == cur.getLeft())
            {
                cur.setLeft(successor.getRight());
            }
        }
        return successor;
    }

    private int heightRec(TreeNode curNode)
    {
        int htSoFar, iLeftHt, iRightHt;
        if(curNode == null)
        {
            htSoFar = -1;
        }
        else
        {
            iLeftHt = heightRec(curNode.getLeft());
            iRightHt = heightRec(curNode.getRight());

            // Get highest of left vs right branches
            /*if(iLeftHt > iRightHt)
            {
                htSoFar = iLeftHt + 1;
            }
            else
            {
                htSoFar = iRightHt + 1;
            }*/
            htSoFar = Math.max( iLeftHt, iRightHt ) + 1;
        }
        return htSoFar;
    }
        
    private void preorderTraversal(TreeNode cur, DSAQueue q)
    {   
        if(cur != null)
        {
            q.enqueue(cur.value);
            preorderTraversal(cur.getLeft(),q);
            preorderTraversal(cur.getRight(),q); 
        }
    }


    private void inorderTraversal(TreeNode cur, DSAQueue q)
    {
        if(cur != null)
        {
            inorderTraversal(cur.getLeft(),q);
            q.enqueue(cur.value);
            inorderTraversal(cur.getRight(),q);
        }
    }

    private void postorderTraversal(TreeNode cur, DSAQueue q)
    {
        if(cur != null)
        {
            postorderTraversal(cur.getLeft(),q);
            postorderTraversal(cur.getRight(),q);
            q.enqueue(cur.value);
        }
    }
}
