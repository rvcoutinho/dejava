package org.dejava.component.ejb.test.auxiliary;

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
	 * Generated setial.
	 */
	private static final long serialVersionUID = 1089614179485837298L;

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

}
