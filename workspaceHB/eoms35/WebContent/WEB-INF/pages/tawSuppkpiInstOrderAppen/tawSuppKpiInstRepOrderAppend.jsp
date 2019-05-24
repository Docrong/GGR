<jsp:directive.page import="java.util.ArrayList,java.util.Iterator" />
<jsp:directive.page
	import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo,
	        com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem"/>
<jsp:directive.page import="com.boco.eoms.extra.supplierkpi.webapp.form.TawSuppKpiInstReportOrderForm,
                       com.boco.eoms.base.util.StaticMethod"/>   
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	valider({form:'tawSuppKpiInstReportOrderForm',vbtn:'su'});
})
function getAllQuarterName(el){
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';  
  for(var i = 0; i < tmpels.length; i++){
     if(tmpels[i].checked==true){
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '@@'; 
     }
  }
  document.getElementById("quarter_id").value = checkStatus;
}
function getAllMonthName(el){
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';  
  for(var i = 0; i < tmpels.length; i++){
     if(tmpels[i].checked==true){
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '@@'; 
     }
  }
  document.getElementById("month_id").value = checkStatus;
}
function getAllKpiItemName(el){
  var tmpels = document.getElementsByName(el.name);
  var checkStatus = '';
  var checkName = '';
  for(var i = 0; i < tmpels.length; i++){
     if(tmpels[i].checked==true){
       checkStatus  = checkStatus + tmpels[i].value;
       checkStatus = checkStatus + '##'; 
     }
  }
  document.getElementById("kpiItemName_1").value = checkStatus;
}
function validate() {
  var objResult1 = document.getElementById("slt1");
  var objResult2 = document.getElementById("slt2");
  var frm = document.forms[0];
  if (frm.reportName.value == '') {
    alert("${eoms:a2u('请填写报表名称')}");
    return false;
  }
  if (frm.latitude.value == '') {
    alert("${eoms:a2u('请选择周期')}");
    return false;
  }
  if (frm.reportType.value == '') {
    alert("${eoms:a2u('请选择报表类型')}");
    return false;
  }
  if (frm.reportStartTime.value == '') {
    alert("${eoms:a2u('请选择报表开始时间')}");
    return false;
  }
  if (frm.reportEndTime.value == '') {
    alert("${eoms:a2u('请选择报表结束时间')}");
    return false;
  }
  if (frm.kpiItemId.value == '') {
    alert("${eoms:a2u('请选择指标项')}");
    return false;
  }
  if (frm.year.value == '') {
    alert("${eoms:a2u('请选择报表年度')}");
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
   	//var tmpels = frm.reportName.value;   	
   	//var tempArray = tmpels.split('@');
   	var latitude = frm.latitude.value;   	
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
   TawSuppKpiInstReportOrderForm orderForm= (TawSuppKpiInstReportOrderForm)request.getAttribute("tawSuppKpiInstReportOrderForm");
 %>
<div class="list-title">
	${eoms:a2u('报表指标追加页面')}
</div>

<div class="list">
	<html:form action="/saveAppendTawSuppKpiInstOrder.do?method=append" method="post"  styleId="tawSuppKpiInstReportOrderForm" onsubmit="return validate();">
		<table>
		<input type = "hidden" name = "serviceType" value = "<%=String.valueOf(request.getAttribute("serviceType"))%>"/>
		<input type = "hidden" name = "specialType" value = "<%=String.valueOf(request.getAttribute("specialType"))%>"/>
		<input type ="hidden" name="id" value="<%=String.valueOf(request.getAttribute("id")) %>"/>
		    <tr height="50">
		        <td>
		            <center><bean:message key="tawSupplierkpiInstanceList.reportName" /></center>
		        </td>
		        <td colspan="3">
		            <input type="text" name="reportName" value="<%=String.valueOf(request.getAttribute("reportName")) %>"/>
		        </td>
		    </tr>				    
			<tr height="50">
				<td>
					<center><bean:message key="tawSupplierkpiInstanceList.latitude" /></center>
				</td>
				<td>
				    <eoms:comboBox name="latitude" id="latitude" initDicId="1060103" 
		      		    styleClass="select-class"  onchange="changeLatitude();"/> 			    				
				</td>				

			     <td>
			         <center><bean:message key="tawSupplierkpiInstanceList.reportType" /></center>
			     </td>
			     <td>				    
					<eoms:comboBox name="reportType" id="reportType" initDicId="1060107" 
		      		    styleClass="select-class"/> 			      
			     </td>
			</tr>
		    <tr height="50">
		         <td width="7%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportStartTime" /></center>
		         </td>
		         <td>		           
		           <input type="text" name="reportStartTime" id="reportStartTime" alt="timer:true" value="<%=String.valueOf(request.getAttribute("reportStartTime"))%>"/>
		         </td>

		         <td width="7%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportEndTime" /></center>
		         </td>
		         <td>
		           <input type="text" name="reportEndTime" id="reportEndTime" alt="timer:true" value="<%=String.valueOf(request.getAttribute("reportEndTime")) %>"/>
		         </td>			
		    </tr>
		    <tr>
		         <td>
		            <center><bean:message key="tawSupplierkpiInstanceList.kpiItemName" /></center>
		         </td>
		         
		         <td colspan="3">
		            <% 
		              String tempId = String.valueOf(request.getAttribute("kpiItemId"));
		              String[] temp1 = StaticMethod.TokenizerString2(tempId, ",");
		              ArrayList kpiItemNames = (ArrayList)request.getAttribute("kpiItemNames");
		              Iterator iter = kpiItemNames.iterator();
		              int i=0;
		              int j=0;
		              String checkValue="";
		               
		                while (iter.hasNext()) {
							TawSupplierkpiItem kpiItem = (TawSupplierkpiItem)iter.next();
							i++;
							boolean isChecked=false;							    
                      		    String key = kpiItem.getId();
                                String value = kpiItem.getKpiName().trim();
                             for(j=0;j<temp1.length;j++){
                                String key1 = temp1[j].trim();
                                if(key1.equals(key)){
                                    isChecked=true;                                   
                                    out.print("<input type=checkbox name=itemName  value="+key+"@@"+value+" onClick=getAllKpiItemName(this); checked>");
                                    out.print(value);
                                    out.print("</input><br>");
                                    checkValue = checkValue + key+"@@"+value+"##";                                                                        
                                }
							 }
                              //  if(i % 1 == 0)
                               // {
                                  	//out.print("<br>");
                               // }
                                if(isChecked)
                                 {
                                   continue;
                                 }
                                out.print("<input type=checkbox name=itemName  value="+key+"@@"+value+" onClick=getAllKpiItemName(this);>");
                                out.print(value);
                                out.print("</input><br>");                 							                                                                

					   }
					  if(kpiItemNames.size()<= 0 || kpiItemNames == null){
		                 out.print("\u6CA1\u6709\u975E\u4E3B\u89C2\u6570\u636E\u7684\u6307\u6807!");
		              }
                    %>
                     
                    <input type ="hidden" id="kpiItemName_1" name="kpiItemId" value="<%=checkValue %>"/>
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
                  <input type="submit" styleClass="btn" property="method.append" styleId="method.append" value="<bean:message key="button.update" />" />
				</td>
				
			</tr>
		</table>
	</html:form>
</div>


<%@ include file="/common/footer_eoms.jsp"%>
