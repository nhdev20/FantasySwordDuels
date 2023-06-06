package fsd;

import java.util.Scanner;

public class FantasySwordDuels {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        FantasySwordDuels fsdApp = new FantasySwordDuels();
        fsdApp.run();
    }

    public void run() {
        userHero hero = new userHero();
        CombatUtils combat = new CombatUtils();

        int completedLevels = 0;

        welcomeAndNameSetting(input, hero);
        rulesExplanation();
        hero.attributeSelection();

        for (int levelCounter = 1; levelCounter <= 10; levelCounter++) {
            hero.setHealth(hero.getBaseHealth());

            System.out.println("**************************************************************");
            System.out.println("************************** Level " + levelCounter + " ***************************");
            System.out.println();

            Enemy enemy = new Enemy(levelCounter);
            enemy.statRandomizer(levelCounter);

            boolean result = combat.combatEncounter(hero, enemy);
            if (result) {
                completedLevels = levelCounter;
                //hero.postLevel(completedLevels);
            } else {
                break;
            }
        }

        if (completedLevels == 10) {
            System.out.println();
            System.out.println("- - - - - -");
            System.out.println("Congratulations!!! You completed ALL 10 levels!!!");
        } else {
            System.out.println();
            System.out.println("- - - - - -");
            System.out.println("You completed " + completedLevels + " of 10 levels. Give it another try!");
        }
    }

    public void welcomeAndNameSetting(Scanner input, userHero hero) {
        System.out.println("Welcome to Fantasy Sword Duels!");
        System.out.print("What is your name? ");
        String name = input.nextLine();
        hero.setName(name);
        System.out.println("Greetings, " + hero.getName() + "!");
    }

    public void rulesExplanation() {
        //here I will explain the combat logic/rules
    }
}
