package org.dejava.component.util.test.runner.statement.provider;

import java.io.InputStream;
import java.util.List;

import junit.framework.TestCase;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.reflection.handler.PackageHandler;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.statement.InvokeMultiDataTestMethod;
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
	public static final String DEFAULT_XML_PATH = "xml/" + InvokeMultiDataTestMethod.METHOD_NAME_EXPRESSION
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
			filePath = filePath.replace(InvokeMultiDataTestMethod.METHOD_NAME_EXPRESSION,
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
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Gets the XML stream for the test data objects.
	 * 
	 * @param targetTest
	 *            The target test object for the method invocation.
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The XML stream for the test data objects.
	 * @throws EmptyParameterException
	 *             If the test class cannot be accessed.
	 */
	private InputStream getXMLStream(final Object targetTest, final FrameworkMethod testMethod)
			throws EmptyParameterException {
		// Gets the path for the XML.
		final String xmlPath = PackageHandler.getPackageAsDirPath(targetTest.getClass()) + '/'
				+ getFilePath(testMethod);
		// Returns the XML stream.
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
	}
	
	/**
	 * @see org.dejava.component.util.test.runner.statement.provider.TestDataProvider#getTestData(java.lang.Object,
	 *      org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public List<?> getTestData(final Object targetTest, final FrameworkMethod testMethod)
			throws UnavailableTestDataException {
		// Tries to get the data.
		try {
			// Gets the XML input stream.
			final InputStream xmlInputStream = getXMLStream(targetTest, testMethod);
			// Creates the XML document for the stream.
			final Document xmlDocument = XMLCreator.createXMLDocument(xmlInputStream);
			// Gets the test data object from the XML document.
			return (List<?>) XMLDecoder.fromXML(xmlDocument, null, TestCase.class, null);
		}
		// If the test data cannot be retrieved.
		catch (Exception exception) {
			// Throws an exception. FIXME
			throw new UnavailableTestDataException(null, null, null);
		}
	}
}
