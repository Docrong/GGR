package com.boco.eoms.partner.baseinfo.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;
public class PartnerOilDecorator extends TableDecorator {

	
	public String getArea_id(){
		TawPartnerOil tawPartnerOilForm = (TawPartnerOil) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawPartnerOilForm.getArea_id());
		return area_name;
	}
	public String getDept_id(){
		TawPartnerOil tawPartnerOilForm = (TawPartnerOil) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawPartnerOilForm.getDept_id());
		return area_name;
		
	}
}
