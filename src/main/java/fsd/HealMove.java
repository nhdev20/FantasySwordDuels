package fsd;

public class HealMove extends SpecialMove{

    public HealMove(String name) {
        super(name);
    }
    @Override
    public void enact(userHero hero) {
        hero.setHealth(hero.getBaseHealth());
    }
}
