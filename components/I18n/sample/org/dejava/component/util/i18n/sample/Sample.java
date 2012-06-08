package org.dejava.component.util.i18n.sample;

import java.util.Locale;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.i18n.message.annotation.MessageBundle;
import org.dejava.component.util.i18n.message.annotation.MessageBundles;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.MessageHandler;
import org.dejava.component.util.i18n.message.handler.impl.DefaultMessageHandler;
import org.dejava.component.util.i18n.sample.constant.ErrorKeys;
import org.dejava.component.util.i18n.sample.constant.InformationKeys;

/**
 * I18n API sample.
 */
@MessageBundles(messageBundles = {
		@MessageBundle(type = "information", baseName = "org.dejava.component.util.i18n.sample.properties.information"),
		@MessageBundle(type = "error", baseName = "org.dejava.component.util.i18n.sample.properties.error") })
public class Sample {
	
	/**
	 * TODO
	 * 
	 * @param params
	 *            TODO
	 * @throws InvalidParameterException
	 *             TODO
	 * @throws MessageNotFoundException
	 *             TODO
	 */
	public static void main(final String... params) throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the default message handler for the pt_BR locale.
		final MessageHandler ptbrMessageHandler = DefaultMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Prints a message using the pt_BR locale.
		System.out.println(ptbrMessageHandler.getMessage("information", InformationKeys.SUCCESS, null));
		// Prints a message using the pt_BR locale.
		System.out.println(ptbrMessageHandler.getMessage("error", ErrorKeys.FAILURE, null));
		// Gets the default message handler for the en_US locale.
		final MessageHandler enusMessageHandler = DefaultMessageHandler.getMessageHandler(Locale.US);
		// Prints a message using the en_US locale.
		System.out.println(enusMessageHandler.getMessage("information", InformationKeys.SUCCESS, null));
		// Prints a message using the en_US locale.
		System.out.println(enusMessageHandler.getMessage("error", ErrorKeys.FAILURE, null));
	}
}
