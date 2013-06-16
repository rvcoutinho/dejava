package org.dejava.service.location.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Neighborhood of a city.
 */
@Entity
@Table(name = "neighborhood")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Neighborhood extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5418637140281604461L;

	/**
	 * City for the neighborhood.
	 */
	private City city;

	/**
	 * Gets the city for the neighborhood.
	 * 
	 * @return The city for the neighborhood.
	 */
	@ManyToOne
	@JoinColumn(name = "city")
	public City getCity() {
		return city;
	}

	/**
	 * Sets the city for the neighborhood.
	 * 
	 * @param city
	 *            New city for the neighborhood.
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Gets the state for the neighborhood.
	 * 
	 * @return The state for the neighborhood.
	 */
	@Transient
	public State getState() {
		// If there is not a related city.
		if (getCity() == null) {
			// Returns null.
			return null;
		}
		// If there is not.
		else {
			// Returns the state.
			return getCity().getState();
		}
	}

	/**
	 * Gets the country for the neighborhood.
	 * 
	 * @return The country for the neighborhood.
	 */
	@Transient
	public Country getCountry() {
		// If there is not a related city.
		if (getCity() == null) {
			// Returns null.
			return null;
		}
		// If there is not.
		else {
			// Returns the country.
			return getCity().getCountry();
		}
	}
}
