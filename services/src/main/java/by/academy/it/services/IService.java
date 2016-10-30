package by.academy.it.services;

public interface IService<Entity> {

    void create(Entity entity);

    Entity findEntityById(Integer id);

    void update(Entity entity);

    void delete(Integer id);
}
