package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;
import org.vso.constants.FriendStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FriendShip.class)
public abstract class FriendShipConstants {

	public static volatile SingularAttribute<FriendShip, FriendStatus> friendStatus;
	public static volatile SingularAttribute<FriendShip, User> receiver;
	public static volatile SingularAttribute<FriendShip, User> sender;
	public static volatile SingularAttribute<FriendShip, Integer> id;

	public static final String FRIEND_STATUS = "friendStatus";
	public static final String RECEIVER = "receiver";
	public static final String SENDER = "sender";
	public static final String ID = "id";

}

