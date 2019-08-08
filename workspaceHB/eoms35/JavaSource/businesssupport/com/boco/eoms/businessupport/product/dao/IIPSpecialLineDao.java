
package com.boco.eoms.businessupport.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;

public interface IIPSpecialLineDao extends Dao {

    /**
     * Retrieves all of the ipspeciallines
     */
    public List getIPSpecialLines();

    /**
     * Retrieves all of the deleted ipspeciallines
     */
    public List getIPSpecialLinesDeleted();


    /**
     * Gets ipspecialline's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the ipspecialline's id
     * @return ipspecialline populated ipspecialline object
     */
    public IPSpecialLine getIPSpecialLine(final String id);

    /**
     * Saves a ipspecialline's information
     *
     * @param ipspecialline the object to be saved
     */
    public void saveIPSpecialLine(IPSpecialLine ipspecialline);

    /**
     * Removes a ipspecialline from the database by id
     *
     * @param id the ipspecialline's id
     */
    public void removeIPSpecialLine(final String id);

    /**
     * restore a ipspecialline from the database by id
     *
     * @param id the ipspecialline's id
     */
    public void restoreIPSpecialLine(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getIPSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据父节点查询下级子节点
     *
     * @param parentId 子节点中parentId字段即父节点id
     */
    public ArrayList getChildList(String parentId);

    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getIPSpecialLinesByHql(String hql);

    /**
     * 根据订单id得到相关服务存量信息
     *
     * @param condition
     * @return
     */
    public List getSpecialLines(String id);

    /**
     * 根据条件分页查询定单
     */
    public Map getQueryIPSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                      final String whereStr);

// 	/**
//     * 根据订单id和定单业务类型得到相关服务存量信息
//     * @param condition
//     * @return
//     */
//    public List getSpecialLines(String id, String specialtyType);

    public void saveOrUpdate(IPSpecialLine ipspecialline) throws HibernateException;

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public List getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception;

    /**
     * 通过Z端业务设备端口查找电路
     */
    public List getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception;
}

