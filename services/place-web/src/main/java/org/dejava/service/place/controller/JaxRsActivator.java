package org.dejava.service.place.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath is the Java EE 6 "no XML"
 * approach to activating JAX-RS.
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}
