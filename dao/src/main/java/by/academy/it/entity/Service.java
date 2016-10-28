package by.academy.it.entity;

public class Service extends Entity {
    private String name;
    private int cost;

    public Service() {
    }

    public Service(String name, int cost) {

        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}

