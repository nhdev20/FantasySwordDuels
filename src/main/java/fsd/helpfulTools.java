package fsd;

import java.util.Date;
import java.util.Random;

public class helpfulTools {
    public int getRandomNumber(int max) {      //replace with the Math.random() version
        Random random = new Random(getSeed());
        return random.nextInt(max) + 1;
    }

    public long getSeed() {
        Date now = new Date();
        return now.getTime();
    }
}
