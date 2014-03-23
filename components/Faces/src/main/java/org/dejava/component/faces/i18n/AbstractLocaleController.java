package org.dejava.component.faces.i18n;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.dejava.component.faces.message.FacesMessageHandler;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;

/**
 * Controller that handles the application locale for a session.
 */
public abstract class AbstractLocaleController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2921686219608561186L;

	/**
	 * The locale controller session attribute name.
	 */
	public static final String LOCALE_CONTROLLER_SESSION_ATTR = "localeController";

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
			locale = getFacesContext().getViewRoot().getLocale();
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

	/**
	 * Gets the default implementation for the application message handler.
	 * 
	 * @param facesContext
	 *            The faces context to be used for the message handler.
	 * @return The default implementation for the application message handler.
	 */
	public ApplicationMessageHandler getAppMessageHandler(final FacesContext facesContext) {
		return new FacesMessageHandler(SimpleMessageHandler.getMessageHandler(getLocale()), facesContext);
	}

	/**
	 * Gets the locale controller for the faces context.
	 * 
	 * @param facesContext
	 *            The faces context to get the locale controller for.
	 * @return The locale controller for the faces context.
	 */
	public static AbstractLocaleController getLocaleController(final FacesContext facesContext) {
		// Gets the locale controller for the HTTP session associated with the faces context.
		return (AbstractLocaleController)  facesContext.getExternalContext()
				.getSessionMap().get(LOCALE_CONTROLLER_SESSION_ATTR);
	}
}
