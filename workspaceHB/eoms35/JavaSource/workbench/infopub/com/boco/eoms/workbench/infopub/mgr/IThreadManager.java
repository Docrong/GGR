package com.boco.eoms.workbench.infopub.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

/**
 * <p>
 * Title:信息（贴子）管理mgr
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 5:12:45 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public interface IThreadManager extends Manager {
    /**
     * Retrieves all of the threads
     */
    public List getThreads(final Thread thread);

    // add by gongyufeng
    public List getThreadList(final String whereStr);

    /**
     * Gets thread's information based on id.
     *
     * @param id the thread's id
     * @return thread populated thread object
     */
    public Thread getThread(final String id);

    /**
     * Saves a thread's information
     *
     * @param thread the object to be saved
     */
    public void saveThread(Thread thread);

    /**
     * 信息发布（无需审核）
     *
     * @param thread                 信息对象
     * @param threadPermimissionOrgs 信息权限记录列表
     */
    public void saveThread(Thread thread, List threadPermimissionOrgs);

    /*
     * /** 信息发布（需要审核）
     *
     * @param thread 信息对象 @param threadPermimissionOrgs 信息权限 @param
     * threadAuditHistory 信息发布审核对象
     */
    /*
     * public void saveThread(Thread thread, List threadPermimissionOrgs,
     * ThreadAuditHistory threadAuditHistory);
     */

    /**
     * Removes a thread from the database by id
     *
     * @param id the thread's id
     */
    public void removeThread(final String id);

    public Map getThreads(final Integer curPage, final Integer pageSize);

    public Map getThreads(final Integer curPage, final Integer pageSize,
                          final String whereStr);

    public Map getThreads(final String whereStr);

    /**
     * 分页取未读贴子列表
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @param userId   用户id
     * @param whereStr where条件
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
     * 删除ids[]数组中的所有信息（做删除标记）
     *
     * @param ids 信息ids数组
     */
    public void removeThread(String ids[]);

    /**
     * 访问次数新增并记入访问历史记录
     *
     * @param thread        信息
     * @param threadHistory 信息历史
     */
    public void saveThreadHistory(Thread thread, ThreadHistory threadHistory);

    /**
     * 通过审核状态和用户id分页取信息列表
     *
     * @param curPage  要获取的页码
     * @param pageSize 每页多少条
     * @param userId   用户id
     * @param status   审核状态
     * @return map中两key：result:分页后的列表;total：共多少条
     */
    public Map getThreads4Draft(final Integer curPage, final Integer pageSize,
                                final String userId);

    /**
     * 保存审核信息并提交审核人
     *
     * @param threadAuditHistory 审核信息
     */
    public void saveThreadAuditHistoryAudit(ThreadAuditHistory threadAuditHistory);

    /**
     * 保存审核信息不提交审核人，使信息处于草稿状态
     *
     * @param threadAuditHistory 审核信息
     */
    public void saveThreadAuditHistoryDraft(ThreadAuditHistory threadAuditHistory);

    /**
     * 审核信息
     *
     * @param threadAuditHistoryForm 信息历史form
     */
    public void auditThread(ThreadAuditHistoryForm threadAuditHistoryForm);

    /**
     * 在之前审核未通过的情况下提交审核
     *
     * @param threadAuditHistory 审核记录
     */
    public void submitAudit4nopass(ThreadAuditHistory threadAuditHistory);

    /**
     * 在之前需要提交审核且未提交审核的情况下提交审核
     *
     * @param threadAuditHistory 审核记录
     */
    public void submitAudit4noaudit(ThreadAuditHistory threadAuditHistory);

    /**
     * 查询信息
     *
     * @param curPage    要获取的页码
     * @param pageSize   每页多少条
     * @param threadForm 查询信息form
     * @param deptStr    要查看的部门范围
     * @return
     */
    public Map searchThreads(final Integer curPage, final Integer pageSize,
                             ThreadForm threadForm, String deptStr);


    public Thread getSerialNoToThread(String serialNo);

    public String getMaxSerialNo(String serialNo);

}
