package com.boco.eoms.duty.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.AttemperContrast;

public class DutyAttemperDisplayDecorator extends TableDecorator {
	
	// 显示查看网调信息字眼
	public String getViewAttemperInfo() {
		AttemperContrast attemperContrast = (AttemperContrast)getCurrentRowObject();
		String url =  new String();
		url += "<a href='attempers.do?method=view&id="+attemperContrast.getAttemperId() + "' "
			+  "title='查看网调信息'>"
			+  attemperContrast.getAttemper().getSheetId()
			+ "</a>";
		return url;
	}
	
	// 显示查看网调子过程信息字眼
	public String getViewAttemperSubInfo() {
		AttemperContrast attemperContrast = (AttemperContrast)getCurrentRowObject();
		String url =  new String();
		url += "<a href='attemperSubs.do?method=view&id="+attemperContrast.getSubAttemperId() + "' "
			+  "title='查看网调子过程信息'>"
			+  attemperContrast.getAttemper().getAttemperSub().getIntendBeginTime()
			+ "</a>";
		return url;
	}
	
	// 显示查看对比表信息字眼
	public String getViewAttemperContrastInfo() {
		Attemper attemper = (Attemper)getCurrentRowObject();
		String url =  new String();

		try {
		url += "<a href='attemperContrasts.do?method=add&subAttemperId="+attemper.getAttemperSub().getId() 
			+ "&attemperId="+attemper.getId()+"&beginTime="+attemper.getAttemperSub().getIntendBeginTime()
			+ "&endTime="+attemper.getAttemperSub().getIntendEndTime() + "' "
			+ "title='生成对比表,已有 " + attemper.getAttemperSub().getIfContrastReport() + "个对比表!'>"
			+ (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId("dict-duty","attemperSpeciality"), attemper.getSpeciality())
			+ "(" + (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId("dict-duty","attemperSpecialitySub"), attemper.getSubSpeciality())+ ")"
			+ "</a>";
		} catch(DictServiceException e) {
			e.printStackTrace();
		}
		return url;
	}

}
