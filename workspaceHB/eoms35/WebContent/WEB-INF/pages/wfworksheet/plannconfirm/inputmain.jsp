<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","1870");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/plannconfirm/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "plannconfirm.do?method=newShowLimit&flowName=PlannConfirm",
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
	<input type="hidden" name="processTemplateName" value="PlannConfirm" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="ConfirmTask" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iPlannConfirmMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.plannconfirm.model.PlannConfirmMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.plannconfirm.model.PlannConfirmLink"/>
    <br>

    <!-- 工单基本信息 --> 
<table id="sheet" class="formTable" >
                 <tr>
					   <td class="label">受理时限*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
							id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
							onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>   
					  </td>					  
					  <td class="label">回复时限*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
							   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
							   onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>   
					  </td>
			    </tr> 
		<tr><td class="label">
				<!-- 所属专业 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.professional"/>*
			</td>
			<td>
				<eoms:comboBox name="professional" id="professional" 
						 initDicId="1012001"  
						defaultValue="${sheetMain.professional}" alt="allowBlank:false"/>
			</td>
 <td class="label">
				<!-- 网络分类一级类别 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeOne"/>*
			</td>
			<td>
				<eoms:comboBox name="networkTypeOne" id="networkTypeOne" 
						 initDicId="1012002"  
						defaultValue="${sheetMain.networkTypeOne}" alt="allowBlank:false"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 网络分类二级类别 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeTwo"/>*
			</td>
			<td>
				<eoms:comboBox name="networkTypeTwo" id="networkTypeTwo" 
						 initDicId="1012003"  
						defaultValue="${sheetMain.networkTypeTwo}" alt="allowBlank:false"/>
			</td>
 <td class="label">
				<!-- 网络分类三级类别 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.networkTypeThree"/>*
			</td>
			<td>
				<eoms:comboBox name="networkTypeThree" id="networkTypeThree" 
						 initDicId="1012004"  
						defaultValue="${sheetMain.networkTypeThree}" alt="allowBlank:false"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 任务类型 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.taskType"/>*
			</td>
			<td>
				<eoms:comboBox name="taskType" id="taskType" 
						 initDicId="1012005"  
						defaultValue="${sheetMain.taskType}" alt="allowBlank:false"/>
			</td>
 <td class="label">
				<!-- 任务描述 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.taskDescription"/>*
			</td>
			<td>
				<input type="text"  class="text" name="taskDescription" id="taskDescription"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 任务描述 信息，最多输入 2000 字符'" value="${sheetMain.taskDescription}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 站址选点时间 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.selectionTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="selectionTime" readonly="readonly" 
						id="selectionTime" value="${eoms:date2String(sheetMain.selectionTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td>
 <td class="label">
				<!-- 规划编号 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.planningNumber"/>*
			</td>
			<td>
				<input type="text"  class="text" name="planningNumber" id="planningNumber"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 规划编号 信息，最多输入 1000 字符'" value="${sheetMain.planningNumber}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 属地分公司 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.territorialBranch"/>*
			</td>
			<td>
				<eoms:id2nameDB id="${sheetMain.territorialBranch}" beanId="tawSystemDeptDao"/>
				<input type="hidden" name="territorialBranch" id="territorialBranch" value="${sheetMain.territorialBranch}"/>
			</td>
 <td class="label">
				<!-- 基站站址 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.stationSite"/>*
			</td>
			<td>
				<input type="text"  class="text" name="stationSite" id="stationSite"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 基站站址 信息，最多输入 1000 字符'" value="${sheetMain.stationSite}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 场景类型 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.sceneType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="sceneType" id="sceneType"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 场景类型 信息，最多输入 1000 字符'" value="${sheetMain.sceneType}"/>
			</td>
 <td class="label">
				<!-- 经度 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.longitude"/>*
			</td>
			<td>
				<input type="text"  class="text" name="longitude" id="longitude"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 经度 信息，最多输入 1000 字符'" value="${sheetMain.longitude}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 纬度 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.latitude"/>*
			</td>
			<td>
				<input type="text"  class="text" name="latitude" id="latitude"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 纬度 信息，最多输入 1000 字符'" value="${sheetMain.latitude}"/>
			</td>
 <td class="label">
				<!-- 楼宇类型 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.buildingType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="buildingType" id="buildingType"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 楼宇类型 信息，最多输入 1000 字符'" value="${sheetMain.buildingType}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 楼宇层数 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.buildingNumber"/>*
			</td>
			<td>
				<input type="text"  class="text" name="buildingNumber" id="buildingNumber"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 楼宇层数 信息，最多输入 1000 字符'" value="${sheetMain.buildingNumber}"/>
			</td>
 <td class="label">
				<!-- 天线挂高 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.antennaHeight"/>*
			</td>
			<td>
				<input type="text"  class="text" name="antennaHeight" id="antennaHeight"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 天线挂高 信息，最多输入 1000 字符'" value="${sheetMain.antennaHeight}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 平台类型 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.platformType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="platformType" id="platformType"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 平台类型 信息，最多输入 1000 字符'" value="${sheetMain.platformType}"/>
			</td>
 <td class="label">
				<!-- 天线类型 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.antennaType"/>*
			</td>
			<td>
				<eoms:comboBox name="antennaType" id="antennaType" 
						 initDicId="1012006"  
						defaultValue="${sheetMain.antennaType}" alt="allowBlank:false"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 天线型号 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.antennaModel"/>
			</td>
			<td>
				<input type="text"  class="text" name="antennaModel" id="antennaModel"  alt="maxLength:1000,vtext:'请填入 天线型号 信息，最多输入 1000 字符'" value="${sheetMain.antennaModel}"/>
			</td>
 <td class="label">
				<!-- 经纬度符合要求 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.requirement"/>*
			</td>
			<td>
				<eoms:comboBox name="requirement" id="requirement" 
						 initDicId="10301"  
						defaultValue="${sheetMain.requirement}" alt="allowBlank:false"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 挂高符合要求 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.highRequirement"/>*
			</td>
			<td>
				<eoms:comboBox name="highRequirement" id="highRequirement" 
						 initDicId="10301"  
						defaultValue="${sheetMain.highRequirement}" alt="allowBlank:false"/>
			</td>
 <td class="label">
				<!-- 存在阻挡 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.existenceBarrier"/>*
			</td>
			<td>
				<eoms:comboBox name="existenceBarrier" id="existenceBarrier" 
						 initDicId="10301"  
						defaultValue="${sheetMain.existenceBarrier}" alt="allowBlank:false"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 其他情况 -->
				<bean:message bundle="plannconfirm" key="plannConfirmMain.otherCircumstance"/>
			</td>
			<td>
				<input type="text"  class="text" name="otherCircumstance" id="otherCircumstance"  alt="maxLength:2000,vtext:'请填入 其他情况 信息，最多输入 2000 字符'" value="${sheetMain.otherCircumstance}"/>
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
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>*
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="plannconfirm" alt="allowBlank:false"/> 				
		    </td>
	   </tr>			  
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 规划站址确认管理员
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="1871" flowId="617" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>