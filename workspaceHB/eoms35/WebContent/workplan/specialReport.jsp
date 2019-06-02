<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<head>

</head>

<body leftmargin="0" topmargin="0" class="clssclbar">
<form method="post" action="workResult.jsp" name="planForm">
<br>

<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr><td align="center" class="table_title">
作业计划补录
</td></tr>
</table>

</br>
<table border="0"  cellspacing="1" cellpadding="1" class="table_show" width="500" align=center>

<tr class="tr_show"><td class="clsfth">
 补录时间
</td><td>

<eoms:SelectDate name="time" value="" formName="planForm"/>

</td></tr>

<tr class="tr_show"><td class="clsfth">
 网元编号
</td><td>

<input  type="text" name="serial" />

</td></tr>

<tr class="tr_show"><td class="clsfth">
补录类型
</td><td>
<input  type="radio" name="type" value="1" checked="checked">生成excel文件</input>
<input  type="radio" name="type" value="2">上传文件</input>
<input  type="radio" name="type" value="3">生成并上传文件</input>
<input  type="radio" name="type" value="4">上传目录</input>
<input  type="radio" name="type" value="5">文件改名</input>
</td></tr>



</table>


<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr><td align="right" height="32">
<html:submit property="strutsButton" styleClass="clsbtn2">
提交
</html:submit></td></tr>
</table>

</form></body>

