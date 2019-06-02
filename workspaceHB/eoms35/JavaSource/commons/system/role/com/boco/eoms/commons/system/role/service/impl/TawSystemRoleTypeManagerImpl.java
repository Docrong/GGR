package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemRoleType;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager;

public class TawSystemRoleTypeManagerImpl extends BaseManager implements ITawSystemRoleTypeManager {
    private TawSystemRoleTypeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setTawSystemRoleTypeDao(TawSystemRoleTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager#getTawSystemRoleTypes(com.boco.eoms.commons.system.role.model.TawSystemRoleType)
     */
    public List getTawSystemRoleTypes(final TawSystemRoleType tawSystemRoleType) {
        return dao.getTawSystemRoleTypes(tawSystemRoleType);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager#getTawSystemRoleType(String roletype_id)
     */
    public TawSystemRoleType getTawSystemRoleType(final String roletype_id) {
        return dao.getTawSystemRoleType(new Long(roletype_id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager#saveTawSystemRoleType(TawSystemRoleType tawSystemRoleType)
     */
    public void saveTawSystemRoleType(TawSystemRoleType tawSystemRoleType) {
        dao.saveTawSystemRoleType(tawSystemRoleType);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleTypeManager#removeTawSystemRoleType(String roletype_id)
     */
    public void removeTawSystemRoleType(final String roletype_id) {
        dao.removeTawSystemRoleType(new Long(roletype_id));
    }
    /**
     * 
     */
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize) {
        return dao.getTawSystemRoleTypes(curPage, pageSize,null);
    }
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize, final String whereStr) {
        return dao.getTawSystemRoleTypes(curPage, pageSize, whereStr);
    }
}
