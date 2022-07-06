package org.vso.models.dao.contracts;

import java.util.Optional;

public interface UserDao<T> extends BaseDao<T> {

    Optional<T> getByEmail(String email);
}
