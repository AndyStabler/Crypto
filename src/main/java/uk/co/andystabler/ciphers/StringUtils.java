package uk.co.andystabler.ciphers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * General purpose string manipulation functions
 * <p>
 * Created by Andy Stabler on 21/04/15.
 */
public class StringUtils {

    /**
     * Repeats a given String so its length matches that of the {@code length} argument.
     * <p>Note:
     * <p>if the length of the {@code inStr} is less than {@code length}, it will be truncated
     * <p>if {@code length} is not divisible by the length of the {@code inStr}, a substring of the inStr
     * will appear at the end of the repeated inStr,
     * <p>e.g.
     * <pre>{@code
     * inStr = "hello"
     * length = 12
     * returns "hellohellohe"
     * }</pre>
     *
     * @param inStr  the inStr to be repeated
     * @param length the required length
     * @return the repeated inStr
     */
    public static String repeatString(String inStr, int length) {
        if (inStr == null || length <= 0) return "";
        if (inStr.length() == length) return inStr;
        if (inStr.length() > length) return inStr.substring(0, length);

        // need to repeat the inStr
        int keysInLen = length / inStr.length();
        // if the inStr doesn't fit into the length exactly, a truncated version will be used at the end
        int remainder = length - (inStr.length() * keysInLen);

        StringBuilder newKey = new StringBuilder();
        for (int i = 0; i < keysInLen; i++)
            newKey.append(inStr);
        newKey.append(inStr.substring(0, remainder));
        return newKey.toString();
    }

    /**
     * Returns a string consisting of characters spaced by the {@code interval} starting with the character at position offset.
     * <p>
     * e.g. splitStringAtInterval("THETRUTHISRARELYPUREANDNEVERSIMPLE", 3, 0) -> "TTTSRYRNERME"
     * <p>
     * splitStringAtInterval("THETRUTHISRARELYPUREANDNEVERSIMPLE", 3, 1) -> "HRHREPEDVSP"
     * <p>
     * splitSringAtInterval("THETRUTHISRARELYPUREANDNEVERSIMPLE", 3, 2) -> "EUIALUANEIL"
     *
     * @param inStr    the string to split
     * @param interval the interval to extract characters
     * @param offset   the position to start extracting strings
     * @return the string containing every nth character, where n is the interval
     */
    public static String splitStringAtInterval(String inStr, int interval, int offset) {
        if (interval <= 0 || offset < 0) return inStr;
        return IntStream.range(0, inStr.length())
                // get each index that's a multiple of the interval
                .filter(i -> i % interval == 0)
                        // get the character at index i + offset
                .mapToObj(i -> i + offset < inStr.length() ? inStr.charAt(i + offset) + "" : "")
                        // join them all up to get the resulting string
                .collect(Collectors.joining());
    }

    /**
     * Returns a list of strings that contains characters from {@code inStr} spaced at the passed interval, from an initial offset of 0 up
     * to the size of the interval.
     * <p>                                                                 index: 0369...         147...
     * e.g. getAllStringsAtInterval("THETRUTHISRARELYPUREANDNEVERSIMPLE", 3) -> ["TTTSRYRNERME", "HRHREPEDVSP", "EUIALUANEIL"]
     *
     * @param inStr    the string to extract substrings from
     * @param interval the interval to extract characters
     * @return a list of strings containing characters spaced by the interval
     */
    public static List<String> getAllStringsAtInterval(String inStr, int interval) {
        List<String> strings = new ArrayList<>();
        // iterate up to the interval value or the length of the string
        int len = Math.min(inStr.length(), interval);
        for (int i = 0; i < len; i++)
            strings.add(splitStringAtInterval(inStr, interval, i));
        return strings;
    }
}
