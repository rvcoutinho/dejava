package org.dejava.service.soupsocial.place;

import java.util.Arrays;

import org.dejava.service.place.model.Place;

import com.google.api.client.util.Key;

/**
 * The result of a google place detail query.
 */
public class GooglePlaceResult {

	/**
	 * Status of the detail query.
	 */
	@Key
	private String status;

	/**
	 * Gets the status of the detail query.
	 * 
	 * @return The status of the detail query.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the detail query.
	 * 
	 * @param status
	 *            New status of the detail query.
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * Result of the detail query.
	 */
	@Key
	private GooglePlace result;

	/**
	 * Gets the result of the detail query.
	 * 
	 * @return The result of the detail query.
	 */
	public GooglePlace getResult() {
		return result;
	}

	/**
	 * Sets the result of the detail query.
	 * 
	 * @param result
	 *            New result of the detail query.
	 */
	public void setResult(final GooglePlace result) {
		this.result = result;
	}

	/**
	 * Gets the place for the google place.
	 * 
	 * @return The place for the google place.
	 */
	public Place getPlace() {
		// If there is a result.
		if (getResult() != null) {
			// Creates a new place.
			final Place place = new Place(getResult().getReference(), getResult().getName(), getResult()
					.getShortName(), Arrays.asList(getResult().getTypes()), null);
			// The current child place is the actual place.
			Place currentChildPlace = place;
			// If there are parent places.
			if (getResult().getParentPlaces() != null) {
				// For each parent google place.
				for (final GooglePlace currentGooglePlace : getResult().getParentPlaces()) {
					// Creates a new parent place.
					final Place currentParentPlace = new Place(currentGooglePlace.getReference(),
							currentGooglePlace.getName(), currentGooglePlace.getShortName(),
							Arrays.asList(currentGooglePlace.getTypes()), null);
					// Adds the current parent place as the parent of the current child place.
					currentChildPlace.setParentPlace(currentParentPlace);
					// The current parent place is the next child place.
					currentChildPlace = currentParentPlace;
				}
			}
			// Returns the new place.
			return place;
		}
		// If there is no result.
		else {
			// Returns null.
			return null;
		}
	}
}
