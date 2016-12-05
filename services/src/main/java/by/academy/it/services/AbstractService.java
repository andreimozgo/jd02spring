package by.academy.it.services;

import by.academy.it.dao.impl.ExtraDaoImpl;
import by.academy.it.entity.AbstractEntity;
import by.academy.it.util.HibernateUtil;

public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {
    protected ExtraDaoImpl optionDao = ExtraDaoImpl.getInstance();
    protected HibernateUtil util = HibernateUtil.getInstance();
}
