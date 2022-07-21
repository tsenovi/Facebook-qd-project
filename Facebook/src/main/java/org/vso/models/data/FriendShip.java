package org.vso.models.data;

import jakarta.persistence.*;
import org.vso.constants.FriendStatus;

@Entity
@Table(name = "friendRequests")
public class FriendShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "senderId", referencedColumnName = "ID")
    private User sender;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "receiverId", referencedColumnName = "ID")
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column
    private FriendStatus friendStatus;

    public FriendShip() {
    }

    public FriendShip(User sender, User friend, FriendStatus friendStatus) {
        this.sender = sender;
        this.receiver = friend;
        this.friendStatus = friendStatus;
    }

    public Integer getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public FriendStatus getFriendStatus() {
        return friendStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setFriendStatus(FriendStatus friendStatus) {
        this.friendStatus = friendStatus;
    }

}
