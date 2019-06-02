<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>

<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String userId = sessionform.getUserid();
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 if(operateType.equals("102")){ 
 String mainId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("sheetKey"));
 String sql="SELECT MIN(operatetime) operatetime FROM widencomplaint_link WHERE operatetype='61' ";
	if (mainId != null && mainId.length() > 0)
		sql += " and mainid ='"+mainId+"'"; 		
	IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	List list = service.getSheetAccessoriesList(sql);
	System.out.println("zp====sql===="+sql);		
	Map	map=(Map)list.get(0);
	Timestamp operatetime=(Timestamp)map.get("operatetime");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	String minOperatetime = df.format(operatetime);
	System.out.println("minOperatetime===="+minOperatetime);
	request.setAttribute("minOperatetime",minOperatetime);
 
 
 }
 
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

<c:url var="urlDeal" value="/sheet/widencomplaint/widencomplaint.do">
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
	if("${taskName}"!="cc"&&"${operateType}"!='11'&&"${operateType}"!='55'){
		v.preCommitUrl = "${app}/sheet/widencomplaint/widencomplaint.do?method=performPreCommit";
	}
}

Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}";
   	if (dealTemplateId != null && dealTemplateId != "" && taskName != "draft") {	
   		strUrl = '${app}/sheet/widencomplaint/widencomplaint.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId='+dealTemplateId;
	}
 
	var config = {
		url:strUrl,
		callback : initPage
	}
	
	
	eoms.util.appendPage("idSpecial",config);
});

  function changeType1(){
	  
$('phaseId').value = "ExcuteHumTask";

   	$('operateType').value = "3";	
   }
  function changeType3(){
  	  
$('phaseId').value = "ExcuteHumTask";

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
				url: "${app}/sheet/widencomplaint/widencomplaint.do?method=performSaveInfo",
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
				url: "widencomplaint.do?method=saveDealTemplate&operateType=${operateType}",
				success: function(){
		        	alert("保存模板成功！");
		 		}
		    });
	   	}else {
	   		Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "widencomplaint.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}&operateType=${operateType}",
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
	   		form.action = "${app}/sheet/widencomplaint/widencomplaint.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "widencomplaint.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "widencomplaint.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
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
} 

//2016判断 linkFirstContactUesrTime 首次联系用户时间 不能 早于第一次接单时间 

		function checkOperatetime(){
			var d1='${minOperatetime}';
//			alert("d1==="+d1);
			var pDate = new Date(d1.replace(/-/g,"//"));
//        	alert("pDate==="+pDate);
								
			var d2 = document.getElementById("linkFirstContactUesrTime").value;
//			alert("linkFirstContactUesrTime==="+d2);
			var pDate2 = new Date(d2.replace(/-/g,"//"));
//			alert("pDate2==="+pDate2);
			if(pDate2<pDate){
					alert("首次联系用户时间不能早于第一次接单时间");
//					document.getElementById("linkFirstContactUesrTime").value="";
					return false;
			}else{
				ifisCopy();
				return true;
				
			}

	}
		

</script>

<div id="sheetform">
  <html:form action="/widencomplaint.do?method=newPerformDeal" styleId="theform">
  <input type="hidden" name="type" id="type" value="interface"/>
  <input type="hidden" name="interface"  id="interface" value="interface"/>
  <input type="hidden" name="userName"  id="userName" value="<%=userId%>"/>
	<table>
	  <tr>
	  <td width="100%" align="left">
	  <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61'&& operateType != '46'&& taskName != 'RejectTask'&& taskName != 'DraftTask'}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./widencomplaint.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
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
		<input type="hidden" name="dictKey" value="dict-sheet-widencomplaint"/>
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
		  <input type="submit" class="submit" onclick="this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=performClaimTask'" 
		     value="<bean:message bundle='sheet' key='common.accept'/>" />
		</div>	 
   	</c:when>
   	
    <c:when test="${taskName=='DraftTask'}">    
		<div class="form-btns" id="btns">
		<html:submit styleClass="btn" property="method.save"  styleId="submitone" onclick="javascript:changeType1();">
		 <bean:message bundle="sheet" key="button.send"/>
		</html:submit>
		  <html:button styleClass="btn" property="method.save" styleId="method.save"
		      onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=performSaveInfoAndExit&draft=1'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=showDraftList'" value="<bean:message bundle='sheet' key='button.back'/>" />
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
          <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>	 
   	</c:when>
    <c:otherwise>  
	    <!-- buttons -->
	    <div class="form-btns hide" id="btns">
	  <c:choose>
	    <c:when test="${taskName=='ExcuteHumTask' && operateType == '102'}">  
         	<input type="submit" class="submit" name="method.save" id="method.save"  onclick="return checkOperatetime();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
         </c:when>
         <c:otherwise> 
         	 <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();" value="<bean:message bundle='sheet' key='button.done'/>" >	      
         </c:otherwise>    	   
      </c:choose>
	      <html:button styleClass="btn" property="method.save" styleId="method.save"  onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		   </html:button>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/widencomplaint/widencomplaint.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>    
    </c:otherwise>    	   
   </c:choose>
</html:form>
</div>
