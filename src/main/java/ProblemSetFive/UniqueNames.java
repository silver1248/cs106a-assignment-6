package ProblemSetFive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.vavr.collection.List;

public class UniqueNames {

    public static void main(String[] args) throws IOException {

        List<String> list = List.empty();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Press Ctrl+d to exit");
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.format("Enter name: %s\n", line);
                list = list.append(line);
            }
        }
        
        list = list
                .filter(s -> s.length() > 0)
                .map(s -> s.toLowerCase())
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .distinct()
                ;
        System.out.println(list);
    }
}
