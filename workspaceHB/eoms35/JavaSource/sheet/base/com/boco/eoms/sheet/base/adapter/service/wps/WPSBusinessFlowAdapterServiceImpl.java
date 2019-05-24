/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.wps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowEngineService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.ibm.bpe.api.ProcessInstanceData;
import com.ibm.task.api.Task;

import commonj.sdo.DataObject;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: wps流程service
 * </p>
 * <p>
 * Date:2007-8-3 14:10:41
 * </p>
 * 
 * @author 曲静波
 * 
 * @version 1.0
 * 
 */
public class WPSBusinessFlowAdapterServiceImpl implements
		IBusinessFlowAdapterService {
	

	private IWorkflowEngineService engineService;

	/**
	 * @return Returns the engineService.
	 */
	public IWorkflowEngineService getEngineService() {
		return engineService;
	}

	/**
	 * @param engineService
	 *            The engineService to set.
	 */
	public void setEngineService(IWorkflowEngineService engineService) {
		this.engineService = engineService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getDoneTasksList(int,
	 *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
	 */
	public List getDoneTasksList(final int startIndex, final int length,
			final HashMap filter, final String taskBoName,
			final HashMap taskField, HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				List doneTaskList = new ArrayList();
				try {

					doneTaskList = engineService.getDoneTasksList(startIndex,
							length, filter, taskBoName, taskField);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return doneTaskList;
			}
		};
		List response = (List) com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		return response;

	}

	public List getDoneTasksList(int startIndex, int length, HashMap filter,
			String taskBoName, HashMap taskField) throws Exception {

		List doneTaskList = engineService.getDoneTasksList(startIndex, length,
				filter, taskBoName, taskField);
		return doneTaskList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getDoneTasksList(int,
	 *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
	 */
	public int getDoneTasksCount(final HashMap filter, HashMap sessionMap)
			throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		// Subject subject= (Subject)sessionMap.get("wpsSubject");
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				Integer doneTaskCount = new Integer(0);
				;
				try {

					doneTaskCount = new Integer(engineService
							.getDoneTasksCount(filter));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return doneTaskCount;
			}
		};
		Integer response = (Integer) com.ibm.websphere.security.auth.WSSubject
				.doAs(subject, getProcessStarted);
		return response.intValue();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksCount(java.util.HashMap)
	 */
	public int getUndoTasksCount(final HashMap filter, HashMap sessionMap)
			throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				Integer undoTaskCount = new Integer(0);
				try {
					undoTaskCount = new Integer(engineService
							.getUndoTasksCount(filter));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return undoTaskCount;
			}
		};
		Integer response = (Integer) com.ibm.websphere.security.auth.WSSubject
				.doAs(subject, getProcessStarted);
		return response.intValue();

	}

	/*
	 * 采用JDBC方式，从自行创建的task表中获取数据
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksCount(java.util.HashMap)
	 */
	public int getUndoTasksCount(HashMap filter) throws Exception {
		int undoTaskCount = engineService.getUndoTasksCount(filter);
		return undoTaskCount;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksList(int,
	 *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
	 */
	public List getUndoTasksList(final int startIndex, final int length,
			final HashMap filter, final String taskBoName,
			final HashMap taskField, HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				List unDoneTaskList = new ArrayList();
				try {

					unDoneTaskList = engineService.getUndoTasksList(startIndex,
							length, filter, taskBoName, taskField);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return unDoneTaskList;
			}
		};
		List response = (List) com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		return response;
	}

	public List getUndoTasksList(int startIndex, int length, HashMap filter,
			String taskBoName, HashMap taskField) throws Exception {

		List unDoneTaskList = engineService.getUndoTasksList(startIndex,
				length, filter, taskBoName, taskField);

		return unDoneTaskList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksList(java.util.HashMap)
	 */
	public List getUndoTasksList(final HashMap filter) throws Exception {
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn("", "");
		List taskList = new ArrayList();
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				List unDoneTaskList = null;
				try {
					unDoneTaskList = engineService.getUndoTasksList(filter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return unDoneTaskList;
			}
		};
		Object response = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (response == null) {
			throw new Exception("get undo task list fail!");
		} else {
			taskList = (List) response;
		}
		return taskList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#finishTask(java.lang.String,
	 *      java.util.HashMap)
	 */
	public void finishTask(final String activityId, final HashMap parameters,
			HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				String flag = null;
				try {
					flag = engineService.finishTask(activityId, parameters);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return flag;
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (respone == null) {
			throw new Exception("完成任务失败");
		} else {
			String szReturn = (String) respone;
			if (szReturn.equals("success")) {

			} else if (szReturn.equals("subtaskfail")) {
				throw new Exception("子任务尚未完成");
			}
		}

	}

	public void finishTask(final String activityId, final HashMap parameters)
			throws Exception {
		engineService.finishTask(activityId, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#triggerProcess(java.lang.String,
	 *      java.lang.String, java.util.HashMap)
	 */
	public String triggerProcess(final String processTemplateName,
			final String operationName, final HashMap parameters,
			HashMap sessionMap) throws Exception {
		String processInstanceId = null;
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				String piid = null;
				try {
					piid = engineService.triggerProcess(processTemplateName,
							operationName, parameters);
				} catch (Exception e) {
					piid = null;
					System.out.println("++++++triggerProcess wpsbus++++++++");
					e.printStackTrace();

				}
				return piid;
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (respone == null) {
			System.out.println("piid is null");
			throw new Exception("triggerProcess fail!");
		} else {
			processInstanceId = respone.toString();
			System.out.println("piid is" + processInstanceId);
		}
		return processInstanceId;

	}

	public Map getVariable(final String identifier, final String variableName,
			HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);

		java.security.PrivilegedAction variable = new java.security.PrivilegedAction() {
			public Object run() {
				Map map = new HashMap();
				try {
					return engineService.getVariable(identifier, variableName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return map;
			}

		};

		Object variableObject = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, variable);
		if (variableObject == null) {
			throw new Exception("取对象任务失败");
		}
		return (Map) variableObject;
	}

	public String triggerProcess(String processTemplateName,
			String operationName, HashMap parameters) throws Exception {

		String piid = engineService.triggerProcess(processTemplateName,
				operationName, parameters);
		return piid;

	}

	public Object transferMainObjectToMode(Object object, String modelClassName)
			throws Exception {
		DataObject dataObject = (DataObject) object;
		HashMap objectMap = WPSStaticMethod.createHashMap(dataObject);
		Object modelObject = null;
		modelObject = Class.forName(modelClassName).newInstance();
		SheetBeanUtils.populate(modelObject, objectMap);
		return modelObject;
	}

	public Object transferLinkObjectToMode(Object object, String modelClassName)
			throws Exception {
		DataObject dataObject = (DataObject) object;
		HashMap objectMap = WPSStaticMethod.createHashMap(dataObject);
		Object modelObject = Class.forName(modelClassName).newInstance();
		SheetBeanUtils.populate(modelObject, objectMap);
		return modelObject;
	}

	public boolean cancel(final String piid, HashMap sessionMap)
			throws Exception {

		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				boolean isSuccess = true;
				try {
					engineService.terminateProcess(piid);
					// engineService.deleteProcess(piid);
				} catch (Exception e) {
					e.printStackTrace();
					return new Boolean(false);
				}
				return new Boolean(isSuccess);
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (respone == null) {
			throw new Exception("cancel process fail!");
		} else {
			return ((Boolean) respone).booleanValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferIdFromDBToAction(java.lang.String)
	 */
	public String transferIdFromDBToAction(String id) throws Exception {
		// TODO Auto-generated method stub
		String ID = null;
		ID = WPSStaticMethod.tranferDBToAction(id);
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferIdFormActionToDB(java.lang.String)
	 */
	public String transferIdFormActionToDB(String id) throws Exception {
		// TODO Auto-generated method stub
		String ID = null;
		// ID=WPSStaticMethod.transferActionToDB(id);
		return ID;
	}

	/*
	 * 申明一个任务
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#claimTask(java.lang.String,
	 *      java.util.HashMap)
	 */
	public void claimTask(final String tkid, final HashMap parameters,
			HashMap sessionMap) throws Exception {
		// TODO Auto-generated method stub
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction claimTask = new java.security.PrivilegedAction() {
			public Object run() {
				boolean isSuccess = false;
				try {
					isSuccess = engineService.claimTask(tkid, parameters);
				} catch (Exception e) {
					return new Boolean(false);
				}
				return new Boolean(isSuccess);
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, claimTask);
		if (respone == null || ((Boolean) respone).booleanValue() == false) {
			throw new Exception("claim task fail!");
		}
	}

	/**
	 * 创建一个子任务
	 * 
	 * @param tkid
	 *            任务模板的任务ID
	 * @param parameters
	 *            子任务的参数
	 * @param sessionMap
	 *            用户的登陆信息
	 * @throws Exception
	 */
	public void createSubTask(final String tkid, final HashMap parameters,
			HashMap sessionMap) throws Exception {
		// TODO Auto-generated method stub
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction createSubTask = new java.security.PrivilegedAction() {
			public Object run() {
				boolean isSuccess = false;
				try {
					isSuccess = engineService.createSubTask(tkid, parameters);
				} catch (Exception e) {
					e.printStackTrace();
					return new Boolean(false);
				}
				return new Boolean(isSuccess);
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, createSubTask);
		if (respone == null || ((Boolean) respone).booleanValue() == false) {
			throw new Exception("create sub task fail!");
		}
	}

	/**
	 * 获取节点事件接口列表
	 * 
	 * @param tkid
	 * @param sessionMap
	 * @throws Exception
	 */
	public List getActiveEventHandlers(final String piid, HashMap sessionMap)
			throws Exception {
		// TODO Auto-generated method stub
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		List returnList = new ArrayList();
		java.security.PrivilegedAction getActiveEventHandlers = new java.security.PrivilegedAction() {
			public Object run() {
				List list = new ArrayList();
				try {
					list = engineService.getActiveEventHandlers(piid);
				} catch (Exception e) {
					e.printStackTrace();
					return new ArrayList();
				}
				return list;
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getActiveEventHandlers);
		if (respone == null) {
			throw new Exception("getActiveEventHandlers fail!");
		} else {
			returnList = (List) respone;
		}
		return returnList;
	}

	/**
	 * 根据任务ID号，获取task model对象值
	 * 
	 * @param taskId
	 *            任务ID
	 * @param task
	 *            taskModel对象
	 * @param sessionMap
	 * @throws Exception
	 */
	public ITask getTaskModeByTaskId(final String taskId, final ITask task,
			HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		ITask taskModel = task;
		java.security.PrivilegedAction getActiveEventHandlers = new java.security.PrivilegedAction() {
			public Object run() {
				ITask taskModelTemp = null;
				try {
					taskModelTemp = engineService.getTaskModelByTaskId(taskId,
							task);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				return taskModelTemp;
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getActiveEventHandlers);
		if (respone == null) {
			throw new Exception("getTaskModeByTaskId fail!");
		} else {
			taskModel = (ITask) respone;
		}
		return taskModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#triggerProcess(java.lang.String,
	 *      java.lang.String, java.util.HashMap)
	 */
	public String triggerEvent(final String piid,
			final String processTemplateName, final String operationName,
			final HashMap parameters, HashMap sessionMap) throws Exception {
		String processInstanceId = null;
		// String userId = StaticMethod
		// .nullObject2String(sessionMap.get("userId"));
		// String password = StaticMethod.nullObject2String(sessionMap
		// .get("password"));
		// Subject subject = safeService.logIn(userId, password);
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				String szReturn = "";
				System.out.println("triggerEvent==" + piid);
				try {
					szReturn = engineService.triggerEvent(piid,
							processTemplateName, operationName, parameters);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return szReturn;
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (respone == null) {
			throw new Exception("triggerEvent fail!");
		} else {
			processInstanceId = respone.toString();
		}
		return processInstanceId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferTask(java.lang.String,
	 *      java.lang.String, java.lang.String, java.util.HashMap)
	 */
	public void transferWorkItem(final String taskId,
			final String fromOwnerUserId, final String fromOwnerRoleId,
			final String toOwnerUserId, final String toOwnerRoleId,
			final String operOrgType, final HashMap map, HashMap sessionMap)
			throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction getProcessStarted = new java.security.PrivilegedAction() {
			public Object run() {
				boolean twReturn = true;
				try {
					engineService.transferWorkItem(taskId, fromOwnerUserId,
							fromOwnerRoleId, toOwnerUserId, toOwnerRoleId,
							operOrgType, map);
				} catch (Exception e) {
					e.printStackTrace();
					return new Boolean(false);
				}
				return new Boolean(twReturn);
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, getProcessStarted);
		if (((Boolean) respone).booleanValue() == false) {
			throw new Exception("transfer workItem fail!");
		}
	}

	/**
	 * 流程回调
	 * 
	 * @param piid
	 *            回调流程的piid
	 * @param operationName
	 *            回调流程的taskName
	 * @param parameters
	 * @param sessionMap
	 * @throws Exception
	 */
	public void reInvokeProcess(final String piid, final String operationName,
			final HashMap parameters, HashMap sessionMap) throws Exception {
		// TODO Auto-generated method stub
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap
				.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		java.security.PrivilegedAction createSubTask = new java.security.PrivilegedAction() {
			public Object run() {
				boolean isSuccess = true;
				try {
					engineService.reInvokeProcess(piid, operationName,
							parameters);
				} catch (Exception e) {
					e.printStackTrace();
					return new Boolean(false);
				}
				return new Boolean(isSuccess);
			}
		};
		Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, createSubTask);
		if (respone == null || ((Boolean) respone).booleanValue() == false) {
			throw new Exception("reInvokeProcess fail!");
		}
	}
	/**
	 * 取消申请一个任务
	 * 
	 * @param tkid
	 * @param sessionMap
	 * @throws Exception
	 */
	public void cancelClaimTask(final String tkid,HashMap parameters, HashMap sessionMap) throws Exception{
		String userId = StaticMethod.nullObject2String(sessionMap.get("userId"));
        String password = StaticMethod.nullObject2String(sessionMap.get("password"));
        WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
        Subject subject = safeService.logIn(userId, password);
        java.security.PrivilegedAction createSubTask = new java.security.PrivilegedAction() {
	    public Object run() {
		     boolean isSuccess = true;
		  try {
			 engineService.cancelClaimTask(tkid);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return new Boolean(false);
		   }
		    return new Boolean(isSuccess);
	    }
      };
       Object respone = com.ibm.websphere.security.auth.WSSubject.doAs(
		   subject, createSubTask);
       if (respone == null || ((Boolean) respone).booleanValue() == false) {
	       throw new Exception("cancelClaimTask fail!");
        }		
      }
	/**
	 * 
	 * @param processId
	 * @param propertyName
	 * @param sessionMap
	 * @return
	 * @throws Exception
	 */
	public String getProcessInstanceCustomPropertyValue(final String processId,final String propertyName,
			HashMap sessionMap) throws Exception {
		String userId = StaticMethod
				.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);

		java.security.PrivilegedAction variable = new java.security.PrivilegedAction() {
			public Object run() {
				String value = "";
				try {
					value =  engineService.getProcessInstanceCustomPropertyValue(processId, propertyName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return value;
			}

		};

		Object variableObject = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, variable);
		if (variableObject == null) {
			throw new Exception("取对象任务失败");
		}
		return (String) variableObject;
	}
	
	/**
	 * 
	 * @param processId
	 * @param propertyName
	 * @param propertyValue
	 * @param sessionMap
	 * @throws Exception
	 */
	public void setProcessInstanceCustomProperty(final String processId,
			final String propertyName,final String propertyValue,HashMap sessionMap) throws Exception {
		String userId = StaticMethod
		.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		
		java.security.PrivilegedAction variable = new java.security.PrivilegedAction() {
			public Object run() {
				try {
					engineService.setProcessInstanceCustomProperty(processId,propertyName, propertyValue);
				} catch (Exception e) {
					e.printStackTrace();
					return new Boolean(false);
				}
				return new Boolean(true);
			}
		
		};
		
		Object variableObject = com.ibm.websphere.security.auth.WSSubject.doAs(
				subject, variable);
		if (variableObject == null) {
			throw new Exception("取对象任务失败");
		}
	}
	/**
	 * 根据查询语句查询记录
	 * @return
	 * @throws Exception
	 */
	public HashMap queryAll(final String selectClause,final String whereClause,HashMap sessionMap) throws Exception{
		String userId = StaticMethod.nullObject2String(sessionMap.get("userId"));
		String password = StaticMethod.nullObject2String(sessionMap.get("password"));
		WPSSecutiryServiceImpl safeService=new WPSSecutiryServiceImpl();
		Subject subject = safeService.logIn(userId, password);
		
		java.security.PrivilegedAction variable = new java.security.PrivilegedAction() {
			public Object run() {
				HashMap resultMap = new HashMap();
				try {
					resultMap = engineService.queryAll(selectClause, whereClause);
				} catch (Exception e) {
					e.printStackTrace();
					return new Integer(0);
				}
				return resultMap;
			}
		};
		
		Object variableObject = com.ibm.websphere.security.auth.WSSubject.doAs(subject, variable);
		if (variableObject == null) {
			throw new Exception("取对象任务失败");
		}
		return ((HashMap) variableObject);
	}
	
	
	

}
