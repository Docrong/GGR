
package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;

public interface IGprsSpecialLineManager extends Manager {
    /**
     * Retrieves all of the gprsspeciallines
     */
    public List getGprsSpecialLines();

    /**
     * Retrieves all of the deleted gprsspeciallines
     */
    public List getGprsSpecialLinesDeleted();

    /**
     * Gets gprsspecialline's information based on id.
     *
     * @param id the gprsspecialline's id
     * @return gprsspecialline populated gprsspecialline object
     */
    public GprsSpecialLine getGprsSpecialLine(final String id);

    /**
     * Saves a gprsspecialline's information
     *
     * @param gprsspecialline the object to be saved
     */
    public void saveGprsSpecialLine(GprsSpecialLine gprsspecialline);

    /**
     * Removes a gprsspecialline from the database by id
     *
     * @param id the gprsspecialline's id
     */
    public void removeGprsSpecialLine(final String id);

    /**
     * restore a gprsspecialline from the database by id
     *
     * @param id the gprsspecialline's id
     */
    public void restoreGprsSpecialLine(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getGprsSpecialLines(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getGprsSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr);


    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getGprsSpecialLinesByProCode(String proCode, String moduleName);


    public List getGprsSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName);

    //根据订单id和专业类型得到相关服务存量信息
    public List getSpecialLines(String id);

    /**
     * 根据条件分页查询定单
     */
    public Map getQueryGprsSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                        final String whereStr);

    //根据存量表id获得此对象
    public GprsSpecialLine getSpecialLineById(String id) throws HibernateException;

    public void saveOrUpdate(GprsSpecialLine gprsspecialline) throws HibernateException;

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public GprsSpecialLine getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception;

    /**
     * 通过Z端业务设备端口查找电路
     *
     * @param portZBDeviceName Z端设备名
     * @param portZBDevicePort Z端设备端口名
     */
    public GprsSpecialLine getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception;
}

