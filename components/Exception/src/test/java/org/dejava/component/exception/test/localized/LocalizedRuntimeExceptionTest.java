package org.dejava.component.exception.test.localized;

import java.util.Locale;

import org.dejava.component.exception.test.constant.ErrorKeys;
import org.dejava.component.exception.test.util.FakeApplicationMessageHandler;
import org.dejava.component.exception.test.util.LocalizedRuntimeException;
import org.dejava.component.exception.test.util.MessageTypes;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the localized runtime exception.
 */
public class LocalizedRuntimeExceptionTest {

	/**
	 * Affix to be used in pt_BR messages.
	 */
	private static final String BR_AFFIX = ".br";

	/**
	 * Affix to be used in en_US messages.
	 */
	private static final String US_AFFIX = ".us";

	/**
	 * Tests the get message method.
	 */
	@Test
	public void testGetMessage() {
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.GENERIC, null, null);
		// Sets the locale to pt_BR.
		localizedException.setLocale(new Locale("pt_BR"));
		// Asserts that the message is the expected one.
		Assert.assertEquals(ErrorKeys.GENERIC + US_AFFIX, localizedException.getMessage());
	}

	/**
	 * Tests the get localized message method.
	 */
	@Test
	public void testGetLocalizedMessage() {
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.GENERIC, null, null);
		// Sets the locale to pt_BR.
		localizedException.setLocale(new Locale("pt_BR"));
		// Asserts that the message is the expected one.
		Assert.assertEquals(ErrorKeys.GENERIC + BR_AFFIX, localizedException.getLocalizedMessage());
	}

	/**
	 * Tests the add localized message method.
	 */
	@Test
	public void testAddLocalizedMessage() {
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.GENERIC, null, null);
		// Sets the locale to pt_BR.
		localizedException.setLocale(new Locale("pt_BR"));
		// Creates a new fake application message handler.
		final FakeApplicationMessageHandler appMessageHandler = new FakeApplicationMessageHandler();
		// Adds the localized message for the exception to the "application".
		localizedException.addLocalizedMessages(appMessageHandler);
		// Asserts that there is only one message in the "application".
		Assert.assertEquals(1, appMessageHandler.getFakeAppMessageContext().size());
		// Asserts that the message in the "application" is the expected one.
		Assert.assertEquals(ErrorKeys.GENERIC + BR_AFFIX, appMessageHandler.getFakeAppMessageContext().get(0));
	}

	/**
	 * Tests the add localized message method with recursive messages (for exception causes).
	 */
	@Test
	public void testAddLocalizedMessageRecursive() {
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.INVALID_PARAM, null, null);
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException2 = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.GENERIC, null, localizedException);
		// Sets the locale to pt_BR.
		localizedException.setLocale(new Locale("pt_BR"));
		localizedException2.setLocale(new Locale("pt_BR"));
		// Creates a new fake application message handler.
		final FakeApplicationMessageHandler appMessageHandler = new FakeApplicationMessageHandler();
		// Adds the localized message for the exception to the "application".
		localizedException2.addLocalizedMessages(appMessageHandler);
		// Asserts that there is only one message in the "application".
		Assert.assertEquals(2, appMessageHandler.getFakeAppMessageContext().size());
		// Asserts that the messages in the "application" are the expected ones.
		Assert.assertEquals(ErrorKeys.GENERIC + BR_AFFIX, appMessageHandler.getFakeAppMessageContext().get(0));
		Assert.assertEquals(ErrorKeys.INVALID_PARAM + BR_AFFIX, appMessageHandler.getFakeAppMessageContext()
				.get(1));
	}

	/**
	 * Tests the add localized message method with recursive messages (for exception causes that are not
	 * localized).
	 */
	@Test
	public void testAddLocalizedMessageRecursiveNonLocalized() {
		// Creates a new exception.
		final Exception exception = new Exception();
		// Creates a new localized exception.
		final LocalizedRuntimeException localizedException = new LocalizedRuntimeException(
				MessageTypes.Error.class, ErrorKeys.GENERIC, null, exception);
		// Sets the locale to pt_BR.
		localizedException.setLocale(new Locale("pt_BR"));
		// Creates a new fake application message handler.
		final FakeApplicationMessageHandler appMessageHandler = new FakeApplicationMessageHandler();
		// Adds the localized message for the exception to the "application".
		localizedException.addLocalizedMessages(appMessageHandler);
		// Asserts that there is only one message in the "application".
		Assert.assertEquals(2, appMessageHandler.getFakeAppMessageContext().size());
		// Asserts that the messages in the "application" are the expected ones.
		Assert.assertEquals(ErrorKeys.GENERIC + BR_AFFIX, appMessageHandler.getFakeAppMessageContext().get(0));
		Assert.assertEquals(
				SimpleMessageHandler.getMessageHandler(Locale.getDefault()).getMessage(
						org.dejava.component.exception.util.MessageTypes.Error.class, new Locale("pt_BR"),
						ErrorKeys.GENERIC, null), appMessageHandler.getFakeAppMessageContext().get(1));
	}

}
