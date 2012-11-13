package org.dejava.service.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.model.principal.Principal;

/**
 * Represents a system user.
 */
@Entity
@Table(name = "users")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class User implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2762159417756393897L;

	/**
	 * Id for the user.
	 */
	private Integer id;

	/**
	 * Gets the id for the user.
	 * 
	 * @return The id for the user.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the user.
	 * 
	 * @param id
	 *            New id for the user.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Gets the object (first found) from the given collection with the desired type.
	 * 
	 * @param <Type>
	 *            Type of the object being searched.
	 * @param collection
	 *            Collection to search the object from.
	 * @param type
	 *            The type for the searched object.
	 * @return The object (first found) from the given collection with the desired type. Or null, if no object
	 *         exists for the given type.
	 */
	@Transient
	@SuppressWarnings("unchecked")
	private <Type> Type getObjectByClass(final Collection<?> collection, final Class<Type> type) {
		// For each object in the collection.
		for (final Object currentObject : collection) {
			// If the current object is not null.
			if (currentObject != null) {
				// If the object is an instance from the class.
				if (currentObject.getClass().equals(type)) {
					// Returns the current object.
					return (Type) currentObject;
				}
			}
		}
		// If no object is found, return null.
		return null;
	}

	/**
	 * Principals for this user.
	 */
	private Collection<Principal> principals;

	/**
	 * Gets the principals for this user.
	 * 
	 * @return The principals for this user.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	public Collection<Principal> getPrincipals() {
		return principals;
	}

	/**
	 * Gets the principal with the given type (there should not be more than an object for given type).
	 * 
	 * @param <ConcretePrincipal>
	 *            A concrete principal type.
	 * @param principalType
	 *            The type for the principal being searched.
	 * @return The principal with the given type (there should not be more than an object for given type).
	 */
	@Transient
	public <ConcretePrincipal extends Principal> ConcretePrincipal getPrincipal(
			final Class<ConcretePrincipal> principalType) {
		// Returns the principal with the given type.
		return getObjectByClass(getPrincipals(), principalType);
	}

	/**
	 * Sets the principals for this user.
	 * 
	 * @param principals
	 *            New principals for this user.
	 */
	public void setPrincipals(final Collection<Principal> principals) {
		this.principals = principals;
	}

	/**
	 * Credentials for this user.
	 * 
	 */
	private Collection<Credentials> credentials;

	/**
	 * Gets the credentials for this user.
	 * 
	 * @return The credentials for this user.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	public Collection<Credentials> getCredentials() {
		return credentials;
	}

	/**
	 * Gets the credential with the given type (there should not be more than an object for given type).
	 * 
	 * @param <ConcreteCredential>
	 *            A concrete credential type.
	 * @param credentialType
	 *            The type for the credential being searched.
	 * @return The credential with the given type (there should not be more than an object for given type).
	 */
	@Transient
	public <ConcreteCredential extends Credentials> ConcreteCredential getCredential(
			final Class<ConcreteCredential> credentialType) {
		// Returns the credential with the given type.
		return getObjectByClass(getCredentials(), credentialType);
	}

	/**
	 * Sets the credentials for this user.
	 * 
	 * @param credentials
	 *            New credentials for this user.
	 */
	public void setCredentials(final Collection<Credentials> credentials) {
		this.credentials = credentials;
	}

}
