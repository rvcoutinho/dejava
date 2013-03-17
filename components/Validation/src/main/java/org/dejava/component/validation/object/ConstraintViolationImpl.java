package org.dejava.component.validation.object;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Payload;
import javax.validation.metadata.ConstraintDescriptor;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.handler.MessageCommand;
import org.dejava.component.reflection.ClassMirror;

/**
 * TODO
 * 
 * @param <Entity>
 *            Entity whose constrain has been violated.
 */
public class ConstraintViolationImpl<Entity> implements ConstraintViolation<Entity> {

	/**
	 * Decorated constrain violation.
	 */
	ConstraintViolation<Entity> violation;

	/**
	 * Gets the decorated constrain violation.
	 * 
	 * @return The decorated constrain violation.
	 */
	public ConstraintViolation<Entity> getViolation() {
		return violation;
	}

	/**
	 * Sets the decorated constrain violation.
	 * 
	 * @param violation
	 *            New decorated constrain violation.
	 */
	public void setViolation(final ConstraintViolation<Entity> violation) {
		this.violation = violation;
	}

	/**
	 * Message command that holds the violation message information.
	 */
	private MessageCommand messageCommand;

	/**
	 * Gets the message command that holds the violation message information.
	 * 
	 * @return The message command that holds the violation message information.
	 */
	public MessageCommand getMessageCommand() {
		// If the message command is null.
		if (messageCommand == null) {
			// Creates a new message command. FIXME
			// messageCommand = new SimpleMessageCommand(getPayload(), Locale.getDefault(),
			// getMessageTemplate(), );
		}
		// Returns the message command.
		return messageCommand;
	}

	/**
	 * Sets the message command that holds the violation message information.
	 * 
	 * @param messageCommand
	 *            New message command that holds the violation message information.
	 */
	public void setMessageCommand(final MessageCommand messageCommand) {
		this.messageCommand = messageCommand;
	}

	/**
	 * @see javax.validation.ConstraintViolation#getMessage()
	 */
	@Override
	public String getMessage() {
		return getMessageCommand().getMessage();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getMessageTemplate()
	 */
	@Override
	public String getMessageTemplate() {
		return violation.getMessageTemplate();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getRootBean()
	 */
	@Override
	public Entity getRootBean() {
		return violation.getRootBean();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getRootBeanClass()
	 */
	@Override
	public Class<Entity> getRootBeanClass() {
		return violation.getRootBeanClass();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getLeafBean()
	 */
	@Override
	public Object getLeafBean() {
		return violation.getLeafBean();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getPropertyPath()
	 */
	@Override
	public Path getPropertyPath() {
		return violation.getPropertyPath();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getInvalidValue()
	 */
	@Override
	public Object getInvalidValue() {
		return violation.getInvalidValue();
	}

	/**
	 * @see javax.validation.ConstraintViolation#getConstraintDescriptor()
	 */
	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		return violation.getConstraintDescriptor();
	}

	/**
	 * Gets the payload that contains the message bundle information.
	 * 
	 * @return The payload that contains the message bundle information.
	 */
	private Class<? extends Payload> getBundlePayload() {
		// For each payload.
		for (final Class<? extends Payload> currentPayload : getConstraintDescriptor().getPayload()) {
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
	 * Default constructor.
	 * 
	 * @param violation
	 *            The original constrain violation.
	 */
	public ConstraintViolationImpl(final ConstraintViolation<Entity> violation) {
		this.violation = violation;
	}

}
