<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<table>
	<caption>
		<bean:message key="tawRmDutyEvent.publishflag" />
	</caption>
</table>

<html:form action="/dutyevent.do?method=searchListShow"
	method="post" styleId="tawRmDutyEventForm">
	 
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.eventid" />
			</td>
			<td COLSPAN="10">
			 
					<input name="eventName"   type="text"  class="clstext"   value="" />
			</td>
		 
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.pubflag" />
			</td>
			<td COLSPAN="10">
			<eoms:dict key="dict-duty" dictId="faultflag" beanId="selectXML"  isQuery="false"  selectId="flag"/>
			 
		 	</td>
		 
		 <td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.oid" />
			</td>
			<td COLSPAN="10">
				<eoms:dict key="dict-duty" dictId="complateFlag" beanId="selectXML"  isQuery="false"  selectId="complateFlag"/>
			</td>
			
			</tr> 
		<tr>
		
			<td COLSPAN="4" class="label">
					 <bean:message key="tawRmDutyEvent.eventtitle" />
			</td>
			<td COLSPAN="14"> 
					<input name="eventname"   type="text"  class="clstext"   value="" />
			</td>
			<td COLSPAN="4" class="label">
					<bean:message key="tawRmDutyEvent.man" />
			</td>
			<td COLSPAN="10">
					<input name="eventtitle"   type="text"  class="clstext"   value="" />
		 	</td>
		 	<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.datetime" />
			</td>
			<td COLSPAN="10">
					<input type="text" name="endtime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
		</tr>
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.pubstatus" />
			</td>
			<td COLSPAN="14"> 
				<input name="eventname"   type="text"  class="clstext"   value="" />
			</td>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.starttime" />
			</td>
			<td COLSPAN="10">
				<input type="text" name="starttime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.endtime" />
			</td>
			<td COLSPAN="10">
				<input type="text" name="beginTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
		</tr>
	<tr>
		<td COLSPAN="14"> 
			<input type="submit" value="<bean:message key="designer.title.btnSubmit" />"  class="button">
		</td>
	</tr>
	</table>
</html:form>
