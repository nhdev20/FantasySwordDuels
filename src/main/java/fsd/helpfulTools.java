package fsd;

import java.util.Date;
import java.util.Random;

public class helpfulTools {
    public int getRandomNumber(int max) {
        double randomization = Math.random() * max;
        return (int)(randomization + 1);
    }

    public long getSeed() {
        Date now = new Date();
        return now.getTime();
    }
}
