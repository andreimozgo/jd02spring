package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/test-service-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServicesImplTest extends Assert {

    @Autowired
    private FlightService flightService;


    @Test
    public void testCreate() throws DaoException {
        Flight flight = new Flight(1, "2016-01-01", 20, 10, (byte) 1);
        flightService.createOrUpdate(flight);
       // Flight readFlight = flightService.findEntityById(1);
      //  assertNotNull(readFlight);
      //  assertEquals(flight, readFlight);
    }
}
