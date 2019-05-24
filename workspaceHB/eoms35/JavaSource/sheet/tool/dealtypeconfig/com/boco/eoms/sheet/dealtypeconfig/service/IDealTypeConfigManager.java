package com.boco.eoms.sheet.dealtypeconfig.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;

public interface IDealTypeConfigManager extends Manager {
	 /**
	    * 保存DealTypeConfig
	    * @param DealTypeConfig
	    */    
	   public void saveDealTypeConfig(DealTypeConfig config) throws Exception;
	   /**
	    * 删除DealTypeConfig
	    * @param id
	    */  
	   public void removeDealTypeConfig(String id) throws Exception;
	   /**
	    * 根据id查询dealtypeconfig记录
	    * @param id
	    * @return DealTypeConfig
	    */
	   public DealTypeConfig getDealTypeConfig(String id) throws Exception;
	   /**
	    * 根据工单名查询该工单的dealtypeconfig记录
	    * @param flowName
	    * @return List
	    */
	   public DealTypeConfig getDealTypeConfigByAdmin(String flowName, String taskName) throws Exception;
	   /**
	    * 根据工单名和用户名查询dealtypeconfig记录
	    * @param flowName
	    * @param userId
	    * @return List
	    */
	   public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId,String taskName) throws Exception;
	   /**
	    * 根据工单名查询该工单的dealtypeconfig记录
	    * @param flowName
	    * @return List
	    */
	   public DealTypeConfig getDealTypeConfigByAdmin(String flowName) throws Exception;
	   /**
	    * 根据工单名和用户名查询dealtypeconfig记录
	    * @param flowName
	    * @param userId
	    * @return List
	    */
	   public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId) throws Exception;
	   /**
	    * 根据特定的条件查询dealtypeconfig记录
	    * @param condition
	    * @return List
	    */
	   public List getDealTypeConfigByCondition(String condition) throws Exception;
	   /**
	    * 根据特定的条件查询dealtypeconfig记录
	    * @param condition
	    * @return List
	    */
	   public List getDealTypeConfigByCondition(HashMap condition) throws Exception;
}
