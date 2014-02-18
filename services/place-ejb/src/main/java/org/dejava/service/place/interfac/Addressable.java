package org.dejava.service.place.interfac;

import java.util.Collection;

import org.dejava.service.place.model.Place;

/**
 * Something that is addressable.
 */
public interface Addressable {

	/**
	 * Gets the addresses.
	 * 
	 * @return The addresses.
	 */
	public Collection<Place> getAddresses();

	/**
	 * Sets the addresses.
	 * 
	 * @param addresses
	 *            New addresses.
	 */
	public void setAddresses(Collection<Place> addresses);

}