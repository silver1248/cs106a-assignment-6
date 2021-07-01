package ProblemSetNine;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;

public class ExpandableArrayTest {

    @Test(dataProvider="getTestDP")
    public void getTest(ExpandableArray in, int index, Either<String, Boolean> expected) {
        try {
            String actual = in.get(index);
            assertEquals(actual, expected.getLeft());
        } catch(Exception e) {
            assertTrue(expected.isRight());
        }
    }

    @DataProvider
    Object[][] getTestDP() {
        return new Object[][] { 
            //check the output when it fails
            {new ExpandableArray(), 0, Either.right(true)},
            {new ExpandableArray().set(0, "fred"), 0, Either.left("fred")},
            {new ExpandableArray().set(5, "bob"), 0, Either.left(null)},
        };
    }

    @Test(dataProvider="setTestDP")
    public void setTest(List<Tuple2<Integer,String>>list, Either<String[], Boolean> expected) {
        try {
            ExpandableArray actual = new ExpandableArray();
            for (Tuple2<Integer, String> tuple : list) {
                actual.set(tuple._1(), tuple._2());
            }
            assertEquals(actual.getArray(), expected.getLeft());
        } catch(Exception e) {
            assertTrue(expected.isRight());
        }
    }

    @SafeVarargs
    public final <T> List<T> l(T... ts) {
        return List.ofAll(Arrays.stream(ts));
    }

    public <A,B> Tuple2<A,B> t(A a, B b) {
        return Tuple.of(a, b);
    }

    @DataProvider
    Object[][] setTestDP() {
        return new Object[][] {
            {l(t(2, "george"),t(4, "Door")), Either.left(new String[]{null, null, "george", null, "Door"})},
            {l(t(0, "fred"),t(2, "bob")), Either.left(new String[]{"fred", null, "bob"})},
            {l(t(3, "fred"),t(9, "mary"),t(8, "oof")), Either.left(new String[]{null, null, null, "fred", null, null, null, null, "oof", "mary"})},
            {l(t(4, "george"),t(4, "Door")), Either.left(new String[]{null, null, null, null, "Door"})},
            {l(t(2, "george"),t(8, "doot"),t(2, "Door")), Either.left(new String[]{null, null, "Door", null, null, null, null, null, "doot"})},
            {l(t(2, "fred"),t(2, "bob")), Either.left(new String[]{null, null, "bob"})},
            {l(t(0, "fred"),t(3, "bob"),t(1, "bob"),t(0, "george")), Either.left(new String[]{"george", "bob", null, "bob"})},
            {l(t(0, "fred"),t(-1, "bob")), Either.right(true)},
        };
    }
}