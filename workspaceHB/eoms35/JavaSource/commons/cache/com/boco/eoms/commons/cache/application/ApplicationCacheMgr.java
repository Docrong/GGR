package com.boco.eoms.commons.cache.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;

import com.boco.eoms.commons.cache.exception.EHCacheMgrException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 1, 2007 8:13:58 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ApplicationCacheMgr {

	/**
	 * cache列表，使用ioc在spring中注入
	 */
	private List caches;

	/**
	 * ehcache 中的cacheManager
	 */
	private CacheManager cacheManager;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 通过cache名称取该cache的所有cachekey
	 * 
	 * @param cacheName
	 *            组件使用的cache
	 * @return 该组件cacheKey列表
	 */
	public List getKeys(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getKeys();
	}

	/**
	 * 计算某一缓存组件占用内存
	 * 
	 * @param cacheName
	 *            组件使用的cache
	 * @return 占用大小
	 */
	public long calculateInMemorySize(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.calculateInMemorySize();
	}

	/**
	 * 计算所有缓存组件占用内存
	 * 
	 * @param cacheName
	 *            组件使用的cache
	 * @return 占用大小
	 */
	public long calculateInMemorySize() {
		long sum = 0;
		for (Iterator it = caches.iterator(); it.hasNext();) {
			Cache cache = cacheManager.getCache((String) it.next());
			sum += cache.calculateInMemorySize();

		}
		return sum;
	}

	/**
	 * 刷新所有缓存模块
	 * 
	 * @param cacheName
	 *            缓存组件名称
	 */
	public void flush() {
		for (Iterator it = caches.iterator(); it.hasNext();) {
			Cache cache = cacheManager.getCache((String) it.next());
			// cache.flush();
			cache.removeAll();
		}
	}

	/**
	 * 刷新该缓存模块
	 * 
	 * @param cacheName
	 *            缓存组件名称
	 */
	public void flush(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		cache.removeAll();
	}

	/**
	 * 删除（刷新）cacheName组件的cacheKey所对应的缓存
	 * 
	 * @param cacheName
	 *            缓存组件名称
	 * @param cacheKey
	 *            该缓存组件缓存的对象所对应的key
	 */
	public void remove(String cacheName, String cacheKey)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		cache.remove(cacheKey);
	}

	/**
	 * 取cacheName组件对诮的cacheKey缓存的对象
	 * 
	 * @param cacheName
	 *            缓存组件名称
	 * @param cacheKey
	 *            该缓存组件缓存的对象所对应的key
	 * @return 缓存对象
	 */
	public Object getObject(String cacheName, String cacheKey)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.get(cacheKey);
	}

	/**
	 * 将统计结果清零
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 */
	public void clearStatistics(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		cache.clearStatistics();
	}

	/**
	 * 清除所有统计信息为零
	 * 
	 */
	public void clearStatistics() {
		// 取所有组件缓冲名称
		// 遍历所有cache，并清除所有统计信息为零
		for (Iterator it = caches.iterator(); it.hasNext();) {

			Cache cache = cacheManager.getCache((String) it.next());
			cache.clearStatistics();
		}

	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public int getSize(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getSize();
	}

	/**
	 * 是否已写入磁盘
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public boolean isOverflowToDisk(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.isOverflowToDisk();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public int getMaxElementsInMemory(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getMaxElementsInMemory();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getDiskExpiryThreadIntervalSeconds(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getDiskExpiryThreadIntervalSeconds();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getDiskStoreSize(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getDiskStoreSize();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getDiskStoreHitCount(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getDiskStoreHitCount();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getMemoryStoreHitCount(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getMemoryStoreHitCount();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getTimeToIdleSeconds(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getTimeToIdleSeconds();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public long getTimeToLiveSeconds(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getTimeToLiveSeconds();
	}

	/**
	 * 获取cacheName缓存组件的size
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 缓存组件的size
	 */
	public boolean isEternal(String cacheName) throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.isEternal();
	}

	/**
	 * 获取cacheName缓存组件的统计信息
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 统计信息
	 */
	public Statistics getStatistics(String cacheName)
			throws EHCacheMgrException {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new EHCacheMgrException("no cacheName in cache");
		}
		return cache.getStatistics();
	}

	/**
	 * 取cacheName组件缓存名称的信息
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return 组件缓存信息
	 */
	public ApplicationCache getApplicationCache(String cacheName)
			throws EHCacheMgrException {
		ApplicationCache appCache = new ApplicationCache();
		appCache.setCacheInMemorySize(this.calculateInMemorySize(cacheName));
		appCache.setCacheName(cacheName);

		// 设定内存中创建对象的最大值。
		appCache.setMaxElementsInMemory(this.getMaxElementsInMemory(cacheName));

		appCache.setDiskExpiryThreadIntervalSeconds(this
				.getDiskExpiryThreadIntervalSeconds(cacheName));

		appCache.setDiskStoreSize(this.getDiskStoreSize(cacheName));

		appCache.setDiskStoreHitCount(this.getDiskStoreHitCount(cacheName));

		appCache.setMemoryStoreHitCount(this.getMemoryStoreHitCount(cacheName));

		// 设置当内存中缓存达到 maxInMemory 限制时元素是否可写到磁盘 上
		appCache.setOverflowToDisk(this.isOverflowToDisk(cacheName));

		// 设置某个元素消亡前的停顿时间。
		// 也就是在一个元素消亡之前，两次访问时间的最大时间间隔值。
		// 这只能在元素不是永久驻留时有效（译注：如果对象永恒不灭，则
		// 设置该属性也无用）。
		// 如果该值是 0 就意味着元素可以停顿无穷长的时间。

		appCache.setTimeToIdleSeconds(this.getTimeToIdleSeconds(cacheName));

		// 元素设置消亡前的生存时间。
		// 也就是一个元素从构建到消亡的最大时间间隔值。
		// 这只能在元素不是永久驻留时有效。

		appCache.setTimeToLiveSeconds(this.getTimeToLiveSeconds(cacheName));

		// 设置元素（译注：内存中对象）是否永久驻留。如果是，将忽略超
		// 时限制且元素永不消亡。

		appCache.setEternal(this.isEternal(cacheName));

		// appCache.setCachesInMemorySize(this.calculateInMemorySize());
		appCache.setSize(this.getSize(cacheName));
		Statistics stat = this.getStatistics(cacheName);
		appCache.setCacheHits(stat.getCacheHits());
		appCache.setCacheMisses(stat.getCacheMisses());
		appCache.setInMemoryHits(stat.getInMemoryHits());
		appCache.setObjectCount(stat.getObjectCount());
		appCache.setOnDiskHits(stat.getOnDiskHits());
		appCache.setStatisticsAccuracy(stat.getStatisticsAccuracy());

		return appCache;

	}

	/**
	 * 取cacheName组件缓存名称的信息
	 * 
	 * @param cacheName
	 *            缓存组件的名称
	 * @return
	 */
	public List getApplicationCache() throws EHCacheMgrException {
		List list = new ArrayList();
		for (Iterator it = caches.iterator(); it.hasNext();) {
			String cacheName = (String) it.next();
			ApplicationCache appCache = this.getApplicationCache(cacheName);

			list.add(appCache);
		}
		return list;

	}

	/**
	 * 获取缓存名称列表
	 * 
	 * @return
	 */
	public List getCaches() {
		return caches;
	}

	public void setCaches(List caches) {
		this.caches = caches;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * 取cacheName中的所有cacheObject
	 * 
	 * @param cacheName
	 * @return
	 * @throws EHCacheMgrException
	 */
	public Map getCacheObject(String cacheName) throws EHCacheMgrException {
		Map cacheMap = new HashMap();
		List list = this.getKeys(cacheName);
		for (Iterator it = list.iterator(); it.hasNext();) {
			String cacheKey = (String) it.next();
			cacheMap.put(cacheKey, this.getObject(cacheName, cacheKey));
		}
		return cacheMap;
	}
}
