package Coinage;

import io.vavr.collection.List;
import lombok.Value;

@Value
public class MinimumCoinage {

    public static List<Integer> minimumCoinage(double money) {
        //this is the minimum coinage for a given amount of money without using nickels
        int cents = (int) (money*100);
        int quarters = cents/25;
        int dimes = 0;
        int pennies = 0;
        if (quarters != 0) {
            cents = cents%25;
        }
        dimes = cents/10;
        if (dimes != 0) {
            cents = cents%10;
        }
        if (cents >= 5 && quarters >= 1) {
            pennies = cents - 5;
            quarters = quarters - 1;
            dimes = dimes + 3;
        } else {
            pennies = cents;
        }
        List<Integer> answerList = List.of(quarters, dimes, pennies);
        return answerList;
    }
}
