package org.dejava.service.soupsocial.place;

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
		// Creates a new
		// FIXME
		return null;
	}

}
