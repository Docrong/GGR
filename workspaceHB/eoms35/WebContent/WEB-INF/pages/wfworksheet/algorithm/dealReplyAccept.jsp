<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<style type="text/css">
.x-form-column{width:320px}
</style>

<script type="text/javascript">
function selfx(){
var put=document.getElementsByName("${sheetPageName}deal");
 for(i=0;i<put.length;i++){
   put[i].checked=(put[i].checked)?false:true;
 }
}
function selfcheckall(){
var put1=document.getElementsByName("${sheetPageName}checkall");
var put2=document.getElementsByName("${sheetPageName}checkbackall");
 if(put1[0].checked==true)
 {
 put2[0].checked=false;
 }
 else {
 put2[0].checked=true;
 }
 }
 function selfcheckbackall(){
var put1=document.getElementsByName("${sheetPageName}checkall");
var put2=document.getElementsByName("${sheetPageName}checkbackall");
 if(put2[0].checked==true)
 {
 put1[0].checked=false;
 }
 else {
 put1[0].checked=true;
 }
}
var v;
function check(){
 var put1=document.getElementsByName("${sheetPageName}checkall");
 var put2=document.getElementsByName("${sheetPageName}checkbackall");
 if (put1[0].checked == true) {
  put1[0].checked = false;
 }
 if (put2[0].checked == true) {
  put2[0].checked = false;
 }
 
}

Ext.onReady(function(){	
  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
		var checkArray = document.getElementsByName('${sheetPageName}deal');
		var _toid = document.getElementsByName('${sheetPageName}toid');
        var _toids = ",";
        var i=0;
  	    for (var c0 = 0; c0 < checkArray.length; c0++) {
 	     if(checkArray[c0].checked){
 	         i=1;
 	        _toids = _toids + _toid[c0].value+",";
 	       }
 	     }
 	   if(i==1){
 	      $('${sheetPageName}toTaskId').value = _toids.substring(1,_toids.length-1);
 	    }else{
 	        alert("请选择要回复的对象");
			return false;	
 	    }
 			
		return true;
	};
});
</script>
<div id="sheetform">
<html:form action="/algorithm.do?method=performDealReplyAccept" styleId="theform">
 <%@ include file="/common/taglibs.jsp"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String dealPerformer = (String)request.getAttribute("dealPerformer");
 String dealPerformerLeader = (String)request.getAttribute("dealPerformerLeader");
 System.out.println("dealPerformerLeader>>>>>>"+dealPerformerLeader);
 String dealPerformerType = (String)request.getAttribute("dealPerformerType");
 System.out.println("dealPerformer>>>>>>"+dealPerformer);
 %>

    <input type="hidden" name="${sheetPageName}beanId" value="iAlgorithmMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.algorithm.model.AlgorithmMain"/>	<!--main表Model对象类名-->	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.algorithm.model.AlgorithmLink"/> <!--link表Model对象类名--> 
 	<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="AlgorithmProcess" />
    <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
    <input type="hidden" name="${sheetPageName}toTaskId" id="${sheetPageName}toTaskId" value=""/> 

<%@ include file="/WEB-INF/pages/wfworksheet/algorithm/baseinputlinkhtmlnew.jsp"%>
      
     <table  class="listTable">
     <tr>
        <td class="label"><bean:message bundle="sheet" key="phase.pleaseSelect" /></br>
        <input type="checkbox" name="${sheetPageName}checkall" id="${sheetPageName}checkall" value="${toTask.operateRoleId}" onclick="selfcheckall();eoms.util.checkAll(this, '${sheetPageName}deal')" >
          全选</br>
        <input type="checkbox" name="${sheetPageName}checkbackall" id="${sheetPageName}checkbackall" value="${toTask.operateRoleId}" onclick="selfcheckbackall();selfx()" >
           反选
           </td>
        <td colspan="3">
       <table class="listTable">
         <logic:present name="acceptList" scope="request"> 
          <tr>
           <td class="label">
           目的任务对象
           </td>
           <td class="label">
          目的任务所有者 
           </td>
           <td class="label">
             任务名称
           </td>
        </tr>
      <logic:iterate id="toTask" name="acceptList" type="com.boco.eoms.sheet.algorithm.model.AlgorithmTask">  
        <tr>
          <td>
              <input type="checkbox" name="${sheetPageName}deal" id="${sheetPageName}deal" value="${toTask.operateRoleId}" onclick="check()" >
              <eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
              <input type="hidden" name="${sheetPageName}toid" id="${sheetPageName}toid" value="${toTask.id}" />
          </td>
           <td>
              <eoms:id2nameDB id="${toTask.taskOwner}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.taskOwner}" beanId="tawSystemUserDao" />&nbsp;
          </td>
          <td>
              <bean:write name="toTask" property="taskDisplayName" scope="page"/>
          </td>
        </tr>
      </logic:iterate> 
     </logic:present>
     </table>
     </td>
     </tr>

     <input type="hidden" name="${sheetPageName}operateType" value="6"/> 
	     <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3"> 
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		               alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'" >${sheetlink.remark}</textarea>
		  </td>
		</tr>
		</table>

		    
  <!-- buttons -->
		<div class="x-form-item">
			<div class="form-btns">
    		<html:submit styleClass="btn" property="method.save" styleId="method.save">
            	<fmt:message key="button.save"/>
        	</html:submit></div>
		</div>
 
</html:form>

</div>
