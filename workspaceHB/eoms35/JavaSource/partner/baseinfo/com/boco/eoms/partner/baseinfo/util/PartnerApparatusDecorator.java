package com.boco.eoms.partner.baseinfo.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.model.TawApparatus;

public class PartnerApparatusDecorator extends TableDecorator {

	
	public String getArea_id(){
		TawApparatus tawApparatusForm = (TawApparatus) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawApparatusForm.getArea_id());
		return area_name;
	}
	public String getDept_id(){
		TawApparatus tawApparatusForm = (TawApparatus) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawApparatusForm.getDept_id());
		return area_name;
		
	}
}
