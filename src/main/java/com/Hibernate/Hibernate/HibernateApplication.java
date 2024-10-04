package com.Hibernate.Hibernate;

import java.util.List;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class HibernateApplication {

	// public static void main(String[] args) {
	// 	SpringApplication.run(HibernateApplication.class, args);
	// }

	// public static void main(String[] args) {
    //     // Start a Hibernate session
    //     Session session = HibernateUtil.getSessionFactory().openSession();
    //     session.beginTransaction();

    //     // Create and save an entity
    //     Hibernate entity = new Hibernate();
    //     entity.setName("Example");
    //     session.persist(entity);

    //     // Commit the transaction and close the session
    //     session.getTransaction().commit();
    //     session.close();

    //     // Shut down Hibernate when done
    //     HibernateUtil.shutdown();
    // }

	public static void main(String[] args) {
        HibernateDAO hibernateDAO = new HibernateDAO();

        // Create and save a new Hibernate entity
        Hibernate hibernateEntity = new Hibernate("Sample Name");
        hibernateDAO.saveHibernate(hibernateEntity);

        // Fetch and print the entity by ID
        Hibernate retrievedEntity = hibernateDAO.getHibernateById(hibernateEntity.getId());
        System.out.println("Retrieved Entity: " + retrievedEntity.getName());

        // Update the entity
        retrievedEntity.setName("Updated Name");
        hibernateDAO.updateHibernate(retrievedEntity);

        // Fetch all entities and print them
        List<Hibernate> allEntities = hibernateDAO.getAllHibernate();
        allEntities.forEach(entity -> System.out.println(entity.getName()));

        // Delete the entity by ID
        hibernateDAO.deleteHibernate(retrievedEntity.getId());
    }

}
