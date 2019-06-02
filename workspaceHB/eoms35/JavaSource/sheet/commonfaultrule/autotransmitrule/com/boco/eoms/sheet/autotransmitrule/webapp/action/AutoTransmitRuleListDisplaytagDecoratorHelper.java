package com.boco.eoms.sheet.autotransmitrule.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.autotransmitrule.model.AutoTransmitRule;


public class AutoTransmitRuleListDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getNetTypeOne(){
		AutoTransmitRule form = (AutoTransmitRule) getCurrentRowObject();
	    ID2NameService service = ( ID2NameService )ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String netTypeOne = service.id2Name(form.getNetTypeOne(), "ItawSystemDictTypeDao");
		String url = "<a href=autotransmitrule.do?method=showInputPage&id="+ form.getId()+">"+netTypeOne+"</a>";
		return url;
	}
	
	public String getNetTypeTwo(){
		AutoTransmitRule form = (AutoTransmitRule) getCurrentRowObject();
	    ID2NameService service = ( ID2NameService )ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String netTypeTwo = service.id2Name(form.getNetTypeTwo(), "ItawSystemDictTypeDao");
		return netTypeTwo;
	}
	
	public String getNetTypeThree(){
		AutoTransmitRule form = (AutoTransmitRule) getCurrentRowObject();
	    ID2NameService service = ( ID2NameService )ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String netTypeThree = service.id2Name(form.getNetTypeThree(), "ItawSystemDictTypeDao");
		return netTypeThree;
	}
	public String getRoleId(){
		AutoTransmitRule form = (AutoTransmitRule)getCurrentRowObject();
		ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String roleName = service.id2Name(form.getRoleId(), "tawSystemSubRoleDao");
		System.out.println("form.getRoleId() ==========="+form.getRoleId());
		return roleName;
	}
	public String getDomainId(){
		AutoTransmitRule form = (AutoTransmitRule)getCurrentRowObject();
		ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String domainName = service.id2Name(form.getDomainId(), "tawSystemAreaDao");
		return domainName;
	}
	public String getEquipmentFactory(){
		AutoTransmitRule form = (AutoTransmitRule)getCurrentRowObject();
		ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String equipmentFactory = form.getEquipmentFactory();
		if(equipmentFactory!= null && !equipmentFactory.equals("")){
			equipmentFactory = service.id2Name(equipmentFactory, "ItawSystemDictTypeDao");
		}
		
		return equipmentFactory;
   }
	public String getFaultResponseLevel(){
		AutoTransmitRule form = (AutoTransmitRule)getCurrentRowObject();
		ID2NameService service = (ID2NameService)ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		String faultResponseLevel = form.getFaultResponseLevel();
		if(faultResponseLevel!=null && !faultResponseLevel.equals("")){
			faultResponseLevel = service.id2Name(faultResponseLevel, "ItawSystemDictTypeDao");
		}
		
		return faultResponseLevel;
	}
	public String getStopTime(){
		AutoTransmitRule form = (AutoTransmitRule)getCurrentRowObject();
		String stopTime = form.getStopTime();
		return stopTime;
	}
}
