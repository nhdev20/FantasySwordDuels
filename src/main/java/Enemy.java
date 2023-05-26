public class Enemy {
    helpfulTools tools = new helpfulTools();
    private String name;

    private int health;
    private int speed;
    private int strength;

    public Enemy(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getStrength() {
        return this.strength;
    }

    public int enemyCombatOption() {
        return tools.getRandomNumber(4);
    }
}
