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
        tabs.addTab('form', '${eoms:a2u('项目查询')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<% 

String deptname=new String("&nbsp;&nbsp;&nbsp;&nbsp;项目名称&nbsp;&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String username=new String("&nbsp;&nbsp;项目分解&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String time=new String("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String ip=new String("&nbsp;&nbsp;&nbsp;人员&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String task=new String("&nbsp;&nbsp;&nbsp;任务细分&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String complete=new String("&nbsp;&nbsp;&nbsp;完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan3=new String("&nbsp;&nbsp;&nbsp;三月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete3=new String("&nbsp;&nbsp;&nbsp;三月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan4=new String("&nbsp;&nbsp;&nbsp;四月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete4=new String("&nbsp;&nbsp;&nbsp;四月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan5=new String("&nbsp;&nbsp;&nbsp;五月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete5=new String("&nbsp;&nbsp;&nbsp;五月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan6=new String("&nbsp;&nbsp;&nbsp;六月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete6=new String("&nbsp;&nbsp;&nbsp;六月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan7=new String("&nbsp;&nbsp;&nbsp;七月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete7=new String("&nbsp;&nbsp;&nbsp;七月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan8=new String("&nbsp;&nbsp;&nbsp;八月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete8=new String("&nbsp;&nbsp;&nbsp;八月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan9=new String("&nbsp;&nbsp;&nbsp;九月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete9=new String("&nbsp;&nbsp;&nbsp;九月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan10=new String("&nbsp;&nbsp;&nbsp;十月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete10=new String("&nbsp;&nbsp;&nbsp;十月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan11=new String("&nbsp;&nbsp;&nbsp;十一月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete11=new String("&nbsp;&nbsp;&nbsp;十一月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");

String plan12=new String("&nbsp;&nbsp;&nbsp;十二月计划&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
String complete12=new String("&nbsp;&nbsp;&nbsp;十二月完成情况&nbsp;&nbsp;&nbsp;".getBytes("ISO8859-1"),"utf-8");
%>




<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

<div id="form" class="tab-content">

<display:table name="logList" cellspacing="0" cellpadding="0"
    id="logList" pagesize="${pageSize }" class="table logList"
    export="false" requestURI="${app}/taskplan/taskplan/querydo.do" sort="list" partialList="true" size="resultSize" 
    decorator="com.boco.eoms.taskplan.displaytag.taskplanDispaly">
			<display:column property="project_name" sortable="true" headerClass="sortable" title='<%=deptname%>'/>
			<display:column property="project_decompose" sortable="true" headerClass="sortable" title='<%=username%>'
       
       />
    <display:column property="deptid" sortable="true" headerClass="sortable" title='<%=time%>'
       
        />

        
     <display:column property="task_decompose" sortable="true" headerClass="sortable" title='<%=task%>'
       
       />
       
    <display:column property="stakeholders" sortable="true" headerClass="sortable" title='<%=ip%>'
       
       />
       
      
       
       <display:column property="task_plan_3" sortable="true" headerClass="sortable" title='<%=plan3%>'
       
       />
       
       <display:column property="task_complete_3" sortable="true" headerClass="sortable" title='<%=complete3%>'
       
       />
       
       <display:column property="task_plan_4" sortable="true" headerClass="sortable" title='<%=plan4%>'
       
       />
       
       <display:column property="task_complete_4" sortable="true" headerClass="sortable" title='<%=complete4%>'
       
       />
       
       <display:column property="task_plan_5" sortable="true" headerClass="sortable" title='<%=plan5%>'
       
       />
       
       <display:column property="task_complete_5" sortable="true" headerClass="sortable" title='<%=complete5%>'
       
       />
       
       <display:column property="task_plan_6" sortable="true" headerClass="sortable" title='<%=plan6%>'
       
       />
       
       <display:column property="task_complete_6" sortable="true" headerClass="sortable" title='<%=complete6%>'
       
       />
       
       <display:column property="task_plan_7" sortable="true" headerClass="sortable" title='<%=plan7%>'
       
       />
       
       <display:column property="task_complete_7" sortable="true" headerClass="sortable" title='<%=complete7%>'
       
       />
       
       <display:column property="task_plan_8" sortable="true" headerClass="sortable" title='<%=plan8%>'
       
       />
       
       <display:column property="task_complete_8" sortable="true" headerClass="sortable" title='<%=complete8%>'
       
       />
       
       <display:column property="task_plan_9" sortable="true" headerClass="sortable" title='<%=plan9%>'
       
       />
       
       <display:column property="task_complete_9" sortable="true" headerClass="sortable" title='<%=complete9%>'
       
       />
       
       <display:column property="task_plan_10" sortable="true" headerClass="sortable" title='<%=plan10%>'
       
       />
       
       <display:column property="task_complete_10" sortable="true" headerClass="sortable" title='<%=complete10%>'
       
       />
       
       <display:column property="task_plan_11" sortable="true" headerClass="sortable" title='<%=plan11%>'
       
       />
       
       <display:column property="task_complete_11" sortable="true" headerClass="sortable" title='<%=complete11%>'
       
       />
       
       <display:column property="task_plan_12" sortable="true" headerClass="sortable" title='<%=plan12%>'
       
       />
       
       <display:column property="task_complete_12" sortable="true" headerClass="sortable" title='<%=complete12%>'
       
       />
       
       
         


    <display:setProperty name="paging.banner.item_name" value="taskplan"/>
    <display:setProperty name="paging.banner.items_name" value="taskplan"/>
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
