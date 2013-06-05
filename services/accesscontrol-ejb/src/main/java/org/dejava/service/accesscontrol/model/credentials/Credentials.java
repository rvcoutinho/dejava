package org.dejava.service.accesscontrol.model.credentials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.service.accesscontrol.model.User;

/**
 * Credentials to be used in the authentication/authorization.
 */
@Entity
@Table(name = "credentials")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credentials {

	/**
	 * Id for the credentials.
	 */
	private Integer id;

	/**
	 * Gets the id for the credentials.
	 * 
	 * @return The id for the credentials.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the credentials.
	 * 
	 * @param id
	 *            New id for the credentials.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * The user for this credentials.
	 */
	private User user;

	/**
	 * Gets the user for this credentials.
	 * 
	 * @return The user for this credentials.
	 */
	@JoinColumn(name = "u5er")
	@ManyToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user for this credentials.
	 * 
	 * @param user
	 *            New user for this credentials.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the value for the credentials.
	 * 
	 * @return The value for the credentials.
	 */
	@Transient
	public abstract Object getValue();
}
