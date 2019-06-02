<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<title>BSC1860投诉登记表<bean:message key="label.query"/></title>

<body>
<html:form method="post" action="/TawBsc1860/searchDo">

<div align="center">
  <center>
<br>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" class="table_title" align="center"><b>
    &nbsp;&nbsp;BSC1860投诉登记表<bean:message key="label.list"/>
  </b></td>
  </tr>
  <tr>
    <td width="100%" height="25" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></td>
  </tr>
</table>
    <table border="0" cellspacing="1" cellpadding="1" class="table_show" align=center width="95%">
      <tr class="tr_show">
                            <td>单号</td>
                                     <td>收单人</td>
                                                	<td><bean:message key="label.view"/></td>
        <td><bean:message key="label.edit"/></td>
        <td><bean:message key="label.remove"/></td>

  </tr>
  <logic:iterate id="VO" name="LIST" type="com.boco.eoms.datum.vo.impl.TawBsc1860VO">
  <tr class="tr_show">
                                     <td><bean:write name="VO" property="sheetId" scope="page"/></td>
          			   			                              <td><bean:write name="VO" property="receiver" scope="page"/></td>
          			   			         			         			      
<%
java.util.HashMap map = new java.util.HashMap();
map.put("id", String.valueOf(VO.getId()));
pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
%>

        <td align="center"><html:link page="/TawBsc1860/view.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="<bean:message key="label.view"/>"></html:link></td>
        <td align="center"><html:link page="/TawBsc1860/edit.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="<bean:message key="label.edit"/>"></html:link></td>
        <td align="center"><html:link page="/TawBsc1860/remove.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0" alt="<bean:message key="label.remove"/>"></html:link></td>
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
      <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
    </td>
    <!--
    <td width="100%" height="32" align="right">
      <html:link page="/TawBsc1860/getExcelFile.do" name="map2" scope="page">导出EXCEL</html:link>
    </td>
    -->
  </tr>
</table>

  </center>
</div>
</html:form>
</body>

</html>