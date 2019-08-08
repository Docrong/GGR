package com.boco.eoms.sheet.offlineData.webapp.action;


import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;

public class InfoListDisplaytagDecoratorHelper extends TableDecorator {

    public String getsel() throws Exception {

        OfflineDataInfoList offlineDataInfoList = (OfflineDataInfoList) getCurrentRowObject();
        String id = StaticMethod.nullObject2String(offlineDataInfoList.getId());
        String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

        String str = "<a  href='#' onclick=window.open('" + url
                + "?method=performSelect&id=" + id
                + "&actionForword=infoDetail')" + " >" + "详细信息" + "</a>";
        return str;
    }

    public String getedit() {

        OfflineDataInfoList offlineDataInfoList = (OfflineDataInfoList) getCurrentRowObject();
        String id = StaticMethod.nullObject2String(offlineDataInfoList.getId());
        String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

        String str = "<a  href='#' onclick=window.open('" + url
                + "?method=performEdit&id=" + id
                + "&actionForword=infoEdit')" + " >" + "编辑" + "</a>";
        return str;
    }


    public String getdel() {

        OfflineDataInfoList offlineDataInfoList = (OfflineDataInfoList) getCurrentRowObject();
        String id = StaticMethod.nullObject2String(offlineDataInfoList.getId());
        String url = (String) this.getPageContext().getAttribute("url");
//		url = url.replaceAll("tawSystemAgent.do", "listen.do");
//		System.out.println(url);

        String str = "<a  href='" + url
                + "?method=performDel&id=" + id + "&actionForword=infoList"
                + " ' >" + "删除" + "</a>";
        return str;
    }
}
