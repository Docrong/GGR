package com.boco.eoms.sheet.netdata.webapp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.netdata.model.NetDataTask;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-9-21 15:52:09
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ProcessNetdataListDisplaytagDecoratorHelperHN extends TableDecorator {
    public String getSheetId() {
    	Map taskMap = (HashMap) getCurrentRowObject();
        String url = (String) this.getPageContext().getAttribute("url");
        if(url==null)
        	url = this.getUrl(taskMap);
        
        String sheetKey = StaticMethod.nullObject2String(taskMap.get("sheetKey")).trim();
        String taskName = StaticMethod.nullObject2String(taskMap.get("taskName")).trim();
        String id = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String operateRoleId = StaticMethod.nullObject2String(taskMap.get("operateRoleId")).trim();
        String TKID = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String processId = StaticMethod.nullObject2String(taskMap.get("processId")).trim();
        String taskStatus = StaticMethod.nullObject2String(taskMap.get("taskStatus")).trim();
        String preLinkId = StaticMethod.nullObject2String(taskMap.get("preLinkId")).trim();
        String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId")).trim();
        return "<a  href='" + url
                + "?method=showDetailPage&sheetKey=" + sheetKey
                + "&taskId=" + id + "&taskName=" + taskName
				+ "&operateRoleId="+operateRoleId+"&TKID="+TKID
				+ "&piid="+processId
                + "&taskStatus="+taskStatus+"&preLinkId="+preLinkId+"' >" + sheetId + "</a>";
    }
    
    public String getTaskDisplayName(){
    	 Map taskMap = (HashMap) getCurrentRowObject();
     	String name = StaticMethod.nullObject2String(taskMap.get("taskDisplayName")).trim();
     	String subTaskFlag = StaticMethod.nullObject2String(taskMap.get("subTaskFlag")).trim();
     	if(subTaskFlag!=null && subTaskFlag.equals("true")){
     		name = name + "(子任务)";
     	}
     	return name;
    }
    
    public Date getSendTime(){
    	 Map taskMap = (HashMap) getCurrentRowObject();
     	Date sendTime = (Date)taskMap.get("sendTime");    	
     	return sendTime;
    }
    
   
    private String getUrl(Map taskMap){
    	String url = "";
    	
    	
    	return url;
    }
    public String getId(){
    	return ""; 
    }
    /**
     * 超时提醒
     */
    public String getProcessId(){
    	return ""; 
    }
    
    public String addRowClass(){
        Map taskMap = (HashMap) getCurrentRowObject();
        String urlstr = "";
        String overtimeType=StaticMethod.nullObject2String(taskMap.get("overtimeType"));
        String urgent = (taskMap.get("urgent")!= null && StaticMethod.nullObject2String(taskMap.get("urgent")).equals("1014401") ? " highbackground " : ""); 
        if(overtimeType.equals("1")){
        	urlstr="serious" + urgent;
        }else if(overtimeType.equals("2")){
        	urlstr="alert colorrow  " + urgent;
        }
        return urlstr;
    }

}

