/*
 * Created on 2007-8-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.test.service;

import java.util.HashMap;


import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-6 15:17:24
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class WPSBusinessFlowAdapterServiceImplTest extends ConsoleTestCase {
    IBusinessFlowAdapterService service;

    public void testGetWorkflowManager() {
    }

    public void testSetWorkflowManager() {
    }

    /**
     * 测试 getUndoTaskList方法
     *
     */
    public void testGetUndoTasksList(){
      try{     	
       	HashMap filterMap=new HashMap();
       	filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME","BigActivityProcess,AbruptEventProcess");
       	String taskBOName="task";
       	HashMap taskMap=new HashMap();
       	taskMap.put("id", "id");
       	taskMap.put("taskName", "taskName");
       	taskMap.put("taskDisplayName","taskDisplayName");
       	taskMap.put("createTime", "createTime");
       	taskMap.put("taskStatus", "taskStatus");
       	taskMap.put("processId", "processId");
       	taskMap.put("sheetKey", "sheetKey");
       	taskMap.put("sheetId", "sheetId");
       	taskMap.put("title", "title");
       	taskMap.put("acceptTimeLimit", "acceptTimeLimit");
       	taskMap.put("completeTimeLimit", "completeTimeLimit");
    	//List taskList=service.getDoneTasksList(0,100,filterMap,taskBOName,taskMap);
        //assertNotNull(taskList); 
      }
       catch(Exception e){
       	  fail();
       }
    }
    
    public void testTriggerProcess(){
      try{
      	HashMap mainMap=new HashMap();
      	mainMap.put("id","101001");
      	mainMap.put("sheetId","SC-01-070902-000-00001");
        HashMap linkMap=new HashMap();
        linkMap.put("id","102001");
        linkMap.put("mainId","101001");
        HashMap operate=new HashMap();
        HashMap map=new HashMap();
        map.put("main",mainMap);
        map.put("link",linkMap);
        String processName="BigActivityProcess";
        String operateName="newSheet";
        //String piid=service.triggerProcess(processName,operateName,map);
        //assertNotNull(piid);
      }
      catch (Exception e){
      	fail();
      }
      }
    
    public void testCompleteHumanTask(){
    	
    }
    
    /**
     * 测试 getDoneTaskList方法
     *
     */
    public void testGetDoneTasksList(){
      try{     	
       	HashMap filterMap=new HashMap();
       	filterMap.put("PROCESS_INSTANCE.TEMPLATE_NAME","BigActivityProcess,AbruptEventProcess");
       	String taskBOName="Task";
       	HashMap taskMap=new HashMap();
       	taskMap.put("id", "id");
       	taskMap.put("taskName", "taskName");
       	taskMap.put("taskDisplayName","taskDisplayName");
       	taskMap.put("createTime", "createTime");
       	taskMap.put("taskStatus", "taskStatus");
       	taskMap.put("processId", "processId");
       	taskMap.put("sheetKey", "sheetKey");
       	taskMap.put("sheetId", "sheetId");
       	taskMap.put("title", "title");
       	taskMap.put("acceptTimeLimit", "acceptTimeLimit");
       	taskMap.put("completeTimeLimit", "completeTimeLimit");
    	//List taskList=service.getDoneTasksList(0,100,filterMap,taskBOName,taskMap);
        //assertNotNull(taskList); 
      }
       catch(Exception e){
       	  fail();
       }
    }
    public void testGetMain(){
    	try
		{
	    	IMainDAO dao = (IMainDAO)ApplicationContextHolder.getInstance().getBean("MainDAO");
	    	BaseMain main = dao.getMain("2c9e9eee14e9afb80114e9c26ff80023","com.boco.eoms.sheet.security.model.SeResourceAssessMain");
	    	int i=0;
		}catch(Exception e){
			
		}
    }
}
