
package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;
import com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao;
import com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager;

public class TawSystemOrganizationProxyManagerImpl extends BaseManager implements ITawSystemOrganizationProxyManager {
    private TawSystemOrganizationProxyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemOrganizationProxyDao(TawSystemOrganizationProxyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager#getTawSystemOrganizationProxys(com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy)
     */
    public List getTawSystemOrganizationProxys(final TawSystemOrganizationProxy tawSystemOrganizationProxy) {
        return dao.getTawSystemOrganizationProxys(tawSystemOrganizationProxy);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager#getTawSystemOrganizationProxy(String id)
     */
    public TawSystemOrganizationProxy getTawSystemOrganizationProxy(final String id) {
        return dao.getTawSystemOrganizationProxy(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager#saveTawSystemOrganizationProxy(TawSystemOrganizationProxy tawSystemOrganizationProxy)
     */
    public void saveTawSystemOrganizationProxy(TawSystemOrganizationProxy tawSystemOrganizationProxy) {
        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemOrganizationProxyManager#removeTawSystemOrganizationProxy(String id)
     */
    public void removeTawSystemOrganizationProxy(final String id) {
        dao.removeTawSystemOrganizationProxy(new String(id));
    }
    /**
     * 
     */
    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemOrganizationProxys(curPage, pageSize,null);
    }
    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemOrganizationProxys(curPage, pageSize, whereStr);
    }
}
