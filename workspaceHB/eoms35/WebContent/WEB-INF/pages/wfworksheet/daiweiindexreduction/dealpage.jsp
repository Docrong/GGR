<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<%
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 %>

<c:choose>
	  <c:when test="${taskName=='DraftTask'}">
	  	<c:set var="methodValue" value="showDraftPage" />
	  </c:when>
	    <c:when test="${taskName=='RejectTask'}">
	  	<c:if test="${operateType!='4' }">
	  	  	<c:set var="methodValue" value="showDraftPage" />
	  		<c:set var="operateType" value="54" />
	  	</c:if>
	  	<c:if test="${operateType=='4' }">
	  	  	<c:set var="methodValue" value="showInputDealPage" />
	  		<c:set var="operateType" value="4" />
	  	</c:if>
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

<c:url var="urlDeal" value="/sheet/daiweiindexreduction/daiweiindexreduction.do">
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
</c:url> 

<script type="text/javascript">
var ifAccept;
var reviewResult;
var roleTree;
var v;
function initPage(){
	v = new eoms.form.Validation({form:'theform'});
	if("${taskName}"!="DraftTask"){
        $('btns').removeClass('hide');	
    }
	if("${taskName}"!="cc"&&"${operateType}"!='4'&&"${operateType}"!='11'&&"${operateType}"!='55'){
		v.preCommitUrl = "${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performPreCommit";
	}
}

Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}";
   	if (dealTemplateId != null && dealTemplateId != "" && taskName != "draft") {	
   		strUrl = '${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId='+dealTemplateId;
	}
 
	var config = {
		url:strUrl,
		callback : initPage
	}
	
	
	eoms.util.appendPage("idSpecial",config);
});

  function changeType1(){
	  //草稿派发校验
	var listsize = document.getElementById("listsize").value;
	if( listsize == null || listsize== '' || listsize == 0 ){
		alert("数据校验不规范！");
		return false;
	}
$('phaseId').value = "TrialTask";

   	$('operateType').value = "3";	
   }
  function changeType3(){
  	  
$('phaseId').value = "TrialTask";

		$('operateType').value = "54";   	
  }
  function changeType2(){
   	$('phaseId').value = "DraftTask";
   	$('operateType').value = "22";  	
  }
   
   function saveDealInfo(){
    var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
   	v.passing = false;
    Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performSaveInfo",
				success: function(){
		        	alert("保存成功！");
		 		}
		    });
		  }
   }
   
   
  function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
   	v.passing = false;
   	if (confirm("确认保存模板吗？")) {
	    if (type == "new"){
		   	Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "daiweiindexreduction.do?method=saveDealTemplate&operateType=${operateType}",
				success: function(){
		        	alert("保存模板成功！");
		 		}
		    });
	   	}else {
	   		Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "daiweiindexreduction.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}&operateType=${operateType}",
				success: function(){
		        	alert("保存模板成功！");
		 		}
		    });
	 	}
 	}
}
 }
 function saveTemplate(type) {
   	var form = document.getElementById("theform");
    var ajaxForm = Ext.getDom(form);
   	var templateManage = "${type}";
   	if(v.check()) { 
   	v.passing = false;
   		if (confirm("确认保存模板吗？")) {
	   	if (templateManage == "templateManage") {
	   		form.action = "${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "daiweiindexreduction.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "daiweiindexreduction.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}
	   	}
   	}
  }
  }
function ifisCopy() {
	var mainReserveC = '${sheetMain.mainReserveC}';
	var ope2= '${operateType}';  // 操作人
	if(mainReserveC!='' && ope2 == '101'){
		document.getElementById("phaseId").value="HoldTask"; //到待归档
		document.getElementById("operateType").value="101";
	}
	if(ope2 == '101'){
		var obj = document.getElementById("iframepage").contentWindow.document;
		var infoId = obj.getElementsByName('infoId');
		var reason = obj.getElementsByName('reserveC');
		var desc = obj.getElementsByName('reserveD');
		for(var i = 0; i < reason.length; i++){ // 校验 核减理由是否成立 不能为空
			if(reason[i].value == ''){
				alert('请填入 核减理由是否成立 信息。');
				return false;
			}
		}
		for(var i = 0; i < desc.length; i++){ // 校验 核减说明 不能为空
			if(desc[i].value == ''){
				alert('请填入 说明 信息。');
				return false;
			}
		}
		var infoIds = ''; // 核减内容表id 
		var reasons = ''; // 核减理由是否成立 为 是或否
		var descs = '';  // 申请核减说明
		for(var i = 0; i < infoId.length; i++){
			if(i == 0){
				infoIds = infoId[i].value;
				reasons = reason[i].value;
				descs = desc[i].value;
			}else{
				infoIds += ',' + infoId[i].value;
				reasons += ',' + reason[i].value;
				descs += ',' + desc[i].value;
			}
		}
		if(document.getElementById("infoId") !=null){document.getElementById("infoId").value = infoIds;}
		if(document.getElementById("infoReserveC") !=null){document.getElementById("infoReserveC").value = reasons;}
		if(document.getElementById("infoReserveD") !=null){document.getElementById("infoReserveD").value = descs;}
	}
	try{ 
	 var ope= '${operateType}';
	 if(ope == '11'||ope == '55'||ope == '4') {
	  	$('hasNextTaskFlag').value = 'true';  
	 }	
	 var str=document.forms[0]['copyPerformer'].value;
	 var taskName=document.forms[0]['taskName'].value;
	if(taskName=="cc"||taskName=="reply"||taskName=="advice"){
	  if(str==null||str=="")
	  	$('hasNextTaskFlag').value = 'true';  
	 }
	} catch(e){}
	return true;
} 
function forceOperation(obj){
if(obj == 1){
var url2="daiweiindexreduction.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
eoms.util.appendPage("form-btns",url2); 
   }
   } 
</script>

<div id="sheetform">
  <html:form action="/daiweiindexreduction.do?method=performDeal" styleId="theform">

	<table>
	  <tr>
	  <td width="100%" align="left">
	  <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61'&& operateType != '46'&& taskName != 'RejectTask'&& taskName != 'DraftTask'}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./daiweiindexreduction.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
		         <bean:message bundle="sheet" key="button.refereTemplate"/>
		 </html:button>
	    	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveDealTemplate('new')">
	          	<bean:message bundle="sheet" key="button.saveTemplate"/>
	    	</html:button>
	    	<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
				<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveDealTemplate('current')">
		          	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
		    	</html:button>
			</c:if>
		<input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
		<input type="hidden" name="title" value="${sheetMain.title}"/>
		<input type="hidden" name="dictKey" value="dict-sheet-daiweiindexreduction"/>
   	  </c:if>
	</td>
	</tr>
	</table>
<!-- content -->
    <div id="idSpecial"></div>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag"/> 
    
    
   <c:choose> 
    <c:when test="${operateType=='61'}">    
		<div class="form-btns" id="btns">
		  <input type="submit" class="submit" onclick="this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performClaimTask'" 
		     value="<bean:message bundle='sheet' key='common.accept'/>" />
		</div>	 
   	</c:when>
   	
    <c:when test="${taskName=='DraftTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save"  styleId="submitone" onclick="return changeType1();">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performSaveInfoAndExit&draft=1'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=showDraftList'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>
   	<c:when test="${taskName=='RejectTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save"  styleId="submitone" onclick="javascript:changeType3();">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
          <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	   	 <input type="submit" class="submit" title="执行该操作，工单将进入强制归档状态，其他人不能再处理工单"  onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold'" value="放弃重派并归档" />	    
		</div>	 
   	</c:when>
    <c:otherwise>  
	    <!-- buttons -->
	    <div class="form-btns hide" id="btns">
         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="return ifisCopy();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
	      <html:button styleClass="btn" property="method.save" styleId="method.save"  onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		   </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>    
    </c:otherwise>    	   
   </c:choose>
</html:form>
</div>
