package com.boco.eoms.duty.cache;

import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.duty.service.ITawRmDutyCacheManager;

public class TawDutyManCacheBean {
	private final Logger logger = Logger.getLogger(this.getClass());

	public TawDutyManCacheBean() {
		super();
	}

	/**
	 * 获取所有参与值班人员的信息
	 * 
	 * @return
	 */
	public Map getDutyManCache() {
		ITawRmDutyCacheManager iTawRmDutyCacheManager = (ITawRmDutyCacheManager) ApplicationContextHolder
				.getInstance().getBean("ITawRmDutyCacheManager");
		return iTawRmDutyCacheManager.getDutyManCacheData();
	}

	/**
	 * 添加时间，根据applicationContext-ehcache.xml配置，调用addDate时，getDate()缓冲更新
	 * 
	 */
	public void addDutyManCache() {
		logger.debug("addDutyCache method is processed");
	}
}
