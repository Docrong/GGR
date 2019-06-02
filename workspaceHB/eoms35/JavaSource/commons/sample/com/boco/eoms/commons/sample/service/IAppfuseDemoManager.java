
package com.boco.eoms.commons.sample.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.sample.model.AppfuseDemo;
import com.boco.eoms.commons.sample.dao.AppfuseDemoDao;

public interface IAppfuseDemoManager extends Manager {
    /**
     * Retrieves all of the appfuseDemos
     */
    public List getAppfuseDemos(AppfuseDemo appfuseDemo);

    /**
     * Gets appfuseDemo's information based on id.
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

