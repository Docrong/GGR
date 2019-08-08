package com.boco.eoms.km.expert.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertEdu;

/**
 * <p>
 * Title:教育背景
 * </p>
 * <p>
 * Description:专家教育背景
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public interface KmExpertEduMgr {

    /**
     * 取教育背景 列表
     *
     * @return 返回教育背景列表
     */
    public List getKmExpertEdus();

    /**
     * 根据主键查询教育背景
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmExpertEdu getKmExpertEdu(final String id);

    /**
     * 保存教育背景
     *
     * @param kmExpertEdu 教育背景
     */
    public void saveKmExpertEdu(KmExpertEdu kmExpertEdu);

    /**
     * 根据主键删除教育背景
     *
     * @param id 主键
     */
    public void removeKmExpertEdu(final String id);

    /**
     * 根据id批量删除教育背景
     *
     * @param id 主键
     */
    public void removeKmExpertEdus(final String[] ids);

    /**
     * 根据条件分页查询教育背景
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回教育背景的分页列表
     */
    public Map getKmExpertEdus(final Integer curPage, final Integer pageSize,
                               final String whereStr);

    public Map getKmExpertEdusByUserId(final Integer curPage, final Integer pageSize,
                                       final String userId);
}