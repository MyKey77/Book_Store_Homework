package org.progmatic.Controls;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.progmatic.model.Author;
import org.progmatic.model.Book;
import org.progmatic.model.Store;

import java.util.Properties;

public class HibernateContext implements AutoCloseable {

    private SessionFactory sessionFactory;

    private SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties props = new Properties();

            props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            props.put("hibernate.connection.url", System.getenv("pdb_url"));
            props.put("hibernate.connection.username", System.getenv("pdb_user"));
            props.put("hibernate.connection.password", System.getenv("pdb_pw"));
            //pdb_pw=pikachu;pdb_user=MM;pdb_url=jdbc:mysql://95.111.248.108:16603/pm2201_MM

            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.hbm2ddl.auto", "update");

            configuration.setProperties(props);


            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Store.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void close() throws Exception {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}
