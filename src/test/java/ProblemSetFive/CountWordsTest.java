package ProblemSetFive;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CountWordsTest {

    @Test(dataProvider="countThingsTestDP")
    public void countThings(String in, int[] expected) throws IOException {
        int[] actual = CountWords.countThings(in);
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="exceptionsTestDP", expectedExceptions = FileNotFoundException.class)
    public void countThingsExceptions(String in, int[] expected) throws IOException {
        int[] actual = CountWords.countThings(in);
        assertEquals(actual, expected);
    }
    
    @DataProvider
    Object[][] exceptionsTestDP() {
        return new Object[][] {
            {"", FileNotFoundException.class}, //i gotta sort this one out
            //so far my solution for this is dont use a file that doesnt exist
        };
    }
    
    @DataProvider
    Object[][] countThingsTestDP() {
        return new Object[][] {
            {"toberead", new int[]{3, 7, 33}},
        };
    }
}