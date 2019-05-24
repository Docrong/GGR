
package com.boco.eoms.commons.sample.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.sample.model.AppfuseDemo;
import com.boco.eoms.commons.sample.dao.AppfuseDemoDao;
import com.boco.eoms.commons.sample.service.IAppfuseDemoManager;

public class AppfuseDemoManagerImpl extends BaseManager implements IAppfuseDemoManager {
    private AppfuseDemoDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAppfuseDemoDao(AppfuseDemoDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.sample.service.IAppfuseDemoManager#getAppfuseDemos(com.boco.eoms.commons.sample.model.AppfuseDemo)
     */
    public List getAppfuseDemos(final AppfuseDemo appfuseDemo) {
        return dao.getAppfuseDemos(appfuseDemo);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.IAppfuseDemoManager#getAppfuseDemo(String id)
     */
    public AppfuseDemo getAppfuseDemo(final String id) {
        return dao.getAppfuseDemo(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.sample.service.IAppfuseDemoManager#saveAppfuseDemo(AppfuseDemo appfuseDemo)
     */
    public void saveAppfuseDemo(AppfuseDemo appfuseDemo) {
        dao.saveAppfuseDemo(appfuseDemo);
    }

    /**
     * @see com.boco.eoms.commons.sample.service.IAppfuseDemoManager#removeAppfuseDemo(String id)
     */
    public void removeAppfuseDemo(final String id) {
        dao.removeAppfuseDemo(new String(id));
    }
    /**
     * 
     */
    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize) {
        return dao.getAppfuseDemos(curPage, pageSize,null);
    }
    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getAppfuseDemos(curPage, pageSize, whereStr);
    }
}
