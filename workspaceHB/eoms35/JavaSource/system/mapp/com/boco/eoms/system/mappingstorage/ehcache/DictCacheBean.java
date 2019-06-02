package com.boco.eoms.system.mappingstorage.ehcache;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;
import com.boco.eoms.commons.cache.util.CacheUtil;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.system.mappingstorage.mgr.MappingJdbcMgr;
import com.boco.eoms.system.mappingstorage.mgr.MappingMgr;

public class DictCacheBean {


	
	
	
	

	private final Logger logger = Logger.getLogger(this.getClass());

	public DictCacheBean() {
		super();
	}

	
	
	public String getDictData(String appcode, String sheetkey) {
		
		
		//dictIdToNameMgr mgr=(dictIdToNameMgr)ApplicationContextHolder.getInstance().getBean("dictToname");
		
		MappingJdbcMgr mgr =(MappingJdbcMgr)ApplicationContextHolder.getInstance().getBean("mappingJdbcMgr");
		return mgr.dictToName(appcode, sheetkey);
		
	}

	
	public int getRandom() {
		return new Random().nextInt();
	}

	
	 // 添加时间，根据applicationContext-ehcache.xml配置，调用addDate时，getDate()缓冲更新
	
	public void addDictData() {
		logger.debug("addDate method is processed");
	}

}
