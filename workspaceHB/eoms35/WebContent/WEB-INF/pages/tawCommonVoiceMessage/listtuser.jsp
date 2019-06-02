<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%
	List redList = (List) request.getAttribute("REDLIST");
	
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
        tabs.addTab('form', '${eoms:a2u('查询结果')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/voiceMessage/TawHieApply/queryuser.do"/>'"
        value='${eoms:a2u('返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">
<table cellpadding="0" class="table logList" cellspacing="0">
 <tr > 
            <td width='2%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('序号')}</p></td>
            <td width='8%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('姓名')}</p></td>
		    <td width='8%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('电话')}</p></td>
		    <td width="10%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('用户类型')}</p></td>
		    <td width="12%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('认证码')}</p></td>

		   
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('删除')}</p></td>
		    <td width="5%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('修改')}</p></td>
		 
</tr>
 
 <%for(int j=0;j<redList.size();j++){
 
  	List obj=(List)redList.get(j);

  %>
 <tr class="sortable">
            <td width='2%' align='center'><%=j%></td>

		    <td width='8%' align='center'><%=obj.get(1).toString()%></td>
		    <td width='10%' align='center'><%=obj.get(2).toString()%></td>
		    <td width='12%' align='center'><%=obj.get(5).toString()%></td>
		    <td width='20%' align='center'><%=obj.get(4).toString()%></td>

		    <td width='5%' align='center'>
		    <a href="${app}/voiceMessage/TawHieApply/redupdate.do?id=<%=obj.get(0).toString().trim()%>&flag=0">
		${eoms:a2u('删除')}</a>
				</td>
		    <td width='5%' align='center'>
		     <a href="${app}/voiceMessage/TawHieApply/redupdate.do?id=<%=obj.get(0).toString().trim()%>&flag=1">
${eoms:a2u('修改')}</a>
          </td>
		    
		   
</tr>
		    <%
		    }
		    %>

</table>

</div>

  <div id="info">
    ${eoms:a2u('点击删除，修改，可以链接到具体操作页面')}
  </div>
  
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>
