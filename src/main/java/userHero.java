import java.util.Scanner;

public class userHero {
    private String name;

    private int health;
    private int speed;
    private int strength;
    private int armor = strength / 2;

    public userHero(String name) {
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
        return this.armor;
    }

    public void printOutStats() {
        System.out.println("Health: " + this.health + "\nSpeed: " + this.speed + "\nStrength: " + this.strength);
    }

    Scanner input = new Scanner(System.in);
    public void attributeSelection() {
        System.out.println("\n***********ATTRIBUTE SELECTION***********\n");
        boolean canContinue = false;
        while (!canContinue) {
            System.out.println("You have 15 attribute points to distribute among three attributes: health, speed, and strength");
            System.out.println("Health determines how much damage you can take and continue forward.");
            System.out.println("Speed determines how quickly you can move out of the way or perform a quick attack.");
            System.out.println("Strength determines your damage and armor protection.");
            System.out.println("You must put at least one point in each category");

            int distributeTotal = 15;
            boolean inBounds = false;
            int hpAddition = 0;

            while (!inBounds) {
                System.out.print("How many points would you like to place in Health? ");
                String hpAdd = input.nextLine();
                hpAddition = Integer.parseInt(hpAdd);
                distributeTotal -= hpAddition;
                if (hpAddition >=0 && distributeTotal >= 0) {
                    inBounds = true;
                }
            }

            inBounds = false;
            int speedAddition = 0;

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Speed? ");
                String speedAdd = input.nextLine();
                speedAddition = Integer.parseInt(speedAdd);
                distributeTotal -= speedAddition;
                if (speedAddition >=0 && distributeTotal >= 0) {
                    inBounds = true;
                }
            }

            inBounds = false;
            int strengthAddition = 0;

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Strength? ");
                String strengthAdd = input.nextLine();
                strengthAddition = Integer.parseInt(strengthAdd);
                distributeTotal -= strengthAddition;
                if (strengthAddition >=0 && distributeTotal >= 0) {
                    inBounds = true;
                }
            }

            System.out.println(this.getName() + ", you have opted to disseminate your points as follows:");
            System.out.println("Health: " + hpAddition);
            System.out.println("Speed: " + speedAddition);
            System.out.println("Strength: " + strengthAddition);
            System.out.print("Do you want to (c)ontinue or (r)eselect your attributes? ");
            String response = input.nextLine();
            if (response.equals("c")) {
                canContinue = true;
                this.setHealth(hpAddition);
                this.setSpeed(speedAddition);
                this.setStrength(strengthAddition);
            }
        }
    }

}
