package org.as1iva.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class ApplicationCleaner implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
