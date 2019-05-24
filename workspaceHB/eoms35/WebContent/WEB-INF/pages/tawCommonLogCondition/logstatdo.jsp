<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<% 
List list=(List)request.getAttribute("list");
String starttime=(String)request.getAttribute("starttime");
String endtime=(String)request.getAttribute("endtime");
%>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form', '${eoms:a2u('统计结果')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/log/tawCommonLogStat.do"/>'"
        value="RETURN"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">
<table cellpadding="0" class="table logList" cellspacing="0">
 <tr >
            <td width='10%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('序列号')}</p></td>
		    <td width='30%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('地市公司')}</p></td>
		    <td width="20%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('登录部门数')}</p></td>
		    <td width="20%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('登录用户数')}</p></td>
		    <td width="20%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('登录总次数')}</p></td>
</tr>
 
 <% for(int i=0;i<list.size();i++){
 List obj=(List)list.get(i);
 %>
 <tr class="sortable">
            <td width='10%' align='center'><%=i%></td>
		    <td width='30%' align='center'><%=obj.get(0).toString()%></td>
		    <td width="20%" align='center'><a href="${app}/log/TawCommonLogCondition/logstatdeptdo.do?flag=<%=i%>&searchbystarttime=<%=starttime%>&searchbyendtime=<%=endtime%>" target="_blank"><%=obj.get(1).toString()%></a></td>
		    <td width="20%" align='center'><a href="${app}/log/TawCommonLogCondition/logstatuserdo.do?flag=<%=i%>&searchbystarttime=<%=starttime%>&searchbyendtime=<%=endtime%>" target="_blank"><%=obj.get(2).toString()%></a></td>
		    <td width="20%" align='center'><a href="${app}/log/TawCommonLogCondition/logstatalldo.do?flag=<%=i%>&searchbystarttime=<%=starttime%>&searchbyendtime=<%=endtime%>" target="_blank"><%=obj.get(3).toString()%></a></td>
</tr>
		    <% }%>

</table>

</div>

  <div id="info">
    ${eoms:a2u('点击统计出来的数据，可以链接到具体明细')}
  </div>
  
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>