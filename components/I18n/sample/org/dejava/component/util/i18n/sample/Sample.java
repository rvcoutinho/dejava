package org.dejava.component.util.i18n.sample;

import java.util.Locale;

import org.dejava.component.util.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.util.i18n.message.annotation.MessageBundle;
import org.dejava.component.util.i18n.message.annotation.MessageBundles;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.MessageHandler;
import org.dejava.component.util.i18n.message.handler.impl.DefaultI18nMessageHandler;
import org.dejava.component.util.i18n.test.constant.ErrorKeys;
import org.dejava.component.util.i18n.test.constant.InformationKeys;

/**
 * Java I18n API sample.
 */
@MessageBundles(defaultType = "information", messageBundles = {
		@MessageBundle(type = "information", baseName = "org.dejava.component.util.i18n.sample.properties.information"),
		@MessageBundle(type = "error", baseName = "org.dejava.component.util.i18n.sample.properties.error") })
public final class Sample {
	
	/**
	 * Default constructor for the i18n sample.
	 */
	private Sample() {
		super();
	}
	
	/**
	 * Internationalization framework sample.
	 * 
	 * @param params
	 *            Parameters for the method.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If the parameter values are not valid.
	 */
	public static void main(final String... params) throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the default message handler for the pt_BR locale.
		final MessageHandler ptbrMessageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale(
				"pt", "BR"));
		// Prints a message using the pt_BR locale and the default bundle.
		System.out.println(ptbrMessageHandler.getMessage(InformationKeys.SUCCESS, null));
		// Prints a message using the pt_BR locale, the "error" bundle and one parameter.
		System.out.println(ptbrMessageHandler.getMessage("error", ErrorKeys.FAILURE,
				new Object[] { "em português" }));
		// Gets the default message handler for the en_US locale.
		final MessageHandler enusMessageHandler = DefaultI18nMessageHandler.getMessageHandler(Locale.US);
		// Prints a message using the en_US locale and the default bundle.
		System.out.println(enusMessageHandler.getMessage(InformationKeys.SUCCESS, null));
		// Prints a message using the en_US locale, the "error" bundle and one parameter.
		System.out.println(enusMessageHandler.getMessage("error", ErrorKeys.FAILURE,
				new Object[] { "in English" }));
	}
}
