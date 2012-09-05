package org.dejava.component.i18n.test.sample;

import java.util.Locale;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.test.message.annotation.MessageBundle;
import org.dejava.component.i18n.test.message.annotation.MessageBundles;
import org.dejava.component.i18n.test.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.test.message.handler.MessageHandler;
import org.dejava.component.i18n.test.message.handler.impl.DefaultI18nMessageHandler;
import org.dejava.component.i18n.test.sample.constant.ErrorKeys;
import org.dejava.component.i18n.test.sample.constant.InformationKeys;

/**
 * Java I18n API sample.
 */
@MessageBundles(defaultType = "information", messageBundles = {
		@MessageBundle(type = "information", baseName = "org.dejava.component.i18n.test.sample.properties.information"),
		@MessageBundle(type = "error", baseName = "org.dejava.component.i18n.test.sample.properties.error") })
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
