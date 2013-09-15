package org.dejava.component.faces.i18n;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

/**
 * Controller that handles the application locale for a session.
 */
public abstract class AbstractLocaleController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2921686219608561186L;

	/**
	 * Gets the current instance for the faces context.
	 * 
	 * @return The current instance for the faces context.
	 */
	protected abstract FacesContext getFacesContext();

	/**
	 * The locale to be used in the JSF application.
	 */
	private Locale locale;

	/**
	 * Gets the locale to be used in the JSF application.
	 * 
	 * @return The locale to be used in the JSF application.
	 */
	public Locale getLocale() {
		// If the locale is null.
		if (locale == null) {
			// Gets the request locale.
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		}
		// Returns the locale.
		return locale;
	}

	/**
	 * Sets the locale to be used in the JSF application.
	 * 
	 * @param locale
	 *            New locale to be used in the JSF application.
	 */
	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

}
