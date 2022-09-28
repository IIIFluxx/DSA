import java.util.*;
import java.io.*;

public class Menu implements Serializable
{
	public static void main(String args[])
	{
		DSALinkedList list = new DSALinkedList();
		Scanner sc = new Scanner (System.in);
		Scanner isc = new Scanner (System.in);
		String filename = "";
		String inValue;
		int choice = 0;
		Iterator iter;
        // Enter file name to load in 
        System.out.println("Please enter the name of file you wish to save/display/load \n");
		filename = sc.nextLine();
		
		/* // TEST 1 : SAVE
        System.out.print("Creating template list to save, load in or display() \n");
        list.insertFirst("abc");
        list.insertFirst("jkl");
		list.insertFirst("xyz");
		save(list, filename); */



		do
		{
			System.out.println("Please choose an option: \n 1 - Insert a value into the list \n" 
								+ "2 - Read in a file (load) \n" + "3 - Display list \n "
								+ "4 - Write a file (save) \n"	+ "5 - Exit");
			choice = sc.nextInt();

		
		
			switch(choice)
			{
				case 1:
						System.out.println("Please enter a value to insert \n");
						inValue = isc.nextLine();
						list.insertFirst(inValue);
						break;
				case 2: 
						list = load(filename); // Deserialize the file
						break;
				case 3: 
						iter = list.iterator(); 
						while(iter.hasNext())
						{
							System.out.println(iter.next());
						}
						System.out.println("\n");
						break;
				case 4: 
						save(list, filename); // Serialize the file
						break;
				case 5:
						System.out.println("Exiting....");
						break;
				default:
						throw new IllegalArgumentException("Invalid input");

			}
		} while(choice != 5);
	}
	
	
	// Serializing an Object. 
	public static void save(DSALinkedList objToSave, String filename)
	{
		FileOutputStream fileStrm;
		ObjectOutputStream objStrm;
		
		try
		{
			fileStrm = new FileOutputStream(filename);
			objStrm = new ObjectOutputStream(fileStrm);
			objStrm.writeObject(objToSave);
			
			fileStrm.close();
		}
		catch(IOException e)
		{
			System.out.println("Error in file processing: " + e.getMessage());
		}
	}
	// Deserializing an Object. 
	public static DSALinkedList load(String filename) throws IllegalArgumentException
	{
		FileInputStream fileStrm;
		ObjectInputStream objStrm;
		DSALinkedList inObj = new DSALinkedList();
		
		try
		{
			fileStrm = new FileInputStream(filename);
			objStrm = new ObjectInputStream(fileStrm);
			inObj = (DSALinkedList)objStrm.readObject();
			objStrm.close();
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("Class DSALinkedList not found" + e.getMessage());
		}
		catch (IOException e) 
		{
			throw new IllegalArgumentException("Unable to load object from file");
		}
		return inObj;
	}


}