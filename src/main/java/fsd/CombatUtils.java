package fsd;

import java.util.Scanner;

public class CombatUtils {
//static?
    //attack/move classes
    helpfulTools tools = new helpfulTools();
    Scanner input = new Scanner(System.in);

    public boolean combatEncounter(userHero hero, Enemy enemy) {
        System.out.println("COMBAT");
        System.out.println("You are fighting " + enemy.getName() + "!");

        while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
            hero.stat();
            enemy.stat();
            roundResolve(hero, enemy, selectCombatOption(), enemy.enemyCombatOption());
        }

        if (hero.getHealth() <= 0) {
            System.out.println(hero.getName() + " has fallen in battle.");
            return false;
        } else {
            System.out.println(hero.getName() + " has slain " + enemy.getName() + " and continues on.");
            return true;
        }

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
        System.out.println("Hero option: " + heroOption + " Enemy option: " + enemyOption);

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
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("quick");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            } else if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("quick");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                System.out.println("Both combatants attempt a quick attack and parry each other's blow.");
            }
        }
        if (enemyOption == 2) {
            int enemyChanceToIgnore = enemy.getArmor() * 5;
            boolean doesEnemyIgnore = false;
            if (tools.getRandomNumber(100) <= enemyChanceToIgnore) {
                doesEnemyIgnore = true;
            }
            if (doesEnemyIgnore) {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("heavy") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("heavy");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + "'s armor deflects " + hero.getName() + "'s quick attack and strikes with a heavy attack for " + heroDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("quick");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + " lands a quick attack for " + enemyDamageTaken + "!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            }
        }
        if (enemyOption == 3) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("quick");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(enemy.getName() + " attempts to block, but " + hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                System.out.println(enemy.getName() + " successfully blocks " + hero.getName() + "'s attack.");
            }
        }
        if (enemyOption == 4) {
            if (hero.getSpeed() > enemy.getSpeed()) {
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("quick");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(enemy.getName() + " attempts to dodge, but " + hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                System.out.println(enemy.getName() + " successfully dodges " + hero.getName() + "'s attack.");
            }
        }
    }

    public void heavyAttack(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            int heroChanceToIgnore = hero.getArmor() * 5;
            boolean heroIgnore = false;
            if (tools.getRandomNumber(100) <= heroChanceToIgnore) {
                heroIgnore = true;
            }
            if (heroIgnore) {
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("heavy") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("heavy");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + "'s armor deflects " + enemy.getName() + "'s quick attack and strikes with a heavy attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("quick");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + " lands a quick attack for " + heroDamageTaken + "!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            }
        }
        if (enemyOption == 2) {
            System.out.println("Both combatants use a heavy attack.");
            //Enemy takes damage
            int enemyDamageTaken = 0;
            boolean armorUse = false;
            if (enemy.damageReductionCheck()) {
                enemyDamageTaken = hero.getDamageDealt("heavy") / 2;
                armorUse = true;
            } else {
                enemyDamageTaken = hero.getDamageDealt("heavy");
            }
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            if (armorUse) {
                System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
            }
            //Hero takes damage
            int heroDamageTaken = 0;
            armorUse = false;
            if (hero.damageReductionCheck()) {
                heroDamageTaken = enemy.getDamageDealt("heavy") / 2;
                armorUse = true;
            } else {
                heroDamageTaken = enemy.getDamageDealt("heavy");
            }
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            if (armorUse) {
                System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
            }
            //Combined info output message
            System.out.println(hero.getName() + " hits for " + enemyDamageTaken + ". " + enemy.getName() + " hits for " + heroDamageTaken + ". ");
        }
        if (enemyOption == 3) {
            int enemyDamageTaken = (hero.getDamageDealt("heavy")) / 2;
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println(enemy.getName() + " blocks " + hero.getName() + "'s heavy attack, preventing a portion of the damage. " + enemy.getName() + " takes " + enemyDamageTaken + " damage.");
        }
        if (enemyOption == 4) {
            int heroDamageTaken = enemy.getDamageDealt("quick");
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            System.out.println(hero.getName() + " attempts a heavy attack, but " + enemy.getName() + " dodges and lands an opportunistic quick attack for " + heroDamageTaken + " damage!");
        }
    }

    public void block(userHero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = enemy.getDamageDealt("quick");
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to block, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
            } else {
                System.out.println(hero.getName() + " successfully blocks " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            int heroDamageTaken = (enemy.getDamageDealt("heavy")) / 2;
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            System.out.println(hero.getName() + " blocks " + enemy.getName() + "'s heavy attack, preventing a portion of the damage. " + hero.getName() + " takes " + heroDamageTaken + " damage.");
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
                int heroDamageTaken = enemy.getDamageDealt("quick");
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to dodge, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
            } else {
                System.out.println(hero.getName() + " successfully dodges " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            int enemyDamageTaken = hero.getDamageDealt("quick");
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println(enemy.getName() + " attempts a heavy attack, but " + hero.getName() + " dodges and lands an opportunistic quick attack for " + enemyDamageTaken + " damage!");
        }
        if (enemyOption == 3) {
            System.out.println("As " + enemy.getName() + " moves his shield to block, " + hero.getName() + " dodges in anticipation of an attack. Menacing stares continue.");
        }
        if (enemyOption == 4) {
            System.out.println(enemy.getName() + " and " + hero.getName() + " both dodge away from each other. Could they be dance battling?");
        }
    }
}
