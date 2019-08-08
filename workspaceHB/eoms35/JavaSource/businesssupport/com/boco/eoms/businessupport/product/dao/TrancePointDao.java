package com.boco.eoms.businessupport.product.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.product.model.TrancePoint;

/**
 * <p>
 * Title:业务接入点
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 *
 * @author lizhigang
 * @version 3.5
 */
public interface TrancePointDao extends Dao {

    /**
     * 取业务接入点列表
     *
     * @return 返回业务接入点列表
     */
    public List getBusinessupports();

    /**
     * 根据主键查询业务接入点
     *
     * @param id 主键
     * @return 返回某id的业务接入点
     */
    public TrancePoint getBusinessupport(final String id);

    /**
     * 保存业务接入点
     *
     * @param businessupport 业务接入点
     */
    public void saveBusinessupport(TrancePoint businessupport);

    /**
     * 根据id删除业务接入点
     *
     * @param id 主键
     */
    public void removeBusinessupport(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getBusinessupports(final Integer curPage, final Integer pageSize,
                                  final String whereStr);

    public List getBusinessupportBySheetId(final String orderSheetId);

    public List getTrancePointBySheetAndId(final String orderSheetId, final String portDetailAdd);
}