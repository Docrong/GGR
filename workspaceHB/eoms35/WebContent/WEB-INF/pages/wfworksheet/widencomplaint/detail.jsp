<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>


<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>


<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
String userId = sessionform.getUserid();
WidenComplaintTask task = new WidenComplaintTask();
String operaterType = "";
String ifsub = "";
String ifwaitfor="";
if(request.getAttribute("task")!=null){
	 task = (WidenComplaintTask)request.getAttribute("task");
	 taskStatus = task.getTaskStatus();
	 operaterType = task.getOperateType();
	 ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
	 ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
}

request.setAttribute("operaterType",operaterType);
BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
String sendUserId = basemain.getSendUserId();

request.setAttribute("taskStatus", taskStatus);
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

//add by weichao 20141229
String ift1dealer = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ift1dealer"));
String aa = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("a"));
System.out.println("aa>>>>>>"+aa+">>ift1dealer>>>>>>"+ift1dealer);
//add by weichao 20141229

//add by zhoupan 20160901
String maint2applytime ="";
if(taskName.equals("ExcuteHumTask")){ 
 String Id = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("sheetKey"));
 System.out.println("zp====taskName======"+taskName+",Id==="+Id);
 String sql="SELECT maint2applytime maint2  FROM widencomplaint_main WHERE 1=1 ";
	if (Id != null && Id.length() > 0)
		sql += " and id ='"+Id+"'"; 		
	IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	List list = service.getSheetAccessoriesList(sql);
	System.out.println("zp====detail==sql===="+sql);		
	Map	map=(Map)list.get(0);
	Timestamp maint2=(Timestamp)map.get("maint2");
	if(maint2 !=null ){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		maint2applytime = df.format(maint2);
	}
	

	System.out.println("maint2applytime===="+maint2applytime);

 }
 //add by zhoupan 20160901
 %>

<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	var tabConfig = {
		items : [{
			id : 'sheetinfo',
			text : '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.historyView"/>',
			url : 'widencomplaint.do?method=showSheetDealList&sheetKey=${sheetMain.id}&taskName=${taskName}&ifWaitForSubTask=${task.ifWaitForSubTask}'
		}, {
			text : '<bean:message bundle="sheet" key="sheet.flowchar"/>',
			url : 'widencomplaint.do?method=showWorkFlow&linkServiceName=iWidenComplaintLinkManager&dictSheetName=dict-sheet-widencomplaint&description=mainOperateType&sheetKey=${sheetMain.id}',
			isIframe : true
		  }
		,{
			text : '<bean:message bundle="sheet" key="sheet.filesView"/>',
			url : 'widencomplaint.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
			isIframe : true
		}, {
			text : '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
	 		url  : '../sheetRelation/sheetRelation.do?method=showInvokeRelationShipList&sheetKey=${sheetMain.id}',
			isIframe : true
		}]
	};
	var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
    <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
    	<%if(taskName.equals("cc")){ %>
			var url2="widencomplaint.do?method=newShowCCPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-10&taskStatus=${taskStatus}";
			eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("reply")){ %>
		var url2="widencomplaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
		eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskName.equals("advice")){ %>
		var url2="widencomplaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
		eoms.util.appendPage("sheet-deal-content",url2);	
	<%}}%>

 //add by weichao 20141226
 	<%if(taskStatus.equals("4")&&ift1dealer.equals("true")){ %>
	var url2="widencomplaint.do?method=showDealAsleep&sheetKey=${sheetMain.id}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
	<%if(taskStatus.equals("6")&&userId.equals(aa)){ %>
	var url2="widencomplaint.do?method=showActivationSheet&sheetKey=${sheetMain.id}";
	eoms.util.appendPage("sheet-deal-content",url2);	
	<%}%>
//add by weichao 20141226	

});
function forceOperation(obj){

	if(obj == 1){
	     
		var url2="widencomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else if(obj == 2){
	    
		var url2="widencomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
	}else{
	   
	    var url2="widencomplaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
		eoms.util.appendPage("sheet-deal-content",url2);	
    }
   }
    function eventOperation(obj){
	if(obj == 1){
	     var url2="widencomplaint.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
             eoms.util.appendPage("sheet-deal-content",url2,true);
	}
   }
   function operateType(url){
		var R = GetQueryString("operateType",url);
		var operateTypeObj = document.getElementById("operateType");
		operateTypeObj.value = opertateTypeValue;
		var divObj = document.getElementById("templateButtonDiv");
		if (opertateTypeValue == "") {
			divObj.style.display = "none";
		} else {
			divObj.style.display = "";
		}
   }
  function GetQueryString(name,url) {   
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");   
      var r = url.substr(1).match(reg);   
      if (r != null) return unescape(r[2]); return "";   
  }  
</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="sheetMain" scope="request">
	<%@ include file="/WEB-INF/pages/wfworksheet/widencomplaint/basedetailnew.jsp"%>
	<br/>
<table id="sheet" class="formTable" > 
 
	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.urgentDegree"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.urgentDegree}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
      <tr>
        <td class="label">受理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label">处理时限</td>
		<td class="content">
			<bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>

      <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.mainCompleteLimitT1"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT1" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.mainCompleteLimitT2"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCompleteLimitT2" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr> 
<tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType1"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType1}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType2"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType2}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType4"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType4}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType5"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType5}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType6"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType6}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>		  

	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintType7"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.complaintType7}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	</table>

	<br/>
	<table class="formTable"> 
	  <caption></caption>
      <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.btype1"/></td>
		<td class="content"><bean:write name="sheetMain" property="btype1" scope="request"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.bdeptContact"/></td>
		<td class="content"><bean:write name="sheetMain" property="bdeptContact" scope="request"/></td>
	  </tr> 
	<tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.bdeptContactPhone"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.bdeptContactPhone}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.repeatComplaintTimes"/></td>
		<td class="content">${mainRepeatNum1}
		&nbsp;
			<a href="${app}/sheet/widencomplaint/widencomplaint.do?method=showRepeatDetails1&mainJkaccount=${sheetMain.mainJkaccount}&customPhone=${sheetMain.customPhone}&mainid=${sheetMain.id}">重复投诉详情</a>
		</td>
	  </tr>	
	  <tr>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customerName"/></td>
		<td class="content"><bean:write name="sheetMain" property="customerName" scope="request"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customPhone"/></td>
		<td class="content"><bean:write name="sheetMain" property="customPhone" scope="request"/>&nbsp;
			<a href="${app}/sheet/widencomplaint/widencomplaint.do?method=showRepeatDetails&complaintTime=${sheetMain.complaintTime }&cusPhone=${sheetMain.customPhone}&mainid=${sheetMain.id}">重复投诉详情</a>
		</td>
	  </tr>	
	  <tr>
	  	<td class="label">是否重复投诉</td>
	  	<c:choose>
	  		<c:when test="${sheetMain.mainIfrepeat=='是' }">
	  			<td class="content"><font color="red" style="font-weight: bold">${sheetMain.mainIfrepeat}</font></td>
	  		</c:when>
	  		<c:otherwise>
	  			<td class="content">${sheetMain.mainIfrepeat}</td>
	  		</c:otherwise>
	  	</c:choose>
	  	
	  
	  	
		<td class="label">重复投诉量</td>
		<td class="content">${sheetMain.mainRepeatNum }
		</td>
	  </tr>
	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customLevel"/></td>
		<td class="content"><bean:write name="sheetMain" property="customLevel" scope="request"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customBrand"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.customBrand}" beanId="ItawSystemDictTypeDao"/></td>		
	  </tr>	
	  <tr>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.startDealCity"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.toDeptId}" beanId="tawSystemAreaDao"/></td>
	    <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.customAttribution"/></td>
		<td class="content"><bean:write name="sheetMain" property="customAttribution" scope="request"/></td>
	  </tr>					   	  
      <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintTime"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="complaintTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.faultTime"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="faultTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>  
      <tr>
        <td class="label">网元名称</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainNetname" scope="request"/>
		</td>
		<td class="label">账号</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainJkaccount" scope="request"/>
		</td>
	  </tr>  
      <tr>
        <td class="label">网格名称</td>
		<td class="content">
			<eoms:id2nameDB id="${sheetMain.maindealrolename}" beanId="tawSystemSubRoleDao" />
		</td>
		<td class="label">区县</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainCountycode" scope="request"/>
		</td>
	  </tr>  

	<tr>
	  <td class="label">客服流水号</td>
	  <td class="content" colspan="3">
	  	<pre><bean:write name="sheetMain" property="parentCorrelation" scope="request"/></pre>
	  </td>
	</tr>
	
	<tr>
        <td class="label">家宽用户星级</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainHomeUserstar" scope="request"/>
		</td>
		<td class="label">用户区域属性</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainAreaProperties" scope="request"/>
		</td>
	  </tr> 
	  
	  <tr>
        <td class="label">上网账号</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainOnlineAccount" scope="request"/>
		</td>
		<td class="label">互联网电视串号</td>
		<td class="content">
			<bean:write name="sheetMain" property="mainInternetNumber" scope="request"/>
		</td>
	  </tr> 
	
	
	
	

 <tr>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintNum"/></td>
		<td class="content"><bean:write name="sheetMain" property="complaintNum" scope="request"/></td>
		<td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.faultSite"/></td>
		<td class="content"><bean:write name="sheetMain" property="faultSite" scope="request"/></td>
	  </tr>
	  <tr>
	  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.terminalType"/></td>
	  <td class="content" colspan="3">
	  	<pre><bean:write name="sheetMain" property="terminalType" scope="request"/></pre>
	  </td>
	 </tr>	
	<tr>
	  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.complaintDesc"/>
	  </td>
	  <td class="content" colspan="3">
	  	<pre><bean:write name="sheetMain" property="complaintDesc" scope="request"/></pre>
	  </td>
	</tr>	
 
 <tr>
	  <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintMain.preDealResult"/></td>
	  <td class="content" colspan="3">
	  	<pre><bean:write name="sheetMain" property="preDealResult" scope="request"/></pre>
	  </td>
	 </tr>
	
	  <tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="widencomplaint" 
		             viewFlag="Y"/> 
		    </td>
	  </tr>
	  
<!-- add by weichao 20141229 -->
		<c:if test="${sheetMain.mainSleepStatus!='0' }">
			<tr>
				<td class="label">工单休眠状态</td>
				<td class="content">
					<c:if test="${sheetMain.mainSleepStatus=='1' }">申请休眠中</c:if>
					<c:if test="${sheetMain.mainSleepStatus=='2' }">休眠中</c:if>
					<c:if test="${sheetMain.mainSleepStatus=='3' }">休眠申请被驳回</c:if>
					<c:if test="${sheetMain.mainSleepStatus=='4' }">休眠工单已激活</c:if>
				</td>
				<td class="label">工单休眠时长</td>
				<td class="content">
					${sheetMain.mainSleepTime }天
				</td>
	  		</tr>	
	  		<tr>
				<td class="label">工单休眠申请人</td>
				<td  class="content">
					<eoms:id2nameDB id="${sheetMain.mainSleepUser}" beanId="tawSystemUserDao" />
				</td>			
					<td class="label">工单旧处理时限</td>
				<td class="content">
					<bean:write name="sheetMain" property="mainOldCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
				</td>
	  		</tr>
	  		<tr>
	  			<td class="label">工单休眠申请原因</td>
				<td colspan="3" class="content">
					${sheetMain.mainSleepReason }
				</td>
	  		</tr>
	  			<tr>
				<td class="label">工单休眠申请时间</td>
				<td class="content">
					<bean:write name="sheetMain" property="mainT2ApplyTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
				</td>
				<td class="label">工单休眠处理时间</td>
				<td class="content">
						<bean:write name="sheetMain" property="mainT1DealTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
			</td>
	  		</tr>	
	  			  			<tr>
				<td class="label">工单激活时间</td>
				<td class="content">
					<bean:write name="sheetMain" property="mainActivateTime" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
				</td>
				<td class="label">工单激活对象</td>
				<td class="content">
					${sheetMain.mainActivateDealer }
			</td>
	  		</tr>		
		</c:if>
<!-- add by weichao 20141229 -->

</table>
    </logic:present>
  </div>
</div>
<!-- Sheet Tabs End -->

<!-- 工单处理环节开始 -->
<!-- add by weichao 20141226 -->
<%if(taskStatus.equals("4")&&ift1dealer.equals("true")){ %>
<!-- 工单休眠 add by weichao 20141223 -->
<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<table>
	  <tr>
	    <td width="50%">	
	 <bean:message bundle="sheet" key="sheet.deal"/>:
<select id="dealSelector">
    <option>休眠审批</option>	  
</select>
	</td>
	</tr></table>
	</div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<%} %>
<%if(taskStatus.equals("6")&&userId.equals(aa)){ %>
<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<table>
	  <tr>
	    <td width="50%">	
	 <bean:message bundle="sheet" key="sheet.deal"/>:
<select id="dealSelector">
    <option>激活休眠工单</option>	  
</select>
	</td>
	</tr></table>
	</div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<%} %>
<!-- add by weichao 20141226 -->
<% if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>

<!-- Sheet Deal Content Start -->
<!-- 下面是流程中的步骤URL -->

<!-- 工单休眠 add by weichao 20141223 -->
<c:url var="urlShowAsleepOp" value="widencomplaint.do">
  <c:param name="method" value="showAsleepOp"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!-- 工单休眠 add by weichao 20141223 -->

<!-- 投诉二级处理 -->
<c:url var="urlShowExcuteHumTask102Page" value="widencomplaint.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="102"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	
<!-- 质检合格 -->
<c:url var="urlShowCheckYesDealPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="103"/>
</c:url>

<!-- 质检不合格 -->
<c:url var="urlShowCheckNoDealPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="104"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldTask18Page" value="widencomplaint.do">
	<c:param name="method" value="showDealPage"/>
	<c:param name="sheetKey" value="${sheetMain.id}"/>
	<c:param name="piid" value="${sheetMain.piid}"/>
	<c:param name="taskId" value="${taskId}"/>
	<c:param name="taskName" value="${taskName}"/>
	<c:param name="operateRoleId" value="${operateRoleId}"/>
	<c:param name="TKID" value="${TKID}"/>
	<c:param name="taskStatus" value="${taskStatus}"/>
	<c:param name="preLinkId" value="${preLinkId}"/>
	<c:param name="operateType" value="18"/>
	<c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>	

<!-- 流程中的步骤已结束 -->

<!-- 下面是公共的URL -->
<!-- 退回 -->
<c:url var="urlShowRejectDealPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>


<!-- 移交，由于不走showDealPage方法，所以不需要设置operateType,也不需要保存模板 -->
<c:url var="urlShowTransferkPage" value="widencomplaint.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="8"/>
</c:url>

<!-- 阶段回复 直接到另一个页面,不需要保存模板-->
<c:url var="urlShowPhaseReplyPage" value="widencomplaint.do">
  <c:param name="method" value="showPhaseBackToUpPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="reply"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="${operaterType}"/> 
</c:url>

<!--任务分派-->
<c:url var="urlShowInputSplit" value="widencomplaint.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="WidenComplaintSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="operateType" value="10"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
</c:url>

<!-- 处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="widencomplaint.do">
  <c:param name="method" value="showDealReplyAcceptPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="6"/>
</c:url>

<!-- 处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="widencomplaint.do">
  <c:param name="method" value="showDealReplyRejectPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="WidenComplaintSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="7"/>
</c:url>
<!-- 驳回上一级 -->
<c:url var="urlShowRejectBackPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="4"/>
</c:url>

<!-- 确认受理 -->
<c:url var="urlShowAcceptDealPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="61"/>  
</c:url>

<!-- 被驳回 -->
<c:url var="urlBackDealPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="46"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
  <c:param name="backFlag" value="yes"/>
</c:url>

<!-- 草稿 -->
<c:url var="urlShowDraftPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="3"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>


<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="11"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 会审 -->
<c:url var="urlShowInputSplitAudit" value="widencomplaint.do">
  <c:param name="method" value="showInputSplit"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="WidenComplaintSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/> 
  <c:param name="operateType" value="30"/>  
</c:url>

<!-- 会审回复 -->
<c:url var="urlShowDispatchAuditPage" value="widencomplaint.do">
  <c:param name="method" value="showDealPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="subtaskName" value="WidenComplaintSubTask"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="55"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
</c:url>

<!-- 转审 -->
<c:url var="urlShowTransferAuditPage" value="widencomplaint.do">
  <c:param name="method" value="showTransferWorkItemPage"/>
  <c:param name="sheetKey" value="${sheetMain.id}"/>
  <c:param name="piid" value="${sheetMain.piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="taskStatus" value="${taskStatus}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="88"/>
</c:url>


<script type="text/javascript">
	Ext.onReady(function(){
		eoms.Sheet.setPageLoader("dealSelector","sheet-deal-content");
		
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "widencomplaint.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>

<div class="sheet-deal">
	<div class='sheet-deal-header'>
	<!-- 下面是各个处理环节的下拉框功能 -->
		<table>
					<tr>
						 <td width="50%">  
						 	<%if(!taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) { %>
						 		<bean:message bundle="sheet" key="sheet.deal"/>:
						 	<%}%>
						 	
 							<!-- 投诉处理 -->
								<%   if(taskName.equals("ExcuteHumTask")){ %>
									<select id="dealSelector">
										
										<% if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									 		<!-- 确认受理 -->
									  		<option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									  		<!-- 驳回 -->
									  		<option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									 <% } else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowExcuteHumTask102Page}"><bean:message bundle="widencomplaint" key="operateType.ExcuteHumTask102"/> </option>
											  
											 
				  							  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
				  							 <%if(maint2applytime == null || "".equals(maint2applytime) ){%>
				  							  
				  							  <!-- 工单休眠 add by weichao 20141223 -->
				  							  <!-- <option value="${urlShowAsleepOp}">工单休眠</option>-->
				  							  <!-- 工单休眠 add by weichao 20141223 -->
				  							  
				  							  <% }%><!-- 条件判断 add by zhoupan 20160901 -->
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="widencomplaint" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
									<%}%>
									
							 	</select>
 							<!-- 质检 -->
								<%  } if(taskName.equals("CheckingHumTask")){ %>
									<select id="dealSelector">
									  <%if(taskStatus.equals(Constants.TASK_STATUS_READY)){%>
									   <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet" key="common.accept"/> </option>
									   <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet" key="common.rejectBack"/></option>
									  <%}else if(taskStatus.equals(Constants.TASK_STATUS_CLAIMED)){ %>
									   <option value="${urlShowCheckYesDealPage}">质检通过 </option>
									   <option value="${urlShowCheckNoDealPage}">质检不通过</option>			  
							  		  <%}%>
							  		</select>
 							<!-- 归档 -->
								<% }  if(taskName.equals("HoldTask")){ %>
									<select id="dealSelector">
										
									 	   <!-- 是父任务 -->
									 	  <%if(ifsub.equals("") || ifsub.equals("false") || ifsub == null){%>
									 	  	 <!-- 不需要等待回复 -->
									 	  <% if (ifwaitfor.equals("false")) {%>
											  <!-- 流程步骤，移交，阶段回复，分派 -->
											  <option value="${urlShowHoldTask18Page}"><bean:message bundle="widencomplaint" key="operateType.HoldTask18"/> </option>
											  
											  <!--<option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>-->
				  							  <!--<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>-->
									 		<% }%>
									 <%} else { %>
									 		<!-- 是子任务 -->
									 		<!-- 不需要等待回复 -->
									 	  	<% if (ifwaitfor.equals("false")) {%>
									 			<option value="${urlShowDispatchPage}"><bean:message bundle="widencomplaint" key="operateType.subTaskReply"/></option>
									 		<% }%>
									 <% } %>
									  <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
									  <c:if test="${needDealReply == 'true'}">
									       <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
									       <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
									  </c:if>
									
							 	</select>
							<% }else if(taskName.equals("RejectTask")){%>	
								<select id="dealSelector">
								  <option value="${urlBackDealPage}"><bean:message bundle="sheet" key="common.reSend"/></option>
								</select>
					  		<% }else if(taskName.equals("DraftTask")){%>	
								<select id="dealSelector">
								  <option value="${urlShowDraftPage}"><bean:message bundle="widencomplaint" key="operateType.DraftTask"/></option>
								</select>
					   		<% } %>
					</td>
				</tr>
		</table>
		
	<!-- 各个处理环节的下拉框功能结束 -->
	</div>
	<div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<!-- 工单处理环节结束 -->



<script type="text/javascript">
	Ext.onReady(function(){
	var url = "";
	try{
		 url = $("dealSelector").value + "&taskStatus=${taskStatus}";
		}catch(e){}
		var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
   			url = "widencomplaint.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId="+dealTemplateId
        }
		eoms.util.appendPage("sheet-deal-content",url);		
	});
</script>
<% }%>


<c:if test="${sheetMain.status==0}"> 
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
 <% if(isAdmin.equals("true")){%>
 	<div id="buttonDisplay" style="display:block">
   <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
   <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.forceNullity"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
   <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
 <% }else if((taskStatus == null || taskStatus.equals(""))&&(userId.equals(sendUserId))){%> 
 <div id="advice" style="display:block">
     <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="$('advice').style.display='none';forceOperation(2);">
     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
  </div>
 <% }%> 
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>
