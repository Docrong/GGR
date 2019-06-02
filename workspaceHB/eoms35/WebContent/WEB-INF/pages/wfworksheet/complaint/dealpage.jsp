<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.complaint.model.ComplaintMain" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>

<%@page import="com.boco.eoms.sheet.complaint.service.IComplaintLinkManager" %>
<%@page import="java.util.*" %>

<%
String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
System.out.println("=====operateType=====>"+operateType);
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("taskName"));
System.out.println("=====taskName=====>"+taskName);
request.setAttribute("operateType",operateType);
String sheetKey = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("sheetKey"));

 TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");	
				String userId = sessionform.getUserid();
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

IComplaintLinkManager linkservice = (IComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
String condition = "mainId = '" + sheetKey + "' and operateType = '46' order by operateTime desc";
System.out.println("lyg:condition=" + condition);
List list = linkservice.getLinksBycondition(condition, "ComplaintLink");
Date linkplansolvedate = null;
if (list ! = null && list.size()>0)
{
	ComplaintLink complaintlink = (ComplaintLink)list.get(0);
	linkplansolvedate = complaintlink.getLinkPlanSolveDate();
}

%>

<c:choose>
  <c:when test="${taskName=='DraftHumTask'}">
  	<c:set var="methodValue" value="showDraftPage" />
  </c:when>
  <c:when test="${taskName=='ByRejectHumTask'}">
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

<c:url var="urlDeal" value="/sheet/complaint/complaint.do">
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
  <c:param name="processname" value="ComplaintProcess" />      
</c:url> 

<script type="text/javascript">
var ifAccept;
var reviewResult;
var roleTree;
var v;
function callbackfun() {
var url="${app}/sheet/complaint/complaint.do?method=showReturnCallPage&sheetid=${sheetMain.id}&preLinkId=${preLinkId}"
window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
}
function initPage(){
	v = new eoms.form.Validation({form:'theform'});
	if("${taskName}"!="DraftHumTask"){
        $('btns').removeClass('hide');	
     } 	
	if("${taskName}"!="cc"){
	   v.preCommitUrl = "${app}/sheet/complaint/complaint.do?method=performPreCommit";	
	}
 } 
Ext.onReady(function(){
	var dealTemplateId = "${dealTemplateId}";
	var strUrl = "${urlDeal}";
	var taskName = "${taskName}";
   	if (dealTemplateId != null && dealTemplateId != "" && taskName != "DraftHumTask") {	
   		strUrl = '${app}/sheet/complaint/complaint.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId='+dealTemplateId;
	}
 
	var config = {
		url:strUrl,
		callback : initPage
	}
	
	
	eoms.util.appendPage("idSpecial",config);
});


   function changeType1(){
   
   	$('${sheetPageName}phaseId').value = "FirstExcuteHumTask";
   	$('${sheetPageName}operateType').value = "3";   	
   	//alert($('${sheetPageName}phaseId').value);
   	
   }

   
   function saveDealInfo(){
    var form = document.forms[0];
   	var ajaxForm = Ext.getDom(form);
   	if(v.check()) {
   	v.passing = false;
    Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "${app}/sheet/complaint/complaint.do?method=performSaveInfo",
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
   	v.passing = true;
   	if (confirm("确认保存模板吗？")) {
	    if (type == "new"){
		   	Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "complaint.do?method=saveDealTemplate",
				success: function(){
		        	alert("保存模板成功！");
		 		}
		    });
	   	}else {
	   		Ext.Ajax.request({
		   		form: ajaxForm,
				method:"post",
				url: "complaint.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}",
				success: function(){
		        	alert("保存模板成功！");
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
	   		form.action = "${app}/sheet/complaint/complaint.do?method=saveTemplate&templateId=${templateId}&type=${type}";
	   		form.submit();
	   	} else {
		   	if (type == "new"){
			   	Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "complaint.do?method=saveTemplate&type=${type}",
					success: function(){
			        	alert("保存模板成功！");
			 		}
			    });
		   	}else {
		   		Ext.Ajax.request({
			   		form: ajaxForm,
					method:"post",
					url: "complaint.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
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
	 if(ope == '11'|| ope == '55' || ope == '4')
	 {
	  $('${sheetPageName}hasNextTaskFlag').value = 'true';  
	 }
	 var str=document.forms[0]['${sheetPageName}copyPerformer'].value;
	 var taskName=document.forms[0]['taskName'].value;
	if(taskName=="cc"||taskName=="reply"||taskName=="advice"){
	  if(str==null||str=="")
	  $('${sheetPageName}hasNextTaskFlag').value = 'true';  
	 }
	}
	catch(e){}
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
	  "${app}/sheet/complaint/complaint.do?method=getSearchKnowledgeResult",
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

function ifisDuban() { 
		Ext.Ajax.request({
			url : "${app}/sheet/complaint/complaint.do?method=ifisDuban&sheetKey=<%=sheetKey%>&taskName=${taskName}",			
			method: 'POST',
			success: function (res) {
				var data = eoms.JSONDecode(res.responseText);
				var linkplansolvetypeparent = data[0].linkplansolvetypeparent;
				var sheetId = data[0].sheetId;
				var operateUserId = data[0].operateUserId;
			}
		});
}
</script>

<div id="sheetform">
  <c:if test="${operateType=='46'||operateType=='11'}">
 <%if("101062501010101".equals(complaintType4)||"101062501010103".equals(complaintType4)||"101062501010402".equals(complaintType4)||"101062501010405".equals(complaintType4)||"101062501010401".equals(complaintType4)){%>	
	<form id="form1" name="form1" action="http://10.30.174.16:8089/AiNetEmos/testTask/toSaveTestTaskEoms" method="post"  target="_blank" >
		    <input type="hidden" value="${sheetMain.sheetId}"  id="emosID" name="emosID" />
		  <input type="hidden" value="EMOS" id="SysName" name="SysName" />
	    <input type="hidden" value="<%=username%>" id="eomsUser" name="eomsUser" />
		  <input type="hidden" value="" id="eomsCompany" name="eomsCompany"/>
		  <input type="hidden" value="<%=userdept%>" id="eomsDep" name="eomsDep"  />
		  <input type="hidden" value="<%=urgentDegreeCn%>" id="urgentDegree" name="urgentDegree"/>
		  <input type="hidden" value="<%=complaintTypeCn%>" id="complaintType" name="complaintType"  />
		  <input type="hidden" value="<%=customBrandCn%>" id="customBrand" name="customBrand"/>
	    <input type="hidden" value="${sheetMain.customLevel}" id="customLevel" name="customLevel" />
	    <input type="hidden" value="${sheetMain.customAttribution}" id="customAttribution" name="customAttribution" />
		  <input type="hidden" value="${sheetMain.faultSite}" id="faultSite" name="faultSite"  />
		  <input type="hidden" value="${sheetMain.faultTime}" id="faultTime" name="faultTime"  />
		  <input type="hidden" value="${sheetMain.title}" id="complaintDes" name="complaintDes"  />
		  <input type="hidden" value="${sheetMain.sheetAcceptLimit}" id="dealTime1" name="dealTime1"  />
		  <input type="hidden" value="${sheetMain.mainCompleteLimitT2}" id="dealTime2" name="dealTime2"  />
	  	<input type="button"  class="submit"  value="爱网络测试" onclick="EomsToTest();">
	</form>
			<%} %>
		</c:if>
  <html:form action="/complaint.do?method=performDeal" styleId="theform">
  	<input type="hidden" name="${sheetPageName}operateType" value="${operateType}"/>
  	<input type="hidden" name="type" id="type" value="interface"/>
  <input type="hidden" name="interface"  id="interface" value="interface"/>
  <input type="hidden" name="userName"  id="userName" value="<%=userId%>"/>
	<table>
	  <tr>
	  <td width="100%" align="left">
	  <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61'
	  	&& operateType != '8' && operateType != '1' && operateType != '46' && operateType != '11' && operateType != '56' && operateType != '54'
	  	 && operateType != '17' && operateType != '53' && operateType != '66' && operateType != '64' && operateType != '5' }">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="window.open('./complaint.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
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
		<input type="hidden" name="title" value="${sheetMain.title}"/>
		<input type="hidden" name="dictKey" value="dict-sheet-complaint"/>
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
		  <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=performClaimTask'" 
		     value="<bean:message bundle='sheet' key='button.accept'/>" />
		</div>	 
   	</c:when>

    <c:when test="${taskName=='DraftHumTask'}">    
		<div class="form-btns" id="btns">
			<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="method.save">
			 <bean:message bundle="sheet" key="button.send"/>
			</html:submit>
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealInfo();">
			        <bean:message bundle='sheet' key='button.save'/>
			</html:button>
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=showDraftList'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>

    <c:when test="${taskName=='ByRejectHumTask'}">    
		<div class="form-btns" id="btns">
			<html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="method.save">
			 <bean:message bundle="sheet" key="button.send"/>
			</html:submit>
			<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="v.passing=true;javascript:saveDealInfo();">
			        <bean:message bundle='sheet' key='button.save'/>
			</html:button>
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
		    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
		</div>	 
   	</c:when>   	

    <c:when test="${operateType=='4'}"> 
    	<div class="form-btns" id="btns">
         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();" value="<bean:message bundle='sheet' key='button.done'/>" >
        </div>
   	</c:when>    

    <c:when test="${taskName == 'HoldHumTask'}"> 
    	<div class="form-btns" id="btns">
         <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();" value="<bean:message bundle='sheet' key='button.done'/>" >
        </div>
   	</c:when>  

    <c:otherwise>  
	    <!-- buttons -->
    	<c:if test="${taskName=='FirstExcuteHumTask' || taskName=='SecondExcuteHumTask'}"> 
     	<input type="button" title="knowledge" value="查询知识库" onclick="searchKnowledge();">
     	</c:if>
     	<input type="button" class="submit" onclick="v.passing=true;javascript:callbackfun();" value="需要回访" /> 
	    
	    <div class="form-btns hide" id="btns">
	    	<%if(taskName.equals("CheckingHumTask")&&operateType.equals("56")&& linkplansolvedate != null){%>
          <input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();ifisDuban();" value="<bean:message bundle='sheet' key='button.done'/>" >
        <%}else {%>
         	<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:ifisCopy();showCount();" value="<bean:message bundle='sheet' key='button.done'/>" >
        <%}%>
 	      <html:button styleClass="btn" property="method.save" styleId="method.save"
		    onclick="v.passing=true;javascript:saveDealInfo();">
		          	<bean:message bundle='sheet' key='button.save'/>
		  </html:button><%-- <c:if test="${taskName!='cc'}">--%>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	       <%-- </c:if>--%>
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/complaint/complaint.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    </div>    
    </c:otherwise>   	   
  </c:choose>



</html:form>

</div>
