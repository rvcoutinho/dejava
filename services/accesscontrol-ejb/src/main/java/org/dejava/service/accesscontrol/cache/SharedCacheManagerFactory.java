package org.dejava.service.accesscontrol.cache;

import net.sf.ehcache.CacheManager;

import org.apache.shiro.util.Factory;

/**
 * TODO
 */
public class SharedCacheManagerFactory implements Factory<CacheManager> {

	/**
	 * @see org.apache.shiro.util.Factory#getInstance()
	 */
	@Override
	public CacheManager getInstance() {
		return CacheManager.create();
	}

}
