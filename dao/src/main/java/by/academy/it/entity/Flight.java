package by.academy.it.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "lowcost")
public class Flight extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "date")
    private String date;
    @Column(name = "seats")
    private int seats;
    @Column(name = "cost")
    private int cost;
    @Column(name = "up_cost")
    private byte upCost;

    public Flight() {
    }

    public Flight(Integer id, String date, int seats, int cost, byte upCost) {

        this.id = id;
        this.date = date;
        this.seats = seats;
        this.cost = cost;
        this.upCost = upCost;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public int getCost() {
        return cost;
    }

    public byte getUpCost() {
        return upCost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUpCost(byte upCost) {
        this.upCost = upCost;
    }

    public boolean equals(Object obj) {
        return false;
    }
}

