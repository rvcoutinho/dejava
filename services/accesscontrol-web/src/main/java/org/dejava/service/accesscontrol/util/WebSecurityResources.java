package org.dejava.service.accesscontrol.util;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Web security resources to be injected CDI.
 */
@Singleton
@AccessControlCtx
@ManagedBean(name = "securityUtils")
public class WebSecurityResources {

	/**
	 * The Shiro ini file path.
	 */
	private static final String SHIRO_INI_PATH = "classpath:shiro.ini";

	/**
	 * The security manager.
	 */
	private SecurityManager securityManager;

	/**
	 * Loads the security manager.
	 */
	@PostConstruct
	public void loadSecurityManager() {
		// Creates a new security manager for the ini file.
		securityManager = new IniSecurityManagerFactory(SHIRO_INI_PATH).getInstance();
		// Sets the security manager in the security utils.
		SecurityUtils.setSecurityManager(securityManager);
	}

	/**
	 * Gets the security manager for the web app.
	 * 
	 * @return The security manager for the web app.
	 */
	@Produces
	@AccessControlCtx
	public SecurityManager getSecurityManager() {
		return securityManager;
	}

	/**
	 * Gets the current subject.
	 * 
	 * @return The current subject.
	 */
	@Produces
	@AccessControlCtx
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
