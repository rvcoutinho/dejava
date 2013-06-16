package org.dejava.component.reflection.test;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.reflection.FieldMirror;
import org.dejava.component.reflection.MethodMirror;
import org.dejava.component.reflection.test.util.SomeClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the method mirror.
 */
public class FieldMirrorTest {

	/**
	 * Tests the constructor for a null field value (expects an empty parameter exception).
	 */
	@Test(expected = EmptyParameterException.class)
	public void testConstructorNullField() {
		// Tries to create a new field mirror for a null field value.
		new FieldMirror(null);
	}

	/**
	 * Tests the getGetter method for a field that has no getter.
	 */
	@Test(expected = InvalidParameterException.class)
	public void testGetGetterNoGetter() {
		// Creates a new class mirror.
		final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
		// Gets a field for the class.
		final FieldMirror field = someClass.getField("fieldA");
		// Tries to get the getter for the field.
		field.getGetter();
	}

	/**
	 * Tests the getGetter method for a field that has a regular getter.
	 */
	@Test
	public void testGetGetterRegularGetter() {
		// Creates a new class mirror.
		final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
		// Gets a field for the class.
		final FieldMirror field = someClass.getField("fieldC");
		// Creates a new instance for the class.
		final SomeClass someClassInstance = new SomeClass();
		// Sets the value for the field.
		someClassInstance.setFieldC(1);
		// Tries to get the getter for the field.
		final MethodMirror getter = field.getGetter();
		// Ensures that the getter returns the expected value.
		Assert.assertEquals(1, getter.invokeMethod(someClassInstance, false, null));
	}

}
