package org.vso.models.data;

import jakarta.persistence.*;
import org.vso.constants.FriendshipStatus;

@Entity
@Table(name = "friendships")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "ID")
    private User sender;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "receiver_id", referencedColumnName = "ID")
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column
    private FriendshipStatus friendshipStatus;

    public Friendship() {
    }

    public Friendship(User sender, User friend, FriendshipStatus friendStatus) {
        this.sender = sender;
        this.receiver = friend;
        this.friendshipStatus = friendStatus;
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setFriendshipStatus(FriendshipStatus friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

}
