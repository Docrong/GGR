package com.boco.eoms.sheet.numberapply.webapp.action;


import org.displaytag.decorator.TableDecorator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.numberapply.model.NumberApplyHlrid;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMscid;

public class MSCListDisplaytagDecoratorHelper  extends TableDecorator {
	
	public String getsel() {

		NumberApplyMscid numberApplyMscid = (NumberApplyMscid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyMscid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str ="<a  href='#' onclick=window.open('" + url
			+ "?method=performEdit&id=" + id
			+ "&actionForword=mscdetail')" + " >" + "详细信息"+ "</a>";
		 return str;
	}
	
	public String getedit() {

		NumberApplyMscid numberApplyMscid = (NumberApplyMscid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyMscid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str ="<a  href='#' onclick=window.open('" + url
			+ "?method=performEdit&id=" + id
			+ "&actionForword=mscnew')" + " >" + "编辑"+ "</a>";
		 return str;
	}
	public String getdel() {

		NumberApplyMscid numberApplyMscid = (NumberApplyMscid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyMscid.getId());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

		 String str = "<a  href='" + url
			+ "?method=performDel&id=" + id + "&actionForword=msclist"
			+ " ' >" + "删除"+ "</a>";
	return str;
	}
	
	public String getmanual() {

		NumberApplyMscid numberApplyMscid = (NumberApplyMscid)getCurrentRowObject();
		String id = StaticMethod.nullObject2String(numberApplyMscid.getId());
		String commond24 = StaticMethod.nullObject2String(numberApplyMscid.getCommandCode24());
		String commond14 = StaticMethod.nullObject2String(numberApplyMscid.getCommandCode14());
		String commondid = StaticMethod.nullObject2String(numberApplyMscid.getMscId());
		String sheetKey = StaticMethod.nullObject2String(numberApplyMscid.getMainid());
		String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);
		 String str ="<a  href='#' onclick=window.open('" + url
			+ "?method=showManual&id=" + id+"&commond24="+commond24+"&commond14="+commond14+"&commondid="+commondid + "&sheetKey=" + sheetKey + "&actionForword=msclist')"
			+ " >" + "手动配置"+ "</a>";
	return str;
	}
}
