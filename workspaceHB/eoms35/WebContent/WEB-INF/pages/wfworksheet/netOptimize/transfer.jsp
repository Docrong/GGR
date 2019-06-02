<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
 var v = new eoms.form.Validation({form:'theform'});
</script>

<div id="sheetform">
<html:form action="/netOptimize.do?method=performTransferWorkItem" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/netOptimize/baseinputlinkhtmlnew.jsp"%>
    <br/>
                <input type="hidden" name="${sheetPageName}beanId" value="iNetOptimizeMainManager"/>
			    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeMain"/>	<!--main表Model对象类名-->	
			    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink"/> <!--link表Model对象类名-->
				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
        
       <table class="listTable">
        
           <td class="label">
           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>
			 </td>
             <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max" id="${sheetPageName}transferReason" 
		            alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'"></textarea>	
			</td>		
	   </tr>
	   
	   </table> 
    <fieldset>
	 <legend>
			请选择派发对象
	 </legend>

	 <div class="x-form-item" >
             <eoms:chooser id="test"  config="{{returnJSON:true}"
                category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                panels="[
                   {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
          />   	

	 </div>	
	</fieldset>	
	
    
       
   <div class="form-btns">
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
          	<bean:message bundle="sheet" key="button.done"/>
       	</html:submit>
   </div>
</html:form>

</div>

