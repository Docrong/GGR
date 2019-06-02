/*
 * Created on 2007-9-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.ejb.bo;

import java.util.HashMap;

import com.boco.eoms.ejb.service.SaveDataObjectImpl;
/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SaveDataObjectBO {

	private static SaveDataObjectBO objectBO = null;
	

	private SaveDataObjectBO(){
	    
	}
	
	public static SaveDataObjectBO getInstance() {
		if(objectBO==null){
			objectBO=init();
		}
		return objectBO;
	}

	private static SaveDataObjectBO init(){
	   objectBO=new SaveDataObjectBO();	  
	   return objectBO;
	}
	
//	public HashMap saveMain(Object main, String modelName,String mainServiceBeanId,int startFlag) throws Exception {
//		SaveDataObjectImpl object = new SaveDataObjectImpl();
//		return object.saveMain(main, modelName,mainServiceBeanId,startFlag);
//	}
	
	public void saveMain(Object main, String modelName) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.saveMain(main, modelName);
	}
	
	public String saveLink(Object link, String modelName) throws Exception {
		 SaveDataObjectImpl object = new SaveDataObjectImpl();
         return object.saveLink(link, modelName);
	}
	public void saveTask(HashMap taskMap, String taskBeanId) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.saveTask(taskMap,taskBeanId);
	}
	/*public String saveMainAndLink(Object main, String mainModelName,
			   Object link, String linkModelName) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		String sheetId=object.saveMainAndLink(main, mainModelName,link,linkModelName);
		return sheetId;
	} */
	
	public void saveOrUpdateMain(Object main, String modelName) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.saveOrUpdateMain(main, modelName);
	}
	
	public String saveOrUpdateLink(Object link, String modelName) throws Exception {
		 SaveDataObjectImpl object = new SaveDataObjectImpl();
         return object.saveOrUpdateLink(link, modelName);
	}
	public void saveOrUpdateTask(HashMap taskMap, String taskBeanId) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.saveOrUpdateTask(taskMap,taskBeanId);
	}
	
	public void updateMain(HashMap mainMap,String mainBeanId) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateMain(mainMap,mainBeanId);
	}
	public void updateLink(HashMap linkMap,String linkBeanId) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateLink(linkMap,linkBeanId);
	}
	public void updateTask(HashMap taskMap,String taskBeanId) throws Exception {
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateTask(taskMap,taskBeanId);
	}
	
	
	public void updateTaskByStatus(String sheetKey,String taskBeanId) throws Exception{
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateTaskByStatus(sheetKey,taskBeanId);
	}
	
	public void updateTaskBySubTaskFlag(String taskId,String subTaskFlag,String taskBeanId) throws Exception{
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateTaskBySubTaskFlag(taskId,subTaskFlag,taskBeanId);
	}
	
	public void updateSheetRelationState(String sheetKey) throws Exception{
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateSheetRelationState(sheetKey);
	}
	
	public void updateTaskState(String taskId,String taskState,String taskBeanId) throws Exception{
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.updateTaskState(taskId, taskState, taskBeanId);
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
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		object.invokeWfInterface(mainBeanId,sheetKey,linkId,linkBeanId,interfaceType,methodType,sendType);
	}
	public String getUUID() throws Exception{
		SaveDataObjectImpl object = new SaveDataObjectImpl();
		return object.getUUID();
	}

}
