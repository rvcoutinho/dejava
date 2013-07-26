package org.dejava.component.faces.test.converter;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dejava.component.faces.test.converter.util.ConverterController;
import org.dejava.component.faces.test.converter.util.Faces;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.reflection.FieldMirror;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.jsf.AfterPhase;
import org.jboss.arquillian.warp.jsf.Phase;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Tests for the faces message handler.
 */
@RunWith(Arquillian.class)
@WarpTest
@RunAsClient
public class InvRegexConvTest {

	/**
	 * Browser simulator.
	 */
	@Drone
	WebDriver browser;

	/**
	 * Application context path.
	 */
	@ArquillianResource
	URL contextPath;

	/**
	 * Defines what should be done during deployment (before tests).
	 * 
	 * @return The configuration for the archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		// Gets the maven dependency resolver.
		final MavenDependencyResolver dependencyResolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsLibraries(
						dependencyResolver.artifacts("org.jboss.arquillian.extension:arquillian-warp-jsf",
								"org.dejava.component:ejb", "org.dejava.component:validation",
								"org.dejava.component:exception", "org.dejava.component:i18n",
								"org.dejava.component:reflection").resolveAsFiles())
				.addPackages(true, "org.dejava.component.faces.converter")
				.addPackages(true, "org.dejava.component.faces.test.converter.util")
				.addAsWebResource(new File("src/test/resources/WEB-INF/converter.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * The value of the field.
	 */
	class FieldValue implements Serializable {

		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = 4570352714451318758L;

		/**
		 * The raw value for the field.
		 */
		private String rawValue;

		/**
		 * Gets the raw value for the field.
		 * 
		 * @return The raw value for the field.
		 */
		public String getRawValue() {
			return rawValue;
		}

		/**
		 * Sets the raw value for the field.
		 * 
		 * @param rawValue
		 *            New raw value for the field.
		 */
		public void setRawValue(final String rawValue) {
			this.rawValue = rawValue;
		}

		/**
		 * The expected value for the field (after conversion).
		 */
		private String expectedValue;

		/**
		 * Gets the expected value for the field (after conversion).
		 * 
		 * @return The expected value for the field (after conversion).
		 */
		public String getExpectedValue() {
			return expectedValue;
		}

		/**
		 * Sets the expected value for the field (after conversion).
		 * 
		 * @param expectedValue
		 *            New expected value for the field (after conversion).
		 */
		public void setExpectedValue(final String expectedValue) {
			this.expectedValue = expectedValue;
		}

		/**
		 * Default constructor.
		 * 
		 * @param rawValue
		 *            The raw value for the field.
		 * @param expectedValue
		 *            The expected value for the field (after conversion).
		 */
		public FieldValue(final String rawValue, final String expectedValue) {
			super();
			this.rawValue = rawValue;
			this.expectedValue = expectedValue;
		}

	}

	/**
	 * Represents a form fill and submit action.
	 */
	class FillFormAndSubmit implements Activity {

		/**
		 * The converter form name.
		 */
		public static final String CONV_FORM = "converter-form";

		/**
		 * The map with the fields ids and values.
		 */
		private Map<String, FieldValue> fieldsValues;

		/**
		 * Gets the map with the fields ids and values.
		 * 
		 * @return The map with the fields ids and values.
		 */
		public Map<String, FieldValue> getFieldsValues() {
			return fieldsValues;
		}

		/**
		 * Sets the map with the fields ids and values.
		 * 
		 * @param fieldsValues
		 *            New map with the fields ids and values.
		 */
		public void setFieldsValues(final Map<String, FieldValue> fieldsValues) {
			this.fieldsValues = fieldsValues;
		}

		/**
		 * Default constructor.
		 * 
		 * @param fieldsValues
		 *            New map with the fields ids and values.
		 */
		public FillFormAndSubmit(final Map<String, FieldValue> fieldsValues) {
			this.fieldsValues = fieldsValues;
		}

		/**
		 * @see org.jboss.arquillian.warp.Activity#perform()
		 */
		@Override
		public void perform() {
			// Goes to the index page.
			browser.navigate().to(contextPath + "converter.jsf");
			// For each entry in the fields values map.
			for (final Entry<String, FieldValue> curFieldValue : getFieldsValues().entrySet()) {
				// Sets the value of the current field.
				browser.findElement(By.id(curFieldValue.getKey())).sendKeys(
						curFieldValue.getValue().getRawValue());
			}
			// Submits the converter form.
			browser.findElement(By.id(CONV_FORM)).submit();
		}

	}

	/**
	 * Inspects if the fields have the expected value.
	 */
	class FieldInpection extends Inspection {

		/**
		 * Generated serial.
		 */
		private static final long serialVersionUID = -5890035609796543851L;

		/**
		 * The converter controller.
		 */
		@Faces
		@ArquillianResource
		private ConverterController converterController;

		/**
		 * The map with the fields ids and values.
		 */
		private Map<String, FieldValue> fieldsValues;

		/**
		 * Gets the map with the fields ids and values.
		 * 
		 * @return The map with the fields ids and values.
		 */
		public Map<String, FieldValue> getFieldsValues() {
			return fieldsValues;
		}

		/**
		 * Sets the map with the fields ids and values.
		 * 
		 * @param fieldsValues
		 *            New map with the fields ids and values.
		 */
		public void setFieldsValues(final Map<String, FieldValue> fieldsValues) {
			this.fieldsValues = fieldsValues;
		}

		/**
		 * Asserts that the messages have been added.
		 */
		@AfterPhase(Phase.UPDATE_MODEL_VALUES)
		public void assertFieldsValues() {
			// For each entry in the fields values map.
			for (final Entry<String, FieldValue> curFieldValue : getFieldsValues().entrySet()) {
				// Gets the actual field value.
				final FieldMirror field = new ClassMirror<>(ConverterController.class).getField(curFieldValue
						.getKey());
				// Asserts that the field has the expected value.
				Assert.assertEquals(curFieldValue.getValue().getExpectedValue(),
						field.getValue(converterController, false, true));
			}
		}

		/**
		 * Default constructor.
		 * 
		 * @param fieldsValues
		 *            New map with the fields ids and values.
		 */
		public FieldInpection(final Map<String, FieldValue> fieldsValues) {
			this.fieldsValues = fieldsValues;
		}
	}

	/**
	 * Some data for the field taht should only contain digits (each pair represents a raw and expected
	 * value).
	 */
	private static final String[][] ONLY_DIG_VALUES = { { "dakjhf4erv4", "44" }, { "f437vbw3k4j", "43734" } };

	/**
	 * The fields names for the converter form.
	 */
	private static final String[] FIELDS_NAMES = { "onlyDigits" };

	/**
	 * T
	 */
	@Test
	public void testInvRegexConv() {
		// For each given fields values (raw and expected)
		for (final String[] curFieldValueArray : ONLY_DIG_VALUES) {
			// Creates a new map for the fields values.
			final HashMap<String, FieldValue> fieldsValues = new HashMap<>();
			// Creates a new field value object for the field that should only have digits.
			final FieldValue curFieldValue = new FieldValue(curFieldValueArray[0], curFieldValueArray[1]);
			// Adds the field value to the list.
			fieldsValues.put(FIELDS_NAMES[0], curFieldValue);
			// Fill and submit the form.
			Warp.initiate(new FillFormAndSubmit(fieldsValues))
			// Inspect to assert field has the expected value.
					.inspect(new FieldInpection(fieldsValues));

		}
	}

}
