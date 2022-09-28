/*  --------------------------------------------------------------------------
**  Author: Bharath Sukesh
**  Date: 08/05/2020
**  Purpose: Implementation of generic doubly-linked,double ended linked list.
**  Reference: This class was adapted from my submission for DSA Practical 04.
** --------------------------------------------------------------------------
*/
 
import java.io.*;
import java.util.*;
public class DSALinkedList implements Iterable,Serializable
{
    /* ------------------ DSAListNode ------------------ */
    private class DSAListNode implements Serializable
    {
        /* -- Private classfields -- */
        private Object m_value;
        private DSAListNode m_next;
        private DSAListNode m_prev;
        
        // Alternate constructor -- CONSTRUCTOR DSAListNode IMPORTS inValue
        // Exports memory adress of a new DSAListNode
        public DSAListNode(Object inValue)
        {
            m_value = inValue;
            m_next = null;
            m_prev = null;
        }
        public Object getValue()
        {
            return m_value;
        }
        public DSAListNode getNext()
        {
            return m_next;
        }
        
        public DSAListNode getPrev()
        {
            return m_prev;
        }

        public void setNext(DSAListNode newNd)
        {
            m_next = newNd;
        }

        public void setPrev(DSAListNode newNd)
        {
            m_prev = newNd;
        }

        public void setValue(Object inValue)
        {
            m_value = inValue;
        }

    }

    private class DSALinkedListIterator implements Iterator, Serializable
    {
        /* -- Private classfields -- */
        private DSAListNode iterNext;
        
        // Exports memory adress of a DSALinkedListIterator
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }
        public boolean hasNext()
        {
            return iterNext != null;
        }
        public Object next()
        {
            Object value;
            if(iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");  
        }
    }

    /* -- Private classfields -- */
    private DSAListNode head;
    private DSAListNode tail;
    /* Default constructor */
    // Exports memory adress of a DSALinkedList
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }
    
    // Inserts given import Object as a new node at the head of the list 
    public void insertFirst(Object newValue)
    {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);  // newNd ← allocate DSAListNode(newValue) 

        if(isEmpty())
        {
            head = tail = newNd; 
        }
        else
        {
            head.setPrev(newNd);
            newNd.setNext(head);
            head = newNd;
        }
    }

    // Inserts given import Object as a new node at the tail of the list 
    public void insertLast(Object newValue)
    {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);  // newNd ← allocate DSAListNode(newValue) 
        
        if(isEmpty())
        {
            head = newNd; 
            tail = newNd;
        }
        else
        { 
            tail.setNext(newNd);
            newNd.setPrev(tail);
            tail = newNd;
        }
    }
    // Returns boolean on whether linked list is empty or not
    public boolean isEmpty()
    {
        return (head == null);
    }

    // Returns an Object of the head node
    public Object peekFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot peek first element - list is empty \n Program terminated");
        }
        else
        {
           nodeValue = head.getValue();
        }
        return nodeValue;
    }

    // Returns an Object of the tail node
    public Object peekLast()
    {
        Object nodeValue = null;

        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot peek last element - list is empty \n Program terminated");
        }
        else  
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    // Returns the value of the first node in the list as an Object and removes it from the list 
    public Object removeFirst()
    {
        Object nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot remove first element - list is empty \n Program terminated");
        }
        else if((head.getNext() == null) && (tail.getPrev() == null))
        {
            nodeValue = head.getValue();
            head = tail = null;
        }
        else
        {
           nodeValue = head.getValue();
           head = head.getNext();
           head.setPrev(null);
        }
        return nodeValue;
    }

    // Returns the value of the last node in the list as an Object and removes it from the list 
    public Object removeLast()
    {
        Object nodeValue = null;

        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot remove last element - list is empty \n Program terminated");
        }
        else if(head == tail)
        {
                head = tail = null;
        }
        else
        {
            nodeValue = tail.getValue();
            tail = tail.getNext();
            tail.setPrev(null);
        }
        return nodeValue;
    }

    // Returns an instance of DSALinkedListIterator
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }


    // Outputs the contents of a linkedlist to the terminal
    public void printList() 
	{
		DSAListNode currentNode;
		if(head != null)
		{
			currentNode = head;
			System.out.println(currentNode.getValue());
			while(currentNode.m_next != null)
			{
                currentNode = currentNode.m_next;
                System.out.println(currentNode.getValue());
			}
		}
    }
    

    // Removes a node with the given Object import
    public void removeNode(Object inObject)
    {
        boolean found;
        DSAListNode tempNode = head;
        found = false;

        if( isEmpty() )
        {
            throw new IllegalArgumentException( "List is empty" );
        }
        else if((head.getNext() == null) && (tail.getPrev() == null))
        {
            if(tail.getValue().equals(inObject))
            {
                head = null;
                tail = null;
            }
        }
        else
        {
            while(tempNode != null && found == false)
            {
                if(tempNode.getValue().equals(inObject))
                {
                    // We found it!
                    found = true;
                    if(tempNode.m_prev != null)
                    {
                        tempNode.m_prev.setNext((tempNode.m_next));
                    }
                    else
                    {
                        head = tempNode.getNext();
                    }

                    if(tempNode.m_next != null)
                    {
                        tempNode.m_next.setPrev((tempNode.m_prev));
                    }
                    else
                    {
                        tail = tempNode.getPrev();
                    }
                }
                else
                {
                    tempNode = tempNode.getNext();
                }
            }
            if(found)
            {
                System.out.println("Object " + inObject.toString() + " has been removed");
            }
        }
    }

    // Returns a String containing 4 elements of the list. Adapted for timestep functionality.
    public String toString()
    {
        String export = " ";
        Iterator iter = iterator(); // Uses LL iterator(), NOT Java's in built iterator. 

        if(isEmpty())
        {
            export = "[]";
        }
        else
        {
            while(iter.hasNext())
            {
                export = export + "[ " + iter.next() + "," + iter.next() + "," + iter.next() + "," + iter.next() + "]\n";
            }
        }
        return export;

    }
}