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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
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
        String name = input.nextLine();
        hero.setName(name);
        System.out.println("Greetings, " + hero.getName() + "!");
    }

    public void rulesExplanation() {
        //here I will explain the combat logic/rules
        //use ANSI
    }

    public void requestGamerTag(User user) {
        System.out.print("Would you like your score (highest level completed) to be added to our public scoreboard? (y)es or (n)o: ");
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
