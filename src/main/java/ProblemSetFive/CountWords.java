package ProblemSetFive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountWords {

    public static int[] countThings(String fileName) throws IOException {
        BufferedReader count = 
                new BufferedReader(new FileReader(fileName));
        int lines = 0;
        int words = 0;
        int chars = 0;
        boolean wuzLastSpace = true;
        
        for (int i = 0; i < 555555555; i++) {
            char nextChar = (char)count.read();
            if (wuzLastSpace == true && nextChar != 0) {
                words++;
            }
            if (nextChar == ' ' || nextChar == '\n' || nextChar == '\t') {
                wuzLastSpace = true;
            } else {
                wuzLastSpace = false;
            }
            if (nextChar == '￿') {
                break;
            }
            chars++;
        }
        for (int i = 0; i > -1; i++) {
            String line = count.readLine();
            lines++;
            if (line == null) {
                break;
            }
            
        }
        int[] lwc = {lines, words, chars};/*lines words characters*/
        return lwc;
    }
}
