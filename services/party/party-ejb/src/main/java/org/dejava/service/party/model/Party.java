package org.dejava.service.party.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Represents a abstract party.
 */
@Entity()
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
	public void setName(String name) {
		this.name = name;
	}

}
