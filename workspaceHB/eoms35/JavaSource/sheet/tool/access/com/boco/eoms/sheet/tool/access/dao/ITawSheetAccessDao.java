
package com.boco.eoms.sheet.tool.access.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;

public interface ITawSheetAccessDao extends Dao {

    /**
     * Retrieves all of the tawSheetAccesss
     */
    public List getTawSheetAccesss(TawSheetAccess tawSheetAccess);

    /**
     * Gets tawSheetAccess's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawSheetAccess's id
     * @return tawSheetAccess populated tawSheetAccess object
     */
    public TawSheetAccess getTawSheetAccess(final Integer id);

    /**
     * Saves a tawSheetAccess's information
     * @param tawSheetAccess the object to be saved
     */    
    public void saveTawSheetAccess(TawSheetAccess tawSheetAccess);

    /**
     * Removes a tawSheetAccess from the database by id
     * @param id the tawSheetAccess's id
     */
    public void removeTawSheetAccess(final Integer id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getTawSheetAccesss(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    
    /**
     * 根据附件ID查询附件信息
     * @param accessid
     * @return
     */
    public TawSheetAccess getAccessByAccessId(String accessid);
    /**
     * 查询某附件的下一级附件信息
     * @param accessid
     * @return
     */
    public List getSonAccessByAccessId(String accessid);
    /**
     * 查询同级附件信息
     * @param parentareaid
     * @param ordercode
     * @return
     */
    public List getSameLevelAccess(String parentaccessid,Integer ordercode);
    /**
     * 查询某附件名称是否存在
     * @param areaname
     * @return 
     */
    public boolean isExitTaskName(String processname,String taskname);
    /**
     * 查询某附件的所有子附件信息
     * @param accessid
     * @return
     */
    public List getAllSonAccessByAreaid(String accessid);
    
    /**
	 * 根据PROCESSNAME TASKNAME查询流程附件模板
	 * @param processname
	 * @param taskname
	 * @return
	 */
	public TawSheetAccess getAccessByPronameAndTaskname(String processname,String taskname);
}

