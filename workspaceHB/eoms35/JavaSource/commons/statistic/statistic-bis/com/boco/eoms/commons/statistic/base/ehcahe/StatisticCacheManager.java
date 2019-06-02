package com.boco.eoms.commons.statistic.base.ehcahe;

import java.io.FileNotFoundException;

import net.sf.ehcache.CacheManager;

import com.boco.eoms.commons.statistic.base.reference.StaticMethod;


public class StatisticCacheManager extends CacheManager{
	
	private static CacheManager scm = null;
	
	private static final String satEhCachePath = "classpath:config/statistic/base-config/statehcache.xml";
	
	public static synchronized CacheManager getInstance() {

        if (scm == null) {
			try {
				String satEhCacheABSPath = StaticMethod.getFilePathForUrl(satEhCachePath);
				scm = new CacheManager(satEhCacheABSPath);
			} catch (FileNotFoundException e) {
				System.err.println("请查看缓存文件路径是否正确 ："  + satEhCachePath);
				e.printStackTrace();
			}
        	
        }
        return scm;
    }
	
	
//	public synchronized CacheManager getCacheManager() {
//		if (cm == null) {
//			URL url = getClass().getResource("/ehcache.xml");
//			cm = new CacheManager(url);
//		}
//		return cm;
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
