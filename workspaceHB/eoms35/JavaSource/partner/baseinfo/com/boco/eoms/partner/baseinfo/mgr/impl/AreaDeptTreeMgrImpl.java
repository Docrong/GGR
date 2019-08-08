package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.dao.AreaDeptTreeDao;
import com.boco.eoms.partner.baseinfo.util.AreaDeptTreeConstants;

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
public class AreaDeptTreeMgrImpl implements AreaDeptTreeMgr {

    private AreaDeptTreeDao areaDeptTreeDao;

    public AreaDeptTreeDao getAreaDeptTreeDao() {
        return this.areaDeptTreeDao;
    }

    public void setAreaDeptTreeDao(AreaDeptTreeDao areaDeptTreeDao) {
        this.areaDeptTreeDao = areaDeptTreeDao;
    }

    public AreaDeptTree getAreaDeptTree(final String id) {
        return areaDeptTreeDao.getAreaDeptTree(id);
    }

    public void saveAreaDeptTree(AreaDeptTree areaDeptTree) {
        boolean isNew = (null == areaDeptTree.getId() || "".equals(areaDeptTree.getId()));
        if (isNew) {
            // 生成新节点Id
            String nodeId = getUsableNodeId(areaDeptTree.getParentNodeId());
            areaDeptTree.setNodeId(nodeId);
            areaDeptTree.setLeaf(AreaDeptTreeConstants.NODE_LEAF);
            // 保存新节点
            areaDeptTreeDao.saveAreaDeptTree(areaDeptTree);

            // 如果父节点不是根结点则设置父节点为非叶子节点
            if (!AreaDeptTreeConstants.TREE_ROOT_ID.equals(areaDeptTree.getParentNodeId())) {
                updateNodeLeaf(areaDeptTree.getParentNodeId(), AreaDeptTreeConstants.NODE_NOTLEAF);
            }
        } else {
            areaDeptTreeDao.saveAreaDeptTree(areaDeptTree);
        }
    }

    public AreaDeptTree getAreaDeptTreeByNodeId(final String nodeId) {
        return areaDeptTreeDao.getAreaDeptTreeByNodeId(nodeId);
    }

    public void removeAreaDeptTreeByNodeId(final String nodeId) {
        AreaDeptTree areaDeptTree = areaDeptTreeDao.getAreaDeptTreeByNodeId(nodeId);
        // 获得父节点Id
        String parentNodeId = areaDeptTree.getParentNodeId();
        areaDeptTreeDao.removeAreaDeptTreeByNodeId(nodeId);

        // 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
        if (!AreaDeptTreeConstants.TREE_ROOT_ID.equals(parentNodeId)) {
            if (!isHasNextLevel(parentNodeId)) {
                updateNodeLeaf(parentNodeId, AreaDeptTreeConstants.NODE_LEAF);
            }
        }
    }

    public List getNextLevelAreaDeptTrees(String parentNodeId) {
        return areaDeptTreeDao.getNextLevelAreaDeptTrees(parentNodeId);
    }

    public String getUsableNodeId(String parentNodeId) {
        return areaDeptTreeDao.getUsableNodeId(parentNodeId, parentNodeId.length()
                + AreaDeptTreeConstants.NODEID_BETWEEN_LENGTH);
    }

    public boolean isHasNextLevel(String parentNodeId) {
        boolean flag = false;
        List list = areaDeptTreeDao.getNextLevelAreaDeptTrees(parentNodeId);
        if (list.iterator().hasNext()) {
            flag = true;
        }
        return flag;
    }

    public void updateNodeLeaf(String nodeId, String leaf) {
        AreaDeptTree areaDeptTree = areaDeptTreeDao.getAreaDeptTreeByNodeId(nodeId);
        areaDeptTree.setLeaf(leaf);
        areaDeptTreeDao.saveAreaDeptTree(areaDeptTree);
    }

    public List getChildNodes(String parentNodeId) {
        return areaDeptTreeDao.getChildNodes(parentNodeId);
    }

    //根据父节点parentNodeId和节点类型nodeType得到某个省、某个地市、某个厂商下的节点（人力信息、仪器仪表、车辆管理、油机管理）
    public List getChildLeafNodes(final String parentNodeId, String nodeType) {
        return areaDeptTreeDao.getChildLeafNodes(parentNodeId, nodeType);
    }

    //根据叶子节点（人力信息、仪器仪表、车辆管理、油机管理）对象集，获得nodeId，并把nodeId组装成字符串
    public String getStringNodeIdByLeaf(final String parentNodeId, String nodeType) {
        List list = this.getChildLeafNodes(parentNodeId, nodeType);
        String nodes = "'a'";//当父节点下无任何人力信息时返回
        if (list.size() > 0) {
            nodes = "";
            String[] nodeIds = new String[list.size()];
            Iterator it = list.iterator();
            for (int i = 0; i < list.size(); i++) {
                AreaDeptTree areaDeptTree = (AreaDeptTree) it.next();
                nodeIds[i] = areaDeptTree.getNodeId();
                if ((i + 1) < list.size()) nodes += nodeIds[i] + ",";
                else if ((i + 1) == list.size()) nodes += nodeIds[i];
            }
        }
        return nodes;
    }

    public String id2Name(final String id) {
        return areaDeptTreeDao.id2Name(id);
    }

    public List getAreaDeptTreesByType(final String nodeType) {
        return areaDeptTreeDao.getAreaDeptTreesByType(nodeType);
    }

    //由节点名得到节点ID 用于根据地市名查询地市nodeId，用于批量导入
    public String getNodeIdByNodeName(String name) {
        return areaDeptTreeDao.getNodeIdByNodeName(name);
    }

    //由父节点ID和节点名得到节点Id，用于根据地市nodeId和厂商名查询厂商nodeId，用于批量导入
    public String getNodeIdByParentAndName(String parentNodeId, String name) {
        return areaDeptTreeDao.getNodeIdByParentAndName(parentNodeId, name);
    }

    //由地市名、厂商名得到叶子节点的nodeId（人力信息、车辆管理、仪器仪表、油机管理）
    public String getLeafNodeIdByNames(String area, String factory, String type) {
        return areaDeptTreeDao.getLeafNodeIdByNames(area, factory, type);
    }

    //由节点id，得到叶子节点
    public List getLeavesByNodeId(final String nodeId, String nodeType) {
        return areaDeptTreeDao.getLeavesByNodeId(nodeId, nodeType);
    }

    //由省名得到省下全部地市
    public List getAreaByProvince(String provinceName) {
        return areaDeptTreeDao.getAreaByProvince(provinceName);
    }

    //返回查询条件得到的结果
    public List getInfoByCondition(String sql) {
        return areaDeptTreeDao.getInfoByCondition(sql);
    }

    //由合作伙伴名称得到全部合作伙伴节点
    public List getDeptByDeptName(String provinceName) {
        return areaDeptTreeDao.getDeptByDeptName(provinceName);
    }

    //得到省的列表
    public List getProvinceNodes() {
        return areaDeptTreeDao.getProvinceNodes();
    }

    //得到一列地市名下的树节点集合
    public List getNodesByAreas(String areaNames) {
        return areaDeptTreeDao.getNodesByAreas(areaNames);
    }

    //09.03.12 加方法 不同用户的人可以看到不同的节点,当登录用户是地市用户时执行这个方法
    public List getNextLevelAreaDeptTrees(final String parentNodeId, String areaNames) {
        return areaDeptTreeDao.getNextLevelAreaDeptTrees(parentNodeId, areaNames);
    }
}