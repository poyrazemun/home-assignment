package com.poyraz.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static double extractNumber(String text) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }

        throw new IllegalArgumentException("No numeric value found in: " + text);
    }
}
