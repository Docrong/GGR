package com.boco.eoms.workbench.infopub.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

/**
 * <p>
 * Title:版块下信息发布（操作）dao接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:01:32 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public interface ThreadDao extends Dao {

    /**
     * Retrieves all of the threads
     */
    public List getThreads(Thread thread);

    /**
     * Gets thread's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the thread's id
     * @return thread populated thread object
     */
    public Thread getThread(final String id);

    // add by gongyufeng
    public List getThreadList(final String whereStr);

    /**
     * Saves a thread's information
     *
     * @param thread the object to be saved
     */
    public void saveThread(Thread thread);

    /**
     * Removes a thread from the database by id
     *
     * @param id the thread's id
     */
    public void removeThread(final String id);

    /**
     * 分页取贴子
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getThreads(final Integer curPage, final Integer pageSize);

    /**
     * 分页取贴子
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @param whereStr where条件
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getThreads(final Integer curPage, final Integer pageSize,
                          final String whereStr);

    /**
     * 分页取贴子
     *
     * @param whereStr where条件
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getThreads(final String whereStr);

    /**
     * 分页取未读贴子列表
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @param userId   用户id
     * @param whereStr where条件语句
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getUnreadThreads(final Integer curPage, final Integer pageSize,
                                final String userId, final String whereStr);

    /**
     * 信息分类
     *
     * @param ids      信息id数组
     * @param forumsId 版块id
     */
    public void sort(String ids[], String forumsId);

    /**
     * 取审核列表，不通用（应重构）
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @param userId   用户id
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getThreads4audit(final Integer curPage, final Integer pageSize,
                                final String userId);

    /**
     * 查询信息
     *
     * @param curPage    要获取的页码
     * @param pageSize   每页多少条
     * @param threadForm 查询信息form
     * @param deptStr    要查看的部门范围
     * @return
     */
    public Map listThreads(final Integer curPage, final Integer pageSize,
                           ThreadForm threadForm, String deptStr);

    public Thread getSerialNoToThread(final String serialNo);

    public String getMaxSerialNo(String serialNo);

}
