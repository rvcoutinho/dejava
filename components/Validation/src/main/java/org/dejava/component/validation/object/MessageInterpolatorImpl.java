package org.dejava.component.validation.object;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;

/**
 * The message interpolator implementation with i18n.
 */
public class MessageInterpolatorImpl implements MessageInterpolator {

	/**
	 * The message handler to be used i the interpolation.
	 */
	private final MessageHandler messageHandler = SimpleMessageHandler.getMessageHandler(Locale.getDefault());

	/**
	 * Gets the message handler to be used i the interpolation.
	 * 
	 * @return The message handler to be used i the interpolation.
	 */
	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

	/**
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 *      javax.validation.MessageInterpolator.Context)
	 */
	@Override
	public String interpolate(final String messageTemplate, final Context context) {
		return interpolate(messageTemplate, context, Locale.getDefault());
	}

	/**
	 * @see javax.validation.MessageInterpolator#interpolate(java.lang.String,
	 *      javax.validation.MessageInterpolator.Context, java.util.Locale)
	 */
	@Override
	public String interpolate(final String messageTemplate, final Context context, final Locale locale) {
		return new ViolationMessageCommand(context.getConstraintDescriptor(), locale, messageTemplate)
				.getMessage(getMessageHandler());
	}

}
