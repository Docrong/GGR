<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
  var  v = new eoms.form.Validation({form:'theform'});
</script>



<div id="sheetform">
<html:form action="/${module}.do?method=performCreateSubTask" styleId="theform">
	  <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp" />
      <br/>		
      <table class="formTable">
       		<tr>
			       <td class="label">
			         <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*      
			       </td>
			       <td class="content">
			                <input class="text" onclick="popUpCalendar(this, this)" type="text" 
			                   name="nodeAcceptLimit" id="nodeAcceptLimit" 
			                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
			            
			       </td>
			       <td class="label">       
			               <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
			             </td>
			             <td class="content"> 
			              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
			                 name="nodeCompleteLimit" readonly="readonly" 
			                    value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="nodeCompleteLimit" alt="vtype:'moreThen',link:'nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
			       </td>
	      	</tr>
      		<tr>        
			       <td class="label">
			        <bean:message bundle="sheet" key="linkForm.remark" />*
				    </td>
					<td colspan="3">			
				        <textarea name="remark" class="textarea max" id="remark" 
				        alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetLink.remark}</textarea>
				  </td>
		   </tr>
     </table>

	 <fieldset>
		 <legend>
				请选择派发对象 
		 </legend>
	            <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
				   category="[{id:'dealPerformer',childType:'user',limit:'none',text:'派发',allowBlank:false,vtext:'请选择派发人'}]" 
				 />	
	 </fieldset>
	 
	 		
     <div class="form-btns">
	      <input type="submit" class="submit" name="method.save" id="method.save"   value="<fmt:message key='button.done'/>" >
	       		<!-- 隐藏域 -->
	       		 <input type="hidden" name="taskNamespace" value="${taskNamespace}"/>
	       		 <%@include file="/WEB-INF/pages/wfworksheet/common/sheet/splitAndReplyHidden.jsp" %>
     </div> 
</html:form>

</div>
