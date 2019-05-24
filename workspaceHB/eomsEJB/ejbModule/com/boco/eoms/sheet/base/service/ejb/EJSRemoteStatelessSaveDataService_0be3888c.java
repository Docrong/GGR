package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessSaveDataService_0be3888c
 */
public class EJSRemoteStatelessSaveDataService_0be3888c extends EJSWrapper implements SaveDataService {
	/**
	 * EJSRemoteStatelessSaveDataService_0be3888c
	 */
	public EJSRemoteStatelessSaveDataService_0be3888c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * getUUID
	 */
	public java.lang.String getUUID() throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.String _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.getUUID();
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
		return _EJS_result;
	}
	/**
	 * saveLink
	 */
	public java.lang.String saveLink(java.lang.Object obj, java.lang.String modelName) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.String _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = obj;
				_jacc_parms[1] = modelName;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.saveLink(obj, modelName);
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
		return _EJS_result;
	}
	/**
	 * saveOrUpdateLink
	 */
	public java.lang.String saveOrUpdateLink(java.lang.Object obj, java.lang.String modelName) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.String _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = obj;
				_jacc_parms[1] = modelName;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 2, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.saveOrUpdateLink(obj, modelName);
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
		return _EJS_result;
	}
	/**
	 * invokeWfInterface
	 */
	public void invokeWfInterface(java.lang.String mainBeanId, java.lang.String sheetKey, java.lang.String linkId, java.lang.String linkBeanId, java.lang.String interfaceType, java.lang.String methodType, java.lang.String sendType) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[7];
				_jacc_parms[0] = mainBeanId;
				_jacc_parms[1] = sheetKey;
				_jacc_parms[2] = linkId;
				_jacc_parms[3] = linkBeanId;
				_jacc_parms[4] = interfaceType;
				_jacc_parms[5] = methodType;
				_jacc_parms[6] = sendType;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 3, _EJS_s, _jacc_parms);
			beanRef.invokeWfInterface(mainBeanId, sheetKey, linkId, linkBeanId, interfaceType, methodType, sendType);
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
	/**
	 * saveMain
	 */
	public void saveMain(java.lang.Object obj, java.lang.String modelName) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = obj;
				_jacc_parms[1] = modelName;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 4, _EJS_s, _jacc_parms);
			beanRef.saveMain(obj, modelName);
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
				container.postInvoke(this, 4, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * saveOrUpdateMain
	 */
	public void saveOrUpdateMain(java.lang.Object obj, java.lang.String modelName) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = obj;
				_jacc_parms[1] = modelName;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 5, _EJS_s, _jacc_parms);
			beanRef.saveOrUpdateMain(obj, modelName);
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
				container.postInvoke(this, 5, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * saveOrUpdateTask
	 */
	public void saveOrUpdateTask(java.util.HashMap taskMap, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = taskMap;
				_jacc_parms[1] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 6, _EJS_s, _jacc_parms);
			beanRef.saveOrUpdateTask(taskMap, taskBeanId);
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
				container.postInvoke(this, 6, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * saveTask
	 */
	public void saveTask(java.util.HashMap taskMap, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = taskMap;
				_jacc_parms[1] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 7, _EJS_s, _jacc_parms);
			beanRef.saveTask(taskMap, taskBeanId);
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
				container.postInvoke(this, 7, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateLink
	 */
	public void updateLink(java.util.HashMap linkMap, java.lang.String linkBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = linkMap;
				_jacc_parms[1] = linkBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 8, _EJS_s, _jacc_parms);
			beanRef.updateLink(linkMap, linkBeanId);
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
				container.postInvoke(this, 8, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateMain
	 */
	public void updateMain(java.util.HashMap mainMap, java.lang.String mainBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = mainMap;
				_jacc_parms[1] = mainBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 9, _EJS_s, _jacc_parms);
			beanRef.updateMain(mainMap, mainBeanId);
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
				container.postInvoke(this, 9, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateSheetRelationState
	 */
	public void updateSheetRelationState(java.lang.String sheetKey) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = sheetKey;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 10, _EJS_s, _jacc_parms);
			beanRef.updateSheetRelationState(sheetKey);
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
				container.postInvoke(this, 10, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateTask
	 */
	public void updateTask(java.util.HashMap taskMap, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = taskMap;
				_jacc_parms[1] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 11, _EJS_s, _jacc_parms);
			beanRef.updateTask(taskMap, taskBeanId);
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
				container.postInvoke(this, 11, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateTaskByStatus
	 */
	public void updateTaskByStatus(java.lang.String sheetKey, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[2];
				_jacc_parms[0] = sheetKey;
				_jacc_parms[1] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 12, _EJS_s, _jacc_parms);
			beanRef.updateTaskByStatus(sheetKey, taskBeanId);
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
				container.postInvoke(this, 12, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateTaskBySubTaskFlag
	 */
	public void updateTaskBySubTaskFlag(java.lang.String taskId, java.lang.String subTaskFlag, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = taskId;
				_jacc_parms[1] = subTaskFlag;
				_jacc_parms[2] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 13, _EJS_s, _jacc_parms);
			beanRef.updateTaskBySubTaskFlag(taskId, subTaskFlag, taskBeanId);
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
				container.postInvoke(this, 13, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
	/**
	 * updateTaskState
	 */
	public void updateTaskState(java.lang.String taskId, java.lang.String taskState, java.lang.String taskBeanId) throws java.lang.Exception {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[3];
				_jacc_parms[0] = taskId;
				_jacc_parms[1] = taskState;
				_jacc_parms[2] = taskBeanId;
			}
	com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean beanRef = (com.boco.eoms.sheet.base.service.ejb.SaveDataServiceBean)container.preInvoke(this, 14, _EJS_s, _jacc_parms);
			beanRef.updateTaskState(taskId, taskState, taskBeanId);
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
				container.postInvoke(this, 14, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
