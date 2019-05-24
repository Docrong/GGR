package com.boco.eoms.sheet.base.adapter.service.wps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.security.auth.Subject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowSecutiryService;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSSecutiryServiceImpl;
import com.ibm.websphere.management.AdminService;
import com.ibm.websphere.management.AdminServiceFactory;

public class EomsSheetErrorFix {
	private ObjectName processContainer = null;

	private ObjectName taskManager = null;

	/**
	 * Use AdminService at server side to query and invoke MBean.
	 */
	private AdminService adminService = AdminServiceFactory.getAdminService();

	/**
	 * Get numberOfMessagesInRetentionQueue in Business Flow Manager.
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 */
	public int getBFMRetentionMessageCount() throws AttributeNotFoundException,
			InstanceNotFoundException, MBeanException, ReflectionException,
			MalformedObjectNameException, NullPointerException {

		String attrName = "numberOfMessagesInRetentionQueue";
		Object number = adminService.getAttribute(getProcessContainer(),
				attrName);
		return ((Integer) number).intValue();
	}

	/**
	 * Get numberOfMessagesInHoldQueue in Business Flow Manager.
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 */
	public int getBFMHoldMessageCount() throws AttributeNotFoundException,
			InstanceNotFoundException, MBeanException, ReflectionException,
			MalformedObjectNameException, NullPointerException {

		String attrName = "numberOfMessagesInHoldQueue";
		Object number = adminService.getAttribute(getProcessContainer(),
				attrName);
		return ((Integer) number).intValue();
	}

	/**
	 * Get numberOfMessagesInHoldQueue in Human Task Manager.
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 */
	public int getHTMRetentionMessageCount() throws AttributeNotFoundException,
			InstanceNotFoundException, MBeanException, ReflectionException,
			MalformedObjectNameException, NullPointerException {

		String attrName = "numberOfMessagesInRetentionQueue";
		Object number = adminService.getAttribute(getTaskManager(), attrName);
		return ((Integer) number).intValue();
	}

	/**
	 * Get numberOfMessagesInHoldQueue in Human Task Manager.
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 */
	public int getHTMHoldMessageCount() throws AttributeNotFoundException,
			InstanceNotFoundException, MBeanException, ReflectionException,
			MalformedObjectNameException, NullPointerException {

		String attrName = "numberOfMessagesInHoldQueue";
		Object number = adminService.getAttribute(getTaskManager(), attrName);
		return ((Integer) number).intValue();
	}

	/**
	 * Replay all messages in Business Flow Manager Retention Queue.
	 * 
	 * @throws InstanceNotFoundException
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws NullPointerException
	 */
	public void replayBFMRetentionMessages() throws InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException,
			NullPointerException {
		String opName = "replayRetentionQueueMessages";
		adminService.invoke(getProcessContainer(), opName, null, null);
	}

	/**
	 * Replay all messages in Business Flow Manager Hold Queue.
	 * 
	 * @throws InstanceNotFoundException
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws NullPointerException
	 */
	public void replayBFMHoldMessages() throws InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException,
			NullPointerException {
		String opName = "replayHoldQueueMessages";
		adminService.invoke(getProcessContainer(), opName, null, null);
	}

	/**
	 * Replay all messages in Human Task Manager Retention Queue.
	 * 
	 * @throws InstanceNotFoundException
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws NullPointerException
	 */
	public void replayHTMRetentionMessages() throws InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException,
			NullPointerException {
		String opName = "replayRetentionQueueMessages";
		adminService.invoke(getTaskManager(), opName, null, null);

	}

	/**
	 * Replay all messages in Human Task Manager Hold Queue.
	 * 
	 * @throws InstanceNotFoundException
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws NullPointerException
	 */
	public void replayHTMHoldMessages() throws InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException,
			NullPointerException {
		String opName = "replayHoldQueueMessages";
		adminService.invoke(getTaskManager(), opName, null, null);

	}

	public ObjectName getProcessContainer()
			throws MalformedObjectNameException, NullPointerException {

		if (null == processContainer) {
			AdminService adminService = AdminServiceFactory.getAdminService();
			ObjectName queryName = new ObjectName(
					"WebSphere:*,type=ProcessContainer");
			Set mbeans = adminService.queryNames(queryName, null);
			if (!mbeans.isEmpty()) {
				processContainer = (ObjectName) mbeans.iterator().next();
				System.out.println("Obtained ProcessContainer MBean: "
						+ processContainer);
			}
		}
		return processContainer;
	}

	public ObjectName getTaskManager() throws MalformedObjectNameException,
			NullPointerException {

		if (null == taskManager) {
			AdminService adminService = AdminServiceFactory.getAdminService();
			ObjectName queryName = new ObjectName(
					"WebSphere:*,type=TaskManager");
			Set mbeans = adminService.queryNames(queryName, null);
			if (!mbeans.isEmpty()) {
				taskManager = (ObjectName) mbeans.iterator().next();
				System.out
						.println("Obtained TaskManager MBean: " + taskManager);
			}
		}
		return taskManager;
	}

	public void fixBFMRetentionQueue() {
		try {
			IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
					.getInstance().getBean("WorkflowSecutiryService");
			Subject subject = safeService.logIn("admin", "111");
			java.security.PrivilegedAction fixBFMRetentionQueue = new java.security.PrivilegedAction() {
				public Object run() {
					try {
						int beforeCount = getBFMRetentionMessageCount();
						if (beforeCount != 0) {
							BocoLog.debug(this, "BFMretentionQueue中共存在"
									+ beforeCount + "个需要修复");
							replayBFMRetentionMessages();
							int afterCount = getBFMRetentionMessageCount();
							if (afterCount != 0) {
								BocoLog.debug(this, "BFMretentionQueue中共还存在"
										+ afterCount + "个Queue需要修复,请关注");
							}
						}
					} catch (InstanceNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MalformedObjectNameException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MBeanException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (ReflectionException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (NullPointerException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (AttributeNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					}
					return null;
				}
			};
			com.ibm.websphere.security.auth.WSSubject.doAs(subject,
					fixBFMRetentionQueue);

		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
			e.printStackTrace();
		}
	}

	public void fixBFMHoldQueue() {
		try {
			IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
					.getInstance().getBean("WorkflowSecutiryService");
			Subject subject = safeService.logIn("admin", "111");
			java.security.PrivilegedAction fixBFMHoldQueue = new java.security.PrivilegedAction() {
				public Object run() {
					try {
						int beforeCount = getBFMHoldMessageCount();
						if (beforeCount != 0) {
							BocoLog.debug(this, "BFMHoldQueue中共存在"
									+ beforeCount + "个需要修复");
							replayBFMHoldMessages();
							int afterCount = getBFMHoldMessageCount();
							if (afterCount != 0) {
								BocoLog.debug(this, "BFMHoldQueue中共还存在"
										+ afterCount + "个Queue需要修复,请关注");
							}
						}
					} catch (InstanceNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MalformedObjectNameException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MBeanException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (ReflectionException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (NullPointerException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (AttributeNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					}
					return null;
				}
			};
			com.ibm.websphere.security.auth.WSSubject.doAs(subject,
					fixBFMHoldQueue);
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
			e.printStackTrace();
		}
	}

	private void fixHTMRetentionQueue() {
		try {
			IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
					.getInstance().getBean("WorkflowSecutiryService");
			Subject subject = safeService.logIn("admin", "111");
			java.security.PrivilegedAction fixHTMRetentionQueue = new java.security.PrivilegedAction() {
				public Object run() {
					try {
						int beforeCount = getHTMRetentionMessageCount();
						if (beforeCount != 0) {
							BocoLog.debug(this, "HTMRetentionQueue中共存在"
									+ beforeCount + "个需要修复");
							replayHTMRetentionMessages();
							int afterCount = getHTMRetentionMessageCount();
							if (afterCount != 0) {
								BocoLog.debug(this, "HTMRetentionQueue中共还存在"
										+ afterCount + "个Queue需要修复,请关注");
							}
						}
					} catch (InstanceNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MalformedObjectNameException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MBeanException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (ReflectionException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (NullPointerException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (AttributeNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					}
					return null;
				}
			};
			com.ibm.websphere.security.auth.WSSubject.doAs(subject,
					fixHTMRetentionQueue);
		} catch (Exception e) {
			BocoLog.error(this, e.getMessage());
			e.printStackTrace();
		}
	}

	private void fixHTMHoldQueue() {
		try {
			IWorkflowSecutiryService safeService = (IWorkflowSecutiryService) ApplicationContextHolder
					.getInstance().getBean("WorkflowSecutiryService");
			Subject subject = safeService.logIn("admin", "111");
			java.security.PrivilegedAction fixHTMHoldQueue = new java.security.PrivilegedAction() {
				public Object run() {
					try {
						int beforeCount = getHTMHoldMessageCount();
						if (beforeCount != 0) {
							BocoLog.debug(this, "HTMHoldQueue中共存在"
									+ beforeCount + "个需要修复");
							replayBFMHoldMessages();
							int afterCount = getHTMHoldMessageCount();
							if (afterCount != 0) {
								BocoLog.debug(this, "HTMHoldQueue中共还存在"
										+ afterCount + "个Queue需要修复,请关注");
							}
						}
					} catch (InstanceNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MalformedObjectNameException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (MBeanException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (ReflectionException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (NullPointerException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					} catch (AttributeNotFoundException e) {
						BocoLog.error(this, e.getMessage());
						e.printStackTrace();
					}
					return null;
				}
			};
			com.ibm.websphere.security.auth.WSSubject.doAs(subject,
					fixHTMHoldQueue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
