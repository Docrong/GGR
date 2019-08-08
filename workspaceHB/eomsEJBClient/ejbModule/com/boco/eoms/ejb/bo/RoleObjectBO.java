package com.boco.eoms.ejb.bo;

import java.util.HashMap;

import com.boco.eoms.ejb.service.RoleObjectImpl;

public class RoleObjectBO {
    private static RoleObjectBO objectBO = null;


    private RoleObjectBO() {

    }

    public static RoleObjectBO getInstance() {
        if (objectBO == null) {
            objectBO = init();
        }
        return objectBO;
    }

    private static RoleObjectBO init() {
        objectBO = new RoleObjectBO();
        return objectBO;
    }


    public boolean isVirtualRole(String subRoleId) throws Exception {
        RoleObjectImpl object = new RoleObjectImpl();
        return object.isVirtualRole(subRoleId);
    }

}
