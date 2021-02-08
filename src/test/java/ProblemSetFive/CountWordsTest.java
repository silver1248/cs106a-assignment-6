package ProblemSetFive;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ProblemSetFive.CountWords.WcResult;

public class CountWordsTest {

    @Test(dataProvider="countThingsTestDP")
    public void countThings(String in, WcResult expected) throws IOException {
        WcResult actual = CountWords.countThings(in);
        assertEquals(actual, expected);
    }
    
    @Test(expectedExceptions = FileNotFoundException.class)
    public void countThingsExceptions() throws IOException {
        CountWords.countThings("");
    }
    
    @DataProvider
    Object[][] countThingsTestDP() {
        return new Object[][] {
            {"toberead", new WcResult(2, 7, 35)},
            {"src/test/resources/emptyfile", new WcResult(0, 0, 0)},
            {"src/test/resources/justALetter", new WcResult(0, 1, 1)},
            {"src/test/resources/justANewLine", new WcResult(1, 0, 1)},
        };
    }
}