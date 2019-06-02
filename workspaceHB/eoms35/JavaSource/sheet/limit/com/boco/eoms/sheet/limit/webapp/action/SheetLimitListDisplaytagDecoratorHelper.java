package com.boco.eoms.sheet.limit.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;


public class SheetLimitListDisplaytagDecoratorHelper extends TableDecorator{
	public String getLevel1() {
		LevelLimit form = (LevelLimit) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String level1 = service.id2Name(form.getLevel1(), "ItawSystemDictTypeDao");
        String url = "sheetLimit.do?method=editLevelLimit&id="+form.getId()+"&flowName="+form.getFlowName();
        return "<a href="+url+">"+level1+ "</a>";
    }
	public String getLevel2() {
		LevelLimit form = (LevelLimit) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String level2 = service.id2Name(form.getLevel2(), "ItawSystemDictTypeDao");
        return level2;
    }
	public String getLevel3() {
		LevelLimit form = (LevelLimit) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String level3 = service.id2Name(form.getLevel3(), "ItawSystemDictTypeDao");
        return level3;
    }
	public String getSpecialty1() {
		LevelLimit form = (LevelLimit) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty1 = service.id2Name(form.getSpecialty1(), "ItawSystemDictTypeDao");
        String url = "sheetLimit.do?method=editLevelLimit&id="+form.getId()+"&flowName="+form.getFlowName();
        return "<a href="+url+">"+specialty1+ "</a>";
    }
	
	public String getSpecialty2(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty2 = service.id2Name(temForm.getSpecialty2(), "ItawSystemDictTypeDao");
        return specialty2;
        
	}
	public String getSpecialty3(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty3 = service.id2Name(temForm.getSpecialty3(), "ItawSystemDictTypeDao");
        return specialty3;
	}

	public String getSpecialty4(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty4 = service.id2Name(temForm.getSpecialty4(), "ItawSystemDictTypeDao");
        return specialty4;
	}

	public String getSpecialty5(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty5 = service.id2Name(temForm.getSpecialty5(), "ItawSystemDictTypeDao");
        return specialty5;
	}
	public String getSpecialty6(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty6 = service.id2Name(temForm.getSpecialty6(), "ItawSystemDictTypeDao");
        return specialty6;
	}
	public String getSpecialty7(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty7 = service.id2Name(temForm.getSpecialty7(), "ItawSystemDictTypeDao");
        return specialty7;
	}
	public String getSpecialty8(){
		LevelLimit temForm = (LevelLimit) getCurrentRowObject();
		ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String specialty8 = service.id2Name(temForm.getSpecialty8(), "ItawSystemDictTypeDao");
        return specialty8;
	}
    public String getFlowName(){
    	LevelLimit temForm = (LevelLimit) getCurrentRowObject();
    	String flowName = temForm.getFlowName();
         return flowName;
    }
    public String getAcceptLimit(){
    	LevelLimit temForm = (LevelLimit) getCurrentRowObject();
    	String url = "sheetLimit.do?method=editLevelLimit&id="+temForm.getId()+"&flowName="+temForm.getFlowName();
         return "<a href="+url+">"+temForm.getAcceptLimit()+ "</a>";
    }
    public String getReplyLimit(){
    	LevelLimit temForm = (LevelLimit) getCurrentRowObject();
         return temForm.getReplyLimit();
    }
    public String getStepLimitList(){
    	LevelLimit temForm = (LevelLimit) getCurrentRowObject();
    	String url = "sheetLimit.do?method=search&levelId="+temForm.getId();
    	return "<a onclick=\"javascript:getUrl('"+url+"')\">查看</a>";
    }
}
