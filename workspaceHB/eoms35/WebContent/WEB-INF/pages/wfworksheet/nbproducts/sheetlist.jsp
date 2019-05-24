<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<html>
<head>
  <title></title>
  <!-- HTTP 1.1 -->
  <meta http-equiv="Cache-Control" content="no-store"/>
  <!-- HTTP 1.0 -->
  <meta http-equiv="Pragma" content="no-cache"/>
  <!-- Prevents caching at the Proxy Server -->
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

  <script type="text/javascript" charset="utf-8" src="../../scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="../../scripts/base/eoms.js"></script>
   <script type="text/javascript">
	eoms.appPath = "../..";
  </script>
  <link rel="stylesheet" type="text/css" media="all" href="../../styles/default/theme.css" />
</head>

<table>
   <tr>
     <td>
       ${eoms:a2u('相关新业务设计配合工单列表')}
     </td>
   </tr>
</table>
<logic:present name="planList" scope="request">  
 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
             <thead>
  				<tr>
  				  <td nowrap width="200">
  				   ${eoms:a2u('工单流水号')}
  				  </td>
                 <td nowrap width="200">
  				 	${eoms:a2u('工单主题')}
                  </td>
                  <td nowrap width="200">
  				    ${eoms:a2u('受理时限')}
  				  </td>
  				  <td nowrap width="200">
  				  	 ${eoms:a2u('完成时限')}
                  </td>
                  <td nowrap width="100">
  				  	 ${eoms:a2u('工单状态')}
                  </td>
                </tr>
              </thead>
      <logic:iterate id="plan" name="planList" type="com.boco.eoms.sheet.businessplan.model.BusinessPlanMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/businessplan/businessplan.do?method=showDetailPage&sheetKey=${plan.id}" target="_blank"><bean:write name="plan" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="plan" property="title" scope="page"/>
  			
  				  </td>
  				  <td  class="content">
  				    <bean:write name="plan" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  				  </td>
  				  <td class="content">
  				    <bean:write name="plan" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
                  </td>
                  <td class="content">
                  <%if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    ${eoms:a2u('运行中')}
				  <%}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    ${eoms:a2u('已归档')}
				<%	}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						${eoms:a2u('已撤销')}
				<%	}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						${eoms:a2u('已删除')}
				<%	}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						${eoms:a2u('强制归档')}
				<%	}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						${eoms:a2u('强制作废')}
				<%	}else if(plan.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						${eoms:a2u('作废')}
				<%	}else{%>
						${eoms:a2u('')}
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="planList" scope="request">${eoms:a2u('无数据')}</logic:notPresent>
</br>
</br>
<table>
   <tr>
     <td>
       ${eoms:a2u('相关新业务试点工单列表')}
     </td>
   </tr>
</table>
<logic:present name="pilotList" scope="request">  

<table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<thead>
  				<tr>
  				  <td nowrap width="200">
  				   ${eoms:a2u('工单流水号')}
  				  </td>
                 <td nowrap width="200">
  				 	${eoms:a2u('工单主题')}
                  </td>
                  <td nowrap width="200">
  				    ${eoms:a2u('受理时限')}
  				  </td>
  				  <td nowrap width="200">
  				  	 ${eoms:a2u('完成时限')}
                  </td>
                  <td nowrap width="100">
  				  	 ${eoms:a2u('工单状态')}
                  </td>
                </tr>
              </thead>
    
      <logic:iterate id="pilot" name="pilotList" type="com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/business/businesspilot.do?method=showDetailPage&sheetKey=${pilot.id}" target="_blank"><bean:write name="pilot" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="pilot" property="title" scope="page"/>
  			
  				  </td>
  				  <td  class="content">
  				    <bean:write name="pilot" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  				  </td>
  				  <td class="content ">
  				    <bean:write name="pilot" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
                  </td>
                  <td class="content">
                  <%if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    ${eoms:a2u('运行中')}
				  <%}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    ${eoms:a2u('已归档')}
				<%	}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						${eoms:a2u('已撤销')}
				<%	}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						${eoms:a2u('已删除')}
				<%	}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						${eoms:a2u('强制归档')}
				<%	}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						${eoms:a2u('强制作废')}
				<%	}else if(pilot.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						${eoms:a2u('作废')}
				<%	}else{%>
						${eoms:a2u('')}
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="pilotList" scope="request">${eoms:a2u('无数据')}</logic:notPresent>
</br>
</br>
<table>
   <tr>
     <td>
       ${eoms:a2u('相关新业务正式实施工单列表')}
     </td>
   </tr>
</table>
<logic:present name="operationList" scope="request"> 
<table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<thead>
  				<tr>
  				  <td nowrap width="200">
  				   ${eoms:a2u('工单流水号')}
  				  </td>
                 <td nowrap width="200">
  				 	${eoms:a2u('工单主题')}
                  </td>
                  <td nowrap width="200">
  				    ${eoms:a2u('受理时限')}
  				  </td>
  				  <td nowrap width="200">
  				  	 ${eoms:a2u('完成时限')}
                  </td>
                  <td nowrap width="100">
  				  	 ${eoms:a2u('工单状态')}
                  </td>
                </tr>
              </thead>
              <%java.util.List list = (java.util.List)request.getAttribute("operationList");
              System.out.println("size==="+list.size()); %>
      <logic:iterate id="operate" name="operationList" type="com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain"> 
           <tr>
  				  <td  class="content">
  				   <a href="${app}/sheet/businessoperation/businessoperation.do?method=showDetailPage&sheetKey=${operate.id}" target="_blank"><bean:write name="operate" property="sheetId" scope="page"/></a> 			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="operate" property="title" scope="page"/>
  			
  				  </td>
  				  <td  class="content">
  				    <bean:write name="operate" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
  				  </td>
  				  <td class="content ">
  				    <bean:write name="operate" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll" bundle="sheet" scope="page"/>&nbsp; 
                  </td>
                  <td class="content">
                  <%if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    ${eoms:a2u('运行中')}
				  <%}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    ${eoms:a2u('已归档')}
				<%	}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						${eoms:a2u('已撤销')}
				<%	}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						${eoms:a2u('已删除')}
				<%	}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						${eoms:a2u('强制归档')}
				<%	}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						${eoms:a2u('强制作废')}
				<%	}else if(operate.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						${eoms:a2u('作废')}
				<%	}else{%>
						${eoms:a2u('')}
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="operationList" scope="request">${eoms:a2u('无数据')}</logic:notPresent>
</br>
</br>
<table>
   <tr>
     <td>
			<input type="button" style="margin-right: 5px"
					 value="${eoms:a2u("关闭")}" Onclick="window.close()">
     </td>
   </tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>