package ProblemSetEight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.HashMultimap;
import io.vavr.collection.List;
import io.vavr.collection.Multimap;
import io.vavr.collection.Traversable;

public class FlightRouteTest {
    
    @Test
    public void nameTest() {
        new FlightRoute(); //for coverage
    }

    @Test(dataProvider="getCitiesTestDP")
    public void getCitiesTest(String in, List<String> expected) throws IOException {
        List<String> actual = FlightRoute.getCities(Paths.get(in)); //look up: paths.get vs path.of
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="possibleDestinationsTestDP")
    public void possibleDestinationsTest(List<String> in, Multimap<String, String> expected) {
        Multimap<String, String> actual = FlightRoute.possibleDestinations(in);
        assertEquals((Object)actual, expected);
    }
    
    @Test(dataProvider="printKeysTestDP")
    public void printtKeysTest(Multimap<String, String> in, List<String> expected) {
        List<String> actual = FlightRoute.printKeys(in);
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="printNextTestDP")
    public void printNextTest(Multimap<String, String> in, String location, Traversable<String> expected) {
        Traversable<String> actual = FlightRoute.printNext(in, location);
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="makePrettyTestDP")
    public void makePrettyTest(List<String> in, String expected) {
        String actual = FlightRoute.makePretty(in);
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="finalRouteTestDP")
    public void finalRouteTest(List<String> in, String expected) {
        String actual = FlightRoute.finalRoute(in);
        assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] getCitiesTestDP() {
        return new Object[][] {
            {"src/test/resources/flights.txt", List.of(
                    "San Jose -> San Francisco",
                    "San Jose -> Anchorage",
                    "New York -> Anchorage",
                    "New York -> San Jose",
                    "New York -> San Francisco",
                    "New York -> Honolulu",
                    "Anchorage -> New York",
                    "Anchorage -> San Jose",
                    "Honolulu -> New York",
                    "Honolulu -> San Francisco",
                    "Denver -> San Jose",
                    "San Francisco -> New York",
                    "San Francisco -> Honolulu",
                    "San Francisco -> Denver")},
        };
    }

    @DataProvider
    Object[][] possibleDestinationsTestDP() {
        return new Object[][] {
            {List.of(
                    "San Jose -> San Francisco",
                    "San Jose -> Anchorage",
                    "New York -> Anchorage",
                    "New York -> San Jose",
                    "New York -> San Francisco",
                    "New York -> Honolulu",
                    "Anchorage -> New York",
                    "Anchorage -> San Jose",
                    "Honolulu -> New York",
                    "Honolulu -> San Francisco",
                    "Denver -> San Jose",
                    "San Francisco -> New York",
                    "San Francisco -> Honolulu",
                    "San Francisco -> Denver"), HashMultimap.withSet().of(
                            "San Jose", "San Francisco",
                            "San Jose", "Anchorage",
                            "New York", "Anchorage",
                            "New York", "San Jose",
                            "New York", "San Francisco",
                            "New York", "Honolulu",
                            "Anchorage", "New York",
                            "Anchorage", "San Jose",
                            "Honolulu", "New York",
                            "Honolulu", "San Francisco") 
                .put("San Francisco", "New York")
                .put("Denver", "San Jose")
                .put("San Francisco", "Honolulu") //you may notice that the orders of the list and Multimap do not match, this is 
                .put("San Francisco", "Denver")}, //because we cast actual to object making it not an iterable
        }; // also i know this is ugly but it's the only way i could get it to work
    }
    
    @DataProvider
    Object[][] printKeysTestDP() {
        return new Object[][] {
            {HashMultimap.withSet().of(
            "San Jose", "San Francisco",
            "San Jose", "Anchorage",
            "New York", "Anchorage",
            "New York", "San Jose",
            "New York", "San Francisco",
            "New York", "Honolulu",
            "Anchorage", "New York",
            "Anchorage", "San Jose",
            "Honolulu", "New York",
            "Honolulu", "San Francisco"), 
                List.of("San Jose", "New York", "Anchorage", "Honolulu")
                .toSortedSet().toList()} //added this line 
            //in the hopes that it would remove the randomness of sets
        };
    } //dislike for sets edit: VICTORY

    @DataProvider
    Object[][] printNextTestDP() {
        return new Object[][] {
            {HashMultimap.withSet().of(
            "San Jose", "San Francisco",
            "San Jose", "Anchorage",
            "New York", "Anchorage",
            "New York", "San Jose",
            "New York", "San Francisco",
            "New York", "Honolulu",
            "Anchorage", "New York",
            "Anchorage", "San Jose",
            "Honolulu", "New York",
            "Honolulu", "San Francisco"), "San Jose",
                (Traversable)List.of("San Francisco", "Anchorage")}
        };
    }
    
    @DataProvider
    Object[][] makePrettyTestDP() {
        return new Object[][] {
            {List.of("egg", "caterpillar", "cacoon",
                    "butterfly"), 
                "egg, caterpillar, cacoon, butterfly"}
        };
    }
    
    @DataProvider
    Object[][] finalRouteTestDP() {
        return new Object[][] {
            {List.of("egg", "caterpillar", "cacoon",
                    "butterfly"), 
            "egg -> caterpillar -> cacoon -> butterfly"}
        };
    }
}