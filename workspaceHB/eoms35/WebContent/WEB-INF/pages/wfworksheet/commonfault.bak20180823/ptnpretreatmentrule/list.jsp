<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        var v = new eoms.form.Validation({form: 'theform'});
    });

    function openQuery(handler) {
        var el = Ext.get('listQueryObject');
        if (el.isVisible()) {
            el.slideOut('t', {useDisplay: true});
            handler.innerHTML = "打开快速查询";
        } else {
            el.slideIn();
            handler.innerHTML = "关闭快速查询";
        }
    }

    function showAdd() {
        window.location.href = 'ptnpretreatmentrule.do?method=showInputNewPage';
    }

    function showImport() {
        window.location.href = 'ptnpretreatmentrule.do?method=showImportPage';
    }

    function deleteRule(id) {
        if (confirm('你确定要删除吗?')) {
            Ext.Ajax.request({
                method: 'post',
                url: 'ptnpretreatmentrule.do?method=delete&id=' + id,
                success: function (res) {
                    var data = eoms.JSONDecode(res.responseText);
                    if (data.deleted == 'yes') {
                        alert('删除成功。');
                        window.location.href = 'ptnpretreatmentrule.do?method=showList';
                    }
                }
            });
        }
    }
</script>

<div style="border:1px solid #98c0f4;padding:5px;" class="x-layout-panel-hd">
    工具栏：
    <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
    <span id="openQuery" style="cursor:pointer" onclick="openQuery(this);">打开快速查询</span>
</div>
<div id="listQueryObject" style="display:none;">
    <html:form styleId="theform" method="post" action="ptnpretreatmentrule.do?method=showList">
        <table class="listTable taskList">
            <tr>
                <td style="background-color:#eff6ff;">告警ID*</td>
                <td style="background-color:#eff6ff;">
                    <input type="text" class="text" id="alarmId" name="alarmId"
                           alt="allowBlank:false,vtext:'请填入 告警ID 信息'" value="${alarmId }"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="submit" class="btn" value="查询"/>
                </td>
            </tr>
        </table>
    </html:form>
</div>
<br/>
<div style="width:100%">
    <display:table name="ruleList" cellspacing="0" cellpadding="0"
                   id="ruleList" pagesize="${pageSize}" class="listTable taskList"
                   export="true" requestURI="ptnpretreatmentrule.do"
                   sort="external" size="total" partialList="true"
                   decorator="">

        <display:caption media="html">
            传输网自动归档配置列表
        </display:caption>
        <display:column sortable="true" headerClass="sortable" title="厂家">
            <eoms:id2nameDB id="${ruleList.factory }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="设备类型">
            ${ruleList.equipmentType }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="告警名称">
            ${ruleList.alarmName }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="告警ID">
            ${ruleList.alatmID }
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="处理措施">
            <eoms:id2nameDB id="${ruleList.faultDealDesc }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="归因一级">
            <eoms:id2nameDB id="${ruleList.faultReasonSort1 }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="归因二级">
            <eoms:id2nameDB id="${ruleList.faultReasonSort2 }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="归因三级">
            <eoms:id2nameDB id="${ruleList.faultReasonSort3 }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="预处理对应关系">
            <eoms:id2nameDB id="${ruleList.preDealRelation }" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="操作" media="html">
            <a href="ptnpretreatmentrule.do?method=showInputNewPage&id=${ruleList.id }">编辑</a>
            &nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="deleteRule('${ruleList.id }')">删除</a>
        </display:column>

        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>
    </display:table>
    <br/>
    <input type="button" class="btn" value="新增" onclick="showAdd();"/>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" class="btn" value="导入新增" onclick="showImport();"/>
</div>
<%@ include file="/common/footer_eoms.jsp" %>