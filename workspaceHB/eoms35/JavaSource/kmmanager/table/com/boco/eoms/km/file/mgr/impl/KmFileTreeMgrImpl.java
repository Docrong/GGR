package com.boco.eoms.km.file.mgr.impl;

import java.util.List;

import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.file.dao.KmFileTreeDao;
import com.boco.eoms.km.file.util.KmFileTreeConstants;

/**
 * <p>
 * Title:文件管理树
 * </p>
 * <p>
 * Description:文件管理树
 * </p>
 * <p>
 * Wed Mar 25 17:09:37 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmFileTreeMgrImpl implements KmFileTreeMgr {
 
	private KmFileTreeDao  kmFileTreeDao;
 	
	public KmFileTreeDao getKmFileTreeDao() {
		return this.kmFileTreeDao;
	}
 	
	public void setKmFileTreeDao(KmFileTreeDao kmFileTreeDao) {
		this.kmFileTreeDao = kmFileTreeDao;
	}
	
	public KmFileTree getKmFileTree(final String id) {
    	return kmFileTreeDao.getKmFileTree(id);
    }
    
    public void saveKmFileTree(KmFileTree kmFileTree) {
    	boolean isNew = (null == kmFileTree.getNodeId() || "".equals(kmFileTree.getNodeId()));
    	if (isNew) {
    		// 生成新节点Id
    		String nodeId = getUsableNodeId(kmFileTree.getParentNodeId());
    		kmFileTree.setNodeId(nodeId);
    		kmFileTree.setLeaf(KmFileTreeConstants.NODE_LEAF);
    		// 保存新节点
    		kmFileTreeDao.saveKmFileTree(kmFileTree);
    		
    		// 如果父节点不是根结点则设置父节点为非叶子节点
    		if (!KmFileTreeConstants.TREE_ROOT_ID.equals(kmFileTree.getParentNodeId())) {
    			updateNodeLeaf(kmFileTree.getParentNodeId(), KmFileTreeConstants.NODE_NOTLEAF);
    		}
    	} else {
    		kmFileTreeDao.saveKmFileTree(kmFileTree);
    	}
    }
    
	public KmFileTree getKmFileTreeByNodeId(final String nodeId) {
		return kmFileTreeDao.getKmFileTreeByNodeId(nodeId);
	}
	
	public void removeKmFileTreeByNodeId(final String nodeId) {
		KmFileTree kmFileTree = kmFileTreeDao.getKmFileTreeByNodeId(nodeId);
		// 获得父节点Id
		String parentNodeId = kmFileTree.getParentNodeId();
		kmFileTreeDao.removeKmFileTreeByNodeId(nodeId);
		
		// 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
		if (!KmFileTreeConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			if (!isHasNextLevel(parentNodeId)) {
				updateNodeLeaf(parentNodeId, KmFileTreeConstants.NODE_LEAF);
			}
		}
	}
	
	public List getNextLevelKmFileTrees(String parentNodeId) {
		return kmFileTreeDao.getNextLevelKmFileTrees(parentNodeId);
	}
	
	public String getUsableNodeId(String parentNodeId) {
		return kmFileTreeDao.getUsableNodeId(parentNodeId, parentNodeId.length()
				+ KmFileTreeConstants.NODEID_BETWEEN_LENGTH);
	}
	
	public boolean isHasNextLevel(String parentNodeId) {
		boolean flag = false;
		List list = kmFileTreeDao.getNextLevelKmFileTrees(parentNodeId);
		if (list.iterator().hasNext()) {
			flag = true;
		}
		return flag;
	}
	
	public void updateNodeLeaf(String nodeId, String leaf) {
		KmFileTree kmFileTree = kmFileTreeDao.getKmFileTreeByNodeId(nodeId);
		kmFileTree.setLeaf(leaf);
		kmFileTreeDao.saveKmFileTree(kmFileTree);
	}
	
	public List getChildNodes(String parentNodeId) {
		return kmFileTreeDao.getChildNodes(parentNodeId);
	}
	
}