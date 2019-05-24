
package com.boco.eoms.otherwise.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.otherwise.model.TawRmTestcard;

public interface ITawRmTestcardManager extends Manager {
    /**
     * Retrieves all of the tawRmTestcards
     */
    public List getTawRmTestcards(TawRmTestcard tawRmTestcard);

    /**
     * Gets tawRmTestcard's information based on id.
     * @param id the tawRmTestcard's id
     * @return tawRmTestcard populated tawRmTestcard object
     */
    public TawRmTestcard getTawRmTestcard(final String id);

    /**
     * Saves a tawRmTestcard's information
     * @param tawRmTestcard the object to be saved
     */
    public void saveTawRmTestcard(TawRmTestcard tawRmTestcard);

    /**
     * Removes a tawRmTestcard from the database by id
     * @param id the tawRmTestcard's id
     */
    public void removeTawRmTestcard(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize, final String whereStr);
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
}

