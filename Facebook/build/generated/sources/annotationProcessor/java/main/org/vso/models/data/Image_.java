package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Image.class)
public abstract class Image_ {

	public static volatile SingularAttribute<Image, User> owner;
	public static volatile SingularAttribute<Image, String> description;
	public static volatile SingularAttribute<Image, Long> id;
	public static volatile SingularAttribute<Image, String> url;

	public static final String OWNER = "owner";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String URL = "url";

}

