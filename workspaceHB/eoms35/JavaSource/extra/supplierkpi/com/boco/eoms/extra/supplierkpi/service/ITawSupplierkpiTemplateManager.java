
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;

public interface ITawSupplierkpiTemplateManager extends Manager {
    /**
     * Retrieves all of the tawSupplierkpiTemplates
     */
    public List getTawSupplierkpiTemplates(TawSupplierkpiTemplate tawSupplierkpiTemplate);

    /**
     * Gets tawSupplierkpiTemplate's information based on id.
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
    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize);
    public Map getTawSupplierkpiTemplates(final int curPage, final int pageSize, final String whereStr);
    public String getTemplateIdBySpecialType(final String specialType);
    public String getTemplateIdByServiceType(final String serviceType);
    public void saveDictAndTemplate(TawSupplierkpiDict tawSupplierkpiDict, TawSupplierkpiTemplate tawSupplierkpiTemplate);
    /**
     * 删除专业模型，级联删除下属专业、项目分类和指标
     * @param dictId
     */
    public void removeDictAndTemplate(final TawSupplierkpiTemplate template);
    /**
     * 从模型表获取树形节点
     * @param whereStr
     * @return
     */
    public List getNodesFromTemplate(final String whereStr);
}

