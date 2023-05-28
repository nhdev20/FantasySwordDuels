import java.util.Scanner;

public class CombatUtils {

    helpfulTools tools = new helpfulTools();
    Scanner input = new Scanner(System.in);

    private final double HEAVY_ATTACK_STR_PERC_DAM = .5;
    private final double QUICK_ATTACK_STR_PERC_DAM = .25;

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
                int enemyDamageTaken = (int)(hero.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
            } else if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = (int)(enemy.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
            } else {
                System.out.println("Both combatants attempt a quick and parry each other's blow.");
            }
        }
        if (enemyOption == 2) {
            int enemyChanceToIgnore = enemy.getArmor() * 5;
            boolean doesEnemyIgnore = false;
            if (tools.getRandomNumber(100) <= enemyChanceToIgnore) {
                doesEnemyIgnore = true;
            }
            if (doesEnemyIgnore) {
                int heroDamageTaken = (int) (enemy.getStrength() * HEAVY_ATTACK_STR_PERC_DAM);
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + "'s armor deflects " + hero.getName() + "'s quick attack and strikes with a heavy attack for " + heroDamageTaken + "damage!");
            } else {
                int enemyDamageTaken = (int)(hero.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + " lands a quick attack for " + enemyDamageTaken + "!");
            }
        }
        if (enemyOption == 3) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                int enemyDamageTaken = (int)(hero.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(enemy.getName() + " attempts to block, but " + hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
            } else {
                System.out.println(enemy.getName() + " successfully blocks " + hero.getName() + "'s attack.");
            }
        }
        if (enemyOption == 4) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                int enemyDamageTaken = (int)(hero.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(enemy.getName() + " attempts to dodge, but " + hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
            } else {
                System.out.println(enemy.getName() + "Successfully dodges " + hero.getName() + "'s attack.");
            }
        }

    }

    public void heavyAttack(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            int heroChanceToIgnore = hero.getArmor() * 5;
            boolean doesHeroIgnore = false;
            if (tools.getRandomNumber(100) <= heroChanceToIgnore) {
                doesHeroIgnore = true;
            }
            if (doesHeroIgnore) {
                int enemyDamageTaken = (int) (hero.getStrength() * HEAVY_ATTACK_STR_PERC_DAM);
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + "'s armor deflects " + enemy.getName() + "'s quick attack and strikes with a heavy attack for " + enemyDamageTaken + "damage!");
            } else {
                int heroDamageTaken = (int)(enemy.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + " lands a quick attack for " + heroDamageTaken + "!");
            }
        }
        if (enemyOption == 2) {
            int heroDamageTaken = (int) (enemy.getStrength() * HEAVY_ATTACK_STR_PERC_DAM);
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            int enemyDamageTaken = (int) (hero.getStrength() * HEAVY_ATTACK_STR_PERC_DAM);
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println("Both combatants use a heavy attack. " + hero.getName() + " hits for " + enemyDamageTaken + ". " + enemy.getName() + " hits for " + heroDamageTaken + ". ");
        }
        if (enemyOption == 3) {
            System.out.println(enemy.getName() + " blocks " + hero.getName() + "'s heavy attack.");
        }
        if (enemyOption == 4) {
            int heroDamageTaken = (int)(enemy.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            System.out.println(hero.getName() + " attempts a heavy attack, but " + enemy.getName() + " dodges and lands an opportunistic quick attack for " + heroDamageTaken + "damage!");
        }
    }

    public void block(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = (int)(enemy.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to block, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
            } else {
                System.out.println(hero.getName() + " successfully blocks " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            System.out.println(hero.getName() + " blocks " + enemy.getName() + "'s heavy attack.");
        }
        if (enemyOption == 3) {
            System.out.println("Both combatants put up their shields and glower at each other.");
        }
        if (enemyOption == 4) {
            System.out.println("As " + hero.getName() + " moves his shield to block, " + enemy.getName() + " dodges in anticipation of an attack. Menacing stares continue.");
        }
    }

    public void dodge(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = (int)(enemy.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to dodge, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
            } else {
                System.out.println(hero.getName() + " successfully dodges " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            int enemyDamageTaken = (int)(hero.getStrength() * QUICK_ATTACK_STR_PERC_DAM);
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println(enemy.getName() + " attempts a heavy attack, but " + hero.getName() + " dodges and lands an opportunistic quick attack for " + enemyDamageTaken + "damage!");
        }
        if (enemyOption == 3) {

        }
        if (enemyOption == 4) {

        }
    }
}
