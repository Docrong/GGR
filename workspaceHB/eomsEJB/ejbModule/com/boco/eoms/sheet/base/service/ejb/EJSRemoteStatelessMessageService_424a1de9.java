package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessMessageService_424a1de9
 */
public class EJSRemoteStatelessMessageService_424a1de9 extends EJSWrapper implements MessageService {
	/**
	 * EJSRemoteStatelessMessageService_424a1de9
	 */
	public EJSRemoteStatelessMessageService_424a1de9() throws java.rmi.RemoteException {
		super();	}
	/**
	 * closeMsg
	 */
	public void closeMsg(java.lang.String sheetKey, java.lang.String workflowName, int closeMsgType) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = sheetKey;
				_jacc_parms[1] = workflowName;
				_jacc_parms[2] = new java.lang.Integer(closeMsgType);
			}
	com.boco.eoms.sheet.base.service.ejb.MessageServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.MessageServiceBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			beanRef.closeMsg(sheetKey, workflowName, closeMsgType);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * sendMsg
	 */
	public void sendMsg(commonj.sdo.DataObject mainObject, java.lang.String workflowName, java.lang.String sheetKey, java.lang.String sheetId, java.lang.String title, int receiveType, java.lang.String receiverId, java.lang.String acceptLimitTime, java.lang.String dealLimitTime) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[9];
				_jacc_parms[0] = mainObject;
				_jacc_parms[1] = workflowName;
				_jacc_parms[2] = sheetKey;
				_jacc_parms[3] = sheetId;
				_jacc_parms[4] = title;
				_jacc_parms[5] = new java.lang.Integer(receiveType);
				_jacc_parms[6] = receiverId;
				_jacc_parms[7] = acceptLimitTime;
				_jacc_parms[8] = dealLimitTime;
			}
	com.boco.eoms.sheet.base.service.ejb.MessageServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.MessageServiceBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			beanRef.sendMsg(mainObject, workflowName, sheetKey, sheetId, title, receiveType, receiverId, acceptLimitTime, dealLimitTime);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * sendMsgHie
	 */
	public void sendMsgHie(java.lang.String workflowName, java.lang.String sheetKey, java.lang.String sheetId, int receiveType, java.lang.String receiverId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[5];
				_jacc_parms[0] = workflowName;
				_jacc_parms[1] = sheetKey;
				_jacc_parms[2] = sheetId;
				_jacc_parms[3] = new java.lang.Integer(receiveType);
				_jacc_parms[4] = receiverId;
			}
	com.boco.eoms.sheet.base.service.ejb.MessageServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.MessageServiceBean)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			beanRef.sendMsgHie(workflowName, sheetKey, sheetId, receiveType, receiverId);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 2, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * sendMsgHie
	 */
	public void sendMsgHie(java.lang.String workflowName, java.lang.String sheetKey, java.lang.String sheetId, java.lang.String title, int receiveType, java.lang.String receiverId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[6];
				_jacc_parms[0] = workflowName;
				_jacc_parms[1] = sheetKey;
				_jacc_parms[2] = sheetId;
				_jacc_parms[3] = title;
				_jacc_parms[4] = new java.lang.Integer(receiveType);
				_jacc_parms[5] = receiverId;
			}
	com.boco.eoms.sheet.base.service.ejb.MessageServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.MessageServiceBean)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			beanRef.sendMsgHie(workflowName, sheetKey, sheetId, title, receiveType, receiverId);
		}
		catch (java.lang.RuntimeException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (java.lang.Exception ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 3, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
