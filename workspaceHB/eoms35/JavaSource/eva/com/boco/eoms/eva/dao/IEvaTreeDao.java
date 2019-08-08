package com.boco.eoms.eva.dao;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaTree;

/**
 * <p>
 * Title:考核树Dao接口
 * </p>
 * <p>
 * Description:考核树Dao接口
 * </p>
 * <p>
 * Date:2008-12-4 下午08:29:50
 * </p>
 *
 * @author 李秋野
 * @version 3.5.1
 */
public interface IEvaTreeDao extends Dao {

    /**
     * 根据主键查询树节点
     *
     * @param id 主键
     * @return
     */
    public EvaTree getTreeNode(String id);

    /**
     * 根据节点id查询树节点
     *
     * @param nodeId 节点id
     * @return
     */
    public EvaTree getTreeNodeByNodeId(String nodeId);

    /**
     * 保存树节点
     *
     * @param evaTreeNode 树节点
     */
    public void saveTreeNode(EvaTree evaTreeNode);

    /**
     * 根据节点id删除节点
     *
     * @param evaTreeNode 树节点
     */
    public void removeTreeNode(EvaTree evaTreeNode);

    /**
     * 查询下一级特定类型的节点，如果不分类型查找则nodeType传入一个空的字符串
     *
     * @param parentNodeId 父节点id
     * @param nodeType     节点类型（EvaConstants.NODETYPE_KPI,NODETYPE_TEMPLATETYPE,NODETYPE_TEMPLATE）
     * @return
     */
    public ArrayList listNextLevelNode(String parentNodeId, String nodeType);

    /**
     * 判断所给nodeId对应记录是否为第一层KPI
     *
     * @param nodeId 节点id
     * @return
     */
    public boolean isFirstKpiLevel(String nodeId);

    /**
     * 查询最大的节点id
     *
     * @param parentNodeId 父节点id
     * @param length       长度
     * @return
     */
    public String getMaxNodeId(String parentNodeId, int length);

    /**
     * 根据节点id返回节点名称
     *
     * @param nodeId 节点id
     * @return
     */
    public String id2Name(String nodeId);

    /**
     * 删除某Id对应的节点以及所有下级节点
     *
     * @param nodeId
     */
    public void removeTreeNodeByNodeId(final String nodeId);

    /**
     * 查询子节点
     *
     * @param parentNodeId 父节点id
     * @param nodeType     节点类型（不区分节点类型请传入空字符串）
     * @param leaf         叶子节点标志(不区分是否叶子节点请传入空字符串参数）
     */
    public List listChildNodes(String parentNodeId, String nodeType, String leaf);

    /**
     * 根据父节点Id和对象Id查询节点
     *
     * @param parentNodeId 父节点id
     * @param objectId     对象Id
     */
    public EvaTree getNodeByObjId(String parentNodeId, String objectId);

}
