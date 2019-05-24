package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;

import com.boco.eoms.km.ask.model.KmAskSpeciality;
import com.boco.eoms.km.ask.mgr.KmAskSpecialityMgr;
import com.boco.eoms.km.ask.dao.KmAskSpecialityDao;
import com.boco.eoms.km.ask.util.KmAskSpecialityConstants;

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
 * 
 */
public class KmAskSpecialityMgrImpl implements KmAskSpecialityMgr {
 
	private KmAskSpecialityDao  kmAskSpecialityDao;
 	
	public KmAskSpecialityDao getKmAskSpecialityDao() {
		return this.kmAskSpecialityDao;
	}
 	
	public void setKmAskSpecialityDao(KmAskSpecialityDao kmAskSpecialityDao) {
		this.kmAskSpecialityDao = kmAskSpecialityDao;
	}
	
	public KmAskSpeciality getKmAskSpeciality(final String id) {
    	return kmAskSpecialityDao.getKmAskSpeciality(id);
    }
    
    public void saveKmAskSpeciality(KmAskSpeciality kmAskSpeciality) {
    	boolean isNew = (null == kmAskSpeciality.getId() || "".equals(kmAskSpeciality.getId()));
    	if (isNew) {
    		// 生成新节点Id
    		String nodeId = getUsableNodeId(kmAskSpeciality.getParentNodeId());
    		kmAskSpeciality.setNodeId(nodeId);
    		kmAskSpeciality.setLeaf(KmAskSpecialityConstants.NODE_LEAF);
    		// 保存新节点
    		kmAskSpecialityDao.saveKmAskSpeciality(kmAskSpeciality);
    		
    		// 如果父节点不是根结点则设置父节点为非叶子节点
    		if (!KmAskSpecialityConstants.TREE_ROOT_ID.equals(kmAskSpeciality.getParentNodeId())) {
    			updateNodeLeaf(kmAskSpeciality.getParentNodeId(), KmAskSpecialityConstants.NODE_NOTLEAF);
    		}
    	} else {
    		kmAskSpecialityDao.saveKmAskSpeciality(kmAskSpeciality);
    	}
    }
    
	public KmAskSpeciality getKmAskSpecialityByNodeId(final String nodeId) {
		return kmAskSpecialityDao.getKmAskSpecialityByNodeId(nodeId);
	}
	
	public void removeKmAskSpecialityByNodeId(final String nodeId) {
		KmAskSpeciality kmAskSpeciality = kmAskSpecialityDao.getKmAskSpecialityByNodeId(nodeId);
		// 获得父节点Id
		String parentNodeId = kmAskSpeciality.getParentNodeId();
		kmAskSpecialityDao.removeKmAskSpecialityByNodeId(nodeId);
		
		// 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
		if (!KmAskSpecialityConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			if (!isHasNextLevel(parentNodeId)) {
				updateNodeLeaf(parentNodeId, KmAskSpecialityConstants.NODE_LEAF);
			}
		}
	}
	
	public List getNextLevelKmAskSpecialitys(String parentNodeId) {
		return kmAskSpecialityDao.getNextLevelKmAskSpecialitys(parentNodeId);
	}
	
	public String getUsableNodeId(String parentNodeId) {
		return kmAskSpecialityDao.getUsableNodeId(parentNodeId, parentNodeId.length()
				+ KmAskSpecialityConstants.NODEID_BETWEEN_LENGTH);
	}
	
	public boolean isHasNextLevel(String parentNodeId) {
		boolean flag = false;
		List list = kmAskSpecialityDao.getNextLevelKmAskSpecialitys(parentNodeId);
		if (list.iterator().hasNext()) {
			flag = true;
		}
		return flag;
	}
	
	public void updateNodeLeaf(String nodeId, String leaf) {
		KmAskSpeciality kmAskSpeciality = kmAskSpecialityDao.getKmAskSpecialityByNodeId(nodeId);
		kmAskSpeciality.setLeaf(leaf);
		kmAskSpecialityDao.saveKmAskSpeciality(kmAskSpeciality);
	}
	
	public List getChildNodes(String parentNodeId) {
		return kmAskSpecialityDao.getChildNodes(parentNodeId);
	}
	
}