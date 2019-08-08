package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;

import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.exam.mgr.KmExamSpecialtyMgr;
import com.boco.eoms.km.exam.dao.KmExamSpecialtyDao;
import com.boco.eoms.km.exam.util.KmExamSpecialtyConstants;

/**
 * <p>
 * Title:专业表
 * </p>
 * <p>
 * Description:专业表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamSpecialtyMgrImpl implements KmExamSpecialtyMgr {

    private KmExamSpecialtyDao kmExamSpecialtyDao;

    public KmExamSpecialtyDao getKmExamSpecialtyDao() {
        return this.kmExamSpecialtyDao;
    }

    public void setKmExamSpecialtyDao(KmExamSpecialtyDao kmExamSpecialtyDao) {
        this.kmExamSpecialtyDao = kmExamSpecialtyDao;
    }

    public KmExamSpecialty getKmExamSpecialty(final String id) {
        return kmExamSpecialtyDao.getKmExamSpecialty(id);
    }

    public void saveKmExamSpecialty(KmExamSpecialty kmExamSpecialty) {
        boolean isNew = (null == kmExamSpecialty.getSpecialtyID() || "".equals(kmExamSpecialty.getSpecialtyID()));
        if (isNew) {
            // 生成新节点Id
            String nodeId = getUsableNodeId(kmExamSpecialty.getParentNodeId());
            kmExamSpecialty.setNodeId(nodeId);
            kmExamSpecialty.setLeaf(KmExamSpecialtyConstants.NODE_LEAF);
            // 保存新节点
            kmExamSpecialtyDao.saveKmExamSpecialty(kmExamSpecialty);

            // 如果父节点不是根结点则设置父节点为非叶子节点
            if (!KmExamSpecialtyConstants.TREE_ROOT_ID.equals(kmExamSpecialty.getParentNodeId())) {
                updateNodeLeaf(kmExamSpecialty.getParentNodeId(), KmExamSpecialtyConstants.NODE_NOTLEAF);
            }
        } else {
            kmExamSpecialtyDao.saveKmExamSpecialty(kmExamSpecialty);
        }
    }

    public KmExamSpecialty getKmExamSpecialtyByNodeId(final String nodeId) {
        return kmExamSpecialtyDao.getKmExamSpecialtyByNodeId(nodeId);
    }

    public void removeKmExamSpecialtyByNodeId(final String nodeId) {
        KmExamSpecialty kmExamSpecialty = kmExamSpecialtyDao.getKmExamSpecialtyByNodeId(nodeId);
        // 获得父节点Id
        String parentNodeId = kmExamSpecialty.getParentNodeId();
        kmExamSpecialtyDao.removeKmExamSpecialtyByNodeId(nodeId);

        // 删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
        if (!KmExamSpecialtyConstants.TREE_ROOT_ID.equals(parentNodeId)) {
            if (!isHasNextLevel(parentNodeId)) {
                updateNodeLeaf(parentNodeId, KmExamSpecialtyConstants.NODE_LEAF);
            }
        }
    }

    public List getNextLevelKmExamSpecialtys(String parentNodeId) {
        return kmExamSpecialtyDao.getNextLevelKmExamSpecialtys(parentNodeId);
    }

    public String getUsableNodeId(String parentNodeId) {
        return kmExamSpecialtyDao.getUsableNodeId(parentNodeId, parentNodeId.length()
                + KmExamSpecialtyConstants.NODEID_BETWEEN_LENGTH);
    }

    public boolean isHasNextLevel(String parentNodeId) {
        boolean flag = false;
        List list = kmExamSpecialtyDao.getNextLevelKmExamSpecialtys(parentNodeId);
        if (list.iterator().hasNext()) {
            flag = true;
        }
        return flag;
    }

    public void updateNodeLeaf(String nodeId, String leaf) {
        KmExamSpecialty kmExamSpecialty = kmExamSpecialtyDao.getKmExamSpecialtyByNodeId(nodeId);
        kmExamSpecialty.setLeaf(leaf);
        kmExamSpecialtyDao.saveKmExamSpecialty(kmExamSpecialty);
    }

    public List getChildNodes(String parentNodeId) {
        return kmExamSpecialtyDao.getChildNodes(parentNodeId);
    }

}