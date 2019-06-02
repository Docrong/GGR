package com.boco.eoms.businessupport.serviceprepare.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;


public class ListPSCommonDisplaytagDecoratorHelper  extends TableDecorator {
	   public String getName(){
		   	String returnUrl="";
		   	ProductSpecification productSpecification = (ProductSpecification) getCurrentRowObject();
		    String url = (String) this.getPageContext().getAttribute("url");
		    String id = StaticMethod.nullObject2String(productSpecification.getId()).trim();    
		    String name = StaticMethod.nullObject2String(productSpecification.getName()).trim(); 
		    returnUrl="<a  href='" + url+ "?method=showEditProductSpecification&id=" + id+"' >" + name + "</a>";
		    return returnUrl;
		   }	
}
