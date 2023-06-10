package fsd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombatUtils {
// Yoav: make static class?
// Yoav: attack/move classes?

    private String displayCombatOptions = "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge";

    private int numOfOptions = 4;

    Tools tools = new Tools();

    public String getDisplayCombatOptions() {
        return displayCombatOptions;
    }

    public void setDisplayCombatOption(String displayCombatOptions) {
        this.displayCombatOptions = displayCombatOptions;
    }

    public int getNumOfOptions() {
        return numOfOptions;
    }

    public void setNumOfOptions(int numOfOptions) {
        this.numOfOptions = numOfOptions;
        if (numOfOptions == 5) {
            this.displayCombatOptions = "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge, (5) Heal";
        }
        if (numOfOptions == 6) {
            this.displayCombatOptions = "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge, (5) Heal, (6) Fiery Strike";
        }
    }

    public boolean combatEncounter(Hero hero, Enemy enemy, Scanner input) {
        System.out.println("COMBAT");
        System.out.println("You are fighting " + enemy.getName() + "!");

        hero.setHealCount(1);
        hero.setFieryStrikeCount(1);

        while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
            hero.stat();
            enemy.stat();
            int heroCombatOption = selectCombatOption(input, hero);
            roundResolve(hero, enemy, heroCombatOption, enemy.enemyCombatOption());
        }

        if (hero.getHealth() <= 0) {
            System.out.println(hero.getName() + " has fallen in battle.");
            return false;
        } else {
            System.out.println(hero.getName() + " has slain " + enemy.getName() + " and continues on.");
            return true;
        }

    }
    public int selectCombatOption(Scanner input, Hero hero) {
        System.out.println(getDisplayCombatOptions());
        int move = 0;
        while (move < 1 || move > numOfOptions) {
            System.out.print("Please select your move: ");
            String moveSelection = input.nextLine();
            move = Integer.parseInt(moveSelection);
            if (((move == 5) && (hero.getHealCount() == 0)) || ((move == 6) && (hero.getFieryStrikeCount() == 0))) {
                System.out.println("You already used this skill for this level, please select a different option.");
            }
        }
        return move;
    }

    public void roundResolve(Hero hero, Enemy enemy, int heroOption, int enemyOption) {
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
        if (heroOption == 5) {
            heal(hero, enemy);
            hero.setHealCount(0);
        }
        if (heroOption == 6) {
            fieryStrike(hero, enemy);
            hero.setFieryStrikeCount(0);
        }
    }

    public void heal(Hero hero, Enemy enemy) {
        hero.setHealth(hero.getBaseHealth());
        System.out.println(hero.getName() + "used Heal to restore to full health!");
    }

    public void fieryStrike(Hero hero, Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - 10);
        System.out.println(hero.getName() + " uses Fiery Strike, damaging " + enemy.getName() + " for 10 damage!");
    }

    public void quickAttack(Hero hero, Enemy enemy, int enemyOption) {
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
                System.out.println(enemy.getName() + "'s armor deflects " + hero.getName() + "'s quick attack and " + enemy.getName() + " strikes with a heavy attack for " + heroDamageTaken + " damage!");
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

    public void heavyAttack(Hero hero, Enemy enemy, int enemyOption) {
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
            int enemyDamageTaken = 0;
            boolean armorUse = false;
            if (enemy.damageReductionCheck()) {
                enemyDamageTaken = hero.getDamageDealt("heavy") / 2;
                armorUse = true;
            } else {
                enemyDamageTaken = hero.getDamageDealt("heavy");
            }
            enemyDamageTaken /= 2;
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println(enemy.getName() + " blocks " + hero.getName() + "'s heavy attack, preventing a portion of the damage. " + enemy.getName() + " takes " + enemyDamageTaken + " damage.");
            if (armorUse) {
                System.out.println("(" + enemy.getName() + "'s armor helped further buffer the blow.)");
            }
        }
        if (enemyOption == 4) {
            int heroDamageTaken = 0;
            boolean armorUse = false;
            if (hero.damageReductionCheck()) {
                heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                armorUse = true;
            } else {
                heroDamageTaken = enemy.getDamageDealt("quick");
            }
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            System.out.println(hero.getName() + " attempts a heavy attack, but " + enemy.getName() + " dodges and lands an opportunistic quick attack for " + heroDamageTaken + " damage!");
            if (armorUse) {
                System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
            }
        }
    }

    public void block(Hero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("quick");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to block, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                System.out.println(hero.getName() + " successfully blocks " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            int heroDamageTaken = 0;
            boolean armorUse = false;
            if (hero.damageReductionCheck()) {
                heroDamageTaken = enemy.getDamageDealt("heavy") / 2;
                armorUse = true;
            } else {
                heroDamageTaken = enemy.getDamageDealt("heavy");
            }
            heroDamageTaken /= 2;
            hero.setHealth(hero.getHealth() - heroDamageTaken);
            System.out.println(hero.getName() + " blocks " + enemy.getName() + "'s heavy attack, preventing a portion of the damage. " + hero.getName() + " takes " + heroDamageTaken + " damage.");
            if (armorUse) {
                System.out.println("(" + hero.getName() + "'s armor helped further buffer the blow.)");
            }
        }
        if (enemyOption == 3) {
            System.out.println("Both combatants put up their shields and glower at each other.");
        }
        if (enemyOption == 4) {
            System.out.println("As " + hero.getName() + " moves his shield to block, " + enemy.getName() + " dodges in anticipation of an attack. Menacing stares continue.");
        }
    }

    public void dodge(Hero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (enemy.getSpeed() > hero.getSpeed()) {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("quick");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to dodge, but " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            } else {
                System.out.println(hero.getName() + " successfully dodges " + enemy.getName() + "'s attack.");
            }
        }
        if (enemyOption == 2) {
            int enemyDamageTaken = 0;
            boolean armorUse = false;
            if (enemy.damageReductionCheck()) {
                enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                armorUse = true;
            } else {
                enemyDamageTaken = hero.getDamageDealt("quick");
            }
            enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
            System.out.println(enemy.getName() + " attempts a heavy attack, but " + hero.getName() + " dodges and lands an opportunistic quick attack for " + enemyDamageTaken + " damage!");
            if (armorUse) {
                System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
            }
        }
        if (enemyOption == 3) {
            System.out.println("As " + enemy.getName() + " moves his shield to block, " + hero.getName() + " dodges in anticipation of an attack. Menacing stares continue.");
        }
        if (enemyOption == 4) {
            System.out.println(enemy.getName() + " and " + hero.getName() + " both dodge away from each other. Could they be dance battling?");
        }
    }


}
