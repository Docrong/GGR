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
function getAllQuarterName(el)
{
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';  
  for(var i = 0; i < tmpels.length; i++)
  {
     if(tmpels[i].checked==true)
     {
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '@@'; 
     }
  }
     document.getElementById("quarter_id").value = checkStatus;
}

function getAllMonthName(el)
{
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';  
  for(var i = 0; i < tmpels.length; i++)
  {
     if(tmpels[i].checked==true)
     {
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '@@'; 
     }
  }
     document.getElementById("month_id").value = checkStatus;
}
function validate() {
  var objResult1 = document.getElementById("slt1");
  var objResult2 = document.getElementById("slt2");
  var frm = document.forms[0];
  if (frm.reportName.value == '') {
    alert("${eoms:a2u('请选择报表名称!')}");
    return false;
  }
  if (frm.manufacturerName.value == '') {
    alert("${eoms:a2u('请选择厂商名称!')}");
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
	${eoms:a2u('纵向报表查询页面')}
</div>

<div class="list">
	<html:form action="/tawSuppKpiInstReOrderQuery.do?method=vertical" method="post" styleId="theform" onsubmit="return validate();">
		<table width="80%">
 		    <input type = "hidden" name = "specialType" value = "<%=specialType%>"/>
		    <tr height="50">
		         <td width="10%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportName" /></center>
		         </td>
			    <td>
			       <select name="reportName" onchange="changeLatitude();">
					<option value="">
						=<label>${eoms:a2u('请选择报表名称')}</label>=
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
			    <td width="8%">
			        <center><bean:message key="tawSupplierkpiInstanceList.supplier" /></center>
			    </td>
			    <td>
			       <select name="manufacturerName">
					<option value="">
						=<label>${eoms:a2u('请选择厂商名称')}</label>=
					</option>
					<%
						ArrayList manufacturers = (ArrayList) request
								.getAttribute("manufacturers");
						Iterator iterator = manufacturers.iterator();
						while (iterator.hasNext()) {
							TawSupplierkpiInfo tawSupplierkpiInfo = (TawSupplierkpiInfo)iterator.next();
							  
					%>
                      <option value="<%=tawSupplierkpiInfo.getId()%>"><%=tawSupplierkpiInfo.getSupplierName()%></option>
					<%
					}
					%>					
				  </select>			        
			    </td>
	
		    </tr>
					    
			<tr height="50">

				<td>
					<center><bean:message key="tawSupplierkpiInstanceList.year" /></center>
				</td>
				<td colspan="3">
				    
					<select name="year">
						<option value="">==<label>${eoms:a2u('请选择年度')}</label>==</option>
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
				</td>
			</tr>
			<tr height="50">
			    <td>
			        <center><bean:message key="tawSupplierkpiInstanceList.quarter" /></center>
			    </td>
			    <td colspan="3" id="slt1">
			        <input type="checkbox" name="quarter_1"  value="one" onClick="getAllQuarterName(this);"><label>${eoms:a2u('第一季度')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="quarter_1"  value="two" onClick="getAllQuarterName(this);"><label>${eoms:a2u('第二季度')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="quarter_1"  value="three" onClick="getAllQuarterName(this);"><label>${eoms:a2u('第三季度')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="quarter_1"  value="four" onClick="getAllQuarterName(this);"><label>${eoms:a2u('第四季度')}</label></input>
			    </td>
			    <input type ="hidden" id="quarter_id" name="quarter"/>
			</tr>
			<tr height="50">
			    <td>
			        <center><bean:message key="tawSupplierkpiInstanceList.month" /></center>	
			    </td>
			    <td colspan="3" id="slt2">
			        <input type="checkbox" name="month_1"  value="01" onClick="getAllMonthName(this);"><label>${eoms:a2u('一月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="02" onClick="getAllMonthName(this);"><label>${eoms:a2u('二月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="03" onClick="getAllMonthName(this);"><label>${eoms:a2u('三月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="04" onClick="getAllMonthName(this);"><label>${eoms:a2u('四月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="05" onClick="getAllMonthName(this);"><label>${eoms:a2u('五月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="06" onClick="getAllMonthName(this);"><label>${eoms:a2u('六月')}</label></input>
			        <br>
			        <input type="checkbox" name="month_1"  value="07" onClick="getAllMonthName(this);"><label>${eoms:a2u('七月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="08" onClick="getAllMonthName(this);"><label>${eoms:a2u('八月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="09" onClick="getAllMonthName(this);"><label>${eoms:a2u('九月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="10" onClick="getAllMonthName(this);"><label>${eoms:a2u('十月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="11" onClick="getAllMonthName(this);"><label>${eoms:a2u('十一月')}</label></input>
			        &nbsp;&nbsp;&nbsp;&nbsp;
			        <input type="checkbox" name="month_1"  value="12" onClick="getAllMonthName(this);"><label>${eoms:a2u('十二月')}</label></input>			        			        
			    </td>
			    <input type ="hidden" id="month_id" name="month"/>
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
