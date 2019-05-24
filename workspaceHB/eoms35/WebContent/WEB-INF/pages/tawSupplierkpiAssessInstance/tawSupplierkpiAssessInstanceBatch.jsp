<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String specialType = request.getAttribute("specialType").toString();
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
	var url="<c:url value="/supplierkpi/editTawSupplierkpiItem.do?method=getSpecialKpis"/>";
	pars="&specialType="+specialType;
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

//*************************

function getSuppliers() {
	var specialType = document.forms[0].specialType.value;
	var url="<c:url value="/supplierkpi/editTawSupplierkpiInfo.do?method=getSuppliers"/>";
	pars="";
	var myAjax=new Ajax.Request(url,{method:'get',parameters:pars,onComplete:call1});
}
function call1(originalRequest){

   var str=originalRequest.responseText;
  // console.log(str);
   var pars=eval('('+str+')');
   var suppliers=$('suppliers');
   var checked="";
   var i;
   suppliers.innerHTML="";
   for(i=0;i<pars.size;i++){
    if(pars.KPIObjects[i].check){
      checked="checked";
     
    }else{
      checked="";
    }
    suppliers.innerHTML=suppliers.innerHTML+"<p>"+"<input type=\"checkbox\" name=\"supplier\" value="+pars.KPIObjects[i].kpiId+" "+checked+">"+pars.KPIObjects[i].kpiName;
   // console.log(checkboxes.innerHTML);
     
}
}
function check(){
   var checkbox=document.getElementsByName('supplier');
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

function validate() {
  var supplier=document.getElementsByName('supplier');
  var array=$A(supplier);
  var num=0;
  for(i=0;i<array.length;i++){
    if(array[i].checked==true){
      num=num+1;
    }
  }
  if(num>0){
    //var answer = window.confirm("${eoms:a2u('保存将会覆盖原有数据,是否继续?')}");
    //if (answer) {
      return true;
    //}
    //return false;
  }
  else{
    alert("${eoms:a2u('您没有选择供应商！')}");
    return false;
  }
}

function selAll(name, flag) {
  var supplier=document.getElementsByName(name);
  var array=$A(supplier);
  for(i=0;i<array.length;i++){
    if (flag == 1) {
      array[i].checked=true;
    }
    else {
      array[i].checked=false;
    }
  }
}
</script>

<div class="list-title">
	${eoms:a2u('批量定制评估指标')}
</div>

<html:form action="saveTawSupplierkpiAssessInstance" method="post" styleId="tawSupplierkpiAssessInstanceForm" onsubmit="return validate();"> 
<html:hidden property="specialType" value="<%=specialType%>" />
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
	<tr height="30" class="header" align="center">
		<td width="20%">
			${eoms:a2u('供应商列表')}
		</td>
		<td width="80%">
			${eoms:a2u('专业评估指标列表')}
		</td>
	</tr>
	<tr height="60">
		<td>
			<div id="suppliers">
			</div>
		</td>
		<td>
			<div id="checkboxes">
			</div>
		</td>
	</tr>
	<tr height="30" align="center">
		<td>
			<a href="javascript:selAll('supplier', 1)">${eoms:a2u('全选')}</a>
			<a href="javascript:selAll('supplier', 0)">${eoms:a2u('全不选')}</a>
		</td>
		<td>
			<a href="javascript:selAll('checkbox', 1)">${eoms:a2u('全选')}</a>
			<a href="javascript:selAll('checkbox', 0)">${eoms:a2u('全不选')}</a>
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
	getSuppliers();
</script>  
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>