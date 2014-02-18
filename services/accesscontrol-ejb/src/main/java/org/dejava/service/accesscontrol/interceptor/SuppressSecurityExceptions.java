package org.dejava.service.accesscontrol.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.service.accesscontrol.exception.SecurityException;

/**
 * If any {@link SecurityException} should be suppressed. When suppressed, the interceptor forces a null
 * return for the secured method.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SuppressSecurityExceptions {

}
