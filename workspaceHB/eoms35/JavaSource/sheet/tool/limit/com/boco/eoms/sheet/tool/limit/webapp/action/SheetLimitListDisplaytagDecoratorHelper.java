package com.boco.eoms.sheet.tool.limit.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.sheet.tool.limit.model.SheetLimit;


public class SheetLimitListDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getDeptId() {
		SheetLimit form = (SheetLimit) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String deptName = service.id2Name(form.getDeptId(), "tawSystemDeptDao");
        return deptName;
    }
	
	public String getSpecialty1(){
		SheetLimit temForm = (SheetLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty1 = service.id2Name(temForm.getSpecialty1(), "ItawSystemDictTypeDao");
        String url = "editSheetLimit.do";
        return "<a href="+url+"?id="+temForm.getId()+">"+specialty1+ "</a>";
        
	}
	public String getSpecialty2(){
		SheetLimit temForm = (SheetLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty2 = service.id2Name(temForm.getSpecialty2(), "ItawSystemDictTypeDao");
        return specialty2;
	}

	public String getSpecialty3(){
		SheetLimit temForm = (SheetLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty3 = service.id2Name(temForm.getSpecialty3(), "ItawSystemDictTypeDao");
        return specialty3;
	}

	public String getSpecialty4(){
		SheetLimit temForm = (SheetLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty4 = service.id2Name(temForm.getSpecialty4(), "ItawSystemDictTypeDao");
        return specialty4;
	}             
    public String getMoudleId(){
    	SheetLimit temForm = (SheetLimit) getCurrentRowObject();
    	String moudleId = temForm.getMoudleId();
    	String tempName="";
    	if( moudleId.equals("2")){
    		tempName="应急与事件管理流程";
       	}else if(moudleId.equals("3")){
       		tempName="网络优化流程";
    	}else if(moudleId.equals("4")){
    		tempName="供应商管理流程";
    	}else if(moudleId.equals("5")){
    		tempName="紧急故障管理流程";
    	}
    	 String url = "editSheetLimit.do";
         return "<a href="+url+"?id="+temForm.getId()+">"+tempName+ "</a>";
       
    }
    public String getRoleName(){
    	SheetLimit tempForm = (SheetLimit)getCurrentRowObject();
    	ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
    	String roleName = service.id2Name(tempForm.getRoleName(), "ItawSystemDictTypeDao");
    	return roleName;
    }
    public String getStepId(){
    	SheetLimit tempForm = (SheetLimit)getCurrentRowObject();
    	ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
    	String stepName = service.id2Name(tempForm.getStepId(), "ItawSystemDictTypeDao");
    	return stepName;
    }

}
