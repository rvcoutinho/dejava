package org.dejava.service.accesscontrol.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.model.principal.Principal;

/**
 * Represents a system user.
 */
@Entity
@Table(name = "u5er")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class User extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2762159417756393897L;

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
		// If the collection is not null.
		if (collection != null) {
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
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Collection<Principal> getPrincipals() {
		// If the collection is null.
		if (principals == null) {
			// Creates a new list for the collection.
			principals = new ArrayList<>();
		}
		// Returns the collection.
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
	 * @param updateRelationship
	 *            If the relationship of the given principals should be updated.
	 */
	public void setPrincipals(final Collection<Principal> principals, final Boolean updateRelationship) {
		// If there are principals (and they should be updated).
		if ((principals != null) && (updateRelationship)) {
			// For each principal.
			for (final Principal currentPrincipal : principals) {
				// Sets the user for the current principal.
				currentPrincipal.setUser(this);
			}
		}
		// Sets the principals.
		this.principals = principals;
	}

	/**
	 * Sets the principals for this user.
	 * 
	 * @param principals
	 *            New principals for this user.
	 */
	public void setPrincipals(final Collection<Principal> principals) {
		setPrincipals(principals, false);
	}

	/**
	 * Adds a principal to the user.
	 * 
	 * @param principal
	 *            Principal to be added to the user.
	 */
	public void addPrincipal(final Principal principal) {
		// If the principal is not null.
		if (principal != null) {
			// Sets the user for the principal.
			principal.setUser(this);
			// Adds the principal to the user.
			getPrincipals().add(principal);
		}
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
		// If the collection is null.
		if (credentials == null) {
			// Creates a new list for the collection.
			credentials = new ArrayList<>();
		}
		// Returns the collection.
		return credentials;
	}

	/**
	 * Gets the credentials with the given type (there should not be more than an object for given type).
	 * 
	 * @param <ConcreteCredentials>
	 *            A concrete credentials type.
	 * @param credentialsType
	 *            The type for the credentials being searched.
	 * @return The credentials with the given type (there should not be more than an object for given type).
	 */
	@Transient
	public <ConcreteCredentials extends Credentials> ConcreteCredentials getCredentials(
			final Class<ConcreteCredentials> credentialsType) {
		// Returns the credentials with the given type.
		return getObjectByClass(getCredentials(), credentialsType);
	}

	/**
	 * Sets the credentials for this user.
	 * 
	 * @param credentials
	 *            New credentials for this user.
	 * @param updateRelationship
	 *            If the relationship of the given principals should be updated.
	 */
	public void setCredentials(final Collection<Credentials> credentials, final Boolean updateRelationship) {
		// If there are credentials (and they should be updated).
		if ((credentials != null) && updateRelationship) {
			// For each credentials.
			for (final Credentials currentCredentials : credentials) {
				// Sets the user for the current credentials.
				currentCredentials.setUser(this);
			}
		}
		// Sets the credentials.
		this.credentials = credentials;
	}

	/**
	 * Sets the credentials for this user.
	 * 
	 * @param credentials
	 *            New credentials for this user.
	 */
	public void setCredentials(final Collection<Credentials> credentials) {
		setCredentials(credentials, false);
	}

	/**
	 * Adds credentials to the user.
	 * 
	 * @param credentials
	 *            Credentials to be added to the user.
	 */
	public void addCredentials(final Credentials credentials) {
		// If the credentials are not null.
		if (credentials != null) {
			// Sets the user for the credentials.
			credentials.setUser(this);
			// Adds the credentials to the user.
			getCredentials().add(credentials);
		}
	}

	/**
	 * Default constructor.
	 */
	public User() {
		super();
	}

	/**
	 * Creates a new user with the given name and password.
	 * 
	 * @param name
	 *            Name of the user.
	 * @param email
	 *            Email of the user.
	 * @param rawPassword
	 *            Password of the user.
	 */
	public User(final String name, final String email, final String rawPassword) {
		// Adds the name to the user principals.
		addPrincipal(new Name(name));
		// Adds the email to the user principals.
		addPrincipal(new Email(email));
		// Adds the password to the user credentials.
		addCredentials(new Password(rawPassword));
	}

	/**
	 * Creates a new user with the facebook user information.
	 * 
	 * @param facebookUser
	 *            Facebook user information.
	 */
	public User(final com.restfb.types.User facebookUser) {
		// Adds the name to the user principals.
		addPrincipal(new Name(facebookUser.getUsername()));
		// Adds the email to the user principals.
		addPrincipal(new Email(facebookUser.getEmail()));
		// Adds the password to the user credentials.
		addPrincipal(new Facebook(facebookUser.getId()));
	}
}
