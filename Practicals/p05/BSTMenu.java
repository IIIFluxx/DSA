import java.util.*;
import java.io.*;
/* Used LaFore textbook as reference 
    Test: Insert, find, delete, (traverse, height)
*/

public class BSTMenu
{
        public static void main(String [] args)
    {
        DSATree testTree = new DSATree();
		Scanner sc = new Scanner (System.in);
        Scanner isc = new Scanner (System.in);
        String filename = "";
		String inKey, inValue;
        int choice = 0;
        int orderChoice = 0;
        DSAQueue nodeQueue;

        System.out.println("Please enter the name of file you wish to save/display/load \n");
		filename = sc.nextLine();
        do
        {
            System.out.println("Please choose an option: \n 1 - Insert a value into Tree \n " 
                                + "2 - Read a CSV file (load) \n" + "3 - Read in a Serialized file (load) \n" + "4 - Display tree \n "
                                + "5 - Write a CSV file (save) \n" + "6 - Write a Serialized file (save) \n"	
                                + "7 - Exit");
            choice = sc.nextInt();
            
            switch(choice)
            {
                case 1:
                    System.out.println("Please enter a key to insert \n");
                    inKey = isc.nextLine();    
                    System.out.println("Please enter a value to insert \n");
                    inValue = isc.nextLine();
                    testTree.insert(inKey, inValue);
                    break;

                case 2: // load from CSV File
                        testTree = loadCSV(filename);
                        break;

                case 3: // Deserialize the file
                        testTree = loadSer(filename); 
                        break; 
                case 4: // Display tree
                        testTree.displayTree();
                        break;  

                case 5: // Save as CSV File
                        System.out.println("Please choose a method to traverse");
                        System.out.println("1 - Pre-order, 2 - In-order, 3 - Post-order \n");
                        orderChoice = isc.nextInt();
                        switch(orderChoice)
                        {
                            case 1: // pre-order
                                    nodeQueue = testTree.preOrder();
                                    saveCSV(filename, nodeQueue);

                                    break;

                            case 2: // in order
                                    nodeQueue = testTree.inOrder();
                                    saveCSV(filename, nodeQueue);
                                    break;

                            case 3: // post order
                                    nodeQueue = testTree.postOrder();
                                    saveCSV(filename, nodeQueue);
                                    break;

                            default:
                                    throw new IllegalArgumentException("Invalid input");

                        }
                        break;
                case 6: // Save as serialized file.
                        saveSer(testTree, filename); // Serialize the file
                        break;
                
                case 7: 
                        System.out.println("Exiting program..");
                        break;
                default:
                        throw new IllegalArgumentException("Invalid input");
            }
        } while(choice != 7);
        sc.close();
        isc.close();
    }
        


        // Deserializing an Object. 
    public static DSATree loadSer(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSATree inObj = new DSATree();
        
        try
        {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSATree)objStrm.readObject();
            objStrm.close();
        }
        catch (ClassNotFoundException e) 
        {
            System.out.println("Class DSATree not found" + e.getMessage());
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Unable to load object from file");
        }
        return inObj;
    }

        

         // Serializing an Object. 
    public static void saveSer(DSATree objToSave, String filename) 
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


    public static DSATree loadCSV(String filename)
    {
        FileInputStream fileStrm = null;
		InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray;
        DSATree inObj = new DSATree();
        try
        {
            fileStrm = new FileInputStream(filename);
			rdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(rdr);
			line = bufRdr.readLine();
			while (line != null)
			{
                lineArray = line.split(","); // split on comma
                inObj.insert(lineArray[0], (String)lineArray[1]); // processing
				line = bufRdr.readLine();
			}
			
			fileStrm.close();

        }
        catch (IOException e) 
		{
			if (fileStrm != null) 
			{
				try { fileStrm.close(); } catch (IOException ex2) { }
			}
		System.out.println("Error in file processing: " + e.getMessage());
		}
        return inObj;
    }

    public static void saveCSV(String filename, DSAQueue objToSave)
	{
		FileOutputStream fileStrm = null;
        PrintWriter pw;
        Iterator iter;
        String s = " ";
        try 
		{
			fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);
            iter = objToSave.iterator();
            while(iter.hasNext())
            { // print out the queue elements (containing values) in specific order
                s = s + iter.next() + ",\n";
                pw.print(s);
            }
    
            /*s = s + iter2.next() + " ";*/


            // pw.print(objToSave) but for each line = key value pair
            pw.close();
		}
		catch (IOException e) 
		{
			if (fileStrm != null)
			{
				try { fileStrm.close(); } catch (IOException ex2) { }
			}
		System.out.println("Error occured whilst writing to file: " 
							+ e.getMessage());	
		}   
	}

    // To-do: saveCSV
    // preorder - for exact same tree

    public static void displayQueue(DSAQueue q)
    {
        q.printQueue();
    }

}