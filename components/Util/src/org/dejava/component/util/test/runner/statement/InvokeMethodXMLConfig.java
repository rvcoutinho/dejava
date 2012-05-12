package org.dejava.component.util.test.runner.statement;

import java.io.InputStream;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.reflection.handler.PackageHandler;
import org.dejava.component.util.test.annotation.TestCaseConfig;
import org.dejava.component.util.test.model.TestCase;
import org.dejava.component.util.xml.XMLCreator;
import org.dejava.component.util.xml.XMLDecoder;
import org.dejava.component.util.xml.exception.XMLConversionException;
import org.dejava.component.util.xml.exception.XMLCreationException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.w3c.dom.Document;

/**
 * Statement to invoke methods using the XML configuration for the running test.
 */
public class InvokeMethodXMLConfig extends Statement {
	
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
	 * Returns the test class.
	 * 
	 * @return The test class.
	 */
	public Class<?> getTestClass() {
		return getTargetTest().getClass();
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param targetTest
	 *            The target test object for the method invocation.
	 */
	public InvokeMethodXMLConfig(final FrameworkMethod testMethod, final Object targetTest) {
		super();
		// Sets the main fields.
		this.testMethod = testMethod;
		this.targetTest = targetTest;
	}
	
	/**
	 * Gets the XML stream for the configuration contents of a test case.
	 * 
	 * @param testCaseConfig
	 *            Test case configuration annotation.
	 * @return The XML stream for the configuration contents of a test case.
	 * @throws EmptyParameterException
	 *             If the test class cannot be accessed.
	 */
	protected InputStream getXMLStream(final TestCaseConfig testCaseConfig) throws EmptyParameterException {
		// XML relative path.
		String xmlRelativePath;
		// If the test case configuration does not provide a configuration file
		// path.
		if (testCaseConfig.xmlRelativePath().isEmpty()) {
			// The relative path is the default one.
			xmlRelativePath = TestCaseConfig.DEFAULT_RELATIVE_PATH;
			// Replaces the method name in the default relative path.
			xmlRelativePath = xmlRelativePath.replace(TestCaseConfig.METHOD_NAME_EXPRESSION, getTestMethod()
					.getName());
		}
		// If the test case configuration does provide a configuration file
		// path.
		else {
			// Gets the given path.
			xmlRelativePath = testCaseConfig.xmlRelativePath();
		}
		// Gets the path for the XML.
		final String xmlPath = PackageHandler.getPackageAsDirPath(getTestClass()) + '/' + xmlRelativePath;
		// Returns the XML stream.
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlPath);
	}
	
	/**
	 * Gets the parameter values for the test method.
	 * 
	 * @return The parameter values for the test method.
	 * @throws EmptyParameterException
	 *             If the test class cannot be accessed.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws XMLCreationException
	 *             If the XML document cannot be created.
	 */
	public Object[] getParametersValues() throws EmptyParameterException, XMLConversionException,
			XMLCreationException {
		// Gets the annotation with test case configuration.
		final TestCaseConfig testCaseConfig = getTestMethod().getAnnotation(TestCaseConfig.class);
		// If the annotation exists.
		if (testCaseConfig != null) {
			// Gets the XML input stream.
			final InputStream xmlInputStream = getXMLStream(testCaseConfig);
			// Creates the XML document for the stream.
			final Document xmlDocument = XMLCreator.createXMLDocument(xmlInputStream);
			// Gets the test case from the XML.
			final TestCase testCase = (TestCase) XMLDecoder.fromXML(xmlDocument, null, TestCase.class, null);
			// Returns an array with the test case.
			return new Object[] { testCase };
		}
		// As default, returns an empty array.
		return new Object[0];
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Invoke the method.
		getTestMethod().invokeExplosively(getTargetTest(), getParametersValues());
	}
}
