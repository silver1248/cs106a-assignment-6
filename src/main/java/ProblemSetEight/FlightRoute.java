package ProblemSetEight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Path;

import io.vavr.collection.HashMultimap;
import io.vavr.collection.List;
import io.vavr.collection.Multimap;
import io.vavr.collection.Traversable;

public class FlightRoute {


    public static void main(String[] args) throws IOException {
        List<String> route = List.empty();
        String startingCity = "lol";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Welcome to Flight Planner!\n"
                    + "Here's a list of all the cities in our database:\n"
                    +  makePretty(printKeys(convenience()))
                    + "\nLet's plan a round trip route!\n");
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (convenience().containsKey(line)) {
                    if (startingCity.equals("lol")) {
                        System.out.format("Enter the starting City: %s\n", line);
                        startingCity = line;
                        route = route.append(line);
                        System.out.println(makePretty((List<String>) printNext(convenience(), line)));
                    } else if (startingCity.equals(line)) {
                        System.out.println("You have completed your round trip plan!");
                        route = route.append(line);
                        
                        System.out.println("Your final route is " +finalRoute(route));
                    } else {
                        System.out.println("From there you can fly to:\n");
                        System.out.println(makePretty((List<String>) printNext(convenience(), line)));
                        System.out.format("Please choose one: %s\n", line);
                        route = route.append(line);
                    }
                } else {
                    System.out.println("We cant find that location please try again");
                }
            }
        }
    }

    public static Multimap<String, String> convenience() throws IOException { //to make things shorter
        return possibleDestinations(getCities(Path.of("src/test/resources/flights.txt")));
    }

    public static List<String> getCities(Path filePath) throws IOException { //gets all the cities
        List<String> wholeFile = List.empty();
        BufferedReader places = new BufferedReader(new FileReader(filePath.toFile()));

        for (String nextLine = places.readLine(); nextLine != null; nextLine = places.readLine()) {
            wholeFile = wholeFile.append(nextLine);
        }
        return wholeFile;
    }

    public static Multimap<String, String> possibleDestinations(List<String> flightPaths) { 
        String divider = " -> ";
        HashMultimap<String, String> paths = HashMultimap.withSet().empty();
        for (int i = 0; i < flightPaths.length(); i++) {
            String split = flightPaths.get(i);
            String firstPart = split.substring(0, split.indexOf(divider));
            String secondPart = split.substring(split.indexOf(divider)+divider.length());
            paths = paths.put(firstPart, secondPart);
        }
        return paths;
    } //gets where you can go to from any given place

    public static List<String> printKeys(Multimap<String, String> map) {
        return map.keySet().toSortedSet().toList();
    } //prints the starting cities

    public static Traversable<String> printNext(Multimap<String, String> map, String from) {
        return map.get(from).get().toList();
    } // prints the next possible destinations

    public static String makePretty(List<String> list) {
        return list.intersperse(", ").fold("", (a, b) -> a+b);
    } //makes the lists from printKeys printNext, and getCities look better
    
    public static String finalRoute(List<String> list) {
        return list.intersperse(" -> ").fold("", (a, b) -> a+b);
    }
}