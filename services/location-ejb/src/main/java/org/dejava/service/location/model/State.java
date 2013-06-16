package org.dejava.service.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * State/province/region of a country.
 */
@Entity
@Table(name = "state")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class State extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 6256520083929092546L;

	/**
	 * Country for the state.
	 */
	private Country country;

	/**
	 * Gets the country for the state.
	 * 
	 * @return The country for the state.
	 */
	@ManyToOne
	@JoinColumn(name = "country")
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country for the state.
	 * 
	 * @param country
	 *            New country for the state.
	 */
	public void setCountry(final Country country) {
		this.country = country;
	}

	/**
	 * Name of the state.
	 */
	private String name;

	/**
	 * Gets the name of the state.
	 * 
	 * @return The name of the state.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the state.
	 * 
	 * @param name
	 *            New name of the state.
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
