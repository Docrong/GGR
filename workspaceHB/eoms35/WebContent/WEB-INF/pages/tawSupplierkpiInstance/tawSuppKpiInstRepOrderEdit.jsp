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
  else {
    return true;
  }
}				
</script>
<%
   TawSuppKpiInstReportOrderForm orderForm= (TawSuppKpiInstReportOrderForm)request.getAttribute("tawSuppKpiInstReportOrderForm");
 %>
<div class="list-title">
	<bean:message key="tawSupplierkpiInstanceList.StatOrderUpdate" />
</div>

<div class="list">
	<html:form action="/saveTawSuppKpiInstReportOrder.do?method=save" method="post"  styleId="tawSuppKpiInstReportOrderForm" onsubmit="return validate();">
		<table>
		<input type = "hidden" name = "serviceType" value = "<%=String.valueOf(request.getAttribute("serviceType"))%>"/>
		<input type = "hidden" name = "specialType" value = "<%=String.valueOf(request.getAttribute("specialType"))%>"/>
		<input type ="hidden" name="id" value="<%=String.valueOf(request.getAttribute("id")) %>"/>
		    <tr height="50">
		        <td>
		            <center><bean:message key="tawSupplierkpiInstanceList.reportName" /></center>
		        </td>
		        <td colspan="3">
		            <input type="text" class="text" name="reportName" value="${requestScope.reportName}" style="width:88%"/>
		        </td>
		    </tr>
				    
			<tr height="50">

				<td>
					<center><bean:message key="tawSupplierkpiInstanceList.latitude" /></center>
				</td>
				<td>
				    <eoms:dict key="dict-supplierkpi" dictId="statictsCycle" isQuery="false"
						defaultId="${requestScope.latitude}" selectId="latitude" beanId="selectXML" />		    				
				</td>				

			     <td>
			         <center><bean:message key="tawSupplierkpiInstanceList.reportType" /></center>
			     </td>
			     <td>
			     	<eoms:dict key="dict-supplierkpi" dictId="reportType" isQuery="false"
						defaultId="${requestScope.reportType}" selectId="reportType" beanId="selectXML" />		    	      
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
						onclick="popUpCalendar(this, this,null,null,null,true,-1);" value="${requestScope.reportStartTime}" />
		         </td>

		         <td width="7%">
		            <center><bean:message key="tawSupplierkpiInstanceList.reportEndTime" /></center>
		         </td>
		         <td>
		         	<input type="text" name="reportEndTime" id="fillEndTime" class="text"
						readonly="readonly"
						alt="vtype:'moreThen',link:'reportStartTime',vtext:'${eoms:a2u('终止时间不能早于生效时间！')}'"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"  value="${requestScope.reportEndTime}" />
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

			<tr align="right" height="40">
				<td colspan="4">
                  <input type="submit" class="btn" property="method.save" styleId="method.save" value="<bean:message key="button.update" />" />
				</td>
				
			</tr>
		</table>
	</html:form>
</div>


<%@ include file="/common/footer_eoms.jsp"%>
