import java.util.*;

public class DSAHashTable 
{
    
    /* ------------------ DSAHashEntry  ------------------ */
    private class DSAHashEntry 
    {
        /* Classfields */
        private String key;
        private Object value;
        private int state;

        /* Default Constructor */

        private DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }

        /* Alternate Constructor */

        private DSAHashEntry(String inKey, Object inValue)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key must not be null!\n");
            }
            key = inKey;
            value = inValue;
            state = 1;
        }

        public String toString()
        {
            return ("Key: " + key + ", Value: " + value + ", State: " + state);
        }

    }

    /* ------------------ DSAHashTable  ------------------ */

    /* Classfields */

    private DSAHashEntry[] hashArray;
    private int count;

    /* Constants */

    private static final double TOL = 0.001; // Tolerance for floats.

    private static final int MAX_STEP = 5; // Prime number - used in double hashing.

    private static final double LOWER_LF  = 0.4;  // Lower bound value for load factor to be before resizing
    private static final double UPPER_LF = 0.7;  // Upper bound value for load factor to be before resizing

    /* Default constructor */
    public DSAHashTable(int tableSize)
    {
        tableSize = findNextPrime(tableSize);
        hashArray = new DSAHashEntry[tableSize];
        count = 0;

        for(int ii=0;ii<tableSize;ii++)
        {
            hashArray[ii] = new DSAHashEntry();
        }
    }

    // Looking for a match on a key, in order to export its value. 
    public Object get(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false; // Two reasons to give up -- (a) empty spaace found, (b) wrapped around entire array.

        while(!found && !giveUp)
        {
            if(hashArray[hashIdx].state == 0) // If state = 0/ if we have wrapped around fully, keep on probing. 
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key.equals(inKey)) // If a match is found
            {
                found = true;
            }
            else // Otherwise (match not found)
            {
                hashIdx = (hashIdx + stepHash(origIdx) % hashArray.length); // Probing -- Double hashing

                if(hashIdx == origIdx) // If we reach the original index - we have checked everything in the table. 
                {
                    giveUp = true;
                }
            }
        }
        if(!found)
        {
            throw new NoSuchElementException("No entry found for key: " + inKey);
        }
        return hashArray[hashIdx].value; // Return value according to index. 
    }


    /* Load factor = numItems / Capacity */



    public double getLoadFactor()
    {
        return ((double)count / (double)hashArray.length);
    }

    // put(), hasKey() and get() all must take the passed-in key and call hash() to convert the key into an integer. This integer is then used as the index into hashArray.

    // - put(inKey, inValue) 

    public void put(String inKey, Object inValue)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIdx].state == 0 || hashArray[hashIdx].state == -1) // if spot found.
            {
                found = true;
            }
            else if(hashArray[hashIdx].state == 1) // Key potentially already present within an index.
            {
                if(hashArray[hashIdx].key.equals(inKey)) // Duplicate found.
                {
                    throw new IllegalArgumentException("Key: " + inKey + " exists within the table already. ");
                }
                // Double hashing
                hashIdx = (hashIdx + stepHash(origIdx)) % hashArray.length; // Probing -- Double hashing

                if(hashIdx == origIdx) // If we reach the original index - we have checked everything in the table. 
                {
                    giveUp = true;
                }
            }
        }
        if(!found)
        {
            throw new NoSuchElementException("No entry found for key: " + inKey);
        }
        // Sets new value of entry
        hashArray[hashIdx] = new DSAHashEntry(inKey, inValue); 
        count++;

        //  When the number of elements is outside of this, the put() or remove() method should call reSize(size) automatically. 
        if(getLoadFactor() > UPPER_LF)
        {
            resize(hashArray.length * 2);
        }
    }


    // + remove(inKey)
    public void remove(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;

        while(!found && !giveUp)
        {
            if(hashArray[hashIdx].state == 0) // if spot not found.
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key.equals(inKey))  // Found value at key.
            {
                found = true;
                count--;
            }
            else
            {
                // Double hashing
                hashIdx = (hashIdx + stepHash(origIdx) % hashArray.length); // Probing -- Double hashing

                if(hashIdx == origIdx) // If we reach the original index - we have checked everything in the table. 
                {
                    giveUp = true;
                }
            }
        }
        if(!found)
        {
            throw new NoSuchElementException("No entry found for key: " + inKey);
        }

       // Clear entry.
       hashArray[hashIdx].state = -1;
       hashArray[hashIdx].key = "";
       hashArray[hashIdx].value = null;

        //  When the number of elements is outside of this, the put() or remove() method should call reSize(size) automatically.
       if(getLoadFactor() < LOWER_LF)
       {
           resize(hashArray.length / 2);
       }
   }


    // + export() 
    public String[] export()
    {
        // Export class state as a string array
        String[] lines = new String[count];
        int jj = 0;
        for(int ii=0;ii<hashArray.length;ii++)
        {
            if(hashArray[ii].state == 1)
            {
                lines[jj] = (hashArray[ii].key + "," + hashArray[ii].value);
                jj++;
            }
        }
        return lines;
    }


    // - hash(inKey) 
    private int hash(String inKey) // Hash using double-hashing -- algorithm taken from Lecture Slides.
    {   
        int a = 63689;
        int b = 378551;
        int hashIdx = 0;
        
        for (int ii = 0; ii < inKey.length(); ii++)
        {
            hashIdx = (hashIdx * a) + inKey.charAt(ii);
            a *= b;
        }
        return Math.abs(hashIdx % hashArray.length);
    }
    /*  private int hash(byte[] key)
        {
            int a = 63689;
            int b = 378551;
            int hashIdx = 0;
            for (int ii = 0; ii < key.length; ii++)
            {
                hashIdx = (hashIdx * a) + key[ii];
                a *= b;
            }
            return hashIdx % hashArray.length;
        }  */
    // -------------------------------------------------------


    // - resize(size)

    /* One simple way to resize is to create the new array, then iterate over the list (ignoring
        unused and previously used slots), re-hashing (using put()). To select a suitable size for
        the new array, you can either use a "look-up" list of suitable primes (web search for this),
        or recalculate a new prime after doubling/halving the previous size. */

    private void resize(int inSize)
    {
        DSAHashEntry[] duplicateArray = hashArray; // Makes a copy of the current hash array.
        inSize = findNextPrime(inSize); // Finds the next prime number using the given integer - inSize
        int newCount = 0;

        hashArray = new DSAHashEntry[inSize]; // Overwrites hashArray(hash table) with an empty array of size inSize.
        for(int ii=0;ii<inSize;ii++) // Initialises new hash table.
        {
            hashArray[ii] = new DSAHashEntry();
        }


        for(int ii=0;ii<duplicateArray.length;ii++) // Initialises new hash table.
        {
            if(duplicateArray[ii].state == 1)
            {
                // Populate existing entries

                // put(String inKey, Object inValue)
                put(duplicateArray[ii].key, duplicateArray[ii].value);
                newCount++;
            }
        }
        count = newCount;
    }


    // Finds the next prime number from the given number
    private int findNextPrime(int startVal)
    {
        int prime = startVal;
        boolean isPrime = false;
        int ii;
        double rootVal;

        if(startVal % 2 == 0) // is it even? If so, make it odd (even numbers cannot be prime). 
        {
            prime--;
        }

        do // Test if our prime candidate is actually a prime number 
        {
            prime += 2; // Adds 2 -- odd, odd, odd
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt((double)prime); 
            do // Checks if there are any factors for our prime candidate. 
            {
                if(prime % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2;
                }
            } while( ( ((double)ii<rootVal) || (Math.abs(rootVal-(double)ii) < TOL)) && isPrime);
        } while(!isPrime);
        return prime;
    }



    /*  FUNCTION stepHash IMPORT key (integer) EXPORT hashStep (integer)
        hashStep ← MAX_STEP – ( key % MAX_STEP ) // Step size will be between 1 and maxStep */
    
    private int stepHash(int inIndex)
    {
        return MAX_STEP - (inIndex % MAX_STEP);
    }

    public boolean hasKey(String inKey)
    {
        boolean found = false;

        if( hashArray[hash(inKey)].state == 1)
        {
            found = true;
        }

        return found;
    }

}
