package org.dejava.service.philanthropy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.service.party.model.Organization;

/**
 * Philanthropy project.
 */
@Entity
@Table(name = "project")
public class Project extends Idea implements Serializable {

	/**
	 * Genareted serial.
	 */
	private static final long serialVersionUID = 4851354607337382534L;

	/**
	 * Foundation supporting the philanthropy project.
	 */
	private Organization foundation;

	/**
	 * Gets the foundation supporting the philanthropy project.
	 * 
	 * @return The foundation supporting the philanthropy project.
	 */
	public Organization getFoundation() {
		return foundation;
	}

	/**
	 * Sets the foundation supporting the philanthropy project.
	 * 
	 * @param foundation
	 *            New foundation supporting the philanthropy project.
	 */
	public void setFoundation(final Organization foundation) {
		this.foundation = foundation;
	}

}
