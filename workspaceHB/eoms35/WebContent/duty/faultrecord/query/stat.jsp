<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<%
	String currenTime = StaticMethod.getLocalString();
	String yesterdayTime = StaticMethod.getLocalString(-1);
%>
<html:html>
<head>
<title>new</title>

</head>
<body>
<center>
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
	<tr>
		<td class="table_title" align="center"><b>${eoms:a2u('故障记录')}&nbsp;${eoms:a2u('选择条件')}</b></TD>
	</tr>
</table>

<!---正文开始------------------------------------------------------------------------------------------>
<html:form action="/Faultrecord/statdo.do">

<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>

	<tr class="tr_show">
		<td class="label">
			  ${eoms:a2u('创建时间')}</td>
		<td>
		    <bean:message key="label.from"/><!--从-->
			<input type="text" name="insertTimeFrom" size="30" value="<%=yesterdayTime%>" class="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true">
		    &nbsp;&nbsp;
		    <bean:message key="label.to"/><!--到-->
			<input type="text" name="insertTimeTo" size="30" value="<%=currenTime%>" class="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true">
    	</td>
	</tr>

    <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.startTime"/></td>
        <td colspan = 3>
 			<input type="text" name="startTime" size="30" value="" class="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true">
        </td>
    </tr>
   	
   	<tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.networkName"/></td>
        <td colspan = 3>
            <html:text property="networkName" size="20"  title="${eoms:a2u('网元名称')}" styleClass="text"/>
        </td>
    </tr>
    <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.devicetype"/></td>
        <td colspan = 3>
            <eoms:dict key="dict-plancontent" dictId="devicetype" beanId="selectXML" isQuery="false" selectId="devicetype" alt="allowBlank:false" />
        </td>
    </tr>
    <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.faultUnitLevel"/></td>
        <td colspan = 3>
			<eoms:dict key="dict-plancontent" dictId="faultUnitLevel" beanId="selectXML" isQuery="false" selectId="faultUnitLevel" alt="allowBlank:false" />
        </td>
    </tr>
                <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.faultLevel"/></td>
        <td colspan = 3>
			<eoms:dict key="dict-plancontent" dictId="faultLevel" beanId="selectXML" isQuery="false" selectId="faultLevel" alt="allowBlank:false" />
        </td>
    </tr>
    <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.declareUser"/></td>
        <td colspan = 3>
             <html:text property="declareUser" size="20"  title="${eoms:a2u('故障申报人')}" styleClass="text"/>
        </td>
    </tr>
    <tr class="tr_show">
        <td class="label">
            <bean:message key="Faultrecord.dealUser"/></td>
        <td colspan = 3>
            <html:text property="dealUser" size="20" title="${eoms:a2u('故障处理人')}" styleClass="text"/>
        </td>
    </tr>
</table>

<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
	<tr align="right" valign="middle" height="32">
		<td>
		<html:submit styleClass="button">
			${eoms:a2u('统计')}
		</html:submit>&nbsp;
		</td>
	</tr>
</table>
</html:form>
</center>
</body>
</html:html>
<%@ include file="/common/footer_eoms.jsp"%>
