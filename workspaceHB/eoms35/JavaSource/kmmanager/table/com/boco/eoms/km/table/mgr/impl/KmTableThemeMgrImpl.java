package com.boco.eoms.km.table.mgr.impl;

import java.util.List;

import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.dao.KmTableThemeDao;
import com.boco.eoms.km.table.util.KmTableThemeConstants;

/**
 * <p>
 * Title:模型分类表
 * </p>
 * <p>
 * Description:模型分类
 * </p>
 * <p>
 * Thu Mar 26 10:16:39 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmTableThemeMgrImpl implements KmTableThemeMgr {

    private KmTableThemeDao kmTableThemeDao;

    public KmTableThemeDao getKmTableThemeDao() {
        return this.kmTableThemeDao;
    }

    public void setKmTableThemeDao(KmTableThemeDao kmTableThemeDao) {
        this.kmTableThemeDao = kmTableThemeDao;
    }

    public KmTableTheme getKmTableTheme(final String id) {
        return kmTableThemeDao.getKmTableTheme(id);
    }

    public void saveKmTableTheme(KmTableTheme kmTableTheme) {
        boolean isNew = (null == kmTableTheme.getId() || "".equals(kmTableTheme.getId()));
        if (isNew) {
            // 生成新节点Id
            String nodeId = getUsableNodeId(kmTableTheme.getParentNodeId());
            kmTableTheme.setNodeId(nodeId);
            kmTableTheme.setLeaf(KmTableThemeConstants.NODE_LEAF);
            // 保存新节点
            kmTableThemeDao.saveKmTableTheme(kmTableTheme);

            // 如果父节点不是根结点则设置父节点为非叶子节点
            if (!KmTableThemeConstants.TREE_ROOT_ID.equals(kmTableTheme.getParentNodeId())) {
                updateNodeLeaf(kmTableTheme.getParentNodeId(), KmTableThemeConstants.NODE_NOTLEAF);
            }
        } else {
            kmTableThemeDao.saveKmTableTheme(kmTableTheme);
        }
    }

    public KmTableTheme getKmTableThemeByNodeId(final String nodeId) {
        return kmTableThemeDao.getKmTableThemeByNodeId(nodeId);
    }

    public void removeKmTableThemeByNodeId(final String nodeId) {
        // 1查询：当前节点详细信息
        KmTableTheme kmTableTheme = kmTableThemeDao.getKmTableThemeByNodeId(nodeId);

        // 获得父节点Id
        String parentNodeId = kmTableTheme.getParentNodeId();

        // 2：删除：将改节点和所有子节点的删除标记都值为1
        kmTableThemeDao.removeKmTableThemeByNodeId(nodeId);

        // 3修改：删除节点后若父节点下已经没有其他子节点则将父节点设置为叶子节点
        if (!KmTableThemeConstants.TREE_ROOT_ID.equals(parentNodeId)) {
            if (!isHasNextLevel(parentNodeId)) {
                updateNodeLeaf(parentNodeId, KmTableThemeConstants.NODE_LEAF);
            }
        }
    }

    public List getNextLevelKmTableThemes(String parentNodeId) {
        return kmTableThemeDao.getNextLevelKmTableThemes(parentNodeId);
    }

    public String getUsableNodeId(String parentNodeId) {
        return kmTableThemeDao.getUsableNodeId(parentNodeId, parentNodeId.length()
                + KmTableThemeConstants.NODEID_BETWEEN_LENGTH);
    }

    public boolean isHasNextLevel(String parentNodeId) {
        boolean flag = false;
        List list = kmTableThemeDao.getNextLevelKmTableThemes(parentNodeId);
        if (list.iterator().hasNext()) {
            flag = true;
        }
        return flag;
    }

    public void updateNodeLeaf(String nodeId, String leaf) {
        KmTableTheme kmTableTheme = kmTableThemeDao.getKmTableThemeByNodeId(nodeId);
        kmTableTheme.setLeaf(leaf);
        kmTableThemeDao.saveKmTableTheme(kmTableTheme);
    }

    public List getChildNodes(String parentNodeId) {
        return kmTableThemeDao.getChildNodes(parentNodeId);
    }

    public List getNextLevelShowThemes(String parentNodeId) {
        return kmTableThemeDao.getNextLevelShowThemes(parentNodeId);
    }

    /**
     * 知识列表:查询第一层已经创建表的知识库分类
     * 用户查询已经和模型绑定的主题分类
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     * @author zhangxb
     */
    public List getFirstCreateThemes() {
        return kmTableThemeDao.getFirstCreateThemes();
    }

    /**
     * 查询第一级未使用的模型分类
     *
     * @param parentNodeId
     * @return
     */
    public List getFirstLevelUnUsedByParentNodeId(String parentNodeId) {
        return kmTableThemeDao.getFirstLevelByParentNodeIdAndIsUsed(parentNodeId, "0");
    }

    /**
     * 查询第一级已使用的模型分类
     *
     * @param parentNodeId
     * @return
     */
    public List getFirstLevelIsUsedByParentNodeId(String parentNodeId) {
        return kmTableThemeDao.getFirstLevelByParentNodeIdAndIsUsed(parentNodeId, "1");
    }

}