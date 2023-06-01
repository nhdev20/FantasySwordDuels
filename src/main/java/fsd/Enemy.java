package fsd;

import fsd.Character;

public class Enemy extends Character {
    helpfulTools tools = new helpfulTools();
    private String name;

    public Enemy(int currentLevel) {
        setName(enemyNamesByLevel[currentLevel - 1]);
        setHealth(currentLevel);
        setSpeed(currentLevel);
        setStrength(currentLevel);
    }

    public void statRandomizer(int currentLevel) {
//        int rand1 = (int)((Math.random() * (5 + currentLevel)) + 1);
        int rand1 = (int)((Math.random() * 5) + 1);
        int rand2 = (int)((Math.random() * 5) + 1);
        int rand3 = (int)((Math.random() * 5) + 1);

        setHealth(getHealth() + rand1);  //AKA current health + random integer between 1 and (5 + current level)
        setSpeed(getSpeed() + rand2);
        setStrength(getStrength() + rand3);
    }

    public int enemyCombatOption() {
        return tools.getRandomNumber(4);
    }

    String[] enemyNamesByLevel = new String[] {"Artego", "Boris", "Calan", "Doron", "Ernet", "Falon", "Gratz", "Hortia", "Ingito", "Jilo"};


}
