<%@ page language="java" import="java.util.*,com.boco.eoms.base.util.StaticMethod,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance,com.boco.eoms.base.util.StaticMethod,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
//String specialType = request.getAttribute("specialType").toString();
//String serviceType = request.getAttribute("serviceType").toString();
//String month = request.getAttribute("month").toString();
String modelId = StaticMethod.null2String(request.getAttribute("modelId").toString());
String reportTime = StaticMethod.null2String(request.getAttribute("reportTime").toString());
String specialType = StaticMethod.null2String(request.getAttribute("specialType").toString());
String manufacturerId = StaticMethod.null2String(request.getAttribute("manufacturerId").toString());
%>
<script type="text/javascript">
function trInfo(kpiId, graphType){
 var modelId = document.forms[0].modelId.value;
 var reportTime = document.forms[0].reportTime.value;
 var specialType = document.forms[0].specialType.value;
 var manufacturerId = document.forms[0].manufacturerId.value;
 
 var url="<c:url value="/supplierkpi/tawSupplierkpiInstanceAsss.do?method=openVerticalWindow"/>";
 url = url + "&manufacturerId=" + manufacturerId + "&modelId=" + modelId + "&reportTime=" + reportTime + "&specialType=" + specialType + "&graphType=" + graphType + "&kpiId=" + kpiId;
  window.open(url,'','height=500,width=600,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
}

function getValues(el){
  var trElements=document.getElementById(el);
  var kpiValue = '';    
  var temp  = trElements.cells;       
    for(var j = 0; j<temp.length-1; j++){
      kpiValue  = kpiValue + temp[j].innerHTML.replace(/<.+?>/gim,'');
      kpiValue = kpiValue + '@@ ';
    }
    return(kpiValue);
}
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
	${eoms:a2u('纵向报表页面')}
</div>
</fmt:bundle>

<form>
	<input type="hidden" id="modelId" value="<%=modelId%>" />
	<input type="hidden" id="reportTime" value="<%=reportTime%>" />
	<input type="hidden" id="specialType" value="<%=specialType%>" />
	<input type="hidden" id="manufacturerId" value="<%=manufacturerId%>" />
</form>

<div class="list">
<table>

<%
ArrayList list1 = (ArrayList) request.getAttribute("tawSuppkpiReportStorages_1");
ArrayList list2 = (ArrayList) request.getAttribute("tawSuppkpiReportStorages_2");

 for (int i=0;i<list1.size();i++){
 HashMap hm = (HashMap)list1.get(i);
 String key = "col_"+i;
 String kpiName = "";
 String kpiId = "";
 String trName = "trName"+i;
 if (i > 0) {
   TawSupplierkpiItem item = (TawSupplierkpiItem) hm.get(key);
   kpiName = StaticMethod.null2String(item.getKpiName()).trim();
   kpiId = StaticMethod.null2String(item.getId());
%>
  <tr id="<%=trName%>">
  <%}else {%>
  <tr id="<%=trName%>" class="header" height="40">
  <%}%>
  <td width="20%"><%=kpiName%></td>
<%
 for(int j=0;j<list2.size();j++){
 HashMap map = (HashMap)list2.get(j);
 String temp1 = String.valueOf(map.get(key)).trim();
 String kpi2 = StaticMethod.null2String(temp1);
%>
  <td id="b1" width="6%"><%=kpi2 %></td>
<%  
 }if(i==0){
%>
  <td width="8%"><label>${eoms:a2u('图表显示')}</label></td> 
<%
}else{
%>
  <td>
    <a href="javascript:trInfo('<%=kpiId%>', '3D-Column.xml')">${eoms:a2u('柱图')}</a>
    <a href="javascript:trInfo('<%=kpiId%>', '3D-Pie.xml')">${eoms:a2u('饼图')}</a>
  </td>
</tr>
<% 
}
}
%>

</table>
</div>
<%@ include file="/common/footer_eoms.jsp"%>