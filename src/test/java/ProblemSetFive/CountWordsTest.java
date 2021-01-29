package ProblemSetFive;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CountWordsTest {

    @Test(dataProvider="countThingsTestDP")
    public void countThings(String in, int[] expected) throws IOException {
        int[] actual = CountWords.countThings(in);
        assertEquals(actual, expected);
    }
    
    @DataProvider
    Object[][] countThingsTestDP() {
        return new Object[][] {
            {"toberead", new int[]{4, 14, 66}},
            {"", new int[] {0, 0, 0}},
        };
    }
}