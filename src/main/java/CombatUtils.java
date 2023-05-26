import java.util.Scanner;

public class CombatUtils {

    helpfulTools tools = new helpfulTools();
    Scanner input = new Scanner(System.in);
    public void combatEncounter(userHero hero, Enemy enemy) {
        System.out.println("COMBAT");
        System.out.println("You have " + hero.getHealth() + "HP");
        System.out.println("You are fighting a " + enemy.getName() + "!");
//
//        while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
//
//        }
        roundResolve(hero, enemy, selectCombatOption(), enemy.enemyCombatOption());

    }
    public int selectCombatOption() {
        System.out.println("(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge");
        int move = 0;
        while (move < 1 || move > 4) {
            System.out.print("Please select your move: ");
            String moveSelection = input.nextLine();
            move = Integer.parseInt(moveSelection);
        }
        return move;
    }

    public void roundResolve(userHero hero, Enemy enemy, int heroOption, int enemyOption) {

        if (heroOption == 1) {
            quickAttack(hero, enemy, enemyOption);
        }
        if (heroOption == 2) {

        }
        if (heroOption == 3) {

        }
        if (heroOption == 4) {

        }
    }

    public void quickAttack(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                enemy.setHealth(enemy.getHealth() - (int)(hero.getStrength() * .25));
                System.out.println("Hero lands a quick attack for " + (int)(hero.getStrength() * .25) + " damage!");
//                System.out.println(enemy.getName() + " health is " + enemy.getHealth() + ".");
            } else if (enemy.getSpeed() > hero.getSpeed()) {

            } else {
                //equal
            }
        }
        if (enemyOption == 2) {

        }
        if (enemyOption == 3) {

        }
        if (enemyOption == 4) {

        }

    }
}
