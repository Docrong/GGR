package com.boco.eoms.sheet.overtimetip.service;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;

public interface IOvertimeTipManager extends Manager {
	 /**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */    
	   public void saveOvertimeTip(OvertimeTip overtimeTip);

	   /**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */  
	   public void removeOvertimeTip(String id); 
	   /**
	    * 根据id查询overtimetip记录
	    * @param id
	    * @return OvertimeTip
	    */
	   public OvertimeTip getOvertimeTip(String id);
	   /**
	    * 根据工单名查询该工单的overtimetip记录
	    * @param flowName
	    * @return List
	    */
	   public HashMap getOvertimeTipByAdmin(String flowName, Integer pageIndex, Integer pageSize);
	   /**
	    * 根据工单名和用户名查询overtimetip记录
	    * @param flowName
	    * @param userId
	    * @return List
	    */
	   public HashMap getOvertimeTipByUserId(String flowName,String userId, Integer pageIndex, Integer pageSize);
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param condition
	    * @return List
	    */
	   public List getOvertimeTipByCondition(HashMap condition);
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param condition
	    * @return List
	    */
	   public List getEffectOvertimeTip(String flowName,String userId);
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param condition
	    * @return List
	    */
	   public String getEffectOvertimeTip(String flowName,String userId,HashMap mappingMap);
}
