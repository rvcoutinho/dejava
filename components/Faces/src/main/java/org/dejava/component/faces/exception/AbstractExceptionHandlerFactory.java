package org.dejava.component.faces.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.dejava.component.faces.i18n.AbstractLocaleController;

/**
 * The exception handler factory for the applications. The factory must be annotated with the qualifiers to
 * get the correct {@link AbstractLocaleController} bean instance.
 */
public abstract class AbstractExceptionHandlerFactory extends ExceptionHandlerFactory {

	/**
	 * The wrapped exception handler factory.
	 */
	private final ExceptionHandlerFactory wrappedFactory;

	/**
	 * Default constructor.
	 * 
	 * @param wrappedFactory
	 *            The original factory to be injected.
	 */
	public AbstractExceptionHandlerFactory(final ExceptionHandlerFactory wrappedFactory) {
		this.wrappedFactory = wrappedFactory;
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		// Returns a new exception handler.
		return new ExceptionHandlerImpl(wrappedFactory.getExceptionHandler(), getClass().getAnnotations());
	}
}