package com.boco.eoms.commons.statistic.base.util;

import com.boco.eoms.commons.statistic.base.ehcahe.StatisticCacheManager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class StatCacheUtil {

	public static void saveCache(String key,Object obj)
	{
		CacheManager cacheManager = StatisticCacheManager.getInstance();
		Cache cache =cacheManager.getCache("statisticEhcache");
		Element element = new Element(key,obj);
		cache.put(element);
	}
	
	public static Object getCache(String key)
	{
		CacheManager cacheManager = StatisticCacheManager.getInstance();
		Cache cache =cacheManager.getCache("statisticEhcache");
		Element element = cache.get(key);
		if(element == null)
		{
			System.out.println("cache中不存在 "+ key +" 该对象");
			return null;
		}
		return element.getObjectValue();
	}
}
