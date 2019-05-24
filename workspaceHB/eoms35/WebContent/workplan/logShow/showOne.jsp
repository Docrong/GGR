<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import ="java.util.List"%>
<html>
<head>
<title>作业计划接口监控日志</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

<table width="750" border="0" align="center" cellpadding="1"  cellspacing="1" class="table_show">
  <tr class="td_label">
    <td ><center>
      时间
    </center></td>
    <td >
      <bean:write name="showOne" property="createTime" />
    </td>
  </tr>
  <tr class="td_label">
    <td ><center>
      专业名称
    </center>    </td>
    <td ><bean:write name="showOne" property="model" /></td>
  </tr>
  <tr class="td_label">
    <td ><center>
      日志类型
    </center>    </td>
    <td ><bean:write name="showOne" property="logType" /></td>
  </tr>
  <tr class="td_label">
    <td ><center>
      结果
    </center>    </td>
    <td ><bean:write name="showOne" property="resultState" /></td>
  </tr>
  <tr class="td_label">
    <td ><center>
      详细信息
    </center>    </td>
    <td ><bean:write name="showOne" property="message" /></td>
  </tr>
  
  <tr class="tr_show">
    <td align="middle"  width="700"  class="clsthd2" colspan='8'><center>
      <input name="button" type="button" class="clsbtn2" onClick="javascript:window.history.back();" value="返回">
    </center></td>
  </tr>
</table>
