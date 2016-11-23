package by.academy.it.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private final Logger LOG = Logger.getLogger(HibernateUtil.class);
    private static HibernateUtil util;
    private SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessions = new ThreadLocal<Session>();

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            LOG.error("Initial session factory creation failed ", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static synchronized HibernateUtil getInstance() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    public Session getSession() {
        Session session = sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void releaseSession(Session session) {
        if (session != null) {
            try {
                session.close();
                sessions.remove();
            } catch (HibernateException e) {
                LOG.error(e);
            }
        }
    }
}