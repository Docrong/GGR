package com.boco.eoms.operuser.mgr.impl;

import java.util.List;

import com.boco.eoms.operuser.model.Operuser;
import com.boco.eoms.operuser.mgr.OperuserMgr;
import com.boco.eoms.operuser.dao.OperuserDao;
import com.boco.eoms.operuser.util.OperuserConstants;

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
public class OperuserMgrImpl implements OperuserMgr {

    private OperuserDao operuserDao;

    public OperuserDao getOperuserDao() {
        return this.operuserDao;
    }

    public void setOperuserDao(OperuserDao operuserDao) {
        this.operuserDao = operuserDao;
    }

    public Operuser getOperuser(final String id) {
        return operuserDao.getOperuser(id);
    }

    /* public void saveOperuser(Operuser operuser) {
         boolean isNew = (null == operuser.getId() || "".equals(operuser.getId()));
         if (isNew) {
             // 生成新节点Id
             //String nodeId = getUsableNodeId(operuser.getParentNodeId());
             //operuser.setNodeId(nodeId);
             //operuser.setLeaf(OperuserConstants.NODE_LEAF);
             // 保存新节点
             operuserDao.saveOperuser(operuser);

             // 如果父节点不是根结点则设置父节点为非叶子节点
             if (!OperuserConstants.TREE_ROOT_ID.equals(operuser.getParentNodeId())) {
                 updateNodeLeaf(operuser.getParentNodeId(), OperuserConstants.NODE_NOTLEAF);
             }
         } else {
             operuserDao.saveOperuser(operuser);
         }
     }*/
    public void saveOperuser(Operuser operuser) {
        operuserDao.saveOperuser(operuser);
    }

    public Operuser getOperuserByNodeId(final String nodeId) {
        return operuserDao.getOperuserByNodeId(nodeId);
    }

    /**
     * 得到部门的所有人员
     *
     * @param deptid
     * @return
     */
    public List getUserBydeptid(String deptid) {
        return operuserDao.getUserBydeptid(deptid);
    }

    public List getUserByNameOrdeptid(String name, String deptname) {
        return operuserDao.getUserByNameOrdeptid(name, deptname);
    }
	
	/*public void removeOperuserByNodeId(final String nodeId) {
		Operuser operuser = operuserDao.getOperuserByNodeId(nodeId);
		// 获得父节点Id
		String parentNodeId = operuser.getParentNodeId();
		operuserDao.removeOperuserByNodeId(nodeId);
		
		// 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
		if (!OperuserConstants.TREE_ROOT_ID.equals(parentNodeId)) {
			if (!isHasNextLevel(parentNodeId)) {
				updateNodeLeaf(parentNodeId, OperuserConstants.NODE_LEAF);
			}
		}
	}*/

    public List getNextLevelOperusers(String parentNodeId) {
        return operuserDao.getNextLevelOperusers(parentNodeId);
    }

    public String getUsableNodeId(String parentNodeId) {
        return operuserDao.getUsableNodeId(parentNodeId, parentNodeId.length()
                + OperuserConstants.NODEID_BETWEEN_LENGTH);
    }

    public boolean isHasNextLevel(String parentNodeId) {
        boolean flag = false;
        List list = operuserDao.getNextLevelOperusers(parentNodeId);
        if (list.iterator().hasNext()) {
            flag = true;
        }
        return flag;
    }
	
	/*public void updateNodeLeaf(String nodeId, String leaf) {
		Operuser operuser = operuserDao.getOperuserByNodeId(nodeId);
		operuser.setLeaf(leaf);
		operuserDao.saveOperuser(operuser);
	}*/

    public List getChildNodes(String parentNodeId) {
        return operuserDao.getChildNodes(parentNodeId);
    }

    public void removeOperuserById(String id) {
        operuserDao.removeObject(Operuser.class, id);
    }

    public String name2Id(final String dictName, final String parentDictId) {
        return operuserDao.name2Id(dictName, parentDictId);
    }

}