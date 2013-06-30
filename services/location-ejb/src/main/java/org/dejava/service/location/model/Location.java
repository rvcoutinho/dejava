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
 * Location.
 */
@Entity
@Table(name = "location")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Location extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 937091602120548072L;

	/**
	 * The name of the location.
	 */
	private String name;

	/**
	 * Gets the name of the location.
	 * 
	 * @return The name of the location.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the location.
	 * 
	 * @param name
	 *            New name of the location.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Party id for the contact.
	 */
	private Integer partyId;

	/**
	 * Gets the party id for the contact.
	 * 
	 * @return The party id for the contact.
	 */
	@Column(name = "party")
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the party id for the contact.
	 * 
	 * @param partyId
	 *            New party id for the contact.
	 */
	public void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * The description of the location.
	 */
	private String description;

	/**
	 * Gets the description of the location.
	 * 
	 * @return The description of the location.
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the location.
	 * 
	 * @param description
	 *            New description of the location.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Country for the location.
	 */
	private Country country;

	/**
	 * Gets the country for the location.
	 * 
	 * @return The country for the location.
	 */
	@ManyToOne
	@JoinColumn(name = "country")
	public Country getCountry() {
		// If the state is null.
		if (getState() == null) {
			// Returns country attribute value.
			return country;
		}
		// If there is a state.
		else {
			// Returns the country of the state.
			return getState().getCountry();
		}
	}

	/**
	 * Sets the country for the location.
	 * 
	 * @param country
	 *            New country for the location.
	 */
	public void setCountry(final Country country) {
		this.country = country;
	}

	/**
	 * State for the location.
	 */
	private State state;

	/**
	 * Gets the state for the location.
	 * 
	 * @return The state for the location.
	 */
	@ManyToOne
	@JoinColumn(name = "state")
	public State getState() {
		// If the city is null.
		if (getState() == null) {
			// Returns state attribute value.
			return state;
		}
		// If there is a city.
		else {
			// Returns the state of the city.
			return getCity().getState();
		}
	}

	/**
	 * Sets the state for the location.
	 * 
	 * @param state
	 *            New state for the location.
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * City for the location.
	 */
	private City city;

	/**
	 * Gets the city for the location.
	 * 
	 * @return The city for the location.
	 */
	@ManyToOne
	@JoinColumn(name = "city")
	public City getCity() {
		// If the neighborhood is null.
		if (getState() == null) {
			// Returns city attribute value.
			return city;
		}
		// If there is a city.
		else {
			// Returns the city of the neighborhood.
			return getNeighborhood().getCity();
		}
	}

	/**
	 * Sets the city for the location.
	 * 
	 * @param city
	 *            New city for the location.
	 */
	public void setCity(final City city) {
		this.city = city;
	}

	/**
	 * Neighborhood for the location.
	 */
	private Neighborhood neighborhood;

	/**
	 * Gets the neighborhood for the location.
	 * 
	 * @return The neighborhood for the location.
	 */
	@ManyToOne
	@JoinColumn(name = "neighborhood")
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	/**
	 * Sets the neighborhood for the location.
	 * 
	 * @param neighborhood
	 *            New neighborhood for the location.
	 */
	public void setNeighborhood(final Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

}
