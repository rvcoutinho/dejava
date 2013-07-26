package org.dejava.component.faces.test.converter.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Converter controller.
 */
@Faces
@RequestScoped
@ManagedBean
public class ConverterController {

	/**
	 * A field that should only contain digits.
	 */
	private String onlyDigits;

	/**
	 * Gets the field that should only contain digits.
	 * 
	 * @return The field that should only contain digits.
	 */
	public String getOnlyDigits() {
		return onlyDigits;
	}

	/**
	 * Sets the field that should only contain digits.
	 * 
	 * @param onlyDigits
	 *            New field that should only contain digits.
	 */
	public void setOnlyDigits(String onlyDigits) {
		this.onlyDigits = onlyDigits;
	}

}
