package com.boco.eoms.cutapply.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.cutapply.model.CutApplyAdmin;

/**
 * <p>
 * Title:干线割接管理
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 *
 * @author wangsixuan
 * @version 3.5
 */
public interface CutApplyAdminMgr {

    /**
     * 取干线割接管理 列表
     *
     * @return 返回干线割接管理列表
     */
    public List getCutApplyAdmins();

    /**
     * 根据主键查询干线割接管理
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public CutApplyAdmin getCutApplyAdmin(final String id);

    /**
     * 保存干线割接管理
     *
     * @param cutApply 干线割接管理
     */
    public void saveCutApplyAdmin(CutApplyAdmin cutApplyAdmin);

    /**
     * 根据主键删除干线割接管理
     *
     * @param id 主键
     */
    public void removeCutApplyAdmin(final String id);

    /**
     * 根据条件分页查询干线割接管理
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回干线割接管理的分页列表
     */
    public Map getCutApplyAdmins(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    /**
     * 根据条件查询
     *
     * @param hql 查询SQL
     * @return 结果LIST
     */
    public List getCutApplyAdminsByCondition(final String hql);

}