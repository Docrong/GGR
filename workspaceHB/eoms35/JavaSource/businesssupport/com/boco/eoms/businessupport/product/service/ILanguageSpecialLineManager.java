
package com.boco.eoms.businessupport.product.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;

public interface ILanguageSpecialLineManager extends Manager {
    /**
     * Retrieves all of the languagespeciallines
     */
    public List getLanguageSpecialLines();

    /**
     * Retrieves all of the deleted languagespeciallines
     */
    public List getLanguageSpecialLinesDeleted();

    /**
     * Gets languagespecialline's information based on id.
     *
     * @param id the languagespecialline's id
     * @return languagespecialline populated languagespecialline object
     */
    public LanguageSpecialLine getLanguageSpecialLine(final String id);

    /**
     * Saves a languagespecialline's information
     *
     * @param languagespecialline the object to be saved
     */
    public void saveLanguageSpecialLine(LanguageSpecialLine languagespecialline);

    /**
     * Removes a languagespecialline from the database by id
     *
     * @param id the languagespecialline's id
     */
    public void removeLanguageSpecialLine(final String id);

    /**
     * restore a languagespecialline from the database by id
     *
     * @param id the languagespecialline's id
     */
    public void restoreLanguageSpecialLine(final String id);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     */
    public Map getLanguageSpecialLines(final Integer curPage, final Integer pageSize);

    /**
     * 用于分页显示
     *
     * @param curPage  the current page number
     * @param pageSize the size number per page
     * @param whereStr the "where.." conditional statement,must start with "where", can be blank
     */
    public Map getLanguageSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr);


    /**
     * 根据查询条件查询列表
     *
     * @param condition
     * @return
     */
    public List getLanguageSpecialLinesByProCode(String proCode, String moduleName);


    public List getLanguageSpecialLinesByTxtwords(Integer newDeleted, String txtwords, String moduleName);

    //根据订单id和专业类型得到相关服务存量信息
    public List getSpecialLines(String id);

    /**
     * 根据条件分页查询定单
     */
    public Map getQueryLanguageSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                            final String whereStr);

    //根据存量表id获得此对象
    public LanguageSpecialLine getSpecialLineById(String id) throws HibernateException;

    public void saveOrUpdate(LanguageSpecialLine languagespecialline) throws HibernateException;

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public LanguageSpecialLine getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception;

    /**
     * 通过Z端业务设备端口查找电路
     *
     * @param portZBDeviceName Z端设备名
     * @param portZBDevicePort Z端设备端口名
     */
    public LanguageSpecialLine getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception;
}

