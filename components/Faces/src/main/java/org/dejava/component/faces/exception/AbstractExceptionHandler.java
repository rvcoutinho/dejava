package org.dejava.component.faces.exception;

import java.util.Iterator;

import javax.ejb.EJBException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;

import org.dejava.component.exception.localized.LocalizedException;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;

/**
 * A customized exception handler for the applications.
 */
public abstract class AbstractExceptionHandler extends ExceptionHandlerWrapper {

	/**
	 * The wrapped exception handler.
	 */
	private final ExceptionHandler wrappedHandler;

	/**
	 * Default constructor.
	 * 
	 * @param wrappedHandler
	 *            The original handler to be injected.
	 */
	public AbstractExceptionHandler(final ExceptionHandler wrappedHandler) {
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
	 * Gets the application message handler.
	 * 
	 * @return The application message handler.
	 */
	protected abstract ApplicationMessageHandler getAppMessageHandler();

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
			ExceptionQueuedEvent currentExceptionEvent = exceptionEventIterator.next();
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
				((LocalizedException) currentException).addLocalizedMessages(getAppMessageHandler());
				// Removes the event from the queue.
				exceptionEventIterator.remove();
			}
		}
	}
}