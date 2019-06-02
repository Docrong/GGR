
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.SmsContentTemplate;

public interface ISmsContentTemplateManager extends Manager {
    /**
     * Retrieves all of the smsContentTemplates
     */
    public List getSmsContentTemplates(SmsContentTemplate smsContentTemplate);

    /**
     * Gets smsContentTemplate's information based on id.
     * @param id the smsContentTemplate's id
     * @return smsContentTemplate populated smsContentTemplate object
     */
    public SmsContentTemplate getSmsContentTemplate(final String id);

    /**
     * Saves a smsContentTemplate's information
     * @param smsContentTemplate the object to be saved
     */
    public void saveSmsContentTemplate(SmsContentTemplate smsContentTemplate);

    /**
     * Removes a smsContentTemplate from the database by id
     * @param id the smsContentTemplate's id
     */
    public void removeSmsContentTemplate(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getSmsContentTemplates(final Integer curPage, final Integer pageSize, final String whereStr);
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

