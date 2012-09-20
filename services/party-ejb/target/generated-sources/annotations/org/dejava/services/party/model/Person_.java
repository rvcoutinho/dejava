package org.dejava.service.party.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ extends org.dejava.service.party.model.Party_ {

	public static volatile SingularAttribute<Person, String> middleName;
	public static volatile SingularAttribute<Person, String> lastName;
	public static volatile SingularAttribute<Person, Gender> gender;
	public static volatile SingularAttribute<Person, Date> birthDate;

}

