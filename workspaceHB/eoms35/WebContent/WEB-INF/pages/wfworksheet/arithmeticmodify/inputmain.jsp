<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 request.setAttribute("roleId","80045"); 
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>

<%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/baseinputmainhtmlnew.jsp"%>

	<input type="hidden" name="${sheetPageName}processTemplateName" value="ArithmeticModifyProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
       <input type="hidden" name="${sheetPageName}gatherPhaseId" id="${sheetPageName}gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iArithmeticModifyMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink"/>
     <br/>
      <table class="formTable">
      <tr>
	       <td class="label">受理时限</td>
	       <td class="content">
	           <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
			       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
			       onclick="popUpCalendar(this, this)"
			       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:true"/>   
	       </td>
	       <td class="label">回复时限</td>
	       <td class="content">
	           <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
			       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
			       onclick="popUpCalendar(this, this)"
			       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:true"/>   
	       </td>
	   </tr>	
	   <tr>
	       <td class="label">指标级别*</td>
			  <td class="content">			   
			      <eoms:comboBox name="${sheetPageName}mainTargetLevel" id="${sheetPageName}mainTargetLevel" 
			      initDicId="1017002" defaultValue="${sheetMain.mainTargetLevel}"  styleClass="select-class" alt="allowBlank:false"/>
			 </td>
			<td class="label">指标分类*</td>
			  <td class="content">			   
			      <eoms:comboBox name="${sheetPageName}mainTargetType" id="${sheetPageName}mainTargetType" 
			      initDicId="1017003" defaultValue="${sheetMain.mainTargetType}"  styleClass="select-class" alt="allowBlank:false" onchange="getType(this.value);"/>
			 </td>
	   </tr>	  	  	
	    <tr>
		    <td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>	
			<td colspan="3"><eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="arithmeticmodify" /> 	
		    </td>
	    </tr>				          
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
	     	 <span id="roleName">技术专家组</span>
	 </legend>
		  <eoms:chooser id="test1"  type="role" roleId="80046" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />	
			 <eoms:chooser id="test2"  type="role" roleId="80050" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />	
			   <eoms:chooser id="test3"  type="role" roleId="80051" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />	
	</fieldset>
	</c:if>
	<script type="text/javascript" language="javascript">
		  
		function getType(temp){
			if(temp=='101700301'){
				$('roleName').innerHTML="网优中心技术组";
				chooser_test1.enable();
				chooser_test2.disable();
				chooser_test3.disable();
			}
			if(temp=='101700302'){
				$('roleName').innerHTML="增值业务技术组";
				chooser_test2.enable();
				chooser_test1.disable();
				chooser_test3.disable();
			}
			if(temp=='101700303'){
				$('roleName').innerHTML="核心网技术组";
				chooser_test3.enable();
				chooser_test2.disable();
				chooser_test1.disable();
			}
		}
		
	</script>
<script type="text/javascript" language="javascript">
	 function setTree(){
	 	var type='${sheetMain.mainTargetType}';
	 	if(type==""){
	 			chooser_test3.disable();
				chooser_test2.disable();
				chooser_test1.disable();
			}else{
		getType(type);
		}
	 };
	 setTree();

</script>