import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;

package co.uk.danfoad.util;

/**
 * ReadCSV
 * ---------
 * @author Dan Foad
 * @version 1.0.0
 */
public class ReadCSV {
    
    private BufferedReader br; // Used to read from file
    
    /** ReadCSV::readCSV
     * Read data from arbitrary csv and return result as CSVResult object
     * @param String filename   Filename of CSV file to read in
     * @return CSVResult        CSVResult object representing contents of CSV
     */
    public CSVResult readCSV(String filename) {
        CSVResult result = new CSVResult(); // Make new CSVResult
        
        try {
            br = new BufferedReader(new FileReader(filename)); // Open file
            result.setHeaders(br.readLine()); // Get CSV headers from first line

            String raw = ""; // String to hold each line
            while ((raw = br.readLine()) != null && !raw.isEmpty()) { // Loop until EOF
                String[] fields = raw.split(","); // Split CSV fields
                HashMap<String, String> datum = new HashMap<String, String>(); // New datum
                for (int i = 0; i < result.getHeaders().size(); i++) { // Add each field under each header
                    datum.put(result.getHeaders().get(i), fields[i]);
                }
                result.addData(datum); // Add datum to main data arraylist 
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found, exitting.");
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println("IOException, exitting. " + ioe.toString());
            System.exit(1);
        }
        
        return result;
    }
    
    /**
     * CSVResult
     * -----------
     * @author Dan Foad
     * @version 1.0.0
     */
    class CSVResult {
        
        // ArrayList of CSV headers
        private ArrayList<String> headers;
        
        // ArrayList of Hashmap of Header->Field mappings from data
        private ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        
        /** CSVResult::getHeaders
         * Returns the headers from the CSV file
         * @return ArrayList<String>    ArrayList of Strings containing the headers from CSV
         */
        public ArrayList<String> getHeaders() {
            return headers;
        }
        
        /** CSVResult::setHeaders
         * Sets the headers from the raw first line of CSV file
         * @param String headersRaw     Raw String containing first line of CSV
         */
        public void setHeaders(String headersRaw) {
            // Split based on comma, convert to list and construct ArrayList from that
            headers = new ArrayList<String>(Arrays.asList(headersRaw.split(",")));
        }
        
        /** CSVResult::getData
         * Return the ArrayList of HashMaps representing each file of CSV file
         * @return ArrayList<HashMap<String, String>>   Data structure containing CSV fields
         */
        public ArrayList<HashMap<String, String>> getData() {
            return data;
        }
        
        /** CSVResult::addData
         * Add datum to main data container
         * @param HashMap<String, String> datum     Datum to add to main data container
         */
        public void addData(HashMap<String, String> datum) {
            data.add(datum);
        }
        
        /** CSVResult::toString
         * Convert CSVResult to human-readable format
         * @return String   String representation of CSV file
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            // Add header line, comma seperated
            for (int i = 0; i < headers.size(); i++) {
                sb.append(headers.get(i));
                if (i != headers.size() - 1) sb.append(",");
            }
            sb.append("\r\n");
            
            // Add each datum, comma seperated
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < headers.size(); j++) {
                    sb.append(data.get(i).get(headers.get(j)));
                    if (j != headers.size() - 1) sb.append(",");
                }
                if (i != data.size() - 1) sb.append("\r\n");
            }
            
            return sb.toString();
        }
        
    }
    
    public static void main(String[] args) {
        CSVResult result = new ReadCSV().readCSV("test.csv");
        System.out.println(result);
    }
}