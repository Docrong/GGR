
package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;

public interface ITawSystemOrganizationProxyManager extends Manager {
    /**
     * Retrieves all of the tawSystemOrganizationProxys
     */
    public List getTawSystemOrganizationProxys(TawSystemOrganizationProxy tawSystemOrganizationProxy);

    /**
     * Gets tawSystemOrganizationProxy's information based on id.
     * @param id the tawSystemOrganizationProxy's id
     * @return tawSystemOrganizationProxy populated tawSystemOrganizationProxy object
     */
    public TawSystemOrganizationProxy getTawSystemOrganizationProxy(final String id);

    /**
     * Saves a tawSystemOrganizationProxy's information
     * @param tawSystemOrganizationProxy the object to be saved
     */
    public void saveTawSystemOrganizationProxy(TawSystemOrganizationProxy tawSystemOrganizationProxy);

    /**
     * Removes a tawSystemOrganizationProxy from the database by id
     * @param id the tawSystemOrganizationProxy's id
     */
    public void removeTawSystemOrganizationProxy(final String id);
    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize);
    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize, final String whereStr);
}

