package org.dejava.component.util.i18n.test.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import junit.framework.Assert;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.util.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.util.i18n.message.annotation.MessageBundle;
import org.dejava.component.util.i18n.message.annotation.MessageBundles;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.MessageHandler;
import org.dejava.component.util.i18n.message.handler.impl.DefaultI18nMessageHandler;
import org.dejava.component.util.i18n.message.handler.model.ApplicationMessageType;
import org.dejava.component.util.i18n.test.message.constant.InformationKeys;
import org.dejava.component.util.reflection.ClassMirror;
import org.dejava.component.util.reflection.FieldMirror;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the default i18n message handler.
 */
@MessageBundles(defaultType = "information", messageBundles = {
		@MessageBundle(type = "information", baseName = "org.dejava.component.util.i18n.test.message.properties.information"),
		@MessageBundle(type = "error", baseName = "org.dejava.component.util.i18n.test.message.properties.error") })
@RunWith(value = ParametricJUnitRunner.class)
public class DefaultI18nMessageHandlerTest {
	
	/**
	 * The information message bundle name.
	 */
	private static final String INFO_BUNDLE = "information";
	
	/**
	 * The error message bundle name.
	 */
	private static final String ERROR_BUNDLE = "error";
	
	/**
	 * Tests getting a message by key with a null key.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetMessageByKeyNullKey() throws MessageNotFoundException, InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage(null, null);
	}
	
	/**
	 * Tests getting a message by type (type) and key with a null key.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetMessageByTypeAndKeyNullKey() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage(ApplicationMessageType.ERROR, null, null);
	}
	
	/**
	 * Tests getting a message by type (String) and key with a null key.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetMessageByStringTypeAndKeyNullKey() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage(ERROR_BUNDLE, null, null);
	}
	
	/**
	 * Tests getting a message by key that is not found.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = MessageNotFoundException.class)
	public void testGetMessageByKeyNoKeyFound() throws MessageNotFoundException, InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage("test", null);
	}
	
	/**
	 * Tests getting a message by type (type) and key that is not found.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = MessageNotFoundException.class)
	public void testGetMessageByTypeAndKeyKeyNoKeyFound() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage(ApplicationMessageType.INFORMATION, "test", null);
	}
	
	/**
	 * Tests getting a message by type (String) and key that is not found.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test(expected = MessageNotFoundException.class)
	public void testGetMessageByStringTypeAndKeyKeyNoKeyFound() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		messageHandler.getMessage(INFO_BUNDLE, "test", null);
	}
	
	/**
	 * Tests getting a message by key for a not found locale.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test
	public void testGetMessageByKeyNoLocaleFound() throws MessageNotFoundException, InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler1 = DefaultI18nMessageHandler.getMessageHandler(Locale.CHINESE);
		// Tries to get the message.
		final String message1 = messageHandler1.getMessage(InformationKeys.SUCCESS1, null);
		// Gets the message handler.
		final MessageHandler messageHandler2 = DefaultI18nMessageHandler.getMessageHandler(Locale
				.getDefault());
		// Tries to get the message.
		final String message2 = messageHandler2.getMessage(InformationKeys.SUCCESS1, null);
		// Assert that the messages are the same.
		Assert.assertEquals(message1, message2);
	}
	
	/**
	 * Tests getting a message by type (type) and key for a not found locale.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test
	public void testGetMessageByTypeAndKeyKeyNoLocaleFound() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler1 = DefaultI18nMessageHandler.getMessageHandler(Locale.CHINESE);
		// Tries to get the message.
		final String message1 = messageHandler1.getMessage(ApplicationMessageType.INFORMATION,
				InformationKeys.SUCCESS1, null);
		// Gets the message handler.
		final MessageHandler messageHandler2 = DefaultI18nMessageHandler.getMessageHandler(Locale
				.getDefault());
		// Tries to get the message.
		final String message2 = messageHandler2.getMessage(ApplicationMessageType.INFORMATION,
				InformationKeys.SUCCESS1, null);
		// Assert that the messages are the same.
		Assert.assertEquals(message1, message2);
	}
	
	/**
	 * Tests getting a message by type (String) and key for a not found locale.
	 * 
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	@Test
	public void testGetMessageByStringTypeAndKeyKeyNoLocaleFound() throws MessageNotFoundException,
			InvalidParameterException {
		// Gets the message handler.
		final MessageHandler messageHandler1 = DefaultI18nMessageHandler.getMessageHandler(Locale.CHINESE);
		// Tries to get the message.
		final String message1 = messageHandler1.getMessage(INFO_BUNDLE, InformationKeys.SUCCESS1, null);
		// Gets the message handler.
		final MessageHandler messageHandler2 = DefaultI18nMessageHandler.getMessageHandler(Locale
				.getDefault());
		// Tries to get the message.
		final String message2 = messageHandler2.getMessage(INFO_BUNDLE, InformationKeys.SUCCESS1, null);
		// Assert that the messages are the same.
		Assert.assertEquals(message1, message2);
	}
	
	/**
	 * Gets the keys defined in the information key class.
	 * 
	 * @return The keys defined in the information key class.
	 */
	public static Collection<String> testGetMessageSuccessKeys() {
		// Creates the list of the keys.
		final Collection<String> keys = new ArrayList<>();
		// Gets the information key class.
		final ClassMirror<InformationKeys> infoKeys = new ClassMirror<>(InformationKeys.class);
		// For each field in the class.
		for (final FieldMirror currentField : infoKeys.getFields()) {
			// Tries to add the current key to the list.
			try {
				keys.add((String) currentField.getValue(null, true, false));
			}
			// If the field value cannot be retrieved.
			catch (final Exception exception) {
				// Ignores and continues the loop.
			}
		}
		// Returns the retrieved keys.
		return keys;
	}
	
	/**
	 * Tests getting a message by an existent key.
	 * 
	 * @param key
	 *            A key that exists in the resource bundle.
	 */
	@ParametricTest(paramsValues = { "testGetMessageSuccessKeys" })
	public void testGetMessageByKeySuccess(final String key) {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		final String message = messageHandler.getMessage(key, null);
		// Assert that the message was retrieved (key exists).
		Assert.assertNotNull(message);
	}
	
	/**
	 * Tests getting a message by type (type) and an existent key.
	 * 
	 * @param key
	 *            A key that exists in the resource bundle.
	 */
	@ParametricTest(paramsValues = { "testGetMessageSuccessKeys" })
	public void testGetMessageByTypeAndKeySuccess(final String key) {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		final String message = messageHandler.getMessage(ApplicationMessageType.INFORMATION, key, null);
		// Assert that the message was retrieved (key exists).
		Assert.assertNotNull(message);
	}
	
	/**
	 * Tests getting a message by type (String) and an existent key.
	 * 
	 * @param key
	 *            A key that exists in the resource bundle.
	 */
	@ParametricTest(paramsValues = { "testGetMessageSuccessKeys" })
	public void testGetMessageByStringTypeAndKeySuccess(final String key) {
		// Gets the message handler.
		final MessageHandler messageHandler = DefaultI18nMessageHandler.getMessageHandler(new Locale("pt",
				"BR"));
		// Tries to get the message.
		final String message = messageHandler.getMessage(INFO_BUNDLE, key, null);
		// Assert that the message was retrieved (key exists).
		Assert.assertNotNull(message);
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testGetMessageByKeyWithParametersSuccess() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testGetMessageByTypeAndKeyWithParametersSuccess() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testGetMessageByStringTypeAndKeyWithParametersSuccess() {
		
	}
	
}
