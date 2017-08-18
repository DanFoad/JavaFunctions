import danfoad.util.FileUtil;
import danfoad.util.IOUtil;
import danfoad.util.FileUtil.CSVResult;

import static danfoad.util.IOUtil.print;
import static danfoad.util.IOUtil.println;

public class Test {
    public static void main(String[] args) {
        
        println("Reading with FileUtil.readCSV: ");
        CSVResult result = FileUtil.readCSV("test.csv");
        println(result + "\r\n");
        
        println("Reading with FileUtil.read: ");
        String contents = FileUtil.read("test.csv");
        println(contents + "\r\n");

        String[] lines = FileUtil.readLines("test.csv");
        for (int i = 0; i < lines.length; i++) {
            println("Line " + i + ": " + lines[i]);
        }
        println("\r\n");
        
        println("Line 4: " + FileUtil.readLine("test.csv", 4));
        println("Line 10: " + FileUtil.readLine("test.csv", 10));
        
        println("\r\nLines: " + FileUtil.countLines("test.csv"));
        
        FileUtil.writeFile("testwrite.txt", contents);
        FileUtil.writeFile("testwrite2.txt", lines, false);
        
        println("Is testwrite empty? " + FileUtil.isEmpty("testwrite.txt"));
        
        String test = IOUtil.getInput("enter a string: ", "incorrect string", " > ");
        int test2 = IOUtil.getInt("enter an int: ");
        
        println(IOUtil.shellExecute("ls"));
    }
}