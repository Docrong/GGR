package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessSaveDataServiceHome_0be3888c
 */
public class EJSRemoteStatelessSaveDataServiceHome_0be3888c extends EJSWrapper implements com.boco.eoms.sheet.base.service.ejb.SaveDataServiceHome {
    /**
     * EJSRemoteStatelessSaveDataServiceHome_0be3888c
     */
    public EJSRemoteStatelessSaveDataServiceHome_0be3888c() throws java.rmi.RemoteException {
        super();
    }

    /**
     * create
     */
    public com.boco.eoms.sheet.base.service.ejb.SaveDataService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;
        com.boco.eoms.sheet.base.service.ejb.SaveDataService _EJS_result = null;
        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[0];
            }
            com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c _EJS_beanRef = (com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c) container.preInvoke(this, 0, _EJS_s, _jacc_parms);
            _EJS_result = _EJS_beanRef.create();
        } catch (javax.ejb.CreateException ex) {
            _EJS_s.setCheckedException(ex);
            throw ex;
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

    /**
     * getEJBMetaData
     */
    public javax.ejb.EJBMetaData getEJBMetaData() throws java.rmi.RemoteException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;
        javax.ejb.EJBMetaData _EJS_result = null;
        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[0];
            }
            com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c _EJS_beanRef = (com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c) container.preInvoke(this, 1, _EJS_s, _jacc_parms);
            _EJS_result = _EJS_beanRef.getEJBMetaData();
        } catch (java.rmi.RemoteException ex) {
            _EJS_s.setUncheckedException(ex);
        } catch (Throwable ex) {
            _EJS_s.setUncheckedException(ex);
            throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
        } finally {
            try {
                container.postInvoke(this, 1, _EJS_s);
            } finally {
                container.putEJSDeployedSupport(_EJS_s);
            }
        }
        return _EJS_result;
    }

    /**
     * getHomeHandle
     */
    public javax.ejb.HomeHandle getHomeHandle() throws java.rmi.RemoteException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;
        javax.ejb.HomeHandle _EJS_result = null;
        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[0];
            }
            com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c _EJS_beanRef = (com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c) container.preInvoke(this, 2, _EJS_s, _jacc_parms);
            _EJS_result = _EJS_beanRef.getHomeHandle();
        } catch (java.rmi.RemoteException ex) {
            _EJS_s.setUncheckedException(ex);
        } catch (Throwable ex) {
            _EJS_s.setUncheckedException(ex);
            throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
        } finally {
            try {
                container.postInvoke(this, 2, _EJS_s);
            } finally {
                container.putEJSDeployedSupport(_EJS_s);
            }
        }
        return _EJS_result;
    }

    /**
     * remove
     */
    public void remove(java.lang.Object arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;

        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[1];
                _jacc_parms[0] = arg0;
            }
            com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c _EJS_beanRef = (com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c) container.preInvoke(this, 3, _EJS_s, _jacc_parms);
            _EJS_beanRef.remove(arg0);
        } catch (java.rmi.RemoteException ex) {
            _EJS_s.setUncheckedException(ex);
        } catch (javax.ejb.RemoveException ex) {
            _EJS_s.setCheckedException(ex);
            throw ex;
        } catch (Throwable ex) {
            _EJS_s.setUncheckedException(ex);
            throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
        } finally {
            try {
                container.postInvoke(this, 3, _EJS_s);
            } finally {
                container.putEJSDeployedSupport(_EJS_s);
            }
        }
        return;
    }

    /**
     * remove
     */
    public void remove(javax.ejb.Handle arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
        EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
        Object[] _jacc_parms = null;

        try {
            if (container.doesJaccNeedsEJBArguments(this)) {
                _jacc_parms = new Object[1];
                _jacc_parms[0] = arg0;
            }
            com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c _EJS_beanRef = (com.boco.eoms.sheet.base.service.ejb.EJSStatelessSaveDataServiceHomeBean_0be3888c) container.preInvoke(this, 4, _EJS_s, _jacc_parms);
            _EJS_beanRef.remove(arg0);
        } catch (java.rmi.RemoteException ex) {
            _EJS_s.setUncheckedException(ex);
        } catch (javax.ejb.RemoveException ex) {
            _EJS_s.setCheckedException(ex);
            throw ex;
        } catch (Throwable ex) {
            _EJS_s.setUncheckedException(ex);
            throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
        } finally {
            try {
                container.postInvoke(this, 4, _EJS_s);
            } finally {
                container.putEJSDeployedSupport(_EJS_s);
            }
        }
        return;
    }
}
