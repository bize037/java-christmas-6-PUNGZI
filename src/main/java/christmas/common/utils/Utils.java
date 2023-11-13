package christmas.common.utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private static DecimalFormat dec = new DecimalFormat("###,###");
    private static final int MAKE_MINUS = -1;

    public static List<String> splitInList(String input, String splitSymbol) {
        return Arrays.stream(input.split(splitSymbol))
                .map(String::trim)
                .toList();
    }

    public static String decFormat(int input) {
        return dec.format(input);
    }

    public static String minusDecformat(int input) {
        return dec.format(input * MAKE_MINUS);
    }
}
