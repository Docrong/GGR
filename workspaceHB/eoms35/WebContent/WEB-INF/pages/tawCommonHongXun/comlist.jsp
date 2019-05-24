<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%
	List redList = (List) request.getAttribute("REDLIST");
	Iterator iter = redList.iterator();
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
        tabs.addTab('form', '${eoms:a2u('机构列表')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/hongxun/TawCommonHongXunAction/comadd.do"/>'"
        value='${eoms:a2u('返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">
<table cellpadding="0" class="table logList" cellspacing="0">
 <tr > 
            <td width='2%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('序号')}</p></td>
            <td width='8%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('机构名称')}</p></td>
		    
		  
</tr>
 
 <%
  	int i = 0;
  	while (iter.hasNext()) {
  		Map map = (Map) iter.next();
  %>
 <tr class="sortable">
            <td width='2%' align='center'><%=++i%></td>
		    <td width='8%' align='center'><%=map.get("com_name").toString().trim()%></td>
		   
</tr>
		    <%
		    }
		    %>

</table>

</div>

  <div id="info">
    ${eoms:a2u('机构列表')}
  </div>
  
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>
