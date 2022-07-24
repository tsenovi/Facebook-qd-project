package org.vso.models.services.implementations;

import org.vso.models.dao.contracts.FriendshipDao;
import org.vso.models.dao.implementations.FriendshipDaoImpl;
import org.vso.models.data.Friendship;
import org.vso.models.services.contracts.FriendshipService;

public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipDao<Friendship> friendshipDao;

    public FriendshipServiceImpl() {
        this.friendshipDao = new FriendshipDaoImpl();
    }

    @Override
    public void save(Friendship friendship) {
        friendshipDao.save(friendship);
    }
}
