package com.boco.eoms.businessupport.serviceprepare.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;


public class ListCommonDisplaytagDecoratorHelper  extends TableDecorator {

   public String getprofessionalServiceDirectory_name(){
   	String returnUrl="";
    Map taskMap = (HashMap) getCurrentRowObject();
    String url = (String) this.getPageContext().getAttribute("url");
    String id = StaticMethod.nullObject2String(taskMap.get("serviceConfiguration_id")).trim();   
    String professionalServiceDirectory_name = StaticMethod.nullObject2String(taskMap.get("professionalServiceDirectory_name")).trim();       
    returnUrl="<a  href='" + url+ "?method=showServiceConfigurationDetailPage&id=" + id+"&type=show' >" + professionalServiceDirectory_name + "</a>";
    return returnUrl;
   }	
}
