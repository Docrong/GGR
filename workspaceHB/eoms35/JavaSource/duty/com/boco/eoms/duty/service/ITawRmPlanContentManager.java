
package com.boco.eoms.duty.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawRmAddonsTable;
import com.boco.eoms.duty.model.TawRmPlanContent;

public interface ITawRmPlanContentManager extends Manager {
    /**
     * Retrieves all of the tawRmPlanContents
     */
    public List getTawRmPlanContents(TawRmPlanContent tawRmPlanContent);

    /**
     * Gets tawRmPlanContent's information based on id.
     * @param id the tawRmPlanContent's id
     * @return tawRmPlanContent populated tawRmPlanContent object
     */
    public TawRmPlanContent getTawRmPlanContent(final String id);

    /**
     * Saves a tawRmPlanContent's information
     * @param tawRmPlanContent the object to be saved
     */
    public void saveTawRmPlanContent(TawRmPlanContent tawRmPlanContent);

    /**
     * Removes a tawRmPlanContent from the database by id
     * @param id the tawRmPlanContent's id
     */
    public void removeTawRmPlanContent(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmPlanContents(final Integer curPage, final Integer pageSize, final String whereStr);
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
    
    public void insertTawRoomdutyCheck(String roomId, String addonsTableIDS,String checktype);
    public Map getTawRoomdutyCheckInfo(String roomId,String checktype);
    public List searchTawRoomDutyCheck(String roomId, String checktype,String formname, String state);
    public Map getTableURLMap(String tawRoomdutyCheckId);
    public TawRmAddonsTable getTawRmAddonsTable(String tawRoomdutyCheckId);

}

