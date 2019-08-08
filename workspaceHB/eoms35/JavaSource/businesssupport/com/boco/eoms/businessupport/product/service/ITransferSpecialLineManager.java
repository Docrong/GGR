
package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;

public interface ITransferSpecialLineManager extends Manager {
    /**
     * Retrieves all of the ipspeciallines
     */
    public List getTransferSpecialLines();

    /**
     * Retrieves all of the deleted ipspeciallines
     */
    public List getTransferSpecialLinesDeleted();

    /**
     * Gets ipspecialline's information based on id.
     *
     * @param id the ipspecialline's id
     * @return ipspecialline populated ipspecialline object
     */
    public TransferSpecialLine getTransferSpecialLine(final String id);

    /**
     * Saves a ipspecialline's information
     *
     * @param ipspecialline the object to be saved
     */
    public void saveTransferSpecialLine(TransferSpecialLine transferspecialline);

    /**
     * Removes a ipspecialline from the database by id
     *
     * @param id the ipspecialline's id
     */
    public void removeTransferSpecialLine(final String id);

    /**
     * restore a ipspecialline from the database by id
     *
     * @param id the ipspecialline's id
     */
    public void restoreTransferSpecialLine(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getTransferSpecialLines(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getTransferSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr);

    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getTransferSpecialLinesByProCode(String proCode, String moduleName);


    public List getTransferSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName);

    //根据订单id和专业类型得到相关服务存量信息
    public List getSpecialLines(String id);

    /**
     * 根据条件分页查询定单
     */
    public Map getQueryTransferSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                            final String whereStr);

    //根据存量表id获得此对象
    public TransferSpecialLine getSpecialLineById(String id) throws HibernateException;

    public void saveOrUpdate(TransferSpecialLine transferspecialline) throws HibernateException;

    //根据电路名称得到对象
    public TransferSpecialLine getTransferByCircuitName(String circuitName) throws HibernateException;

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public TransferSpecialLine getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception;

    /**
     * 通过Z端业务设备端口查找电路
     *
     * @param portZBDeviceName Z端设备名
     * @param portZBDevicePort Z端设备端口名
     */
    public TransferSpecialLine getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception;
}

