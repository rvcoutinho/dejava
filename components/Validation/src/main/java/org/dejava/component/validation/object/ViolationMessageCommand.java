package org.dejava.component.validation.object;

import java.util.Locale;
import java.util.TreeMap;

import javax.validation.Payload;
import javax.validation.metadata.ConstraintDescriptor;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;
import org.dejava.component.reflection.ClassMirror;

/**
 * Violation message command.
 */
public class ViolationMessageCommand extends SimpleMessageCommand {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5305193574153456484L;

	/**
	 * Gets the payload that contains the message bundle information.
	 * 
	 * @param constraintDescriptor
	 *            The constraint descriptor to get the payload from.
	 * @return The payload that contains the message bundle information.
	 */
	private static Class<? extends Payload> getBundlePayload(
			final ConstraintDescriptor<?> constraintDescriptor) {
		// For each payload.
		for (final Class<? extends Payload> currentPayload : constraintDescriptor.getPayload()) {
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
	 * @param constraintDescriptor
	 *            The constraint descriptor to get the message parameters from.
	 * @return The parameters for the violation message (constraint attributes).
	 */
	private static Object[] getViolationParameters(final ConstraintDescriptor<?> constraintDescriptor) {
		// Gets the attributes for the constraint and make sure they are ordered by name.
		final TreeMap<String, Object> constraintAttrs = new TreeMap<>(constraintDescriptor.getAttributes());
		// Exclude the default ones.
		constraintAttrs.remove("groups");
		constraintAttrs.remove("message");
		constraintAttrs.remove("payload");
		// Gets the attributes ordered by name.
		return constraintAttrs.values().toArray();
	}

	/**
	 * Gets the message command for the given violation.
	 * 
	 * @param constraintDescriptor
	 *            The constraint descriptor for the message.
	 * @param locale
	 *            The locale for the message.
	 * @param messageTemplate
	 *            The message template to be used.
	 */
	public ViolationMessageCommand(final ConstraintDescriptor<?> constraintDescriptor, final Locale locale,
			final String messageTemplate) {
		super(getBundlePayload(constraintDescriptor), locale, messageTemplate,
				getViolationParameters(constraintDescriptor));
	}
}
