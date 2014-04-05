package org.dejava.service.soupsocial.exception;

import javax.faces.context.ExceptionHandlerFactory;

import org.dejava.component.faces.exception.AbstractExceptionHandlerFactory;
import org.dejava.service.soupsocial.constant.ErrorKeys;
import org.dejava.service.soupsocial.util.MessageTypes;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The exception handler factory for the applications.
 */
@SoupSocialCtx
public class ExceptionHandlerFactoryImpl extends AbstractExceptionHandlerFactory {

	/**
	 * Default constructor.
	 * 
	 * @param wrappedFactory
	 *            The original factory to be injected.
	 */
	public ExceptionHandlerFactoryImpl(final ExceptionHandlerFactory wrappedFactory) {
		super(wrappedFactory, MessageTypes.Error.class, ErrorKeys.GENERIC);
	}

	/**
	 * @see org.dejava.component.faces.exception.AbstractExceptionHandlerFactory#getBeanManagerAttrName()
	 */
	@Override
	public String getBeanManagerAttrName() {
		return "com.sun.faces.util.Util_CDI_AVAILABLE";
	}
}