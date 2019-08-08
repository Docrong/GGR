<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<style>
    #tabs {
        width: 90%;
    }

    #tabs .x-tabs-item-body {
        display: none;
        padding: 10px;
    }
</style>
<script type="text/javascript">
    var Tabs = {
        init: function () {
            var tabs = new Ext.TabPanel('tabs');
            tabs.addTab('form', '${eoms:a2u('日志查询结果')}');
            tabs.addTab('info', '${eoms:a2u('帮助')}');
            tabs.activate('form');
        }
    }
    Ext.onReady(Tabs.init, Tabs, true);
</script>

<content tag="heading"><fmt:message key="tawCommonLogDeployList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/log/tawCommonLogConditions.do?method=search"/>'"
           value='${eoms:a2u('返回')}'/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<div id="tabs">

    <div id="form" class="tab-content">

        <display:table name="logList" cellspacing="0" cellpadding="0"
                       id="logList" pagesize="${pageSize }" class="table logList"
                       export="true" requestURI="${app}/log/TawCommonLogCondition/querydo.do" sort="list"
                       partialList="true" size="resultSize"
                       decorator="com.boco.eoms.commons.log.displaytag.logListDispaly">

            <display:column property="userid" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.userid"/>

            <display:column property="modelid" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.modelid"/>

            <display:column property="modelname" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.modelname"/>

            <display:column property="operid" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.operid"/>

            <display:column property="opername" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.opername"/>

            <display:column property="issucess" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.loglevel"/>


            <display:column property="beginnotetime" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.beginnotetime"/>

            <display:column property="operremark" sortable="true" headerClass="sortable"

                            titleKey="tawCommonLogDeployForm.noteremark"/>

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
    <div id="info">
        <dl>
            <dt>${eoms:a2u('功能说明')}</dt>
            <dd>${eoms:a2u('显示用户对此模块操作记录，用于管理员根据错误信息找到问题所在。')}</dd>
            <dt>${eoms:a2u('日志导出')}</dt>
            <dd>${eoms:a2u('能够将所得列表导出，保存到本地进行详细查看。')}</dd>
        </dl>
    </div>
</div>
<c:out value="${buttons}" escapeXml="false"/>


<%@ include file="/common/footer_eoms.jsp" %>