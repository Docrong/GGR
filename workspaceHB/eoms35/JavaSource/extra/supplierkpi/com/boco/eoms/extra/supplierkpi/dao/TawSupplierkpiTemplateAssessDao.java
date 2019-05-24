
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;

public interface TawSupplierkpiTemplateAssessDao extends Dao {

    /**
     * Retrieves all of the tawSupplierkpiTemplateAssesss
     */
    public List getTawSupplierkpiTemplateAssesss(TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess);

    /**
     * Gets tawSupplierkpiTemplateAssess's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawSupplierkpiTemplateAssess's id
     * @return tawSupplierkpiTemplateAssess populated tawSupplierkpiTemplateAssess object
     */
    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssess(final String id);

    /**
     * Saves a tawSupplierkpiTemplateAssess's information
     * @param tawSupplierkpiTemplateAssess the object to be saved
     */    
    public void saveTawSupplierkpiTemplateAssess(TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess);

    /**
     * Removes a tawSupplierkpiTemplateAssess from the database by id
     * @param id the tawSupplierkpiTemplateAssess's id
     */
    public void removeTawSupplierkpiTemplateAssess(final String id);
    /**
     * curPage
     * pageSize
     */
    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize);
    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize, final String whereStr);
    /**
     * @param templateId
     * @return
     */
    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssessByTemplateId(final String templateId);
    /**
     * 返回专业模型审核表中相应审核状态项
     * @param whereStr
     * @return
     */
    public List getNodesFromTemplateAssess(final String whereStr);
}

