package com.boco.eoms.km.exam.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamPublic;

/**
 * <p>
 * Title:考试发布
 * </p>
 * <p>
 * Description:考试发布
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmExamPublicMgr {

    /**
     * 取考试发布 列表
     *
     * @return 返回考试发布列表
     */
    public List getKmExamPublics();

    /**
     * 根据主键查询考试发布
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmExamPublic getKmExamPublic(final String id);

    /**
     * 保存考试发布
     *
     * @param kmExamPublic 考试发布
     */
    public void saveKmExamPublic(KmExamPublic kmExamPublic);

    /**
     * 根据主键删除考试发布
     *
     * @param id 主键
     */
    public void removeKmExamPublic(final String id);

    /**
     * 根据条件分页查询考试发布
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回考试发布的分页列表
     */
    public Map getKmExamPublics(final Integer curPage, final Integer pageSize,
                                final String whereStr);

}