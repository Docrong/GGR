<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
.x-form-column{width:320px}
</style>

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
var v;

Ext.onReady(function(){	
  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
		var checkArray = document.getElementsByName('deal');
        var _performer = document.getElementsByName('performer');
        var _performerType = document.getElementsByName('performerType');
        var _performerLeader = document.getElementsByName('performerLeader');
        var _deal = ",";
        var _dealType = ",";
        var _dealLeader = ",";
        var i=0;
  	    for (var c0 = 0; c0 < checkArray.length; c0++) {
 	     if(checkArray[c0].checked){
 	         i=1;
 	        _deal = _deal + _performer[c0].value+",";
 	        _dealType = _dealType + _performerType[c0].value+",";
 	        _dealLeader = _dealLeader + _performerLeader[c0].value+",";
 	       }
 	     }
 	   if(i==1){
 	      $('dealPerformer').value = _deal.substring(1,_deal.length-1);
 	      $('dealPerformerType').value = _dealType.substring(1,_dealType.length-1);
 	      $('dealPerformerLeader').value = _dealLeader.substring(1,_dealLeader.length-1);
 	    }else{
 	        alert("请选择要回复的对象");
			return false;	
 	    }
 			
		return true;
	};
});
</script>
<div id="sheetform">
<html:form action="/${module}.do?method=newPerformNonFlow" styleId="theform">   
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
		        <logic:present name="toOperaterList" scope="request"> 
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
	        
			      <logic:iterate id="toTask" name="toOperaterList">  
			        <tr>
			          	<td>
				              	<input type="checkbox" name="deal" id="deal" value="${toTask.operateRoleId}" onclick="check()">
				              	<eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
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
     
    <c:choose>
    	<c:when test="${taskName == 'advice'}">
    			<input type="hidden" name="operateType" value="-11"/> 
			     <tr>
			       	<td class="label">
		        			<bean:message bundle="sheet" key="linkForm.remark" />*
				    </td>
					<td colspan="3"> 
			           		<textarea name="remark" class="textarea max" id="remark" 
			              	alt="allowBlank:false,width:500" alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetlink.remark}</textarea>
				  	</td>
				  </tr>
			</table>
   		</c:when>	
   		<c:when test="${taskName == 'reply' }">
   				<input type="hidden" name="operateType" value="9"/> 
		    	<tr>
			       <td class="label">
			        	<bean:message bundle="sheet" key="linkForm.remark" />*
			       </td>
				   <td colspan="3">
			           	<textarea name="remark" class="textarea max" id="remark" 
			              alt="allowBlank:false,width:500"  alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetlink.remark}</textarea>
				    </td>
				</tr>
			</table>
   		</c:when>
    </c:choose>
      
		    
  <!-- buttons -->
	<div class="x-form-item">
		<div class="form-btns">
	  		<html:submit styleClass="btn" property="method.save" styleId="method.save">
	          	<bean:message bundle='sheet' key='button.done'/>
	      	</html:submit>
	      	
	      	<!-- 隐藏域 -->
			 	 <input type="hidden" name="aiid" value="${sheetLink.aiid}"/>
				 <input type="hidden" name="mainId" value="${sheetMain.id}"/>
				 <input type="hidden" name="piid" value="${sheetMain.piid}"/>
				 <input type="hidden" name="activeTemplateId" value="${taskName}"/>
			     <input type="hidden" name="dealPerformer" id="dealPerformer" value=""/> 
			     <input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value=""/> 
			     <input type="hidden" name="dealPerformerType" id="dealPerformerType" value=""/>
			      
	    </div>
	</div>
 
</html:form>

</div>
