package org.vso.models.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.vso.models.dao.contracts.UserDao;
import org.vso.models.data.User;
import org.vso.models.data.User_;
import org.vso.utils.contracts.Hibernate;
import org.vso.utils.implementations.HibernateImpl;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {

    @PersistenceUnit(name = "Facebook")
    private EntityManagerFactory entityManagerFactory;

    private final Hibernate hibernate;

    public UserDaoImpl() {
        this.hibernate = new HibernateImpl();
        init();
    }

    private void init() {
        try {
            entityManagerFactory = hibernate.buildEntityManagerFactory();
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

    @Override
    public Optional<User> getByEmail(String email) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(User_.EMAIL), email));

        Optional<User> singleResult = Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());

        closeEntityManagerInTransaction(entityManager);

        return singleResult;
    }

    @Override
    public Optional<User> get(long id) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(User_.ID), id));

        Optional<User> singleResult = Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());

        closeEntityManagerInTransaction(entityManager);

        return singleResult;
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<User> resultList = entityManager.createQuery(criteria).getResultList();

        closeEntityManagerInTransaction(entityManager);

        return resultList;
    }

    @Override
    public void save(User user) {
        EntityManager entityManager = getEntityManagerInTransaction();
        entityManager.persist(user);

        closeEntityManagerInTransaction(entityManager);
    }

    //TODO
    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> delete = builder.createCriteriaDelete(User.class);
        Root<User> root = delete.from(User.class);
        delete.where(builder.lessThanOrEqualTo(root.get(User_.ID), id));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }
}
