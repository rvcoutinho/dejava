package org.dejava.component.i18n.source.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorted properties.
 */
public class SortedProperties extends Properties {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5500566494461092976L;

	/**
	 * Ordered set for the keys.
	 */
	private final Set<Object> keys = new TreeSet<>();

	/**
	 * @see java.util.Hashtable#keys()
	 */
	@Override
	public Enumeration<Object> keys() {
		return Collections.enumeration(keys);
	}

	/**
	 * @see java.util.Hashtable#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(final Object key, final Object value) {
		// Adds the key to the key set.
		keys.add(key);
		// Adds the key/value par to the map.
		return super.put(key, value);
	}

}
