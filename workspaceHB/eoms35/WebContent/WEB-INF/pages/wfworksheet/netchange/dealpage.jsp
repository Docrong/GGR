<%@ include file="/common/taglibs.jsp"%>
<%

 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType=="+operateType);
  System.out.println("=====>"+operateType);
  request.setAttribute("operateType",operateType);
%>

<c:choose>
  <c:when test="${taskName=='DraftTask'}">
  	<c:set var="methodValue" value="showDraftPage" />
  </c:when>
  <c:when test="${taskName=='RejectTask'}">
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

<c:url var="urlDeal" value="/sheet/netchange/netchange.do">
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
  <c:param name="processname" value="NetChangeMainProcess" /> 
</c:url> 

<script type="text/javascript">
var ifAccept;
var reviewResult;
var roleTree;
var v;
function initPage(){
	v = new eoms.form.Validation({form:'theform'});
	if("${taskName}"!="DraftHumTask"){
        $('btns').removeClass('hide');	
    }
	if("${taskName}"!="cc"&&"${operateType}"!='4'&&"${operateType}"!='124'&&"${operateType}"!='111'&&"${operateType}"!='121'&&"${operateType}"!='122'&&"${operateType}"!='11'&&"${operateType}"!='123'){
	v.preCommitUrl = "${app}/sheet/netchange/netchange.do?method=performPreCommit";	
	}
 } 
Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}";
   	if (dealTemplateId != null && dealTemplateId != "" && taskName != "DraftTask") {	
   		strUrl = '${app}/sheet/netchange/netchange.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId='+dealTemplateId;
	}
 
	var config = {
		url:strUrl,
		callback : initPage
	}
	
	
	eoms.util.appendPage("idSpecial",config);
});


  function changeType1(){
   	//if($('${sheetPageName}phaseId').value == "ProjectCreateTask"){
	     if($('${sheetPageName}mainIfRequireProject').value=="1030101"){
		   	$('${sheetPageName}phaseId').value = "AuditTask";
		   	$('${sheetPageName}operateType').value = "110";
		   	$('${sheetPageName}activeTemplateId').value = "ProjectCreateTask";
		}else{
			$('${sheetPageName}phaseId').value = "ProjectCreateTask";
			$('${sheetPageName}operateType').value = "3";
		}
   	//}else{
	//	$('${sheetPageName}phaseId').value = "ProjectCreateTask";
	//	$('${sheetPageName}operateType').value = "3";   	
   	//}
   }
   function changeType2(){
   
   	$('${sheetPageName}phaseId').value = "DraftTask";
   	$('${sheetPageName}operateType').value = "22";  	
   	//alert($('${sheetPageName}operateType').value);
   	
   }
   function changeType3(){
   
   	//if($('${sheetPageName}phaseId').value == "ProjectCreateTask"){
	     if($('${sheetPageName}mainIfRequireProject').value=="1030101"){
		   	$('${sheetPageName}phaseId').value = "AuditTask";
		   	$('${sheetPageName}operateType').value = "110";
		   	$('${sheetPageName}activeTemplateId').value = "ProjectCreateTask";
//		}
   	}else{
		$('${sheetPageName}phaseId').value = "ProjectCreateTask";
		$('${sheetPageName}operateType').value = "54";   	
   	}
   	
   }
   function saveDealInfo(){
    var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
    v.passing = false;
    Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "${app}/sheet/netchange/netchange.do?method=performSaveInfo",
				success: function(){
		        	alert("${eoms:a2u('保存成功！')}");
		 		}
		    });
	}
   }
   
   
  function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
    v.passing = false;
   	if (confirm("${eoms:a2u('确认保存模板吗？')}")) {
	    if (type == "new"){
		   	Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "netchange.do?method=saveDealTemplate&operateType=${operateType}",
				success: function(){
		        	alert("${eoms:a2u('保存模板成功！')}");
		 		}
		    });
	   	}else {
	   		Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "netchange.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}&operateType=${operateType}",
				success: function(){
		        	alert("${eoms:a2u('保存模板成功！')}");
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
   		if (confirm("${eoms:a2u('确认保存模板吗？')}")) {
	   	if (templateManage == "templateManage") {
	   		form.action = "${app}/sheet/netchange/netchange.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "netchange.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("${eoms:a2u('保存模板成功！')}");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "netchange.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
					success: function(){
			        	alert("${eoms:a2u('保存模板成功！')}");
			 		}
			    });
		   	}
	   	}
   	}
   	}
  }
function ifisCopy() {
	try{ 
	  	var ope= '${operateType}';
  		if(ope == '11'||ope == '55'||ope == '4'||ope == '124')
  		{
   			$('${sheetPageName}hasNextTaskFlag').value = 'true';  
  		}
	 var str=document.forms[0]['${sheetPageName}copyPerformer'].value;
	 var taskName=document.forms[0]['taskName'].value;
	 // eoms.log(document.forms[0]['${sheetPageName}copyPerformer'],str);
	if(taskName=="cc"||taskName=="reply"||taskName=="advice"){
	  if(str==null||str=="")
	  $('${sheetPageName}hasNextTaskFlag').value = 'true';  
	 }
	}
	catch(e){}
} 

</script>

<div id="sheetform">
  <html:form action="/netchange.do?method=newPerformDeal" styleId="theform">
	<table>
	  <tr>
	  <td width="100%" align="left">
	  <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4' && operateType != '115' && operateType != '61'&&taskName!='DraftTask'&&taskName!='RejectTask'}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./netchange.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
		         <bean:message bundle="sheet" key="button.refereTemplate"/>
		 </html:button>
		 <c:choose> 
		<c:when test="${taskName=='DraftTask'}">
	    <!-- <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveTemplate('new')">
	          	<bean:message bundle="sheet" key="button.saveTemplate"/>
	    </html:button>
	    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveTemplate('current')">
	          	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
	    	</html:button>
		</c:if> -->
	    </c:when>
	    <c:otherwise> 
	    	<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveDealTemplate('new')">
	          	<bean:message bundle="sheet" key="button.saveTemplate"/>
	    	</html:button>
	    	<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
				<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;saveDealTemplate('current')">
		          	<bean:message bundle="sheet" key="button.saveCurTemplate"/>
		    	</html:button>
			</c:if>
	    </c:otherwise>
	    </c:choose>
		<input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
		<input type="hidden" name="title" value="${sheetMain.title}"/>
		<input type="hidden" name="dictKey" value="dict-sheet-netchange"/>
   	  </c:if>
	</td>
	</tr>
	</table>
  	<!-- content -->
    <div id="idSpecial"></div>
       <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"/> 
   <c:choose> 
    <c:when test="${operateType=='61'}">    
		<div class="form-btns" id="btns">
		  <input type="submit" class="submit" onclick="this.form.action='${app}/sheet/netchange/netchange.do?method=performClaimTask'" 
		     value="<bean:message bundle='sheet' key='button.accept'/>" />
		</div>	 
   	</c:when>
    <c:when test="${operateType=='4'}">    
		<div class="form-btns" id="btns">
		  <input type="submit" class="submit" onclick="javascript:ifisCopy();" 
		     value="<bean:message bundle='sheet' key='button.reject'/>" />
		</div>	 
   	</c:when>
    <c:when test="${taskName=='DraftTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="submitone">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfoAndExit&draft=1'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=showListsendundo&draft=1'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>
    <c:when test="${taskName=='RejectTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType3();" styleId="submitone">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfoAndExit&draft=1'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=showListsendundo&draft=1'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>
    <c:when test="${taskName=='HoldTask'}">    
	    <!-- buttons -->
	    <div class="form-btns hide" id="btns">
         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
	      <html:button styleClass="btn" property="method.save" styleId="method.save"
		    onclick="v.passing=true;$('${sheetPageName}status').value = '0';javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		    	</html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;$('${sheetPageName}status').value = '0';this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>	 
   	</c:when>
    <c:otherwise>
    <c:if test="${taskName=='ExecuteTask'&&operateType!='11'}">
    	<font color=red>${eoms:a2u('为避免丢单,启动其他流程后请您尽快提交本工单!')}</font>
    </c:if>
	    <!-- buttons -->
	    <div class="form-btns hide" id="btns">
         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
	      <html:button styleClass="btn" property="method.save" styleId="method.save"
		    onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		    	</html:button><%-- <c:if test="${taskName!='cc'}">--%>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	       <%-- </c:if>--%>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/netchange/netchange.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>    
    </c:otherwise>    	   
   </c:choose>

</html:form>
</div>
