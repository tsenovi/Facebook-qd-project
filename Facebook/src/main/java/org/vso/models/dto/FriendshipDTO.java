package org.vso.models.dto;

import org.vso.models.data.Friendship;
import org.vso.models.data.User;
import org.vso.models.services.contracts.AuthenticationService;
import org.vso.models.services.implementations.AuthenticationServiceImpl;

import java.util.List;

public class FriendshipDTO {
    private final AuthenticationService authenticationService;

    public FriendshipDTO() {
        this.authenticationService = AuthenticationServiceImpl.getInstance();
    }

    public List<Friendship> getFriendShipsOfTheLoggedUser(){
        return authenticationService.getLoggedUser().getFriendShips();
    }

    public List<User> getUserFriends(User user){
        return user.getFriends();
    }

    public List<Friendship> getSenderFriends(Friendship friendship){
        return friendship.getSender().getFriendShips();
    }

    public List<Friendship> getReceiverFriends(Friendship friendship){
        return friendship.getReceiver().getFriendShips();
    }

    public void addAFriend(List<User> friendList, User friend){
        friendList.add(friend);
    }

    public void removeFriendShipOfTheLoggedUser(Friendship friendship){
        getFriendShipsOfTheLoggedUser().remove(friendship);
    }
}
