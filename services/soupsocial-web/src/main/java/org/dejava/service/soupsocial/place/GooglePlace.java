package org.dejava.service.soupsocial.place;

import com.google.api.client.util.Key;

/**
 * Google place detail.
 */
public class GooglePlace {

	/**
	 * Reference for the place.
	 */
	@Key
	private String reference;

	/**
	 * Gets the reference for the place.
	 * 
	 * @return The reference for the place.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference for the place.
	 * 
	 * @param reference
	 *            New reference for the place.
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * Long name for the place.
	 */
	@Key(value = "long_name")
	private String longName;

	/**
	 * Gets the long name for the place.
	 * 
	 * @return The long name for the place.
	 */
	public String getLongName() {
		return longName;
	}

	/**
	 * Sets the long name for the place.
	 * 
	 * @param longName
	 *            New long name for the place.
	 */
	public void setLongName(final String longName) {
		this.longName = longName;
	}

	/**
	 * Name for the place.
	 */
	@Key
	private String name;

	/**
	 * Gets the name for the place.
	 * 
	 * @return The name for the place.
	 */
	public String getName() {
		// If the name is null.
		if (name == null) {
			// Returns the long name.
			return getLongName();
		}
		// If the name is not null.
		else {
			// Returns it.
			return name;
		}
	}

	/**
	 * Sets the name for the place.
	 * 
	 * @param name
	 *            New name for the place.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Short name for the place.
	 */
	@Key(value = "short_name")
	private String shortName;

	/**
	 * Gets the short name for the place.
	 * 
	 * @return The short name for the place.
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * Sets the short name for the place.
	 * 
	 * @param shortName
	 *            New short name for the place.
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * Types for the place.
	 */
	@Key
	private String[] types;

	/**
	 * Gets the types for the place.
	 * 
	 * @return The types for the place.
	 */
	public String[] getTypes() {
		return types;
	}

	/**
	 * Sets the types for the place.
	 * 
	 * @param types
	 *            New types for the place.
	 */
	public void setTypes(final String[] types) {
		this.types = types;
	}

	/**
	 * The parent places for the place.
	 */
	@Key(value = "address_components")
	private GooglePlace[] parentPlaces;

	/**
	 * Gets the parent places for the place.
	 * 
	 * @return The parent places for the place.
	 */
	public GooglePlace[] getParentPlaces() {
		return parentPlaces;
	}

	/**
	 * Sets the parent places for the place.
	 * 
	 * @param parentPlaces
	 *            New parent places for the place.
	 */
	public void setParentPlaces(final GooglePlace[] parentPlaces) {
		this.parentPlaces = parentPlaces;
	}

}
