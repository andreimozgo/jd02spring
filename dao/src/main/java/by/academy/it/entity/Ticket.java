package by.academy.it.entity;

public class Ticket extends AbstractEntity {
    private int id;
    private int fligthId;
    private int userId;
    private int cost;
    private int paid;

    public Ticket() {

    }

    public Ticket(int id, int flightId, int userId, int cost, int paid) {
        this.id = id;
        this.fligthId = flightId;
        this.userId = userId;
        this.cost = cost;
        this.paid = paid;
    }

    public int getFligthId() {
        return fligthId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFligthId(int fligthId) {
        this.fligthId = fligthId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

