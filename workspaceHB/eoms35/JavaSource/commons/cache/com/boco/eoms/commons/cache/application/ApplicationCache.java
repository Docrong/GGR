package com.boco.eoms.commons.cache.application;



/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 2, 2007 10:32:43 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ApplicationCache {

	/**
	 * 设定内存中创建对象的最大值。
	 */
	private int maxElementsInMemory;

	private long diskExpiryThreadIntervalSeconds;

	/**
	 * 磁盘分配的大小
	 */
	private long diskStoreSize;

	private long diskStoreHitCount;

	private long memoryStoreHitCount;

	/**
	 * 设置当内存中缓存达到 maxInMemory 限制时元素是否可写到磁盘 上
	 */
	private boolean overflowToDisk;

	/**
	 * 设置某个元素消亡前的停顿时间。
	 * 
	 * 也就是在一个元素消亡之前，两次访问时间的最大时间间隔值。
	 * 
	 * 这只能在元素不是永久驻留时有效（译注：如果对象永恒不灭，则
	 * 
	 * 设置该属性也无用）。
	 * 
	 * 如果该值是 0 就意味着元素可以停顿无穷长的时间。
	 */
	private long timeToIdleSeconds;

	/**
	 * 元素设置消亡前的生存时间。
	 * 
	 * 也就是一个元素从构建到消亡的最大时间间隔值。
	 * 
	 * 这只能在元素不是永久驻留时有效。
	 */
	private long timeToLiveSeconds;

	/**
	 * 设置元素（译注：内存中对象）是否永久驻留。如果是，将忽略超
	 * 
	 * 时限制且元素永不消亡。
	 */
	public boolean eternal;

	/**
	 * 某组件缓存内存大小
	 */
	private long cacheInMemorySize;

	/**
	 * 缓存名称
	 */
	private String cacheName;

	/**
	 * 缓存大小
	 */
	private int size;

	private int statisticsAccuracy;

	private int cacheHits;

	private int onDiskHits;

	private int inMemoryHits;

	private int cacheMisses;

	private int statisticsSize;

	private int objectCount;

	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}

	public int getCacheHits() {
		return cacheHits;
	}

	public void setCacheHits(int cacheHits) {
		this.cacheHits = cacheHits;
	}

	public long getCacheInMemorySize() {
		return cacheInMemorySize;
	}

	public void setCacheInMemorySize(long cacheInMemorySize) {
		this.cacheInMemorySize = cacheInMemorySize;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public int getInMemoryHits() {
		return inMemoryHits;
	}

	public void setInMemoryHits(int inMemoryHits) {
		this.inMemoryHits = inMemoryHits;
	}

	public int getCacheMisses() {
		return cacheMisses;
	}

	public void setCacheMisses(int cacheMisses) {
		this.cacheMisses = cacheMisses;
	}

	public int getOnDiskHits() {
		return onDiskHits;
	}

	public void setOnDiskHits(int onDiskHits) {
		this.onDiskHits = onDiskHits;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStatisticsAccuracy() {
		return statisticsAccuracy;
	}

	public void setStatisticsAccuracy(int statisticsAccuracy) {
		this.statisticsAccuracy = statisticsAccuracy;
	}

	public int getStatisticsSize() {
		return statisticsSize;
	}

	public void setStatisticsSize(int statisticsSize) {
		this.statisticsSize = statisticsSize;
	}

	public long getDiskExpiryThreadIntervalSeconds() {
		return diskExpiryThreadIntervalSeconds;
	}

	public void setDiskExpiryThreadIntervalSeconds(
			long diskExpiryThreadIntervalSeconds) {
		this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
	}

	public long getDiskStoreHitCount() {
		return diskStoreHitCount;
	}

	public void setDiskStoreHitCount(long diskStoreHitCount) {
		this.diskStoreHitCount = diskStoreHitCount;
	}

	public long getDiskStoreSize() {
		return diskStoreSize;
	}

	public void setDiskStoreSize(long diskStoreSize) {
		this.diskStoreSize = diskStoreSize;
	}

	public boolean isEternal() {
		return eternal;
	}

	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}

	public int getMaxElementsInMemory() {
		return maxElementsInMemory;
	}

	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}

	public long getMemoryStoreHitCount() {
		return memoryStoreHitCount;
	}

	public void setMemoryStoreHitCount(long memoryStoreHitCount) {
		this.memoryStoreHitCount = memoryStoreHitCount;
	}

	public boolean isOverflowToDisk() {
		return overflowToDisk;
	}

	public void setOverflowToDisk(boolean overflowToDisk) {
		this.overflowToDisk = overflowToDisk;
	}

	public long getTimeToIdleSeconds() {
		return timeToIdleSeconds;
	}

	public void setTimeToIdleSeconds(long timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}

	public long getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public void setTimeToLiveSeconds(long timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

}
