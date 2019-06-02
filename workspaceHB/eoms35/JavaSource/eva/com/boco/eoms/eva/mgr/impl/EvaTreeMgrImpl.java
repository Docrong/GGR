package com.boco.eoms.eva.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.eva.dao.IEvaTreeDao;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;

public class EvaTreeMgrImpl implements IEvaTreeMgr {

	private IEvaTreeDao evaTreeDao;

	public IEvaTreeDao getEvaTreeDao() {
		return evaTreeDao;
	}

	public void setEvaTreeDao(IEvaTreeDao evaTreeDao) {
		this.evaTreeDao = evaTreeDao;
	}

	public EvaTree getTreeNode(String id) {
		return evaTreeDao.getTreeNode(id);
	}

	public String getMaxNodeId(String parentNodeId) {
		return evaTreeDao.getMaxNodeId(parentNodeId, parentNodeId.length()
				+ EvaConstants.NODEID_BETWEEN_LENGTH);
	}

	public EvaTree getTreeNodeByNodeId(String nodeId) {
		return evaTreeDao.getTreeNodeByNodeId(nodeId);
	}

	public boolean isHaveSameLevel(String parentNodeId, String nodeType) {
		boolean flag = false;
		List list = evaTreeDao.listNextLevelNode(parentNodeId, nodeType);
		if (null != list && 0 < list.size()) {
			flag = true;
		}
		return flag;
	}

	public ArrayList listNextLevelNode(String parentNodeId, String nodeType) {
		return evaTreeDao.listNextLevelNode(parentNodeId, nodeType);
	}
	
	
	public boolean isFirstKpiLevel(String nodeId){
		return evaTreeDao.isFirstKpiLevel(nodeId);
	}

	public void removeTreeNodeByNodeId(final String nodeId) {
		// 删除当前节点及下级节点
		evaTreeDao.removeTreeNodeByNodeId(nodeId);
		// 得到父节点
		String parentNodeId = getTreeNodeByNodeId(nodeId).getParentNodeId();
		if (!EvaConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			EvaTree parentNode = getTreeNodeByNodeId(parentNodeId);
			// 如果删除后没有同级节点则父节点为leaf
			if (isHaveSameLevel(parentNode.getNodeId(), "")) {
				updateParentNodeLeaf(parentNode.getNodeId(),
						EvaConstants.NODE_LEAF);
			}
		}
	}

	public void saveTreeNode(EvaTree evaTreeNode) {
		evaTreeDao.saveTreeNode(evaTreeNode);
	}

	public void updateParentNodeLeaf(String nodeId, String leaf) {
		EvaTree node = evaTreeDao.getTreeNodeByNodeId(nodeId);
		node.setLeaf(leaf);
		evaTreeDao.saveTreeNode(node);
	}

	public String id2Name(String nodeId) {
		return evaTreeDao.id2Name(nodeId);
	}

	public void removeTreeNode(EvaTree evaTreeNode) {
		evaTreeDao.removeTreeNode(evaTreeNode);
	}

	public List listChildNodes(String parentNodeId, String nodeType, String leaf) {
		return evaTreeDao.listChildNodes(parentNodeId, nodeType, leaf);
	}

	public EvaTree getNodeByObjId(String parentNodeId, String objectId) {
		return evaTreeDao.getNodeByObjId(parentNodeId, objectId);
	}

	public int getMaxLevelOfChildNode(String parentNodeId) {
		int maxLevel = 0;
		// 最大深度的节点一定是叶子节点
		List sonList = listChildNodes(parentNodeId, "", EvaConstants.NODE_LEAF);
		String maxNodeId = parentNodeId;
		for (Iterator sonIt = sonList.iterator(); sonIt.hasNext();) {
			EvaTree childNode = (EvaTree) sonIt.next();
			if (childNode.getNodeId().length() > maxNodeId.length()) {
				maxNodeId = childNode.getNodeId();
			}
		}
		maxLevel = (maxNodeId.length() - parentNodeId.length())
				/ EvaConstants.NODEID_BETWEEN_LENGTH;
		return maxLevel;
	}
}
