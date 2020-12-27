package org.sweatshop.FunWithArrays;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ArrayReversalTest {

    @Test(dataProvider="turnAroundTestDP")
    public void turnAroundTest(int[] in, int[] expected) {
    	int[] actual = ArrayReversal.reversal(in);
        assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] turnAroundTestDP() {
        return new Object[][] {
            {new int[0],new int[0]},
            {null, new int[0]},
            {new int[]{1}, new int[]{1}},
            {new int[]{1,8}, new int[]{8,1}},
            {new int[]{8,1}, new int[]{1,8}},
            {new int[]{5,4,3,2,1}, new int[]{1,2,3,4,5}},
            {new int[]{8,7,4,9,3}, new int[]{3,9,4,7,8}},
            {new int[]{18,19,20,21,22}, new int[]{22,21,20,19,18}},
        };
    }
}