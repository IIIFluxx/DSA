public class DSAHashTableTestHarness 
{
    public static void main( String[] args )
    {
        DSAHashTable table1 = new DSAHashTable(7000);
        DSAHashTable table2 = new DSAHashTable(7000);

        try
        {
            // Fill graph
            System.out.println("Loading table from RandomNames7000.csv \n");
            FileIOHashTable.loadCSV(table1,"RandomNames7000.csv");
            System.out.println("\nLoaded in successfully.");

            spacer();

            // Get
            System.out.println("Testing get() \n");

            System.out.println("Testing for existing key - 14495655" );
            
            System.out.println("Value found: " + table1.get("14495655") +
            " (Expected: Sofia Bonfiglio)\n" );

            System.out.println( "Testing for non existent key 19977114" );
            try
            {
                table1.get( "19977114" );
                System.out.println( "19977114 found - no exception found - unexpected. \n" );
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }

            spacer();

            // hasKey()

            System.out.println("Testing hasKey() \n");

            System.out.println("Testing for existing key - 14495655: " +
            table1.hasKey("14495655") + " (Expected:true)\n");

            System.out.println("Testing for existing key - 19977114: " +
            table1.hasKey("19977114") + " (Expected:false)\n");
            
            spacer();

            // loadFactor, put, remove

            System.out.printf("Initial load factor: %.5f \n\n",table1.getLoadFactor());
            
            System.out.println("Adding an entry with put() (Key: \"ABC\", Value: \"XYZ\")" );
            // put(String inKey, Object inValue)
            table1.put("ABC", "XYZ");

            System.out.printf("Load factor after put() addition: %.5f (Expected to be higher)\n\n",
            table1.getLoadFactor());


            System.out.println("Attempting to add a duplicate \n");
            try
            {
                table1.put("ABC", "XYZ");
                System.out.println("No exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }
            // -----------
            System.out.println("\nTesting remove() - removing ABC // XYZ entry. \n");
            table1.remove("ABC");
            System.out.printf(("Load factor after removal: %.5f " + 
            "(Expected to be back to original number)\n\n" ), 
            table1.getLoadFactor());

            System.out.println("Attempting to remove an non-existent entry - (Key \"789\")");

            try
            {
                table1.remove("789");
                System.out.println("No exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }

            // --------
            spacer();

            // export()

            System.out.println("Testing export() --- saving table to 'saved.csv'");

            FileIOHashTable.saveCSV(table1, "saved.csv");

            spacer();

            System.out.println("Testing operations with saved.csv\n");

            System.out.println("Loading table from saved.csv \n");

            FileIOHashTable.loadCSV(table2,"saved.csv");

            System.out.println("\nLoaded in successfully.");

            // Testing get
            System.out.println("Testing get() with 'saved.csv' \n");

            System.out.println("Testing for existing key - 14495655");
            
            System.out.println("Value found: " + table2.get("14495655") +
            " (Expected: Sofia Bonfiglio)\n" );

            System.out.println( "Testing for non existent key 19977114" );
            try
            {
                table2.get("19977114");
                System.out.println( "19977114 found - no exception found - unexpected. \n" );
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }

            spacer();

            // hasKey()

            System.out.println("Testing hasKey() with 'saved.csv' \n");

            System.out.println("Testing for existing key - 14495655: " +
            table2.hasKey("14495655") + " (Expected:true)\n");

            System.out.println("Testing for existing key - 19977114: " +
            table2.hasKey("19977114") + " (Expected:false)\n");

            spacer();
            // loadFactor, put, remove
            
            System.out.printf("Initial load factor: %.5f \n\n",table2.getLoadFactor());
            
            System.out.println("Adding an entry with put() (Key: \"ABC\", Value: \"XYZ\")" );
            // put(String inKey, Object inValue)

            table2.put("ABC", "XYZ");

            System.out.printf("Load factor after put() addition: %.5f (Expected to be higher)\n\n",
            table2.getLoadFactor());


            System.out.println("Attempting to add a duplicate \n");
            try
            {
                table2.put("ABC", "XYZ");
                System.out.println("No exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }
            // -----------
            System.out.println("\nTesting remove() - removing ABC // XYZ entry. \n");
            table2.remove("ABC");
            System.out.printf(("Load factor after removal: %.5f " + 
            "(Expected to be back to original number)\n\n" ), 
            table2.getLoadFactor());

            System.out.println("Attempting to remove an non-existent entry - (Key \"789\")");

            try
            {
                table2.remove("789");
                System.out.println("No exception found - unexpected. \n");
            }
            catch(Exception e)
            {
                System.out.println("Exception thrown - expected");
            }
            // -------

            spacer();
            System.out.println("End. \n");
            spacer();
        }
        catch(Exception e)
        {
            System.out.println("Exception thrown - unknown cause. \n");
        }
    }


    public static void spacer()
    {
        System.out.println(
            "\n ------------------------------------------------- \n");
    }

}