package org.dejava.service.accesscontrol.model.credential;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.service.accesscontrol.model.User;

/**
 * Credential to be used in the authentication/authorization.
 */
@Table(name = "credential")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credential {

	/**
	 * Id for the credential.
	 */
	private Integer id;

	/**
	 * Gets the id for the credential.
	 * 
	 * @return The id for the credential.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the credential.
	 * 
	 * @param id
	 *            New id for the credential.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * The user for this credential.
	 */
	private User user;

	/**
	 * Gets the user for this credential.
	 * 
	 * @return The user for this credential.
	 */
	@Column(name = "user")
	@ManyToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user for this credential.
	 * 
	 * @param user
	 *            New user for this credential.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the value for the credential.
	 * 
	 * @return The value for the credential.
	 */
	@Transient
	public abstract Object getValue();
}
