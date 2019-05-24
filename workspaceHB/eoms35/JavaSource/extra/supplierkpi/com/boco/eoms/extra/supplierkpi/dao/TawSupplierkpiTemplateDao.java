
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;

public interface TawSupplierkpiTemplateDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiTemplates
     */
    public List getTawSupplierkpiTemplates(TawSupplierkpiTemplate tawSupplierkpiTemplate);

    /**
     * Gets tawSupplierkpiTemplate's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawSupplierkpiTemplate's id
     * @return tawSupplierkpiTemplate populated tawSupplierkpiTemplate object
     */
    public TawSupplierkpiTemplate getTawSupplierkpiTemplate(final String id);

    /**
     * Saves a tawSupplierkpiTemplate's information
     * @param tawSupplierkpiTemplate the object to be saved
     */    
    public void saveTawSupplierkpiTemplate(TawSupplierkpiTemplate tawSupplierkpiTemplate);

    /**
     * Removes a tawSupplierkpiTemplate from the database by id
     * @param id the tawSupplierkpiTemplate's id
     */
    public void removeTawSupplierkpiTemplate(final String id);
    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize);
    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize, final String whereStr);
    
    public String getTemplateIdBySpecialType(final String specialType);
    
    public String getTemplateIdByServiceType(final String serviceType);
    
    /**
     * 从模型表获取树形节点
     * @param whereStr
     * @return
     */
    public List getNodesFromTemplate(final String whereStr);
}

