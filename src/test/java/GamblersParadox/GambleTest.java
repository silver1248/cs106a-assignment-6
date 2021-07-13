package GamblersParadox;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;

public class GambleTest {

    static class MyRandom2 implements RandomBoolean {
        int i = 0;
        @Override public boolean getRandomBoolean() {
            return i++ % 2 == 1;
        } //provides true then false repeating
    }
    static class MyRandom3 implements RandomBoolean {
        int i = 0;
        @Override public boolean getRandomBoolean() {
            return i++ % 3 == 1;
        } //provides true then false repeating
    }

    @Test (dataProvider="averageTestDP")
    public void averageTest(List<Long> in, Double expected) {
        Double actual = Gamble.average(in);
        assertEquals(actual, expected);
    }

    @Test
    public void readFileTest() throws NumberFormatException, IOException {
        int actual = Gamble.readFile();
        assertEquals(actual, 12);
    }

    @Test (dataProvider="resultsTestDP")
    public void resultsTest(int in, RandomBoolean rand, List<Long> expected) throws NumberFormatException, IOException {
        List<Long> actual = Gamble.results(in, rand);
        assertEquals(actual, expected);
    }

    @Test
    public void instantiationTest() {
        new Gamble();
    }

    @DataProvider
    Object[][] resultsTestDP() {

        return new Object[][] {
            {1, new MyRandom2(), List.of(1l)},
            {2, new MyRandom2(), List.of(1l, 2l)},
            {8, new MyRandom2(), List.of(1l, 2l, 2l, 2l, 2l, 2l, 2l, 2l)},

            {1, new MyRandom3(), List.of(1l)}, //random3 makes every 3rd flip a heads so it's 1, 2, 1, 2 etc
            {2, new MyRandom3(), List.of(1l, 2l)},
            {8, new MyRandom3(), List.of(1l, 2l, 1l, 2l, 1l, 2l, 1l, 2l)},
};
    }

    @DataProvider
    Object[][] averageTestDP() {
        return new Object[][] {
            {List.of(8l), 8.0},
            {List.of(8l, 8l, 8l), 8.0},
            
            {List.of(2l, 4l), 3.0},
            
            {List.of(1l, 8l ,12l, 84l), 26.25},
            {List.of(2l, 9l, 6l, 2l, 5l), 4.8},
        };
    }
}
