package org.dejava.service.party.model;

/**
 * Gender of a person.
 */
public enum Gender {

	/**
	 * Male gender.
	 */
	MALE,

	/**
	 * Female gender.
	 */
	FEMALE;

	/**
	 * Gets #{enumClassName}.#{enumName}.
	 */
	@Override
	public String toString() {
		return (this.getClass().getSimpleName() + "." + name()).toLowerCase();
	}

}
