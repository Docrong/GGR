package com.boco.eoms.km.kmAuditer.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.boco.eoms.km.core.ElementTranslator;
import com.boco.eoms.km.core.bizlets.DataQueryExt;
import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.core.dataservice.sql.SelectSQLBuilder;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.dao.KmAuditerDao;
import com.boco.eoms.km.servlet.context.RequestContext;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 *
 * @author 戴志刚
 * @version 1.0
 */
public class KmAuditerMgrImpl implements KmAuditerMgr {

    private KmAuditerDao kmAuditerDao;

    public KmAuditerDao getKmAuditerDao() {
        return this.kmAuditerDao;
    }

    public void setKmAuditerDao(KmAuditerDao kmAuditerDao) {
        this.kmAuditerDao = kmAuditerDao;
    }

    public List getKmAuditers() {
        return kmAuditerDao.getKmAuditers();
    }

    public KmAuditer getKmAuditer(final String id) {
        return kmAuditerDao.getKmAuditer(id);
    }

    public void saveKmAuditer(KmAuditer kmAuditer) {
        kmAuditerDao.saveKmAuditer(kmAuditer);
    }

    public void removeKmAuditer(final String id) {
        kmAuditerDao.removeKmAuditer(id);
    }

    public Map getKmAuditers(final Integer curPage, final Integer pageSize,
                             final String whereStr) {
        return kmAuditerDao.getKmAuditers(curPage, pageSize, whereStr);
    }

    /**
     * 根据知识条目的 THEME_ID 查询知识条目
     *
     * @param THEME_ID 知识模型所属分类ID
     */
    public KmAuditer getKmAuditerByTheme(String themeId) {

        return kmAuditerDao.getKmAuditerByTheme(themeId);

    }

    public KmAuditer getKmAuditerByNodeid(final String nodeId) {
        return kmAuditerDao.getKmAuditerByNodeid(nodeId);
    }
}