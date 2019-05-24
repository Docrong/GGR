package com.boco.eoms.commons.statistic.base.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Testehcache {

	String configurationFileName = "E:/ehcache.xml";
	
	CacheManager manager = CacheManager.create(configurationFileName);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Testehcache t = new Testehcache();
		String key = "key1";
		List list = new ArrayList();
		list.add("wo");
		
		t.saveCache(key,list);
		
		System.out.println(t.getCache(key));
		
	}
	
	public void saveCache(String key,Object obj)
	{
		Cache cache =manager.getCache("sampleEhcache");
		Element element = new Element(key,obj);
		cache.put(element);
	}
	
	
	public  Object getCache(String key)
	{
		Cache cache =manager.getCache("sampleEhcache");
		Element element = cache.get(key);
		if(element == null)
		{
			System.out.println("cache中没有");
			return null;
		}
		return element.getObjectValue();
	}

	
	
}
