
package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import net.sf.json.JSONArray;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;

public interface IIPSpecialLineManager extends Manager {
    /**
     * Retrieves all of the ipspeciallines
     */
    public List getIPSpecialLines();
    /**
     * Retrieves all of the deleted ipspeciallines
     */
    public List getIPSpecialLinesDeleted();
    /**
     * Gets ipspecialline's information based on id.
     * @param id the ipspecialline's id
     * @return ipspecialline populated ipspecialline object
     */
    public IPSpecialLine getIPSpecialLine(final String id);
    /**
     * Saves a ipspecialline's information
     * @param ipspecialline the object to be saved
     */
    public void saveIPSpecialLine(IPSpecialLine ipspecialline);

    /**
     * Removes a ipspecialline from the database by id
     * @param id the ipspecialline's id
     */
    public void removeIPSpecialLine(final String id);
    
    /**
     * restore a ipspecialline from the database by id
     * @param id the ipspecialline's id
     */
    public void restoreIPSpecialLine(final String id);
    
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     */    
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize);
    /**
     * 用于分页显示
     * @param curPage the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr);
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
     * 根据查询条件查询列表
     * @param condition
     * @return
     */
    public List getIPSpecialLinesByProCode(String proCode,String moduleName);
    
    
    public List getIPSpecialLinesByTxtwords(Integer newDeleted,String txtwords,String moduleName);
	 //根据订单id和专业类型得到相关服务存量信息
	public List getSpecialLines(String id);
	
	/**
	 * 根据条件分页查询定单
	 */	
	public Map getQueryIPSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
			final String whereStr);
	//根据存量表id获得此对象
	public IPSpecialLine getSpecialLineById(String id) throws HibernateException;

	public void saveOrUpdate(IPSpecialLine ipspecialline) throws HibernateException;
	
	/**
	 * 通过A端站点名称和Z端站点名称查找电路
	 * @param siteNameA
	 * @param siteNameZ
	 * @return
	 */
	public IPSpecialLine getSpecialLinesBySiteName(String siteNameA,String siteNameZ) throws Exception;
	/**
	 * 通过Z端业务设备端口查找电路
	 * @param portZBDeviceName Z端设备名
	 * @param portZBDevicePort	Z端设备端口名
	 */
	public IPSpecialLine getSpecialLineByZPort(String portZBDeviceName,String portZBDevicePort) throws Exception;
}

