
package com.boco.eoms.message.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.EmailMonitor;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public interface IEmailMonitorDao extends Dao {

    /**
     * Retrieves all of the emailMonitors
     */
    public List getEmailMonitors(EmailMonitor emailMonitor);

    /**
     * Gets emailMonitor's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the emailMonitor's id
     * @return emailMonitor populated emailMonitor object
     */
    public EmailMonitor getEmailMonitor(final String id);

    /**
     * Saves a emailMonitor's information
     * @param emailMonitor the object to be saved
     */    
    public void saveEmailMonitor(EmailMonitor emailMonitor);

    /**
     * Removes a emailMonitor from the database by id
     * @param id the emailMonitor's id
     */
    public void removeEmailMonitor(final String id);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getEmailMonitors(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getEmailMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    /**
	 * 发送email，根据传递过来的内部系统组织（用户，角色，部门）id发送email
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param subject
	 *            邮件主题
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param addresser
	 * 			  发件人
	 * @param orgIds
	 *            系统组织（用户，角色，部门）ids,id与typ成对出现，中间以逗号相隔，多条之间以井号出现，如：userId,1#roleId,2
	 * @param accessoriesUrl
	 * 			  附件url地址
	 * @return success,fail（成功与否）
	 */
	public String sendEmail(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl);
    /**
	 * 发送email，根据传递过来的内部系统组织（用户，角色，部门）id发送email
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param subject
	 *            邮件主题
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds
	 *            系统组织（用户，角色，部门）ids,id与typ成对出现，中间以逗号相隔，多条之间以井号出现，如：userId,1#roleId,2
	 * @return success,fail（成功与否）
	 */
	public String sendEmail4Org(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl);
	public List listNeedSendEmail();
    public void closeEmail(String serviceId, String buizId ,String userId);
    public void closeEmail(String serviceId, String buizId);
    public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls);
}

