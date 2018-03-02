package server.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class StringHelper {
    public static String[] tokenize(String sentence) {
        StringTokenizer tokenizer = new StringTokenizer(sentence);
        String[] tokens = new String[tokenizer.countTokens()];

        int count = 0;
        while(tokenizer.hasMoreElements()) {
            tokens[count++] = new String(tokenizer.nextToken());
        }

        return tokens;
    }

    public static String[] extractKeywords(String utterance) {
        String filteredUtterance = utterance.replaceAll("\"<(.*?)>\"", "");
        return tokenize(filteredUtterance);
    }
}
