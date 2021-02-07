package ProblemSetFive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

public class CountWords {
    
    static int lines = 1;
    static int words = 0;
    static int chars = 0;
    
    @Value
    @Builder
    public static class WcResult {
        int lines;
        int words;
        int chars;
    }
    
    public static Set<Character> whitespace = HashSet.of(' ', '\n', '\t');

    public static WcResult countThings(String fileName) throws IOException {
        lines = 1;
        words = 0;
        chars = 0;
        BufferedReader count = 
                new BufferedReader(new FileReader(fileName));
        boolean wuzLastSpace = true;

        for (int i = 0; i < 555555555; i++) { // the 555555555 is just there as a maximum changing it wont affect the code
            char nextChar = (char)count.read();
            if (nextChar == '￿' && i == 0) {
                lines = 0;
                break;
            }
            if (nextChar == '￿') {
                break;
            }
            if (wuzLastSpace == true && nextChar != '￿' && !whitespace.contains(nextChar)) {
                words++;
            }
            if (whitespace.contains(nextChar)) {
                if (nextChar == '\n') {
                    lines++;
                }
                wuzLastSpace = true;
            } else {
                wuzLastSpace = false;
            }
            chars++;
        }
        return new WcResult(lines, words, chars);
    }

}
