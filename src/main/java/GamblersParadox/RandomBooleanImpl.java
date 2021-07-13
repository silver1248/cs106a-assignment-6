package GamblersParadox;

import java.util.Random;

import lombok.Value;

@Value
public class RandomBooleanImpl implements RandomBoolean {
    long seed;
    Random random;
    
    public RandomBooleanImpl (long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    @Override
    public boolean getRandomBoolean() {
        return random.nextBoolean();
    }
}
