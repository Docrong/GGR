package com.boco.eoms.km.ask.mgr;

import java.util.List;

import com.boco.eoms.km.ask.model.KmAskSpeciality;

/**
 * <p>
 * Title:问答类型
 * </p>
 * <p>
 * Description:问答类型
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmAskSpecialityMgr {

    /**
     * 根据主键查询问答类型
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmAskSpeciality getKmAskSpeciality(final String id);

    /**
     * 保存问答类型
     *
     * @param kmAskSpeciality 问答类型
     */
    public void saveKmAskSpeciality(KmAskSpeciality kmAskSpeciality);

    /**
     * 根据节点id查询问答类型
     *
     * @param nodeId 节点id
     * @return 返回某节点id的对象
     */
    public KmAskSpeciality getKmAskSpecialityByNodeId(final String nodeId);

    /**
     * 根据节点id删除问答类型
     *
     * @param nodeId 节点id
     */
    public void removeKmAskSpecialityByNodeId(final String nodeId);

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelKmAskSpecialitys(String parentNodeId);

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
	