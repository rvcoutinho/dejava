package org.dejava.service.accesscontrol.model.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Facebook identifier of the user (as principal).
 */
@Table(name = "facebook")
@Entity
public class Facebook extends Principal {

	/**
	 * Facebook identifier of the user.
	 */
	private String identifier;

	/**
	 * Gets the facebook identifier of the user.
	 * 
	 * @return The facebook identifier of the user.
	 */
	@Column(name = "identifier")
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the facebook identifier of the user.
	 * 
	 * @param identifier
	 *            New facebook identifier of the user.
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getIdentifier();
	}

	/**
	 * Public constructor.
	 */
	public Facebook() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param identifier
	 *            Facebook identifier of the user.
	 */
	public Facebook(final String identifier) {
		super();
		this.identifier = identifier;
	}

}
