package terrails.terracore.helper;

public class StringHelper {

    public static String getSubstringBefore(String string, String index) {
        return string.substring(0, string.indexOf(index));
    }
    public static String getSubstringAfter(String string, String index) {
        return string.substring(string.indexOf(index));
    }
}
