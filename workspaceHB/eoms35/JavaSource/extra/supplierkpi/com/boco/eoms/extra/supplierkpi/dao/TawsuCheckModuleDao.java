
package com.boco.eoms.extra.supplierkpi.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.extra.supplierkpi.model.TawsuCheckModule;

public interface TawsuCheckModuleDao extends Dao {

    /**
     * Retrieves all of the tawsuCheckModules
     */
    public List getTawsuCheckModules(TawsuCheckModule tawsuCheckModule);

    /**
     * Gets tawsuCheckModule's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawsuCheckModule's id
     * @return tawsuCheckModule populated tawsuCheckModule object
     */
    public TawsuCheckModule getTawsuCheckModule(final String id);

    /**
     * Saves a tawsuCheckModule's information
     * @param tawsuCheckModule the object to be saved
     */    
    public void saveTawsuCheckModule(TawsuCheckModule tawsuCheckModule);

    /**
     * Removes a tawsuCheckModule from the database by id
     * @param id the tawsuCheckModule's id
     */
    public void removeTawsuCheckModule(final String id);
    /**
     * curPage
     * pageSize
     */
    public Map getTawsuCheckModules(final int curPage, final int pageSize);
    public Map getTawsuCheckModules(final int curPage, final int pageSize, final String whereStr);
}

