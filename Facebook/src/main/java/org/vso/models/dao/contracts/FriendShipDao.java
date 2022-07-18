package org.vso.models.dao.contracts;

public interface FriendShipDao<T> extends BaseDao<T> {

    @Override
    void save(T t);
}
