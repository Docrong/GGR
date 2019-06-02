package com.boco.eoms.sheet.base.service.ejb;

import java.lang.reflect.Method;
import java.util.HashMap;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSStaticMethod;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSStaticVariable;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;

public class SaveDataServiceBeanBAK {

	private ThreadLocal serviceBeanId = new ThreadLocal();

	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public void init(String mainServiceBeanId) throws Exception {
		serviceBeanId.set(mainServiceBeanId);
	}

	/**
	 * 新增业务main到DB,不用
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public HashMap saveMain(Object obj, String modelName, int startFlag,
			String mainServiceBeanId) throws Exception {

		System.out.println("mainServiceBeanId=================73="
				+ mainServiceBeanId);
		IMainService mainService = (IMainService) ApplicationContextHolder
				.getInstance().getBean(mainServiceBeanId);
		HashMap map = new HashMap();
		String sheetId = null;
		// 现将wps端传输过来的BO对象转换成HashMap
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		Object model = businessFlowManager.transferMainObjectToMode(obj,
				modelName);
		// 获取工单流水号，并保存到main
		System.out.println("startFlag=" + startFlag);
		if (startFlag == WPSStaticVariable.STARTFLAG_START) {
			// 说明该工单不在数据库中存在，获取工单流水号
			sheetId = mainService.getSheetId();
			System.out.println("sheet id is " + sheetId);
			Method setterMethod = model.getClass().getMethod("setSheetId",
					new Class[] { String.class });
			setterMethod.invoke(model, new Object[] { sheetId });
		}
		mainService.addMain(model);

		Method getterMethod = model.getClass().getMethod("getId",
				new Class[] {});
		Object idObject = getterMethod.invoke(model, new Object[] {});
		String id = idObject.toString();

		Method getterSheetIdMethod = model.getClass().getMethod("getSheetId",
				new Class[] {});
		Object sheetIdObject = getterSheetIdMethod.invoke(model,
				new Object[] {});
		sheetId = sheetIdObject.toString();

		map.put("mainId", id);
		map.put("sheetId", sheetId);
		return map;
	}

	/**
	 * 单独保存main信息
	 * 
	 * @param obj
	 * @param modelName
	 * @param mainServiceBeanId
	 * @return
	 * @throws Exception
	 */
	public void saveMain(Object obj, String modelName, String mainServiceBeanId)
			throws Exception {

		System.out.println("mainServiceBeanId=================115="
				+ mainServiceBeanId);
		IMainService mainService = (IMainService) ApplicationContextHolder
				.getInstance().getBean(mainServiceBeanId);
		// 现将wps端传输过来的BO对象转换成HashMap
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		Object model = businessFlowManager.transferMainObjectToMode(obj,
				modelName);
		mainService.addMain(model);
		// Method getterIdMethod = model.getClass().getMethod("getId",
		// new Class[] {});
		// Object idObject = getterIdMethod.invoke(model, new Object[] {});

	}

	/**
	 * 保存业务数据link内容，不用
	 * 
	 * @param obj
	 *            link对象
	 * @throws Exception
	 */
	public String saveLink(Object obj, String modelName, String mainId)
			throws Exception {
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		ILinkService linkService = (ILinkService) ApplicationContextHolder
				.getInstance().getBean("LinkService");
		// 现将wps端传输过来的BO对象转换成HashMap
		Object model = businessFlowManager.transferLinkObjectToMode(obj,
				modelName);
		Method setterMethod = model.getClass().getMethod("setMainId",
				new Class[] { String.class });
		setterMethod.invoke(model, new Object[] { mainId });
		linkService.addLink(model);
		Method getterMethod = model.getClass().getMethod("getId",
				new Class[] {});
		Object idObject = getterMethod.invoke(model, new Object[] {});
		String id = idObject.toString();
		return id;

	}

	/**
	 * 单独保存业务数据link内容
	 * 
	 * @param obj
	 * @param modelName
	 * @return
	 * @throws Exception
	 */
	public void saveLink(Object obj, String modelName) throws Exception {
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		ILinkService linkService = (ILinkService) ApplicationContextHolder
				.getInstance().getBean("LinkService");
		// 现将wps端传输过来的BO对象转换成HashMap
		Object model = businessFlowManager.transferLinkObjectToMode(obj,
				modelName);
		linkService.addLink(model);
		// Method getterMethod = model.getClass().getMethod("getId",
		// new Class[] {});
		// Object idObject = getterMethod.invoke(model, new Object[] {});
		// String id = idObject.toString();

	}

	/**
	 * 同时保存业务数据main&link信息
	 * 
	 * @param mainObj
	 * @param mainModelName
	 * @param linkObj
	 * @param linkModelName
	 * @param startFlag
	 * @param mainServiceBeanId
	 * @return sheetId 工单流水号
	 * @throws Exception
	 */
	/*public String saveMainAndLink(Object mainObj, String mainModelName,
			Object linkObj, String linkModelName, int startFlag,
			String mainServiceBeanId) throws Exception {
		System.out.println("mainServiceBeanId=================88="
				+ mainServiceBeanId);
		IMainService mainService = (IMainService) ApplicationContextHolder
				.getInstance().getBean("MainService");

		String sheetId = null;
		// 现将wps端传输过来的BO对象转换成HashMap
		IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder
				.getInstance().getBean("BusinessFlowAdapterService");
		Object mainModel = businessFlowManager.transferMainObjectToMode(
				mainObj, mainModelName);
		// 获取工单流水号，并保存到main
		// if (startFlag == WPSStaticVariable.STARTFLAG_START) {
		// // 说明该工单不在数据库中存在，获取工单流水号
		// sheetId = mainService.getSheetId();
		// System.out.println("new sheet id is " + sheetId);
		// Method setterMethod = mainModel.getClass().getMethod("setSheetId",
		// new Class[] { String.class });
		// setterMethod.invoke(mainModel, new Object[] { sheetId });
		// }
		Method getterMethod = mainModel.getClass().getMethod("getId",
				new Class[] {});
		Object mainIdObject = getterMethod.invoke(mainModel, new Object[] {});
		String mainId = mainIdObject.toString();

		Object linkModel = businessFlowManager.transferLinkObjectToMode(
				linkObj, linkModelName);
		Method setterMethod = linkModel.getClass().getMethod("setMainId",
				new Class[] { String.class });
		setterMethod.invoke(linkModel, new Object[] { mainId });
		mainService.addMainAndLink(mainModel, linkModel);

		if (sheetId == null || sheetId.equals("")) {
			System.out.println("mainServiceBeanId=================90="
					+ mainServiceBeanId);
			Method getterSheetIdMethod = mainModel.getClass().getMethod(
					"getSheetId", new Class[] {});
			Object sheetIdObject = getterSheetIdMethod.invoke(mainModel,
					new Object[] {});
			sheetId = sheetIdObject.toString();
			System.out.println("sheet id is " + sheetId);
		}

		return sheetId;
	}

	*/

	/**
	 * 保存任务信息
	 * 
	 * @param taskMap
	 * @param taskBeanId
	 * @throws Exception
	 */
	public void saveTask(HashMap taskMap, String taskBeanId)
			throws Exception {
		ITaskService taskService = (ITaskService) ApplicationContextHolder
				.getInstance().getBean(taskBeanId);
		ITask taskModel = taskService.getTaskModelObject();
		HashMap taskBoMap = (HashMap) taskMap.get("task");
		HashMap processTemplateMap = (HashMap) taskMap.get("process");
		HashMap taskObjectMap = (HashMap) taskMap.get("taskBO");

		// 将任务信息放置到任务model对象中
		Method[] superMethods = taskModel.getClass().getSuperclass()
				.getDeclaredMethods();
		Method[] methods = taskModel.getClass().getDeclaredMethods();
		taskModel = (ITask) WPSStaticMethod.setTaskModel(taskModel,
				superMethods, processTemplateMap, taskBoMap, taskObjectMap);
		taskModel = (ITask) WPSStaticMethod.setTaskModel(taskModel, methods,
				processTemplateMap, taskBoMap, taskObjectMap);

		taskService.addTask(taskModel);
	}

	
}
