
<%@ include file="/common/taglibs.jsp"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String dealPerformer = (String)request.getAttribute("dealPerformer");
 String dealPerformerLeader = (String)request.getAttribute("dealPerformerLeader");
 String dealPerformerType = (String)request.getAttribute("dealPerformerType");
 %>

 
 
   <input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/>	<!--main表Model对象类名-->	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--link表Model对象类名-->
    <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="UrgentFaultMainFlowProcess" />
    <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
     <input type="hidden" name="${sheetPageName}dealPerformer" value="${dealPerformer}"/> 
     <input type="hidden" name="${sheetPageName}dealPerformerLeader" value="${dealPerformerLeader}"/> 
     <input type="hidden" name="${sheetPageName}dealPerformerType" value="${dealPerformerType}"/> 
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp"%>
     <table  class="formTable">
     <%System.out.println("page taskName:"+taskName); %>
      <%if(taskName.equals("advice")){%>
     <input type="hidden" name="${sheetPageName}operateType" value="-11"/> 
	     <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3"> 
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
		  </td>
		</tr>
		<%} else if(taskName.equals("reply")){%>
     <input type="hidden" name="${sheetPageName}operateType" value="9"/> 
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
		  </td>
		</tr>
		</table>
  <%} %>
   <script type="text/javascript">
      

 </script> 
       