<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<title>地市区号对应<bean:message key="label.query"/></title>
</head>
<body style="margin-top:20px;">
<div align="center">
<table border="0" width="95%" cellspacing="0" style="margin-bottom:10px;">
  <tr>
    <td width="100%" class="table_title" align="center"><b>&nbsp;&nbsp;地市区号对应<bean:message key="label.list"/></b></td>
  </tr>
</table>
    <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
      <tr class="td_label">
        <td>地市名称</td>
        <td>区号</td>
        <td>对应部门</td>
        <td>负责人员ID号</td>
        <td><bean:message key="label.view"/></td>
        <td><bean:message key="label.edit"/></td>
  </tr>
  <logic:iterate id="VO" name="AllCityzone" type="com.boco.eoms.datum.vo.impl.TawBureaudataCityzoneVO">
  <tr class="tr_show">
    <td><bean:write name="VO" property="cityName"/></td>
    <td><bean:write name="VO" property="zoneNum"/></td>
    <td><bean:write name="VO" property="deptName"/></td>
    <td><bean:write name="VO" property="userId"/></td>
<%
java.util.HashMap map = new java.util.HashMap();
map.put("cityId", String.valueOf(VO.getCityId()));
pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
%>
        <td align="center"><html:link page="/TawBureaudataCityzone/view.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0" alt="<bean:message key="label.view"/>"></html:link></td>
        <td align="center"><html:link page="/TawBureaudataCityzone/edit.do" name="map" scope="page"><img src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0" alt="<bean:message key="label.edit"/>"></html:link></td>
  </tr>
  </logic:iterate>
</table>
</div>
</body>
</html>
