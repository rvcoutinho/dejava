package org.dejava.component.i18n.test.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.dejava.component.i18n.test.message.constant.SomeKeys;
import org.dejava.component.i18n.test.message.util.AnnotatedClass;
import org.dejava.component.i18n.test.message.util.AnnotatedInterface;
import org.dejava.component.i18n.test.message.util.AnnotatedSuperClass;
import org.dejava.component.i18n.test.message.util.TestMessageCommand;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the default i18n message handler.
 */
public class SimpleMessageHandlerTest {

	/**
	 * Locale (en_US) message.
	 */
	private static final String EN_US = "(en_US)";

	/**
	 * Locale (pt_BR) message.
	 */
	private static final String PT_BR = "(pt_BR)";

	/**
	 * Class message.
	 */
	private static final String CLASS = "class";

	/**
	 * Super class key.
	 */
	private static final String SUPERCLASS_KEY = "somekeys.somekey";

	/**
	 * Super class message.
	 */
	private static final String SUPERCLASS = "superclass";

	/**
	 * Interface key.
	 */
	private static final String INTERFACE_KEY = "somekeys";

	/**
	 * Interface message.
	 */
	private static final String INTERFACE = "interface";

	/**
	 * Some parameter to be used in the message retrieval.
	 */
	private static final String PARAM_1 = "some param";

	/**
	 * Some parameter to be used in the message retrieval.
	 */
	private static final String PARAM_2 = "another param";

	/**
	 * Some parameter to be used in the message retrieval.
	 */
	private static final String PARAM_3 = "yet another param";

	/**
	 * Some parameter to be used in the message retrieval.
	 */
	private static final String PARAM_4 = "last param";

	/**
	 * Gets some valid message commands to be tested.
	 * 
	 * @return Some valid message commands to be tested.
	 */
	public static Collection<TestMessageCommand> getValidMessageCommands() {
		// Creates a new list with valid message commands to be tested.
		final Collection<TestMessageCommand> msgCommands = new ArrayList<>();
		// Adds test commands with different locales.
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, Locale.US, SomeKeys.KEY1, null, CLASS
				+ EN_US));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, new Locale("pt", "BR"), SomeKeys.KEY1,
				null, CLASS + PT_BR));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, Locale.CHINESE, SomeKeys.KEY1, null,
				CLASS + PT_BR));
		msgCommands
				.add(new TestMessageCommand(AnnotatedClass.class, null, SomeKeys.KEY1, null, CLASS + PT_BR));
		// Adds test commands with parameters.
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, SomeKeys.KEY2,
				new Object[] { PARAM_1 }, CLASS + PT_BR + " " + PARAM_1));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, Locale.US, SomeKeys.KEY2,
				new Object[] { PARAM_2 }, CLASS + EN_US + " " + PARAM_2));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, SomeKeys.KEY2, new Object[] {
				PARAM_3, PARAM_4 }, CLASS + PT_BR + " " + PARAM_3));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, Locale.US, SomeKeys.KEY3, new Object[] {
				PARAM_2, PARAM_4 }, CLASS + EN_US + " " + PARAM_2 + " " + PARAM_4));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, SomeKeys.KEY3,
				new Object[] { PARAM_3 }, CLASS + PT_BR + " " + PARAM_3 + " {1}"));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, SomeKeys.KEY1,
				new Object[] { PARAM_3 }, CLASS + PT_BR));
		// Adds test commands for bundles in super classes and interfaces.
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, INTERFACE_KEY, null, INTERFACE
				+ PT_BR));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, Locale.US, SUPERCLASS_KEY, null,
				SUPERCLASS + EN_US));
		// Returns the list.
		return msgCommands;
	}

	/**
	 * Tests the simple message handler with valid parameters.
	 */
	@Test
	public void testGetMessageValidParams() {
		// For each valid message.
		for (final TestMessageCommand testMsgCommand : getValidMessageCommands()) {
			// Assert that the message is the expected one.
			Assert.assertEquals("The retrieved message is different than the expected.",
					testMsgCommand.getExpectedMessage(),
					testMsgCommand.getMessage(SimpleMessageHandler.getMessageHandler(Locale.getDefault())));
		}
	}

	/**
	 * Gets some invalid message commands to be tested.
	 * 
	 * @return Some invalid message commands to be tested.
	 */
	public static Collection<TestMessageCommand> getInvalidMessageCommands() {
		// Creates a new list with valid message commands to be tested.
		final Collection<TestMessageCommand> msgCommands = new ArrayList<>();
		// Adds test commands with no keys.
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, null, null, null));
		msgCommands.add(new TestMessageCommand(AnnotatedClass.class, null, "", null, null));
		// Adds test commands with no type.
		msgCommands.add(new TestMessageCommand(null, null, SUPERCLASS_KEY, null, null));
		// Returns the list.
		return msgCommands;
	}

	/**
	 * Tests the simple message handler with invalid parameters.
	 */
	@Test
	public void testGetMessageInvalidParams() {
		// For each invalid message.
		for (final TestMessageCommand testMsgCommand : getInvalidMessageCommands()) {
			// Tries to get the message from the command.
			try {
				testMsgCommand.getMessage(SimpleMessageHandler.getMessageHandler(Locale.getDefault()));
				// If no exception is thrown, fails the test.
				Assert.fail();
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Ignores it.
			}
		}

	}

	/**
	 * Gets some message commands (for messages that cannot be found) to be tested.
	 * 
	 * @return Some message commands (for messages that cannot be found) to be tested.
	 */
	public static Collection<TestMessageCommand> getMessageCommandsNoMessages() {
		// Creates a new list with valid message commands to be tested.
		final Collection<TestMessageCommand> msgCommands = new ArrayList<>();
		// Adds test commands with inexistent keys.
		msgCommands.add(new TestMessageCommand(AnnotatedInterface.class, null, SomeKeys.KEY1, null, null));
		msgCommands.add(new TestMessageCommand(AnnotatedInterface.class, null, SUPERCLASS_KEY, null, null));
		msgCommands.add(new TestMessageCommand(AnnotatedSuperClass.class, null, SomeKeys.KEY1, null, null));
		msgCommands.add(new TestMessageCommand(AnnotatedSuperClass.class, null, INTERFACE_KEY, null, null));
		msgCommands.add(new TestMessageCommand(AnnotatedSuperClass.class, null, SomeKeys.KEY3,
				new Object[] { 1 }, null));
		// Returns the list.
		return msgCommands;
	}

	/**
	 * Tests the simple message handler with valid parameters (yet for messages that cannot be found), and
	 * with message not found exceptions expected.
	 */
	@Test
	public void testGetMessageNoMessageException() {
		// For each command with no predefined message.
		for (final TestMessageCommand testMsgCommand : getMessageCommandsNoMessages()) {
			// Tries to get the message from the command.
			try {
				testMsgCommand.getMessage(SimpleMessageHandler.getMessageHandler(Locale.getDefault(), true));
				// If no exception is thrown, fails the test.
				Assert.fail();
			}
			// If an invalid parameter exception is thrown.
			catch (final MessageNotFoundException exception) {
				// Ignores it.
			}
		}
	}

	/**
	 * Tests the simple message handler with valid parameters (yet for messages that cannot be found), and
	 * with no message not found exceptions expected.
	 */
	@Test
	public void testGetMessageNoMessageNoException() {
		// For each command with no predefined message.
		for (final TestMessageCommand testMsgCommand : getMessageCommandsNoMessages()) {
			// Tries to get the message from the command.
			String message = testMsgCommand.getMessage(SimpleMessageHandler.getMessageHandler(Locale
					.getDefault()));
			// Asserts that the message is equals to the given key.
			Assert.assertEquals(testMsgCommand.getMessageKey(), message);
		}
	}
}
