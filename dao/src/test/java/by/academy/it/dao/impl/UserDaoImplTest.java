package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "txManager", propagation = Propagation.SUPPORTS)
public class UserDaoImplTest extends Assert {

    @Autowired
    private UserDao userDao;

    final Logger LOG = Logger.getLogger(ExtraDaoImpl.class);

    @Test
    public void testCreate() {
        try {
            User user = new User("testLogin", "testPassword");
            userDao.create(user);
            User readUser = userDao.findEntityById(1);
            assertNotNull(readUser);
            assertEquals(user, readUser);
            userDao.delete(1);
        } catch (DaoException e) {
            LOG.error(e);
        }
    }
}
