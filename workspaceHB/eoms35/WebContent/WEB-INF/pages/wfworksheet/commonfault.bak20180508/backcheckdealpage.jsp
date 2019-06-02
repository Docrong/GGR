<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
.x-form-column{width:320px}
</style>

<%


 System.out.println("dealPerformerLeader>>>>>>");

 System.out.println("dealPerformer>>>>>>");
 %>

<script type="text/javascript">

var v;

Ext.onReady(function(){	
  v = new eoms.form.Validation({form:'theform'});
  v.custom = function(){
 		
 		
		return true;
	};
});

</script>
<div id="sheetform">
<html:form action="/commonfault.do?method=doBackCheckSave" styleId="theform">
 <%@ include file="/common/taglibs.jsp"%>


    <input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/>	<!--main表Model对象类名-->	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--link表Model对象类名-->
 	<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="CommonFaultMainFlowProcess" />     
 	
 	<input type="hidden" name="${sheetPageName}mainid" id="${sheetPageName}mainid" value="${mainid}"/> 
     <table  class="formTable">
     
     	<input type="hidden" name="${sheetPageName}operateType" value="5"/> 
     	
    	<tr>
	       <td class="label">
	        <bean:message bundle="commonfault" key="commonfault.linkCheckResult" />*
		    </td>
			<td colspan="3">
				  	<eoms:comboBox name="${sheetPageName}mainCheckResult" 
				  	  id="${sheetPageName}mainCheckResult" initDicId="10301"
				  	   defaultValue="${sheetLink.linkIfExcuteNetChange}" alt="allowBlank:false"/>
		    </td>
		</tr>
    	<tr>
	       <td class="label">
	        <bean:message bundle="commonfault" key="commonfault.linkCheckIdea" />
		    </td>
			<td colspan="3">
		           <textarea name="${sheetPageName}mainCheckIdea" class="textarea max" id="${sheetPageName}mainCheckIdea" 
		               alt="width:'500px'"></textarea>
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
