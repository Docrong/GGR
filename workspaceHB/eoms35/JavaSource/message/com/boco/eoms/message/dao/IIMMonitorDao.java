
package com.boco.eoms.message.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.message.model.EmailMonitor;
import com.boco.eoms.message.model.IMMonitor;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 *
 * </p>
 * 
 * @author 卓璐
 * @version 1.0
 * 
 */
public interface IIMMonitorDao extends Dao {

	/**
	 * 根据用户id获得用户加密后密码
	 */
	public String getUserPwdByUserid(String userid);
	/***
	 * 获得所有的immonitor
	 * @param IMMonitor
	 * @return List
	 */
    public List getIMMonitors(IMMonitor iMMonitor);

   /***
    * 获得指定的IMMonitor
    * @param id
    * @return
    */
    public IMMonitor getIMMonitor(final String id);

   /***
    * 保存
    * @param iMMonitor
    */  
    public void saveIMMonitor(IMMonitor iMMonitor);

   /***
    * 移除
    * @param id
    */
    public void removeIMMonitor(final String id);
    
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getIMMonitors(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */ 
    public Map getIMMonitors(final Integer curPage, final Integer pageSize, final String whereStr);
    /**
     * 根据父节点查询下级子节点
     * @param parentId 子节点中parentId字段即父节点id
     */    
    public ArrayList getChildList(String parentId);
    
    
   
	/***
	 * 得到需要发送的IMMonitor集合
	 * @return
	 */
	public List listNeedSendIM();
	
    public void closeIM(String serviceId, String buizId ,String userId);
    public void closeIM(String serviceId, String buizId);
    
    /***
     * 反向查找所有人
     */
    public List selectUserListByUserList(List userList);
}

