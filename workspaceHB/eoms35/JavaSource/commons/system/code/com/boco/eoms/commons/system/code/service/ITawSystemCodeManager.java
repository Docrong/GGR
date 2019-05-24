
package com.boco.eoms.commons.system.code.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.code.model.TawSystemCode;

public interface ITawSystemCodeManager extends Manager {
    /**
     * Retrieves all of the tawSystemCodes
     */
    public List getTawSystemCodes(TawSystemCode tawSystemCode);

    /**
     * Gets tawSystemCode's information based on id.
     * @param id the tawSystemCode's id
     * @return tawSystemCode populated tawSystemCode object
     */
    public TawSystemCode getTawSystemCode(final String id);

    /**
     * Saves a tawSystemCode's information
     * @param tawSystemCode the object to be saved
     */
    public void saveTawSystemCode(TawSystemCode tawSystemCode);

    /**
     * Removes a tawSystemCode from the database by id
     * @param id the tawSystemCode's id
     */
    public void removeTawSystemCode(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize, final String whereStr);
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

	public List getTawSystemCodeByCondition(String str);
}

