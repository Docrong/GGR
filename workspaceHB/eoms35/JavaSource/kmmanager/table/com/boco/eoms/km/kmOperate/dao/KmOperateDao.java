package com.boco.eoms.km.kmOperate.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.kmOperate.model.KmOperate;

/**
 * <p>
 * Title:知识管理权限配置
 * </p>
 * <p>
 * Description:知识管理权限配置
 * </p>
 * <p>
 * Fri May 22 14:03:33 CST 2009
 * </p>
 *
 * @author 戴志刚
 * @version 1.0
 */
public interface KmOperateDao extends Dao {

    /**
     * 根据节点和节点类型取知识管理权限配置列表
     *
     * @return 返回知识管理权限配置列表
     */
    public List getKmOperates(final String nodeId, final String nodeType);

    /**
     * 根据条件取知识管理权限配置列表
     *
     * @return 返回知识管理权限配置列表
     */
    public List getKmOperates(final String whereStr);

    /**
     * 取知识管理权限配置列表
     *
     * @return 返回知识管理权限配置列表
     */
    public List getKmOperates();

    /**
     * 根据主键查询知识管理权限配置
     *
     * @param id 主键
     * @return 返回某id的知识管理权限配置
     */
    public KmOperate getKmOperate(final String id);

    /**
     * 根据节点查询知识管理权限配置
     *
     * @param nodeId 节点,nodeType 节点类型
     * @return 返回某id的知识管理权限配置
     */
    public KmOperate getKmOperate(final String nodeId, final String nodeType, final String orgId, final String orgType);

    /**
     * 保存知识管理权限配置
     *
     * @param kmOperate 知识管理权限配置
     */
    public void saveKmOperate(KmOperate kmOperate);

    /**
     * 根据id删除知识管理权限配置
     *
     * @param id 主键
     */
    public void removeKmOperate(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmOperates(final Integer curPage, final Integer pageSize,
                             final String whereStr);

    /**
     * 根据操作者和操作者类型取知识管理权限配置列表
     *
     * @return 返回知识管理权限配置列表
     */
    public List getKmOperatesByOrgIdAndOrgType(final String nodeType, final String orgId, final String orgType);
}