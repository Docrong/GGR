<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
<c:if test="${resourceconfirmList != null}">
<table>
   <tr>
     <td>
       相关资源确认工单列表
     </td>
   </tr>
</table>
<logic:present name="resourceconfirmList" scope="request">  
 <table class="listTable" width="100%" cellpadding="0" cellspacing="0">
             <thead>
  				<tr>
  				  <td nowrap width="200">
  				   工单流水号
  				  </td>
                 <td nowrap width="200">
  				 	工单主题
                  </td>
                  <td nowrap width="200">
  				    受理时限
  				  </td>
  				  <td nowrap width="200">
  				  	 完成时限
                  </td>
                  <td nowrap width="100">
  				  	 工单状态
                  </td>
                </tr>
              </thead>
      <logic:iterate id="resourceconfirm" name="resourceconfirmList" type="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/resourceconfirm/resourceconfirm.do?method=showDetailPage&sheetKey=${resourceconfirm.id}" target="_blank"><bean:write name="resourceconfirm" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="resourceconfirm" property="title" scope="page"/>
  				  </td>
  				  <td  class="content">
  				    ${eoms:date2String(resourceconfirm.sheetAcceptLimit)}&nbsp; 
  				  </td>
  				  <td class="content">
  				    ${eoms:date2String(resourceconfirm.sheetCompleteLimit)}&nbsp; 
                  </td>
                  <td class="content">
                  <%if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    运行中
				  <%}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    已归档
				<%	}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						已撤销
				<%	}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						已删除
				<%	}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						强制归档
				<%	}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						强制作废
				<%	}else if(resourceconfirm.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						作废
				<%	}else{%>
						
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="resourceconfirmList" scope="request">无数据</logic:notPresent>
</br>
</br>
</c:if>
<c:if test="${businessimplementList != null}">
<table>
   <tr>
     <td>
       相关业务开通工单列表
     </td>
   </tr>
</table>
<logic:present name="businessimplementList" scope="request">  

<table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<thead>
  				<tr>
  				  <td nowrap width="200">
  				   工单流水号
  				  </td>
                 <td nowrap width="200">
  				 	工单主题
                  </td>
                  <td nowrap width="200">
  				    受理时限
  				  </td>
  				  <td nowrap width="200">
  				  	完成时限
                  </td>
                  <td nowrap width="100">
  				  	 工单状态
                  </td>
                </tr>
              </thead>
    
      <logic:iterate id="businessimplement" name="businessimplementList" type="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/businessimplement/businessimplement.do?method=showDetailPage&sheetKey=${businessimplement.id}" target="_blank"><bean:write name="businessimplement" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="businessimplement" property="title" scope="page"/>
  			
  				  </td>
  				  <td  class="content">
  				    ${eoms:date2String(businessimplement.sheetAcceptLimit)}&nbsp; 
  				  </td>
  				  <td class="content">
  				    ${eoms:date2String(businessimplement.sheetCompleteLimit)}&nbsp; 
                  </td>
                  <td class="content">
                  <%if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    运行中
				  <%}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    已归档
				<%	}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						已撤销
				<%	}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						已删除
				<%	}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						强制归档
				<%	}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						强制作废
				<%	}else if(businessimplement.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						作废
				<%	}else{%>
						
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="businessimplementList" scope="request">无数据</logic:notPresent>
</br>
</br>
</c:if>
<c:if test="${operationchangeList != null}">
<table>
   <tr>
     <td>
       相关业务变更工单列表
     </td>
   </tr>
</table>
<logic:present name="operationchangeList" scope="request">  

<table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<thead>
  				<tr>
  				  <td nowrap width="200">
  				   工单流水号
  				  </td>
                 <td nowrap width="200">
  				 	工单主题
                  </td>
                  <td nowrap width="200">
  				    受理时限
  				  </td>
  				  <td nowrap width="200">
  				  	完成时限
                  </td>
                  <td nowrap width="100">
  				  	 工单状态
                  </td>
                </tr>
              </thead>
    
      <logic:iterate id="operationchange" name="operationchangeList" type="com.boco.eoms.sheet.operationchange.model.OperationChangeMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/operationchange/operationchange.do?method=showDetailPage&sheetKey=${operationchange.id}" target="_blank"><bean:write name="operationchange" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="operationchange" property="title" scope="page"/>
  			
  				  </td>
  				   <td  class="content">
  				    ${eoms:date2String(operationchange.sheetAcceptLimit)}&nbsp; 
  				  </td>
  				  <td class="content">
  				    ${eoms:date2String(operationchange.sheetCompleteLimit)}&nbsp; 
                  </td>
                  <td class="content">
                  <%if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    运行中
				  <%}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    已归档
				<%	}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						已撤销
				<%	}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						已删除
				<%	}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						强制归档
				<%	}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						强制作废
				<%	}else if(operationchange.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						作废
				<%	}else{%>
						
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="operationchangeList" scope="request">无数据</logic:notPresent>
</br>
</br>
</c:if>
<c:if test="${operationbackoutList != null}">
<table>
   <tr>
     <td>
       相关业务拆除工单列表
     </td>
   </tr>
</table>
<logic:present name="operationbackoutList" scope="request">  

<table class="listTable" width="100%" cellpadding="0" cellspacing="0">
  				<thead>
  				<tr>
  				  <td nowrap width="200">
  				   工单流水号
  				  </td>
                 <td nowrap width="200">
  				 	工单主题
                  </td>
                  <td nowrap width="200">
  				    受理时限
  				  </td>
  				  <td nowrap width="200">
  				  	完成时限
                  </td>
                  <td nowrap width="100">
  				  	 工单状态
                  </td>
                </tr>
              </thead>
    
      <logic:iterate id="operationbackout" name="operationbackoutList" type="com.boco.eoms.sheet.operationbackout.model.OperationBackOutMain"> 
           <tr>
  				  <td  class="content">
  				  <a href="${app}/sheet/operationbackout/operationbackout.do?method=showDetailPage&sheetKey=${operationbackout.id}" target="_blank"><bean:write name="operationbackout" property="sheetId" scope="page"/></a>
  			
  				  </td>
  				  <td  class="content">
  				  <bean:write name="operationbackout" property="title" scope="page"/>
  			
  				  </td>
  				  <td  class="content">
  				    ${eoms:date2String(operationbackout.sheetAcceptLimit)}&nbsp; 
  				  </td>
  				  <td class="content">
  				    ${eoms:date2String(operationbackout.sheetCompleteLimit)}&nbsp; 
                  </td>
                  <td class="content">
                  <%if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_RUN.intValue()){%>
					    运行中
				  <%}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_HOLD.intValue()){%>
					    已归档
				<%	}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_CANCEL.intValue()){%>
						已撤销
				<%	}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.SHEET_DELETE.intValue()){%>
						已删除
				<%	}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_HOLD){%>
						强制归档
				<%	}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_FORCE_NULLITY){%>
						强制作废
				<%	}else if(operationbackout.getStatus().intValue()== com.boco.eoms.sheet.base.util.Constants.ACTION_NULLITY){%>
						作废
				<%	}else{%>
						
				<%	}%>
  				  </td>
                </tr>
      </logic:iterate>
         </table>
</logic:present> 
<logic:notPresent name="operationbackoutList" scope="request">无数据</logic:notPresent>
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>