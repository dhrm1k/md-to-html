import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class mdtohtml {
    public static void main(String args[]) {
        mdtohtml w = new mdtohtml();

        w.createOutputDir();
        w.indexhtml();
        w.appendatStart();
        w.content();
        w.appendatEnd();
        System.out.println(w.fetchTitle());
    }

    void createOutputDir() {
        File outputdir = new File("outputdir");
        outputdir.mkdir();        
    }

    void indexhtml() {
        try {
            File indexhtml = new File("outputdir/index.html");

            indexhtml.createNewFile();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void appendatStart() {
        String s_start = "<html><head><title>" + fetchTitle() + "</title></head><body>";
    
        try {
        File readIndexhtml = new File("outputdir/index.html");
        Scanner readingIndexhtml = new Scanner(readIndexhtml);
        
        boolean contains = false;
        
        if(readIndexhtml.exists()) {
            while(readingIndexhtml.hasNextLine()) {
                String line = readingIndexhtml.nextLine();
                if (line.contains(s_start)) {
                contains = true;
                break;
                }
            }
        }
        readingIndexhtml.close();
        
        if (contains == false) {
            FileWriter htmlbasicstart = new FileWriter(readIndexhtml, true);
            htmlbasicstart.write(s_start + "\n");
            htmlbasicstart.close();
            System.out.println("The string is added to file");
        }

        else {
            //it already exists.
        }

    }

        catch(IOException e) {
            e.printStackTrace();
        }
    }

    void content() {
        // Read index.md and paste it to index.html once
        File writeIndexhtml = new File("outputdir/index.html");
    
        try {
            File readIndexmd = new File("index.md");
            Scanner readingIndexmd = new Scanner(readIndexmd);
            FileWriter writingIndexhtml = new FileWriter(writeIndexhtml, true);
    
            // Check if the file has at least one line and skip it
            if (readingIndexmd.hasNextLine()) {
                readingIndexmd.nextLine(); // Read and discard the first line
            }
    
            // Check if the file has at least a second line and skip it
            if (readingIndexmd.hasNextLine()) {
                readingIndexmd.nextLine(); // Read and discard the second line
            }
    
            // Write remaining content to index.html
            while (readingIndexmd.hasNextLine()) {
                String content = readingIndexmd.nextLine();
                writingIndexhtml.write(content + "\n");
            }
    
            // Close resources
            writingIndexhtml.close();
            readingIndexmd.close();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    void appendatEnd() {
        String s_end = "</body></html>";
        try {
            File readIndexhtml = new File("outputdir/index.html");
            Scanner readingIndexhtml = new Scanner(readIndexhtml);
            
            boolean contains = false;
            
            if(readIndexhtml.exists()) {
                while(readingIndexhtml.hasNextLine()) {
                    String line = readingIndexhtml.nextLine();
                    if (line.contains(s_end)) {
                        contains = true;
                        break;
                        }
                    }
            }
            readingIndexhtml.close();
            
            if (contains == false) {
                FileWriter htmlbasicstart = new FileWriter(readIndexhtml, true);
                htmlbasicstart.write(s_end);
                htmlbasicstart.close();
                System.out.println("The string is added to file");
            }
    
            else {
                //it already exists.
            }
        }

        catch(IOException e) {
            e.printStackTrace();
        }
   
    }

    String fetchTitle() {
        try {
            File readIndexmd = new File("index.md");

            Scanner readingIndexmd = new Scanner(readIndexmd);

            if (readingIndexmd.hasNextLine()) {
                String s = readingIndexmd.nextLine();
                //System.out.println(s);
                String title = s.substring(7);
               // System.out.println(title);
               readingIndexmd.close();    

                return title;    
            }

            else {
                //file doesn't contain any any title or anything.
                readingIndexmd.close();    
            return "No title found.";
            }
        }

        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return "No title found.";
    }
}
