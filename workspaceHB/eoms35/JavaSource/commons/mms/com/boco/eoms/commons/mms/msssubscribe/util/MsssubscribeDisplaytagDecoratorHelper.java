package com.boco.eoms.commons.mms.msssubscribe.util;


import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;
import com.boco.eoms.commons.statistic.base.util.StatUtil;


public class MsssubscribeDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getShowDetail()
	{
		Msssubscribe vo = (Msssubscribe) getCurrentRowObject();
		
		String url = (String) this.getPageContext().getAttribute("url");
		this.getPageContext().getRequest().getContentType();

		String reURL = "";
		
			reURL = "<a onclick=window.open('" + url
			+ "?method=edit&id=" + vo.getId()
			+ "'); href='#'>" + "查看" + "</a>";

		return reURL;
	}
	
	public String getShowDelete() {
		
		Msssubscribe vo = (Msssubscribe) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
		reURL = "<a onclick=openSheet('" + url
		+ "?method=remove&id=" + vo.getId()
		+ "'); href='#'>" + "删除" + "</a>";
			
		return reURL;
	}
	public String getShowModify() {
		
		Msssubscribe vo = (Msssubscribe) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
		reURL = "<a onclick=window.open('" + url
		+ "?method=modify&id=" + vo.getId()
		+ "'); href='#'>" + "修改" + "</a>";

	return reURL;
	}
	
	public String getReceivePerson()
	{
		Msssubscribe vo = (Msssubscribe) getCurrentRowObject();
		String[] p = vo.getReceivePerson().split(",");
		return getReceivePerson(p);
	}
	
	public static String getReceivePerson(String[] p)
	{
		String rp = "";
		for(int i=0;i<p.length;i++)
		{
			if(!rp.equalsIgnoreCase(""))
			{
				rp += ",";
			}
			
			rp += StatUtil.id2Name(p[i], "statBaseUserId2name_v35");
		}
		
		return rp;
	}
}
