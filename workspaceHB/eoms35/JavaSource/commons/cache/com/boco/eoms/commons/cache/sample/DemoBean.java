package com.boco.eoms.commons.cache.sample;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 1, 2007 2:11:43 PM
 * </p>
 * 
 * @author ��
 * @version 1.0
 * 
 */
public class DemoBean {

	private final Logger logger = Logger.getLogger(this.getClass());

	public DemoBean() {
		super();
	}

	/**
	 * 取当前时间
	 * 
	 * @return
	 */
	public Date getDate() {
		return new Date();
	}

	/**
	 * 取随机数
	 * 
	 * @return
	 */
	public int getRandom() {
		return new Random().nextInt();
	}

	/**
	 * 添加时间，根据applicationContext-ehcache.xml配置，调用addDate时，getDate()缓冲更新
	 * 
	 */
	public void addDate() {
		logger.debug("addDate method is processed");
	}

}
