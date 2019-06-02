<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
 }
 else {
 put2[0].checked=true;
 }
 }
 function selfcheckbackall(){
var put1=document.getElementsByName("checkall");
var put2=document.getElementsByName("checkbackall");
 if(put2[0].checked==true)
 {
 put1[0].checked=false;
 }
 else {
 put1[0].checked=true;
 }
}

  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
		var checkArray = document.getElementsByName('deal');
		var _toid = document.getElementsByName('toid');
        var _performer = document.getElementsByName('performer');
        var _performerType = document.getElementsByName('performerType');
        var _performerLeader = document.getElementsByName('performerLeader');
        var _toids = ",";
        var _deal = ",";
        var _dealType = ",";
        var _dealLeader = ",";
        var i=0;
  	    for (var c0 = 0; c0 < checkArray.length; c0++) {
 	     if(checkArray[c0].checked){
 	         i=1;
 	        _toids = _toids + _toid[c0].value+",";
 	        _deal = _deal + _performer[c0].value+",";
 	        _dealType = _dealType + _performerType[c0].value+",";
 	        _dealLeader = _dealLeader + _performerLeader[c0].value+",";
 	       }
 	     }
 	   if(i==1){
 	      $('toTaskId').value = _toids.substring(1,_toids.length-1);
 	      $('dealPerformer').value = _deal.substring(1,_deal.length-1);
 	      $('dealPerformerType').value = _dealType.substring(1,_dealType.length-1);
 	      $('dealPerformerLeader').value = _dealLeader.substring(1,_dealLeader.length-1);
 	    }else{
 	        alert("请选择要回复的对象");
			return false;
 	    }
 			
		return true;
	};

</script>
<div id="sheetform">
<html:form action="/${module}.do?method=newPerformDealReplyReject" styleId="theform">

     <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp" />
     <br/>
     <table  class="formTable">
     		<tr>
			     <td class="label">
				     	<bean:message bundle="sheet" key="phase.pleaseSelect" /><br/><br/>
				        <input type="checkbox" name="checkall" id="checkall" value="${toTask.operateRoleId}" onclick="selfcheckall();eoms.util.checkAll(this, 'deal')" >
				           全选<br/><br/>
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
				      	  <logic:iterate id="toTask" name="acceptList">  
					        <tr>
						          <td>
						              <input type="checkbox" name="deal" id="deal" value="${toTask.operateRoleId}" onclick="check()">
						              <eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
						              <input type="hidden" name="toid" id="toid" value="${toTask.id}" />
						              <input type="hidden" name="performer" id="performer" value="${toTask.operateRoleId}" />
						              <input type="hidden" name="performerType" id="performerType" value="${toTask.operateType}" />
						              <input type="hidden" name="performerLeader" id="performerLeader" value="${toTask.taskOwner}" />
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
	       	
       	    <input type="hidden" name="subtaskName" value="${subtaskName}"/> 
		    <input type="hidden" name="dealPerformer" id="dealPerformer" value=""/>
		    <input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value=""/> 
		    <input type="hidden" name="dealPerformerType" id="dealPerformerType" value=""/>
		    <input type="hidden" name="toTaskId" id="toTaskId" value=""/>
		    <input type="hidden" name="operateType" value="7"/>
		    <input type="hidden" name="taskId" value="${sheetLink.tkid}"/>   
       </div>
 
</html:form>

</div>
