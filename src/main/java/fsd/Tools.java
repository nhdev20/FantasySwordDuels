package fsd;

import java.util.Date;
import java.util.Random;

public class Tools {
    public int getRandomNumber(int max) {
        double randomization = Math.random() * max;
        return (int)(randomization + 1);
    }
}