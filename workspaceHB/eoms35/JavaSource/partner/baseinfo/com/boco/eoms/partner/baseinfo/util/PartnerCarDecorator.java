package com.boco.eoms.partner.baseinfo.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.model.TawPartnerCar;

public class PartnerCarDecorator extends TableDecorator {

	public String getPiece() {
		TawPartnerCar tawPartnerCarForm = (TawPartnerCar) getCurrentRowObject();
		ID2NameService iD2NameService=(ID2NameService)ApplicationContextHolder.getInstance().getBean("id2nameService");
		String str=tawPartnerCarForm.getPiece();
		String [] temp = str.split("'");
		String chinese="";
		for(int i=0;i<temp.length;i++){
			chinese+=iD2NameService.id2Name(temp[i], "ItawSystemDictTypeDao")+",";
			
		}
		chinese =chinese.substring(0, (chinese.length()-1));
		String ff = chinese.charAt(chinese.length()-1)+"";
		if(",".equals(ff)){
			chinese =chinese.substring(0, (chinese.length()-1));
		}
		return chinese;
	}
	public String getArea_id(){
		TawPartnerCar tawPartnerCarForm = (TawPartnerCar) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawPartnerCarForm.getArea_id());
		return area_name;
	}
	public String getDept_id(){
		TawPartnerCar tawPartnerCarForm = (TawPartnerCar) getCurrentRowObject();
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) ApplicationContextHolder.getInstance().getBean("areaDeptTreeMgr");
		String area_name = areaDeptTreeMgr.id2Name(tawPartnerCarForm.getDept_id());
		return area_name;
		
	}
}
