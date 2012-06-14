package org.dejava.component.util.i18n.test.message;

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
	 * Tests the getting a message by key with a null key.
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
	 * Tests the getting a message by type (type) and key with a null key.
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
	 * Tests the getting a message by type (String) and key with a null key.
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
	 * Tests the getting a message by key that is not found.
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
	 * Tests the getting a message by type (type) and key that is not found.
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
	 * Tests the getting a message by type (String) and key that is not found.
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
	 * Tests the getting a message by key for a not found locale.
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
	 * Tests the getting a message by type (type) and key for a not found locale.
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
	 * Tests the getting a message by type (String) and key for a not found locale.
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
	 * TODO
	 */
	@Test
	public void testGetMessageByKeySuccess() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testGetMessageByTypeAndKeySuccess() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testGetMessageByStringTypeAndKeySuccess() {
		
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
