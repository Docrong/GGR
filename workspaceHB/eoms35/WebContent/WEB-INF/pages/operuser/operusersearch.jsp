<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod" %>
<fmt:bundle basename="config/applicationResource-operuser">
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
                tabs.addTab('form', '运维人员查询');
                tabs.addTab('info', '帮助');
                tabs.activate('form');
            }
        }
        Ext.onReady(Tabs.init, Tabs, true);
        Ext.onReady(function () {
            var deptAction = '${app}/xtree.do?method=dept';
            new xbox({
                btnId: 'deptname',
                dlgId: 'dlg-dept',
                dlgTitle: '请选择该人员所属部门',
                treeDataUrl: deptAction,
                treeRootId: '-1',
                treeRootText: '所有部门',
                treeChkMode: 'single',
                treeChkType: 'dept',
                showChkFldId: 'deptname',
                saveChkFldId: 'deptid'
            });
        });

        function validate() {
            var frm = document.forms[0];
            if (frm.name.value == '' && frm.deptname.value == '') {
                alert("请输入查询条件！");
                return false;
            } else {
                return true;
            }

        }
    </script>
    <div id="tabs">
    <div id="form" class="tab-content">
    <html:form action="/operusers.do?method=query" onsubmit="return validate();">
        <table border="0" width="95%" cellspacing="1">

            <tr class="tr_show">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td class="clsfth" width="20%"><fmt:message key="operuser.name"/></td>
                <td colspan=3>
                    <html:text property="name" styleClass="text medium"/>
                </td>

            </tr>
            <tr class="tr_show">
                <td class="clsfth" width="20%"><fmt:message key="operuser.deptname"/></td>
                <td colspan=3>
                    <input type="hidden" id="deptid" name="deptid" value="${operuserForm.deptid}"/>
                    <input type="text" value="${operuserForm.deptname}" id="deptname" name="deptname"
                           class="text medium" readonly="readonly"/>
                </td>

            </tr>

        </table>
        <table border="0" width="20%" cellspacing="0">
            <tr>
                <td width="100%" height="32" align="right">
                    <center><html:submit styleClass="clsbtn2">
                        <fmt:message key="operuser.submit"/>
                    </html:submit></center>
                </td>
            </tr>
        </table>

    </html:form>
</fmt:bundle>
</div>
<div id="info">
    <dl>
        <dt>运维人员管理</dt>
        <dd>运维人员管理提供对运维人员的增加、修改、查询、统计和删除功能。</dd>
        <dt>运维人员查询</dt>
        <dd>输入运维人员查询条件，查询结果。</dd>
    </dl>
</div>
</div>
</body>
</html>
