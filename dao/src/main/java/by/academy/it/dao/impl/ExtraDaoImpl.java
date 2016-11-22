package by.academy.it.dao.impl;

import by.academy.it.dao.ExtraDao;
import by.academy.it.entity.Extra;
import org.apache.log4j.Logger;

public class ExtraDaoImpl extends BaseDao<Extra> implements ExtraDao {
    final Logger LOG = Logger.getLogger(ExtraDaoImpl.class);
    private static ExtraDaoImpl instance = null;

    private ExtraDaoImpl() {
    }

    public static synchronized ExtraDaoImpl getInstance() {
        if (instance == null) instance = new ExtraDaoImpl();
        return instance;
    }
}
