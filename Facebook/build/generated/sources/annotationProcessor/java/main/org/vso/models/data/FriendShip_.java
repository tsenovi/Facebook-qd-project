package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;
import org.vso.constants.FriendshipStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Friendship.class)
public abstract class Friendship_ {

	public static volatile SingularAttribute<Friendship, User> receiver;
	public static volatile SingularAttribute<Friendship, User> sender;
	public static volatile SingularAttribute<Friendship, FriendshipStatus> friendshipStatus;
	public static volatile SingularAttribute<Friendship, Long> id;

	public static final String RECEIVER = "receiver";
	public static final String SENDER = "sender";
	public static final String FRIENDSHIP_STATUS = "friendshipStatus";
	public static final String ID = "id";

}

