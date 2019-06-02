<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveLink  bureaudataSaveLink = (com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(bureaudataSaveLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(bureaudataSaveLink.getOperateType()));
%>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		
   		return false;
   	}
   	form.action = "${app}/sheet/bureaudataSave/bureaudataSave.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
   	form.submit();
 }
function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
		thisform.submit();
	}
}
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/bureaudataSave/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/bureaudataSave.do" styleId="theform">       
	<br/>
               <input type="hidden" name="${sheetPageName}beanId" value="iBureaudataSaveMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.bureaudataSave.model.bureaudataSaveMain"/>	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.bureaudataSave.model.bureaudataSaveLink"/> 
     <table class="formTable">
     		<caption><bean:message bundle="bureaudataSave" key="bureaudataSave.header"/></caption>
		<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text max" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>
           
		
		
      <!-- 号段审核 -->
        <%if(taskName.equals("Assessor")){ %>
             <%if(operateType.equals("200")||operateType.equals("11")){ %>
		     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />	
	          <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
			  </tr>
			  <tr>
	              <td  class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkZContact"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}linkZContact" id="${sheetPageName}linkZContact"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z施工联系人，最多输入25字'" value="${sheetLink.linkZContact}"/>
		          </td>					    
	              <td  class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkZContactTel"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}linkZContactTel" id="${sheetPageName}linkZContactTel"    alt="allowBlank:false,maxLength:25,vtext:'请填入Z施工业务联系电话，最多输入25字'" value="${sheetLink.linkZContactTel}"/>
		          </td>	
		       </tr>	  		    	
 		       <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		        </tr>			          
	            <tr>
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="bureaudataSave" alt="allowBlank:true"/>
		                </td>
		         </tr>  		    	  
         <%}} %>
         
         
         
         
         
		 <%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
			<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>				
<!--    	<tr>-->
<!--	       <td class="label">-->
<!--	        <bean:message bundle="bureaudataSave" key="bureaudataSave.remark" />*-->
<!--		    </td>-->
<!--			<td colspan="3">			-->
<!--		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" -->
<!--		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>-->
<!--		  </td>-->
<!--		</tr>  	-->
		
		<%} %>         
		  <!-- 待归档 -->
          <%if( taskName.equals("HoldTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
      			<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	
         	 	 
	 		 <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
			    <td colspan='3'>
			      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
			        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
			    </td>
		     </tr>
			  
			  <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
			    <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
			      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>
 		       <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		        </tr>			          
	            <tr>
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="bureaudataSave" alt="allowBlank:true"/>
		                </td>
		         </tr> 			  	        			    			 
              <%}%>              
          <%}%>

      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%}%>
		

		
		<% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 
	 </table>
</html:form>
<logic:present name="type">
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
          	<bean:message bundle="sheet" key="button.saveCurTemplate" />
    	</html:button>
</c:if>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         <bean:message bundle="sheet" key="button.delete" />
</html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
         <bean:message bundle="sheet" key="button.back" />
</html:button>
