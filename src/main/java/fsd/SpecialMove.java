package fsd;

abstract class SpecialMove {
    private String name;

    private String description;

    public SpecialMove(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void enact(userHero hero); //needs specific code per special move
}
