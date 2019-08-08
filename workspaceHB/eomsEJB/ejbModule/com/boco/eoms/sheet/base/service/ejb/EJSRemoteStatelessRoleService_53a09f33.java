package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessRoleService_53a09f33
 */
public class EJSRemoteStatelessRoleService_53a09f33 extends EJSWrapper implements RoleService {
    /**
     * EJSRemoteStatelessRoleService_53a09f33
     */
    public EJSRemoteStatelessRoleService_53a09f33() throws java.rmi.RemoteException {
        super();
    }

    /**
     * isVirtualRole
     */
    public boolean isVirtualRole(java.lang.String subRoleId) throws java.rmi.RemoteException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;
        boolean _EJS_result = false;
        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[1];
                _jacc_parms[0] = subRoleId;
            }
            com.boco.eoms.sheet.base.service.ejb.RoleServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.RoleServiceBean) container.preInvoke(this, 0, _EJS_s, _jacc_parms);
            _EJS_result = beanRef.isVirtualRole(subRoleId);
        } catch (java.rmi.RemoteException ex) {
            _EJS_s.setUncheckedException(ex);
        } catch (Throwable ex) {
            _EJS_s.setUncheckedException(ex);
            throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
        } finally {
            try {
                container.postInvoke(this, 0, _EJS_s);
            } finally {
                container.putEJSDeployedSupport(_EJS_s);
            }
        }
        return _EJS_result;
    }
}
