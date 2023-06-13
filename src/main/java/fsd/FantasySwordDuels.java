package fsd;

import fsd.util.ConsoleUtility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static fsd.util.ConsoleUtility.*;

public class FantasySwordDuels {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
//        ConsoleUtility.demoAll();
        FantasySwordDuels fsdApp = new FantasySwordDuels();
        fsdApp.run();
    }

    public void run() {
        Hero hero = new Hero();
        CombatUtils combat = new CombatUtils();
        User user = new User();
        setUpCombatTracking(user);

        int completedLevels = 0;

        welcomeAndNameSetting(input, hero);

        System.out.println(ANSI_UNDERLINE + "\nHow Combat Resolves:" + ANSI_RESET);
        rulesExplanation();
        hero.attributeSelection(input);

        for (int levelCounter = 1; levelCounter <= 10; levelCounter++) {
            hero.setHealth(hero.getBaseHealth());

            System.out.println();
            System.out.println(ANSI_LIGHT_GREEN + "************************** Level " + levelCounter + " ***************************");
            System.out.println(ANSI_RESET);

            Enemy enemy = new Enemy(levelCounter);
            enemy.statRandomizer(levelCounter);

            boolean result = combat.combatEncounter(hero, enemy, input, user);

            if (result) {
                completedLevels = levelCounter;
                int updatedNumOfCombatOptions = hero.postLevelUpgrade(completedLevels, input);
                combat.setNumOfOptions(updatedNumOfCombatOptions);
            } else {
                break;
            }
        }

        if (completedLevels == 10) {
            System.out.println();
            System.out.println("- - - - - -");
            System.out.println(ANSI_GREEN + "Congratulations!!! " + ANSI_BLUE + "You completed " + ANSI_BOLD + ANSI_WHITE + "ALL " + ANSI_RESET + ANSI_BLUE + "10 levels!!!");
        } else {
            System.out.println();
            System.out.println("- - - - - -");
            System.out.println("You completed " + completedLevels + " of 10 levels. Give it another try!");
        }

        requestGamerTag(user);
        user.setHighestLevelCompleted(completedLevels);

        LocalDateTime now = LocalDateTime.now();
        user.setEndOfPlay(now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = now.format(formatter);
        user.setTimeAndDateAtEndOfPlay(formattedDate);

        Map<String, Integer> attributeDistribution = new HashMap<>();
        attributeDistribution.put("Health", hero.getBaseHealth());
        attributeDistribution.put("Speed", hero.getSpeed());
        attributeDistribution.put("Strength", hero.getStrength());
        user.setAttributeDistribution(attributeDistribution);

        System.out.println(user.toString());
    }

    public void welcomeAndNameSetting(Scanner input, Hero hero) {
        System.out.print(ANSI_BLUE);
        System.out.println("Welcome to Fantasy Sword Duels!");
        System.out.print(ANSI_RESET);
        System.out.print("What is your name? ");
        String name = "";
        while (true) {
            name = input.nextLine();
            if (name != null) {
                break;
            }
            System.out.println("You did not enter anything, please enter your name.");
        }
        hero.setName(name);
        System.out.println("Greetings, " + hero.getName() + "!");
    }

    public void rulesExplanation() {
        System.out.println(ANSI_BOLD + "Quick Attack vs Quick Attack" + ANSI_RESET + " - the higher " + ANSI_LIGHT_GREEN + "Speed" + ANSI_RESET + " value lands the attack.");
        System.out.println(ANSI_BOLD + "Quick Attack vs Heavy Attack" + ANSI_RESET + " - Quick Attack usually wins, but there is a chance, which increases based on "
                + ANSI_MAGENTA + "Strength" + ANSI_RESET + ", that the armor will deflect the Quick Attack and the Heavy Attack will then land.");
        System.out.println(ANSI_BOLD + "Quick Attack vs Block" + ANSI_RESET + " - Block stops Quick Attack, and there is a chance that a counter Quick Attack might occur.");
        System.out.println(ANSI_BOLD + "Quick Attack vs Dodge" + ANSI_RESET + " - if " + ANSI_LIGHT_GREEN + "Speed" + ANSI_RESET + " of the Quick Attack is higher, the attack lands.");
        System.out.println();
        System.out.println(ANSI_BOLD + "Heavy Attack vs Heavy Attack" + ANSI_RESET + " - both attacks land.");
        System.out.println(ANSI_BOLD + "Heavy Attack vs Block" + ANSI_RESET + " - Block reduces the damage of Heavy Attack.");
        System.out.println(ANSI_BOLD + "Heavy Attack vs Dodge" + ANSI_RESET + " - the Heavy Attack misses and a counter Quick Attack occurs.");
        System.out.println();
        System.out.println("With " + ANSI_BOLD + "Dodge" + ANSI_RESET + ", there is a small chance that the character might trip. If this occurs and the opponent performed a Quick or Heavy attack, " +
                "the attack lands for full damage.");
        System.out.println();
        System.out.println(ANSI_LIGHT_GRAY + "Armor" + ANSI_RESET + " is calculated based on the " + ANSI_MAGENTA + "Strength" + ANSI_RESET + " attribute." + ANSI_LIGHT_GRAY
                + " Armor" + ANSI_RESET + " provides a chance that incoming damage will be reduced or prevented.");
        System.out.println(ANSI_MAGENTA + "Strength" + ANSI_RESET + " is the attribute used in damage calculations.");
    }

    public void requestGamerTag(User user) {
        System.out.print("\nWould you like your score (highest level completed) to be added to our public scoreboard? (y)es or (n)o: ");
        String yesOrNo = "";
        while (true) {
            yesOrNo = input.nextLine();
            if (yesOrNo.equals("y") || yesOrNo.equals("n")) {
                break;
            }
            System.out.print("Please enter 'y' for yes or 'n' for no: ");
        }

        String gamerTag = "";
        if (yesOrNo.equals("y")) {
            while (true) {
                System.out.print("Please enter the name you would like to be displayed: ");
                gamerTag = input.nextLine();
                if (gamerTag.length() >= 3 && gamerTag.length() <= 16) {
                    break;
                }
                System.out.println("Your name must be between 3-16 characters in length.");
            }
        }
        user.setGamerTag(gamerTag);
    }

    public void setUpCombatTracking(User user) {
        Map<String, Integer> combatOptionDistribution = new HashMap<>();

        combatOptionDistribution.put("Quick Attack", 0);
        combatOptionDistribution.put("Heavy Attack", 0);
        combatOptionDistribution.put("Block", 0);
        combatOptionDistribution.put("Dodge", 0);
        combatOptionDistribution.put("Heal", 0);
        combatOptionDistribution.put("Fiery Strike", 0);

        user.setCombatOptionDistribution(combatOptionDistribution);
    }
}
