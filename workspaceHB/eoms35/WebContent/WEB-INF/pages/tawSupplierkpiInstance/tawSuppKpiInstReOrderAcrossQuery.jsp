<jsp:directive.page import="java.util.ArrayList,java.util.Iterator" />
<jsp:directive.page
	import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo,
	        com.boco.eoms.extra.supplierkpi.model.TawSuppKpiInstReportOrder" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'theform',vbtn:''});
})

function validate() {
  var objResult1 = document.getElementById("slt1");
  var objResult2 = document.getElementById("slt2");
  var frm = document.forms[0];
  if (frm.reportName.value == '') {
    alert("${eoms:a2u('请选择报表名称!')}");
    return false;
  }
  if (frm.year.value == '') {
    alert("${eoms:a2u('请选择年度!')}");
    return false;
  }
  if (objResult1.disabled != true && frm.quarter.value =='') {
    alert("${eoms:a2u('请选择季度!')}");
    return false;
  }
  if (objResult2.disabled != true && frm.month.value =='') {
    alert("${eoms:a2u('请选择月度!')}");
    return false;
  }    
  else {
    return true;
  }
}

function changeLatitude(){
	var objResult1 = document.getElementById("slt1");
		objResult1.disabled = "";
	var objResult2 = document.getElementById("slt2");
		objResult2.disabled = "";		    
    var frm = document.forms[0];
   	var tmpels = frm.reportName.value;   	
   	var tempArray = tmpels.split('@');
   	var latitude = tempArray[1];   	
   	if(latitude=='106010301'){
   	   hidded2();
   	}else if(latitude=='106010302'){
   	   hidded1();
   	}else if(latitude=='106010303'){
   	   hidded1();
   	   hidded2();
   	}
}
function hidded1() {
	var objResult = document.getElementById("slt2");
		objResult.disabled = "disabled";	
	}
function hidded2() {
	var objResult = document.getElementById("slt1");	
		objResult.disabled = "disabled";	
	}	
	
</script>
<%
  String specialType =String.valueOf(request.getAttribute("specialType"));
%>

<div class="list-title">
	${eoms:a2u('横向报表查询页面')}
</div>

<div class="list">
	<html:form action="/tawSuppKpiInstReOrderQuery.do?method=across" method="post" styleId="theform" onsubmit="return validate();">
		<table width="80%">
		<input type = "hidden" name = "specialType" value = "<%=specialType%>"/>
            <tr height="50"> 
		         <td width="12%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportName" /></center>
		         </td>
			    <td colspan="3">
			       <select name="reportName"  onchange="changeLatitude();">
					<option value="">
						<label>${eoms:a2u('请选择报表名称')}</label>
					</option>
					<%
						ArrayList reportNames = (ArrayList) request
								.getAttribute("reportNames");
						Iterator iter = reportNames.iterator();
						while (iter.hasNext()) {
							TawSuppKpiInstReportOrder reportName = (TawSuppKpiInstReportOrder)iter.next();
					%>
                      <option value="<%=reportName.getId()%>@<%=reportName.getLatitude()%>"><%=reportName.getReportName() %></option>
					<%
					}
					%>					
				  </select>			        
			    </td>               
            </tr>					    
			<tr height="50">

				<td>
					<center><bean:message key="tawSupplierkpiInstanceList.evaluationTime" /></center>
				</td>
				<td colspan="3">
				    
					<select name="year">
						<option value=""><label>${eoms:a2u('请选择年度')}</label></option>
						<option value="2005">2005</option>
						<option value="2006">2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
						<option value="2015">2015</option>
					</select>
    			    <bean:message key="tawSupplierkpiInstanceList.year" />
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="quarter" id="slt1" >
						<option value=""><label>${eoms:a2u('请选择季度')}</label></option>
						<option value="one">${eoms:a2u('第一季度')}</option>
						<option value="two">${eoms:a2u('第二季度')}</option>
						<option value="three">${eoms:a2u('第三季度')}</option>
						<option value="four">${eoms:a2u('第四季度')}</option>
					</select>
					<bean:message key="tawSupplierkpiInstanceList.quarter" />	
			         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="month" id="slt2" >
						<option value=""><label>${eoms:a2u('请选择月度')}</label></option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
					<bean:message key="tawSupplierkpiInstanceList.month" />					
				</td>
			</tr>
			<tr align="right" height="40">
				<td colspan="4">
				<input type="submit" styleClass="btn" property="method.save" styleId="method.save" value="<bean:message key="button.querys" />" />
				</td>				
				
			</tr>
		</table>
	</html:form>
</div>


<%@ include file="/common/footer_eoms.jsp"%>
