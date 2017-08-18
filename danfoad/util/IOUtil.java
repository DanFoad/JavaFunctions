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
 * @version 1.0.0
 */
public class IOUtil {
    
    public static String getInput(String prompt, String errorMessage, String inputIdentifer) {
        BufferedReader br = null;
        
        if (prompt != null)
            print(prompt);
        
        if (inputIdentifer != null)
            print("\r\n" + inputIdentifer);
        
        try {
            br = new BufferedReader(new InputStreamReader(System.in)); // Open file

            String raw = null; // String to hold each line
            while (raw == null || raw.isEmpty()) { // Loop until EOF
                raw = br.readLine();
                if (raw == null || raw.isEmpty()) {
                    if (errorMessage != null)
                        println(errorMessage);
                    else
                        println("Invalid input, try again.");
                    
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
    
    public static String getInput(String prompt, String errorMessage) {
        return getInput(prompt, errorMessage, null);
    }
    
    public static String getInput(String prompt) {
        return getInput(prompt, null, null);
    }
    
    public static String getInput() {
        return getInput(null, null, null);
    }
    
    public static int getInt(String prompt, String errorMessage, String inputIdentifer) {
        int result = -1;
        boolean gotInt = false;
        String raw = getInput(prompt, errorMessage, inputIdentifer);
        
        while (!gotInt) {
            try {
                result = Integer.parseInt(raw);
                gotInt = true;
            } catch (NumberFormatException nfe) {
                println(errorMessage);
                raw = getInput(prompt, errorMessage, inputIdentifer);
            }
        }
        
        return result;
    }
    
    public static int getInt(String prompt, String errorMessage) {
        return getInt(prompt, errorMessage, null);
    }
    
    public static int getInt(String prompt) {
        return getInt(prompt, "Enter a valid integer.", null);
    }
    
    public static int getInt() {
        return getInt(null,  "Enter a valid integer.", null);
    }
    
    public static String shellExecute(String command) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            process.waitFor();
            return streamToString(process.getInputStream());
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        } catch (InterruptedException ie) {
            System.err.println("Shell script interrupted. " + ie.toString());
        }
        return null;
    }
    
    static String streamToString(InputStream inputStream) {
        String output = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            char[] buffer = new char[8192];
            while (true) {
                int byteCount = br.read(buffer, 0, buffer.length);
                if (byteCount < 0) {
                    break;
                }
                sb.append(buffer, 0, byteCount);
            }
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
    
    public static void print(String output) {
        System.out.print(output);
    }
        
    public static void println(String output) {
        System.out.println(output);
    }
}