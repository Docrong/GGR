
package com.boco.eoms.otherwise.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;

public interface ITawRmInoutRecordManager extends Manager {
    /**
     * Retrieves all of the tawRmInoutRecords
     */
    public List getTawRmInoutRecords(TawRmInoutRecord tawRmInoutRecord);

    /**
     * Gets tawRmInoutRecord's information based on id.
     * @param id the tawRmInoutRecord's id
     * @return tawRmInoutRecord populated tawRmInoutRecord object
     */
    public TawRmInoutRecord getTawRmInoutRecord(final String id);

    /**
     * Saves a tawRmInoutRecord's information
     * @param tawRmInoutRecord the object to be saved
     */
    public void saveTawRmInoutRecord(TawRmInoutRecord tawRmInoutRecord);

    /**
     * Removes a tawRmInoutRecord from the database by id
     * @param id the tawRmInoutRecord's id
     */
    public void removeTawRmInoutRecord(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize, final String whereStr);
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
    
    public List getInRecordList(String ids);
    
    /**
  	 * 根据查询条件得到测试卡记录id列表
  	 * @param whereStr
  	 * @return List
  	 */
  	public List getIdList(String whereStr);
  	
  	public TawRmInoutRecord getOutRecordByTestCardId(String testCardId);
}

