import java.io.*;
import java.util.*;
public class DSALinkedList implements Iterable,Serializable
{
    private class DSAListNode implements Serializable
    {
        public Object m_value;
        public DSAListNode m_next;
        public DSAListNode m_prev;
        
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
    }

    private class DSALinkedListIterator implements Iterator
    {
        /* Class fields */
        public DSAListNode iterNext;
        
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

    /* Private class fields */
    private DSAListNode head;
    private DSAListNode tail;
    /* Default constructor */
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    public void insertFirst(Object newValue)
    {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);        // newNd ← allocate DSAListNode(newValue) 

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

    public void insertLast(Object newValue)
    {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);        // newNd ← allocate DSAListNode(newValue) 
        
        if(isEmpty())
        {
            head = tail = newNd;  // does it matter? head or
        }
        else
        { 
            tail.setNext(newNd);
            newNd.setPrev(newNd);
            tail = newNd;
        }
    }

    public boolean isEmpty()
    {
        return (head == null);
    }

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
    public Iterator iterator()
    {
            return new DSALinkedListIterator(this);
    }

}