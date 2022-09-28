import java.util.*;
import java.io.*;

public class DSAHashTableTestHarness 
{
    public static void main( String[] args )
    {
        HashMap<String, String> table1 = new HashMap<String, String>();

        try
        {
            // Fill graph
            System.out.println("Loading table from RandomNames7000.csv \n");
            loadCSV(table1,"RandomNames7000.csv");
            System.out.println("\nLoaded in successfully.");

            spacer();

            // Get
            System.out.println("Testing get() \n");

            System.out.println("Testing for existing key - 14495655" );
            
            System.out.println("Value found: " + table1.get("14495655") +
            " (Expected: Sofia Bonfiglio)\n" );

            System.out.println( "Testing for non existent key 19977114" );
            if( table1.get("19977114") == null )
            {
                System.out.println("Null returned - expected");
            }
            else
            {
                System.out.println("19977114 found - not null returned - unexpected. \n");
            }

            spacer();

            // hasKey()

            System.out.println("Testing hasKey() \n");

            System.out.println("Testing for existing key - 14495655: " +
            table1.containsKey("14495655") + " (Expected:true)\n");

            System.out.println("Testing for existing key - 19977114: " +
            table1.containsKey("19977114") + " (Expected:false)\n");
            
            spacer();

            // loadFactor, put, remove
            
            System.out.println("Adding an entry with put() (Key: \"ABC\", Value: \"XYZ\")" );
            // put(String inKey, Object inValue)
            table1.put("ABC", "XYZ");

            System.out.println("\nTesting remove() - removing ABC // XYZ entry. \n");
            table1.remove("ABC");

            System.out.println("Attempting to remove an non-existent entry - (Key \"789\")");

            if( table1.remove( "789" ) == null )
            {
                System.out.println( "Null returned - expected" );
            }
            else
            {
                System.out.println("Null not returned - unexpected. \n");
            }

            spacer();
            System.out.println("End. \n");
            spacer();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Exception thrown - unknown cause. \n");
        }
    }


    public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }

    public static void loadCSV(HashMap<String, String> table, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        LinkedList<String> lines = new LinkedList<>();
        int duplicates = 0;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line != null)
            {
                lines.addLast(line);
                line = bufRdr.readLine();
            }
            bufRdr.close();
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("File " + filename + " not found" );
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException e2) { }
                System.out.println("File reading error, file cannot be closed" );
            }
        }

        for(String s : lines)
        {
            try
            {
                table.put(s.split(",")[0], s.split(",")[1]);
            }
            catch(IllegalArgumentException e)
            {
                duplicates++;
            }
        }

        if(duplicates != 0)
        {
            System.out.println("File IO error: " + duplicates + " duplicate keys located in file");
        }
    }

}