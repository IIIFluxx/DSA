/**
 * DSA Final Assessment Question 4 - DSAHashTable.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 *
 **/

public class DSAHashTable {

    private DSAHashEntry[] hashArray; 
    private int hashCount; 

    public DSAHashTable(int tableSize) {

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
        int i = 1;

        System.out.println("Initial hash: " + initIdx);

        while (hashArray[hashIdx] != null && !hashArray[hashIdx].getKey().equals(inKey)) 
        {
            if (!hashArray[hashIdx].getKey().equals(inKey)) // Not a duplicate.
            { 
                if (hashArray[hashIdx].getState() == 1)  // Key potentially already present within an index.
                {
                    // Linear probing --> hashIdx = (initIdx + i) % hashArray.length; 
                    // Quadratic probing
                    //hashIdx = (hashIdx + (i*i + 1)) % hashArray.length;
                    hashIdx = (hashIdx + i * i++) % hashArray.length;                    

                }

                if (hashArray[hashIdx].getState() < 1) { 
                    hashArray[hashIdx] = new DSAHashEntry(inKey, inValue); 
                    hashCount++; 
                }
            }
            i++; 
        } 
        System.out.println("Actual insert position: " + hashIdx);

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


    private int hash(String inKey) 
	{
        int hashIdx = 0;

        for (int i = 0; i < inKey.length(); i++) 
        {  
            hashIdx = hashIdx + inKey.charAt(i);
        } 
        return hashIdx % hashArray.length;
    }


   private int nextPrime(int inNum) 
   {
        int prime;
        boolean isPrime = false;

        if (inNum % 2 == 0) {
            prime = inNum - 1; 
        } else {
            prime = inNum;
        }

        do { 
            prime = prime + 2; 
            int i = 3;
            isPrime = true;
            double rootVal = Math.sqrt(prime); 
            
            do{
                if ((prime % i) == 0) { 
                    isPrime = false;
                } else {
                    i = i + 2; 
                }
            } while ((i <= rootVal) && (isPrime));
        } while (!isPrime);

        return prime;
    }

    public int getArrayLength() 
	{
        return hashArray.length;
    }
} 
