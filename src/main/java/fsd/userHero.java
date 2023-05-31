package fsd;

import fsd.Character;

import java.util.Scanner;

public class userHero extends Character {

    Scanner input = new Scanner(System.in);

    private int baseHealth;

    public int getBaseHealth() {
        return baseHealth;
    }

    public void attributeSelection() {
        System.out.println("\n***********ATTRIBUTE SELECTION***********\n");
        boolean canContinue = false;
        while (!canContinue) {
            System.out.println("You have 15 attribute points to distribute among three attributes: Health, Speed, and Strength");
            System.out.println("Health determines how much damage you can take and continue forward.");
            System.out.println("Speed determines how quickly you can move out of the way or perform a quick attack.");
            System.out.println("Strength determines your damage and armor protection.");
            System.out.println("\n<You must put at least one point in each category.>\n");

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

            System.out.println("\n" + this.getName() + ", you have opted to disseminate your points as follows:");
            System.out.println("Health: " + hpAddition);
            System.out.println("Speed: " + speedAddition);
            System.out.println("Strength: " + strengthAddition);
            System.out.print("\nDo you want to (c)ontinue or (r)eselect your attributes? ");
            String response = input.nextLine();
            if (response.equals("c")) {
                canContinue = true;
                this.setHealth(hpAddition);
                this.baseHealth = hpAddition;
                this.setSpeed(speedAddition);
                this.setStrength(strengthAddition);
            }
        }
    }

}
