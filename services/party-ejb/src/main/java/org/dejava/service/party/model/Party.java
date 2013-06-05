package org.dejava.service.party.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.contact.model.Contact;

/**
 * Represents a abstract party.
 */
@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6745781199872773566L;

	/**
	 * Id for the party.
	 */
	private Integer id;

	/**
	 * Gets the id for the party.
	 * 
	 * @return The id for the party.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the party.
	 * 
	 * @param id
	 *            New id for the party.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Name of the party.
	 */
	private String name;

	/**
	 * Gets the name of the party.
	 * 
	 * @return The name of the party.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the party.
	 * 
	 * @param name
	 *            New name of the party.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * The identifier of the user.
	 */
	private Integer userId;

	/**
	 * Gets the user identifier.
	 * 
	 * @return The user identifier.
	 */
	@Column(name = "user")
	protected Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user identifier.
	 * 
	 * @param userId
	 *            New user identifier.
	 */
	protected void setUserId(final Integer userId) {
		this.userId = userId;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            New user.
	 */
	public final void setUser(final User user) {
		// If the user is given.
		if (user != null) {
			// Sets the user id for the party.
			setUserId(user.getId());
		}
	}

	/**
	 * Default constructor.
	 */
	public Party() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            Name of the party.
	 * @param user
	 *            User for the party.
	 */
	public Party(final String name, final User user) {
		super();
		// Sets the basic info for the party.
		this.name = name;
		setUser(user);
	}

	/**
	 * Contacts identifications for the party.
	 */
	private Set<Integer> contactsIds;

	/**
	 * Gets the contacts identifications for the party.
	 * 
	 * @return The contacts identifications for the party.
	 */
	@ElementCollection
	public Set<Integer> getContactsIds() {
		return contactsIds;
	}

	/**
	 * Sets the contacts identifications for the party.
	 * 
	 * @param contactsIds
	 *            New contacts identifications for the party.
	 */
	public void setContactsIds(Set<Integer> contactsIds) {
		this.contactsIds = contactsIds;
	}

	/**
	 * Contacts for the party.
	 */
	private Set<Contact> contacts;

}
