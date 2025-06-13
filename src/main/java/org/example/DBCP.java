package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.impl.ServletContextCleaner;

import java.sql.SQLException;

@WebListener
public class DBCP implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/CMS");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Ijse@1234");
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(50);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("ds",basicDataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext servletContext = sce.getServletContext();
            BasicDataSource basicDataSource = (BasicDataSource) servletContext.getAttribute("ds");
            basicDataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
