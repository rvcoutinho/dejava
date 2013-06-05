package org.dejava.service.philanthropy.model;

/**
 * Category of the philantropy idea.
 */
public enum Category {

	/**
	 * Environment category.
	 */
	ENVIRONMENT,

	/**
	 * Citizenship category.
	 */
	CITIZENSHIP,

	/**
	 * Culture category.
	 */
	CULTURE,

	/**
	 * Education category.
	 */
	EDUCATION,

	/**
	 * Sport category.
	 */
	SPORT,

	/**
	 * Health category.
	 */
	HEALTH;

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return (this.getClass().getSimpleName() + "." + name()).toLowerCase();
	}

}
