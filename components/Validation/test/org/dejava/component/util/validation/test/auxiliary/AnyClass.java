package org.dejava.component.util.validation.test.auxiliary;

import org.dejava.component.util.validation.annotation.ValidationMethod;
import org.dejava.component.util.validation.annotation.Validations;


/**
 * A class to be used in the validation tests.
 */
public class AnyClass {
	
	/**
	 * Field1.
	 */
	@Validations(validationMethods = { @ValidationMethod(methodName = "validateNotNull") })
	private String field1;
	
	/**
	 * Gets the field1.
	 * 
	 * @return The field1.
	 */
	public String getField1() {
		return field1;
	}
	
	/**
	 * Sets the field1.
	 * 
	 * @param field1
	 *            New field1.
	 */
	public void setField1(final String field1) {
		this.field1 = field1;
	}
	
}
