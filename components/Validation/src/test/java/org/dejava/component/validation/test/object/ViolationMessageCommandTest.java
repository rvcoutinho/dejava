package org.dejava.component.validation.test.object;

import java.util.Arrays;

import javax.validation.Validation;

import org.dejava.component.validation.object.ValidationException;
import org.dejava.component.validation.test.object.util.FakeApplicationMessageHandler;
import org.dejava.component.validation.test.object.util.FakeEntity;
import org.dejava.component.validation.test.object.util.SubFakeEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the violation message command class.
 */
public class ViolationMessageCommandTest {

	/**
	 * Some invalid entity parameters.
	 */
	private static final Object[][] INVALID_ENTITY_PARAMS = {
			{ null, 120, new String[] { "Numero grande 100" } },
			{ "cdvxc", null, new String[] { "Email errado" } },
			{ "khdskjhccdslx", -9, new String[] { "Email errado", "Numero pequeno 0" } } };

	
	/**
	 * Tests the messages generated by the validation exception class.
	 */
	@Test
	public void testViolationExceptionMessages() {
		// For each invalid parameters.
		for (final Object[] currentParams : INVALID_ENTITY_PARAMS) {
			// Creates a new entity to be validated.
			final FakeEntity fakeEntity = new FakeEntity((String) currentParams[0],
					(Integer) currentParams[1]);
			// Tries to validate the entity.
			try {
				ValidationException.throwViolationExceptions(Validation.buildDefaultValidatorFactory()
						.getValidator().validate(fakeEntity));
				// If no exception is raised, fails the test.
				Assert.fail();
			}
			// If a validation exception is raised.
			catch (final ValidationException exception) {
				// Creates a new fake application message handler.
				final FakeApplicationMessageHandler messageHandler = new FakeApplicationMessageHandler();
				// Adds the violation messages to the fake application message handler.
				exception.addLocalizedMessages(messageHandler);
				// Asserts that the expected messages are there.
				Assert.assertTrue(messageHandler.getFakeAppMessageContext().containsAll(
						Arrays.asList((String[]) currentParams[2])));
			}
		}
	}
	
	/**
	 * Some invalid entity parameters (using wild cards).
	 */
	private static final Object[][] INVALID_ENTITY_PARAMS_WC = {
			{ null, 120, new String[] { "Numero grande (sub) 100" } },
			{ "cdvxc", null, new String[] { "Email errado" } },
			{ "khdskjhccdslx", -9, new String[] { "Email errado", "Numero pequeno 0" } } };

	
	/**
	 * Tests the messages generated by the validation exception class (using wild cards).
	 */
	@Test
	public void testViolationExceptionMessagesWithWildCards() {
		// For each invalid parameters.
		for (final Object[] currentParams : INVALID_ENTITY_PARAMS_WC) {
			// Creates a new entity to be validated.
			final FakeEntity fakeEntity = new SubFakeEntity((String) currentParams[0],
					(Integer) currentParams[1]);
			// Tries to validate the entity.
			try {
				ValidationException.throwViolationExceptions(Validation.buildDefaultValidatorFactory()
						.getValidator().validate(fakeEntity));
				// If no exception is raised, fails the test.
				Assert.fail();
			}
			// If a validation exception is raised.
			catch (final ValidationException exception) {
				// Creates a new fake application message handler.
				final FakeApplicationMessageHandler messageHandler = new FakeApplicationMessageHandler();
				// Adds the violation messages to the fake application message handler.
				exception.addLocalizedMessages(messageHandler);
				// Asserts that the expected messages are there.
				Assert.assertTrue(messageHandler.getFakeAppMessageContext().containsAll(
						Arrays.asList((String[]) currentParams[2])));
			}
		}
	}
}
