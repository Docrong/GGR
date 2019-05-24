<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

 request.setAttribute("roleId","4060"); 

long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/circuitoperation/baseinputmainhtmlnew.jsp"%>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="CircuitOperationProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="CircuitOperationProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeZTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iCircuitOperationMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.circuitoperation.model.CircuitOperationMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.circuitoperation.model.CircuitOperationLink"/>
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
				  <td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainUrgency"/>*</td>
				  <td class="content" colspan="3">
				  	 <eoms:comboBox name="${sheetPageName}mainUrgency" id="${sheetPageName}mainUrgency" 
				  	       initDicId="10302" defaultValue="${sheetMain.mainUrgency}" alt="allowBlank:false"/>
				  </td>
				</tr>
				<tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainAContact"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}mainAContact" id="${sheetPageName}mainAContact"   alt="allowBlank:false,maxLength:25,vtext:'请填入A施工联系人，最多输入25字'" value="${sheetMain.mainAContact}"/>
		          </td>					    
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainAContactTel"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}mainAContactTel" id="${sheetPageName}mainAContactTel"    alt="allowBlank:false,maxLength:25,vtext:'请填入A施工业务联系电话，最多输入25字'" value="${sheetMain.mainAContactTel}"/>
		          </td>	
		         </tr>				



		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainRequireUse"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}mainRequireUse" id="${sheetPageName}mainRequireUse" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入需求用途，最多输入255个字符'">${sheetMain.mainRequireUse}</textarea>
					</td>
				</tr> 
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainRequireDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}mainRequireDesc" id="${sheetPageName}mainRequireDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入需求描述，最多输入255个字符'">${sheetMain.mainRequireDesc}</textarea>
					</td>
				</tr>						         	         
			    <tr>
			      <td class="label">
			    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
				  </td>	
				<td colspan="3">							
			    <eoms:attachment name="tawSheetAccess1" property="accesss" 
			            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y" />			            
			    </td>
			  </tr>
			  		
				<tr>
				  <td class="label"><bean:message bundle="sheet" key="mainForm.accessories" /></td>
				  <td colspan="3">
					<eoms:attachment name="sheetMain" property="sheetAccessories"
			            scope="request" idField="${sheetPageName}sheetAccessories" appCode="circuitoperation" />	   
				  </td>
				</tr>

	         			         	         		         		                   		          		             
	</table>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:Z端业务侧方案制作员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			   panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
			   data="${sendUserAndRoles}"/>			   
			 </div>		   
			 </div>
	</fieldset>
	</c:if>
<script type="text/javascript">
</script>