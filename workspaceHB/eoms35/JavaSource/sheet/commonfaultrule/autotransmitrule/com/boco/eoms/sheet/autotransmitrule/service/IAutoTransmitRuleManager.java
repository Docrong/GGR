package com.boco.eoms.sheet.autotransmitrule.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.autotransmitrule.model.AutoTransmitRule;

public interface IAutoTransmitRuleManager extends Manager {
	 /**
	    * Saves a AutoTransmitRule information
	    * @param AutoTransmitRule the object to be saved
	    */    
	   public void saveAutoTransmitRule(AutoTransmitRule autotransmitrule);

	   /**
	    * Saves a AutoTransmitRule information
	    * @param AutoTransmitRule the object to be saved
	    */  
	   public void removeAutoTransmitRule(String id); 
	   /**
	    * 根据id查询AutoTransmitRule记录
	    * @param id
	    * @return AutoTransmitRule
	    */
	   public AutoTransmitRule getAutoTransmitRule(String id);
		/**
		 * 得到所有的记录
		 */
		public HashMap getAllAutoTransmitRule(Integer pageIndex, Integer pageSize);
}
