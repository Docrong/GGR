
package com.boco.eoms.message.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.SmsMobilesTemplate;
import com.boco.eoms.message.model.SmsUserMgr;
import com.boco.eoms.message.webapp.form.SmsUserLogForm;

public interface ISmsMobilesTemplateDao extends Dao {

    /**
     * Retrieves all of the smsMobilesTemplates
     */
    public List getSmsMobilesTemplates(SmsMobilesTemplate smsMobilesTemplate);

    /**
     * Gets smsMobilesTemplate's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the smsMobilesTemplate's id
     * @return smsMobilesTemplate populated smsMobilesTemplate object
     */
    public SmsMobilesTemplate getSmsMobilesTemplate(final String id);

    /**
     * Saves a smsMobilesTemplate's information
     * @param smsMobilesTemplate the object to be saved
     */    
    public void saveSmsMobilesTemplate(SmsMobilesTemplate smsMobilesTemplate);

    /**
     * Removes a smsMobilesTemplate from the database by id
     * @param id the smsMobilesTemplate's id
     */
    public void removeSmsMobilesTemplate(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getSmsMobilesTemplates(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getSmsMobilesTemplates(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    /**
     * 根据deleted标志查询号码组模板列表
     * @param deleted
     * @return
     */
    public List getMobileTempByDeleted(String deleted);
    
    public List getNodes4Team();
    public Map getUsersListById(final Integer curPage, final Integer pageSize,
			final String id);
    
    public void saveSmsUser(final SmsUserMgr smsUserMgr);
    public List getSmsUserMgr(String id);
    public void removeUser(String id);
    public Map searchUser(final Integer curPage, final Integer pageSize,SmsUserLogForm smsUserLogForm);
    public String getTeamNameById(String teamId);
    public String saveLog(String str,String content,String status);
}

