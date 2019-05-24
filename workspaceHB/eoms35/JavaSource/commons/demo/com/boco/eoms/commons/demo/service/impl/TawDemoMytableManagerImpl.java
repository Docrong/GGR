
package com.boco.eoms.commons.demo.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.demo.model.TawDemoMytable;
import com.boco.eoms.commons.demo.dao.TawDemoMytableDao;
import com.boco.eoms.commons.demo.service.TawDemoMytableManager;

public class TawDemoMytableManagerImpl extends BaseManager implements TawDemoMytableManager {
    private TawDemoMytableDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawDemoMytableDao(TawDemoMytableDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.demo.service.TawDemoMytableManager#getTawDemoMytables(com.boco.eoms.commons.demo.model.TawDemoMytable)
     */
    public List getTawDemoMytables(final TawDemoMytable tawDemoMytable) {
        return dao.getTawDemoMytables(tawDemoMytable);
    }

    /**
     * @see com.boco.eoms.commons.demo.service.TawDemoMytableManager#getTawDemoMytable(String id)
     */
    public TawDemoMytable getTawDemoMytable(final String id) {
        return dao.getTawDemoMytable(new Integer(id));
    }

    /**
     * @see com.boco.eoms.commons.demo.service.TawDemoMytableManager#saveTawDemoMytable(TawDemoMytable tawDemoMytable)
     */
    public void saveTawDemoMytable(TawDemoMytable tawDemoMytable) {
        dao.saveTawDemoMytable(tawDemoMytable);
    }

    /**
     * @see com.boco.eoms.commons.demo.service.TawDemoMytableManager#removeTawDemoMytable(String id)
     */
    public void removeTawDemoMytable(final String id) {
        dao.removeTawDemoMytable(new Integer(id));
    }
    
    /**
     * 
     */
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawDemoMytables(curPage, pageSize, whereStr);
    }
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize) {
        return dao.getTawDemoMytables(curPage, pageSize);
    }
}
