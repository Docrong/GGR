package com.boco.eoms.duty.cache;

import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.duty.service.ITawRmDutyCacheManager;

public class TawDutyCacheBean {

	private final Logger logger = Logger.getLogger(this.getClass());

	public TawDutyCacheBean() {
		super();
	}

	/**
	 * 获取值班人员的信息
	 * 
	 * @return
	 */
	public Map getDutyCache() {
		ITawRmDutyCacheManager iTawRmDutyCacheManager = (ITawRmDutyCacheManager) ApplicationContextHolder
				.getInstance().getBean("ITawRmDutyCacheManager");
		return iTawRmDutyCacheManager.getCacheData();
	}

	/**
	 * 添加时间，根据applicationContext-ehcache.xml配置，调用addDate时，getDate()缓冲更新
	 * 
	 */
	public void addDutyCache() {
		logger.debug("addDutyCache method is processed");
	}
	
}
