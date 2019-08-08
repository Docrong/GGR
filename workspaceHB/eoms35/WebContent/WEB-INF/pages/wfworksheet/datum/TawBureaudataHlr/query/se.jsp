<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<title>HLR����<bean:message key="label.query"/></title>

<body>
<html:form method="post" action="/TawBureaudataHlr/searchDo">

    <div align="center">
        <center>
            <br>
            <table border="0" width="95%" cellspacing="0">
                <tr>
                    <td width="100%" class="table_title" align="center"><b>
                        &nbsp;&nbsp;�����ϵ�HLR����<bean:message key="label.list"/>
                    </b></td>
                </tr>
                <tr>
                    <td width="100%" height="25" align="right"><bean:write name="pagerHeader" scope="request"
                                                                           filter="false"/><%! String key;%></td>
                </tr>
            </table>
            <table border="0" cellspacing="1" cellpadding="1" class="table_show" align=center width="95%">
                <tr class="td_label">
                    <td>HLR����</td>
                    <td>HLR�����</td>
                    <td>HLR ID</td>
                    <td>�ָ�</td>
                    <td>����ɾ��</td>
                </tr>
                <logic:iterate id="VO" name="LIST" type="com.boco.eoms.datum.vo.impl.TawBureaudataHlrVO">
                    <tr class="tr_show">
                        <td><bean:write name="VO" property="hlrName" scope="page"/></td>
                        <td><bean:write name="VO" property="hlrSignalId" scope="page"/></td>
                        <td><bean:write name="VO" property="hlrId" scope="page"/></td>
                        <%
                            java.util.HashMap map = new java.util.HashMap();
                            map.put("rowid", String.valueOf(VO.getId()));
                            map.put("cacheid", request.getAttribute("cacheid"));
                            pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
                        %>
                        <td align="center"><html:link page="/TawBureaudataHlr/recover.do" name="map" scope="page"
                                                      onclick="return confirm('�Ƿ�ȷ���ָ�������?');"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_hf.gif" border="0"
                                alt="<bean:message key="label.edit"/>"></html:link></td>
                        <td align="center"><html:link page="/TawBureaudataHlr/truncate.do" name="map" scope="page"
                                                      onclick="return confirm('ע��:���ǲ��ɻָ��Ĳ���!�Ƿ�ȷ������ɾ��������?');"><img
                                src="<%=request.getContextPath()%>/images/bottom/an_yj.gif" border="0"
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

                    <!--  <td width="100%" height="32" align="right">
      <html:link page="/TawBureaudataHlr/getExcelFile.do" name="map2" scope="page">����EXCEL</html:link>
    </td>
-->
                </tr>
            </table>
        </center>
    </div>
</html:form>
</body>
</html>
