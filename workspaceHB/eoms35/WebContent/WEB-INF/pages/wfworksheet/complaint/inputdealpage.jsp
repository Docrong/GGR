<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%@ page import="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink" %>
<%@page import="com.boco.eoms.sheet.complaint.model.ComplaintMain" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.boco.eoms.commons.system.dict.model.TawSystemDictType" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 //add by chenyuanshu 2009-07-17 因为老版本投诉流程中phaseId为ShiftXXXX所以需要判断，如果是老流程数据，则按照老的流程执行
 //如果不修改，则会导致投诉工单移交、驳回丢单 
 boolean ifOldFlow = false;
 com.boco.eoms.sheet.base.model.BaseMain main = null;
 if(request.getAttribute("sheetMain")!=null){
   main = (com.boco.eoms.sheet.base.model.BaseMain)request.getAttribute("sheetMain");
}




System.out.println("lizhiteststatus="+main.getStatus());
//2009-07-15是贵州上线时间，所以这之前派的投诉工单按照老逻辑来流转
  java.util.Date onLinkDate = com.boco.eoms.sheet.base.util.SheetUtils.StringToDate("2009-07-15 00:00:00");

   if(main.getSendTime().before(onLinkDate)){
      ifOldFlow = true;
   }
   TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");	
 ComplaintMain cMain =new ComplaintMain();
cMain =(ComplaintMain)request.getAttribute("sheetMain");
String username =sessionform.getUsername();
String userdept = sessionform.getDeptname();
String urgentDegree= com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getUrgentDegree());
String complaintType1 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType1());
String complaintType2 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType2());
String complaintType3 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType());
String complaintType4 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType4());
String complaintType5 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType5());
String complaintType6 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType6());
String complaintType7 =com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType7());
String customBrand = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getCustomBrand());
ITawSystemDictTypeManager m = (ITawSystemDictTypeManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
String urgentDegreeCn = m.id2Name(urgentDegree);
String customBrandCn = m.id2Name(customBrand);
String complaintTypeCn = m.id2Name(complaintType1)+"."+ m.id2Name(complaintType2)+"."+ m.id2Name(complaintType3)+"."+ m.id2Name(complaintType4)+"."+ m.id2Name(complaintType5)+"."+ m.id2Name(complaintType6)+"."+ m.id2Name(complaintType7);

ArrayList dealDesclist = m.getDictSonsByDictid("1010627");
ArrayList dealResultlist = m.getDictSonsByDictid("1010611");
 %> 

 <script type="text/javascript">
	var frm = document.forms[0];
	var count=0;
	var fm = eoms.form;

	function popupKnowledge()
	{
	
		Ajax.Request(
		  "${app}/sheet/complaint/complaint.do?method=createKnowledge",
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
		//处理时限不能超过工单完成时限
		//var dtnow = new Date();
		//var str = '${sheetMain.sheetCompleteLimit}';
		//str = str.replace(/-/g,"/");
		//str = str.substring(0,str.length-2);
		//var dt2 = new Date(str);
		//if(dt2>dtnow){
		//	document.getElementById("tmpCompleteLimit").value='${sheetMain.sheetCompleteLimit}';
		//}else{
		//	document.getElementById("tmpCompleteLimit").value='';
		//}

	function selectTypeOne(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		if(document.getElementById("issueReasonTypeOne").selectedIndex == 0){
			document.getElementById("${sheetPageName}issueEliminatReason").value="";
		}else{
			document.getElementById("${sheetPageName}issueEliminatReason").value=a;
		}
	}
		function selectTypeTwo(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
		var bvalue = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].value;
		var bplan = document.getElementById("linkIsComplaintSolve").options[document.getElementById("linkIsComplaintSolve").selectedIndex].value;

		if(document.getElementById("issueReasonTypeTwo").selectedIndex == 0){
			document.getElementById("${sheetPageName}issueEliminatReason").value=a;
		}else{
			document.getElementById("${sheetPageName}issueEliminatReason").value=a+"|"+b;
			if("10106150107"==bvalue && "1030102"==bplan){
				eoms.form.enableArea('typecombop');
		  document.getElementById('typecombop').style.display='block';
			 }					

		}
	}
	function selectTypeThree(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
		var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
		if(document.getElementById("issueReasonTypeThree").selectedIndex == 0){
			document.getElementById("${sheetPageName}issueEliminatReason").value=a+"|"+b;
		}else{
			document.getElementById("${sheetPageName}issueEliminatReason").value=a+"|"+b+"|"+c;
		}
	}

	
	function selectTypeFour(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
		var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
		var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;	
	        if(document.getElementById("issueReasonTypeFour").selectedIndex == 0){
			document.getElementById("${sheetPageName}issueEliminatReason").value=a+"|"+b+"|"+c;
		}else{
				document.getElementById("${sheetPageName}issueEliminatReason").value=a+"|"+b+"|"+c+"|"+d;		
		}
	}
	
		function selectTypeFive(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
		var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
	
		var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;
				
  	var e = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].text;	

             var isseValue =document.getElementById("${sheetPageName}issueEliminatReason").value;
		if(document.getElementById("issueReasonTypeFive").selectedIndex == 0){
		   
				document.getElementById("${sheetPageName}issueEliminatReason").value=isseValue;		
		}else{
		    alert(isseValue);
     		document.getElementById("${sheetPageName}issueEliminatReason").value=isseValue+"|"+e;	
		}
	}
	
	
		function selectTypeSix(){
		var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
		var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
		var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
		
		var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;
					
  	var e = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].text;	
  	var f = document.getElementById("issueReasonTypeSix").options[document.getElementById("issueReasonTypeSix").selectedIndex].text;	
        var issfValue =	document.getElementById("${sheetPageName}issueEliminatReason").value;
		if(document.getElementById("issueReasonTypeSix").selectedIndex == 0){
				document.getElementById("${sheetPageName}issueEliminatReason").value=issfValue;		
		}else{
     		document.getElementById("${sheetPageName}issueEliminatReason").value=issfValue+"|"+f;	
		}
	}
	
function searchTicket(id){
	 alert(id);
Ext.Ajax.request({

		url : "${app}/sheet/complaint/eomsreplyticketclient.do?method=getEomsInfo&sheetKey="+id+"&type=interface&interface=interface",				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			$('${sheetPageName}ndeptContact').value = data[0].ndeptContact;
					$('${sheetPageName}ndeptContactPhone').value = data[0].ndeptContactPhone;
					$('${sheetPageName}issueEliminatReason').value = data[0].issueEliminatReason;
					$('${sheetPageName}dealDesc').value = data[0].dealDesc;
					$('${sheetPageName}linkReplyPerson').value = data[0].linkReplyPerson;
					$('${sheetPageName}linkReplayPhone').value = data[0].linkReplayPhone;
					$('${sheetPageName}linkDealDesc').value = data[0].linkDealDesc;
					$('${sheetPageName}linkSpecialty').value = data[0].linkSpecialty;
					$('${sheetPageName}linkQuality').value = data[0].linkQuality;
					$('${sheetPageName}linkAddressCI').value = data[0].linkAddressCI;
					$('${sheetPageName}linkAddressName').value = data[0].linkAddressName;
					$('${sheetPageName}linkEquipmentType').value = data[0].linkEquipmentType;
					$('${sheetPageName}linkEquipmentFactory').value = data[0].linkEquipmentFactory;
					$('${sheetPageName}compProp').value = data[0].compProp;
					$('${sheetPageName}dealResult').value = data[0].dealResult;
					$('${sheetPageName}linkIsUserStatisfied').value = data[0].linkIsUserStatisfied;
					$('${sheetPageName}isReplied').value = data[0].isReplied;
					$('${sheetPageName}linkIsComplaintSolve').value = data[0].linkIsComplaintSolve;
					$('${sheetPageName}linkIsUserConfirm').value = data[0].linkIsUserConfirm;
					$('${sheetPageName}linkIsRepeatComplaint').value = data[0].linkIsRepeatComplaint;
					$('${sheetPageName}linkIsContactUser').value = data[0].linkIsContactUser;
					$('${sheetPageName}linkIsAlarm').value = data[0].linkIsAlarm;
					$('${sheetPageName}issueEliminatTime').value = data[0].issueEliminatTime;
					$('${sheetPageName}linkCoverDian').value = data[0].linkCoverDian;
					$('${sheetPageName}linkCoverLian').value = data[0].linkCoverLian;
					$('${sheetPageName}issueReasonTypeOne').value = data[0].issueReasonType0;
					var sel = document.getElementById("issueReasonTypeTwo");
					sel.options[0].value= data[0].issueReasonType1;
					sel.options[0].innerText = data[0].issueReasonTypeName1;
					var sel1 = document.getElementById("issueReasonTypethree");
					sel1.options[0].value= data[0].issueReasonType2;
					sel1.options[0].innerText = data[0].issueReasonTypeName2;
					var sel2 = document.getElementById("issueReasonTypeFour");
					sel2.options[0].value= data[0].issueReasonType3;
					sel2.options[0].innerText = data[0].issueReasonTypeName3;

		}
	});	
}
	
function searchContent(id){
	Ext.Ajax.request({
		url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&operatedeptid=${sheetLink.operateDeptId}&type1=complaint&sheetid="+id+"&type=interface&interface=interface",				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var mainStatus = data[0].mainStatus;
			if(mainStatus == 1){
						if (data[0].nodeAccessories!=null&&data[0].nodeAccessories!="") {
							var a = eoms.$('nodeAccessories');
							a.value = "'"+data[0].nodeAccessories+"'";
							var s =document.getElementById("UIFrame1-nodeAccessories");
							s.src="/accessories/pages/upload.jsp?appId=complaint&filelist='"+data[0].nodeAccessories+"'&idField=nodeAccessories";
					 }
					$('${sheetPageName}ndeptContact').value = data[0].linkComContactUser;
					$('${sheetPageName}ndeptContactPhone').value = data[0].linkComContactPhone;
					$('${sheetPageName}compProp').value = data[0].linkComcompProp;
					$('${sheetPageName}isReplied').value = data[0].linkComIsContactUser;
					$('${sheetPageName}issueEliminatTime').value = data[0].linkComFaultEndTime;
					$('${sheetPageName}dealResult').value = data[0].linkComdealResult;
					$('${sheetPageName}issueEliminatReason').value = data[0].linkComTransmitReason;
					$('${sheetPageName}dealDesc').value = data[0].linkComLocalDealDesc;
					alert("代维工单已归档");
			} else {
				var dwTotal = data[0].dwTotal;
				if(dwTotal==0){
					alert("此工单无对应代维流程!");
				}else{
					var total = data[0].total;
					mainSheetState = data[0].mainSheetState;
					if(mainSheetState == 1){
						total = 0;
					}
					if(total==0){
						alert("此工单对应的代维流程未回复，无回复内容!");
					}else{
						document.getElementById('tr1').style.display='';
						document.getElementById('tr2').style.display='none';
						document.getElementById('tr3').style.display='';
						document.getElementById('select1').selectedIndex="0";
						if (data[0].nodeAccessories!=null&&data[0].nodeAccessories!="") {
							var a = eoms.$('nodeAccessories');
							a.value = "'"+data[0].nodeAccessories+"'";
							var s =document.getElementById("UIFrame1-nodeAccessories");
							s.src="/accessories/pages/upload.jsp?appId=complaint&filelist='"+data[0].nodeAccessories+"'&idField=nodeAccessories";
					 }
						$('${sheetPageName}ndeptContact').value = data[0].linkComContactUser;
						$('${sheetPageName}ndeptContactPhone').value = data[0].linkComContactPhone;
						$('${sheetPageName}compProp').value = data[0].linkComcompProp;
						$('${sheetPageName}isReplied').value = data[0].linkComIsContactUser;
						$('${sheetPageName}issueEliminatTime').value = data[0].linkComFaultEndTime;
						$('${sheetPageName}dealResult').value = data[0].linkComdealResult;
						$('${sheetPageName}issueEliminatReason').value = data[0].linkComTransmitReason;
						$('${sheetPageName}dealDesc').value = data[0].linkComLocalDealDesc;
						
						if(count==0){
							document.getElementById("tr1").style.display=""; 
							document.getElementById('tr2').style.display='none';
							document.getElementById('tr3').style.display='';
							document.getElementById('select1').selectedIndex="0";
						}
					}
				}
			}
		}
	});	
}
function openwin(id) {
	Ext.Ajax.request({
		url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&operatedeptid=${sheetLink.operateDeptId}&type1=complaint&sheetid="+id+"&type=interface&interface=interface",				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var dwTotal = data[0].dwTotal;
			if(dwTotal==0){
				var url="http://10.30.227.22:9080/sheet/agentmaintenance/agentmaintenance.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iComplaintMainManager"
				+"&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism&type1=complaint&type=interface&interface=interface&userName=${sheetLink.operateUserId}";
				window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
			}else{
				alert("此工单已调用代维流程!");
			}
			
		}
	});
}



//add by hanlili
function ifchoosePass(obj){
	var now = obj.selectedIndex;
	if("1"==now){
		document.getElementById('tr2').style.display='';
		document.getElementById('noAuditReason').value="请重新处理";
		document.getElementById('tr3').style.display='none';
	}else if("0"==now){
		document.getElementById('tr2').style.display='none';
		document.getElementById('tr3').style.display='';
	}
	
}
function ifPass(mainId){
	var select1 = document.getElementById('select1');
	var now = select1.selectedIndex
	if("0"==now){
		Ext.Ajax.request({
			url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=ifAllReply&operatedeptid=${sheetLink.operateDeptId}&id="+mainId,				
			method: 'POST',
			success: function (res) {
				var data = eoms.JSONDecode(res.responseText);
				var flag = data[0].flag;
				if (flag=="true") {
					var auditReason = document.getElementById('auditReason').value;
					if(null==auditReason || ""==auditReason){
						alert("请填写处理回复意见，且最多输入1000汉字");
					}else{
						admit(mainId,auditReason);
					}
				} else {
					alert("请查看所有子工单回复满意后再归档!");
				}
			}
		});
	}else if("1"==now){
		var noAuditReason = document.getElementById('noAuditReason').value;
		if(null==noAuditReason || ""==noAuditReason){
			alert("请填写工单驳回意见，且最多输入1000汉字");
		}else{
			noAdmit(mainId,noAuditReason);
		}
	}
}


function admit(id,auditReason) {
	
	if(mainSheetState == 0){
		alert("代维回复在此之前已经通过了");
	}else{
		Ext.Ajax.request({
			url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=performDeal1&operatedeptid=${sheetLink.operateDeptId}&operateType=211&phaseId=Receive&id="+id,				
			method: 'POST',
			params:{
				'auditReason':auditReason
			},
			success: function () {
				alert("代维工单已归档");
				document.getElementById("tr1").style.display="none";
				document.getElementById("tr3").style.display="none";
				count++;
			}
		});	
	}
}
function noAdmit(id,noAuditReason) {
	var url = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=performDeal1&operatedeptid=${sheetLink.operateDeptId}&operateType=212&phaseId=ExcuteHumTask&id="+id;
	if(mainSheetState == 0){
		alert("代维回复在此之前已经通过了");
	}else{
		Ext.Ajax.request({
			url : url,				
			method: 'POST',
			params:{	
				'id':id,
				'noAuditReason':noAuditReason
			},
			success: function () {
				alert("代维工单已驳回");
				document.getElementById("tr1").style.display="none";
				document.getElementById("tr2").style.display="none";
				count++;
			}
		});	
	}
}
</script>

<%if( operateType.equals("19")){%>

   <%@include file="/WEB-INF/pages/wfworksheet/complaint/hiddendealtitle.jsp"%>

 <%}else{%>
 
    <%@ include file="/WEB-INF/pages/wfworksheet/complaint/baseinputlinkhtmlnew.jsp"%>

<%}%>
<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
	<br/>      <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="ComplaintProcess" />
               <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
               <input type="hidden" name="${sheetPageName}beanId" value="iComplaintMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.complaint.model.ComplaintMain"/>	<!--main表Model对象类名-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.complaint.model.ComplaintLink"/> <!--link表Model对象类名-->
               <input type="hidden" name="linkBeanId" value="iComplaintLinkManager"/>
		<%if((taskName.equals("SecondExcuteHumTask")||taskName.equals("FirstExcuteHumTask")) && operateType.equals("4")){ %>	
			<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${fOperateroleid}"/>
		<%} else if (!taskName.equals("HoldHumTask")) { %>
			<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
		<%} %>	

     <table class="formTable">
     
   
     <%if(taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ) { %>
		

		<%if(operateType.equals("1")){ %>	
		<!-- 移交T2处理 -->	
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1" />		
			<%if(taskName.equals("FirstExcuteHumTask")){ %>
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
			<%} %>

	    	<tr>
		       <td class="label"><bean:message bundle="complaint" key="complaint.linkTransmitReason" /></td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}linkTransmitReason" class="textarea max" id="${sheetPageName}linkTransmitReason" 
			        alt="width:500,maxLength:1600,vtext:'最多输入800汉字'" alt="width:'500px'">${sheetLink.linkTransmitReason}</textarea>
			  </td>
			</tr>				
		<%} %>
		
		<%if(operateType.equals("8")){ %>	
		<!-- 组内 移交处理 -->	
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8" />
			
			<%if(ifOldFlow==false){%>
				<%if(taskName.equals("FirstExcuteHumTask")){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
				<%} %>
				<%if(taskName.equals("SecondExcuteHumTask")){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
				<%} %>
			<%}else{ %>			
				<%if(taskName.equals("FirstExcuteHumTask")){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
				<%} %>
				<%if(taskName.equals("SecondExcuteHumTask")){ %>
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT2Task" />
				<%} %>	
			<%} %>
	    	<tr>
		       <td class="label"><bean:message bundle="complaint" key="complaint.linkTransmitReason" /></td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}linkTransmitReason" class="textarea max" id="${sheetPageName}linkTransmitReason" 
			        alt="width:500,maxLength:1600,vtext:'最多输入800汉字'" alt="width:'500px'">${sheetLink.linkTransmitReason}</textarea>
			  </td>
			</tr>				
		<%} %>		



		<%if(operateType.equals("46") || operateType.equals("11") ){ %>
			<!-- 处理完成/分派回复 -->			
			
			<!--<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckingHumTask" /> -->	
			<%if(operateType.equals("46")){ %>
				<input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value=" " />
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value=46 />	
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckingHumTask" />

			 <tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkIfInvokeChange"/></td>
			  <td class="content" colspan="3">			   
			      <eoms:comboBox name="${sheetPageName}linkIfInvokeChange" id="${sheetPageName}linkIfInvokeChange" 
			      initDicId="10301" defaultValue="${sheetLink.linkIfInvokeChange}"  styleClass="select-class"/>
			  </td>
			 <!--   <td class="label"><bean:message bundle="complaint" key="complaint.linkChangeSheetId"/></td>
			  <td class="content">
				<input type="text" class="text" name="${sheetPageName}linkChangeSheetId" id="${sheetPageName}linkChangeSheetId" 
				value="${sheetLink.linkChangeSheetId}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>-->
			</tr>
			<%} %>
			<%if(operateType.equals("11")){ %>
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11" />	
			<%} %>	
			<input type="button" class="submit" value="投诉工单调用代维流程" onclick="openwin('${sheetMain.id}')"/>
  			&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="button" class="submit" value="代维回复内容" onclick="searchContent('${sheetMain.id}')"/>
  			&nbsp;&nbsp;&nbsp;&nbsp;
  			
	    <input type="button" class="submit" value="查询双向回复内容" onclick="searchTicket('HB-052-161019-74254')"/>
  			&nbsp;&nbsp;&nbsp;&nbsp;
  			
	  			<!-- add by hanlili 增加代维回复不通过时的不通过理由 -->
			<tr id="tr1" style="display:none">
				<td class="label">
					代维流程是否通过：
				</td>	
				<td class="content">
					<select id="select1" name="select1" onchange="ifchoosePass(this)">
						<option value="101130101">通过</option>
						<option value="101130102">不通过</option>
				</td>
				<td colspan="2">
					<input type="button" class="submit" value="确定" onclick="ifPass('${sheetMain.id}')" />
				</td>
			</tr>
			<tr id="tr2" style="display:none">
				<td class="label">工单驳回意见*</td>
				<td class="content" colspan="3">
					<textarea name="noAuditReason" id="noAuditReason" 
			   	 		class="textarea max">请重新处理</textarea>	
				</td>
			</tr>
			<tr id="tr3" style="display:none">
				<td class="label">处理回复意见*</td>
				<td class="content" colspan="3">
					<textarea name="auditReason" id="auditReason" 
			   	 		class="textarea max"></textarea>	
				</td>
			</tr>
			<br>
  		   <tr>
  		   <!-- 联系人 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.ndeptContact"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact" 
			  	value="${sheetLink.ndeptContact}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>			
			  <!-- 联系人方式 -->	
			  <td class="label"><bean:message bundle="complaint" key="complaint.ndeptContactPhone"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" 
			  	value="${sheetLink.ndeptContactPhone}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
			  </td>		  
			</tr>
			<tr>
			<!-- 投诉性质 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.compProp"/></td>
			   <td class="content">			   
			      <eoms:comboBox name="${sheetPageName}compProp" id="${sheetPageName}compProp" 
			      initDicId="1010610" defaultValue="${sheetLink.compProp}"  styleClass="select-class"/>
			  </td>
			  <!-- 是否已答复客户 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.isReplied"/></td>
			  <td class="content">			   
			      <eoms:comboBox name="${sheetPageName}isReplied" id="${sheetPageName}isReplied" 
			      initDicId="10301" defaultValue="1030101"  styleClass="select-class"/>
			  </td>
			</tr>
            <!-- <tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkFaultStartTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}linkFaultStartTime" readonly="readonly" 
					id="${sheetPageName}linkFaultStartTime" value="${eoms:date2String(sheetLink.linkFaultStartTime)}" 
					onclick="popUpCalendar(this, this,null,null,null,true,-1,-1)"
					alt="vtype:'lessThen',link:'${sheetPageName}linkFaultEndTime',vtext:'故障开始时间不能晚于故障结束时间',allowBlank:false" /> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkFaultEndTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}linkFaultEndTime" readonly="readonly" 
					id="${sheetPageName}linkFaultEndTime" value="${eoms:date2String(sheetLink.linkFaultEndTime)}" 
					onclick="popUpCalendar(this, this,null,null,null,true,-1)"
					alt="vtype:'moreThen',link:'${sheetPageName}linkFaultStartTime',vtext:'故障结束时间不能早于故障开始时间',allowBlank:false" />   
			  </td>		  
			</tr> 
			
  		   <tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkFaultGenerantPlace"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}linkFaultGenerantPlace" id="${sheetPageName}linkFaultGenerantPlace" 
			  	value="${sheetLink.linkFaultGenerantPlace}" alt="allowBlank:false,maxLength:100,vtext:'最多输入50汉字'"/>
			  </td>				
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkPlaceDesc"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}linkPlaceDesc" id="${sheetPageName}linkPlaceDesc" 
			  	value="${sheetLink.linkPlaceDesc}" alt="allowBlank:false,maxLength:100,vtext:'最多输入50汉字'"/>
			  </td>		  
			</tr>-->

  		   <tr>
  		   <!-- 问题消除时间 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.issueEliminatTime"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}issueEliminatTime" readonly="readonly" 
					id="${sheetPageName}issueEliminatTime" value="${eoms:date2String(sheetLink.issueEliminatTime)}" 
					onclick="popUpCalendar(this, this,null,null,null,true,-1)"
					alt="allowBlank:false" />   			  	
			  </td>
			  <!-- 处理结果 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.dealResult"/></td>
			  <td>
		  			  <select id="dealResult" name="dealResult" alt="allowBlank:false">
					<%
					if (dealResultlist.size() > 0 ) {
					 for(int i=0;i<dealResultlist.size();i++){
					 	  TawSystemDictType dealResult =  (TawSystemDictType)dealResultlist.get(i);
					 	  String dealResultname = dealResult.getDictName();
					%>
					<option value="<%=dealResultname%>"><%=dealResultname%></option> 
					<%} }%>
			  </select>
			  </td>				
			  	  
			</tr>

			<tr>
				<td class="label">
					问题原因分类
				</td>
				<td class="content" colspan="3">
	
					<eoms:comboBox name="issueReasonTypeOne" id="issueReasonTypeOne" sub="issueReasonTypeTwo" initDicId="1010615" onchange="selectTypeOne();" alt="allowBlank:true" />

					<eoms:comboBox name="issueReasonTypeTwo" id="issueReasonTypeTwo" sub="issueReasonTypeThree" styleClass="border" onchange="selectTypeTwo();" alt="allowBlank:true" />					
					<eoms:comboBox name="issueReasonTypeThree" id="issueReasonTypeThree" sub="issueReasonTypeFour" styleClass="border" onchange="selectTypeThree();" alt="allowBlank:true" />	
					<eoms:comboBox name="issueReasonTypeFour" id="issueReasonTypeFour" onchange="selectTypeFour();" alt="allowBlank:true" />
					 <div  id="typecombop" style="display:none">
			    <eoms:comboBox name="issueReasonTypeFive" id="issueReasonTypeFive" sub="issueReasonTypeSix" initDicId="10106150121"   onchange="selectTypeFive(this);" alt="allowBlank:false" />	       
					<eoms:comboBox name="issueReasonTypeSix" id="issueReasonTypeSix" onchange="selectTypeSix();" alt="allowBlank:false" />
	         </div> 	
				</td>
			</tr>
			
			<tr>
			<!-- 问题原因 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.issueEliminatReason"/></td>
			  <td colspan="3">
			  	 <textarea name="${sheetPageName}issueEliminatReason" id="${sheetPageName}issueEliminatReason" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'最多输入254汉字'"  readonly="true">${sheetLink.issueEliminatReason}</textarea>			   			 
			  </td>	
			</tr>
<%if("101062502".equals(complaintType1)){%>
			<tr>
			<!-- 解决措施 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.dealDesc"/></td>
			  <td colspan="3">
			  <select id="dealDesc" name="dealDesc" alt="allowBlank:false">
					<%
					if (dealDesclist.size() > 0 ) {
					 for(int i=0;i<dealDesclist.size();i++){
					 	  TawSystemDictType dealDesc =  (TawSystemDictType)dealDesclist.get(i);
					 	  String dealDescname = dealDesc.getDictName();
					%>
					<option value="<%=dealDescname%>"><%=dealDescname%></option> 
					<%} }%>
			  </select>
		    </td>
			</tr>
			
<%}else{%>			
			<tr>
			<!-- 解决措施 -->
			  <td class="label"><bean:message bundle="complaint" key="complaint.dealDesc"/></td>
			  <td colspan="3">
		  		<textarea name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'最多输入1000汉字'">${sheetLink.dealDesc}</textarea>			   
			  </td>
			</tr>
<%}%>			
			<!-- 湖北本地化需求增加字段 -->
			<tr>
				  <td class="label">回复人</td>
				  <td>
				  		<input type="text" class="text" name="linkReplyPerson" id="linkReplyPerson" value="${sheetLink.linkReplyPerson}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
				  </td>				
				  <td class="label">回复人联系电话</td>
				  <td>
				  		<input type="text" class="text" name="linkReplayPhone" id="linkReplayPhone" value="${sheetLink.linkReplayPhone}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
				  </td>		  
			</tr>
			<tr>
				  <td class="label">处理经过（解释口径）*</td>
				  <td colspan="3">
		  				<textarea name="linkDealDesc" id="linkDealDesc" class="textarea max" alt="allowBlank:false,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkDealDesc}</textarea>			   
			  	  </td>
					  
			</tr>
	<!--
			<tr>
					
				  <td class="label">故障类别</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkFaultType" id="linkFaultType" initDicId="1010613" defaultValue="${sheetLink.linkFaultType}" alt="allowBlank:ture" />
				  </td>	
				 	  
			</tr>
	
			<tr>
			 
				  <td class="label">业务类别</td>
				  <td>
				  		<eoms:comboBox name="linkBusinessType" id="linkBusinessType" initDicId="1010613" defaultValue="${sheetLink.linkBusinessType}" alt="allowBlank:ture" />
				  </td>
				  		
				  <td class="label">归因分类</td>
				  <td>
				  		<eoms:comboBox name="linkReasonType" id="linkReasonType" initDicId="1010613" defaultValue="${sheetLink.linkReasonType}" alt="allowBlank:ture" />
				  </td>		
				  <td class="label">是否收到故障工单</td>
				  <td>
				  		<eoms:comboBox name="linkIsReciveFaultId" id="linkIsReciveFaultId" initDicId="10301" defaultValue="${sheetLink.linkIsReciveFaultId}" alt="allowBlank:ture" onchange="changeReciveFaultId(this.value);"/>
				  </td> 			
				 	  
			</tr>
			-->	
			<tbody id='ReciveFaultId' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					故障工单号
	  				</td>
	  				<td colspan="3">
	  					<input type="text" class="text" name="linkReciveFaultId" id="linkReciveFaultId" value="${sheetLink.linkReciveFaultId}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			</tr>
	  		</tbody>
			<tr>
				  <td class="label">投诉是否解决</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsComplaintSolve" id="linkIsComplaintSolve" initDicId="10301" defaultValue="${sheetLink.linkIsComplaintSolve}" alt="allowBlank:false" onchange="changeComplaintSolve(this.value);"/>
				  </td>				
				 	  
			</tr>
			<tbody id='ComplaintSolveDate' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					解决时间
	  				</td>
	  				<td colspan="3">
	  					<input type="text" class="text" name="linkComplaintSolveDate" readonly="readonly" id="linkComplaintSolveDate" value="${eoms:date2String(sheetLink.linkComplaintSolveDate)}" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>   
	  				</td>
	  			</tr>
	  		</tbody>
	  		<tbody id='NoSolveReseason' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					是否计划解决
	  				</td> 
	  				<td colspan="3">
	  					<eoms:comboBox name="linkPlanSolveTypeparent" id="linkPlanSolveTypeparent" initDicId="1010616"  sub="linkPlanSolveType"  alt="allowBlank:false" onchange="changePlanSolveTypeparent(this.value);"/>
	  					<eoms:comboBox name="linkPlanSolveType" id="linkPlanSolveType"  defaultValue="${sheetLink.linkPlanSolveType}" alt="allowBlank:ture" onchange=""/>
	  				</td>
	  			</tr>
	  		</tbody>
	  		<tbody id='PlanSolveDate' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					计划解决时间*
	  				</td>
	  				<td colspan="3">
	  					<input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate" value="${eoms:date2String(sheetLink.linkPlanSolveDate)}" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>   
	  				</td>
	  			</tr>
	  		</tbody>
	  	<tbody id='PlanSolveDate2' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					计划解决时间*
	  				</td>
	  				<td colspan="3">
	  					<input type="hidden" name="${sheetPageName}planDate" id="${sheetPageName}planDate" value="${sheetPageName}planDate" />
	  					<input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate" value="${eoms:date2String(sheetLink.linkPlanSolveDate)}" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="vtype:'lessThen',link:'${sheetPageName}planDate',vtext:'计划解决时间不能晚于工单当日之后的6个月',allowBlank:false"/>
	  				</td>
	  			</tr>
	  		</tbody>
	  		<tbody id='PlanSolveDate3' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					计划解决时间*
	  				</td>
	  				<td colspan="3">
	  		  		<input type="hidden" name="${sheetPageName}planDate" id="${sheetPageName}planDate" value="${sheetPageName}planDate" />
         				<input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate" value="${eoms:date2String(sheetLink.linkPlanSolveDate)}" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="vtype:'lessThen',link:'${sheetPageName}planDate',vtext:'计划解决时间不能晚于工单当日之后的9个月',allowBlank:false"/>
	  				</td>
	  			</tr>
	  		</tbody>	
			<tr>
				 <td class="label">用户是否确认或签署回执单</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsUserConfirm" id="linkIsUserConfirm" initDicId="10301" defaultValue="${sheetLink.linkIsUserConfirm}" alt="allowBlank:ture" onchange="changeIsUserConfirm(this.value);"/>
				  </td> 	
			</tr>
			<tbody id='NoConfirmReason' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					原因
	  				</td>
	  				<td colspan="3">
	  					<textarea name="linkNoConfirmReason" id="linkNoConfirmReason" class="textarea max" alt="allowBlank:true,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkNoConfirmReason}</textarea>			   
	  				</td>
	  			</tr>
	  		</tbody>
			
			<tr>
				  <td class="label">投诉是否重复投诉</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsRepeatComplaint" id="linkIsRepeatComplaint" initDicId="10301" defaultValue="${sheetLink.linkIsRepeatComplaint}" alt="allowBlank:ture" onchange="changeIsRepeatComplaint(this.value);"/>
				  </td>
			</tr>
			<tbody id='IsRepeatComplaint' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					重复投诉原因
	  				</td>
	  				<td colspan="3">
	  					<eoms:comboBox name="linkRepeatComplaintType" id="linkRepeatComplaintType" initDicId="1010617" defaultValue="${sheetLink.linkRepeatComplaintType}" alt="allowBlank:ture" />
	  				</td>
	  			</tr>
	  		</tbody>
	  		
			<tr>				
				  <td class="label">用户满意情况</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsUserStatisfied" id="linkIsUserStatisfied" initDicId="1010618" defaultValue="${sheetLink.linkIsUserStatisfied}" alt="allowBlank:ture" onchange="changeIsUserStatisfied(this.value);"/>				  		
				  </td> 		  
			</tr>
			<tbody id='IsUserStatisfied' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					不满意原因
	  				</td>
	  				<td colspan="3">
	  					<textarea name="linkUserNoStatisfied" id="linkUserNoStatisfied" class="textarea max" alt="allowBlank:true,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkUserNoStatisfied}</textarea>			   
	  				</td>
	  			</tr>
	  		</tbody>
			
			<tr>
				  <td class="label">是否联系用户</td>                                      
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsContactUser" id="linkIsContactUser" initDicId="10301" defaultValue="${sheetLink.linkIsContactUser}" alt="allowBlank:ture" onchange="changeIsContactUser(this.value);" /> 		
				  </td>				
			</tr>
			<tbody id='IsContactUser' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					联系时间
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkContactDate" readonly="readonly" id="linkContactDate" value="${eoms:date2String(sheetLink.linkContactDate)}" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:true"/>
	  				</td>
	  				<td class="label" > 
	  					联系方式
	  				</td>
	  				<td>
	  					<eoms:comboBox name="linkContactship" id="linkContactship" initDicId="1010619" defaultValue="${sheetLink.linkContactship}" alt="allowBlank:ture" />
	  				</td>
	  			</tr>
	  			<tr> 
	  				<td class="label" > 
	  					联系人
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkContactUser" id="linkContactUser" value="${sheetLink.linkContactUser}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  				<td class="label" > 
	  					联系电话
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkContactPhone" id="linkContactPhone" value="${sheetLink.linkContactPhone}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			</tr>
	  		</tbody>
	  		<tbody id='NoContactReason' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					未与用户联系原因
	  				</td>
	  				<td colspan="3">
	  					<eoms:comboBox name="linkNoContactReason" id="linkNoContactReason" initDicId="1010621" defaultValue="${sheetLink.linkNoContactReason}" alt="allowBlank:ture" />
	  				</td>
	  			</tr>
	  		</tbody>
	  			<tr>
	  			  	<td class="label" > 
	  					投诉涉及专业
	  				</td>
	  				<td>
	  					<eoms:comboBox name="linkSpecialty" id="linkSpecialty" initDicId="1010620" defaultValue="${sheetLink.linkSpecialty}" alt="allowBlank:ture" />
	  				</td>
	  			  	<td class="label" > 
	  					投诉区域性质
	  				</td>
	  				<td>
	  					<eoms:comboBox name="linkQuality" id="linkQuality" initDicId="1010622" defaultValue="${sheetLink.linkQuality}" alt="allowBlank:ture" />
	  				</td>
	  			</tr>
	  			<tr>
	  			  	<td class="label" > 
	  					主覆盖小区CI
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkAddressCI" id="linkAddressCI" value="${sheetLink.linkAddressCI}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			  	<td class="label" > 
	  					主覆盖小区名称
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkAddressName" id="linkAddressName" value="${sheetLink.linkAddressName}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			</tr>
	  			<tr>
	  			  	<td class="label" > 
	  					设备类型
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkEquipmentType" id="linkEquipmentType" value="${sheetLink.linkEquipmentType}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			  	<td class="label" > 
	  					设备类型厂家
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkEquipmentFactory" id="linkEquipmentFactory" value="${sheetLink.linkEquipmentFactory}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			</tr>
			<tr>
				  <td class="label">是否有告警</td>
				  <td colspan="3">
				  		<eoms:comboBox name="linkIsAlarm" id="linkIsAlarm" initDicId="10301" defaultValue="${sheetLink.linkIsAlarm}" alt="allowBlank:ture" onchange="changeIsAlarm(this.value);" /> 		
				  </td>				
			</tr>
			<tbody id='IsAlarm' style="display:none">
	  			<tr> 
	  				<td class="label" > 
	  					告警详情
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkAlarmDetail" id=""linkAlarmDetail"" value="${sheetLink.linkAlarmDetail}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  				<td class="label" > 
	  					故障工单号
	  				</td>
	  				<td>
	  					<input type="text" class="text" name="linkCommonfaultNumber" id="linkCommonfaultNumber" value="${sheetLink.linkCommonfaultNumber}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
	  				</td>
	  			</tr>
	  		</tbody>
	  			<tr> 
	  				<td class="label" > 
	  					弱覆盖三网测试情况*
	  				</td>
	  				<td colspan="1.5">
	  					<eoms:comboBox name="linkCoverDian" id="linkCoverDian" initDicId="1010623" defaultValue="${sheetLink.linkCoverDian}" alt="allowBlank:false" />
	  				</td>
	  				<td colspan="2">
	  					<eoms:comboBox name="linkCoverLian" id="linkCoverLian" initDicId="1010624" defaultValue="${sheetLink.linkCoverLian}" alt="allowBlank:false" />
	  				</td>
	  			</tr>
			<!--湖北本地化需求增加字段  结束  -->

     <%if("101062501010101".equals(complaintType4)||"101062501010103".equals(complaintType4)||"101062501010402".equals(complaintType4)||"101062501010405".equals(complaintType4)||"101062501010401".equals(complaintType4)){%>	
     	<tr >
				  <td class="label">是否爱网络测试*</td>
				  <td colspan="3">
				  	   <eoms:comboBox name="${sheetPageName}linkIfAiNet"
				  	      id="${sheetPageName}linkIfAiNet" initDicId="10301" 
				  	       alt="allowBlank:false"
				  	      onchange="ifAiNet(this.value);"/>	   
				  </td>
				</tr>
	   
	  <tbody id='AiNetReasonBody' style="display:none">
	     <tr >
		   	<td class="label" >原因选择*</td>
			  <td colspan="3">	
			    <select id="selectAiNetReason" name="selectAiNetReason" onchange="selectAiNet();" alt="allowBlank:false,vtext:'原因类别未填写'">
	        <option value="0">其它</option> 
	      	<option value="网管处理无需测试">网管处理无需测试</option> 
				</td>
			</tr>
			<tr >
		   	<td class="label" >原因说明*</td>
			  <td colspan="3">
			  <textarea name="aiNetReasonDesc" id="aiNetReasonDesc" class="textarea max"
					  		alt=" allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" >${aiNetReasonDesc}</textarea>		
				</td>
			</tr>
			</tbody>
		 <tbody id='AiNetResultBody' style="display:none">
	     <tr >
		   	<td class="label" >测试结果*</td>
			  <td colspan="3">
			  <textarea name="aiNetResult" id="aiNetResult" class="textarea max"
					  		alt=" allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" >${aiNetResult}</textarea>		
				</td>
			</tr>
			</tbody>
				<%} %>
					<%} %>
		<%if(!operateType.equals("4")&&!operateType.equals("61")){%>	
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
			  <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			  <td colspan="3">				
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="complaint" />		   
			  </td>
			</tr>
		<%}%>  
		
     <%}%>
     <%if(operateType.equals("4")){ %>
		
			<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
				 <%if(taskName.equals("SecondExcuteHumTask")&& "101062502".equals(complaintType1)) {%>		
	 		<input type="hidden" name="${sheetPageName}copyPerformer" id="${sheetPageName}copyPerformer" value="${ccsubroleid}" />
	 		<input type="hidden" name="${sheetPageName}copyPerformerLeader" id="${sheetPageName}copyPerformerLeader" value="${ccsubroleid}" />
			<input type="hidden" name="${sheetPageName}copyPerformerType" id="${sheetPageName}copyPerformerType" value="subrole" />
				  <% } %>				
				  
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />

			<%if(ifOldFlow == false){ %>
		  	<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
				  <%if(taskName.equals("SecondExcuteHumTask")) {%>
				   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
				  <%}else { %>
				    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
			      <% } %>	
						</c:when>
			  	<c:when test="${fPreTaskName == 'DraftHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>				
			  	<c:when test="${fPreTaskName == 'FirstExcuteHumTask'}">
			  		<%if(taskName.equals("FirstExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
					<%}else{ %>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
					<%} %>
				</c:when>						
			  	<c:when test="${fPreTaskName == 'SecondExcuteHumTask'}">
			  		<%if(taskName.equals("SecondExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=SecondExcuteHumTask />
					<%}else{%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteHumTask" />
					<%}%>				
				</c:when>						
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>
			<%}else{ %>		
			<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
				  <%if(taskName.equals("SecondExcuteHumTask")) {%>
				   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
				  <%}else { %>
				    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
			      <% } %>	
						</c:when>
			  	<c:when test="${fPreTaskName == 'DraftHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>				
			  	<c:when test="${fPreTaskName == 'FirstExcuteHumTask'}">
			  		<%if(taskName.equals("FirstExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
					<%}else{ %>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT1Task" />
					<%} %>
				</c:when>						
			  	<c:when test="${fPreTaskName == 'SecondExcuteHumTask'}">
			  		<%if(taskName.equals("SecondExcuteHumTask")) {%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=ShiftScopeT2Task />
					<%}else{%>
						<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeT2Task" />
					<%}%>				
				</c:when>						
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>
			<%} %>
	    	<tr>
		        <td class="label"><bean:message bundle="sheet" key="linkForm.remark" />*</td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			  	</td>
			</tr>  	
		
		<%} %>
		
       <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />			 			
    	<tr>
	       	<td class="label"><bean:message bundle="sheet" key="linkForm.remark" />*</td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:'500px',maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.remark}</textarea>
		   	</td>
		</tr>	
		<%} %>


     <%if(taskName.equals("CheckingHumTask")) {%>
 		<!-- 质检通过 -->
 		<%if(operateType.equals("56")){ %> 		
	      	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldHumTask" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="56" />
	     	<input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030101" />
	     	<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/> 
 		
			<tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkCheckResult"/></td>
			  <td colspan="3">
  				是
  				<input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult" value="1030101" />		
			  </td>
			</tr>		 		
			<tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkCheckIdea"/>*</td>
			  <td colspan="3">
		  		<textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>			   
			  </td>
			</tr>		 		
 		<%} %>
		<!-- 质检不通过 -->
 		<%if(operateType.equals("54")){ %> 		
	      	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="54" />
 			<input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030102" />
 			<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/> 
 			<input type="hidden" name="${sheetPageName}mainQcRejectTimes" id="${sheetPageName}mainQcRejectTimes" value="${sheetMain.mainQcRejectTimes+1}"/>	
			<tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkCheckResult"/></td>
			  <td colspan="3">
 				否
 				<input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult" value="1030102" />		
			  </td>
			</tr>
			<tr>
			  <td class="label"><bean:message bundle="complaint" key="complaint.linkCheckIdea"/>*</td>
			  <td colspan="3">
		  		<textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max" alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>			   
			  </td>
			</tr>			 		
 		<%} %>

     <%}%>  

     <%if(taskName.equals("DeferExamineHumTask")) {%>
     
     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />     
		<%if(operateType.equals("66")){ %>		
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030101" />		
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="1" />		
		<%} %>		
		<%if(operateType.equals("64")){ %>				
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64" />
			<input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve" value="1030102" />	
			<input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="0" />				
		<%} %> 	
    	<tr>
	       <td class="label">
	        <bean:message bundle="complaint" key="complaint.linkExamineContent" />*
		    </td>
			<td colspan="3">			 
		           <textarea name="${sheetPageName}linkExamineContent" class="textarea max" id="${sheetPageName}linkExamineContent" 
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.linkExamineContent}</textarea>
		  </td>
		</tr>   		

     <%}%>  

     <%if(taskName.equals("HoldHumTask")) {%>
     
     	<%if(operateType.equals("17")){ %>
     	
     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17" />
		<tr>
		  <td class="label"><bean:message bundle="complaint" key="complaint.linkUntreadReason"/>*</td>
		  <td colspan="3">
	  		<textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max" 
	  		alt="allowBlank:false,maxLength:500,vtext:'最多输入250汉字'">${sheetLink.linkUntreadReason}</textarea>			   
		  </td>
		</tr>	
			     	
     	<%} %>


     	<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>			
			
		 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
		    </td>
		  </tr>
		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
		      alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>			
		  <tr>
		    	<td colspan="4">
		    		<input type="button" title="knowledge" value="新增知识库" onclick="popupKnowledge();">
		    	</td>
		   </tr>
		     	
     	<%} %>
     <%} %> 

 <!-- <%  if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %> --> 


	 </table>
	 
  <%if(operateType.equals("1")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="complaint" key="role.SecondExcute"/>			 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="215" flowId="052" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>	
	</fieldset>		
	<%} %>
  <%}%>
 
  <%if(operateType.equals("8")){ %>	 
  	<%if(taskName.equals("FirstExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="complaint" key="role.FirstExcute"/>		 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="214" flowId="052" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发对象'} ]"/>	
	</fieldset>		
	<%} %>
  	<%if(taskName.equals("SecondExcuteHumTask")) {%>  	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="complaint" key="role.SecondExcute"/>			 
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="215" flowId="052" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发对象'} ]"/>	
	</fieldset>		
	<%} %>	
  <%}%> 
  

   
  <%if(operateType.equals("46")){ %>	 
 	
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
	 		<bean:message bundle="complaint" key="role.check"/>	
	 </legend>
		<eoms:chooser id="commonfaultSendRole" type="role" roleId="217" flowId="052" data="[{id:'${zjSubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"   config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"/>		 
	</fieldset>		

  <%}%> 
  <script type="text/javascript">
	function changeReciveFaultId(objectvalue){
		if (objectvalue == '1030101') {
			eoms.form.enableArea('ReciveFaultId');	
		} else {
			eoms.form.disableArea('ReciveFaultId',true); 
		}
	}
	
	function changeComplaintSolve(objectvalue){
	var bvalue = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].value;
	
		if (objectvalue == '1030101') {
			eoms.form.enableArea('ComplaintSolveDate');
			eoms.form.disableArea('NoSolveReseason',true);
			eoms.form.disableArea('PlanSolveDate',true);  		
			eoms.form.disableArea('PlanSolveDate2',true);  	
			eoms.form.disableArea('PlanSolveDate3',true); 
			eoms.form.disableArea('typecombop',true);
		  document.getElementById('typecombop').style.display='none'; 	
		} else if (objectvalue == '1030102') {
		    if("10106150107"==bvalue){
			eoms.form.enableArea('typecombop');
		  document.getElementById('typecombop').style.display='block';
			 }else{
			eoms.form.disableArea('typecombop',true);
		  document.getElementById('typecombop').style.display='none';
			 }
			eoms.form.enableArea('NoSolveReseason');
			eoms.form.disableArea('ComplaintSolveDate',true); 
			eoms.form.disableArea('PlanSolveDate',true); 
			eoms.form.disableArea('PlanSolveDate2',true);  	
			eoms.form.disableArea('PlanSolveDate3',true);			  	
		} else {
			eoms.form.disableArea('ComplaintSolveDate',true); 
			eoms.form.disableArea('PlanSolveDate',true); 
			eoms.form.disableArea('NoSolveReseason',true); 
			eoms.form.disableArea('PlanSolveDate2',true);  	
			eoms.form.disableArea('PlanSolveDate3',true);  	
			eoms.form.disableArea('typecombop',true);
		  document.getElementById('typecombop').style.display='none';
		}
	}
	
	function changePlanSolveTypeparent(objectvalue){
if (objectvalue.charAt(8) == '1') {				
			var issueReasonTypeChose = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].value;
                   if(issueReasonTypeChose =='1010615012101'|| issueReasonTypeChose =='1010615012102'){	

	           var pDate = new Date().add(Date.MONTH,6);
	           $("${sheetPageName}planDate").value =pDate.format('Y-m-d H:i:s');	
	         	eoms.form.enableArea('PlanSolveDate2');  
			}else if (issueReasonTypeChose =='1010615012103'||issueReasonTypeChose =='1010615012104'||issueReasonTypeChose =='1010615012105'){
			   var pDate = new Date().add(Date.MONTH,9);		  
			       $("${sheetPageName}planDate").value =pDate.format('Y-m-d H:i:s');	
			    eoms.form.enableArea('PlanSolveDate3');  			
			}	else{
					eoms.form.enableArea('PlanSolveDate'); 
			}

		} else {
			eoms.form.disableArea('PlanSolveDate',true); 
		}  				
	}
	
	function changeNoSolveReseason(objectvalue){
		if (objectvalue.charAt(8) == '1') {
			eoms.form.enableArea('PlanSolveDate');
		} else {
			eoms.form.disableArea('PlanSolveDate',true); 
		}
	}
	
	function changeIsUserConfirm(objectvalue){
		if (objectvalue == '1030102') {
			eoms.form.enableArea('NoConfirmReason');
		} else {
			eoms.form.disableArea('NoConfirmReason',true); 
		}
	}
	function changeIsRepeatComplaint(objectvalue){
		if (objectvalue == '1030101') {
			eoms.form.enableArea('IsRepeatComplaint');
		} else {
			eoms.form.disableArea('IsRepeatComplaint',true); 
		}
	}
	function changeIsUserStatisfied(objectvalue){
		if (objectvalue == '101061503') {
			eoms.form.enableArea('IsUserStatisfied');
		} else {
			eoms.form.disableArea('IsUserStatisfied',true); 
		}
	}
	
	function changeIsContactUser(objectvalue) {
		if (objectvalue == '1030101') {
			eoms.form.enableArea('IsContactUser');
			eoms.form.disableArea('NoContactReason',true); 
			
		} else if (objectvalue == '1030102') {
			eoms.form.enableArea('NoContactReason');
			eoms.form.disableArea('IsContactUser',true); 
		} else {
			eoms.form.disableArea('IsContactUser',true); 
			eoms.form.disableArea('NoContactReason',true); 
		}
	}
	
	function changeIsAlarm(objectvalue) {
		if (objectvalue == '1030101') {
			eoms.form.enableArea('IsAlarm');
		} else {
			eoms.form.disableArea('IsAlarm',true); 
		}
	}
		function selectAiNet(){
		var a = document.getElementById("selectAiNetReason").options[document.getElementById("selectAiNetReason").selectedIndex].text;
		if(document.getElementById("selectAiNetReason").selectedIndex == 0){
			document.getElementById("${sheetPageName}aiNetReasonDesc").value="";
			document.getElementById("${sheetPageName}aiNetReasonDesc").disabled=false;
		}else{
			document.getElementById("${sheetPageName}aiNetReasonDesc").value=a;
			document.getElementById("${sheetPageName}aiNetReasonDesc").disabled='disabled';
		}
	}
	  var urlLocal="http://10.30.174.16:8089";	
		function ifAiNet(objectvalue){
		if (objectvalue == '1030102') {
		 eoms.form.enableArea('AiNetReasonBody'); 
	   eoms.form.disableArea('AiNetResultBody',true); 
		} else if (objectvalue == '1030101') {	
		 var emosID="${sheetMain.sheetId}";
        if(emosID!="") {
  	Ext.Ajax.request({
  	  
			url :  "${app}/sheet/complaint/complaint.do?method=getAiNetResult&sheetKey=${sheetMain.sheetId}",				
			method: 'POST',
		
			success: function (res) {
      var data = eoms.JSONDecode(res.responseText);
      var result= data[0].reply;
      if(null==result || ""==result){
           alert("没有测试结果，请填写");
           document.getElementById("${sheetPageName}aiNetResult").value ="";
           document.getElementById("${sheetPageName}aiNetResult").disabled=false;
           }else{
           document.getElementById("${sheetPageName}aiNetResult").value =result;
           document.getElementById("${sheetPageName}aiNetResult").disabled='disabled';
           }
			}
		});	        
        }
        else {
            alert("没有测试结果");
        }
	  eoms.form.enableArea('AiNetResultBody'); 
		eoms.form.disableArea('AiNetReasonBody',true); 
		} else {
	  eoms.form.disableArea('AiNetResultBody',true);
	  eoms.form.disableArea('AiNetReasonBody',true); 
		}
	}


  function EomsToTest()
  {
  document.getElementById("form1").action=urlLocal+"/AiNetEmos/testTask/toSaveTestTaskEoms";
	     document.getElementById("form1").submit();
	  }
	  
	 function EomsToSearch()
	 {
	  var url = urlLocal+"/AiNetEmos/testTask/toTestTaskList?emosID=${sheetMain.sheetId}";
	  window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	 }    

	
	
</script>