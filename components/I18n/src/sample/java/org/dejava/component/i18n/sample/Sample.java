package org.dejava.component.i18n.sample;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.dejava.component.i18n.sample.constant.ErrorKeys;
import org.dejava.component.i18n.sample.constant.InformationKeys;

/**
 * Java I18n API sample.
 */
public final class Sample {

	/**
	 * Default constructor for the i18n sample.
	 */
	private Sample() {
		super();
	}

	/**
	 * TODO
	 */
	private void regularI18nSample() {
		// Gets the bundle.
		final ResourceBundle bundle = ResourceBundle.getBundle(
				"org.dejava.component.i18n.test.sample.properties.information", Locale.US);
		// Prints the bundle entry value.
		System.out.println(bundle.getString(InformationKeys.SUCCESS));
	}

	/**
	 * TODO
	 */
	private void dejavaExtendedI18nSample() {
		// Gets the default message handler for the pt_BR locale.
		final MessageHandler ptbrMessageHandler = SimpleMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
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
		
		
	}
}
