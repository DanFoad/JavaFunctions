package danfoad.util;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtil {
    public static String read(String filename) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                sb.append(raw);
                raw = br.readLine();
                if (raw != null && !raw.isEmpty())
                    sb.append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return sb.toString();
    }
    
    public static String[] readLines(String filename) {
        ArrayList<String> ret = new ArrayList<String>();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                ret.add(raw);
                raw = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        Object[] rawArray = ret.toArray();
        String[] stringArray =  Arrays.copyOf(rawArray, rawArray.length, String[].class);
        return (stringArray);
    }
    
    public static String readLine(String filename, int lineNumber) {
        BufferedReader br = null;
        int lineCount = 0;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                if (lineCount == lineNumber)
                    return raw;
                
                raw = br.readLine();    
                lineCount++;
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return null;
    }
    
    public static int countLines(String filename) {
        BufferedReader br = null;
        int lineCount = 0;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                raw = br.readLine();    
                lineCount++;
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return lineCount;
    }
    
    public static void writeFile(String filename, String contents, boolean append) {
        BufferedWriter bw = null;
        
        try {
            bw = new BufferedWriter(new FileWriter(filename, append)); // Open file
            bw.write(contents);
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
    }
    
    public static void writeFile(String filename, String[] contents, boolean append) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < contents.length; i++) {
            sb.append(contents[i]);
            if (i != contents.length - 1)
                sb.append(System.getProperty("line.separator"));
        }
        
        writeFile(filename, sb.toString(), append);
    }
    
    public static void writeFile(String filename, String contents) {
        writeFile(filename, contents, false);
    }
    
    public static void writeFile(String filename, String[] contents) {
        writeFile(filename, contents, false);
    }
}