package org.dejava.component.exception.localized.unchecked;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.exception.util.MessageTypes;

/**
 * Exception related invalid state of an object.
 */
public class InvalidStateException extends AbstractLocalizedRuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4808868487987663824L;

	/**
	 * Basic constructor.
	 * 
	 * @param invalidParameter
	 *            State that is invalid.
	 * @param InvalidParamValue
	 *            Invalid parameter value.
	 * @param cause
	 *            Exception cause.
	 */
	public InvalidStateException(final String invalidParameter, final Object InvalidParamValue,
			final Throwable cause) {
		super(MessageTypes.Error.class, ErrorKeys.INVALID_STATE, new Object[] { invalidParameter,
				InvalidParamValue }, cause);
	}

	/**
	 * @see AbstractLocalizedRuntimeException#AbstractLocalizedRuntimeException(Object, String, Object[],
	 *      Throwable)
	 */
	public InvalidStateException(final Object type, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		super(type, messageKey, parameters, cause);
	}
}
