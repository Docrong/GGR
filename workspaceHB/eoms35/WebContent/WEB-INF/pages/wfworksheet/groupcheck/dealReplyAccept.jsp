<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function selfx(){
var put=document.getElementsByName("deal");
 for(i=0;i<put.length;i++){
   put[i].checked=(put[i].checked)?false:true;
 }
}
function check(){
	var put1=document.getElementsByName("checkall");
	var put2=document.getElementsByName("checkbackall");
	if (put1[0].checked == true) {
		put1[0].checked = false;
	}
	if (put2[0].checked == true) {
		put2[0].checked = false;
	}
	
}

function selfcheckall(){
	var put1=document.getElementsByName("checkall");
	var put2=document.getElementsByName("checkbackall");
	 if(put1[0].checked==true)
	 {
	 	put2[0].checked=false;
	 } else {
	 	put2[0].checked=true;
	 }
}

function selfcheckbackall(){
	var put1=document.getElementsByName("checkall");
	var put2=document.getElementsByName("checkbackall");
	 if(put2[0].checked==true)
	 {
	 	put1[0].checked=false;
	 } else {
	 	put1[0].checked=true;
	 }
}

  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
		var checkArray = document.getElementsByName('deal');
		var _toid = document.getElementsByName('toid');
        var _toids = ",";
        var i=0;
  	    for (var c0 = 0; c0 < checkArray.length; c0++) {
 	     if(checkArray[c0].checked){
 	         i=1;
 	        _toids = _toids + _toid[c0].value+",";
 	       }
 	     }
 	   if(i==1){
 	      $('toTaskId').value = _toids.substring(1,_toids.length-1);
 	    }else{
 	        alert("请选择要回复的对象");
			return false;	
 	    }
		return true;
	};
</script>
<div id="sheetform">
<html:form action="/groupcheck.do?method=performDealReplyAccept" styleId="theform">

<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String dealPerformer = (String)request.getAttribute("dealPerformer");
 String dealPerformerLeader = (String)request.getAttribute("dealPerformerLeader");
 String dealPerformerType = (String)request.getAttribute("dealPerformerType");
 %>  
 	  
<input type="hidden" name="toTaskId" id="toTaskId" value=""/> 
<input type="hidden" name="processTemplateName" id="processTemplateName" value="GroupCheck" />
<input type="hidden" name="beanId" value="iGroupCheckMainManager"/> 
<input type="hidden" name="operateName" id="operateName" value="nonFlowOperate" />
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckMain"/>	
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckLink"/>

<%@ include file="/WEB-INF/pages/wfworksheet/groupcheck/baseinputlinkhtmlnew.jsp"%>
      
     <table  class="formTable">
     <tr>
        <td class="label"><bean:message bundle="sheet" key="phase.pleaseSelect" /></br>
        <input type="checkbox" name="checkall" id="checkall" value="${toTask.operateRoleId}" onclick="selfcheckall();eoms.util.checkAll(this, 'deal')" >
           全选</br>
        <input type="checkbox" name="checkbackall" id="checkbackall" value="${toTask.operateRoleId}" onclick="selfcheckbackall();selfx()" >
          反选
           </td>
        <td colspan="3">
       <table class="formTable">
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
      <logic:iterate id="toTask" name="acceptList" type="com.boco.eoms.sheet.groupcheck.model.GroupCheckTask">  
        <tr>
          <td>
              <input type="checkbox" name="deal" id="deal" value="${toTask.operateRoleId}" onclick="check()">
              <eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
              <input type="hidden" name="toid" id="toid" value="${toTask.id}" />
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

     <input type="hidden" name="operateType" value="6"/> 
	     <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3"> 
		           <textarea name="remark" class="textarea max" id="remark" 
		              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
		  </td>
		</tr>
		</table>

		    
  <!-- buttons -->
			<div class="form-btns">
    		<html:submit styleClass="btn" property="method.save" styleId="method.save">
            	<fmt:message key="button.save"/>
        	</html:submit>
        	</div>
 
</html:form>

</div>

