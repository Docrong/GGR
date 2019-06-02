package com.boco.eoms.duty.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmEmergency;
import com.boco.eoms.duty.model.TawRmEmergency;
 
/**
 * @author User 龚玉峰
 *
 */
public interface ITawRmEmergencyManager extends Manager{
    /**
     * Retrieves all of the TawRmEmergencys
     */
    public List getTawRmEmergencys(TawRmEmergency TawRmEmergency);

    /**
     * Gets TawRmEmergency's information based on id.
     * @param id the TawRmEmergency's id
     * @return TawRmEmergency populated TawRmEmergency object
     */
    public TawRmEmergency getTawRmEmergency(final String id);

    /**
     * Saves a TawRmEmergency's information
     * @param TawRmEmergency the object to be saved
     */
    public String saveTawRmEmergency(TawRmEmergency TawRmEmergency);

    /**
     * Removes a TawRmEmergency from the database by id
     * @param id the TawRmEmergency's id
     */
    public void removeTawRmEmergency(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmEmergencys(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmEmergencys(final Integer curPage, final Integer pageSize, final String whereStr);
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
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmEmergencySubs(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmEmergencySubs(final Integer curPage, final Integer pageSize, final String whereStr);
    
    /**
     * Gets TawRmEmergency's information based on id.
     * @param id the TawRmEmergency's id
     * @return TawRmEmergency populated TawRmEmergency object
     */
     /*
	 * 根据主事件记录id得到所有子事件记录
	 */
	
	public List getTawRmEmergencySubByEventid(final String eventId);
	/*
	 * 根据部门id得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmEmergencyByDept(final String deptid);
	/**
     * 取主事件在值班班次内并且重要等级大于等于3级
     * @param startTime
     * @param endTime
     * @return 
     */
    public List getTawRmEmergencyByTime(String startTime, String endTime);
	/*
	 * 根据部门id和事件的重要登记得到这个部门添加的事件记录。关联到子部门
	 */
	public List getTawRmEmergencyByDeptAndFlag(final String deptid,final String startFlag,final String endFlag);

}
