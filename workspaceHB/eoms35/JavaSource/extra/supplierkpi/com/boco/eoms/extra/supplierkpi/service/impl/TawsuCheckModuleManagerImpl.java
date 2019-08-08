
package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.model.TawsuCheckModule;
import com.boco.eoms.extra.supplierkpi.dao.TawsuCheckModuleDao;
import com.boco.eoms.extra.supplierkpi.service.ITawsuCheckModuleManager;

public class TawsuCheckModuleManagerImpl extends BaseManager implements ITawsuCheckModuleManager {
    private TawsuCheckModuleDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawsuCheckModuleDao(TawsuCheckModuleDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawsuCheckModuleManager#getTawsuCheckModules(com.boco.eoms.commons.sample.model.TawsuCheckModule)
     */
    public List getTawsuCheckModules(final TawsuCheckModule tawsuCheckModule) {
        return dao.getTawsuCheckModules(tawsuCheckModule);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawsuCheckModuleManager#getTawsuCheckModule(String id)
     */
    public TawsuCheckModule getTawsuCheckModule(final String id) {
        return dao.getTawsuCheckModule(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawsuCheckModuleManager#saveTawsuCheckModule(TawsuCheckModule tawsuCheckModule)
     */
    public void saveTawsuCheckModule(TawsuCheckModule tawsuCheckModule) {
        dao.saveTawsuCheckModule(tawsuCheckModule);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.ITawsuCheckModuleManager#removeTawsuCheckModule(String id)
     */
    public void removeTawsuCheckModule(final String id) {
        dao.removeTawsuCheckModule(new String(id));
    }

    /**
     *
     */
    public Map getTawsuCheckModules(final int curPage, final int pageSize) {
        return dao.getTawsuCheckModules(curPage, pageSize, null);
    }

    public Map getTawsuCheckModules(final int curPage, final int pageSize, final String whereStr) {
        return dao.getTawsuCheckModules(curPage, pageSize, whereStr);
    }
}
