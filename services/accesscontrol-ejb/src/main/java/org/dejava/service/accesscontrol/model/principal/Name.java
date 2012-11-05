package org.dejava.service.accesscontrol.model.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Name of the user (as principal).
 */
@Table(name = "name")
@Entity
public class Name extends Principal {

	/**
	 * Name of the user.
	 */
	private String name;

	/**
	 * Gets the name of the user.
	 * 
	 * @return The name of the user.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name
	 *            New name of the user.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getName();
	}

}
