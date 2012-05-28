package org.dejava.component.util.test.runner.dataset.impl;

import java.io.InputStream;
import java.util.Collection;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.reflection.handler.PackageHandler;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.dejava.component.util.xml.XMLCreator;
import org.dejava.component.util.xml.XMLDecoder;
import org.junit.runners.model.FrameworkMethod;
import org.w3c.dom.Document;

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
		final String xmlPath = PackageHandler.getPackageAsDirPath(testMethod.getMethod().getDeclaringClass())
				+ '/' + getFilePath(testMethod);
		// Returns the XML stream.
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
	}
	
	/**
	 * @see org.dejava.component.util.test.runner.dataset.TestDataProvider#getTestData(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Collection<?> getTestData(final FrameworkMethod testMethod) throws UnavailableTestDataException {
		// Tries to get the data.
		try {
			// Gets the XML input stream.
			final InputStream xmlInputStream = getXMLStream(testMethod);
			// Creates the XML document for the stream.
			final Document xmlDocument = XMLCreator.createXMLDocument(xmlInputStream);
			// Gets the test data object from the XML document. TODO Think about expected class
			return (Collection<?>) XMLDecoder.fromXML(xmlDocument, null, null, null);
		}
		// If the test data cannot be retrieved.
		catch (final Exception exception) {
			// Throws an exception.
			throw new UnavailableTestDataException(exception, testMethod.getName());
		}
	}
}
