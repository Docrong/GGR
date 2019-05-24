<%@ include file="/common/taglibs.jsp"%>
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

Ext.onReady(function(){	
  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
		var checkArray = document.getElementsByName('${sheetPageName}deal');
        var _performer = document.getElementsByName('${sheetPageName}performer');
        var _performerType = document.getElementsByName('${sheetPageName}performerType');
        var _performerLeader = document.getElementsByName('${sheetPageName}performerLeader');
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
 	      $('${sheetPageName}dealPerformer').value = _deal.substring(1,_deal.length-1);
 	      $('${sheetPageName}dealPerformerType').value = _dealType.substring(1,_dealType.length-1);
 	      $('${sheetPageName}dealPerformerLeader').value = _dealLeader.substring(1,_dealLeader.length-1);
 	    }else{
 	        alert("${eoms:a2u('请选择要回复的对象')}");
			return false;	
 	    }
 			
		return true;
	};
});
</script>
<div id="sheetform">
<html:form action="/netdata.do?method=newPerformNonFlow" styleId="theform">

<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String dealPerformer = (String)request.getAttribute("dealPerformer");
 String dealPerformerLeader = (String)request.getAttribute("dealPerformerLeader");
 System.out.println("dealPerformerLeader>>>>>>"+dealPerformerLeader);
 String dealPerformerType = (String)request.getAttribute("dealPerformerType");
 System.out.println("dealPerformer>>>>>>"+dealPerformer);
 %>

     <input type="hidden" name="${sheetPageName}beanId" value="iNetDataMainManager"/> 
     <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netdata.model.NetDataMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netdata.model.NetDataLink"/>
 	<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="NetDataMainProcess" />
    <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
     <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value=""/> 
     <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value=""/> 
     <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value=""/> 
<%@ include file="/WEB-INF/pages/wfworksheet/netdata/baseinputlinkhtmlnew.jsp"%>
      
     <table  class="formTable">
     <tr>
        <td class="label"><bean:message bundle="sheet" key="phase.pleaseSelect" /></br>
        <input type="checkbox" name="${sheetPageName}checkall" id="${sheetPageName}checkall" value="${toTask.operateRoleId}" onclick="selfcheckall();eoms.util.checkAll(this, '${sheetPageName}deal')" >
           ${eoms:a2u('全选')}</br>
        <input type="checkbox" name="${sheetPageName}checkbackall" id="${sheetPageName}checkbackall" value="${toTask.operateRoleId}" onclick="selfcheckbackall();selfx()" >
           ${eoms:a2u('反选')}
           </td>
        <td colspan="3">
       <table class="formTable">
         <logic:present name="toOperaterList" scope="request"> 
          <tr>
           <td class="label">
            ${eoms:a2u('目的任务对象')}
           </td>
           <td class="label">
            ${eoms:a2u('目的任务所有者')} 
           </td>
           <td class="label">
             ${eoms:a2u('任务名称')}
           </td>
        </tr>
      <logic:iterate id="toTask" name="toOperaterList" type="com.boco.eoms.sheet.netdata.model.NetDataTask">  
        <tr>
          <td>
              <input type="checkbox" name="${sheetPageName}deal" id="${sheetPageName}deal" value="${toTask.operateRoleId}" onclick="check()">
              <eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemSubRoleDao" /><eoms:id2nameDB id="${toTask.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;
              <input type="hidden" name="${sheetPageName}performer" id="${sheetPageName}performer" value="${toTask.operateRoleId}" />
              <input type="hidden" name="${sheetPageName}performerType" id="${sheetPageName}performerType" value="${toTask.operateType}" />
              <input type="hidden" name="${sheetPageName}performerLeader" id="${sheetPageName}performerLeader" value="${toTask.taskOwner}" />
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
      <%if(taskName.equals("advice")){%>
     <input type="hidden" name="${sheetPageName}operateType" value="-11"/> 
	     <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3"> 
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		              alt="allowBlank:false,width:500" alt="allowBlank:false,maxLength:4000,vtext:'${eoms:a2u('请填入备注信息，最多输入4000字符')}'">${sheetlink.remark}</textarea>
		  </td>
		</tr>
		</table>
		<%} else if(taskName.equals("reply")){%>
     <input type="hidden" name="${sheetPageName}operateType" value="9"/> 

    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		              alt="allowBlank:false,width:500"  alt="allowBlank:false,maxLength:4000,vtext:'${eoms:a2u('请填入备注信息，最多输入4000字符')}'">${sheetlink.remark}</textarea>
		  </td>
		</tr>
		</table>
		<%} %>
		    
  <!-- buttons -->
		<div class="x-form-item">
			<div class="form-btns">
    		<html:submit styleClass="btn" property="method.save" styleId="method.save">
            	<fmt:message key="button.save"/>
        	</html:submit></div>
		</div>
 
</html:form>

</div>
