package com.boco.eoms.operuser.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;

import com.boco.eoms.operuser.model.Operuser;

/**
 * <p>
 * Title:operuser
 * </p>
 * <p>
 * Description:operuser
 * </p>
 * <p>
 * Tue Mar 31 09:42:13 CST 2009
 * </p>
 *
 * @author xiang
 * @version 35
 */
public interface OperuserDao extends Dao {

    /**
     * 根据主键查询operuser
     *
     * @param id 主键
     * @return 返回某id的operuser
     */
    public Operuser getOperuser(final String id);

    /**
     * 保存operuser
     *
     * @param operuser operuser
     */
    public void saveOperuser(Operuser operuser);

    /**
     * 根据节点id查询operuser
     *
     * @param nodeId 节点id
     * @return 返回某节点id的对象
     */
    public Operuser getOperuserByNodeId(final String nodeId);

    /**
     * 得到部门的所有人员
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptid(String deptid);

    /**
     * 通过用户名称或部门查询人员
     *
     * @param deptid
     * @return
     */
    public List getUserByNameOrdeptid(String name, String deptname);

    /**
     * 根据节点id删除operuser
     *
     * @param nodeId 节点id
     */
    public void removeOperuserByNodeId(final String nodeId);

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelOperusers(final String parentNodeId);

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

    /**
     * name2Id，即字典id转为字典名称 added by fengshaohong
     *
     * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
     */
    public String name2Id(final String dictName, final String parentDictId);
}