package com.boco.eoms.km.expert.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertBasic;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public interface KmExpertBasicDao extends Dao {

    /**
     * 取基本信息列表
     *
     * @return 返回基本信息列表
     */
    public List getKmExpertBasics();

    /**
     * 根据主键查询基本信息
     *
     * @param id 主键
     * @return 返回某id的基本信息
     */
    public KmExpertBasic getKmExpertBasic(final String id);

    /**
     * 根据用户ID查询基本信息
     *
     * @param userId 用户ID
     * @return 返回某用户的基本信息
     */
    public KmExpertBasic getKmExpertBasicByUserId(final String userId);

    /**
     * 保存基本信息
     *
     * @param kmExpertBasic 基本信息
     */
    public void saveKmExpertBasic(KmExpertBasic kmExpertBasic);

    /**
     * 根据id删除基本信息
     *
     * @param id 主键
     */
    public void removeKmExpertBasic(final String id, final String userId);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

    public Map getKmExpertBasics(final Integer curPage, final Integer pageSize,
                                 final KmExpertBasic kmExpertBasic);

}