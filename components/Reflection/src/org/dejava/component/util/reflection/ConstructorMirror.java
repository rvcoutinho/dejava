package org.dejava.component.util.reflection;

import java.lang.reflect.Constructor;

/**
 * TODO
 * 
 * @param <ReflectedClass>
 *            The class for the reflected constructor.
 */
public class ConstructorMirror<ReflectedClass> {
	
	/**
	 * Constructor being reflected.
	 */
	private Constructor<ReflectedClass> reflectedConstructor;
	
	/**
	 * Gets the constructor being reflected.
	 * 
	 * @return The constructor being reflected.
	 */
	public Constructor<ReflectedClass> getReflectedConstructor() {
		return reflectedConstructor;
	}
	
	/**
	 * Sets the constructor being reflected.
	 * 
	 * @param reflectedConstructor
	 *            New constructor being reflected.
	 */
	public void setReflectedConstructor(final Constructor<ReflectedClass> reflectedConstructor) {
		this.reflectedConstructor = reflectedConstructor;
	}
	
	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedConstructor
	 *            Constructor being reflected.
	 */
	public ConstructorMirror(final Constructor<ReflectedClass> reflectedConstructor) {
		this.reflectedConstructor = reflectedConstructor;
	}
	
}
