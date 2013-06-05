package org.dejava.service.philanthropy.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Philanthropy qualifier.
 */
@Qualifier
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface Philanthropy {

}
