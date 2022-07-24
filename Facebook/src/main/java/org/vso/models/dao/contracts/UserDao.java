package org.vso.models.dao.contracts;

import org.vso.models.data.User;

public interface UserDao<T> extends BaseDao<T> {

    User getByEmail(String email);
}
