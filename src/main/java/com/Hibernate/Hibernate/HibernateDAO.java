package com.Hibernate.Hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateDAO {
    // Save a new Hibernate entity
    public void saveHibernate(Hibernate hibernate) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(hibernate);  // Use persist() instead of save()
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

    // Get all Hibernate entities using CriteriaBuilder (JPA Criteria API)
    public List<Hibernate> getAllHibernate() {
        Transaction transaction = null;
        List<Hibernate> hibernateList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            // Using CriteriaBuilder for fetching all entities
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Hibernate> query = builder.createQuery(Hibernate.class);
            Root<Hibernate> root = query.from(Hibernate.class);
            query.select(root);
            
            hibernateList = session.createQuery(query).getResultList();
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
            session.merge(hibernate); // Use merge() instead of update()
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a Hibernate entity using createMutationQuery (for HQL Delete)
    public void deleteHibernate(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Hibernate hibernate = session.get(Hibernate.class, id); // Fetch entity
            if (hibernate != null) {
                session.remove(hibernate); // Use remove() instead of delete()
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
