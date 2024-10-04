package com.Hibernate.Hibernate;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class HibernateApplication {

	// public static void main(String[] args) {
	// 	SpringApplication.run(HibernateApplication.class, args);
	// }

	public static void main(String[] args) {
        // Start a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create and save an entity
        YourEntityClass entity = new YourEntityClass();
        entity.setName("Example");
        session.save(entity);

        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();

        // Shut down Hibernate when done
        HibernateUtil.shutdown();
    }

}
