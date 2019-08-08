package com.boco.eoms.ejb.service;

import javax.naming.InitialContext;

import com.boco.eoms.sheet.base.service.ejb.RoleService;
import com.boco.eoms.sheet.base.service.ejb.RoleServiceHome;

public class RoleObjectImpl {
    private RoleService roleService;

    public RoleObjectImpl() {
    }

    /**
     * @return Returns the saveDataService.
     */

    public RoleService getRoleService() throws Exception {
        InitialContext initialContext = new InitialContext();
        RoleServiceHome home
                = (RoleServiceHome) initialContext.lookup("ejb/com/boco/eoms/sheet/base/service/ejb/RoleServiceHome");
        roleService = home.create();
        //"ejb/com/boco/eoms/sheet/base/service/ejb/SaveDataServiceHome";
        return roleService;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.ejb.service.ISaveDataObject#saveMain(commonj.sdo.DataObject,
     *      java.lang.String)
     */
    public boolean isVirtualRole(String subRoleId) throws Exception {
        return this.getRoleService().isVirtualRole(subRoleId);
    }
}
