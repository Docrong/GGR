/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:29:14
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public interface IMainDAO extends Dao {
    /**
     * 通过主表的主键得到主表的PO
     *
     * @param id
     * @param obj TODO
     * @return
     * @throws HibernateException
     */
    public abstract BaseMain loadSinglePO(String id, Object obj) throws HibernateException;

    /**
     * 通过工单的pre，得到现在数据库中满足pre的个数 例如:传入1-040801-110,如果库中满足这个前缀工单数是100，那么就返回100
     *
     * @param id
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public abstract int getXYZ(String id, Object mainObject) throws HibernateException;

    public abstract BaseMain loadSinglePOByProcessId(String processId, Object obj)
            throws HibernateException;

    /**
     * @param id
     * @param mainObject TODO
     * @throws HibernateException
     */
    public abstract void deleteMain(String id, Object mainObject) throws HibernateException;

    /**
     * 取归档列表
     *
     * @param curPage    当前页
     * @param pageSize   一页显示条数
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public abstract List getHolds(final Map condition, final Integer curPage, final Integer pageSize, final Object mainObject)
            throws HibernateException;

    /**
     * 取归档列表数量
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public abstract Integer getHoldsCount(final Object mainObject) throws HibernateException;

    /**
     * 取某人启动流程列表
     *
     * @param userId     用户ID
     * @param curPage    当前页
     * @param pageSize   一页显示条数
     * @param mainObject TODO
     * @param condition  TODO
     * @return
     * @throws HibernateException
     */
    public abstract HashMap getStarterList(String userId, final Integer curPage,
                                           final Integer pageSize, final Object mainObject, final HashMap condition) throws HibernateException;

    /**
     * 取某人启动流程数量
     *
     * @param userId     用户id
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public abstract Integer getStarterCount(String userId, Object mainObject)
            throws HibernateException;


    /**
     * 根据查询条件查询工单, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @param aTotal   查询结构总数
     * @return List, 类型 BaseMain
     * @throws HibernateException
     */
    public abstract List getQuerySheetByCondition(String hsql,
                                                  final Integer curPage,
                                                  final Integer pageSize,
                                                  int[] aTotal, String queryType)
            throws HibernateException;

    /**
     * 查询总数, 方法本身可以去除hsql中(distinct,order by)关键字
     *
     * @param hsql
     * @return
     * @throws HibernateException
     */
    public Integer count(final String hsql) throws HibernateException;

    public BaseMain getMain(String id, String className) throws Exception;

    /**
     * 根据用户ID查找出他的所有模板
     *
     * @param mainObject TODO
     * @param userId,    curPage, pageSize, aTotal
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-07-22
     */
    public List getTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, Object mainObject) throws HibernateException;

    /**
     * find All attachment by Main and Link
     *
     * @param userId, curPage, pageSize, aTotal
     * @return
     * @throws Exception
     * @author wangjianhua
     * @date 2008-08-02
     */
    public List getAllAttachmentsBySheet(String where) throws HibernateException;

    /**
     * 根据查询条件查询任务信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public abstract HashMap getMainListBySql(String hsql,
                                             final Integer curPage, final Integer pageSize)
            throws HibernateException;

    /**
     * 根据查询条件查询任务信息, 不进行分页处理
     *
     * @param hsql 查询语句
     * @throws HibernateException
     */
    public abstract List getMainListBySql(String hsql) throws HibernateException;


    public void saveOrUpdateMain(Object main) throws HibernateException;

    /**
     * 取撤销列表数量
     * zhangying
     *
     * @param mainObject TODO
     * @return
     * @throws HibernateException
     */
    public abstract Integer getCancelCount(final Object mainObject) throws HibernateException;

    /**
     * 取撤销列表
     * zhangying
     *
     * @param curPage    当前页
     * @param pageSize   一页显示条数
     * @param mainObject TODO
     * @param condition  TODO
     * @return
     * @throws HibernateException
     */
    public abstract List getCancelList(final Integer curPage, final Integer pageSize, final Object mainObject, final HashMap condition)
            throws HibernateException;

    /**
     * 通过工单编号获取main对象
     *
     * @param sheetId
     * @param object  TODO
     * @return
     */
    public BaseMain getMainBySheetId(String sheetId, Object object);

    /**
     * 删除mian对象
     *
     * @param baseMain main对象
     * @return
     */
    public void removeMain(Object baseMain);

    /**
     * 清除当前session
     */
    public void clearObjectOfCurrentSession();

    /**
     * 当有两个相同标识不同实体时执行
     */
    public void mergeObject(Object obj);

    /**
     * 草稿显示
     * zhangying
     *
     * @param curPage  当前页
     * @param pageSize 一页显示条数
     * @return
     * @throws HibernateException 整合用
     */
    public abstract List getDraftListByUserIds(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, Object obj)
            throws HibernateException;
}
