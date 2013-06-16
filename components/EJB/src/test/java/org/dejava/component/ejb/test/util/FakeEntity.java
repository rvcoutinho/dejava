package org.dejava.component.ejb.test.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.IdentifiedEntity;

/**
 * Fake entity.
 */
@Entity
@Table(name = "fake_entity")
public class FakeEntity extends IdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1089614179485837298L;

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
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FakeEntity other = (FakeEntity) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
