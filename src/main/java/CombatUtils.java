import java.util.Scanner;

public class CombatUtils {
    public int selectCombatOption(Scanner input) {
        System.out.println("(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge");
        int move = 0;
        while (move < 1 && move > 4) {
            System.out.print("Please select your move: ");
            String moveSelection = input.nextLine();
            move = Integer.parseInt(moveSelection);
        }

        return move;
    }

//    public void roundResolve(userHero hero, Enemy enemy, int heroAttack, int enemyAttack) {
//        if (heroAttack == 1) {
//            quickAttack(hero, enemy, enemyAttack);
//        }
//        if (heroAttack == 2) {
//
//        }
//        if (heroAttack == 3) {
//
//        }
//        if (heroAttack == 4) {
//
//        }
//    }

//    public void quickAttack(userHero hero, Enemy enemy, int enemyAttack) {
//        if (enemyAttack == 1) {
//            if (hero.speed > enemy.speed) {
//                enemy.health -= (hero.strength * .25);
//                System.out.println("Hero lands a quick attack for " + (int)(hero.strength * .25) + " damage!");
//                System.out.println(enemy.name + " health is " + enemy.health + ".");
//            } else if (enemy.speed > hero.speed) {
//
//            } else {
//                //equal
//            }
//        }
//
//    }
}
