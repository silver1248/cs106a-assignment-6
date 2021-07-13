package GamblersParadox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import io.vavr.collection.List;

public class Gamble {
    
    public static List<Long> results(int games, RandomBoolean random) throws NumberFormatException, IOException {
        List<Long> list = List.empty();
        
        for (int i = 0; i < games; i++) {
            long money = 1;
            for (int j = 1; j > 0; j++) {
                System.out.print(money+"\t");
                if (random.getRandomBoolean() == true) {
                    System.out.println("tails");
                    money = money * 2;
                    System.out.println(" _______ "+j);
                } else {
                    System.out.println("heads");
                    list = list.append(money);
                    System.out.println("new game");
                    break;
                }
            }
        }
        return list;
    }
    
    public static Double average(List<Long> list) {
        Double total = 0.0;
        System.out.println();
        for (int i = 0; i < list.length(); i++) {
            total = total + list.get(i);
        }
        total = total/list.length();
        System.out.printf("total post div = %f", (total));
        return (total);
    }
    
    public static int readFile () throws NumberFormatException, IOException {
        try (BufferedReader fileNum = 
                new BufferedReader(new FileReader("src/test/resources/gamblersParadox.txt"))) {
            int times = Integer.valueOf(fileNum.readLine());
            return times;
        }
    }
}
