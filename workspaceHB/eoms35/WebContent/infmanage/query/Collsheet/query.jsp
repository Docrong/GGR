<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*" %>
<html:html>
    <head>
        <title>查询综合经验库</title>
        <SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/css/table_calendar.js"></SCRIPT>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
        <html:base/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    </head>
    <body>
    <html:form action="/Collsheet/querydone" method="post">
        <SCRIPT language=javascript>
            <!--
            //以下四行用于日历显示
            var outObject;
            var old_dd = null;
            writeCalender();
            bltSetDay(bltTheYear, bltTheMonth);
            //-->
        </SCRIPT>
        <style type="text/css">
            body {
                font-size: 9pt;
                color: #000000;
                LINE-HEIGHT: 18px
            }

            #tree {
                position: absolute;
                visibility: hidden;
                left: 72%;
                top: 10%;
                z-index: 2;
                background-color: #ECF2FE;
                padding: 12px;
                border-top: 1px solid #FeFeFe;
                border-left: 1px solid #FeFeFe;
                border-right: 3px solid #8E9295;
                border-bottom: 3px solid #8E9295;
            }
        </style>
        <br>
        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    工单类型:
                </td>
                <td width="75%" height="25">
                    <html:select name="CollsheetForm" style="width: 4.0cm;" property="worksheet_type">
                        <html:options name="CollsheetForm" property="values" labelProperty="labels"/>
                    </html:select>

                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    工单流水号:
                </td>
                <td width="75%" height="25">
                    <html:text styleClass="clstext" property="region_code" size="20"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    录入人:
                </td>
                <td width="75%" height="25">
                    <html:text property="achieve_person" size="30" styleClass="clstext" name="CollsheetForm"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    入库时间：
                </td>
                <td width="75%" height="25">
                    <html:text styleClass="clstext" property="achieve_time" onfocus="setday(this)" size="30"
                               name="CollsheetForm"/>

                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    主题:
                </td>
                <td width="75%" height="25">
                    <html:text styleClass="clstext" property="key_word" size="30"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    问题描述:
                </td>
                <td width="75%" height="25">
                    <html:textarea property="fault_description" rows="4" style="width:100%" value="" title="问题描述"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    问题分析:
                </td>
                <td width="75%" height="25">
                    <html:textarea property="fault_anolyize" rows="4" style="width:100%" value="" title="问题分析"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td width="25%" height="25" class="clsfth">&nbsp;
                    处理过程:
                </td>
                <td width="75%" height="25">
                    <html:textarea property="resovl_process" rows="4" style="width:100%" value="" title="处理过程"/>
                </td>
            </tr>
        </table>
        <table border="0" width="100%" cellspacing="0" align="center">
            <tr>
                <td width="100%" colspan="2" height="32" align="right">
                    <input Class="clsbtn2" type="submit" name="tosubmit" value="<bean:message key="label.query"/>">
                </td>
            </tr>
        </table>
    </html:form>
    </body>
</html:html>
