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
 * City of a state.
 */
@Entity
@Table(name = "city")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class City extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4939552693397648164L;

	/**
	 * State for the city.
	 */
	private State state;

	/**
	 * Gets the state for the city.
	 * 
	 * @return The state for the city.
	 */
	@ManyToOne
	@JoinColumn(name = "state")
	public State getState() {
		return state;
	}

	/**
	 * Sets the state for the city.
	 * 
	 * @param state
	 *            New state for the city.
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Gets the country for the city.
	 * 
	 * @return The country for the city.
	 */
	@Transient
	public Country getCountry() {
		// If there is not a related state.
		if (getState() == null) {
			// Returns null.
			return null;
		}
		// If there is not.
		else {
			// Returns the country.
			return getState().getCountry();
		}
	}
}
