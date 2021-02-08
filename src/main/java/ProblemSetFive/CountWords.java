package ProblemSetFive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ProblemSetFive.CountWords.WcResult.WcResultBuilder;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

public class CountWords {

    @Value
    @Builder
    public static class WcResult {
        int lines;
        int words;
        int chars;
    }

    public static Set<Character> whitespace = HashSet.of(' ', '\n', '\t');

    public static WcResult countThings(String fileName) throws IOException {
        WcResultBuilder result = WcResult.builder();
        try (BufferedReader count = 
                new BufferedReader(new FileReader(fileName)))
        {
            boolean wuzLastSpace = true;

            for (int i = 0; i < 555555555; i++) { // the 555555555 is just there as a maximum changing it wont affect the code
                int tmp = count.read();
                if (tmp == -1) {
                    break;
                }
                char nextChar = (char)tmp;
                if (whitespace.contains(nextChar)) {
                    if (nextChar == '\n') {
                        result.lines++;
                    }
                    wuzLastSpace = true;
                } else {
                    if (wuzLastSpace == true) {
                        result.words++;
                    }
                    wuzLastSpace = false;
                }
                result.chars++;
            }
            return result.build();
        }
    }

}
