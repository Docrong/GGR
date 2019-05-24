package com.boco.eoms.commons.cache.event;

import java.util.Properties;

import org.apache.log4j.Logger;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 4, 2007 10:16:13 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class EHCacheEventListenerFactory extends CacheEventListenerFactory {

	private Logger logger = Logger.getLogger(this.getClass());

	public CacheEventListener createCacheEventListener(Properties properties) {
		logger.debug("缓存管理监听启动...");
		return new EHCacheEventListener();
	}

}
