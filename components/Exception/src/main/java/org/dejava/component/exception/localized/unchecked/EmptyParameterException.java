package org.dejava.component.exception.localized.unchecked;

import org.dejava.component.exception.constant.ErrorKeys;

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
	 *            Parameter that is empty.
	 */
	public EmptyParameterException(final String emptyParameter) {
		super(ErrorKeys.EMPTY_PARAM, null, emptyParameter, null);
	}
}
