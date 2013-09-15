package org.dejava.component.faces.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * The exception handler factory for the applications.
 */
public class ExceptionHandlerFactoryImpl extends ExceptionHandlerFactory {

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
	public ExceptionHandlerFactoryImpl(final ExceptionHandlerFactory wrappedFactory) {
		this.wrappedFactory = wrappedFactory;
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		// Returns a new exception handler.
		return new ExceptionHandlerImpl(wrappedFactory.getExceptionHandler());
	}
}