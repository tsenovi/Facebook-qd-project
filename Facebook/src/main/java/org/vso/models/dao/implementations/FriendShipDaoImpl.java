package org.vso.models.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.vso.models.dao.contracts.FriendShipDao;
import org.vso.models.data.FriendShip;
import org.vso.models.data.FriendShipConstants;
import org.vso.utils.contracts.Hibernate;
import org.vso.utils.implementations.HibernateImpl;

import java.util.List;
import java.util.Optional;

public class FriendShipDaoImpl implements FriendShipDao<FriendShip> {

    @PersistenceUnit(name = "Facebook")
    private EntityManagerFactory entityManagerFactory;

    private final Hibernate hibernate;


    public FriendShipDaoImpl() {
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
    public Optional<FriendShip> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<FriendShip> getAll() {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FriendShip> criteria = builder.createQuery(FriendShip.class);
        Root<FriendShip> root = criteria.from(FriendShip.class);
        criteria.select(root);

        List<FriendShip> resultList = entityManager.createQuery(criteria).getResultList();

        closeEntityManagerInTransaction(entityManager);

        return resultList;
    }

    @Override
    public void save(FriendShip friendRequest) {
        EntityManager entityManager = getEntityManagerInTransaction();
        entityManager.persist(friendRequest);

        closeEntityManagerInTransaction(entityManager);
    }

    @Override
    public void update(FriendShip friendRequest, String[] params) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void delete(FriendShip friendShip) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<FriendShip> delete = builder.createCriteriaDelete(FriendShip.class);
        Root<FriendShip> root = delete.from(FriendShip.class);
        delete.where(builder.lessThanOrEqualTo(root.get(FriendShipConstants.ID), friendShip.getId()));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }
}
