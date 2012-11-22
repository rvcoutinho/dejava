package org.dejava.component.exception.localized.unchecked;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.exception.util.Severity;

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
		super(Severity.Error.class, ErrorKeys.EMPTY_PARAM, new Object[] { emptyParameter }, null);
	}
}
