
package com.boco.eoms.duty.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;

public interface ITawRmWorkorderRecordManager extends Manager {
    /**
     * Retrieves all of the tawRmWorkorderRecords
     */
    public List getTawRmWorkorderRecords(TawRmWorkorderRecord tawRmWorkorderRecord);

    /**
     * Gets tawRmWorkorderRecord's information based on id.
     * @param id the tawRmWorkorderRecord's id
     * @return tawRmWorkorderRecord populated tawRmWorkorderRecord object
     */
    public TawRmWorkorderRecord getTawRmWorkorderRecord(final String id);

    /**
     * Saves a tawRmWorkorderRecord's information
     * @param tawRmWorkorderRecord the object to be saved
     */
    public void saveTawRmWorkorderRecord(TawRmWorkorderRecord tawRmWorkorderRecord);

    /**
     * Removes a tawRmWorkorderRecord from the database by id
     * @param id the tawRmWorkorderRecord's id
     */
    public void removeTawRmWorkorderRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize, final String whereStr);
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
	 * 得到未处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getUndoWorkOrderList(String userId,String roleIdList);
	
	/**
	 * 得到已处理任务工单
	 * @param userId
	 * @return List
	 */
	public List getFinishWorkOrderList(String userId,String userName);
	
	/** 
     * 通过prelinkId得到userId
	 * @param preLinkId
     */
	public String getUserIdByPrelinkId (String preLinkId);
	
	/** 
   * 通过mainId得到userId
 * @param preLinkId
   */
	public String getUserIdByMainId (String mainId);
}

