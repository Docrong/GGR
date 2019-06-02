/*
 * Created on 2007-9-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.ejb.service;

import java.util.HashMap;

import javax.naming.InitialContext;

import com.boco.eoms.sheet.base.service.ejb.SaveDataService;
import com.boco.eoms.sheet.base.service.ejb.SaveDataServiceHome;
 
/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SaveDataObjectImpl  {
	 private SaveDataService saveDataService;
     
	 public SaveDataObjectImpl(){}
	/**
	 * @return Returns the saveDataService.
	 */

	public SaveDataService getSaveDataService() throws Exception {
		InitialContext initialContext = new InitialContext();
		SaveDataServiceHome home 
		    = (SaveDataServiceHome)initialContext.lookup("ejb/com/boco/eoms/sheet/base/service/ejb/SaveDataServiceHome");
		saveDataService = home.create();
		//"ejb/com/boco/eoms/sheet/base/service/ejb/SaveDataServiceHome";
		return saveDataService;
	} 

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.ejb.service.ISaveDataObject#saveMain(commonj.sdo.DataObject,
	 *      java.lang.String)
	 */
	public void saveMain(Object main, String modelName) throws Exception {
		// TODO Auto-generated method stub
		//this.getSaveDataService().init(mainServiceBeanId);
		this.getSaveDataService().saveMain(main, modelName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.ejb.service.ISaveDataObject#saveMain(commonj.sdo.DataObject,
	 *      java.lang.String)
	 */
//	public HashMap saveMain(Object main, String modelName,String mainServiceBeanId,int startFlag) throws Exception {
//		// TODO Auto-generated method stub
//		this.getSaveDataService().init(mainServiceBeanId);
//		HashMap mainMap = this.getSaveDataService().saveMain(main, modelName,startFlag, mainServiceBeanId);
//		return mainMap;
//	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.ejb.service.ISaveDataObject#saveLink(commonj.sdo.DataObject,
	 *      java.lang.String)
	 */
	public String saveLink(Object link, String modelName) throws Exception {
		return this.getSaveDataService().saveLink(link, modelName);
	}
	 
	/*public String saveMainAndLink(Object main, String mainModelName,
			Object link, String linkModelName) throws Exception {
		// TODO Auto-generated method stub
		//this.getSaveDataService().init(mainServiceBeanId);
		String sheetId=this.getSaveDataService().saveMainAndLink(main,mainModelName,link, linkModelName);
		return sheetId;
	}*/
	
	public void saveTask(HashMap taskMap,String taskBeanId) throws Exception {
		this.getSaveDataService().saveTask(taskMap, taskBeanId);
	}
	
	public void saveOrUpdateMain(Object main, String modelName) throws Exception {
		// TODO Auto-generated method stub
		//this.getSaveDataService().init(mainServiceBeanId);
		this.getSaveDataService().saveOrUpdateMain(main, modelName);
	}
	public String saveOrUpdateLink(Object link, String modelName) throws Exception {
		return this.getSaveDataService().saveOrUpdateLink(link, modelName);
	}
	public void saveOrUpdateTask(HashMap taskMap,String taskBeanId) throws Exception {
		this.getSaveDataService().saveOrUpdateTask(taskMap, taskBeanId);
	}
	
	public void updateMain(HashMap mainMap,String mainBeanId) throws Exception {
		this.getSaveDataService().updateMain(mainMap,mainBeanId);
	}
	public void updateLink(HashMap linkMap,String linkBeanId) throws Exception {
		this.getSaveDataService().updateLink(linkMap,linkBeanId);
	}
	public void updateTask(HashMap taskMap,String taskBeanId) throws Exception {
		this.getSaveDataService().updateTask(taskMap,taskBeanId);
	}
	
	public void updateTaskByStatus(String sheetKey,String taskBeanId) throws Exception{
		this.getSaveDataService().updateTaskByStatus(sheetKey,taskBeanId);
	}
	
	public void updateTaskBySubTaskFlag(String taskId,String subTaskFlag,String taskBeanId) throws Exception{
		this.getSaveDataService().updateTaskBySubTaskFlag(taskId,subTaskFlag,taskBeanId);
	}
	
	public void updateSheetRelationState(String sheetKey) throws Exception{
		this.getSaveDataService().updateSheetRelationState(sheetKey);
	}
	
	public void updateTaskState(String taskId,String taskState,String taskBeanId) throws Exception{
		this.getSaveDataService().updateTaskState(taskId, taskState, taskBeanId);
	}
	/**
	 * 调用接口
	 * @param mainBeanId
	 * @param sheetKey
	 * @param linkId
	 * @param linkBeanId
	 * @param interfaceType
	 * @param methodType
	 * @return
	 */
	public void invokeWfInterface(String mainBeanId,String sheetKey,String linkId,String linkBeanId,String interfaceType,String methodType,String sendType)throws Exception{
		this.getSaveDataService().invokeWfInterface(mainBeanId,sheetKey,linkId,linkBeanId,interfaceType,methodType,sendType);
	}
	public String getUUID() throws Exception{
		return this.getSaveDataService().getUUID();
	}

	
}
