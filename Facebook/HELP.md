# Facebook 

* Structure: 
  * MVP
* Database: 
  * Postgres
* Connection via:
  * /resources/META-INF/persistence.xml.
  
* Here I will post the persistence file content, because I will exclude it for Git, so we don`t change it by every merged branch.
* Create the persistence.xml and make these changes:
  * Update url, user and password properties, with your own local database settings. 
  * Also don`t forget to add mappings in the class tag, as shown in the example (copy content within ' '): 


### Persistence.xml

'<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">

    <persistence-unit name="Facebook" transaction-type="RESOURCE_LOCAL">
        <class>org.vso.data.User</class>
        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver" />
            <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/Facebook" />
            <property name="jakarta.persistence.jdbc.user" value="postgres" />
            <property name="jakarta.persistence.jdbc.password" value="admin" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL82Dialect" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="update" />
            <property name="hibernate.cache.provider_class" value="org.hibernate.cache.NoCacheProvider"/>
            <property name="hibernate.id.new_generator_mappings" value="true"/>
        </properties>
    </persistence-unit>
</persistence>'

# Queries
### Using the Criteria API and Metamodel API to Create Basic Typesafe Queries
https://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html