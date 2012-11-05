package org.dejava.component.i18n.test.message;

import java.util.ArrayList;
import java.util.Collection;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;
import org.dejava.component.i18n.test.message.auxiliary.TestMessageCommand;
import org.dejava.component.i18n.test.message.constant.SomeKeys;
import org.dejava.component.test.annotation.ParametricTest;
import org.dejava.component.test.runner.JUnitParametricRunner;
import org.dejava.component.test.runner.dataset.impl.StaticMethodTestDataProvider;
import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * Tests for the default i18n message handler.
 */
@MessageBundles(defaultType = "stacktrace1", messageBundles = {
		@MessageBundle(type = "stacktrace1", baseName = "org.dejava.component.i18n.test.message.properties.stacktrace1"),
		@MessageBundle(type = "stacktrace2", baseName = "org.dejava.component.i18n.test.message.properties.stacktrace2") })
@RunWith(value = JUnitParametricRunner.class)
public class SimpleMessageHandlerTest {

	/**
	 * TODO
	 * 
	 * @return TODO
	 */
	public static Collection<TestMessageCommand> getValidMessageCommands() {
		// Creates a new list with valid message commands to be tested.
		Collection<TestMessageCommand> msgCommands = new ArrayList<>();
		
		// Test commands giving no bundle info (using the stack trace).
		msgCommands.add(new TestMessageCommand(null, null, null, SomeKeys.KEY1, null, ""));
		
		
		// Returns the list.
		return msgCommands;
	}

	/**
	 * Tests the simple message handler with valid parameters.
	 * 
	 * @param testMessageCommand
	 *            The command with the required information to get a message (using the message handler).
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getValidMessageCommands" })
	public void testGetMessageValidParams(final TestMessageCommand testMessageCommand) {
		// Assert that the message is the expected one.
		Assert.assertEquals("TODO", testMessageCommand.getExpectedMessage(), testMessageCommand.getMessage());
	}

}
