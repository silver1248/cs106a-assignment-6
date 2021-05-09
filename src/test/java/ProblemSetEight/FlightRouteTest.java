package ProblemSetEight;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.HashMultimap;
import io.vavr.collection.List;
import io.vavr.collection.Multimap;

public class FlightRouteTest {
    
    @Test
    public void nameTest() {
        new FlightRoute(); //for coverage
    }

    @Test(dataProvider="printCitiesTestDP")
    public void printCitiesTest(String in, List<String> expected) throws IOException {
        List<String> actual = FlightRoute.getCities(Paths.get(in)); //look up: paths.get vs path.of
        assertEquals(actual, expected);
    }

    @Test(dataProvider="splitStringsTestDP")
    public void splitStringsTest(String in, List<String> expected) {
        List<String> actual = FlightRoute.splitStrings(in);
        assertEquals(actual, expected);
    }
    
    @Test(dataProvider="possibleDestinationsTestDP")
    public void possibleDestinationsTest(List<String> in, Multimap<String, String> expected) {
        Multimap<String, String> actual = FlightRoute.possibleDestinations(in);
        assertEquals((Object)actual, expected);
    }

    @DataProvider
    Object[][] printCitiesTestDP() {
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
    Object[][] splitStringsTestDP() {
        return new Object[][] {
            {"San Jose -> San Francisco", List.of("San Jose", "San Francisco")},
            {"San Jose -> Anchorage", List.of("San Jose", "Anchorage")},
            {"New York -> Anchorage", List.of("New York", "Anchorage")},
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

}