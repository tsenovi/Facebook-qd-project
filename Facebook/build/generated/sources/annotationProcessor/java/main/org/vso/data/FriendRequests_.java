package org.vso.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;
import org.vso.constants.FriendStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FriendRequests.class)
public abstract class FriendRequests_ {

	public static volatile SingularAttribute<FriendRequests, FriendStatus> friendStatus;
	public static volatile SingularAttribute<FriendRequests, Long> friendId;
	public static volatile SingularAttribute<FriendRequests, Long> id;
	public static volatile SingularAttribute<FriendRequests, Long> userId;

	public static final String FRIEND_STATUS = "friendStatus";
	public static final String FRIEND_ID = "friendId";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

}

