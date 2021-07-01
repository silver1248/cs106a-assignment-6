package org.sweatshop.FunWithArrays;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;
import lombok.Value;

public class ArrayReversalTest {

    @Value
    public static class ArrayHolder {
        int[] array;
    }
    @Test(dataProvider="turnAroundTestDP")
    public void turnAroundTest(ArrayHolder in, ArrayHolder expected) {
        System.out.println(in);
        
    	int[] actual = ArrayReversal.elementReversal(in.getArray());
        assertEquals(actual, expected.getArray());
    }

    @DataProvider
    Object[][] turnAroundTestDP() {
        return new Object[][] {
            {new ArrayHolder(new int[0]), new ArrayHolder(new int[0])},
//            {null, new ArrayHolder(new int[0])}, 
            //if put in to the code, this WOULD work but because arrays suck it tests wrong
            {new ArrayHolder(new int[]{1}), new ArrayHolder(new int[]{1})},
            {new ArrayHolder(new int[]{1,8}), new ArrayHolder(new int[]{8,1})},
            {new ArrayHolder(new int[]{8,1}), new ArrayHolder(new int[]{1,8})},
            {new ArrayHolder(new int[]{5,4,3,2,1}), new ArrayHolder(new int[]{1,2,3,4,5})},
            {new ArrayHolder(new int[]{8,7,4,9,3}), new ArrayHolder(new int[]{3,9,4,7,8})},
            {new ArrayHolder(new int[]{18,19,20,21,22}), new ArrayHolder(new int[]{22,21,20,19,18})},
        };
    }
    
    @Test
    public void instanceTest() {
        new ArrayReversal();
    }
    
    @Test(dataProvider="turnAroundArraysTestDP")
    public void turnAroundArraysTest(int[][] in, int[][] expected) {
        int[][] actual = ArrayReversal.arrayReversal(in);
        assertEquals(actual, expected);
    }
    
    @DataProvider
    Object[][] turnAroundArraysTestDP() {
        return new Object[][] {
            {new int[][]{{}}, new int[][]{{}}},
            {new int[0][], new int[0][]},
            {new int[][]{{1,2}, {3,4}}, new int[][]{{3,4}, {1,2}}},
            {new int[][]{{1,2,3,4}}, new int[][]{{1,2,3,4}}},
            {new int[][]{{1,2,3,4}, {5,6,7,8}}, new int[][]{{5,6,7,8}, {1,2,3,4}}},
        };
    }
}
