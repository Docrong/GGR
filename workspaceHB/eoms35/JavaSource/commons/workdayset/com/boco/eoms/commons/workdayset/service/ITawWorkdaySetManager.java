
package com.boco.eoms.commons.workdayset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.workdayset.model.TawWorkdaySet;

public interface ITawWorkdaySetManager extends Manager {

	/**
	 * Retrieves areaId 
	 * @param deptmentId
     * @return AreaId
	 */
	public String getAreaId(String deptId);
	/**
     * Retrieves all of the tawWorkdaySets
     */
    public List getTawWorkdaySets(TawWorkdaySet tawWorkdaySet);
    /**
	 * Retrieves Areaname 
	 * @param areaId
     * @return Areaname
	 */
	public String getAreaname(final String areaid);
    /**
     * Retrieves all of the tawWorkdaySets
     */
    public List getInfo(final String areaid);
    /**
     * Gets tawWorkdaySet's information . 
     * 
     * @param tawWorkdaySet to get tawWorkdaySet de workday
     * @return tawWorkdaySet list belongs to the date
     */
    public ArrayList getTawWorkdaySetss(final TawWorkdaySet tawWorkdaySet);
    /**
     * Gets tawWorkdaySet's information based on id.
     * @param id the tawWorkdaySet's id
     * @return tawWorkdaySet populated tawWorkdaySet object
     */
    public TawWorkdaySet getTawWorkdaySet(final String id);

    /**
     * Saves a tawWorkdaySet's information
     * @param tawWorkdaySet the object to be saved
     */
    public void saveTawWorkdaySet(TawWorkdaySet tawWorkdaySet);

    /**
     * Removes a tawWorkdaySet from the database by id
     * @param id the tawWorkdaySet's id
     */
    public void removeTawWorkdaySet(final String id);
    /**
     * Removes all tawWorkdaySets from the database by tawWorkdaySet 's workdate
     * @param tawWorkdaySet the tawWorkdaySet to show the workdate
     */
    public void removeTawWorkdaySet(final TawWorkdaySet tawWorkdaySet);
    /**
     * Removes all tawWorkdaySets from the database by tawWorkdaySet 's workdate
     * @param workday the tawWorkdaySet's workday
     * @param areaid  the tawWorkdaySet's areaid
     */
    public void removeTawWorkdaySets(final String workday,final String areaid);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawWorkdaySets(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */     
    public List getChildList(String parentId);
    /**
     * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
     * @param parentId
     */
    public JSONArray xGetChildNodes(String parentId);
    /**
     * Judge day is workDay
     * @param workDay the tawWorkdaySet's workDate
     * @param areaid  the tawworkdaySet's areaid
     */
    public boolean isWorkday(final String workDay,final String areaid);
    /**
     * 得到市级以下的县级的areaid
     * @param areaid 市级的id
     */ 
    public List getSubAreaid(final String areaid);
  
    /**
     * 删除所有的tawWorkdaySet中workdate那天的数据库中所有的表
     * 保存所有市县tawWorkdaySet的信息
     * @param  tawWorkdaySet
     */ 
    public void saveAllTawWorkdaySet(final List tawWorkdaySet);
    /**
     * 删除workday那天数据库中所有的记录
     * @param workday
     */ 
    public void removeTawWorkdaySets(final String workday);
    /**
     * 删除所有的tawWorkdaySet中workdate那天且地址是tawWorkdaySet中城市以下县级的数据库中所有的记录
     * 保存所有市县tawWorkdaySet的信息
     * @param  tawWorkdaySet
     */
    public void saveCityTawWorkdaySet(final List tawWorkdaySet);


    /**
     * 功能：根据地域、给定日期、给定时间判断该时间是否为工作时间
     * @param areaid区域id
     * @param date给定日期
     * @param time给定日期
     * @return true or false
     */
    public boolean isWorktime(final String areaid, final String date, final String time);


	/** 
	 * 功能：根据地域、开始日期、结束日期取该阶段的非工作日集合
	 * @param areaid区域id
	 * @param startdate开始日期
	 * @param enddate结束日期
     * @return List 非工作日集合，String型，用时需要转换成Date或其他类型
	 */
    public List getUnWorkday(final String areaid,  final String startdate, final String enddate);


	/** 
	 * 功能：根据地域、开始日期、结束日期取该阶段的工作日集合
	 * @param areaid区域id
	 * @param startdate开始日期
	 * @param enddate结束日期
     * @return List 工作日集合，String型，用时需要转换成Date或其他类型
	 */
	public List getWorkday(final String areaid,  final String startdate, final String enddate) ;   

	/** 
	 * 功能：返回给定areaid对应的日期的工作时间
	 * @param areaid区域id
     * @return List 工作时间集合，String型，用时需要转换成Date或其他类型
	 */
    public List getWorktime(final String areaid,  final String date);


     /** 
	 * 功能：根据地域、给定日期、开始时间、结束时间判断该段时间是否为工作时间，
	 * @param areaid区域id
	 * @param date给定日期
	 * @param starttime 开始时间
	 * @param endtime结束时间
	 * @return如果这段时间均为工作时间，返回1；均不为工作时间，返回0；否则，返回2；返回值为int型
	 */
    public int isWorkdayT2T(final String areaid, final String date, final String starttime, final String endtime);


     /** 
	 * 功能：根据地域、开始日期、结束日期判断该段时间是否为工作日
	 * @param areaid区域id
	 * @param date给定日期
	 * @param startdate开始日期
	 * @param enddate结束日期
	 * @return 如果这段时间均为工作日，返回1；均不为工作时间，返回0；否则，返回2；返回值为int型
	 */
    public int isWorkdayD2D(final String areaid, final String startdate, final String enddate);
}

