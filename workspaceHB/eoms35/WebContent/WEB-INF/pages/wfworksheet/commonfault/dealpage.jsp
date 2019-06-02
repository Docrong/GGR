<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
 <%@page import="com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager"%>
<%@ include file="/common/taglibs.jsp"%>
<logic:notEmpty name="batch">
	<%@ include file="/common/header_eoms_form.jsp"%>
	<script type="text/javascript">
	
		function batchSubmit() {
	  alert("不建议使用，请联系系统管理员!");		
	  this.document.forms[0].action = "./${module}.do?method=performBatchDeal&taskIds=${taskIds}" ;
		}
		function batchSubmithold() {
	//  alert("不建议使用，请联系系统管理员!");		
			this.document.forms[0].action = "./${module}.do?method=performBatchDeal&taskIds=${taskIds}" ;
		}
	</script>
</logic:notEmpty>

<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String userId = sessionform.getUserid();
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType=="+operateType);
  System.out.println("=====>"+operateType);
  request.setAttribute("operateType",operateType);

  
%>

<c:choose>
  <c:when test="${taskName=='DraftHumTask'}">
  	<c:set var="methodValue" value="showDraftPage" />
  </c:when>
  <c:when test="${taskName=='BackHumTask'}">
  	<c:set var="methodValue" value="showDraftPage" />
  </c:when>
   <c:when test="${taskName=='cc'}">
  	<c:set var="methodValue" value="showInputDealPage" />
  	<c:set var="operateType" value="-15"/>
  </c:when>
  <c:otherwise>
  	<c:set var="methodValue" value="showInputDealPage" />
  	<c:set var="operateType" value="<%=operateType %>" />
  </c:otherwise>
</c:choose>

<c:url var="urlDeal" value="/sheet/commonfault/commonfault.do">
  <c:param name="method" value="${methodValue}"/>
  <c:param name="sheetKey" value="${sheetKey}"/>
  <c:param name="taskStatus" value="${taskStatus}"/> 
  <c:param name="piid" value="${piid}"/>
  <c:param name="taskId" value="${taskId}"/>
  <c:param name="taskName" value="${taskName}"/>
  <c:param name="operateRoleId" value="${operateRoleId}"/>
  <c:param name="TKID" value="${TKID}"/>
  <c:param name="preLinkId" value="${preLinkId}"/>
  <c:param name="operateType" value="${operateType}"/>
  <c:param name="dealTemplateId" value="${dealTemplateId}"/>  
  <c:param name="backFlag" value="${backFlag}"/> 
  <c:param name="processname" value="CommonFaultMainFlowProcess" />
  <c:param name="taskIds" value="${taskIds}" />  
  <c:param name="batch" value="${batch}"/>
  <c:param name="roleLeader" value="${roleLeader}"/>
  <c:param name="centerMonitor" value="${centerMonitor}"/>   
</c:url> 



<div id="sheetform">
  <html:form action="/commonfault.do?method=performDeal" styleId="theform"> 	
  <input type="hidden" name="type" id="type" value="interface"/>
  <input type="hidden" name="interface"  id="interface" value="interface"/>
  <input type="hidden" name="userName"  id="userName" value="<%=userId%>"/>
  
	<table>
	  <tr>
	  <td width="100%" align="left">
	  <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61' && operateType != '46' && operateType != '53' }">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./commonfault.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
		         <bean:message bundle="sheet" key="button.refereTemplate"/>
		 </html:button>
		 <c:choose> 
		<c:when test="${taskName=='DraftHumTask'}">
	    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveTemplate('new')">
	          	<bean:message bundle="sheet" key="button.saveTemplate"/>
	    </html:button>
	    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveTemplate('current')">
	          	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
	    	</html:button>
		</c:if>
	    </c:when>
	    <c:otherwise> 
	    	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('new')">
	          	<bean:message bundle="sheet" key="button.saveTemplate"/>
	    	</html:button>
	    	<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
				<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
		          	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
		    	</html:button>
			</c:if>
	    </c:otherwise>
	    </c:choose>
		<input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
		<c:if test="${taskName!='DraftHumTask'}">
  	    	<input type="hidden" name="title" value="${sheetMain.title}"/>
  	    </c:if>
		<input type="hidden" name="dictKey" value="dict-sheet-commonfault"/>
   	  </c:if>
	</td>
	</tr>
	</table>
	

	
  	<!-- content -->
    <div id="idSpecial"></div>
   <c:choose> 
    <c:when test="${operateType=='61'}">    
		<div class="form-btns" id="btns">
		  <input type="submit" class="submit" onclick="this.form.action='${app}/sheet/commonfault/commonfault.do?method=performClaimTask'" 
		     value="<bean:message bundle='sheet' key='button.accept'/>" />
		</div>	 
   	</c:when>
    <c:when test="${taskName=='DraftHumTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="method.save">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfault.do?method=performSaveInfoAndExit&draft=1'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfault.do?method=showListsendundo&draft=1'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>
   	
    <c:when test="${taskName=='BackHumTask'}">    
		<div class="form-btns" id="btns">
			<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType3();" styleId="method.save">
			 <bean:message bundle="sheet" key="button.send"/>
			</html:submit>
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealInfo();">
			        <bean:message bundle='sheet' key='button.save'/>
			</html:button>
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>     	
    <c:when test="${taskName == 'HoldHumTask'}">  
    	 <logic:empty name="batch">
	    	<div class="form-btns" id="btns">
	         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();" value="<bean:message bundle='sheet' key='button.done'/>" >
	        </div>
        </logic:empty>
         <logic:notEmpty name="batch">
	   	 	<div class="form-btns hide" id="btns">
	         	<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:batchSubmithold();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
		    </div>
	    </logic:notEmpty>
   	</c:when> 
   	
    <c:otherwise>  
	    <!-- buttons -->
	    
        <c:if test="${taskName == 'FirstExcuteHumTask' || taskName == 'SecondExcuteHumTask' || taskName=='ThirdExcuteHumTask' || taskName=='subTask'}">
		  	<input type="button" title="knowledge" value="查看相关知识" onclick="searchKnowledge();" class="btn">
        </c:if>	 
        
        
        <%if(operateType.equals("4")){ %>	 
        	<c:if test="${(citySubrole == '0' && sheetMain.mainNetSortOne == '101010401' && taskName == 'SecondExcuteHumTask') || sheetMain.mainNetSortOne != '101010401'}">
        		<input type="button" title="申请转派其他单位" value="申请转派其他单位" onclick="sendotherbanz();" class="btn">
        	</c:if>
        <%} %>
           
        <logic:empty name="batch">
	    	<div class="form-btns hide" id="btns">
	    		<c:choose>
	    			<c:when test="${taskName=='cc'}">
	    				<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();return setStep();" value="已阅" >
	    				</c:when>
	    				<c:otherwise>
	         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();return setStep();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
		      <html:button styleClass="btn" property="method.save" styleId="method.save"
			    onclick="v.passing=true;javascript:saveDealInfo();">
			          	<bean:message bundle='sheet' key='button.save'/>
			    	</html:button><%-- <c:if test="${taskName!='cc'}">--%>
		      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfault.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
		       <%-- </c:if>--%>
		      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/commonfault/commonfault.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
		      	</c:otherwise>
		      </c:choose>	
		    </div>
	    </logic:empty>
	    <logic:notEmpty name="batch">
	   	 	<div class="form-btns hide" id="btns">
	         	<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:batchSubmit();return setStep();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
		    </div>
	    </logic:notEmpty>
	        
    </c:otherwise>    	   
   </c:choose>
   <script type="text/javascript">
var ifAccept;
var reviewResult;
var roleTree;
var v;
var deptid='<%=sessionform.getDeptid()%>';
v = new eoms.form.Validation({form:'theform'});	
v.custom = function(){ 
	if('${sheetMain.mainSendMode}' !='101030502' && ${operateType}==46){//如果是告警派过来的工单，并且在处理完成时需要校验
		    	if(deptid!='12201'){	
		    		
              if(($("mainAlarmSolveDate1").value==''&&$('${sheetPageName}nodeAccessories').value=='')&&($("mainAlarmSolveDate1").value==''&&$('${sheetPageName}linkFaultAvoidTime').value==''&&$("centerMonitor").value=='true')){
					    	alert('告警消除时间为空，不允许处理完成,如果要强制回复请申请告警核实或上传附件！');
						    return false;
			    	}else{
			    		if($('typecombo').style.display=='none'){
			    		 	return true;	
			    		}else{
				    		var linkFaultAvoidTime = $("${sheetPageName}linkFaultAvoidTime");
			    			if($('${sheetPageName}mainAlarmSolveDateType').value=='101030801'){
				    			v.preCommitUrl = "${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=updateEomsAlarmsolveDate&sheetKey=${sheetMain.id}&linkFaultAvoidTime="+linkFaultAvoidTime.value;
				    		 	return true;
			    			}else if($('${sheetPageName}mainAlarmSolveDateType').value=='101030802'){
			    				v.preCommitUrl = '${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=updateAlarmsolveDate&sheetKey=${sheetMain.id}&linkFaultAvoidTime='+linkFaultAvoidTime.value;
			    				return true;
			    			}
			    		}
			    	}
		    	}else{
		    		return true;
		    	}
	}else{
		return true;
	}
}

function initPage(){
   try{   
	$('btns').removeClass('hide');	
	if("${taskName}"!="cc"){
	v.preCommitUrl = "${app}/sheet/commonfault/commonfault.do?method=performPreCommit";	
	}
   }
   catch(e){
   }
 } 
Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}";
   	if (dealTemplateId != null && dealTemplateId != "" && taskName != "DraftHumTask") {	
   		strUrl = '${app}/sheet/commonfault/commonfault.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId='+dealTemplateId;
	}
 
	var config = {
		url:strUrl,
		callback : initPage
	}
	
	
	eoms.util.appendPage("idSpecial",config);
});


   function changeType1(){
   	//if($('${sheetPageName}phaseId').value == "FirstExcuteTask"){
	     if($('${sheetPageName}mainPretreatment').value=="1030101"){
		   	$('${sheetPageName}phaseId').value = "SecondExcuteTask";
		   	$('${sheetPageName}operateType').value = "1";
		   	$('${sheetPageName}activeTemplateId').value = "FirstExcuteHumTask";
		}else{
			$('${sheetPageName}phaseId').value = "FirstExcuteTask";
			$('${sheetPageName}operateType').value = "3";
		}
   	//}else{
		//$('${sheetPageName}phaseId').value = "FirstExcuteTask";
		//$('${sheetPageName}operateType').value = "3";   	
   		//alert($('${sheetPageName}phaseId').value);
   	//}
   }
   function changeType2(){
   	if($('${sheetPageName}mainPretreatment').value=="1030101"){
	   	$('${sheetPageName}phaseId').value = "SecondExcuteTask";
	   	$('${sheetPageName}operateType').value = "1";
	   	$('${sheetPageName}activeTemplateId').value = "FirstExcuteHumTask";
	}else{
   		$('${sheetPageName}phaseId').value = "DraftTask";
   		$('${sheetPageName}operateType').value = "22";  	
   	//alert($('${sheetPageName}operateType').value);
   	}
   }
   function changeType3(){
    if($('${sheetPageName}mainPretreatment').value=="1030101"){
	   	$('${sheetPageName}phaseId').value = "SecondExcuteTask";
	   	$('${sheetPageName}operateType').value = "1";
	   	$('${sheetPageName}activeTemplateId').value = "FirstExcuteHumTask";
	}else{
   		$('${sheetPageName}phaseId').value = "FirstExcuteTask";
   		$('${sheetPageName}operateType').value = "53";   	
   		//alert($('${sheetPageName}phaseId').value);
   	}
   }   
   

   
   
   function saveDealInfo(){
    var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
   	v.passing = true;
    Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "${app}/sheet/commonfault/commonfault.do?method=performSaveInfo",
				success: function(){
		        	alert("保存成功");
		 		}
		    });
    v.passing = false;
	}
   }
   
   
  function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
   	v.passing = true;
   	if (confirm("确认保存模板吗？")) {
	    if (type == "new"){
		   	Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "commonfault.do?method=saveDealTemplate",
				success: function(){
		        	alert("保存模板成功！");
		 		}
		    });
	   	}else {
	   		Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "commonfault.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}",
				success: function(){
		        	alert("'保存模板成功");
		 		}
		    });
	 	}
 	}
 	v.passing = false;
  }
 }
 function saveTemplate(type) {
   	var form = document.getElementById("theform");
    var ajaxForm = Ext.getDom(form);
   	var templateManage = "${type}";
   	  if(v.check()) {
   	  	v.passing = true;
   		if (confirm("确认保存模板吗？")) {
	   	if (templateManage == "templateManage") {
	   		form.action = "${app}/sheet/commonfault/commonfault.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "commonfault.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("保存模板成功");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "commonfault.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}
	   	}
   	}
   	v.passing = false;
   }
  }
function ifisCopy() {
	try{ 
	 var ope= '${operateType}';

	 if(ope == '11'|| ope == '55')
	 {	
	  $('${sheetPageName}hasNextTaskFlag').value = 'true';  
	 }
	   
	var taskName=document.forms[0]['taskName'].value;
	if(taskName=="cc"||taskName=="reply"||taskName=="advice"){
	  var str=document.forms[0]['${sheetPageName}copyPerformer'].value;
	  if(str==null||str=="")
	  $('${sheetPageName}hasNextTaskFlag').value = 'true';  
	 }
	}
	catch(e){
	}
} 


//add by libo
//
//复用时把得到的复用附件和上传附件拼接传给nodeAccessories
function copyNodeAccessories(){
	var node2 = document.getElementById("nodeAccessories2").value;
	var node1 = $('nodeAccessories').value;
	if(node2!=null && node2!=''){
		//var n = $('nodeAccessories').value;
		if(node1==''){
			document.getElementById("nodeAccessories").value = node2;
		}else{
			document.getElementById("nodeAccessories").value += ','+node2;
		}
	}
	//alert($('nodeAccessories').value);
}
function showCount(){
try{
 var count = document.forms[0]['${sheetPageName}mainRejectCount'].value;
 var mainRejectCount = parseInt(count,10)+1;
  $('${sheetPageName}mainRejectCount').value = mainRejectCount;  
  }catch(e){}
} 


function searchKnowledge()
{

	Ajax.Request(
	  "${app}/sheet/commonfault/commonfault.do?method=getSearchKnowledgeResult",
	  {
  		method:"GET",
  		parameters:"&sheetKey=${sheetMain.id}",
  		onComplete: handleCallBack
	  }
  	);
	var height = window.screen.height/6;
    var width = window.screen.width/4;

    features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    features += "dialogLeft:" + width + ";dialogTop:" + height;
}

function handleCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态

{
	var url=originalRequest.responseText;
	window.open(url);
}



</script>
</html:form>
</div>
