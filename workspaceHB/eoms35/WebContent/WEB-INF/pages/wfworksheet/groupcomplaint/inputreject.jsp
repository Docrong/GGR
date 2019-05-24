<%@ include file="/common/taglibs.jsp"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtml.jsp"%>
 <fieldset>
     <legend>Info</legend>
     <input type="hidden" name="${sheetPageName}operateType" value="12"/>        

</fieldset>
     
     <%-- accessories 
		<fieldset> 
        	<legend><bean:message bundle="sheet" key="mainForm.accessories" /></legend>
        	<eoms:attachment idList="" idField="accessories" appCode="1001"/>
        </fieldset>
       --%> 
        
 <!-- xbox -->
       