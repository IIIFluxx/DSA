/**
 * DSA Final Assessment Question 4 - HashTest.java
 *
 * Name : Bharath Sukesh
 * ID   : 19982634
 * Ref	: "Java HashMap." Accessed June 15, 2020. https://www.w3schools.com/java/java_hashmap.asp.
 * 			for printing out the contents of a Hash Map.
 * Ref 	: Parts of this class was adapted from my submission for DSA Practical 11.
 * */
import java.util.*;

public class HashMapTest
{
	public static void main(String args[])
	{
        HashMap<String, String> tab = new HashMap<String, String>();

		String[] data = {"11111112", "11111121", "11111211", "11112111", "11121111", "11211111", "12111111", "21111111"};
		spacer();
		
		System.out.println("Inserting into table. \n\n");

        for (int i=0; i < data.length; i++)
		{
			tab.put(data[i], "O"+data[i]);
		}
		
		System.out.println(tab);

		// get()
		System.out.println("Testing get() \n");

		System.out.println("Testing for existing key - 11111112" );
		
		System.out.println("Value found: " + tab.get("11111112") +
		" (Expected: O11111112)\n" );

		System.out.println( "Testing for non existent key 9999999" );
		if(tab.get("9999999") == null)
		{
			System.out.println("Null returned - expected");
		}
		else
		{
			System.out.println("Null is not returned - unexpected. \n");
		}

		spacer();

		// ----------

		System.out.println("Adding an entry with put() (Key: \"123\", Value: \"XYZ\")");
		// put(String inKey, Object inValue)
		tab.put("123", "XYZ");

		System.out.println(tab);

		System.out.println("\nTesting remove() - removing 123 // XYZ entry. \n");
		tab.remove("123");

		System.out.println(tab);

		System.out.println("Attempting to remove an non-existent entry - (Key \"789\")");

		if(tab.remove("666") == null)
		{
			System.out.println("Null returned - expected");
		}
		else
		{
			System.out.println("Null not returned - unexpected. \n");
		}

		spacer();
		System.out.println("End. \n");
		spacer();


		 // --------------

		//System.out.println("Table size is: " + tab.getArrayLength()); Outdated
		System.out.println("Table size is: " + tab.size());
	

		for (String i : tab.keySet())
		{
			System.out.println(i);
		}
		//System.out.println("Load Factor is: " + tab.getLoadFactor()); 
	}

	public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }
}