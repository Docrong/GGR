
package com.boco.eoms.commons.demo.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.demo.model.TawDemoMytable;

public interface TawDemoMytableDao extends Dao {

    /**
     * Retrieves all of the tawDemoMytables
     */
    public List getTawDemoMytables(TawDemoMytable tawDemoMytable);

    /**
     * Gets tawDemoMytable's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the tawDemoMytable's id
     * @return tawDemoMytable populated tawDemoMytable object
     */
    public TawDemoMytable getTawDemoMytable(final Integer id);

    /**
     * Saves a tawDemoMytable's information
     * @param tawDemoMytable the object to be saved
     */    
    public void saveTawDemoMytable(TawDemoMytable tawDemoMytable);

    /**
     * Removes a tawDemoMytable from the database by id
     * @param id the tawDemoMytable's id
     */
    public void removeTawDemoMytable(final Integer id);
    
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize, final String whereStr);
    public Map getTawDemoMytables(final Integer curPage, final Integer pageSize);
}

