<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
    <title>地市未确定HLR归属的号段<bean:message key="label.query"/></title>
</head>
<body>
<html:form method="post" action="/TawBureaudataNobelonghlr/searchDo">

    <div align="center">
        <center>
            <br>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" class="table_title" align="center"><b>
                        &nbsp;&nbsp;地市未确定HLR归属的号段<bean:message key="label.list"/>
                    </b></td>
                </tr>
                <tr>
                    <td width="100%" height="25" align="right"><bean:write name="pagerHeader" scope="request"
                                                                           filter="false"/><%! String key;%></td>
                </tr>
            </table>
            <table border="0" cellspacing="1" cellpadding="1" class="table_show" align=center width="95%">
                <tr class="tr_show">
                    <td>地市</td>
                    <td>起始号段（万号）</td>
                    <td>终止号段（万号）</td>
                    <td>是否已确定归属</td>
                    <td>局数据编号</td>
                    <td><bean:message key="label.view"/></td>
                    <td><bean:message key="label.edit"/></td>
                    <td><bean:message key="label.remove"/></td>

                </tr>
                <logic:iterate id="VO" name="LIST" type="com.boco.eoms.datum.vo.impl.TawBureaudataNobelonghlrVO">
                    <tr class="tr_show">
                        <td><bean:write name="VO" property="cityIdName" scope="page"/></td>
                        <td><bean:write name="VO" property="beginSegment" scope="page"/></td>
                        <td><bean:write name="VO" property="endSegment" scope="page"/></td>
                        <td><bean:write name="VO" property="belongFlagName" scope="page"/></td>
                        <td><bean:write name="VO" property="bureauNo" scope="page"/></td>

                        <%
                            java.util.HashMap map = new java.util.HashMap();
                            map.put("rowid", String.valueOf(VO.getId()));
//Modified By Matao,2007-11-09   保存当前URL和参数值，以便在编辑等其他操作之后可以返回到本页
                            map.put("curURL", request.getAttribute("curURL"));
                            map.put("cacheid", request.getAttribute("cacheid"));
                            map.put("pager.offset", request.getAttribute("pager.offset"));
                            map.put("pager.size", request.getAttribute("pager.size"));
                            pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                        %>

                        <td align="center"><html:link page="/TawBureaudataNobelonghlr/view.do" name="map"
                                                      scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0"
                                alt="<bean:message key="label.view"/>"></html:link></td>
                        <td align="center"><html:link page="/TawBureaudataNobelonghlr/edit.do" name="map"
                                                      scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0"
                                alt="<bean:message key="label.edit"/>"></html:link></td>
                        <td align="center"><html:link page="/TawBureaudataNobelonghlr/remove.do" name="map"
                                                      scope="page"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0"
                                alt="<bean:message key="label.remove"/>"></html:link></td>
                    </tr>
                </logic:iterate>

            </table>

            <%
                java.util.HashMap map2 = new java.util.HashMap();
                map2.put("cacheid", request.getAttribute("cacheid").toString());
                pageContext.setAttribute("map2", map2, PageContext.PAGE_SCOPE);
            %>

            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" height="32" align="right">
                        <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()"
                               class="clsbtn2"/>
                    </td>
                    <!--
    <td width="100%" height="32" align="right">
      <html:link page="/TawBureaudataNobelonghlr/getExcelFile.do" name="map2" scope="page">导出EXCEL</html:link>
    </td>
    -->
                </tr>
            </table>

        </center>
    </div>
</html:form>
</body>

</html>
