import java.io.*;

public class FileIOHashTable
{
    public static void loadCSV(DSAHashTable table, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        DSALinkedList lines = new DSALinkedList();
        int duplicates = 0;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line != null)
            {
                lines.insertLast(line);
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
                System.out.println("File reading error, file cannout be closed" );
            }
        }
            // --------------------------------
            // --------------------------------
            // --------------------------------
            // --------------------------------
        for(Object o : lines)
        {
            try
            {
                table.put(((String)o).split(",")[0], ((String)o).split(",")[1]);
            }
            catch(IllegalArgumentException e)
            {
                duplicates++;
            }
        }

        if(duplicates != 0)
        {
            System.out.println("FileIOHashTable message: " + duplicates + " duplicate keys located in file");
        }
    }

    public static void saveCSV(DSAHashTable table, String filename)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        
        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);

            for(String line : table.export())
            {
                pw.println(line);
            }

            pw.close();
        }

        catch( IOException e )
        {
            if( fileStrm != null )
            {
                try { fileStrm.close(); } catch(IOException ex2) { }
                System.out.println("Error occured whilst writing to file: " + e.getMessage());	
            }
            throw new IllegalArgumentException( "File reading error" );
        }
    }
}