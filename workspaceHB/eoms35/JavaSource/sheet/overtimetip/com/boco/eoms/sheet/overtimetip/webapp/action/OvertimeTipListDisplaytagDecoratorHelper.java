package com.boco.eoms.sheet.overtimetip.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;


public class OvertimeTipListDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getSpecialty1(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty1 = temForm.getSpecialty1();
        if(specialty1==null||specialty1.equals("")){
        	specialty1="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty1, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty1 = tmpStr;
        	}
        }
        String url = "<a href=overtimetip.do?method=showInputPage&id="+temForm.getId()+"&flowName="+temForm.getFlowName()+"&userId="+temForm.getUserId()+">"+specialty1+ "</a>";
        return url;
        
	}
	public String getSpecialty2(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty2 = temForm.getSpecialty2();
        if(specialty2==null||specialty2.equals("")){
        	specialty2="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty2, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty2 = tmpStr;
        	}
        }
        return specialty2;
	}

	public String getSpecialty3(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty3 = temForm.getSpecialty3();
        if(specialty3==null||specialty3.equals("")){
        	specialty3="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty3, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty3 = tmpStr;
        	}
        }
        return specialty3;
	}

	public String getSpecialty4(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
		
        String specialty4 = temForm.getSpecialty4();
        if(specialty4==null||specialty4.equals("")){
        	specialty4="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty4, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty4 = tmpStr;
        	}
        }
        return specialty4;
	}
	public String getSpecialty5(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty5 = temForm.getSpecialty5();
        if(specialty5==null||specialty5.equals("")){
        	specialty5="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty5, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty5 = tmpStr;
        	}
        }
        return specialty5;
	}
	public String getSpecialty6(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty6 = temForm.getSpecialty6();
        if(specialty6==null||specialty6.equals("")){
        	specialty6="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty6, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty6 = tmpStr;
        	}
        }
        return specialty6;
	}
	public String getSpecialty7(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty7 = temForm.getSpecialty7();
        if(specialty7==null||specialty7.equals("")){
        	specialty7="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty7, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty7 = tmpStr;
        	}
        }
        return specialty7;
	}
	public String getSpecialty8(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty8 = temForm.getSpecialty8();
        if(specialty8==null||specialty8.equals("")){
        	specialty8="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty8, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty8 = tmpStr;
        	}
        }
        return specialty8;
	}
	public String getSpecialty9(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty9 = temForm.getSpecialty9();
        if(specialty9==null||specialty9.equals("")){
        	specialty9="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty9, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty9 = tmpStr;
        	}
        }
        return specialty9;
	}
	public String getSpecialty10(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty10 = temForm.getSpecialty10();
        if(specialty10==null||specialty10.equals("")){
        	specialty10="系统默认设置";
        }else{
        	String tmpStr = service.id2Name(specialty10, "ItawSystemDictTypeDao");
        	if(tmpStr!=null&&!tmpStr.equals("")){
        		specialty10 = tmpStr;
        	}
        }
        return specialty10;
	}
	public String getOvertimeLimit(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		String url = "<a href=overtimetip.do?method=showInputPage&id="+temForm.getId()+"&flowName="+temForm.getFlowName()+"&userId="+temForm.getUserId()+">"+temForm.getOvertimeLimit()+ "</a>";
		return url;
	}
	public String getUserId(){
		OvertimeTip temForm = (OvertimeTip) getCurrentRowObject();
		String userId = temForm.getUserId();
		if(!userId.equals("system")){
			ITawSystemUserManager service = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
			userId = (service.getUserByuserid(userId)).getUsername();
		}else{
			userId = "系统默认设置";
		}
		return userId;
	}

}
