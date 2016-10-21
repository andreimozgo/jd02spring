package by.academy.it.dao.Entity;

/**
 * Created by Андрей on 19.10.2016.
 */
import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
