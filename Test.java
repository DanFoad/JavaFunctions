import danfoad.util.FileUtil;

public class Test {
    public static void main(String[] args) {
        System.out.println(FileUtil.readTextFile("test.csv"));
        System.out.println("\r\n");
        String[] lines = FileUtil.readLinesFromTextFile("test.csv");
        for (int i = 0; i < lines.length; i++) {
            System.out.println("Line " + i + ": " + lines[i]);
        }
        System.out.println("\r\n");
        System.out.println("Line 4: " + FileUtil.readLineFromTextFile("test.csv", 4));
        System.out.println("Line 10: " + FileUtil.readLineFromTextFile("test.csv", 10));
        System.out.println("\r\nLines: " + FileUtil.countLinesInTextFile("test.csv"));
    }
}