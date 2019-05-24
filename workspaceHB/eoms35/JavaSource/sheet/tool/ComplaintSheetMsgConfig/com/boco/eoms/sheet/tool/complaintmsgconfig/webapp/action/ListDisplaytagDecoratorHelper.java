package com.boco.eoms.sheet.tool.complaintmsgconfig.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig;

public class ListDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getNotifyUserIds(){
		String userNames="";
		try{
		ComplaintSheetMsgConfig config = (ComplaintSheetMsgConfig) getCurrentRowObject();
		String userIds=StaticMethod.nullObject2String(config.getNotifyUserIds());
        String[] userIdList=userIds.split(",");
        ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
			      .getInstance().getBean("itawSystemUserManager");
        for(int i=0;i<userIdList.length;i++){
          String tempUserId=userIdList[i];
          String tempUserName=mgr.id2Name(tempUserId);
          userNames=userNames+tempUserName+",";
        }
        if(!userNames.equals("")) userNames=userNames.substring(0, userNames.lastIndexOf(","));
		}catch(Exception e){
			
		}
		return userNames;
	}

}
