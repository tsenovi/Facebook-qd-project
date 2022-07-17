package org.vso.models.data;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Post, User> author;
	public static volatile SingularAttribute<Post, Long> id;
	public static volatile SingularAttribute<Post, String> content;

	public static final String DATE_TIME = "dateTime";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String CONTENT = "content";

}

