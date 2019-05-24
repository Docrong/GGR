<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String specialType = request.getAttribute("specialType").toString();
String serviceType = request.getAttribute("serviceType").toString();
%>
<style type="text/css">
.x-form-column{width:320px} 
</style>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiAssessInstanceForm',vbtn:'method.save'});
})
</script>

<script type="text/javascript">
function getKPIs() {
	var specialType = document.forms[0].specialType.value;
	var supplierId = document.forms[0].supplierId.value;
	var url="<c:url value="/supplierkpi/editTawSupplierkpiItem.do?method=geneKPIList"/>";
	pars="&specialType="+specialType+"&supplierId="+supplierId;
	var myAjax=new Ajax.Request(url,{method:'get',parameters:pars,onComplete:call});
}
function call(originalRequest){

   var str=originalRequest.responseText;
  // console.log(str);
   var pars=eval('('+str+')');
   var checkboxes=$('checkboxes');
   var checked="";
   var i;
   checkboxes.innerHTML="";
   for(i=0;i<pars.size;i++){
    if(pars.KPIObjects[i].check){
      checked="checked";
     
    }else{
      checked="";
    }
    checkboxes.innerHTML=checkboxes.innerHTML+"<p>"+"<input type=\"checkbox\" name=\"checkbox\" value="+pars.KPIObjects[i].kpiId+" "+checked+">"+pars.KPIObjects[i].kpiName;
   // console.log(checkboxes.innerHTML);
     
}
}
function check(){
   var checkbox=document.getElementsByName('checkbox');
   var array=$A(checkbox);
  // console.log(array[0].value);
   var strs="";
   var check="";
   for(i=0;i<array.length;i++){
      if(i!=0){
       strs=strs+","
       check=check+","
      }
      strs=strs+array[i].value;
      check=check+array[i].checked;
 
    }
  //  console.log(check);
    var supplier=$('supplier').value;
    var specialType=$('specialType').value;
}
</script>

<div class="list-title">
	${eoms:a2u('供应商 ')}<bean:write name="tawSupplierkpiInfoForm" property="supplierName" scope="request"/>
	${eoms:a2u(' 在 ')}<eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao" />
					 -<eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao" />
	${eoms:a2u(' 定制的评估指标')}
</div>

<html:form action="saveTawSupplierkpiAssessInstance" method="post" styleId="tawSupplierkpiAssessInstanceForm"> 
<input type="hidden" name="supplierId" value="<bean:write name="tawSupplierkpiInfoForm" property="id" scope="request"/>" />
<input type="hidden" name="specialType" value="<%=specialType%>" />
<input type="hidden" name="serviceType" value="<%=serviceType%>" />

<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30">
		<td width="30%">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.serviceType"/>
		</td>
		<td width="70%">
			<B><eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao" /></B>
		</td>
	</tr>
	<tr height="30">
		<td>
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
		</td>
		<td>
			<B><eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao" /></B>
		</td>
	</tr>
	<tr height="30">
		<td width="30%">
			<eoms:label styleClass="desc" key="tawSupplierkpiInfoForm.supplierName"/>
		</td>
		<td width="70%">
			<B><bean:write name="tawSupplierkpiInfoForm" property="supplierName" scope="request"/></B>
		</td>
	</tr>
	<tr height="60">
		<td>
			${eoms:a2u('评估指标列表')}
		</td>
		<td>
			<div id="checkboxes" class="x-form-element">
			</div>
		</td>
	</tr>
	<tr height="30">
		<td colspan="2">
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<script language="javascript">   
	getKPIs();
</script>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>