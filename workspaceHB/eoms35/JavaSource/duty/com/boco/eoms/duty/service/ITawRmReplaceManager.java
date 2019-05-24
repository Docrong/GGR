
package com.boco.eoms.duty.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmReplace;

public interface ITawRmReplaceManager extends Manager {
    /**
     * Retrieves all of the tawRmReplaces
     */
    public List getTawRmReplaces(TawRmReplace tawRmReplace);

    /**
     * Gets tawRmReplace's information based on id.
     * @param id the tawRmReplace's id
     * @return tawRmReplace populated tawRmReplace object
     */
    public TawRmReplace getTawRmReplace(final String id);

    /**
     * Saves a tawRmReplace's information
     * @param tawRmReplace the object to be saved
     */
    public void saveTawRmReplace(TawRmReplace tawRmReplace);

    /**
     * Removes a tawRmReplace from the database by id
     * @param id the tawRmReplace's id
     */
    public void removeTawRmReplace(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize, final String whereStr);
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
    
    
    public Vector getreplaceApplyVector(int roomId, String user_id, String startdate,
			String enddate, String flag) throws SQLException ;
    
    //
    public List getreplaceApplyVector(int roomId, String user_id,
			String recever, String startdate, String enddate, String flag)
			throws SQLException ;
     
    public void replaceDuty(int roomId, String inpudate,String user_id,
			String applyfrom,String recevier,String reason,String remark)
			throws SQLException ;
    
   
    
    public void replaceDutyMain(String userid,String workid,String recevier);
    
    public void replaceSendMsg(TawRmReplace tawRmReplace);
    
}

