package org.dejava.component.util.exception.localized;

import org.dejava.component.util.exception.constant.ErrorKeys;

/**
 * Exception related empty parameter use.
 */
public class EmptyParameterException extends InvalidParameterException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4405267486703105707L;
	
	/**
	 * Public constructor.
	 * 
	 * @param emptyParameter
	 *            The parameter that is empty.
	 */
	public EmptyParameterException(final Object emptyParameter) {
		super(ErrorKeys.EMPTY_PARAM, null, new Object[] { emptyParameter });
	}
}
