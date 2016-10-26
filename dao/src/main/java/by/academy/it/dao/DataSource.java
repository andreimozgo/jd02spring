package by.academy.it.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {

    private static DataSource datasource;

    private ComboPooledDataSource cpds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {

        ResourceBundle resource = ResourceBundle.getBundle("database");

        cpds = new ComboPooledDataSource();

        cpds.setDriverClass(resource.getString("db.driver"));
        // loads the jdbc driver

        cpds.setJdbcUrl(resource.getString("db.url"));

        cpds.setUser(resource.getString("db.user"));

        cpds.setPassword(resource.getString("db.password"));


        // the settings below are optional -- c3p0 can work with defaults

		/*cpds.setMinPoolSize(5);

		cpds.setAcquireIncrement(5);

		cpds.setMaxPoolSize(20);

		cpds.setMaxStatements(180);*/

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {

        if (datasource == null) {

            datasource = new DataSource();

            return datasource;

        } else {

            return datasource;

        }

    }

    public Connection getConnection() throws SQLException {

        return this.cpds.getConnection();

    }

}
