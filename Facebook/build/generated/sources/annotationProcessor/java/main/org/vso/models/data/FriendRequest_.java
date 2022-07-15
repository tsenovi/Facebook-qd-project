package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;
import org.vso.constants.FriendStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FriendRequest.class)
public abstract class FriendRequest_ {

	public static volatile SingularAttribute<FriendRequest, FriendStatus> friendStatus;
	public static volatile SingularAttribute<FriendRequest, Long> friendId;
	public static volatile SingularAttribute<FriendRequest, Long> id;
	public static volatile SingularAttribute<FriendRequest, Long> userId;

	public static final String FRIEND_STATUS = "friendStatus";
	public static final String FRIEND_ID = "friendId";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

}

