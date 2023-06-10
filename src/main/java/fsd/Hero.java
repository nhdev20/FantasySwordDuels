package fsd;

import fsd.Character;

import java.util.*;

public class Hero extends Character {
    private int baseHealth;
    private int healCount;
    private int fieryStrikeCount;

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getHealCount() {
        return healCount;
    }

    public void setHealCount(int healCount) {
        this.healCount = healCount;
    }

    public int getFieryStrikeCount() {
        return fieryStrikeCount;
    }

    public void setFieryStrikeCount(int fieryStrikeCount) {
        this.fieryStrikeCount = fieryStrikeCount;
    }

    public void attributeSelection(Scanner input) {
        System.out.println("\n***********ATTRIBUTE SELECTION***********\n");
        System.out.println("HEALTH determines how much damage you can take and continue forward.");
        System.out.println("SPEED determines how quickly you can move out of the way or perform a quick attack.");
        System.out.println("STRENGTH determines your damage and armor protection.");

        boolean canContinue = false;
        while (!canContinue) {
            System.out.println();
            System.out.println("You have 20 attribute points to distribute among three attributes: Health, Speed, and Strength");
            System.out.println("<You must put at least 1 point in each category.>");

            int distributeTotal = 20;

            //HEALTH selection
            boolean inBounds = false;
            int healthAddition = 0;
            String healthAdd = "";

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Health? ");

                while (true) {
                    try {
                        healthAdd = input.nextLine();
                        healthAddition = Integer.parseInt(healthAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + healthAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (healthAddition  <= 0) {
                    System.out.println("You cannot have zero or less as a value for Health.");
                    healthAddition = 0;
                    healthAdd = "";
                    continue;
                }
                if (distributeTotal - healthAddition <= 2) {
                    System.out.println("You overspent your distribution points. Remember, you need at least one point for each attribute.");
                    healthAddition = 0;
                    healthAdd = "";
                    continue;
                }
                distributeTotal -= healthAddition;
                inBounds = true;
            }

            //SPEED selection
            inBounds = false;
            int speedAddition = 0;
            String speedAdd = "";

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Speed? ");

                while (true) {
                    try {
                        speedAdd = input.nextLine();
                        speedAddition = Integer.parseInt(speedAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + speedAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (speedAddition  < 0) {
                    System.out.println("You cannot have a negative value for Speed.");
                    speedAddition = 0;
                    speedAdd = "";
                    continue;
                }
                if (distributeTotal - speedAddition <= 1) {
                    System.out.println("You overspent your distribution points. Remember, you need at least one point for each attribute.");
                    speedAddition = 0;
                    speedAdd = "";
                    continue;
                }
                distributeTotal -= speedAddition;
                inBounds = true;
            }

            //STRENGTH selection
            inBounds = false;
            int strengthAddition = 0;
            String strengthAdd = "";

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Strength? ");

                while (true) {
                    try {
                        strengthAdd = input.nextLine();
                        strengthAddition = Integer.parseInt(strengthAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + strengthAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (strengthAddition  < 0) {
                    System.out.println("You cannot have a negative value for Strength.");
                    strengthAddition = 0;
                    strengthAdd = "";
                    continue;
                }
                if (distributeTotal - strengthAddition < 0) {
                    System.out.println("You overspent your distribution points.");
                    strengthAddition = 0;
                    strengthAdd = "";
                    continue;
                }
                distributeTotal -= strengthAddition;
                inBounds = true;
            }

            //FINAL CHECK of attributes
            System.out.println("\n" + this.getName() + ", you have opted to disseminate your points as follows:");
            System.out.println("Health: " + healthAddition);
            System.out.println("Speed: " + speedAddition);
            System.out.println("Strength: " + strengthAddition);
            if (distributeTotal > 0) {
                System.out.println("You have " + distributeTotal + " unspent points.");
            }
            System.out.print("\nDo you want to (c)ontinue or (r)edistribute your attribute points? ");

            String response = "";
            while (true) {
                response = input.nextLine();
                if (response.equals("c") || response.equals("r")) {
                    break;
                }
                System.out.println("\'" + response + "\' is not (c) or (r) - please select one to continue.");
            }

            if (response.equals("c")) {
                canContinue = true;
                this.setHealth(healthAddition);
                this.baseHealth = healthAddition;
                this.setSpeed(speedAddition);
                this.setStrength(strengthAddition);
            }
        }
    }

    public int postLevelUpgrade(int completedLevel, Scanner input) {
        int numOfCombatOptions = 4;
        if (completedLevel == 2) {
            System.out.println("\nYou have grown through your victories, earning 5 additional attribute points to distribute!");
            System.out.println("--------------------------------");
            additionalPointsUpgrade(input);
        }
        if (completedLevel == 4) {
            System.out.println("\nThe local Forge Master has crafted a new sword for you, which increases your Speed by 2 and your Strength by 3!\n");
            setSpeed(getSpeed() + 2);
            setStrength(getStrength() + 3);
        }
        if (completedLevel == 6) {
            System.out.println("\nYou have learned a Special Move called Heal, which allows you to heal yourself to full capacity once per level!\n");
            numOfCombatOptions = 5;
        }
        if (completedLevel == 8) {
            System.out.println("\nYou have learned a Special Move called Fiery Strike. As you prepare to attack, you mutter an incantation which sets your blade on fire! Your attack automatically causes 10 damage to your enemy! " +
                    "It can be used once per turn\n");
            numOfCombatOptions = 6;
        }
        return numOfCombatOptions;
    }

    public void additionalPointsUpgrade(Scanner input) {
        boolean canContinue = false;
        while (!canContinue) {
            int distributeTotal = 5;

            //HEALTH selection
            boolean inBounds = false;
            int healthAddition = 0;
            String healthAdd = "";

            while (!inBounds) {
                System.out.print("How many points would you like to place in Health? ");

                while (true) {
                    try {
                        healthAdd = input.nextLine();
                        healthAddition = Integer.parseInt(healthAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + healthAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (healthAddition  < 0) {
                    System.out.println("You cannot reduce your Health.");
                    healthAddition = 0;
                    healthAdd = "";
                    continue;
                }
                if (distributeTotal - healthAddition < 0) {
                    System.out.println("You overspent your distribution points. Please try again.");
                    healthAddition = 0;
                    healthAdd = "";
                    continue;
                }
                distributeTotal -= healthAddition;
                inBounds = true;
            }

            //SPEED selection
            inBounds = false;
            int speedAddition = 0;
            String speedAdd = "";

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Speed? ");

                while (true) {
                    try {
                        speedAdd = input.nextLine();
                        speedAddition = Integer.parseInt(speedAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + speedAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (speedAddition  < 0) {
                    System.out.println("You cannot reduce your Speed.");
                    speedAddition = 0;
                    speedAdd = "";
                    continue;
                }
                if (distributeTotal - speedAddition < 0) {
                    System.out.println("You overspent your distribution points. Please try again.");
                    speedAddition = 0;
                    speedAdd = "";
                    continue;
                }
                distributeTotal -= speedAddition;
                inBounds = true;
            }

            //STRENGTH selection
            inBounds = false;
            int strengthAddition = 0;
            String strengthAdd = "";

            while (!inBounds) {
                System.out.println("You have " + distributeTotal + " distribution points remaining.");
                System.out.print("How many points would you like to place in Strength? ");

                while (true) {
                    try {
                        strengthAdd = input.nextLine();
                        strengthAddition = Integer.parseInt(strengthAdd);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\'" + strengthAdd + "\' is not a number - please enter a number.");
                    }
                }

                if (strengthAddition  < 0) {
                    System.out.println("You cannot reduce your Strength.");
                    strengthAddition = 0;
                    strengthAdd = "";
                    continue;
                }
                if (distributeTotal - strengthAddition < 0) {
                    System.out.println("You overspent your distribution points.");
                    strengthAddition = 0;
                    strengthAdd = "";
                    continue;
                }
                distributeTotal -= strengthAddition;
                inBounds = true;
            }

            //FINAL CHECK of attributes
            System.out.println("\nYou have opted to disseminate your points as follows:");
            System.out.println("Health: +" + healthAddition);
            System.out.println("Speed: +" + speedAddition);
            System.out.println("Strength: +" + strengthAddition);
            if (distributeTotal > 0) {
                System.out.println("You have " + distributeTotal + " unspent points.");
            }
            System.out.print("\nDo you want to (c)ontinue or (r)edistribute your attribute points? ");

            String response = "";
            while (true) {
                response = input.nextLine();
                if (response.equals("c") || response.equals("r")) {
                    break;
                }
                System.out.println("\'" + response + "\' is not (c) or (r) - please select one to continue.");
            }

            if (response.equals("c")) {
                canContinue = true;
                baseHealth += healthAddition;
                setSpeed(getSpeed() + speedAddition);
                setStrength(getStrength() + strengthAddition);
            }
        }

    }
}
