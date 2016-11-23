package by.academy.it.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "lowcost")
public class Ticket extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "flight_id")
    private int fligthId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "cost")
    private int cost;
    @Column(name = "paid")
    private int paid;
    @ManyToMany
    @JoinTable(name = "ticket_extra",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id"))
    private Set<Extra> extras = new HashSet<Extra>();

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

    public void setExtras(Set<Extra> extras) {
        this.extras = extras;
    }

    public Set<Extra> getExtras() {
        return extras;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", fligthId=" + fligthId +
                ", userId=" + userId +
                ", cost=" + cost +
                ", paid=" + paid +
                '}';
    }
}

