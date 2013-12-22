package org.dejava.service.accesscontrol.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.method.ArgFormatter;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.permission.Permission;
import org.dejava.service.accesscontrol.model.permission.Role;
import org.dejava.service.accesscontrol.model.permission.UserPermission;
import org.dejava.service.accesscontrol.model.permission.UserRole;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.model.Person;

/**
 * Represents a system user.
 */
@Entity
@Table(name = "u5er")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
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
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
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
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
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
	 * User roles.
	 */
	private Set<UserRole> userRoles;

	/**
	 * Gets the user roles.
	 * 
	 * @return The user roles.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserRole> getUserRoles() {
		// If the set is null.
		if (userRoles == null) {
			// Creates a new set for the set.
			userRoles = new HashSet<>();
		}
		// Returns the set.
		return userRoles;
	}

	/**
	 * Sets the userRoles.
	 * 
	 * @param userRoles
	 *            New userRoles.
	 */
	private void setUserRoles(final Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * Gets the user roles.
	 * 
	 * @return The user roles.
	 */
	@Transient
	public Set<String> getRoles() {
		// Creates a new empty set.
		final Set<String> roles = new HashSet<>();
		// For each user role.
		for (final UserRole currentRole : getUserRoles()) {
			// Adds the current role name to the set.
			roles.add(currentRole.getRole().getName());
		}
		// Returns the set.
		return roles;
	}

	/**
	 * Sets the user roles.
	 * 
	 * @param roles
	 *            New user roles.
	 */
	public void setRoles(final Set<String> roles) {
		// If there are roles.
		if (roles != null) {
			// Creates a new user role set.
			final Set<UserRole> userRoles = new HashSet<>();
			// For each role.
			for (final String currentRole : roles) {
				// Adds a new user role for the current name to the set.
				userRoles.add(new UserRole(this, new Role(currentRole)));
			}
			// Sets the new user roles.
			setUserRoles(userRoles);
		}
	}

	/**
	 * Sets the user roles.
	 * 
	 * @param roles
	 *            New user roles (in a single string, and split by ",").
	 */
	public void setRoles(final String roles) {
		// Sets the new roles.
		setRoles(new HashSet<>(ArgFormatter.split(roles)));
	}

	/**
	 * User permissions.
	 */
	private Set<UserPermission> userPermissions;

	/**
	 * Gets the user permissions.
	 * 
	 * @return The user permissions.
	 */
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserPermission> getUserPermissions() {
		// If the set is null.
		if (userPermissions == null) {
			// Creates a new set for the set.
			userPermissions = new HashSet<>();
		}
		// Returns the set.
		return userPermissions;
	}

	/**
	 * Sets the userPermissions.
	 * 
	 * @param userPermissions
	 *            New userPermissions.
	 */
	private void setUserPermissions(final Set<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	/**
	 * Gets the user permissions.
	 * 
	 * @return The user permissions.
	 */
	@Transient
	public Set<String> getPermissions() {
		// Creates a new permission set.
		final Set<String> permissions = new HashSet<>();
		// For each user permission.
		for (final UserPermission currentPermission : getUserPermissions()) {
			// Adds the current permission name to the set.
			permissions.add(currentPermission.getPermission().getName());
		}
		// Returns the set.
		return permissions;
	}

	/**
	 * Sets the user permissions.
	 * 
	 * @param permissions
	 *            New user permissions.
	 */
	public void setPermissions(final Set<String> permissions) {
		// If there are permissions.
		if (permissions != null) {
			// Creates a new user permission set.
			final Set<UserPermission> userPermissions = new HashSet<>();
			// For each permission.
			for (final String currentPermission : permissions) {
				// Adds a new user permission for the current name to the set.
				userPermissions.add(new UserPermission(this, new Permission(currentPermission)));
			}
			// Sets the new user permissions.
			setUserPermissions(userPermissions);
		}
	}

	/**
	 * Sets the user permissions.
	 * 
	 * @param permissions
	 *            New user permissions (in a single string, and split by ",").
	 */
	public void setPermissions(final String permissions) {
		// Sets the new permissions.
		setPermissions(new HashSet<>(ArgFormatter.split(permissions)));
	}

	/**
	 * Gets the user and its roles permissions.
	 * 
	 * @return The user and its roles permissions.
	 */
	@Transient
	public Set<String> getAllPermissions() {
		// Creates a new permission set.
		final Set<String> permissions = new HashSet<>();
		// Adds the user permissions to the set.
		permissions.addAll(getPermissions());
		// for each user role.
		for (final UserRole currentRole : getUserRoles()) {
			// Adds the current role permissions to the set.
			permissions.addAll(currentRole.getRole().getPermissions());
		}
		// Returns the permissions.
		return permissions;
	}

	/**
	 * The user party id.
	 */
	private Integer partyId;

	/**
	 * Gets the user party id.
	 * 
	 * @return The user party id.
	 */
	@Column(name = "party")
	protected Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the user party id.
	 * 
	 * @param partyId
	 *            New user party id.
	 */
	protected void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * The user party.
	 */
	@ExternalEntity(retrieveObj = "java:app/party-ejb/Component/Party/Party")
	private Party party;

	/**
	 * Gets the user party.
	 * 
	 * @return The user party.
	 */
	@Valid
	@Transient
	public Party getParty() {
		return party;
	}

	/**
	 * Sets the user party.
	 * 
	 * @param party
	 *            New user party.
	 */
	public void setParty(final Party party) {
		// If the party is null.
		if (party == null) {
			// Sets the party id to null.
			setPartyId(null);
		}
		// If the party is not null.
		else {
			// Sets the new party id.
			setPartyId(party.getIdentifier());
		}
		// Updates the party.
		this.party = party;
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
		// Creates a new party with the facebook user.
		setParty(new Person(facebookUser));
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// If there is no party for the user.
		if (getParty() == null) {
			// Returns the user name.
			return getPrincipal(Name.class).getName();
		}
		// If there is a party for the user.
		else {
			// Returns the party display name.
			return getParty().getDisplayName();
		}
	}

}
