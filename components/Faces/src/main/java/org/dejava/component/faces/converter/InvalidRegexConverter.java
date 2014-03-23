package org.dejava.component.faces.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.dejava.component.validation.method.ArgFormatter;

/**
 * Invalid regular expression converter (removes the occurrences of the invalid expression from the string).
 */
@FacesConverter(value = "invRegexConv")
public class InvalidRegexConverter implements Converter, Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4356068886856635682L;

	/**
	 * The invalid regular expression attribute name.
	 */
	public static final String INVALID_REGEX_ATTR = "data-inv-regex";

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		// Gets the invalid regular expression for the string.
		final String invalidRegex = (String) component.getAttributes().get(INVALID_REGEX_ATTR);
		// Returns the string without the invalid regular expression occurrences.
		return ArgFormatter.removeInvalidRegex(value, invalidRegex);
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		return value.toString();
	}

}
