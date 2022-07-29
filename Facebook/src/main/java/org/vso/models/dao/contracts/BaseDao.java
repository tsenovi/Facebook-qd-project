package org.vso.models.dao.contracts;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(long id);
}
