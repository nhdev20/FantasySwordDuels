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

        welcomeAndNameSetting(input, hero);

        hero.attributeSelection();

        hero.printOutStats();

        System.out.println("**************************************************************");
        System.out.println("Let's do a practice round for you to understand the mechanics.");
        System.out.println();

        Enemy practiceDummy = new Enemy("Practice Dummy");
        //create stats randomizer function in Enemy class later (based on level)
        practiceDummy.setHealth(5);
        practiceDummy.setSpeed(4);
        practiceDummy.setStrength(3);
        combat.combatEncounter(hero, practiceDummy);
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
