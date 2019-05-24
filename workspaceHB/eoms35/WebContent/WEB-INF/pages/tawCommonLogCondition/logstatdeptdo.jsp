<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<% 

List list=(List)request.getAttribute("list");

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
        tabs.addTab('form', '${eoms:a2u('部门统计')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>



<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="window.close();"
        value='${eoms:a2u('关闭')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">
<table cellpadding="0" class="table logList" cellspacing="0">
 <tr >
            <td width='10%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('地市公司')}</p></td>
		    <td width='30%' align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('登录用户数')}</p></td>
		    <td width="20%" align='center'  bgcolor='#66CCFF'><p>${eoms:a2u('总登录次数')}</p></td>
		    
</tr>
 
 <% for(int i=0;i<list.size();i++){

 //Object obj=list.get(i);
 Object [] obj=(Object [])list.get(i);
 
 %>
 <tr class="sortable">
            
		    <td width='30%' align='center'><%=obj[0]%></td>
		    <td width="20%" align='center'><%=obj[1]%></a></td>
		    <td width="20%" align='center'><%=obj[2]%></td>
		  
</tr>
		    <% }%>

</table>
<%System.out.print("88888888888888888888"); %>
</div>
  <!--  div id="info">
   <display:table name="tawcommonfilelist" cellspacing="0" cellpadding="0"
    id="tawcommonlogoperlist" pagesize="25" class="table tawcommonlogoperlist"
    export="true" requestURI="" sort="list">

     <display:column property="userid" sortable="true" headerClass="sortable"
      
         titleKey="tawCommonLogDeployForm.userid"/>
     
     
     
      <display:column property="modelname" sortable="true" headerClass="sortable"
       
         titleKey="tawCommonLogDeployForm.modelname"/>
     
     <display:column property="operid" sortable="true" headerClass="sortable"
     
         titleKey="tawCommonLogDeployForm.operid"/>
     
     <display:column property="opername" sortable="true" headerClass="sortable"
    
         titleKey="tawCommonLogDeployForm.opername"/>
     
     
     
     
   
   

    <display:setProperty name="paging.banner.item_name" value="tawCommonLogDeploy"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonLogDeploys"/>
</display:table>
  </div>-->
</div>
<c:out value="${buttons}" escapeXml="false"/>



<%@ include file="/common/footer_eoms.jsp"%>