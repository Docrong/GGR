package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawRmEmergency;
 

public interface ITawRmEmergencyDao extends Dao {
    /**
     * Retrieves all of the TawRmEmergencys
     */
    public List getTawRmEmergencys(TawRmEmergency tawRmEmergency);

    /**
     * Gets TawRmEmergency's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the TawRmEmergency's id
     * @return TawRmEmergency populated TawRmEmergency object
     */
    public TawRmEmergency getTawRmEmergency(final String id);

    /**
     * Saves a TawRmEmergency's information
     * @param TawRmEmergency the object to be saved
     */    
    public String saveTawRmEmergency(TawRmEmergency tawRmEmergency);

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
    public ArrayList getChildList(String parentId);
    
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
     
    
    public ArrayList getTawRmEmergencySubByEventid(final String id);
    
    public List getTawRmEmergencyByDept(final String deptid);
    public List getTawRmEmergencyByDeptAndFlag(final String deptid,final String startFlag,final String endFlag);
    /**
     * 取主事件在值班班次内并且重要等级大于等于3级
     * @param startTime
     * @param endTime
     * @return 
     */
    public List getTawRmEmergencyByTime(String startTime, String endTime);
}
