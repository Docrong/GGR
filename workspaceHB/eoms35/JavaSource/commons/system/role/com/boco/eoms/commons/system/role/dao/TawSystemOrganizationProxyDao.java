
package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;

public interface TawSystemOrganizationProxyDao extends Dao {

    /**
     * Retrieves all of the tawSystemOrganizationProxys
     */
    public List getTawSystemOrganizationProxys(TawSystemOrganizationProxy tawSystemOrganizationProxy);

    /**
     * Gets tawSystemOrganizationProxy's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
    
	public List getMain();
}

