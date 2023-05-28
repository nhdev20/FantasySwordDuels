public class Enemy extends Character{
    helpfulTools tools = new helpfulTools();

    private String name;
    public Enemy(String name) {
        this.setName(name);
    }

    public int enemyCombatOption() {
        return tools.getRandomNumber(4);
    }
}
