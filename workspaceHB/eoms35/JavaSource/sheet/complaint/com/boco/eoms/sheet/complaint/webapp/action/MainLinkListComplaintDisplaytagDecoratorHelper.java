package com.boco.eoms.sheet.complaint.webapp.action;


import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;


public class MainLinkListComplaintDisplaytagDecoratorHelper extends TableDecorator {

    public String getSheetid() {

        Map main = (Map) getCurrentRowObject();
        String url = (String) this.getPageContext().getAttribute("url");


        String sheetid = StaticMethod.nullObject2String(main.get("sheetId")).trim();
        return "<a  href='" + url
                + "?method=showMainDetailPage&sheetKey=" + StaticMethod.nullObject2String(main.get("id")).trim()
                + "' target=_blank>" + sheetid + "</a>";
    }

}
