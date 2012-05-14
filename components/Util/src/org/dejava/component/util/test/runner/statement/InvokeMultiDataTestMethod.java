package org.dejava.component.util.test.runner.statement;

import java.io.InputStream;

import junit.framework.TestCase;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.reflection.handler.MethodHandler;
import org.dejava.component.util.reflection.handler.PackageHandler;
import org.dejava.component.util.test.annotation.MultiDataTest;
import org.dejava.component.util.xml.XMLCreator;
import org.dejava.component.util.xml.XMLDecoder;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.w3c.dom.Document;

/**
 * Statement to invoke test methods. Each test method might be invoked multiple times with the provided test
 * data.
 */
public class InvokeMultiDataTestMethod extends Statement {
	
	/**
	 * The JUnit framework method.
	 */
	private FrameworkMethod testMethod;
	
	/**
	 * Returns the JUnit framework method.
	 * 
	 * @return The JUnit framework method.
	 */
	public FrameworkMethod getTestMethod() {
		return testMethod;
	}
	
	/**
	 * Sets the JUnit framework method.
	 * 
	 * @param testMethod
	 *            New JUnit framework method.
	 */
	public void setTestMethod(final FrameworkMethod testMethod) {
		this.testMethod = testMethod;
	}
	
	/**
	 * The target test object for the method invocation.
	 */
	private Object targetTest;
	
	/**
	 * Returns the target test object for the method invocation.
	 * 
	 * @return The target test object for the method invocation.
	 */
	public Object getTargetTest() {
		return targetTest;
	}
	
	/**
	 * Sets the target test object for the method invocation.
	 * 
	 * @param targetTest
	 *            New target test object for the method invocation.
	 */
	public void setTargetTest(final Object targetTest) {
		this.targetTest = targetTest;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param targetTest
	 *            The target test object for the method invocation.
	 */
	public InvokeMultiDataTestMethod(final FrameworkMethod testMethod, final Object targetTest) {
		super();
		// Sets the main fields.
		this.testMethod = testMethod;
		this.targetTest = targetTest;
	}
	
	/**
	 * Gets the XML stream for the test data objects.
	 * 
	 * @param multiDataTest
	 *            Test case configuration annotation.
	 * @return The XML stream for the test data objects.
	 * @throws EmptyParameterException
	 *             If the test class cannot be accessed.
	 */
	private InputStream getXMLStream(final MultiDataTest multiDataTest) throws EmptyParameterException {
		// XML relative path.
		String xmlRelativePath;
		// If the test case configuration does not provide a configuration file
		// path.
		if (multiDataTest.xmlPath().isEmpty()) {
			// The relative path is the default one.
			xmlRelativePath = MultiDataTest.DEFAULT_XML_PATH;
			// Replaces the method name in the default relative path.
			xmlRelativePath = xmlRelativePath.replace(MultiDataTest.METHOD_NAME_EXPRESSION, getTestMethod()
					.getName());
		}
		// If the test case configuration does provide a configuration file
		// path.
		else {
			// Gets the given path.
			xmlRelativePath = multiDataTest.xmlPath();
		}
		// Gets the path for the XML.
		final String xmlPath = PackageHandler.getPackageAsDirPath(getTargetTest().getClass()) + '/'
				+ xmlRelativePath;
		// Returns the XML stream.
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
	}
	
	/**
	 * Gets the test data from the appropriate source.
	 * 
	 * @param multiDataTest
	 *            the annotation with the data source information.
	 * @return The test data from the appropriate source.
	 */
	private Iterable<?> getTestData(final MultiDataTest multiDataTest) {
		// Test data is empty.
		Iterable<?> testData = null;
		// Depending on the source type.
		switch (multiDataTest.sourceType()) {
		// If the source type is a method.
			case METHOD:
				// The test data is the return of the method invocation.
				testData = MethodHandler.invokeMethod(getTargetTest(), multiDataTest.methodName(), null);
				// Ends the case.
				break;
			// If the source type is a JNDI object.
			case JNDI:
				// TODO
				
				// Ends the case.
				break;
			// If the source type is any other type (including XML).
			default:
				// Gets the XML input stream.
				final InputStream xmlInputStream = getXMLStream(multiDataTest);
				// Creates the XML document for the stream.
				final Document xmlDocument = XMLCreator.createXMLDocument(xmlInputStream);
				// Gets the test data object from the XML document.
				testData = (Iterable<?>) XMLDecoder.fromXML(xmlDocument, null, TestCase.class, null);
				// Ends the case.
				break;
		}
		// Returns the retrieved test data.
		return testData;
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Tries to get the @MultiDataTest annotation.
		final MultiDataTest multiDataTest = getTestMethod().getAnnotation(MultiDataTest.class);
		// If the annotation exists.
		if (multiDataTest != null) {
			// Gets the test data objects.
			Iterable<?> testData = getTestData(multiDataTest);
			// Keeps the current test data object index.
			Integer currentTestDataObjIdx = 0;
			// List of failed tests. TODO
			// For each test data object.
			for (Object currentTestDataObj : testData) {
				// Tries to invoke the test with the current test data.
				try {
					getTestMethod().invokeExplosively(getTargetTest(), new Object[] { currentTestDataObj });
				}
				// If the test raises an exception.
				catch (Exception exception) {
					// Adds the failed test information to the list. TODO
				}
				// The current object index is incremented.
				currentTestDataObjIdx++;
			}
		}
		// If it does not exist.
		else {
			// Invokes the method with no parameters.
			getTestMethod().invokeExplosively(getTargetTest(), new Object[0]);
		}
	}
}
