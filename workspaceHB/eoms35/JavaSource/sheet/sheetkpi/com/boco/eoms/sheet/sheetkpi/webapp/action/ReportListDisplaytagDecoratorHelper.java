package com.boco.eoms.sheet.sheetkpi.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;

public class ReportListDisplaytagDecoratorHelper extends TableDecorator {


    public ReportListDisplaytagDecoratorHelper() {

    }

    public String getTABLEB() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B1";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tableb = StaticMethod.nullObject2String(taskMap.get("TABLEB")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tableb + "</a>";
    }


    public String getTABLEC() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B2";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tablec = StaticMethod.nullObject2String(taskMap.get("TABLEC")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tablec + "</a>";
    }

    public String getTABLED() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B3";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tabled = StaticMethod.nullObject2String(taskMap.get("TABLED")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tabled + "</a>";
    }

    public String getTABLEE() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B4";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tablee = StaticMethod.nullObject2String(taskMap.get("TABLEE")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tablee + "</a>";
    }

    public String getTABLEF() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B5";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tablef = StaticMethod.nullObject2String(taskMap.get("TABLEF")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tablef + "</a>";
    }

    public String getTABLEG() {
        Map taskMap = (HashMap) getCurrentRowObject();
        String filename = (String) getPageContext().getAttribute("filename");
        String url = (String) getPageContext().getAttribute("url");
        String b1 = "_B6";
        String sendTimeStartDate = (String) getPageContext().getAttribute("sendTimeStartDate");
        String sendTimeEndDate = (String) getPageContext().getAttribute("sendTimeEndDate");
        String tableg = StaticMethod.nullObject2String(taskMap.get("TABLEG")).trim();
        String deptid = StaticMethod.nullObject2String(taskMap.get("DEPTID")).trim();
        StringBuffer url1 = new StringBuffer(url);
        String newurl = url1.append(filename).append(b1).toString();
        return "<a  href='" + newurl + "&deptid=" + deptid + "&sendTimeStartDate=" + sendTimeStartDate + "&sendTimeEndDate=" + sendTimeEndDate + "' >" + tableg + "</a>";
    }
}
