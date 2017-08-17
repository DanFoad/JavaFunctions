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

/**
 * FileUtil
 * ---------
 * @author Dan Foad
 * @version 1.3.0
 */
public class FileUtil {
    
    /** FileUtil::read
     * Read in contents from text file as string
     * @param String filename   The filename of the file to read in
     * @return String           Contents of text file as string
     */
    public static String read(String filename) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                sb.append(raw); // Add each line to StringBuilder
                raw = br.readLine();
                
                // Add newline characters if not EOF
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
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return sb.toString();
    }
    
    /** FileUtil::readLines
     * Read in contents from text file as string array
     * @param String filename   The filename of the file to read in
     * @return String[]         Contents of text file as string array
     */
    public static String[] readLines(String filename) {
        ArrayList<String> ret = new ArrayList<String>(); // Temporary ArrayList to hold contents
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                ret.add(raw); // Add each line to ArrayList
                raw = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        // Convert ArrayList to String array
        Object[] rawArray = ret.toArray();
        String[] stringArray =  Arrays.copyOf(rawArray, rawArray.length, String[].class);
        return stringArray;
    }
    
    /** FileUtil::readLine
     * Fetch single line from text file
     * @param String filename   The filename of the file to read
     * @param int lineNumber    The line number of the line to find
     * @return String           Line at index lineNumber, null if non-existent
     */
    public static String readLine(String filename, int lineNumber) {
        BufferedReader br = null;
        int lineCount = 0;
        String ret = null;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            while (raw != null && !raw.isEmpty()) { // Loop until EOF
                if (lineCount == lineNumber) { // If found line set return value and break out
                    ret = raw;
                    break;
                }
                
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
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        // If line was not found return null, otherwise line
        return ret;
    }
    
    /** FileUtil::countLines
     * Count the number of lines in a text file
     * @param String filename   The filename of the file to count lines in
     * @return int              The number of lines in the file
     */
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
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return lineCount;
    }
    
    /** FileUtil::isEmpty
     * Check whether file is empty
     * @param String filename   The filename of the file to count lines in
     * @return boolean          Whether file is empty
     */
    public static boolean isEmpty(String filename) {
        BufferedReader br = null;
        boolean ret = false;
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file

            String raw = br.readLine(); // String to hold each line
            ret = (raw == null); // Check if first line is null
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return ret;
    }
    
    /** FileUtil::writeFile
     * Write String to file
     * @param String filename   The filename of the file to write to
     * @param String contents   The contents to write to file
     * @param boolean append    Whether to append the text to the end of file or overwrite
     */
    public static void writeFile(String filename, String contents, boolean append) {
        BufferedWriter bw = null;
        
        try {
            bw = new BufferedWriter(new FileWriter(filename, append)); // Open file
            bw.write(contents); // Write string to file
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            // Close BufferedWriter
            if (bw != null) try { bw.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
    }
    
    /** FileUtil::writeFile
     * Write String array to file
     * @param String filename   The filename of the file to write to
     * @param String[] contents The contents to write to file
     * @param boolean append    Whether to append the text to the end of file or overwrite
     */
    public static void writeFile(String filename, String[] contents, boolean append) {
        StringBuilder sb = new StringBuilder();
        
        // Concatenate string array to string with newline characters between lines
        for (int i = 0; i < contents.length; i++) {
            sb.append(contents[i]);
            if (i != contents.length - 1)
                sb.append(System.getProperty("line.separator"));
        }
        
        // Write to file
        writeFile(filename, sb.toString(), append);
    }
    
    /** FileUtil::writeFile
     * Write String to file, overwritting contents
     * @param String filename   The filename of the file to write to
     * @param String contents   The contents to write to file
     */
    public static void writeFile(String filename, String contents) {
        writeFile(filename, contents, false);
    }
    
    /** FileUtil::writeFile
     * Write String array to file, overwritting contents
     * @param String filename   The filename of the file to write to
     * @param String[] contents The contents to write to file
     */
    public static void writeFile(String filename, String[] contents) {
        writeFile(filename, contents, false);
    }
    
    /** FileUtil::appendFile
     * Write String to file, appending to end of file
     * @param String filename   The filename of the file to write to
     * @param String contents   The contents to append to file
     */
    public static void appendFile(String filename, String contents) {
        writeFile(filename, contents, true);
    }
    
    /** FileUtil::appendFile
     * Write String array to file, appending to end of file
     * @param String filename   The filename of the file to write to
     * @param String[] contents The contents to append to file
     */
    public static void appendFile(String filename, String[] contents) {
        writeFile(filename, contents, true);
    }
}