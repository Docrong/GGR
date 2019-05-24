package com.boco.eoms.sheet.overtimetip.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.sheet.overtimetip.dao.IOvertimeTipDAO;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;

public class OvertimeTipManagerImpl extends BaseManager implements IOvertimeTipManager {

	private IOvertimeTipDAO dao;
	 public IOvertimeTipDAO getDao() {
		return dao;
	}

	public void setDao(IOvertimeTipDAO dao) {
		this.dao = dao;
	}

	/**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */    
	   public void saveOvertimeTip(OvertimeTip overtimeTip){
		   dao.saveOvertimeTip(overtimeTip);
	   }

	   /**
	    * Saves a tawSheetExpert's information
	    * @param tawSheetExpert the object to be saved
	    */  
	   public void removeOvertimeTip(String id){
		   dao.removeOvertimeTip(id);
	   }
	   /**
	    * 根据id查询overtimetip记录
	    * @param id
	    * @return OvertimeTip
	    */
	   public OvertimeTip getOvertimeTip(String id){
		   return dao.getOvertimeTip(id);
	   }
	   /**
	    * 根据工单名查询该工单的overtimetip记录
	    * @param flowName
	    * @return List
	    */
	   public HashMap getOvertimeTipByAdmin(String flowName, Integer pageIndex, Integer pageSize){
		   return dao.getOvertimeTipByAdmin(flowName, pageIndex, pageSize);
	   }
	   /**
	    * 根据工单名和用户名查询overtimetip记录
	    * @param flowName
	    * @param userId
	    * @return List
	    */
	   public HashMap getOvertimeTipByUserId(String flowName,String userId, Integer pageIndex, Integer pageSize){
		   return dao.getOvertimeTipByUserId(flowName, userId, pageIndex, pageSize);
	   }
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param condition
	    * @return List
	    */
	   public List getOvertimeTipByCondition(HashMap condition){
		   return dao.getOvertimeTipByCondition(condition);
	   }
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param flowName
	    * @param userId
	    * @param mappingMap
	    * @return String
	    */
	   public List getEffectOvertimeTip(String flowName,String userId) {
			String condition = " (userId='"+userId+"' or userId='system' or userId='admin') and (flowName='"+flowName+"' or flowName is null)";
			List timeList = dao.getOvertimeTipByCondition(condition);
			return timeList;
	   }
	   /**
	    * 根据特定的条件查询Overtime记录
	    * @param flowName
	    * @param userId
	    * @param mappingMap
	    * @return String
	    */
	   public String getEffectOvertimeTip(String flowName,
			String userId, HashMap mappingMap) {
		String overtimeLimit = "";
		String specialty1="",specialty2="",specialty3="",specialty4="",specialty5="",specialty6="",specialty7="",specialty8="",specialty9="",specialty10="";
		Iterator it = mappingMap.keySet().iterator();
		while(it.hasNext()){
			String special = (String)it.next();
			String value = (String)mappingMap.get(special);
			if(special.equals("specialty1")){
				specialty1 = value;
			}
			if(special.equals("specialty2")){
				specialty2 = value;
			}
			if(special.equals("specialty3")){
				specialty3 = value;
			}
			if(special.equals("specialty4")){
				specialty4 = value;
			}
			if(special.equals("specialty5")){
				specialty5 = value;
			}
			if(special.equals("specialty6")){
				specialty6 = value;
			}
			if(special.equals("specialty7")){
				specialty7 = value;
			}
			if(special.equals("specialty8")){
				specialty8 = value;
			}
			if(special.equals("specialty9")){
				specialty9 = value;
			}
			if(special.equals("specialty10")){
				specialty10 = value;
			}
		}
		
		
		List timeList = dao.getEffectOvertimeTipByUserId(flowName, userId, specialty1, specialty2, specialty3, specialty4, specialty5, specialty6, specialty7, specialty8, specialty9, specialty10);
		if(timeList!=null&&timeList.size()!=0){
				OvertimeTip overtimetip = (OvertimeTip)timeList.get(0);
				overtimeLimit = overtimetip.getOvertimeLimit();
		}else{
			HashMap condition = new HashMap();
			condition.put("userId", "='system'");
			condition.put("flowName", "='"+flowName+"'");
			timeList = dao.getOvertimeTipByCondition(condition);
			if(timeList==null||timeList.size()==0){
				condition.put("flowName", " is null");
				timeList = dao.getOvertimeTipByCondition(condition);
			}
			OvertimeTip overtimetip = (OvertimeTip)timeList.get(0);
			overtimeLimit = overtimetip.getOvertimeLimit();
		}
		return overtimeLimit;
	}
}
