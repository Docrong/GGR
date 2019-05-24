package com.boco.eoms.km.train.mgr.impl;

import java.util.List;

import com.boco.eoms.km.train.model.TrainSpecialty;
import com.boco.eoms.km.train.mgr.TrainSpecialtyMgr;
import com.boco.eoms.km.train.dao.TrainSpecialtyDao;
import com.boco.eoms.km.train.util.TrainSpecialtyConstants;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainSpecialtyMgrImpl implements TrainSpecialtyMgr {
 
	private TrainSpecialtyDao  trainSpecialtyDao;
 	
	public TrainSpecialtyDao getTrainSpecialtyDao() {
		return this.trainSpecialtyDao;
	}
 	
	public void setTrainSpecialtyDao(TrainSpecialtyDao trainSpecialtyDao) {
		this.trainSpecialtyDao = trainSpecialtyDao;
	}
	
	public TrainSpecialty getTrainSpecialty(final String id) {
    	return trainSpecialtyDao.getTrainSpecialty(id);
    }
    
    public void saveTrainSpecialty(TrainSpecialty trainSpecialty) {
    	boolean isNew = (null == trainSpecialty.getId() || "".equals(trainSpecialty.getId()));
    	if (isNew) {
    		// 生成新节点Id
    		String nodeId = getUsableNodeId(trainSpecialty.getParentNodeId());
    		trainSpecialty.setNodeId(nodeId);
    		trainSpecialty.setLeaf(TrainSpecialtyConstants.NODE_LEAF);
    		// 保存新节点
    		trainSpecialtyDao.saveTrainSpecialty(trainSpecialty);
    		
    		// 如果父节点不是根结点则设置父节点为非叶子节点
    		if (!TrainSpecialtyConstants.TREE_ROOT_ID.equals(trainSpecialty.getParentNodeId())) {
    			updateNodeLeaf(trainSpecialty.getParentNodeId(), TrainSpecialtyConstants.NODE_NOTLEAF);
    		}
    	} else {
    		trainSpecialtyDao.saveTrainSpecialty(trainSpecialty);
    	}
    }
    
	public TrainSpecialty getTrainSpecialtyByNodeId(final String nodeId) {
		return trainSpecialtyDao.getTrainSpecialtyByNodeId(nodeId);
	}
	
	public void removeTrainSpecialtyByNodeId(final String nodeId) {
		TrainSpecialty trainSpecialty = trainSpecialtyDao.getTrainSpecialtyByNodeId(nodeId);
		// 获得父节点Id
		String parentNodeId = trainSpecialty.getParentNodeId();
		trainSpecialtyDao.removeTrainSpecialtyByNodeId(nodeId);
		
		// 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
		if (!TrainSpecialtyConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			if (!isHasNextLevel(parentNodeId)) {
				updateNodeLeaf(parentNodeId, TrainSpecialtyConstants.NODE_LEAF);
			}
		}
	}
	
	public List getNextLevelTrainSpecialtys(String parentNodeId) {
		return trainSpecialtyDao.getNextLevelTrainSpecialtys(parentNodeId);
	}
	
	public String getUsableNodeId(String parentNodeId) {
		return trainSpecialtyDao.getUsableNodeId(parentNodeId, parentNodeId.length()
				+ TrainSpecialtyConstants.NODEID_BETWEEN_LENGTH);
	}
	
	public boolean isHasNextLevel(String parentNodeId) {
		boolean flag = false;
		List list = trainSpecialtyDao.getNextLevelTrainSpecialtys(parentNodeId);
		if (list.iterator().hasNext()) {
			flag = true;
		}
		return flag;
	}
	
	public void updateNodeLeaf(String nodeId, String leaf) {
		TrainSpecialty trainSpecialty = trainSpecialtyDao.getTrainSpecialtyByNodeId(nodeId);
		trainSpecialty.setLeaf(leaf);
		trainSpecialtyDao.saveTrainSpecialty(trainSpecialty);
	}
	
	public List getChildNodes(String parentNodeId) {
		return trainSpecialtyDao.getChildNodes(parentNodeId);
	}
	
}