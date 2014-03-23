package org.dejava.component.faces.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;

import org.dejava.component.exception.localized.LocalizedException;
import org.dejava.component.faces.i18n.AbstractLocaleController;

/**
 * A customized exception handler for the applications.
 */
public class ExceptionHandlerImpl extends ExceptionHandlerWrapper {

	/**
	 * The wrapped exception handler.
	 */
	private final ExceptionHandler wrappedHandler;

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrappedHandler;
	}

	/**
	 * Generic exception error type.
	 */
	private Class<?> genericExceptionErrorType;

	/**
	 * Gets the generic exception error type.
	 * 
	 * @return The generic exception error type.
	 */
	public Class<?> getGenericExceptionErrorType() {
		return genericExceptionErrorType;
	}

	/**
	 * Sets the generic exception error type.
	 * 
	 * @param genericExceptionErrorType
	 *            New generic exception error type.
	 */
	public void setGenericExceptionErrorType(final Class<?> genericExceptionErrorType) {
		this.genericExceptionErrorType = genericExceptionErrorType;
	}

	/**
	 * Generic exception error key.
	 */
	private String genericExceptionKey;

	/**
	 * Gets the generic exception error key.
	 * 
	 * @return The generic exception error key.
	 */
	public String getGenericExceptionKey() {
		return genericExceptionKey;
	}

	/**
	 * Sets the generic exception error key.
	 * 
	 * @param genericExceptionKey
	 *            New generic exception error key.
	 */
	public void setGenericExceptionKey(final String genericExceptionKey) {
		this.genericExceptionKey = genericExceptionKey;
	}

	/**
	 * Default constructor.
	 * 
	 * @param wrappedHandler
	 *            The original handler to be injected.
	 * @param genericExceptionErrorType
	 *            The generic exception error type.
	 * @param genericExceptionKey
	 *            The generic exception error key.
	 */
	public ExceptionHandlerImpl(final ExceptionHandler wrappedHandler,
			final Class<?> genericExceptionErrorType, final String genericExceptionKey) {
		this.wrappedHandler = wrappedHandler;
		this.genericExceptionErrorType = genericExceptionErrorType;
		this.genericExceptionKey = genericExceptionKey;
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		// Gets the iterator for the exception queue events.
		final Iterator<ExceptionQueuedEvent> exceptionEventIterator = getUnhandledExceptionQueuedEvents()
				.iterator();
		// For each unhandled exception.
		while (exceptionEventIterator.hasNext()) {
			// Gets the current exception event.
			final ExceptionQueuedEvent currentExceptionEvent = exceptionEventIterator.next();
			// Gets the current exception.
			Throwable currentException = currentExceptionEvent.getContext().getException();
			// While the exception is not a Localized exception.
			while ((!(currentException instanceof LocalizedException)) && (currentException != null)) {
				// Unwraps the exception.
				currentException = currentException.getCause();
			}
			// If the exception is a localized one.
			if ((currentException instanceof LocalizedException) && (currentException != null)) {
				// Adds the error messages to the application.
				((LocalizedException) currentException).addLocalizedMessages(AbstractLocaleController
						.getLocaleController((currentExceptionEvent.getContext().getContext()))
						.getAppMessageHandler(currentExceptionEvent.getContext().getContext()));
				// Removes the event from the queue.
				exceptionEventIterator.remove();
			}
			// If it is not a localized exception.
			else {
				// Adds a new generic error message.
				AbstractLocaleController
						.getLocaleController((currentExceptionEvent.getContext().getContext()))
						.getAppMessageHandler(currentExceptionEvent.getContext().getContext())
						.addMessage(getGenericExceptionErrorType(), null, getGenericExceptionKey(), null);
			}
		}
	}
}