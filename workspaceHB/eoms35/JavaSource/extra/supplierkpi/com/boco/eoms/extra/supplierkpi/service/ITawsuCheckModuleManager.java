
package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawsuCheckModule;

public interface ITawsuCheckModuleManager extends Manager {
    /**
     * Retrieves all of the tawsuCheckModules
     */
    public List getTawsuCheckModules(TawsuCheckModule tawsuCheckModule);

    /**
     * Gets tawsuCheckModule's information based on id.
     *
     * @param id the tawsuCheckModule's id
     * @return tawsuCheckModule populated tawsuCheckModule object
     */
    public TawsuCheckModule getTawsuCheckModule(final String id);

    /**
     * Saves a tawsuCheckModule's information
     *
     * @param tawsuCheckModule the object to be saved
     */
    public void saveTawsuCheckModule(TawsuCheckModule tawsuCheckModule);

    /**
     * Removes a tawsuCheckModule from the database by id
     *
     * @param id the tawsuCheckModule's id
     */
    public void removeTawsuCheckModule(final String id);

    public Map getTawsuCheckModules(final int curPage, final int pageSize);

    public Map getTawsuCheckModules(final int curPage, final int pageSize, final String whereStr);
}

