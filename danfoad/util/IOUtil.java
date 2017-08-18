package danfoad.util;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.lang.NumberFormatException;
import java.lang.Process;
import java.lang.Runtime;

/**
 * IOUtil
 * ---------
 * @author Dan Foad
 * @version 1.2.0
 */
public class IOUtil {
    
    /** IOUtil::getInput
     * Get user input from user as string
     * @param String prompt             Prompt to give to user for what to input
     * @param String errorMessage       Error message to return to user on incorrect input
     * @param String inputIdentifier    Display for user input line, e.g. ' > '
     * @return String                   User inputted string
     */
    public static String getInput(String prompt, String errorMessage, String inputIdentifer) {
        BufferedReader br = null;
        
        // Print prompt if exists
        if (prompt != null)
            print(prompt);
        
        // Print input identifer if exists
        if (inputIdentifer != null)
            print("\r\n" + inputIdentifer);
        
        try {
            br = new BufferedReader(new InputStreamReader(System.in)); // Open file

            String raw = null; // String to hold each line
            while (raw == null || raw.isEmpty()) { // Loop until EOF
                raw = br.readLine();
                if (raw == null || raw.isEmpty()) {
                    
                    // Print error message if exists
                    if (errorMessage != null)
                        println(errorMessage);
                    else // Default error message
                        println("Invalid input, try again.");
                    
                    // Print input identifer if exists again
                    if (inputIdentifer != null)
                        print(inputIdentifer);
                } else {
                    return raw;
                }
            }
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        }
        
        return null;
    }
    
    /** IOUtil::getInput
     * Get user input from user as string with default input identifer
     * @param String prompt             Prompt to give to user for what to input
     * @param String errorMessage       Error message to return to user on incorrect input
     * @return String                   User inputted string
     */
    public static String getInput(String prompt, String errorMessage) {
        return getInput(prompt, errorMessage, null);
    }
    
    /** IOUtil::getInput
     * Get user input from user as string with defualt error message and input identifer
     * @param String prompt             Prompt to give to user for what to input
     * @return String                   User inputted string
     */
    public static String getInput(String prompt) {
        return getInput(prompt, null, null);
    }
    
    /** IOUtil::getInput
     * Get user input from user as string with all defaults
     * @return String   User inputted string
     */
    public static String getInput() {
        return getInput(null, null, null);
    }
    
    /** IOUtil::getInt
     * Get user input from user as int
     * @param String prompt             Prompt to give to user for what to input
     * @param String errorMessage       Error message to return to user on incorrect input
     * @param String inputIdentifier    Display for user input line, e.g. ' > '
     * @return int                      User inputted int
     */
    public static int getInt(String prompt, String errorMessage, String inputIdentifer) {
        int result = -1;
        boolean gotInt = false; // Whether int has been successfully gathered
        String raw = getInput(prompt, errorMessage, inputIdentifer); // Get raw string input
        
        // Loop until user has entered int
        while (!gotInt) {
            try {
                result = Integer.parseInt(raw); // Try to get int value
                gotInt = true;
            } catch (NumberFormatException nfe) { // Not an int
                println(errorMessage);
                
                // Try again
                raw = getInput(prompt, errorMessage, inputIdentifer);
            }
        }
        
        return result;
    }
    
    /** IOUtil::getInt
     * Get user input from user as int with default input identifer
     * @param String prompt             Prompt to give to user for what to input
     * @param String errorMessage       Error message to return to user on incorrect input
     * @return int                      User inputted int
     */
    public static int getInt(String prompt, String errorMessage) {
        return getInt(prompt, errorMessage, null);
    }
    
    /** IOUtil::getInt
     * Get user input from user as int with default input indentifier and error message
     * @param String prompt             Prompt to give to user for what to input
     * @return int                      User inputted int
     */
    public static int getInt(String prompt) {
        return getInt(prompt, "Enter a valid integer.", null);
    }
    
    /** IOUtil::getInt
     * Get user input from user as int with all defaults
     * @return int                      User inputted int
     */
    public static int getInt() {
        return getInt(null,  "Enter a valid integer.", null);
    }
    
    /** IOUtil::shellExecute
     * Execute shell script and return output as string
     * @param String command    Shell script to execute
     * @return String           Output of shell script
     */
    public static String shellExecute(String command) {
        try {
            // Execute shell script
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            process.waitFor();
            
            // Return output as string
            return streamToString(process.getInputStream());
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } catch (InterruptedException ie) {
            System.err.println("Shell script interrupted. " + ie.toString());
        }
        
        return null;
    }
    
    /** IOUtil::streamtoString
     * Return InputStream's buffer as a string representation
     * @param InputStream is    Input stream to get buffer from
     * @return String           Buffer from stream as a string
     */
    static String streamToString(InputStream is) {
        String output = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        
        try {
            // Open stream
            br = new BufferedReader(new InputStreamReader(is));
            
            // Make and fill buffer from stream
            char[] buffer = new char[8192];
            while (true) {
                int byteCount = br.read(buffer, 0, buffer.length);
                if (byteCount < 0) {
                    break;
                }
                // Shove buffer into string builder
                sb.append(buffer, 0, byteCount);
            }
            
            // Get final result
            output = sb.toString();
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } finally {
            // Close BufferedReader
            if (br != null) try { br.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
        }
        
        return output;
    }
    
    /** IOUtil::print
     * Wrapper for System.out.print so you can just type print
     * Use `import static danfoad.util.print;`
     * @param String output     String to write to console
     */
    public static void print(String output) {
        System.out.print(output);
    }
     
    /** IOUtil::println
     * Wrapper for System.out.print so you can just type println
     * Use `import static danfoad.util.println;`
     * @param String output     String to write to console
     */
    public static void println(String output) {
        System.out.println(output);
    }
}