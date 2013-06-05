package org.dejava.service.location.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Location.
 */
@Entity
@Table(name = "location")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.location.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Location implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 937091602120548072L;

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
