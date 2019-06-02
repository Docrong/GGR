<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","9701");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/groupcheck/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "groupcheck.do?method=newShowLimit&flowName=GroupCheck",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("sheetAcceptLimit").value = "";
        	   // $('sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   </script>
	<input type="hidden" name="processTemplateName" value="GroupCheck" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="CityCheck" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iGroupCheckMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
		
		<tr>
			  <td class="label">受理时限</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label">处理时限*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"
					/>   
			  </td>		  
			</tr>
 		
 		<tr>
 			<td class="label">
				<!-- 投诉时间 -->
				<bean:message bundle="groupcheck" key="groupCheckMain.mainComplaintTime"/>
			</td>
			<td colspan="3">
				<input type="text" class="text" name="mainComplaintTime" readonly="readonly" 
					id="mainComplaintTime" value="${eoms:date2String(sheetMain.mainComplaintTime)}" 
					onclick="popUpCalendar(this, this)"
					alt="allowBlank:false"
					/>
			</td>
		</tr>
 		
		<tr>
			<td class="label">
				<!-- 集团投诉工单号 -->
				<bean:message bundle="groupcheck" key="groupCheckMain.mainGroupSheetId"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainGroupSheetId" id="mainGroupSheetId"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 集团投诉工单号 信息，最多输入 1000 字符'" value="${sheetMain.mainGroupSheetId}"/>
			</td>
 			<td class="label">
				<!-- 产品实例标识 -->
				<bean:message bundle="groupcheck" key="groupCheckMain.mainProductIns"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainProductIns" id="mainProductIns"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 产品实例标识 信息，最多输入 1000 字符'" value="${sheetMain.mainProductIns}"/>
			</td>
		</tr>
 
		<tr>
			<td class="label">
				<!-- 电路代号 -->
				<bean:message bundle="groupcheck" key="groupCheckMain.mainCircuitCode"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainCircuitCode" id="mainCircuitCode"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路代号 信息，最多输入 1000 字符'" value="${sheetMain.mainCircuitCode}"/>
			</td>
 			<td class="label">
				<!-- 用户归属地 -->
				<bean:message bundle="groupcheck" key="groupCheckMain.mainUserAffilia"/>
			</td>
			<td>
				<input type="text"  class="text" name="mainUserAffilia" id="mainUserAffilia"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 用户归属地 信息，最多输入 1000 字符'" value="${sheetMain.mainUserAffilia}"/>
			</td>
		</tr>
 
		
 
 
</table>

	
<!-- 附件 -->
<table id="sheet" class="formTable">
		<!--附件模板-->
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
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="groupcheck" alt="allowBlank:true"/> 				
		    </td>
	   </tr>			  
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 地市核查组
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="9702" flowId="97" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>