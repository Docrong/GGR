<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

 request.setAttribute("roleId","428"); 

long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/netFault/baseinputmainhtmlnew.jsp"%>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="NetFaultProces" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="NetFaultProces" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iNetFaultMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netFault.model.NetFaultMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netFault.model.NetFaultLink"/>
    <br>
	<!-- sheet info --> 
     <table class="formTable">
         <tr>
		  <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
				id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
				onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/> 
	  			  
		  </td>				
		  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
		  <td class="content">
		    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
				id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
				onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
			
		  </td>
		  </tr>
		  <tr>
		  <td class="label">
		  	 <bean:message bundle="netFault" key="netFault.Specialty" />*
		  </td>
		  <td colspan="3">
			  <eoms:comboBox name="${sheetPageName}specialty" id="${sheetPageName}specialty" initDicId="1011501" defaultValue="${sheetMain.specialty}" alt="allowBlank:ture"/>
		  </td>			  
		</tr> 
		<tr>
		  <td class="label">
		  	 <bean:message bundle="netFault" key="netFault.TaskText" />*
		  </td>
		  <td colspan="3">
		  	<textarea name="${sheetPageName}taskText" class="textarea max" id="${sheetPageName}taskText" 
		        alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetMain.taskText}</textarea>
		  </td>		  
		</tr> 
		   <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					

		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="netFault" /> 				
		    </td>
		  </tr>				    
	</table>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	 <legend>
	     	 请选择派发对象
	      </legend>
	        <div class="x-form-item" >
		        <div id="test">
					<div class="viewer-list" id="test-chooser-show">
						<div>派发:
							<div style="display: inline;" class="viewlistitem-dept">网管中心IT支撑网部 </div>
						</div>
					</div>
						<input type="hidden" name="testTotalJSON" id="ext-gen20" value="[{id:'101',nodeType:'dept',categoryId:'dealPerformer'}]">
						<input type="hidden" name="dealPerformer" alt="allowBlank:false,vtext:'请选择派发对象'" id="ext-gen135" value="1223">
						<input type="hidden" name="dealPerformerType" id="ext-gen136" value="dept">
						<input type="hidden" name="dealPerformerLeader" id="ext-gen137" value="1223">
						<input type="hidden" name="dealPerformerJSON" id="ext-gen138" value="[{id:'1223',nodeType:'dept',categoryId:'dealPerformer'}]">
						<input type="hidden" name="copyPerformer" alt="" id="ext-gen149" value="">
						<input type="hidden" name="copyPerformerType" id="ext-gen150" value="">
						<input type="hidden" name="copyPerformerLeader" id="ext-gen151" value="">
						<input type="hidden" name="copyPerformerJSON" id="ext-gen152" value="[]">
				</div>
	        
			 </div>
	</fieldset>
	</c:if>
