package org.dejava.component.faces.converter;

import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Rounded number converter.
 */
public class RoundedNumberConverter implements Converter {

	/**
	 * Number scale symbols.
	 */
	public static final String[] NUMBER_SCALE_SYMBOLS = { "", "k", "M", "G", "T", "P", "E", "Z", "Y" };

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		// TODO
		return null;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		// If the value is not null.
		if (value != null) {
			// Converts the object to a number.
			Number number = (Number) value;
			// The current scale is 0.
			Integer currentScale = 0;
			// While the current number is greater than 1000 (or scale symbols size is reached).
			while ((number.doubleValue() > 1000) && (currentScale + 1 < NUMBER_SCALE_SYMBOLS.length)) {
				// Divide the number by 1000.
				number = number.doubleValue() / 1000;
				// Increment the scale.
				currentScale++;
			}
			// Creates a new clear decimal format. FIXME locale
			final NumberFormat roundedFormat = NumberFormat.getNumberInstance();
			// Sets the maximum fraction digits to 1.
			roundedFormat.setMaximumFractionDigits(1);
			// Returns the rounded number.
			return roundedFormat.format(number) + NUMBER_SCALE_SYMBOLS[currentScale];
		}
		// If the value is null.
		else {
			// Returns 0.
			return "0";
		}
	}
}
