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


//        /*
//        display user's attributes (health, etc.) and enemy info
//        display action options
//            block
//                neutralizes quick jab, half damage from heavy strike
//            quick jab
//                produces small damage
//                speed attribute breaks tie
//                neutralizes a heavy strike
//            heavy strike
//                produces large damage
//                strength attribute breaks tie (but both selecting heavy strike will only grant half damage to winner)
//            dodge
//                against heavy attack avoids damage and automatically uses quick jab as a counterattack
//                against quick jab - evade if faster or take quick jab damage if slower
//
//            enemy actions randomized to start (build in AI later)
//            enemy stats randomized within contours
//         */
//    }





//    public static void combat(Scanner input, userHero hero, Enemy enemy) {
//        CombatUtils combat = new CombatUtils();
//        helpfulTools tools = new helpfulTools();
//
//        System.out.println("You are entering combat against " + enemy.name + ".");
//        System.out.println(hero.printOutStats());
//
//        int heroMove = combat.selectCombatOption(input);
//        int enemyMove = tools.getRandomNumber(4);
//
//        combat.roundResolve(hero, enemy, heroMove, enemyMove);
//
//        if (hero.health <= 0) {
//            //sout game over
//        }
//        if (enemy.health <= 0) {
//            //sout
//
//
//        }
//        //add loops later, for now, just practice dummy
//    }


}
