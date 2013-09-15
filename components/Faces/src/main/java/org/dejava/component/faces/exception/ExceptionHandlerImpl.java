package org.dejava.component.faces.exception;

import java.util.Iterator;

import javax.ejb.EJBException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.inject.Inject;

import org.dejava.component.exception.localized.LocalizedException;
import org.dejava.component.faces.i18n.LocaleController;
import org.dejava.component.faces.message.FacesMessageHandler;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;

/**
 * A customized exception handler for the applications.
 */
public class ExceptionHandlerImpl extends ExceptionHandlerWrapper {

	/**
	 * The wrapped exception handler.
	 */
	private ExceptionHandler wrappedHandler;

	/**
	 * Default constructor.
	 * 
	 * @param wrappedHandler
	 *            The original handler to be injected.
	 */
	public ExceptionHandlerImpl(final ExceptionHandler wrappedHandler) {
		this.wrappedHandler = wrappedHandler;
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrappedHandler;
	}

	/**
	 * Locale controller.
	 */
	@Inject
	private LocaleController localeController;

	/**
	 * Gets the application message handler.
	 * 
	 * @param facesContext
	 *            The faces context.
	 * @return The application message handler.
	 */
	private ApplicationMessageHandler getAppMessageHandler(FacesContext facesContext) {
		// Returns a new application message handler.
		return new FacesMessageHandler(SimpleMessageHandler.getMessageHandler(localeController.getLocale()),
				facesContext);
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		// Gets the iterator for the exception queue events.
		Iterator<ExceptionQueuedEvent> exceptionEventIterator = getUnhandledExceptionQueuedEvents()
				.iterator();
		// For each unhandled exception.
		for (ExceptionQueuedEvent currentExceptionEvent = exceptionEventIterator.next(); exceptionEventIterator
				.hasNext(); currentExceptionEvent = exceptionEventIterator.next()) {
			// Gets the current exception.
			Throwable currentException = currentExceptionEvent.getContext().getException();
			// While the exception is a wrapper (EJB or Faces exception).
			while (((currentException instanceof FacesException) || (currentException instanceof EJBException))
					&& (currentException.getCause() != null)) {
				// Unwraps the exception.
				currentException = currentException.getCause();
			}
			// If the exception is a localize one.
			if (currentException instanceof LocalizedException) {
				// Adds the error messages to the application.
				((LocalizedException) currentException)
						.addLocalizedMessages(getAppMessageHandler(currentExceptionEvent.getContext()
								.getContext()));
				// Removes the event from the queue.
				exceptionEventIterator.remove();
			}
		}
	}
}