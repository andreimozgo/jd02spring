package by.academy.it.dao.impl;

import by.academy.it.entity.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketDaoImplTest {
    private Ticket expected;

    @Before
    public void setUp() {
        expected = TicketDaoImpl.getInstance().findEntityById(1);
    }

    @After
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testTicketDaoImpl() throws Exception {
        TicketDaoImpl instance1 = TicketDaoImpl.getInstance();
        TicketDaoImpl instance2 = TicketDaoImpl.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
        expected = TicketDaoImpl.getInstance().findEntityById(1);
        assertEquals("Table Ticket not accessible", 1, expected.getFligthId());
    }
}