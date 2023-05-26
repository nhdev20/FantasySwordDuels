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
            heavyAttack(hero, enemy, enemyOption);
        }
        if (heroOption == 3) {
            block(hero, enemy, enemyOption);
        }
        if (heroOption == 4) {
            dodge(hero, enemy, enemyOption);
        }
    }

    public void quickAttack(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                enemy.setHealth(enemy.getHealth() - (int)(hero.getStrength() * .25));
                System.out.println(hero.getName() + " lands a quick attack for " + (int)(hero.getStrength() * .25) + " damage!");
//                System.out.println(enemy.getName() + "'s health is " + enemy.getHealth() + ".");
            } else if (enemy.getSpeed() > hero.getSpeed()) {
                hero.setHealth(hero.getHealth() - (int)(enemy.getStrength() * .25));
                System.out.println(enemy.getName() + " lands a quick attack for " + (int)(enemy.getStrength() * .25) + " damage!");
//                System.out.println(hero.getName() + "'s health is " + enemy.getHealth() + ".");
            } else {
                System.out.println("Both combatants attempt a quick and parry each other's blow.");
            }
        }
        if (enemyOption == 2) {
            if ()
        }
        if (enemyOption == 3) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                enemy.setHealth(enemy.getHealth() - (int)(hero.getStrength() * .25));
                System.out.println(enemy.getName() + " attempts to block, but " + hero.getName() + " lands a quick attack for " + (int)(hero.getStrength() * .25) + " damage!");
            } else {
                System.out.println(enemy.getName() + "Successfully blocks " + hero.getName() + "'s attack.");
            }
        }
        if (enemyOption == 4) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                enemy.setHealth(enemy.getHealth() - (int)(hero.getStrength() * .25));
                System.out.println(enemy.getName() + " attempts to dodge, but " + hero.getName() + " lands a quick attack for " + (int)(hero.getStrength() * .25) + " damage!");
            } else {
                System.out.println(enemy.getName() + "Successfully dodges " + hero.getName() + "'s attack.");
            }
        }

    }

    public void heavyAttack(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {

        }
        if (enemyOption == 2) {
            int heroDamageTaken = (int) (enemy.getStrength() * (1 - hero.damageReductionPercentage()));
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            int enemyDamageTaken = (int) (hero.getStrength() * (1 - enemy.damageReductionPercentage()));
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println("Both combatants use a heavy attack. " + hero.getName() + " hits for " + enemyDamageTaken + ". " + enemy.getName() + " hits for " + heroDamageTaken + ". ");
        }
        if (enemyOption == 3) {
            System.out.println(enemy.getName() + " blocks " + hero.getName() + "'s heavy attack.");
        }
        if (enemyOption == 4) {
            hero.setHealth(hero.getHealth() - (int)(enemy.getStrength() * .25));
            System.out.println(hero.getName() + " attempts a heavy attack, but " + enemy.getName() + " dodges and lands an opportunistic quick attack!");
        }
    }

    public void block(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {

        }
        if (enemyOption == 2) {

        }
        if (enemyOption == 3) {

        }
        if (enemyOption == 4) {

        }
    }

    public void dodge(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
           
        }
        if (enemyOption == 2) {

        }
        if (enemyOption == 3) {

        }
        if (enemyOption == 4) {

        }
    }
}
