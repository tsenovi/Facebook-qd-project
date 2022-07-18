package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;
import org.vso.constants.FriendStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FriendShip.class)
public abstract class FriendShip_ {

	public static volatile SingularAttribute<FriendShip, FriendStatus> friendStatus;
	public static volatile SingularAttribute<FriendShip, Long> friendId;
	public static volatile SingularAttribute<FriendShip, Long> id;
	public static volatile SingularAttribute<FriendShip, Long> userId;

	public static final String FRIEND_STATUS = "friendStatus";
	public static final String FRIEND_ID = "friendId";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

}

