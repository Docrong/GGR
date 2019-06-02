<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
  

 
<html:form action="/dutyevent.do?method=searchListShow"
	method="post" styleId="tawRmDutyEventForm">
	 
	<table border=0 cellspacing="1" cellpadding="1" class="listTable">
		<!--附加表以及XML文件基本属性表格：开始-->
		<tr>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.faultType" />
			</td>
			<td COLSPAN="10">
			 
					<eoms:dict key="dict-duty" dictId="faultType" beanId="selectXML"  isQuery="false"  selectId="faultType"/>
			</td>
		 
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.flag" />
			</td>
			<td COLSPAN="10">
			<eoms:dict key="dict-duty" dictId="faultflag" beanId="selectXML"  isQuery="false"  selectId="flag"/>
			 
		 	</td>
		 
		 <td COLSPAN="4" class="label">
				<bean:message key="tawRmDutyEvent.complateFlag" />
			</td>
			<td COLSPAN="10">
				<eoms:dict key="dict-duty" dictId="complateFlag" beanId="selectXML"  isQuery="false"  selectId="complateFlag"/>
			</td>
			
			</tr> 
		<tr>
		
			<td COLSPAN="4" class="label">
					 <bean:message key="tawRmDutyEvent.beginTime" />
			</td>
			<td COLSPAN="10">
				<input type="text" name="beginTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
			<td COLSPAN="4" class="label">
					<bean:message key="tawRmDutyEvent.endtime" />
			</td>
			<td COLSPAN="10">
					<input type="text" name="endtime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
			</td>
	 
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.eventtitle" />
			</td>
			<td COLSPAN="10">
					<input name="eventtitle"   type="text"  class="clstext"   value="" />
		 	
		</tr>
		<TR>
			<td COLSPAN="4" class="label">
				 <bean:message key="tawRmDutyEvent.pubstatus" />
			</td>
			<td COLSPAN="10">
			<html:select property="pubstatus" >
					<html:option value="" ><bean:message key="tawRmDutyEvent.chose" /></html:option>
					<html:option value="0" ><bean:message key="tawRmDutyEvent.nopublish" /></html:option>
        			<html:option value="1" ><bean:message key="tawRmDutyEvent.alpublish" /></html:option>
        			<html:option value="2"><bean:message key="tawRmDutyEvent.cannotpublish" /></html:option>
        		</html:select>
				 
			 
		 	</td>
			<td COLSPAN="28" align="right"> 
			<input type="submit" value="<bean:message key="designer.title.btnSubmit" />"  class="button">
			</td>
	</tr>
	</table>
</html:form>
<%
if(request.getAttribute("pageSize")!=null){
%>
<html:form action="/dutyevent.do?method=saveSerch" method="post" styleId="tawRmdutyEventForm">
 
	<display:table name="TawRmDutyEventList" cellspacing="0" cellpadding="0" id="TawRmDutyEventList" pagesize="${pageSize}" class="table TawRmDutyEventList"  requestURI="${app}/duty/dutyevent.do?method=search" sort="list"
		partialList="true" size="resultSize" decorator="com.boco.eoms.duty.displaytag.support.DutyEventDisplaytagDecorator" >
	 	<display:column property="inputuser" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.inputuser" paramId="id" paramProperty="id" href="${app}/duty/dutyevent.do?method=getEvent" />
		 
		<display:column property="inputdate" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.inputdate" />
		<display:column property="flag" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.flag" />

		<display:column property="eventtitle" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.eventtitle"  />
		<display:column property="beginTime" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.beginTime"  />
  
		<display:column property="complateFlag" sortable="true" headerClass="sortable" titleKey="tawRmDutyEvent.complateFlag"  />
		<display:column property="pubstatus" sortable="true" headerClass="sortable" title="发布状态"  />
   		<display:column  sortable="true" headerClass="sortable"
        url="/duty/dutyevent.do?method=getEvent" paramId="id" paramProperty="id" titleKey="tawRmDutyEvent.read"><bean:message key="tawRmDutyEvent.look"/></display:column>
  

		<display:setProperty name="paging.banner.item_name" value="thread" /> 
		<display:setProperty name="paging.banner.items_name" value="threads" />
	</display:table>
</html:form>
<%}%>
<%@ include file="/common/footer_eoms.jsp"%>
