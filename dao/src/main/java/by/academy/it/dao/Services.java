package by.academy.it.dao;

public class Services extends Entity {
    private String name;
    private int cost;

    public Services() {
    }

    public Services(String name, int cost) {

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

