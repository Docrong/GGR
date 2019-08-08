﻿<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.boco.eoms.commons.statistic.base.util.StatUtil" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String excelFilePath = request.getAttribute("excelFilePath").toString();
    String excelFileName = request.getAttribute("excelFileName").toString();
    String dataUrl = request.getAttribute("dataUrl").toString();
    String path = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    List statlist = (List) request.getAttribute("STATLIST");
    int size = statlist.size();
    String xml = "";
    StringBuffer xmlBuf = new StringBuffer();
    xmlBuf.append("<graph caption='第三层工单类柱状图形报表'  decimalPrecision='0' formatNumberScale='0'>");
    xmlBuf.append("<categories>");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String s11 = StatUtil.id2Name((String) map.get("s1"), "statDictId2name_v35");
        xmlBuf.append("<category name='" + s11 + "' />");
    }
    xmlBuf.append("</categories>");
    xmlBuf.append("<dataset seriesname='待受理' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f1 = (String) map.get("f1");
        String dataUrlf1 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f1", "UTF-8");
        xmlBuf.append("<set value='" + f1 + "' link='n-" + dataUrlf1 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='待处理' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f2 = (String) map.get("f2");
        String dataUrlf2 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f2", "UTF-8");
        xmlBuf.append("<set value='" + f2 + "' link='n-" + dataUrlf2 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='待归档' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f3 = (String) map.get("f3");
        String dataUrlf3 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f3", "UTF-8");
        xmlBuf.append("<set value='" + f3 + "' link='n-" + dataUrlf3 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='2小时超时预警' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f4 = (String) map.get("f4");
        String dataUrlf4 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f4", "UTF-8");
        xmlBuf.append("<set value='" + f4 + "' link='n-" + dataUrlf4 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='1小时超时预警' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f5 = (String) map.get("f5");
        String dataUrlf5 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f5", "UTF-8");
        xmlBuf.append("<set value='" + f5 + "' link='n-" + dataUrlf5 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='30分钟超时预警' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f6 = (String) map.get("f6");
        String dataUrlf6 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f6", "UTF-8");
        xmlBuf.append("<set value='" + f6 + "' link='n-" + dataUrlf6 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("<dataset seriesname='已超时工单' >");
    for (int t = 0; t < size; t++) {
        Map map = (Map) statlist.get(t);
        String s1 = (String) map.get("s1");
        String f7 = (String) map.get("f7");
        String dataUrlf7 = URLEncoder.encode(dataUrl + "&s1=" + s1 + "&fieldId=f7", "UTF-8");
        xmlBuf.append("<set value='" + f7 + "' link='n-" + dataUrlf7 + "'/>");
    }
    xmlBuf.append("</dataset>");
    xmlBuf.append("</graph>");

    xml = xmlBuf.toString();
    System.out.println(xml);
    String flash_path = path + "/FusionCharts/Charts/MSColumn3D.swf";
%>
<link rel="stylesheet" href="${app}/FusionCharts/Contents/Style.css" type="text/css"/>
<script language="JavaScript" src="${app}/FusionCharts/JSClass/FusionCharts.js"></script>
<style type="text/css">
    #tabs {
        width: 100%;
    }

    #tabs .x-tabs-item-body {
        display: none;
        padding: 10px;
    }

</style>
<script language="JavaScript">
    var Tabs = {
        init: function () {
            var tabs = new Ext.TabPanel('tabs');
            tabs.addTab('first', '数据监控');
            tabs.addTab('info', '图形报表');
            tabs.activate('first');
        }
    }
    Ext.onReady(Tabs.init, Tabs, true);

    function GoBack() {
        window.close()
    }

    Ext.onReady(function () {
        colorRows('list-table');
    })


</script>

<br>
<div id="tabs">
    <div id="first" class="tab-content">

        <div id="sheet-list">
            <div class="list-title"></div>

            <table class="listTable">
                <tr class="tr_show">
                    <td nowrap align="center" colspan="9" width="100%"><b>工单监控页面(第三层工单分类)</b></td>
                </tr>

                <tr class="tr_show">
                    <td nowrap align="center"><b>序号</b></td>
                    <td nowrap align="center"><b>工单类型</b></td>

                    <td nowrap align="center"><b>待受理</b></td>
                    <td nowrap align="center"><b>待处理</b></td>

                    <td nowrap align="center"><b>待归档</b></td>

                    <td nowrap align="center"><b><font color="##0000FF">120分钟超时预警</font></b></td>
                    <td nowrap align="center"><b><font color="#D7DF01">60分钟超时预警</font></b></td>
                    <td nowrap align="center"><b><font color="#ff0000">30分钟超时警告</font></b></td>
                    <td nowrap align="center"><b>已超时工单数</b></td>
                </tr>

                <%

                    for (int j = 0; j < size; j++) {
                        Map map = (Map) statlist.get(j);
                        String s1 = (String) map.get("s1");
                        String s11 = StatUtil.id2Name((String) map.get("s1"), "statDictId2name_v35");
                        String f1 = (String) map.get("f1");
                        String f2 = (String) map.get("f2");
                        String f3 = (String) map.get("f3");
                        String f4 = (String) map.get("f4");
                        String f5 = (String) map.get("f5");
                        String f6 = (String) map.get("f6");
                        String f7 = (String) map.get("f7");

                        out.print("<tr >");
                        out.print("<td align=\"center\">");
                        out.print(j + 1);
                        out.print("</td>");

                        out.print("<td align=\"center\">");
                        out.print(s11);
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f1' target=\"_blank\">");
                        out.print(f1);
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f2' target=\"_blank\">");
                        out.print(f2);
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f3' target=\"_blank\">");
                        out.print(f3);
                        out.print("</a>");
                        out.print("</td>");


                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f4' target=\"_blank\">");
                        out.print(f4);
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f5' target=\"_blank\">");
                        out.print(f5);
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f6' target=\"_blank\">");
                        out.print(f6);
                        out.print("</a>");
                        out.print("</td>");
                        out.print("<td align=\"center\">");
                        out.print("<a href=\'" + dataUrl + "&s1=" + s1 + "&fieldId=f7' target=\"_blank\">");
                        out.print(f7);
                        out.print("</a>");
                        out.print("</td>");

                        out.print("</tr>");

                    }

                %>

                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">指标说明:</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">1、所有单元格显示当前对应的工单数;</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">2、"待受理"，指派发后未受理的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">3、"待处理"，指受理后未回复的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">4、"待归档"，指回复后未归档的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">5、"120分钟超时预警"，指距回复时限还有60～120分钟的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">6、"60分钟超时预警"，指距回复时限还有30～60分钟的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">7、"30分钟超时警告"，指距回复时限还有0～30分钟的工单；</td>
                </tr>
                <tr class="tr_show">
                    <td nowrap align="left" colspan="9" width="100%">8、"已超时工单数"，指已超时未回复的工单。</td>
                </tr>
            </table>


            <br>


            <table>
                <tr>
                    <td align="left">
                        <form method="post" action="${app}/statisticfile/download.jsp">
                            <input type="hidden" name="excelFilePath" value="<%=excelFilePath%>">
                            <input type="hidden" name="excelFileName" value="<%=excelFileName%>">
                            <input type="submit" name="fileNamesubmit" value="导出Excel">
                        </form>
                    </td>

                    <td align="right" colspan="8" height="32">

                        <INPUT id="gback" type="button" value="关闭窗口" Onclick="GoBack();">
                    </td>
                </tr>
            </table>

            <br>

            <br>

        </div>


    </div>

    <div id="info">

        <table align="center">

            <tr>
                <td>
                    <jsp:include page="/FusionCharts/Includes/FusionChartsHTMLRenderer.jsp" flush="true">
                        <jsp:param name="chartSWF" value="<%=flash_path%>"/>
                        <jsp:param name="strXML" value="<%=xml%>"/>
                        <jsp:param name="chartId" value="myFirst"/>
                        <jsp:param name="chartWidth" value="1000"/>
                        <jsp:param name="chartHeight" value="450"/>
                        <jsp:param name="debugMode" value="false"/>
                    </jsp:include>
                </td>
            </tr>
        </table>


    </div>

</div>


<br>
<%@ include file="/common/footer_eoms.jsp" %>