
package com.boco.eoms.commons.demo.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.demo.model.TawDemoMytable;

public interface TawDemoMytableManager extends Manager {
    /**
     * Retrieves all of the tawDemoMytables
     */
    public List getTawDemoMytables(TawDemoMytable tawDemoMytable);

    /**
     * Gets tawDemoMytable's information based on id.
     * @param id the tawDemoMytable's id
     * @return tawDemoMytable populated tawDemoMytable object
     */
    public TawDemoMytable getTawDemoMytable(final String id);

    /**
     * Saves a tawDemoMytable's information
     * @param tawDemoMytable the object to be saved
     */
    public void saveTawDemoMytable(TawDemoMytable tawDemoMytable);

    /**
     * Removes a tawDemoMytable from the database by id
     * @param id the tawDemoMytable's id
     */
    public void removeTawDemoMytable(final String id);
    
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize, final String whereStr);
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize);
}

