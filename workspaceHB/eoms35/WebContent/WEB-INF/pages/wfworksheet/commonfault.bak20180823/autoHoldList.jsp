<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function add() {
        location.href = "./commonfaultauto.do?method=showCommonfaultAutoPage&ruleType=autoHold";
    }

    function importData() {

        location.href = "./commonfaultauto.do?method=showCommonfaultconfigPage&ruleType=autoHold";
    }

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }

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

    var checkflag = "false";

    function choose() {
        var objs = document.getElementsByName("ids");
        if (checkflag == "false") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = true;
                checkflag = "true";
            }
        } else if (checkflag == "true") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = false;
                checkflag = "false"
            }
        }
    }

    function getr() {
        var ids = "";
        var objs = document.getElementsByName("ids");
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].type.toLowerCase() == "checkbox") {
                if (objs[i].checked == true) {
                    ids += objs[i].value;
                    ids += "#";
                }
            }
        }
        if (ids != null && ids != '') {
            document.getElementById("commonfaultautoids").value = ids;
            return true;
        } else {
            alert("选择不能为空");
            return false;
        }

    }

    function exportData() {
        var idstring = '';
        var objs = document.getElementsByName("ids");
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].checked == true) {
                idstring = idstring + objs[i].value + ",";
            }
        }
        if (idstring == '') {
            idstring = 'all';
        }
        window.location.href = "./commonfaultauto.do?method=exportData&&idstring=" + idstring;
    }

    function ConfirmExportData() {

        var flag = false;
        var ids;
        var objs = document.getElementsByName("ids");
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].type.toLowerCase() == "checkbox")
                if (objs[i].checked) {
                    flag = true;
                    ids = objs[i];
                }
        }
        if (flag) {
            if (confirm("您确定要导出您选择的数据？")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (confirm("您确定要导出全部数据？")) {
                return true;
            } else {
                return false;
            }
        }
    }
</script>

<bean:define id="url" value="commonfaultauto.do"/>
<input type="button" value="新增" onclick="add()" class="btn">
<input type="button" value="导入" onclick="importData();" class="btn">

<div style="border:1px solid #98c0f4;padding:5px;width:98%;" class="x-layout-panel-hd">
    工具栏：
    <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
    <span id="openQuery" style="cursor:pointer" onclick="openQuery(this);">打开快速查询</span>
</div>

<div id="listQueryObject"
     style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
    <jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/autoHoldListFastSearch.jsp"/>
</div>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
               export="false" requestURI="commonfaultauto.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.AutoHoldDisplaytagDecoratorHelper">

    <display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'>"/>

    <display:column sortable="true" headerClass="sortable" title="网络告警ID" sortName="remark1">
        <a href="./commonfaultauto.do?method=edit&ruleType=autoHold&type=open&id=${taskList.id}">
                ${taskList.remark1}
        </a>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="措施" sortName="commonFaultDesc">
        ${taskList.commonFaultDesc}
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="归档描述" sortName="remark2">
        ${taskList.remark2}
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="告警标题" sortName="alarmTitle">
        ${taskList.alarmTitle}
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="设备厂家" sortName="equipmentName">
        <eoms:id2nameDB id="${taskList.equipmentName}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="一级归因" sortName="linkFaultReasonSort">
        <eoms:id2nameDB id="${taskList.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="二级归因" sortName="linkFaultReasonSubsection">
        <eoms:id2nameDB id="${taskList.linkFaultReasonSubsection}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="三级归因" sortName="linkFaultReasonSubsectionTwo">
        <eoms:id2nameDB id="${taskList.linkFaultReasonSubsectionTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
    <display:setProperty name="export.rtf" value="false"/>
    <display:setProperty name="export.excel" value="false"/>
</display:table>
<form method="post" id="theform" action="commonfaultauto.do?method=delList">
    <input type="hidden" name="commonfaultautoids" id="commonfaultautoids"/>
    <tr>
        <td width="200px">
            <input type="submit" value="批量删除" Onclick=" return getr();" class="btn">
            <input type="button" class="btn" name="export" value="批量导出"
                   onclick="javascript:if(ConfirmExportData()) exportData()"/>
        </td>
    </tr>
</form>
<%@ include file="/common/footer_eoms.jsp" %>