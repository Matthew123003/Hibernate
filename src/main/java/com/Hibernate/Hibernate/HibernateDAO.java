package com.Hibernate.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDAO {
    // Save a new Hibernate entity
    public void saveHibernate(Hibernate hibernate) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(hibernate); // Save the entity to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get a Hibernate entity by ID
    public Hibernate getHibernateById(Long id) {
        Transaction transaction = null;
        Hibernate hibernate = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            hibernate = session.get(Hibernate.class, id); // Fetch entity by ID
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return hibernate;
    }

    // Get all Hibernate entities from the database
    @SuppressWarnings("unchecked")
    public List<Hibernate> getAllHibernate() {
        Transaction transaction = null;
        List<Hibernate> hibernateList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            hibernateList = session.createQuery("from Hibernate").list(); // Fetch all entities
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return hibernateList;
    }

    // Update an existing Hibernate entity
    public void updateHibernate(Hibernate hibernate) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(hibernate); // Update the entity
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a Hibernate entity by ID
    public void deleteHibernate(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Hibernate hibernate = session.get(Hibernate.class, id); // Fetch entity to delete
            if (hibernate != null) {
                session.delete(hibernate); // Delete the entity
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
