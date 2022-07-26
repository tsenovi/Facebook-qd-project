package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photo.class)
public abstract class Photo_ {

	public static volatile SingularAttribute<Photo, User> owner;
	public static volatile SingularAttribute<Photo, String> description;
	public static volatile SingularAttribute<Photo, Long> id;
	public static volatile SingularAttribute<Photo, String> url;

	public static final String OWNER = "owner";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String URL = "url";

}

