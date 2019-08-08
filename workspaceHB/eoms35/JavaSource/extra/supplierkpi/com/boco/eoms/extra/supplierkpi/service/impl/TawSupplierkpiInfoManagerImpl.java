
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInfoDao;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInfoManager;

public class TawSupplierkpiInfoManagerImpl extends BaseManager implements ITawSupplierkpiInfoManager {
    private TawSupplierkpiInfoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSupplierkpiInfoDao(TawSupplierkpiInfoDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInfoManager#getTawSupplierkpiInfos(com.boco.eoms.commons.sample.model.TawSupplierkpiInfo)
     */
    public List getTawSupplierkpiInfos(final TawSupplierkpiInfo tawSupplierkpiInfo) {
        return dao.getTawSupplierkpiInfos(tawSupplierkpiInfo);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInfoManager#getTawSupplierkpiInfo(String id)
     */
    public TawSupplierkpiInfo getTawSupplierkpiInfo(final String id) {
        return dao.getTawSupplierkpiInfo(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInfoManager#saveTawSupplierkpiInfo(TawSupplierkpiInfo tawSupplierkpiInfo)
     */
    public void saveTawSupplierkpiInfo(TawSupplierkpiInfo tawSupplierkpiInfo) {
        dao.saveTawSupplierkpiInfo(tawSupplierkpiInfo);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawSupplierkpiInfoManager#removeTawSupplierkpiInfo(String id)
     */
    public void removeTawSupplierkpiInfo(final String id) {
        dao.removeTawSupplierkpiInfo(new String(id));
    }

    /**
     *
     */
    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize) {
        return dao.getTawSupplierkpiInfos(curPage, pageSize, null);
    }

    public Map getTawSupplierkpiInfos(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawSupplierkpiInfos(curPage, pageSize, whereStr);
    }
}
