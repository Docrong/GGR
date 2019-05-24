<jsp:directive.page import="java.util.ArrayList,java.util.Iterator" />
<jsp:directive.page
	import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo,
	        com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem,
	        com.boco.eoms.base.util.StaticMethod,
	        com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable" />

<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    v = new eoms.form.Validation({form:'tawSuppKpiInstReportOrderForm'});
})

function getAllKpiItemName(el)
{
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';
  var checkName = '';
  for(var i = 0; i < tmpels.length; i++)
  {
     if(tmpels[i].checked==true)
     {
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '##'; 
     }
  }

     document.getElementById("kpiItemName_1").value = checkStatus;
}
function validate() {
  var frm = document.forms[0];
  var checkBox=document.getElementsByName('itemName');
  var array=$A(checkBox);
  var num=0;
  for(i=0;i<array.length;i++){
    if(array[i].checked==true){
      num=num+1;
    }
  }
  if (num <= 0) {
    alert("${eoms:a2u('请选择指标项')}");
    return false;
  }
  if (frm.reportName.value == '') {
    alert("${eoms:a2u('请填写报表名称')}");
    return false;
  }
  else if (frm.latitude.value == '') {
    alert("${eoms:a2u('请选择周期')}");
    return false;
  }
  else if (frm.reportType.value == '') {
    alert("${eoms:a2u('请选择报表类型')}");
    return false;
  }
  else if (frm.reportStartTime.value == '') {
    alert("${eoms:a2u('请选择报表开始时间')}");
    return false;
  }
  else if (frm.reportEndTime.value == '') {
    alert("${eoms:a2u('请选择报表结束时间')}");
    return false;
  }
  else {
    return true;
  }
}		

function submitForm() {
  var result = validate();
  if (result == true) {
    document.forms[0].submit();
  }
}

function checkReportName() {
	var reportName = document.forms[0].reportName.value;
	var url="<c:url value="/supplierkpi/editTawSuppKpiInstReportOrder.do?method=checkReportName"/>";
	pars="&reportName="+reportName;
	var myAjax=new Ajax.Request(url,{method:'get',parameters:pars,onComplete:call});
}
function call(originalRequest){
   var str=originalRequest.responseText;
   if (1 == str) {
     alert("${eoms:a2u('该名称的报表已存在,请重新填写报表名称')}");
   }
   else {
     submitForm();
   }
}

function selAll() {
  var items=document.getElementsByName('itemName');
  var array=$A(items);
  for(i=0;i<array.length;i++){
    array[i].checked=true;
    getAllKpiItemName(array[i]);
  }
}
</script>
<%
  String specialType = (String)request.getAttribute("specialType");
%>
<div class="list-title">
	<bean:message key="tawSupplierkpiInstanceList.StatOrder" />
</div>
<div class="list">
	<html:form action="/saveTawSuppKpiInstReportOrder.do?method=save" method="post"  styleId="tawSuppKpiInstReportOrderForm">
		<table>
		<input type = "hidden" name = "specialType" value = "<%=specialType%>"/>
		    <tr height="50">
		        <td>
		            <center><bean:message key="tawSupplierkpiInstanceList.reportName" /></center>
		        </td>
		        <td colspan="3">
		            <input type="text" class="text" name="reportName" style="width:88%" />
		        </td>
		    </tr>
					    
			<tr height="50">

				<td>
					<center><bean:message key="tawSupplierkpiInstanceList.latitude" /></center>
				</td>
				<td>
					<eoms:dict key="dict-supplierkpi" dictId="statictsCycle" isQuery="false"
						defaultId="${defaultId}" selectId="latitude" beanId="selectXML" />		
				</td>				

			     <td>
			         <center><bean:message key="tawSupplierkpiInstanceList.reportType" /></center>
			     </td>
			     <td>
			     	<eoms:dict key="dict-supplierkpi" dictId="reportType" isQuery="false"
						defaultId="${defaultId}" selectId="reportType" beanId="selectXML" />
			     </td>
			</tr>
		    <tr height="50">
		         <td width="7%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportStartTime" /></center>
		         </td>
		         <td>
		         	<input type="text" name="reportStartTime" id="reportStartTime" class="text"
						readonly="readonly"
						alt="vtype:'lessThen',link:'reportEndTime',vtext:'${eoms:a2u('生效时间不能晚于终止时间！')}'"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);" value="<%=SuppStaticVariable.getLastMouthFirstDay() %>" />
		         </td>

		         <td width="7%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportEndTime" /></center>
		         </td>
		         <td>
		         	<input type="text" name="reportEndTime" id="fillEndTime" class="text"
						readonly="readonly"
						alt="vtype:'moreThen',link:'reportStartTime',vtext:'${eoms:a2u('终止时间不能早于生效时间！')}'"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"  value="<%=SuppStaticVariable.getLastMouthLastDay() %>" />
		         </td>
			
		    </tr>
		    <tr>
		         <td>
		            <center><bean:message key="tawSupplierkpiInstanceList.kpiItemName" /></center>
		         </td>
		         
		         <td colspan="3">
		            <% 
		              ArrayList kpiItemNames = (ArrayList)request.getAttribute("kpiItemNames");
		              Iterator iter = kpiItemNames.iterator();
		              int i=0;
		              while (iter.hasNext()) {
							TawSupplierkpiItem kpiItem = (TawSupplierkpiItem)iter.next();							
							i++;
                      		    String key = kpiItem.getId();
                                String value = kpiItem.getKpiName();
                                out.print("<input type=checkbox name=itemName  value="+key+"@@"+value+" onClick=getAllKpiItemName(this);>");
                                out.print(value);
                                out.print("</input>&nbsp;&nbsp;&nbsp;&nbsp;");
                                if(i % 1 == 0)
                                {
                                  	out.print("<br>");
                                }

					 }
					 if(kpiItemNames.size()<= 0 || kpiItemNames == null){
		                 out.print("\u6CA1\u6709\u975E\u4E3B\u89C2\u6570\u636E\u7684\u6307\u6807!");
		              }
                    %>
                
                    <input type ="hidden" id="kpiItemName_1" name="kpiItemId"/>
                    </td> 		    
		    </tr>
			<tr align="right" height="40">
				<td>
				</td>
				<td colspan="3">
				<input type="button" class="btn" value="${eoms:a2u('全选指标')}" onclick="selAll()" />
				<input type="button" class="btn" value="${eoms:a2u('统计定制')}" onclick="checkReportName()" />
				</td>
				
			</tr>
		</table>
	</html:form>
</div>

<%@ include file="/common/footer_eoms.jsp"%>
