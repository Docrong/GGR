package com.boco.eoms.sheet.proxy.webapp.action;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.sheet.proxy.model.Proxy;

public class ProxyListDisplaytagDecoratorHelper extends TableDecorator {
	public String getUserId() {
		Proxy form = (Proxy) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(form.getUserId(), "tawSystemUserDao");
      //  String url = (String) this.getPageContext().getAttribute("url");
        String url = "editSheetProxy.do";
        System.out.println("name is ====================="+name+"url====================================="+url);
        return "<a href="+url+"?id="+form.getId()+">"+name+ "</a>";
    }
	
	public String getProxyUser() {
		Proxy form = (Proxy) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String proxyname = service.id2Name(form.getProxyUser(), "tawSystemUserDao");
        return proxyname;
    }
}
