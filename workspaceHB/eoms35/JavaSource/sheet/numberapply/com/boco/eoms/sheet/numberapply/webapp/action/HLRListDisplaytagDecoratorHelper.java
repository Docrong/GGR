package com.boco.eoms.sheet.numberapply.webapp.action;


import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMain;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchMscManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyMainManager;

public class HLRListDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getsel() throws Exception {

		NumberApplyHlrid numberApplyHlrid = (NumberApplyHlrid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyHlrid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str ="<a  href='#' onclick=window.open('" + url
				+ "?method=performSelect&id=" + id
				+ "&actionForword=hlrdetail')" + " >" + "详细信息"+ "</a>";
		return str;
	}
	
	public String getedit() {

		NumberApplyHlrid numberApplyHlrid = (NumberApplyHlrid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyHlrid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str ="<a  href='#' onclick=window.open('" + url
			+ "?method=performEdit&id=" + id
			+ "&actionForword=hlrnew')" + " >" + "编辑"+ "</a>";
	return str;
	}
	

	public String getdel() {

		NumberApplyHlrid numberApplyHlrid = (NumberApplyHlrid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyHlrid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str = "<a  href='" + url
			+ "?method=performDel&id=" + id + "&actionForword=hlrlist"
			+ " ' >" + "删除"+ "</a>";
	return str;
	}
	
	public String getmanual() {

		NumberApplyHlrid numberApplyHlrid = (NumberApplyHlrid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyHlrid.getId());
		String commond24 = StaticMethod.nullObject2String(numberApplyHlrid.getCommandCode24());
		String commond14 = StaticMethod.nullObject2String(numberApplyHlrid.getCommandCode14());
		String commondid = StaticMethod.nullObject2String(numberApplyHlrid.getHlrId());
		String sheetKey = StaticMethod.nullObject2String(numberApplyHlrid.getMainid());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str ="<a  href='#' onclick=window.open('" + url
			+ "?method=showManual&id=" + id +"&commond24="+commond24+"&commond14="+commond14+"&commondid="+commondid+ "&sheetKey=" + sheetKey + "&actionForword=hlrlist')"
			+ "  >" + "手动配置"+ "</a>";
	return str;
	}
}
