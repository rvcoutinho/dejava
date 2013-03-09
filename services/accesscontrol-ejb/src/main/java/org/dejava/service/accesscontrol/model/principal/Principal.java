package org.dejava.service.accesscontrol.model.principal;

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
 * Principal (user identity) to be used in the authentication/authorization.
 */
@Entity
@Table(name = "principal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Principal {

	/**
	 * Id for the principal.
	 */
	private Integer id;

	/**
	 * Gets the id for the principal.
	 * 
	 * @return The id for the principal.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the principal.
	 * 
	 * @param id
	 *            New id for the principal.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * The user for this principal.
	 */
	private User user;

	/**
	 * Gets the user for this principal.
	 * 
	 * @return The user for this principal.
	 */
	@JoinColumn(name = "u5er")
	@ManyToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user for this principal.
	 * 
	 * @param user
	 *            New user for this principal.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the value for the principal.
	 * 
	 * @return The value for the principal.
	 */
	@Transient
	public abstract Object getValue();
}
