
package com.boco.eoms.commons.sample.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.sample.model.AppfuseDemo;

public interface AppfuseDemoDao extends Dao {

    /**
     * Retrieves all of the appfuseDemos
     */
    public List getAppfuseDemos(AppfuseDemo appfuseDemo);

    /**
     * Gets appfuseDemo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the appfuseDemo's id
     * @return appfuseDemo populated appfuseDemo object
     */
    public AppfuseDemo getAppfuseDemo(final String id);

    /**
     * Saves a appfuseDemo's information
     * @param appfuseDemo the object to be saved
     */    
    public void saveAppfuseDemo(AppfuseDemo appfuseDemo);

    /**
     * Removes a appfuseDemo from the database by id
     * @param id the appfuseDemo's id
     */
    public void removeAppfuseDemo(final String id);
  
    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize);
    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize, final String whereStr);
}

