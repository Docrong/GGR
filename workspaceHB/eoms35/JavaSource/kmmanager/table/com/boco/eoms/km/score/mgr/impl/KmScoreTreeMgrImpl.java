package com.boco.eoms.km.score.mgr.impl;

import java.util.List;

import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.mgr.KmScoreTreeMgr;
import com.boco.eoms.km.score.dao.KmScoreTreeDao;
import com.boco.eoms.km.score.util.KmScoreTreeConstants;

/**
 * <p>
 * Title:积分设定树
 * </p>
 * <p>
 * Description:积分设定树
 * </p>
 * <p>
 * Thu Aug 13 14:07:31 CST 2009
 * </p>
 * 
 * @author me
 * @version 1.0
 * 
 */
public class KmScoreTreeMgrImpl implements KmScoreTreeMgr {
 
	private KmScoreTreeDao  kmScoreTreeDao;
 	
	public KmScoreTreeDao getKmScoreTreeDao() {
		return this.kmScoreTreeDao;
	}
 	
	public void setKmScoreTreeDao(KmScoreTreeDao kmScoreTreeDao) {
		this.kmScoreTreeDao = kmScoreTreeDao;
	}
	
	public KmScoreTree getKmScoreTree(final String id) {
    	return kmScoreTreeDao.getKmScoreTree(id);
    }
    
    public void saveKmScoreTree(KmScoreTree kmScoreTree) {
    	boolean isNew = (null == kmScoreTree.getId() || "".equals(kmScoreTree.getId()));
    	if (isNew) {
    		// 生成新节点Id
    		String nodeId = getUsableNodeId(kmScoreTree.getParentNodeId());
    		kmScoreTree.setNodeId(nodeId);
    		kmScoreTree.setLeaf(KmScoreTreeConstants.NODE_LEAF);
    		// 保存新节点
    		kmScoreTreeDao.saveKmScoreTree(kmScoreTree);
    		
    		// 如果父节点不是根结点则设置父节点为非叶子节点
    		if (!KmScoreTreeConstants.TREE_ROOT_ID.equals(kmScoreTree.getParentNodeId())) {
    			updateNodeLeaf(kmScoreTree.getParentNodeId(), KmScoreTreeConstants.NODE_NOTLEAF);
    		}
    	} else {
    		kmScoreTreeDao.saveKmScoreTree(kmScoreTree);
    	}
    }
    
	public KmScoreTree getKmScoreTreeByNodeId(final String nodeId) {
		return kmScoreTreeDao.getKmScoreTreeByNodeId(nodeId);
	}
	
	public void removeKmScoreTreeByNodeId(final String nodeId) {
		KmScoreTree kmScoreTree = kmScoreTreeDao.getKmScoreTreeByNodeId(nodeId);
		// 获得父节点Id
		String parentNodeId = kmScoreTree.getParentNodeId();
		kmScoreTreeDao.removeKmScoreTreeByNodeId(nodeId);
		
		// 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
		if (!KmScoreTreeConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			if (!isHasNextLevel(parentNodeId)) {
				updateNodeLeaf(parentNodeId, KmScoreTreeConstants.NODE_LEAF);
			}
		}
	}
	
	public List getNextLevelKmScoreTrees(String parentNodeId) {
		return kmScoreTreeDao.getNextLevelKmScoreTrees(parentNodeId);
	}
	
	public String getUsableNodeId(String parentNodeId) {
		return kmScoreTreeDao.getUsableNodeId(parentNodeId, parentNodeId.length()
				+ KmScoreTreeConstants.NODEID_BETWEEN_LENGTH);
	}
	
	public boolean isHasNextLevel(String parentNodeId) {
		boolean flag = false;
		List list = kmScoreTreeDao.getNextLevelKmScoreTrees(parentNodeId);
		if (list.iterator().hasNext()) {
			flag = true;
		}
		return flag;
	}
	
	public void updateNodeLeaf(String nodeId, String leaf) {
		KmScoreTree kmScoreTree = kmScoreTreeDao.getKmScoreTreeByNodeId(nodeId);
		kmScoreTree.setLeaf(leaf);
		kmScoreTreeDao.saveKmScoreTree(kmScoreTree);
	}
	
	public List getChildNodes(String parentNodeId) {
		return kmScoreTreeDao.getChildNodes(parentNodeId);
	}
	
}