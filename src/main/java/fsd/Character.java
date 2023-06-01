package fsd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Character {
    private String name;
    private int health;
    private int speed;
    private int strength;

    //Map

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getArmor() {
        return this.strength / 2;
    }

    private final double HEAVY_ATTACK_STR_PERC_DAM = .5;
    private final double QUICK_ATTACK_STR_PERC_DAM = .25;
    public int getDamageDealt(String attackType) { //heavy and quick only options
        if (attackType.equals("heavy")) {
            return 2 + (int)(strength * HEAVY_ATTACK_STR_PERC_DAM);  //heavy has base modifier of 2
        }
        return 1 + (int)(strength * QUICK_ATTACK_STR_PERC_DAM);  //quick has base modifier of 1
    }

//    public double damageReductionPercentage() {
//        return (getArmor() * 5) / 100.0;
//    }

    public void stat() {
        System.out.println("HP: " + this.health + "| SPD: " + this.speed + "| STR: " + this.strength + "| ARM: " + this.getArmor());
    }



//    abstract void specialMove();
}
