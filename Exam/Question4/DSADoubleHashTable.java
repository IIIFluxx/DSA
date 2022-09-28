/**
 * DSA Final Assessment Question 4 - DSADoubleHashTable.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 * Ref  : Parts of this class was adapted from my submission for DSA Practical 07.
 **/

public class DSADoubleHashTable {

    private DSAHashEntry[] hashArray; 
    private int hashCount; 

    // Class constants
    private static final double TOL = 0.001; // Tolerance for floats.

    public DSADoubleHashTable(int tableSize) {

        int actualSize = nextPrime(tableSize - 1); 
        hashArray = new DSAHashEntry[actualSize];

        for (int i = 0; i < (actualSize); i++) {
            hashArray[i] = new DSAHashEntry(); 
        }
    }
    /* 
    You must add print statements to the put method to show the initial hash and
    actual insert position for each entry */

    // put(), hasKey() and get() all must take the passed-in key and call hash() to convert the key into an integer. 
    // This integer is then used as the index into hashArray.


    public void put(String inKey, Object inValue) 
    {

        int hashIdx = hash(inKey);
        int initIdx = hashIdx;
        boolean giveUp = false;
        int i = 1;

        System.out.println("Initial hash: " + hashIdx);
        
        while (hashArray[hashIdx] != null && !hashArray[hashIdx].getKey().equals(inKey) && !giveUp) 
        {
            if (!hashArray[hashIdx].getKey().equals(inKey)) // Not a duplicate.
            { 
                if (hashArray[hashIdx].getState() == 1)  // Key potentially already present within an index.
                {
                    // Double hashing
                    hashIdx = (hashIdx + stepHash(initIdx)) % hashArray.length; // Probing -- Double hashing
                    if(hashIdx == initIdx) // If we reach the original index - we have checked everything in the table. 
                    {
                        giveUp = true;
                    }
                }
                
                // Sets new value of entry
                if (hashArray[hashIdx].getState() < 1) {
                    System.out.println("Actual insert position: " + hashIdx);
                    hashArray[hashIdx] = new DSAHashEntry(inKey, inValue); 
                    hashCount++;
                }
            }
            i++; 
        } 
    }

    private int stepHash(int inIndex)
    {
        return 13 - (inIndex % 7);
    }


    public double getLoadFactor() {
        
        double loadFactor;
        loadFactor = (double)hashCount / (double)hashArray.length;

        return loadFactor;
    }


    public void display() 
    {    
        for (int i = 0; i < hashArray.length; i++) {   
            try 
            {
                if (hashArray[i].getValue() != null)
                {
                    System.out.println("\t\t" + i + "\t" + hashArray[i].getKey());
                }

            } 
            catch (NullPointerException e) 
            {
                System.out.println("Null pointer at element: " + i);
            } 
        }
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

   private int nextPrime(int inNum) 
   {
        int prime;
        boolean isPrime = false;

        if (inNum % 2 == 0) // is it even? If so, make it odd (even numbers cannot be prime). 
        {
            prime = inNum - 1; 
        } else {
            prime = inNum;
        }

        do { 
            prime = prime + 2; // Adds 2 -- odd, odd, odd
            int i = 3;
            isPrime = true;
            double rootVal = Math.sqrt(prime); 
            
            do{ // Checks if there are any factors for our prime candidate. 
                if ((prime % i) == 0) { 
                    isPrime = false;
                } else {
                    i = i + 2; 
                }
            } while((((double)i<rootVal) || (Math.abs(rootVal-(double)i) < TOL)) && isPrime);
        } while (!isPrime);

        return prime;
    }

    public int getArrayLength() 
	{
        return hashArray.length;
    }
} 
