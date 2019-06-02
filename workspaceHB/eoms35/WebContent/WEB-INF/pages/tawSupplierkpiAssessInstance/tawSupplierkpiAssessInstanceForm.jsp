<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String supplierId = request.getAttribute("supplierId").toString();
String supplierName = request.getAttribute("supplierName").toString();
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
	${eoms:a2u('为 ')}<B><%=supplierName%></B>${eoms:a2u(' 定制评估指标')}
</div>

<html:form action="saveTawSupplierkpiAssessInstance" method="post" styleId="tawSupplierkpiAssessInstanceForm"> 
<html:hidden property="supplierId" value="<%=supplierId%>" />
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30">
		<td width="50">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.serviceType"/>
		</td>
		<td width="100">
			<eoms:comboBox name="serviceType" id="serviceType" initDicId="104" 
		      		sub="specialType" styleClass="select-class" />
		</td>
		<td width="50">
			<eoms:label styleClass="desc" key="tawSupplierkpiTemplateForm.specialType"/>
		</td>
		<td width="100">
			<select name="specialType" id="specialType" onChange="getKPIs()"/>
		</td>
	</tr>
	<tr height="60">
		<td>
			${eoms:a2u('KPI列表')}
		</td>
		<td colspan="3">
			<div id="checkboxes" class="x-form-element">
			</div>
		</td>
	</tr>
	<tr height="30">
		<td colspan="4">
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>