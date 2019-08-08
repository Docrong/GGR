package com.boco.eoms.partner.baseinfo.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;

import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;

/**
 * <p>
 * Title:地域部门树
 * </p>
 * <p>
 * Description:地域部门树
 * </p>
 * <p>
 * Fri Feb 06 11:46:50 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public interface AreaDeptTreeDao extends Dao {

    /**
     * 根据主键查询地域部门树
     *
     * @param id 主键
     * @return 返回某id的地域部门树
     */
    public AreaDeptTree getAreaDeptTree(final String id);

    /**
     * 保存地域部门树
     *
     * @param areaDeptTree 地域部门树
     */
    public void saveAreaDeptTree(AreaDeptTree areaDeptTree);

    /**
     * 根据节点id查询地域部门树
     *
     * @param nodeId 节点id
     * @return 返回某节点id的对象
     */
    public AreaDeptTree getAreaDeptTreeByNodeId(final String nodeId);

    /**
     * 根据节点id删除地域部门树
     *
     * @param nodeId 节点id
     */
    public void removeAreaDeptTreeByNodeId(final String nodeId);

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelAreaDeptTrees(final String parentNodeId);

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

    //根据父节点parentNodeId和节点类型nodeType得到某个省、某个地市、某个厂商下的节点（人力信息、仪器仪表、车辆管理、油机管理）
    public List getChildLeafNodes(final String parentNodeId, String nodeType);

    public String id2Name(final String id);

    public List getAreaDeptTreesByType(final String nodeType);

    //由节点名得到节点ID 用于根据地市名查询地市nodeId，用于批量导入
    public String getNodeIdByNodeName(String name);

    //由父节点ID和节点名得到节点Id，用于根据地市nodeId和厂商名查询厂商nodeId，用于批量导入
    public String getNodeIdByParentAndName(String parentNodeId, String name);

    //由地市名、厂商名得到叶子节点的nodeId（人力信息、车辆管理、仪器仪表、油机管理）
    public String getLeafNodeIdByNames(String area, String factory, String type);

    //由节点id，得到叶子节点
    public List getLeavesByNodeId(final String nodeId, String nodeType);

    //由省名得到省下全部地市
    public List getAreaByProvince(String provinceName);

    //返回查询条件得到的结果 wangsixuan
    public List getInfoByCondition(String sql);

    //由合作伙伴名称得到全部合作伙伴节点
    public List getDeptByDeptName(String provinceName);

    //得到省的列表
    public List getProvinceNodes();

    //得到一列地市名下的树节点集合
    public List getNodesByAreas(String areaNames);

    //09.03.12 加方法 不同用户的人可以看到不同的节点,当登录用户是地市用户时执行这个方法
    public List getNextLevelAreaDeptTrees(final String parentNodeId, String areaNames);
}