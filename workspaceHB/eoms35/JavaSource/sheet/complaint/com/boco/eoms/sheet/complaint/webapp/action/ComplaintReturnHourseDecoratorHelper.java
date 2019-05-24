package com.boco.eoms.sheet.complaint.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.complaint.model.ComplaintReturnHouse;

public class ComplaintReturnHourseDecoratorHelper extends TableDecorator{

	  public String getsheetkey()
	  {
	    HashMap taskMap = new HashMap();
	    try {
	      taskMap = (HashMap)getCurrentRowObject();
	    } catch (Exception e) {
	      ComplaintReturnHouse crh = (ComplaintReturnHouse)getCurrentRowObject();
	      taskMap = SheetBeanUtils.bean2Map(crh);
	    }
	    String url = (String)getPageContext().getAttribute("url");
	    if (url == null) {
	      url = getUrl(taskMap); 
	    }
	    String sheetkey = StaticMethod.nullObject2String(taskMap.get("sheetkey")).trim();
	    String id = StaticMethod.nullObject2String(taskMap.get("id")).trim();
	    String preLinkId = StaticMethod.nullObject2String(taskMap.get("preLinkId")).trim();
	    String sheetid = StaticMethod.nullObject2String(taskMap.get("sheetid")).trim();
	    String returndealtime = StaticMethod.nullObject2String(taskMap.get("returndealtime")).trim();
	    String count = StaticMethod.nullObject2String(taskMap.get("count")).trim();
	    String returncontext = StaticMethod.nullObject2String(taskMap.get("returncontext")).trim();
	    String dealpartment = StaticMethod.nullObject2String(taskMap.get("dealpartment")).trim();
	    String str = "<a  href='" + url + "?method=showReturnCallPage&id=" + id +  "&sheetid=" + sheetid +
	      "&returndealtime=" + returndealtime +"&count=" + count + "&preLinkId=" + preLinkId + "' >" + sheetkey + "</a>";
	   // System.out.println("-------"+str);
	    return str;
	  }

	  private String getUrl(Map taskMap)
	  {
	    String url = "";

	    return url; }

}
