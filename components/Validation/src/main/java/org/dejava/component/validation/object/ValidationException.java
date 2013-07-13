package org.dejava.component.validation.object;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;

import org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.dejava.component.reflection.ClassMirror;

/**
 * Exception related to constraint violations on a validation.
 */
public class ValidationException extends AbstractLocalizedRuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 6656566241726366508L;

	/**
	 * Violations related to the exception.
	 */
	private Set<ConstraintViolation<?>> violations;

	/**
	 * Gets the violations related to the exception.
	 * 
	 * @return The violations related to the exception.
	 */
	public Set<ConstraintViolation<?>> getViolations() {
		return violations;
	}

	/**
	 * Sets the violations related to the exception.
	 * 
	 * @param violations
	 *            New violations related to the exception.
	 */
	public void setViolations(final Set<ConstraintViolation<?>> violations) {
		this.violations = violations;
	}

	/**
	 * Gets the payload that contains the message bundle information.
	 * 
	 * @param violation
	 *            The violation to get th epayload from.
	 * @return The payload that contains the message bundle information.
	 */
	private Class<? extends Payload> getBundlePayload(final ConstraintViolation<?> violation) {
		// For each payload.
		for (final Class<? extends Payload> currentPayload : violation.getConstraintDescriptor().getPayload()) {
			// If the payload class contains message bundle information.
			if ((new ClassMirror<>(currentPayload).getAnnotation(MessageBundle.class)) != null) {
				// Returns the current payload class.
				return currentPayload;
			}
		}
		// If no payload has message bundle information, return null.
		return null;
	}

	/**
	 * TODO
	 * 
	 * @param violation
	 *            TODO
	 * @return TODO
	 */
	private Object[] getViolationParameters(final ConstraintViolation<?> violation) {

		// FIXME
		return null;
	}

	/**
	 * Gets the localized message for the given violation.
	 * 
	 * @param violation
	 *            The violation to get the message from.
	 * @return The localized message for the given violation.
	 */
	private String getViolationMessage(final ConstraintViolation<?> violation) {
		return SimpleMessageHandler.getMessageHandler(Locale.getDefault()).getMessage(
				getBundlePayload(violation), getLocale(), violation.getMessageTemplate(),
				getViolationParameters(violation));
	}

	/**
	 * Basic constructor.
	 * 
	 * @param violations
	 *            Violations related to the exception.
	 */
	public ValidationException(final Set<ConstraintViolation<?>> violations) {
		super(null, null, null, null);
		// Sets the violations.
		this.violations = violations;
	}

}
