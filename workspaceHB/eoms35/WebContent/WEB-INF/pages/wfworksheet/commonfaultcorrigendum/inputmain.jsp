<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 
 request.setAttribute("roleId","191");
 
 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/commonfaultcorrigendum/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	//selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "commonfaultcorrigendum.do?method=newShowLimit&flowName=CommonfaultCorrigendum",
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
	<input type="hidden" name="processTemplateName" value="CommonfaultCorrigendum" />
	<input type="hidden" name="operateName" value="newWorkSheet" />
	<c:if test="${status!=1}">
	   
	  
<input type="hidden" name="phaseId" id="phaseId" value="NetCorrigendum" />

       <input type="hidden" id="operateType" name="operateType" value="0" />
       <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="phaseId" id="phaseId" value="OverTask" />
    </c:if>
    <input type="hidden" name="beanId" value="iCommonfaultCorrigendumMainManager"/> 
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumMain"/>	
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumLink"/>
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
				<!-- 网管告警流水号 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCommonfaultSheetId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCommonfaultSheetId" id="mainCommonfaultSheetId"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 网管告警流水号 信息，最多输入 2000 字符'" value="${sheetMain.mainCommonfaultSheetId}"/>
			</td>
 <td class="label">
				<!-- 网元名称 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCommonfaultNetName"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCommonfaultNetName" id="mainCommonfaultNetName"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 网元名称 信息，最多输入 2000 字符'" value="${sheetMain.mainCommonfaultNetName}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 勘误类型一级 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCorrigendumTypeOne"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCorrigendumTypeOne" id="mainCorrigendumTypeOne"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 勘误类型一级 信息，最多输入 2000 字符'" value="${sheetMain.mainCorrigendumTypeOne}"/>
			</td>
 <td class="label">
				<!-- 勘误类型二级 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCorrigendumTypeTwo"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCorrigendumTypeTwo" id="mainCorrigendumTypeTwo"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 勘误类型二级 信息，最多输入 2000 字符'" value="${sheetMain.mainCorrigendumTypeTwo}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 勘误类型三级 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCorrigendumTypeThree"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCorrigendumTypeThree" id="mainCorrigendumTypeThree"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 勘误类型三级 信息，最多输入 2000 字符'" value="${sheetMain.mainCorrigendumTypeThree}"/>
			</td> <td class="label">
				<!-- 网元类型 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainNetType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainNetType" id="mainNetType"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 网元类型 信息，最多输入 2000 字符'" value="${sheetMain.mainNetType}"/>
			</td>
		</tr>

		<tr><td class="label">
				<!-- 地市 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCity"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCity" id="mainCity"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 地市 信息，最多输入 2000 字符'" value="${sheetMain.mainCity}"/>
			</td>
<td class="label">
				<!-- 区县 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCounty"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCounty" id="mainCounty"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 区县 信息，最多输入 2000 字符'" value="${sheetMain.mainCounty}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 入库时间 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainSaveTime"/>*
			</td>
			<td>					
				<input type="text" class="text" name="mainSaveTime" readonly="readonly" 
						id="mainSaveTime" value="${eoms:date2String(sheetMain.mainSaveTime)}" 
						onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
			</td>
<td class="label">
				<!-- 创建人 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCreateUserId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCreateUserId" id="mainCreateUserId"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 创建人 信息，最多输入 2000 字符'" value="${sheetMain.mainCreateUserId}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 创建人所属部门 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainCreateDeptId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainCreateDeptId" id="mainCreateDeptId"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 创建人所属部门 信息，最多输入 2000 字符'" value="${sheetMain.mainCreateDeptId}"/>
			</td>
<td class="label">
				<!-- 是否自动移交 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainifAutotran"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainifAutotran" id="mainifAutotran"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 是否自动移交 信息，最多输入 2000 字符'" value="${sheetMain.mainifAutotran}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 维护班组 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainTeamRoleId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainTeamRoleId" id="mainTeamRoleId"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 维护班组 信息，最多输入 2000 字符'" value="${sheetMain.mainTeamRoleId}"/>
			</td>
<td class="label">
				<!-- 抄送对象 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainccObject"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainccObject" id="mainccObject"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 抄送对象 信息，最多输入 2000 字符'" value="${sheetMain.mainccObject}"/>
			</td>
		</tr>
 
		<tr><td class="label">
				<!-- 更新维护班组 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainnewTeamRoleId"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainnewTeamRoleId" id="mainnewTeamRoleId"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 更新维护班组 信息，最多输入 2000 字符'" value="${sheetMain.mainnewTeamRoleId}"/>
			</td>
<td class="label">
				<!-- 更新抄送对象 -->
				<bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumMain.mainnewccObject"/>*
			</td>
			<td>
				<input type="text"  class="text" name="mainnewccObject" id="mainnewccObject"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 更新抄送对象 信息，最多输入 2000 字符'" value="${sheetMain.mainnewccObject}"/>
			</td>
		</tr>
 <input type="button" class="submit" id="handcorrigendum" name="handcorrigendum" value="手工勘误" onclick="onhandcorrigendum(this)"/>
<script type="text/javascript">
function onhandcorrigendum(handcorrigendum){
var sheetKey = '8a9e63944700dc7801471bbe7e5d1bc6';
	Ext.Ajax.request({
		url : "${app}/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getReply&sheetKey=" + sheetKey,				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var reply = data[0].reply;
			if ('false'==reply) {
				alert('工单不在T1环节');
			}
		}
	});
	handcorrigendum.disabled = true;
}
</script>
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
		            scope="request" idField="sheetAccessories" appCode="commonfaultcorrigendum" alt="allowBlank:true"/> 				
		    </td>
	   </tr>	
</table>


<!--派单树-->
<br/>
<fieldset>
 	<legend>
     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
		 <span id="roleName">
		 	 集中监控维护班组
		 </span>
  	</legend>
    <div class="x-form-item" >
			<eoms:chooser id="sendObject"  type="role" roleId="8005106" flowId="620" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sheetMain.sendObject}" />
	</div>	
</fieldset>