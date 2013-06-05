package org.dejava.service.location.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Location qualifier.
 */
@Qualifier
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface Location {

}
