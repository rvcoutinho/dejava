package org.dejava.component.faces.exception;

import java.lang.annotation.Annotation;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.transaction.RollbackException;

import org.dejava.component.exception.localized.LocalizedException;
import org.dejava.component.faces.i18n.AbstractLocaleController;
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
	private final ExceptionHandler wrappedHandler;

	/**
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrappedHandler;
	}

	/**
	 * The classifiers to be used in order to get the locale controller.
	 */
	private Annotation[] localeControllerClassifiers;

	/**
	 * Gets the classifiers to be used in order to get the locale controller.
	 * 
	 * @return The classifiers to be used in order to get the locale controller.
	 */
	public Annotation[] getLocaleControllerClassifiers() {
		return localeControllerClassifiers;
	}

	/**
	 * The bean manager attribute name in the Servlet context.
	 */
	private String beanManagerAttrName;

	/**
	 * Gets the bean manager attribute name in the Servlet context.
	 * 
	 * @return The bean manager attribute name in the Servlet context.
	 */
	public String getBeanManagerAttrName() {
		return beanManagerAttrName;
	}

	/**
	 * Default constructor.
	 * 
	 * @param wrappedHandler
	 *            The original handler to be injected.
	 * @param localeControllerClassifiers
	 *            The classifiers to be used in order to get the locale controller.
	 * @param beanManagerAttrName
	 *            The bean manager attribute name in the Servlet context.
	 */
	public ExceptionHandlerImpl(final ExceptionHandler wrappedHandler,
			Annotation[] localeControllerClassifiers, String beanManagerAttrName) {
		this.wrappedHandler = wrappedHandler;
		this.localeControllerClassifiers = localeControllerClassifiers;
		this.beanManagerAttrName = beanManagerAttrName;
	}

	/**
	 * Gets the application message handler.
	 * 
	 * @param facesContext
	 *            The faces context for the request.
	 * @return The application message handler.
	 */
	protected ApplicationMessageHandler getAppMessageHandler(FacesContext facesContext) {
		// Gets the bean manager.
		BeanManager beanManager = (BeanManager) ((ServletContext) facesContext.getExternalContext()
				.getContext()).getAttribute(getBeanManagerAttrName());
		// Gets the locale controller bean.
		@SuppressWarnings("unchecked")
		Bean<AbstractLocaleController> localeControllerBean = (Bean<AbstractLocaleController>) beanManager
				.getBeans(AbstractLocaleController.class, getLocaleControllerClassifiers()).iterator().next();
		// Gets the locale controller.
		AbstractLocaleController localeController = (AbstractLocaleController) beanManager.getReference(
				localeControllerBean, AbstractLocaleController.class,
				beanManager.createCreationalContext(localeControllerBean));
		// Returns a new faces message handler.
		return new FacesMessageHandler(SimpleMessageHandler.getMessageHandler(localeController.getLocale()),
				facesContext);
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
			ExceptionQueuedEvent currentExceptionEvent = exceptionEventIterator.next();
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
				((LocalizedException) currentException)
						.addLocalizedMessages(getAppMessageHandler(currentExceptionEvent.getContext()
								.getContext()));
				// Removes the event from the queue.
				exceptionEventIterator.remove();
			}
		}
	}
}