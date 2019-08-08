package com.boco.eoms.km.kmAuditer.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.kmAuditer.model.KmAuditer;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 *
 * @author 戴志刚
 * @version 1.0
 */
public interface KmAuditerMgr {

    /**
     * 取知识管理审核人配置表 列表
     *
     * @return 返回知识管理审核人配置表列表
     */
    public List getKmAuditers();

    /**
     * 根据主键查询知识管理审核人配置表
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmAuditer getKmAuditer(final String id);

    /**
     * 保存知识管理审核人配置表
     *
     * @param kmAuditer 知识管理审核人配置表
     */
    public void saveKmAuditer(KmAuditer kmAuditer);

    /**
     * 根据主键删除知识管理审核人配置表
     *
     * @param id 主键
     */
    public void removeKmAuditer(final String id);

    /**
     * 根据条件分页查询知识管理审核人配置表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识管理审核人配置表的分页列表
     */
    public Map getKmAuditers(final Integer curPage, final Integer pageSize,
                             final String whereStr);

    /**
     * 根据知识条目的 THEME_ID 查询知识条目
     *
     * @param THEME_ID 知识模型所属分类ID
     */
    public KmAuditer getKmAuditerByTheme(String themeId);

    /**
     * 根据文件分类审核人配置表
     *
     * @param nodeId 主键
     * @return 返回KmAuditer对象
     */
    public KmAuditer getKmAuditerByNodeid(final String nodeId);

}