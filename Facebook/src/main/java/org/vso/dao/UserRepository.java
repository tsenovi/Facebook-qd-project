package org.vso.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.vso.data.User;
import org.vso.data.User_;
import org.vso.utils.HibernateUtil;

import java.util.List;

public class UserRepository {
    @PersistenceUnit(name = "Facebook")
    private EntityManagerFactory entityManagerFactory;

    public UserRepository() {
        init();
    }

    private void init() {
        try {
            entityManagerFactory = new HibernateUtil().buildEntityManagerFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EntityManager getEntityManagerInTransaction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        return entityManager;
    }

    private void closeEntityManagerInTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void createUser(User user) {
        EntityManager entityManager = getEntityManagerInTransaction();
        entityManager.persist(user);
        closeEntityManagerInTransaction(entityManager);
    }

    public void deleteUser(int selectedId) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> delete = builder.createCriteriaDelete(User.class);
        Root<User> root = delete.from(User.class);
        delete.where(builder.lessThanOrEqualTo(root.get(User_.ID), selectedId));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }

    public User readUserByEmail(String email) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(User_.EMAIL), email));
        User result;
        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            result = null;
        }
        closeEntityManagerInTransaction(entityManager);
        return result;
    }

    //TODO
    public List<User> updateUser(User user) {
        return null;
    }
}
