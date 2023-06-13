package fsd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static fsd.util.ConsoleUtility.*;

public class CombatUtils {
// Yoav: make static class?
// Yoav: attack/move classes?

    private String displayCombatOptions = ANSI_CYAN + "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge" + ANSI_RESET;

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
            this.displayCombatOptions = ANSI_CYAN + "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge, (5) " + ANSI_GREEN + "Heal" + ANSI_RESET;
        }
        if (numOfOptions == 6) {
            this.displayCombatOptions = ANSI_CYAN + "(1) Quick Attack, (2) Heavy Attack, (3) Block, (4) Dodge, (5) " + ANSI_GREEN + "Heal" + ANSI_RESET + ", (6) " + ANSI_RED + "Fiery Strike" + ANSI_RESET;
        }
    }

    public boolean combatEncounter(Hero hero, Enemy enemy, Scanner input, User user) {
        System.out.println(ANSI_BOLD + "COMBAT" + ANSI_RESET);
        System.out.println("You are fighting " + ANSI_LIGHT_GRAY + enemy.getName() + ANSI_RESET + "!");

        hero.setHealCount(1);
        hero.setFieryStrikeCount(1);

        while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
            hero.stat();
            enemy.stat();
            int heroCombatOption = selectCombatOption(input, hero);
            roundResolve(hero, enemy, heroCombatOption, enemy.enemyCombatOption(), user);
            System.out.println();
        }

        if (hero.getHealth() <= 0) {
            System.out.println(hero.getName() + " has fallen in battle.");
            return false;
        } else {
            System.out.println(hero.getName() + " has slain " + ANSI_LIGHT_GRAY + enemy.getName() + ANSI_RESET + " and continues on.");
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
                move = 0;
            }
        }
        return move;
    }

    public void roundResolve(Hero hero, Enemy enemy, int heroOption, int enemyOption, User user) {
        System.out.println("Hero option: " + heroOption + " Enemy option: " + enemyOption);
        Map<String, Integer> tracker = user.getCombatOptionDistribution();

        if (heroOption == 1) {
            int newCount = tracker.get("Quick Attack");
            tracker.put("Quick Attack", newCount + 1);
            quickAttack(hero, enemy, enemyOption);
        }
        if (heroOption == 2) {
            int newCount = tracker.get("Heavy Attack");
            tracker.put("Heavy Attack", newCount + 1);
            heavyAttack(hero, enemy, enemyOption);
        }
        if (heroOption == 3) {
            int newCount = tracker.get("Block");
            tracker.put("Block", newCount + 1);
            block(hero, enemy, enemyOption);
        }
        if (heroOption == 4) {
            int newCount = tracker.get("Dodge");
            tracker.put("Dodge", newCount + 1);
            dodge(hero, enemy, enemyOption);
        }
        if (heroOption == 5) {
            int newCount = tracker.get("Heal");
            tracker.put("Heal", newCount + 1);
            heal(hero, enemy);
            hero.setHealCount(0);
        }
        if (heroOption == 6) {
            int newCount = tracker.get("Fiery Strike");
            tracker.put("Fiery Strike", newCount + 1);
            fieryStrike(hero, enemy);
            hero.setFieryStrikeCount(0);
        }
    }

    public void heal(Hero hero, Enemy enemy) {
        hero.setHealth(hero.getBaseHealth());
        System.out.println(hero.getName() + " used " + ANSI_GREEN + "Heal " + ANSI_RESET + "to restore to full health!");
    }

    public void fieryStrike(Hero hero, Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - 10);
        System.out.println(hero.getName() + " uses " + ANSI_RED + "Fiery Strike" + ANSI_RESET + ", damaging " + enemy.getName() + " for 10 damage!");
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
                System.out.println(hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
            }
        }
        if (enemyOption == 3) {
            if (tools.getRandomNumber(100) <= 25) {
                int heroDamageTaken = 0;
                boolean armorUse = false;
                if (hero.damageReductionCheck()) {
                    heroDamageTaken = enemy.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    heroDamageTaken = enemy.getDamageDealt("quick");
                }
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(enemy.getName() + " successfully blocks " + hero.getName() + "'s attack and performs a quick counterstrike for " + heroDamageTaken + "!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
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
                if (tools.getRandomNumber(100) <= 5) {
                    int enemyDamageTaken = hero.getDamageDealt("quick");
                    enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                    System.out.println(enemy.getName() + " attempts to dodge, but trips. " + hero.getName() + " lands a quick attack for " + enemyDamageTaken + " damage!");
                } else {
                    System.out.println(enemy.getName() + " successfully dodges " + hero.getName() + "'s attack.");
                }
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
            System.out.println(hero.getName() + " strikes for " + enemyDamageTaken + " damage. " + enemy.getName() + " strikes for " + heroDamageTaken + " damage.");
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
            if (tools.getRandomNumber(100) <= 5) {
                int enemyDamageTaken = hero.getDamageDealt("heavy");
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(enemy.getName() + " attempts to dodge, but trips. " + hero.getName() + " lands a heavy attack for " + enemyDamageTaken + " damage!");
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
                System.out.println(hero.getName() + " attempts a heavy attack, but " + enemy.getName() + " dodges and lands an opportunistic quick attack for " + heroDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + hero.getName() + "'s armor helped buffer the blow.)");
                }
            }

        }
    }

    public void block(Hero hero, Enemy enemy, int enemyOption) {
        if (enemyOption == 1) {
            if (tools.getRandomNumber(100) <= 25) {
                int enemyDamageTaken = 0;
                boolean armorUse = false;
                if (enemy.damageReductionCheck()) {
                    enemyDamageTaken = hero.getDamageDealt("quick") / 2;
                    armorUse = true;
                } else {
                    enemyDamageTaken = hero.getDamageDealt("quick");
                }
                enemy.setHealth(enemy.getHealth() - enemyDamageTaken);
                System.out.println(hero.getName() + " successfully blocks " + enemy.getName() + "'s attack and performs a quick counterstrike for " + enemyDamageTaken + "!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
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
                if (tools.getRandomNumber(100) <= 5) {
                    int heroDamageTaken = enemy.getDamageDealt("quick");
                    hero.setHealth(hero.getHealth() - heroDamageTaken);
                    System.out.println(hero.getName() + " attempts to dodge, but trips. " + enemy.getName() + " lands a quick attack for " + heroDamageTaken + " damage!");
                } else {
                    System.out.println(hero.getName() + " successfully dodges " + enemy.getName() + "'s attack.");
                }
            }
        }
        if (enemyOption == 2) {
            if (tools.getRandomNumber(100) <= 5) {
                int heroDamageTaken = enemy.getDamageDealt("heavy");
                hero.setHealth(hero.getHealth() - heroDamageTaken);
                System.out.println(hero.getName() + " attempts to dodge, but trips. " + enemy.getName() + " lands a heavy attack for " + heroDamageTaken + " damage!");
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
                System.out.println(enemy.getName() + " attempts a heavy attack, but " + hero.getName() + " dodges and lands an opportunistic quick attack for " + enemyDamageTaken + " damage!");
                if (armorUse) {
                    System.out.println("(" + enemy.getName() + "'s armor helped buffer the blow.)");
                }
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
