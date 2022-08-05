package Coinage;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;

public class MinimumCoinageTest {

    @Test (dataProvider = "minimumCoinageTestDP")
    public void minimumCoinageTest(double in, List<Integer> expected) {
        List<Integer> actual = MinimumCoinage.minimumCoinage(in);
        assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] minimumCoinageTestDP() {

        return new Object[][] {
            {0.00, List.of(0, 0, 0)},
            {0.03, List.of(0, 0, 3)},
            {0.10, List.of(0, 1, 0)},
            {0.11, List.of(0, 1, 1)},
            {0.16, List.of(0, 1, 6)},
            {0.25, List.of(1, 0, 0)},
            {5.00, List.of(20, 0, 0)},
            {5.83, List.of(22, 3, 3)},
            {8.23, List.of(32, 2, 3)},
            {0.26, List.of(1, 0, 1)},
            {0.30, List.of(0, 3, 0)},
        };
    }
}
