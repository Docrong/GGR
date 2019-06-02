
package com.boco.eoms.message.mgr;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.message.model.EmailMonitor;

public interface IEmailMonitorManager extends Manager {
    /**
     * Retrieves all of the emailMonitors
     */
    public List getEmailMonitors(EmailMonitor emailMonitor);

    /**
     * Gets emailMonitor's information based on id.
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
    public List getChildList(String parentId);
    /**
     * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
     * @param parentId
     */
    public JSONArray xGetChildNodes(String parentId);
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
	public String sendEmail(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl);
    /**
	 * 发送email，根据传递过来的内部系统组织（用户，角色，部门）id发送email
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param msg
	 *            发送内容
	 * @param buzId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param orgIds
	 *            系统组织（用户，角色，部门）ids,id与typ成对出现，中间以逗号相隔，多条之间以井号出现，如：userId,1#roleId,2
	 *            
	 * @return success,fail（成功与否）
	 */
	public String sendEmail4Org(String serviceId, String subject, String msg, String buizId, String addresser,
			String orgIds, String dispatchTime, String accessoriesUrl);
	public List listNeedSendMsg();
    public void closeEmail(String serviceId, String buizId ,String userId);
    public void closeEmail(String serviceId, String buizId);
    
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
	 *            
	 * @param accessoriesUrls
	 *            邮件的附件路径 格式为文件的绝对路径#文件的真实名字，每个附件间用","连接，例如：http://10.32.1.23:8087/eoms/呵呵.txt#呵呵.txt,http://10.32.1.23:8087/eoms/duty/model/new/21/20080617145328.xls#fgsfd.xls           
	 * @return success,fail（成功与否）
	 */
    public String sendEmailByWeb(String serviceId, String subject, String msg,
			String buizId, String addresser, String orgIds,
			String dispatchTime, String accessoriesUrls);
}

