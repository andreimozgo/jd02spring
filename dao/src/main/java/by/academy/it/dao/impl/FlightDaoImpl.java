package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.entity.Flight;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FlightDaoImpl extends BaseDao<Flight> implements FlightDao {
    final Logger LOG = Logger.getLogger(FlightDaoImpl.class);
    private static FlightDaoImpl instance = null;
    public static final String SELECT_ALL_FLIGHT = "SELECT * FROM flight";


    public FlightDaoImpl() {
    }

    public static synchronized FlightDaoImpl getInstance() {
        if (instance == null) instance = new FlightDaoImpl();
        return instance;
    }

    public List getAll() {
        Session session = HibernateUtil.getInstance().getSession();
        String hql = "FROM Flight";
        Query query = session.createQuery(hql);
        List<Flight> flights = query.list();
        return flights;
/*        List<Flight> lst = new ArrayList<Flight>();
        Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(SELECT_ALL_FLIGHT);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Flight flight = new Flight(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                            rs.getByte(5));
                    lst.add(flight);
                }
                rs.close();
            } catch (SQLException e) {
                LOG.error("Exception: ", e);
            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return lst;*/
    }
}
