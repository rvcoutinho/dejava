package org.dejava.component.ejb.constant;

/**
 * Constants related to DAO parameters keys.
 */
public final class ExternalEntityLoaderParamKeys {

	/**
	 * The name of the JNDI object used to retrieve an external entity.
	 */
	public static final String JNDI_RETRIEVE_OBJ_NAME = "JNDI retrieve object name";

	/**
	 * The name of the object method used to retrieve an external entity.
	 */
	public static final String RETRIEVE_METHOD_NAME = "retrieve method name";

	/**
	 * Entity to get external entities loaded.
	 */
	public static final String ENTITY = "entity";

	/**
	 * Private constructor.
	 */
	private ExternalEntityLoaderParamKeys() {
	}
}
