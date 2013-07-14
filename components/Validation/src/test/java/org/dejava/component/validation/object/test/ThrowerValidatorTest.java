package org.dejava.component.validation.object.test;

import org.dejava.component.validation.object.ThrowerValidator;
import org.dejava.component.validation.object.ValidationException;
import org.dejava.component.validation.object.test.util.FakeEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the thrower validator.
 */
public class ThrowerValidatorTest {

	/**
	 * Some valid entity parameters.
	 */
	private static final Object[][] VALID_ENTITY_PARAMS = { { null, null }, { null, 10 },
			{ "rvcoutinho@gmail.com", null }, { "fkjdnb@hde.com", 15 } };

	/**
	 * Tests the validation of an entity with valid states.
	 */
	@Test
	public void testValidState() {
		// For each valid parameters.
		for (final Object[] currentParams : VALID_ENTITY_PARAMS) {
			// Creates a new entity to be validated.
			final FakeEntity fakeEntity = new FakeEntity((String) currentParams[0],
					(Integer) currentParams[1]);
			// Tries to validate the entity.
			ThrowerValidator.getDefaultThrowerValidator().validate(fakeEntity);
		}
	}

	/**
	 * Some invalid entity parameters.
	 */
	private static final Object[][] INVALID_ENTITY_PARAMS = { { null, 120, 1 }, { "cdvxc", null, 1 },
			{ "khdskjhccdslx", -9, 2 } };

	/**
	 * Tests the validation of an entity with invalid states.
	 */
	@Test
	public void testInvalidState() {
		// For each valid parameters.
		for (final Object[] currentParams : INVALID_ENTITY_PARAMS) {
			// Creates a new entity to be validated.
			final FakeEntity fakeEntity = new FakeEntity((String) currentParams[0],
					(Integer) currentParams[1]);
			// Tries to validate the entity.
			try {
				ThrowerValidator.getDefaultThrowerValidator().validate(fakeEntity);
				// If no exceptions is thrown, fails the test.
				Assert.fail();
			}
			// If a validation exception is thrown.
			catch (final ValidationException exception) {
				// Asserts that the number of violations are correct.
				Assert.assertEquals(currentParams[2], exception.getViolations().size());
			}
		}
	}

}
