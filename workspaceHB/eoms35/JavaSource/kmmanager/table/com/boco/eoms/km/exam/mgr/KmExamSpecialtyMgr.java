package com.boco.eoms.km.exam.mgr;

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
public interface KmExamSpecialtyMgr {

    /**
     * 根据主键查询专业表
     *
     * @param id 主键
     * @return 返回某id的对象
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
    public List getNextLevelKmExamSpecialtys(String parentNodeId);

    /**
     * 生成一个可用的节点id
     *
     * @param parentNodeId 父节点id
     * @return
     */
    public String getUsableNodeId(String parentNodeId);

    /**
     * 判断是否有下级节点
     *
     * @param parentNodeId 父节点id
     * @return 有下级节点返回true，无下级节点返回false
     */
    public boolean isHasNextLevel(String parentNodeId);

    /**
     * 更新某节点为叶子节点
     *
     * @param nodeId 节点id
     * @param leaf   叶子节点标志
     */
    public void updateNodeLeaf(String nodeId, String leaf);

    /**
     * 查询所有子节点（按nodeId排序）
     *
     * @param parentNodeId 父节点id
     */
    public List getChildNodes(String parentNodeId);

}
	