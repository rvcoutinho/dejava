package org.dejava.component.ejb.test.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fake entity.
 */
@Entity
@Table(name = "fake_entity")
public class FakeEntity implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1089614179485837298L;

	/**
	 * Identifier for the party.
	 */
	private Integer identifier;

	/**
	 * Gets the identifier for the party.
	 * 
	 * @return The identifier for the party.
	 */
	@Id
	@Column(name = "identifier")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for the party.
	 * 
	 * @param identifier
	 *            New identifier for the party.
	 */
	public void setIdentifier(final Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * Name of the fake entity.
	 */
	private String name;

	/**
	 * Gets the name of the fake entity.
	 * 
	 * @return The name of the fake entity.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the fake entity.
	 * 
	 * @param name
	 *            New name of the fake entity.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Default constructor.
	 */
	public FakeEntity() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of the fake entity.
	 */
	public FakeEntity(final String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FakeEntity other = (FakeEntity) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
