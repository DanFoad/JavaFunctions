import danfoad.util.FileUtil;

public class Test {
    public static void main(String[] args) {
        String contents = FileUtil.read("test.csv");
        System.out.println(contents);
        System.out.println("\r\n");
        String[] lines = FileUtil.readLines("test.csv");
        for (int i = 0; i < lines.length; i++) {
            System.out.println("Line " + i + ": " + lines[i]);
        }
        System.out.println("\r\n");
        System.out.println("Line 4: " + FileUtil.readLine("test.csv", 4));
        System.out.println("Line 10: " + FileUtil.readLine("test.csv", 10));
        System.out.println("\r\nLines: " + FileUtil.countLines("test.csv"));
        FileUtil.writeFile("testwrite.txt", contents);
        FileUtil.writeFile("testwrite2.txt", lines, false);
        System.out.println("Is testwrite empty? " + FileUtil.isEmpty("testwrite.txt"));
    }
}