package org.vso.models.dao.contracts;

import java.util.List;

public interface FriendshipDao<T> extends BaseDao<T> {

    @Override
    void save(T t);

    @Override
    List<T> getAll();

    void delete(T t);
}
