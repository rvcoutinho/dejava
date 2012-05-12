package org.dejava.component.util.reflection.test.clazz.auxiliary;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Object that holds a class and its superclasses.
 */
public class ClassSuperclasses {
	
	/**
	 * Class.
	 */
	private Class<?> clazz;
	
	/**
	 * Gets the class.
	 * 
	 * @return The class.
	 */
	public Class<?> getClazz() {
		return clazz;
	}
	
	/**
	 * Sets the class.
	 * 
	 * @param clazz
	 *            New class.
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Superclasses.
	 */
	private Set<Class<?>> superclasses;
	
	/**
	 * Gets the superclasses.
	 * 
	 * @return the superclasses.
	 */
	public Set<Class<?>> getSuperclasses() {
		// If the set is null.
		if (superclasses == null) {
			// It is a new list.
			superclasses = new LinkedHashSet<Class<?>>();
		}
		// returns the collection.
		return superclasses;
	}
	
	/**
	 * Sets the superclasses.
	 * 
	 * @param superclasses
	 *            New superclsses.
	 */
	public void setSuperclasses(Set<Class<?>> superclasses) {
		this.superclasses = superclasses;
	}
}
