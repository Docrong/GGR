package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessMessageServiceHomeBean_424a1de9
 */
public class EJSStatelessMessageServiceHomeBean_424a1de9 extends EJSHome {
    static final long serialVersionUID = 61;

    /**
     * EJSStatelessMessageServiceHomeBean_424a1de9
     */
    public EJSStatelessMessageServiceHomeBean_424a1de9() throws java.rmi.RemoteException {
        super();
    }

    /**
     * create
     */
    public com.boco.eoms.sheet.base.service.ejb.MessageService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
        BeanO beanO = null;
        com.boco.eoms.sheet.base.service.ejb.MessageService result = null;
        boolean createFailed = false;
        try {
            result = (com.boco.eoms.sheet.base.service.ejb.MessageService) super.createWrapper(null);
        } catch (javax.ejb.CreateException ex) {
            createFailed = true;
            throw ex;
        } catch (java.rmi.RemoteException ex) {
            createFailed = true;
            throw ex;
        } catch (Throwable ex) {
            createFailed = true;
            throw new CreateFailureException(ex);
        } finally {
            if (createFailed) {
                super.createFailure(beanO);
            }
        }
        return result;
    }
}
