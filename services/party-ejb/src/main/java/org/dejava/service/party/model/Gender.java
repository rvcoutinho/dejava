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
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return (this.getClass().getSimpleName() + "." + name()).toLowerCase();
	}

}
