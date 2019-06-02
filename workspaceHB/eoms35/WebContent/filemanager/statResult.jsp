<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<title>统计结果</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/default/theme.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/wsstyle.css" type="text/css">
<%----%>
</head>
<html:html>
<br/>
<br/>
<table width="100%">
    <tr>
        <td>
            &nbsp;&nbsp;<font size="2"><strong>报表处理统计结果</strong></font></td>
    </tr>
</table>
<br/>

            <table width="100%" class="formTable">
                <tr align="center">
                    <td nowrap width="5%" class="label">
                        <b>序号</b>
                    </td>
                    <logic:equal value="1" name="StatType">
                        <td width="25%" height="15" nowrap class="label">
                            <b>部门名称</b>
                        </td>
                    </logic:equal>
                    <logic:equal value="2"  name="StatType">
                        <td width="25%" height="15" nowrap class="label">
                            <b>报表名称</b>
                        </td>
                    </logic:equal>
                    <td width="10%" height="15" nowrap class="label">
                        <b>上报及时个数</b>
                    </td>
                    <td width="10%" height="15" nowrap class="label">
                        <b>上报合格个数</b>
                    </td>
                    <td width="10%" height="15" nowrap class="label">
                        <b>未处理个数</b>
                    </td>
                    <td width="10%" height="15" nowrap class="label">
                        <b>派发总数</b>
                    </td>
                    <td width="15%" height="15" nowrap class="label">
                        <b>上报及时率</b>
                    </td>
                    <td width="10%" height="15" nowrap class="label">
                        <b>上报合格率</b>
                    </td>
                </tr>
                <%
                    int i = 0;
                    int totalCount=0;
                %>
                <logic:iterate id="resultList" name="StatResult" type="com.boco.eoms.filemanager.form.StatResultBean">
                    <tr bgcolor="<%if(i%2==0) out.print("#eeeeee"); else out.print("#F7FBFF");%>" align="center">
                        <td>
                            <%=++i%>
                        </td>
                        <logic:equal value="1" name="StatType">
                            <td nowrap height="15">
                                <bean:write name="resultList" property="acceptDeptName"/>
                            </td>
                        </logic:equal>
                        <logic:equal value="2"  name="StatType">
                            <td nowrap height="15">
                                <bean:write name="resultList" property="reportName"/>
                            </td>
                        </logic:equal>
                        <td nowrap height="15">
                            <bean:write name="resultList" property="intimeCount"/>
                        </td>
                        <td nowrap height="15">
                            <bean:write name="resultList" property="validCount"/>
                        </td>
                        <td nowrap height="15">
                            <bean:write name="resultList" property="notAcceptCount"/>
                        </td>
                        <td nowrap height="15">
                            <bean:define id="count"  name="resultList" property="totalCount"/>
                            <%
                                totalCount+=Integer.parseInt(count.toString());
                            %>
                            <bean:write name="resultList" property="totalCount"/>
                        </td>
                        <td nowrap height="15">
                            <bean:write name="resultList" property="intimeRate"/>
                        </td>
                        <td nowrap height="15">
                            <bean:write name="resultList" property="validRate"/>
                        </td>
                    </tr>
                </logic:iterate>
</table>
<table class="formTable">
<tr><td class="label"><b>统计结果总记录数：<%=totalCount%></b></td>
</tr>
</table>
<br/>
&nbsp;&nbsp;<input class="button" type="button" name="back"  value="返回" onclick="history.back();"/>
</html:html>