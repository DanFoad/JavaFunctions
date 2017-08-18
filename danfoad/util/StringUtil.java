package danfoad.util;

import java.lang.StringBuilder;

/**
 * StringUtil
 * ---------
 * @author Dan Foad
 * @version 1.0.0
 */
public class StringUtil {
    
    public static String trim(String raw) {
        return raw.trim();
    }
    
    public static String ltrim(String raw) {
        return raw.replaceAll("^\\s+", "");
    }
    
    public static String rtrim(String raw) {
         return raw.replaceAll("\\s+$", "");
    }
    
    public static String pad(String raw, int n) {
        System.out.println(n/2);
        return rpad(raw, n/2);
    }

    public static String lpad(String raw, int n) {
        return String.format("%1$" + n + "s", raw);  
    }
    
    public static String rpad(String raw, int n) {
     return String.format("%1$-" + n + "s", raw);  
    }
    
    public static void main(String[] args) {
        System.out.println(pad("hello world", 20) + "|");
    }
}