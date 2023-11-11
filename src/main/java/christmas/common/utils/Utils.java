package christmas.common.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static List<String> splitInList(String input, String splitSymbol) {
        return Arrays.stream(input.split(splitSymbol))
                .map(String::trim)
                .toList();
    }
}
