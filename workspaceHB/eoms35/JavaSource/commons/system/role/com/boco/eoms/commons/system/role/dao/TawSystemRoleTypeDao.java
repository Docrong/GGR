package com.boco.eoms.commons.system.role.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleType;

public interface TawSystemRoleTypeDao extends Dao {

    /**
     * Retrieves all of the tawSystemRoleTypes
     */
    public List getTawSystemRoleTypes(TawSystemRoleType tawSystemRoleType);

    /**
     * Gets tawSystemRoleType's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param roletype_id the tawSystemRoleType's roletype_id
     * @return tawSystemRoleType populated tawSystemRoleType object
     */
    public TawSystemRoleType getTawSystemRoleType(final Long roletype_id);

    /**
     * Saves a tawSystemRoleType's information
     * @param tawSystemRoleType the object to be saved
     */    
    public void saveTawSystemRoleType(TawSystemRoleType tawSystemRoleType);

    /**
     * Removes a tawSystemRoleType from the database by roletype_id
     * @param roletype_id the tawSystemRoleType's roletype_id
     */
    public void removeTawSystemRoleType(final Long roletype_id);

    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize);
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize, final String whereStr);
}

