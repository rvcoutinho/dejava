package org.dejava.component.validation.object;

import java.util.Set;
import java.util.TreeMap;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;

import org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageCommand;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;
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
	 *            The violation to get the payload from.
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
	 * Gets the parameters for the violation message (constraint attributes).
	 * 
	 * @param violation
	 *            The violation to get the message parameters from.
	 * @return The parameters for the violation message (constraint attributes).
	 */
	private Object[] getViolationParameters(final ConstraintViolation<?> violation) {
		// Gets the attributes for the constraint and make sure they are ordered by name.
		final TreeMap<String, Object> constraintAttrs = new TreeMap<>(violation.getConstraintDescriptor()
				.getAttributes());
		// Exclude the default ones.
		constraintAttrs.remove("groups");
		constraintAttrs.remove("message");
		constraintAttrs.remove("payload");
		// Gets the attributes ordered by name.
		return constraintAttrs.values().toArray();
	}

	/**
	 * The message handler to be used.
	 */
	private MessageHandler messageHandler;

	/**
	 * Gets the messageHandler.
	 * 
	 * @return The messageHandler.
	 */
	@Override
	public MessageHandler getMessageHandler() {
		// If the message handler is null.
		if (messageHandler == null) {
			// Creates a new simple message handler.
			messageHandler = SimpleMessageHandler.getDefaultMessageHandler();
		}
		// Returns the message handler.
		return messageHandler;
	}

	/**
	 * Sets the messageHandler.
	 * 
	 * @param messageHandler
	 *            New messageHandler.
	 */
	@Override
	public void setMessageHandler(final MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	/**
	 * Basic constructor.
	 * 
	 * @param violations
	 *            Violations related to the exception.
	 */
	public ValidationException(final Set<ConstraintViolation<?>> violations) {
		super();
		// Sets the violations.
		this.violations = violations;
	}

	/**
	 * Gets the message command for the given violation.
	 * 
	 * @param violation
	 *            The violation to get the message from.
	 * @return The message command for the given violation.
	 */
	private MessageCommand getViolationMessage(final ConstraintViolation<?> violation) {
		return new SimpleMessageCommand(getBundlePayload(violation), getLocale(),
				violation.getMessageTemplate(), getViolationParameters(violation));
	}

	/**
	 * @see org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException#addLocalizedMessages(org.dejava.component.i18n.message.handler.ApplicationMessageHandler)
	 */
	@Override
	public void addLocalizedMessages(final ApplicationMessageHandler appMessageHandler) {
		// If there are constraint violations.
		if (getViolations() != null) {
			// For each constraint violation.
			for (final ConstraintViolation<?> currentViolation : getViolations()) {
				// Adds the message for the current violation.
				getViolationMessage(currentViolation).addMessage(appMessageHandler);
			}
		}
	}

}
