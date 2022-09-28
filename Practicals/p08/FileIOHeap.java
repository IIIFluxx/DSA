import java.io.*;

public class FileIOHeap
{
    public static void loadCSV(DSAHeap heap, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        DSALinkedList lines = new DSALinkedList();
        String line;

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
            throw new IllegalArgumentException("File '" + filename + "' not found");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try { fileStrm.close(); } catch(IOException e2) { }
                System.out.println("Error in reading file - file unable to be closed");
            }
        }

        for(Object o : lines)
        {
            heap.add(Integer.parseInt(((String)o).split(",")[0]),((String)o).split(",")[1]);
        }
    }
}