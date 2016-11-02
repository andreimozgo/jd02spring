package by.academy.it.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    final Logger LOG = Logger.getLogger(DataSource.class);
    private static DataSource datasource;
    private ComboPooledDataSource cpds;
    private ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

    private DataSource() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(resource.getString("db.driver"));
        } catch (PropertyVetoException e) {
            LOG.error("Exception: ", e);
        }
        cpds.setJdbcUrl(resource.getString("db.url"));
        cpds.setUser(resource.getString("db.user"));
        cpds.setPassword(resource.getString("db.password"));
    }

    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() {
        try {
            if ((threadConnection.get() == null) || threadConnection.get().isClosed()) {
                threadConnection.set(this.cpds.getConnection());
            }
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return threadConnection.get();
    }
}
