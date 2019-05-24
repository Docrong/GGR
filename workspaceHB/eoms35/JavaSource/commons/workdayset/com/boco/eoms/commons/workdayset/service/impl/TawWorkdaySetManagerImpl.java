
package com.boco.eoms.commons.workdayset.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.workdayset.dao.ITawWorkdaySetDao;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;
import com.boco.eoms.commons.workdayset.service.ITawWorkdaySetManager;

public class TawWorkdaySetManagerImpl extends BaseManager implements ITawWorkdaySetManager {
    private ITawWorkdaySetDao dao;


	/**
	 * Retrieves areaId 
	 * @param deptmentId
     * @return AreaId
	 */
	public String getAreaId(String deptId)
	{
		return dao.getAreaId(deptId);
	}
	/**
	 * Retrieves Areaname 
	 * @param areaId
     * @return Areaname
	 */
	public String getAreaname(final String areaid){
		return dao.getAreaname(areaid);
	}
	/**
     * Set the Dao for communication with the data layer.
     * @param TawWorkdaySets
     */
    public List getInfo(final String areaid){
    	//if(dao.getInfo(areaid) == null)
    	//	return null;
    	//else
    	return dao.getInfo(areaid);    }
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawWorkdaySetDao(ITawWorkdaySetDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySets(com.boco.eoms.commons.workdayset.model.TawWorkdaySet)
     */
    public List getTawWorkdaySets(final TawWorkdaySet tawWorkdaySet) {
        return dao.getTawWorkdaySets(tawWorkdaySet);
    }

    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySetss(com.boco.eoms.commons.workdayset.model.TawWorkdaySet)
     */
    public ArrayList getTawWorkdaySetss(final TawWorkdaySet tawWorkdaySet){
    	return dao.getTawWorkdaySetss(tawWorkdaySet);
    }
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySet(String id)
     */
    public TawWorkdaySet getTawWorkdaySet(final String id) {
        return dao.getTawWorkdaySet(new String(id));
    }
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySet(String workday,String areaid)
     */
    public void removeTawWorkdaySets(final String workday,final String areaid){
    	 dao.removeTawWorkdaySets(workday,areaid);
    }
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#saveTawWorkdaySet(TawWorkdaySet tawWorkdaySet)
     */
    public void saveTawWorkdaySet(TawWorkdaySet tawWorkdaySet) {
        dao.saveTawWorkdaySet(tawWorkdaySet);
    }

    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#removeTawWorkdaySet(String id)
     */
    public void removeTawWorkdaySet(final String id) {
        dao.removeTawWorkdaySet(new String(id));
    }
    
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#removeTawWorkdaySet(com.boco.eoms.commons.workdayset.model.TawWorkdaySet)
     */
    public void removeTawWorkdaySet(final TawWorkdaySet tawWorkdaySet){
    	dao.removeTawWorkdaySet(tawWorkdaySet);
    }
    
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySets(final Integer curPage, final Integer pageSize)
     */
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize) {
        return dao.getTawWorkdaySets(curPage, pageSize,null);
    }
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getTawWorkdaySets(final Integer curPage, final Integer pageSize, final String whereStr)
     */    
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawWorkdaySets(curPage, pageSize, whereStr);
    }
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getChildList(String parentId)
     */     
    public List getChildList(String parentId) {		
		return dao.getChildList(parentId);
	}
    /**
     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#xGetChildNodes(String parentId)
     */  	
	public JSONArray xGetChildNodes(String parentId) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();	
		list = this.getChildList(parentId);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawWorkdaySet obj = (TawWorkdaySet) rowIt.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", obj.getId());
			jitem.put("text", obj.getWorkDate());
			jitem.put("name", obj.getCreateTime());
			jitem.put("allowChild", true);
			jitem.put("allowDelete", true);
//			if(obj.getLeaf().equals("1")){
//				jitem.put("leaf", true);
//			}
			json.put(jitem);
		}
		return json;
	}	
	/**
	   * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#isWorkday(String workDay,String areaid) 
	   */  	
		public boolean isWorkday(final String workDay,final String areaid) {	
			return dao.isWorkday(workDay,areaid);
			}	
		 /**
	     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#getSubAreaid(String areaid)
	     */
	    public List getSubAreaid(final String areaid){
	    	return dao.getSubAreaid(areaid);
	    }
	   
	    /**
	     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#saveAllTawWorkdaySet(List tawWorkdaySet)
	     */
	    public void saveAllTawWorkdaySet(final List tawWorkdaySet){
	    	dao.saveAllTawWorkdaySet(tawWorkdaySet);
	    }
	    /**
	     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#removeTawWorkdaySets(String workday)
	     */
	    public void removeTawWorkdaySets(final String workday){
	    	dao.removeTawWorkdaySets(workday);
	    }
	    /**
	     * @see com.boco.eoms.workdayset.service.ITawWorkdaySetManager#saveCityTawWorkdaySet(final List tawWorkdaySet)
	     */
	    public void saveCityTawWorkdaySet(final List tawWorkdaySet){
	    	dao.saveCityTawWorkdaySet(tawWorkdaySet);
	    }


	    /**
	     * 功能：根据地域、给定日期、给定时间判断该时间是否为工作时间
	     * @param areaid区域id
	     * @param date给定日期
	     * @param time给定日期
	     * @return true or false
	     */
	    public boolean isWorktime(final String areaid, final String date, final String time)
	    {
	    	return dao.isWorktime(areaid, date, time);
	    }


		/** 
		 * 功能：根据地域、开始日期、结束日期取该阶段的非工作日集合
		 * @param areaid区域id
		 * @param startdate开始日期
		 * @param enddate结束日期
	     * @return List 非工作日集合，String型，用时需要转换成Date或其他类型
		 */
	    public List getUnWorkday(final String areaid,  final String startdate, final String enddate)
	    {
	    	return dao.getUnWorkday(areaid, startdate, enddate);
	    }


		/** 
		 * 功能：根据地域、开始日期、结束日期取该阶段的工作日集合
		 * @param areaid区域id
		 * @param startdate开始日期
		 * @param enddate结束日期
	     * @return List 工作日集合，String型，用时需要转换成Date或其他类型
		 */
		public List getWorkday(final String areaid,  final String startdate, final String enddate) 
		{
			return dao.getWorkday(areaid, startdate, enddate);
		}

		/** 
		 * 功能：返回给定areaid对应的日期的工作时间
		 * @param areaid区域id
	     * @return List 工作时间集合，String型，用时需要转换成Date或其他类型
		 */
	    public List getWorktime(final String areaid,  final String date)
	    {
	    	return dao.getWorktime(areaid, date);
	    }


	     /** 
		 * 功能：根据地域、给定日期、开始时间、结束时间判断该段时间是否为工作时间，
		 * @param areaid区域id
		 * @param date给定日期
		 * @param starttime 开始时间
		 * @param endtime结束时间
		 * @return如果这段时间均为工作时间，返回1；均不为工作时间，返回0；否则，返回2；返回值为int型
		 */
	    public int isWorkdayT2T(final String areaid, final String date, final String starttime, final String endtime)
	    {
	    	return dao.isWorkdayT2T(areaid, date, starttime, endtime);
	    }


	     /** 
		 * 功能：根据地域、开始日期、结束日期判断该段时间是否为工作日
		 * @param areaid区域id
		 * @param date给定日期
		 * @param startdate开始日期
		 * @param enddate结束日期
		 * @return 如果这段时间均为工作日，返回1；均不为工作时间，返回0；否则，返回2；返回值为int型
		 */
	    public int isWorkdayD2D(final String areaid, final String startdate, final String enddate)
	    {
	    	return dao.isWorkdayD2D(areaid, startdate, enddate);
	    }
}
