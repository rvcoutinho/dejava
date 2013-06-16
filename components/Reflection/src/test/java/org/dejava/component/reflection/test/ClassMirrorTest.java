package org.dejava.component.reflection.test;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.RandomAccess;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.reflection.FieldMirror;
import org.dejava.component.reflection.test.util.SomeAnnotation;
import org.dejava.component.reflection.test.util.SomeClass;
import org.dejava.component.reflection.test.util.SomeOtherAnnotation;
import org.dejava.component.reflection.test.util.SomeSuperClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the class mirror.
 */
public class ClassMirrorTest {

	/**
	 * Valid classes names to be used in the tests.
	 */
	private static final String[] VALID_CLASSES_NAMES = { "java.lang.Object", "java.util.ArrayList",
			"org.junit.Assert", "org.dejava.component.reflection.ClassMirror" };

	/**
	 * Gets the parameters to be used in the testGetClassByClass method.
	 * 
	 * @return The parameters to be used in the testGetClassByClass method.
	 */
	public static Collection<String> getValidClassesNames() {
		// Returns some valid classes names.
		return new ArrayList<>(Arrays.asList(VALID_CLASSES_NAMES));
	}

	/**
	 * Tests the getClass method with valid classes names.
	 */
	@Test
	public void testGetClassByClassName() {
		// For each valid class name.
		for (final String curClassName : getValidClassesNames()) {
			// Tries to get the class via ClassMirror.
			final Class<?> clazz = ClassMirror.getClass(curClassName, null, true);
			// Assert that the names match.
			Assert.assertEquals(curClassName, clazz.getName());
		}
	}

	/**
	 * Tests the getClass method with valid classes names and class loaders. TODO
	 */
	public void testGetClassByClassNameAndClassLoader() {
	}

	/**
	 * Gets the stack trace as a map: class names by depth.
	 * 
	 * @return The stack trace as a map: class names by depth.
	 */
	public static Map<Integer, String> getStackTraceByDepth() {
		// Creates a new hash map for the stack entries (by their depths).
		final HashMap<Integer, String> stackByDepth = new HashMap<>();
		// Gets the stack trace.
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// For each entry in the stack trace (skipping the first two - this method and
		// Thread.getStackTrace()).
		for (Integer currentDepth = 0; currentDepth < (stackTrace.length - 2); currentDepth++) {
			// Adds the class in the entry (skipping the first) with the current depth.
			stackByDepth.put(currentDepth, stackTrace[currentDepth + 2].getClassName());
		}
		// Returns the map.
		return stackByDepth;
	}

	/**
	 * Tests the getCurrentClassName method with valid depths.
	 **/
	@Test
	public void testGetCurrentClassName() {
		// For each entry in the stack trace.
		for (final Entry<Integer, String> stackTraceEntry : getStackTraceByDepth().entrySet()) {
			// Tries to get the class for the given depth.
			final String currentClassName = ClassMirror.getCurrentClassName(stackTraceEntry.getKey());
			// Assert that the class name is correct.
			Assert.assertEquals(stackTraceEntry.getValue(), currentClassName);
		}
	}

	/**
	 * Tests the getCurrentClass method with valid depths.
	 **/
	@Test
	public void testGetCurrentClass() {
		// For each entry in the stack trace.
		for (final Entry<Integer, String> stackTraceEntry : getStackTraceByDepth().entrySet()) {
			// Tries to get the class for the given depth.
			final Class<?> currentClass = ClassMirror.getCurrentClass(stackTraceEntry.getKey());
			// Assert that the class name is correct.
			Assert.assertEquals(stackTraceEntry.getValue(), currentClass.getName());
		}
	}

	/**
	 * Test the getSuperClasses method.
	 */
	@Test
	public void testGetSuperClasses() {
		// Creates a new class mirror for the array list class.
		@SuppressWarnings("rawtypes")
		final ClassMirror<ArrayList> clazz = new ClassMirror<>(ArrayList.class);
		// Creates a new collection with the super classes of the array list class.
		final Collection<ClassMirror<?>> superClasses = new ArrayList<>();
		// Adds all its super classes (including itself).
		superClasses.add(clazz);
		superClasses.add(new ClassMirror<>(AbstractList.class));
		superClasses.add(new ClassMirror<>(AbstractCollection.class));
		superClasses.add(new ClassMirror<>(Collection.class));
		superClasses.add(new ClassMirror<>(Iterable.class));
		superClasses.add(new ClassMirror<>(List.class));
		superClasses.add(new ClassMirror<>(RandomAccess.class));
		superClasses.add(new ClassMirror<>(Cloneable.class));
		superClasses.add(new ClassMirror<>(Serializable.class));
		superClasses.add(new ClassMirror<>(Object.class));
		// Assert that the super classes are the same.
		Assert.assertTrue(clazz.getSuperClasses().containsAll(superClasses));
		Assert.assertTrue(superClasses.containsAll(clazz.getSuperClasses()));
	}

	/**
	 * Gets some fields (represented by its name and class name).
	 * 
	 * @param className
	 *            Name of the field declaring class.
	 * @param fieldsNames
	 *            Names of the fields for a class.
	 * @return Some fields (represented by its name and class name).
	 */
	private Object[] getFields(final String className, final String[] fieldsNames) {
		// Creates a new array for the fields.
		final Object[] fields = new Object[fieldsNames.length];
		// For each field name.
		for (Integer currentFieldIdx = 0; currentFieldIdx < fieldsNames.length; currentFieldIdx++) {
			// Adds the current field for the name (and class name).
			fields[currentFieldIdx] = new String[] { className, fieldsNames[currentFieldIdx] };
		}
		// Returns the fields.
		return fields;
	}

	/**
	 * Tests the getFields method.
	 */
	@Test
	public void testGetFields() {
		// Creates a new class mirror for some class.
		final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
		// Gets the fields for the class (and its super classes).
		final Object[] someClassFields = getFields(SomeClass.class.getSimpleName(), SomeClass.FIELDS_NAMES);
		final Object[] someSuperClassFields = getFields(SomeSuperClass.class.getSimpleName(),
				SomeSuperClass.FIELDS_NAMES);
		// For each field retrieved by the method.
		for (final FieldMirror currentField : someClass.getFields()) {
			// If the field is in the retrieved field list.
			Boolean fieldFound = false;
			// For each given field of the class.
			for (final Object currentGivenField : someClassFields) {
				// Converts the current given field in a string array.
				final String[] curGivenFieldArray = (String[]) currentGivenField;
				// If the current field has the same name and declaring class.
				if (curGivenFieldArray[0].equals(currentField.getReflectedField().getDeclaringClass()
						.getSimpleName())
						&& curGivenFieldArray[1].equals(currentField.getReflectedField().getName())) {
					// Sets that the field is found.
					fieldFound = true;
				}
			}
			// For each given field of the super class.
			for (final Object currentGivenField : someSuperClassFields) {
				// Converts the current given field in a string array.
				final String[] curGivenFieldArray = (String[]) currentGivenField;
				// If the current field has the same name and declaring class.
				if (curGivenFieldArray[0].equals(currentField.getReflectedField().getDeclaringClass()
						.getSimpleName())
						&& curGivenFieldArray[1].equals(currentField.getReflectedField().getName())) {
					// Sets that the field is found.
					fieldFound = true;
				}
			}
			// If the field cannot be found.
			if (!fieldFound) {
				// Fails the test (the field should be in the retrieved list).
				Assert.fail();
			}
		}
	}

	/**
	 * Tests the getFields method with a null annotation.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetFieldsNullAnnotation() {
		// Creates a new class mirror for some class.
		final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
		// Tries to get the fields for a null annotation.
		someClass.getFields(null);
	}

	/**
	 * Gets some annotations names and supposed fields that should be annotated.
	 * 
	 * @return Some annotations names and supposed fields that should be annotated.
	 */
	public static Collection<Entry<Class<? extends Annotation>, String[]>> getAnnotatedFields() {
		// Creates a new map for annotations and supposed fields that should be annotated.
		final Map<Class<? extends Annotation>, String[]> annotationsFields = new HashMap<>();
		// Puts the entries to the list.
		annotationsFields.put(SomeAnnotation.class, new String[] { "fieldD", "fieldD", "fieldE", "fieldX" });
		annotationsFields.put(SomeOtherAnnotation.class, new String[] { "fieldF", "fieldY" });
		annotationsFields.put(Deprecated.class, new String[] {});
		// Returns the annotations and supposed fields that should be annotated.
		return annotationsFields.entrySet();
	}

	/**
	 * Tests the getFields method with valid annotations.
	 */
	@Test
	public void testGetFieldsValidAnnotations() {
		// For each annotated fields.
		for (final Entry<Class<? extends Annotation>, String[]> curAnnotatedField : getAnnotatedFields()) {
			// Creates a new class mirror for some class.
			final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
			// Tries to get the fields for the annotation.
			final Collection<FieldMirror> retrievedFields = someClass.getFields(curAnnotatedField.getKey());
			// Creates a list for the expected fields names.
			final Collection<String> expectedFieldsNames = Arrays.asList(curAnnotatedField.getValue());
			// Creates a list for the retrieved fields names.
			final Collection<String> retrievedFieldsNames = new ArrayList<>();
			// For each retrieved field.
			for (final FieldMirror curField : retrievedFields) {
				// Puts the current field name into the appropriate set.
				retrievedFieldsNames.add(curField.getReflectedField().getName());
			}
			// Asserts that the two lists (expected and retrieved) contain the same fields.
			Assert.assertEquals(expectedFieldsNames.size(), retrievedFieldsNames.size());
			Assert.assertTrue(expectedFieldsNames.containsAll(retrievedFieldsNames));
		}
	}

	/**
	 * Tests the getField method with valid names.
	 */
	@Test
	public void testGetFieldValidName() {
		// Creates a new class mirror for some class.
		final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
		// Some fields to be tested.
		final Object[] fields = { new String[] { SomeClass.class.getSimpleName(), "fieldA" },
				new String[] { SomeSuperClass.class.getSimpleName(), "fieldB" },
				new String[] { SomeClass.class.getSimpleName(), "fieldC" } };
		// For each field to be tested.
		for (final Object currentField : fields) {
			// Converts the current field into a string array.
			final String[] currentFieldArray = (String[]) currentField;
			// Gets the field for the name.
			final FieldMirror curRetrievedField = someClass.getField(currentFieldArray[1]);
			// Assert that the retrieved field is the right one.
			Assert.assertTrue(currentFieldArray[0].equals(curRetrievedField.getReflectedField()
					.getDeclaringClass().getSimpleName())
					&& currentFieldArray[1].equals(curRetrievedField.getReflectedField().getName()));
		}
	}

	/**
	 * Empty strings.
	 */
	private static final String[] EMPTY_STRINGS = { null, "" };

	/**
	 * Gets empty strings.
	 * 
	 * @return Empty strings.
	 */
	public static Collection<String> getEmptyStrings() {
		return new ArrayList<>(Arrays.asList(EMPTY_STRINGS));
	}

	/**
	 * Tests the getField method with empty names.
	 */
	@Test
	public void testGetFieldEmptyName() {
		// For each invalid name.
		for (final String curInvName : getEmptyStrings()) {
			// Creates a new class mirror for some class.
			final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
			// Tries to get the field for the name.
			try {
				someClass.getField(curInvName);
				// If the method does not throw an exception, the test fails.
				Assert.fail();
			}
			// If there is an exception, ignores it.
			catch (final InvalidParameterException exception) {
			}
		}
	}

	/**
	 * Invalid fields names.
	 */
	private static final String[] INVALID_FIELDS_NAMES = { "fdsjfd", "fhksdajhfk", "fieldc", "methodA" };

	/**
	 * Gets invalid fields names.
	 * 
	 * @return Invalid fields names.
	 */
	public static Collection<String> getInvalidFieldsNames() {
		return new ArrayList<>(Arrays.asList(INVALID_FIELDS_NAMES));
	}

	/**
	 * Tests the getField method with invalid fields names.
	 */
	@Test
	public void testGetFieldInvalidName() {
		// For each invalid name.
		for (final String curInvName : getEmptyStrings()) {
			// Creates a new class mirror for some class.
			final ClassMirror<SomeClass> someClass = new ClassMirror<>(SomeClass.class);
			// Tries to get the field for the name.
			try {
				someClass.getField(curInvName);
				// If the method does not throw an exception, the test fails.
				Assert.fail();
			}
			// If there is an exception, ignores it.
			catch (final InvalidParameterException exception) {
			}
		}
	}

	// TODO Tests for getFieldPath()

}
