package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.model.TawSystemRoleType;

public interface ITawSystemRoleTypeManager extends Manager {
    /**
     * Retrieves all of the tawSystemRoleTypes
     */
    public List getTawSystemRoleTypes(TawSystemRoleType tawSystemRoleType);

    /**
     * Gets tawSystemRoleType's information based on roletype_id.
     * @param roletype_id the tawSystemRoleType's roletype_id
     * @return tawSystemRoleType populated tawSystemRoleType object
     */
    public TawSystemRoleType getTawSystemRoleType(final String roletype_id);

    /**
     * Saves a tawSystemRoleType's information
     * @param tawSystemRoleType the object to be saved
     */
    public void saveTawSystemRoleType(TawSystemRoleType tawSystemRoleType);

    /**
     * Removes a tawSystemRoleType from the database by roletype_id
     * @param roletype_id the tawSystemRoleType's roletype_id
     */
    public void removeTawSystemRoleType(final String roletype_id);
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize);
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize, final String whereStr);
}

