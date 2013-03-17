package org.dejava.component.test.runner.dataset.impl;

import java.io.InputStream;
import java.util.Collection;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.test.constant.ErrorKeys;
import org.dejava.component.test.exception.parametric.InvalidParametricTestException;
import org.dejava.component.test.runner.dataset.TestDataProvider;
import org.dejava.component.test.runner.statement.ParametricTestMethodInvoker;
import org.dejava.component.test.util.MessageTypes;
import org.junit.runners.model.FrameworkMethod;

/**
 * Provides access to XML test data.
 */
public class XMLTestDataProvider implements TestDataProvider {

	/**
	 * Default path for the XML.
	 */
	public static final String DEFAULT_XML_PATH = "xml/" + ParametricTestMethodInvoker.METHOD_NAME_EXPRESSION
			+ ".xml";

	/**
	 * The XML file path (relative to the test class).
	 */
	private String filePath;

	/**
	 * Gets the XML file path (relative to the test class).
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The XML file path (relative to the test class).
	 */
	public String getFilePath(final FrameworkMethod testMethod) {
		// If the method name is not given.
		if (filePath == null) {
			// The default method name is used.
			filePath = DEFAULT_XML_PATH;
			// Replaces the method name in the path.
			filePath = filePath.replace(ParametricTestMethodInvoker.METHOD_NAME_EXPRESSION,
					testMethod.getName());
		}
		// Returns the method name.
		return filePath;
	}

	/**
	 * Sets the XML file path (relative to the test class).
	 * 
	 * @param filePath
	 *            New XML file path (relative to the test class).
	 */
	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the XML stream for the test data objects.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The XML stream for the test data objects.
	 * @throws EmptyParameterException
	 *             If the test class cannot be accessed.
	 */
	private InputStream getXMLStream(final FrameworkMethod testMethod) throws EmptyParameterException {
		// Gets the path for the XML.
		final String xmlPath = new ClassMirror<>(testMethod.getMethod().getDeclaringClass())
				.getPackageAsDirPath() + '/' + getFilePath(testMethod);
		// Returns the XML stream.
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
	}

	/**
	 * @see org.dejava.component.test.runner.dataset.TestDataProvider#getTestData(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Collection<?> getTestData(final FrameworkMethod testMethod) throws InvalidParametricTestException {
		// Tries to get the data.
		try {
			// Gets the XML input stream.
			final InputStream xmlInputStream = getXMLStream(testMethod);
			// Creates the XML document for the stream.
			// final Document xmlDocument = XMLCreator.createXMLDocument(xmlInputStream);
			// Gets the test data object from the XML document. TODO Think about expected class FIXME
			// return (Collection<?>) XMLDecoder.fromXML(xmlDocument, null, null, null);
			return null;
		}
		// If the test data cannot be retrieved.
		catch (final Exception exception) {
			// Throws an exception.
			throw new InvalidParametricTestException(MessageTypes.Error.class,
					ErrorKeys.UNAVAILABLE_TEST_DATA, testMethod.getName(), null, exception);
		}
	}
}
