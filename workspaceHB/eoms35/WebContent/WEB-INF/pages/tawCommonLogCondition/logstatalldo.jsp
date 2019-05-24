<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
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
        tabs.addTab('form', '${eoms:a2u('所有登录统计')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<% 

String deptname=new String("登录部门".getBytes("ISO8859-1"),"utf-8");
String username=new String("登录人员".getBytes("ISO8859-1"),"utf-8");
String time=new String("登录时间".getBytes("ISO8859-1"),"utf-8");
String ip=new String("登录IP".getBytes("ISO8859-1"),"utf-8");
%>


<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="window.close();"
        value='${eoms:a2u('关闭')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">

<display:table name="logList" cellspacing="0" cellpadding="0"
    id="logList" pagesize="${pageSize }" class="table logList"
    export="true" requestURI="${app}/log/TawCommonLogCondition/logstatalldo.do" sort="list" partialList="true" size="resultSize" >
			<display:column property="deptname" sortable="true" headerClass="sortable" title='<%=deptname%>'/>
			<display:column property="username" sortable="true" headerClass="sortable" title='<%=username%>'
       
       />
    <display:column property="beginnotetime" sortable="true" headerClass="sortable" title='<%=time%>'
       
        />

        
     
    <display:column property="remoteip" sortable="true" headerClass="sortable" title='<%=ip%>'
       
       />
         


    <display:setProperty name="paging.banner.item_name" value="tawCommonLogDeploy"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonLogDeploys"/>
</display:table>
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
