package ProblemSetFive;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HistogramTest {

    @Test(dataProvider="HistogramTestDp")
    public void countThings(int[] in, String expected) {
        String actual = Histogram.makeHistogram(in);
        assertEquals(actual, expected);
    }
    
    @DataProvider
    Object[][] HistogramTestDp() {
        return new Object[][] {
            {new int[] {98, 100, 0, 12, 83}, "00-09: *\n10-19: *\n20-29: \n30-39: \n40-49: \n50-59: \n60-69: \n70-79: \n80-89: *\n90-100: **"},
            
            {new int[] {}, "00-09: \n10-19: \n20-29: \n30-39: \n40-49: \n50-59: \n60-69: \n70-79: \n80-89: \n90-100: "},
        };
    }
}