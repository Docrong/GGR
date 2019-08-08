<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="com.boco.eoms.common.tree.WKTree" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="javax.servlet.jsp.*,org.apache.struts.util.LabelValueBean,java.util.List" %>

<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page language="java" import="com.boco.eoms.common.controller.SaveSessionBeanForm,java.util.Calendar" %>

<html xmlns:BOCO="BOCO Inter-Telecom">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/wsstyle.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

<eoms:MonthList/>
<eoms:WeekList/>
<%
    Calendar cal = Calendar.getInstance();
    int weekOfNow = cal.get(cal.WEEK_OF_YEAR);
    int monthOfNow = cal.get(cal.MONTH) + 1;

    List list = (List) pageContext.getAttribute("MonthList");
    // List listWeek = (List)pageContext.getAttribute("WeekList");
    LabelValueBean lab = (LabelValueBean) list.get(monthOfNow - 1);
    //   LabelValueBean labWeek = (LabelValueBean)listWeek.get(weekOfNow-1);
    String value = lab.getValue();
    //  String valueWeek = labWeek.getValue();
%>
<script language="JavaScript">
    function GoBack() {
        window.history.back()
    }

    function check(form) {

    }

    function check1(form) {


    }


    //查看取到的id，测试用
    function confirm() {

    }

    //-->
</script>
<%
    //得到用户所属部门的id
    SaveSessionBeanForm saveSessionBeanForm
            = (SaveSessionBeanForm) session.getAttribute("SaveSessionBeanForm");
    int WrfDeptId = saveSessionBeanForm.getWrf_DeptID();

    String path = request.getContextPath();

%>
<head>
    <title>统计报表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/css/finallytree.js"></script>
</head>
<body onLoad="initIt();">
<html:form action="/KbsBase/statdo" onsubmit="return check(kbsBaseForm);">
    <div align="center">
        <br>
        <table cellSpacing="0" cellPadding="0" width="60%" border="0">
            <TR>
                <td width="100%" align="center" class="table_title">请选择统计条件<!--请选择统计条件--></td>
            </TR>
        </table>
        <table cellSpacing="0" cellPadding="0" width="60%" border="0">
            <TR>
                <TD align="right" width="100%" bgColor="#f5f5f5">
                    <div align="center">

                        <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
                            <tr class="tr_show">
                                <td noWrap width="10%">
                                    <input type="radio" name="statMode" value="2" checked></td>
                                <td noWrap width="10%">按月统计<!--按月统计-->
                                </td>
                                <td>
                                    <bean:message key="label.from"/><!--从--><eoms:SelectTime name="statFromDate"
                                                                                             formName="kbsBaseForm"
                                                                                             day="-60"/> <br>
                                    <bean:message key="label.to"/><!--到--><eoms:SelectTime name="statToDate"
                                                                                           formName="kbsBaseForm"
                                                                                           day="1"/>
                                </td>
                            </tr>

                        </table>
                        <logic:messagesPresent>
                            <bean:message key="errors.header"/>
                        <ul>
                            <html:messages id="error">
                                <li>
                                    <bean:write name="error"/>
                                </li>
                            </html:messages>
                        </ul>
                        <hr/>
                        </logic:messagesPresent>
                </td>
            </tr>
            <tr>
                <td>
                    <table height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr align="right" height="32">
                            <td>
                                <html:submit property="strutsButton">
                                    <bean:message key="label.query"/>
                                </html:submit>&nbsp;&nbsp;
                            </td>
                        </tr>
                    </table>
    </div>
    </td>
    </tr>
    </table>
    </div>
</html:form>
</body>
</html>
