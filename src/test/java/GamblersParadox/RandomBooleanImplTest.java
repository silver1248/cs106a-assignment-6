package GamblersParadox;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class RandomBooleanImplTest {

    @Test
    public void RandomBooleanImplConstructorTest() {
        RandomBooleanImpl rand = new RandomBooleanImpl(1888888888);
        assertEquals(1888888888, rand.getSeed());
        assertEquals(false, rand.getRandomBoolean());
    }


}
