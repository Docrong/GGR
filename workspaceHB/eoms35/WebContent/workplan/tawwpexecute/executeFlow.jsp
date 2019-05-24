<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>  
<link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/typo.css" />  
<%@ include file="/common/body.jsp"%>
<%@ page import ="com.boco.eoms.workplan.model.TawwpWorkflow"%>
<%@ page import ="java.util.*"%>
<form name="workflowForm" method="post" > 
<table class="listTable">
<%
   if(request.getAttribute("workflowList")!=null){
      ArrayList list=(ArrayList)request.getAttribute("workflowList");
      String url="";
      String typeName="";
      for(int i=0;i<list.size();i++){
           TawwpWorkflow tawwpWorkflow=(TawwpWorkflow)list.get(i);
           if(tawwpWorkflow.getFlowType().equals("1")){
           	  url="/sheet/commonfault/commonfault.do?method=showMainDetailPage&sheetKey=297e06eb1c40b8d6011c40ff51d3002d";
%>            故障处理工单<a href="${app}<%=url%>" target="blank"><%=tawwpWorkflow.getFlowId()+tawwpWorkflow.getFlowType()%></a><br> 
      
<%           		
           }else{
          	  url="#"; 	 
%>      	  网优工单<a href='<%=url%>'  target="blank"><%=tawwpWorkflow.getFlowId()+tawwpWorkflow.getFlowType()%></a><br> 
 
<%    		}
            
        }
   }


%>
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>