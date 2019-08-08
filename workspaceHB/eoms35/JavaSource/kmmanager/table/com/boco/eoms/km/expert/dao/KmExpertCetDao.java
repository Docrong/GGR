package com.boco.eoms.km.expert.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.expert.model.KmExpertCet;

/**
 * <p>
 * Title:证书管理
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public interface KmExpertCetDao extends Dao {

    /**
     * 取证书管理列表
     *
     * @return 返回证书管理列表
     */
    public List getKmExpertCets();

    /**
     * 根据主键查询证书管理
     *
     * @param id 主键
     * @return 返回某id的证书管理
     */
    public KmExpertCet getKmExpertCet(final String id);

    /**
     * 保存证书管理
     *
     * @param kmExpertCet 证书管理
     */
    public void saveKmExpertCet(KmExpertCet kmExpertCet);

    /**
     * 根据id删除证书管理
     *
     * @param id 主键
     */
    public void removeKmExpertCet(final String id);

    /**
     * 根据id批量删除证书
     *
     * @param id 主键
     *           add by liju @ 2009-06-20
     */
    public void removeKmExpertCets(final String[] ids);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmExpertCets(final Integer curPage, final Integer pageSize,
                               final String whereStr);

    public Map getKmExpertCetsByUserId(final Integer curPage, final Integer pageSize,
                                       final String userId);
}