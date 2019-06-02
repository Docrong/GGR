// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryListHBDisplaytagDecoratorHelper.java

package com.boco.eoms.sheet.commonfault.webapp.action;

import com.boco.eoms.sheet.base.model.BaseMain;
import java.util.Date;
import javax.servlet.jsp.PageContext;
import org.displaytag.decorator.TableDecorator;

public class QueryListHBDisplaytagDecoratorHelper extends TableDecorator
{

	public QueryListHBDisplaytagDecoratorHelper()
	{
	}

	public String getId()
	{
		BaseMain main = (BaseMain)getCurrentRowObject();
		return "<input type='checkbox' id='" + main.getPiid() + "' name='ids' value='" + main.getPiid() + "&" + main.getSheetId() + "'>";
	}

	public String getTitle()
	{
		BaseMain main = (BaseMain)getCurrentRowObject();
		String temTitle = main.getTitle();
		return temTitle;
	}

	public String getSheetId()
	{
		BaseMain main = (BaseMain)getCurrentRowObject();
		String url = (String)getPageContext().getAttribute("url");
		return "<a  href='" + url + "?method=showMainDetailPage&sheetKey=" + main.getId() + "' target=_blank>" + main.getSheetId() + "</a>";
	}

	public Date getSendTime()
	{
		BaseMain main = (BaseMain)getCurrentRowObject();
		Date sendTime = main.getSendTime();
		return sendTime;
	}

	public String getPiid()
	{
		BaseMain main = (BaseMain)getCurrentRowObject();
		String url = (String)getPageContext().getAttribute("url");
		if (main.getStatus().equals(new Integer(0)))
			return "<a  href='" + url + "?method=showCancelInputPage&piid=" + main.getPiid() + "&sheetKey=" + main.getId() + "' target=_blank>" + "³·Ïû" + "</a>";
		else
			return "";
	}
}
