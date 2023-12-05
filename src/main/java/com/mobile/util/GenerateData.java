package com.mobile.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateData {

    public static String base64(String originalString) {
        byte[] encodeBytes = originalString.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(encodeBytes);
    }

    public static String limitCharacters(String originalString, int numberCharacters) {
        return originalString.substring(0, Math.min(numberCharacters, originalString.length()));
    }

    public static int randomInteger(int minInclusive, int maxExclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
    }
}