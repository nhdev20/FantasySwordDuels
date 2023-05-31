package fsd;

import java.util.Scanner;

public class Character {
    private String name;
    private int health;
    private int speed;
    private int strength;

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

    public double damageReductionPercentage() {
        return (getArmor() * 5) / 100.0;
    }

    public void stat() {
        System.out.println("HP: " + this.health + "| SPD: " + this.speed + "| STR: " + this.strength + "| ARM: " + this.getArmor());
    }
}
