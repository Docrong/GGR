<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
function selectProcess(obj){
       Ext.Ajax.request({
		method:"get",
		url: "${app}/access/tawSheetAccesss.do?method=getProcessHumanTask&processName="+obj+"&taskName=${tawSheetAccessForm.taskname}",
		success: function(x){
        	//document.getElementById('taskname').innerHTML=x.responseText;
        	var j = eoms.JSONDecode(x.responseText);
        	eoms.form.Options.loadJSON('taskname',j);
 		}
       });
   }
 Ext.onReady(function(){
      selectProcess('${tawSheetAccessForm.processname}');
  });
</script>
<title>${eoms:a2u('流程附件模板管理')}</title>
 <content tag="heading">${eoms:a2u('流程附件模板管理')}</content>

<html:form action="saveTawSheetAccess" method="post" styleId="tawSheetAccessForm"> 
<table id="sheet" class="formTable">	
    <html:hidden property="id"/>
    <html:hidden property="accessid"/>
    <html:hidden property="ordercode"/>
    <html:hidden property="parAccessid"/>
      <br>
      <br>
      <tr>
		   <td class="label">${eoms:a2u('附件模板')}</td>
			  <td colspan="3">
			  <eoms:attachment name="tawSheetAccessForm" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess"/> 		
			  </td>
      </tr>
		        	
	      <tr>
		   <td class="label">${eoms:a2u('流程名称')}*</td>
			  <td colspan="3">
			   <html:select property="processname" onchange="selectProcess(this.value);">
                 <html:options collection="workflowlist" property="name" labelProperty="remark"/>
               </html:select>
			  </td>
          </tr>
		    <tr>
		   <td class="label">${eoms:a2u('节点名称')}*</td>
			  <td colspan="3">
			  <select id="taskname" name="taskname">
			  <option>please select
			  </option>
			  </select>
		
			 <%-- 
			 <input type="text" class="text" name="taskname" id="taskname" value="${tawSheetAccessForm.taskname}" alt="allowBlank:false"/>
			 --%> 
			  </td>
          </tr>
		     <tr>
		   <td class="label">${eoms:a2u('备注')}</td>
			  <td colspan="3">
			 <input type="text" class="text" name="remark" id="remark" value="${tawSheetAccessForm.remark}"/>
			  </td>
          </tr>

</table>	
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save">
            <fmt:message key="button.save"/>
        </html:submit>
        <html:submit styleClass="button" property="method.delete">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>


</html:form>
<script type="text/javascript">
Ext.onReady(function(){
	var v = new eoms.form.Validation({form:"tawSheetAccessForm"});
});
</script>
<%@ include file="/common/footer_eoms.jsp"%>