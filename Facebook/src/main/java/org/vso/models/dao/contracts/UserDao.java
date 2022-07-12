package org.vso.models.dao.contracts;

import org.vso.models.data.User;

import java.util.Optional;

public interface UserDao<T> extends BaseDao<T> {

    User getByEmail(String email);

    User getByName(String name);
}
