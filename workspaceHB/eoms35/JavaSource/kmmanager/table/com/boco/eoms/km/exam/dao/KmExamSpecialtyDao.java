package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;

import com.boco.eoms.km.exam.model.KmExamSpecialty;

/**
 * <p>
 * Title:专业表
 * </p>
 * <p>
 * Description:专业表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public interface KmExamSpecialtyDao extends Dao {

    /**
     * 根据主键查询专业表
     *
     * @param id 主键
     * @return 返回某id的专业表
     */
    public KmExamSpecialty getKmExamSpecialty(final String id);

    /**
     * 保存专业表
     *
     * @param kmExamSpecialty 专业表
     */
    public void saveKmExamSpecialty(KmExamSpecialty kmExamSpecialty);

    /**
     * 根据节点id查询专业表
     *
     * @param nodeId 节点id
     * @return 返回某节点id的对象
     */
    public KmExamSpecialty getKmExamSpecialtyByNodeId(final String nodeId);

    /**
     * 根据节点id删除专业表
     *
     * @param nodeId 节点id
     */
    public void removeKmExamSpecialtyByNodeId(final String nodeId);

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelKmExamSpecialtys(final String parentNodeId);

    /**
     * 生成一个可用的节点id
     *
     * @param parentNodeId 父节点id
     * @param length       节点长度
     * @return
     */
    public String getUsableNodeId(final String parentNodeId, final int length);

    /**
     * 查询所有子节点（按nodeId排序）
     *
     * @param parentNodeId 父节点id
     */
    public List getChildNodes(final String parentNodeId);
}