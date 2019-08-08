<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<script type="text/javascript">
    function editx(id) {
        location.href = "netownershipwireless.do?method=xedit&id=" + id;
    }

    function deletex(id) {
        if (confirm("确认删除吗?")) {
            Ext.Ajax.request({
                url: "netownershipwireless.do?method=xdelete",
                method: 'POST',
                params: {id: id},
                success: function (x) {
                    var data = eoms.JSONDecode(x.responseText);
                    Ext.each(data, function (d) {
                        alert(d.alarmMsg);
                        if (d.alarmMsg == '删除成功') {
                            location.href = "netownershipwireless.do?method=showQueryPage";
                        }
                    });

                }
            });
        }
    }

    function deleteBatch() {
        var batchIds = document.getElementsByName("batchIds");
        var s = "";
        if (batchIds.length > 0) {
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].checked == true) {
                    s = s + batchIds[i].value + ",";
                }
            }
        }
        if (s == '') {
            Ext.Msg.alert("提示", "请勾选你要删除的网元!");
        } else {
            if (confirm("确认删除吗?")) {
                Ext.Ajax.request({
                    url: "netownershipwireless.do?method=xdeleteSome",
                    method: 'POST',
                    params: {ids: s},
                    success: function (x) {
                        var data = eoms.JSONDecode(x.responseText);
                        Ext.each(data, function (d) {
                            alert(d.alarmMsg);
                            if (d.alarmMsg == '删除成功') {
                                location.href = "netownershipwireless.do?method=showQueryPage";
                            }
                        });
                    }
                });
            }
        }
    }

    function edite() {
        var batchIds = document.getElementsByName("batchIds");
        var s = "";
        var saveEditFlag = document.getElementById("saveEditFlag").value;
        if (batchIds.length > 0) {
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].checked == true) {
                    s = s + batchIds[i].value + ",";
                }
            }
        }

        if (s == '') {
            alert("请勾选你要编辑的网元!");
        } else {
            if (saveEditFlag == '') {
                chooser_test22.enable();
                chooser_test2.enable();
                chooser_test33.enable();
                eoms.$('link1').show();
                eoms.$('link2').show();
                eoms.$('link3').show();
                eoms.$('edit').show();
                document.getElementById("saveEditFlag").value = "true";
            } else {
                eoms.$('link1').hide();
                eoms.$('link2').hide();
                eoms.$('link3').hide();
                eoms.$('edit').hide();
                document.getElementById("saveEditFlag").value = "";
            }
        }
    }

    function editBatch() {
        var teamRoleId = document.getElementsByName("teamRoleId");
        var ccObject = document.getElementsByName("ccObject");
        var ttRoleId = document.getElementsByName("ttRoleId");
        var ifauto = document.getElementById("ifAutotran").value;
        var center = "";
        var team = "";
        var tieta = "";
        var cc = "";
        if (ccObject.length > 0) {
            for (var i = 0; i < ccObject.length; i++) {
                cc = ccObject[i].value;
            }
        }
        if (teamRoleId.length > 0) {
            for (var i = 0; i < teamRoleId.length; i++) {
                team = teamRoleId[i].value;
            }
        }

        if (ttRoleId.length > 0) {
            for (var i = 0; i < ttRoleId.length; i++) {
                tieta = ttRoleId[i].value;
            }
        }
        var batchIds = document.getElementsByName("batchIds");
        var s = "";
        var el = Ext.get('editSomex');
        if (batchIds.length > 0) {
            for (var i = 0; i < batchIds.length; i++) {
                if (batchIds[i].checked == true) {
                    s = s + batchIds[i].value + ",";
                }
            }
        }
        if (s == '') {
            alert("请勾选你要修改的网元!");
        } else if (team == '' && tieta == '') {
            alert("维护班组和铁塔班组不能同时为空!");
        } else {
            Ext.Ajax.request({
                url: "netownershipwireless.do?method=xeditSome",
                method: 'POST',
                params: {
                    ids: s,
                    center: center,
                    team: team,
                    cc: cc,
                    tieta: tieta,
                    ifauto: ifauto
                },
                success: function (x) {
                    var data = eoms.JSONDecode(x.responseText);
                    Ext.each(data, function (d) {
                        alert(d.alarmMsg);
                        if (d.alarmMsg == '保存成功') {
                            location.href = "netownershipwireless.do?method=showQueryPage";
                        }
                    });
                }
            });
        }
    }
</script>

<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<jsp:include page="/WEB-INF/pages/wfworksheet/netownershipwireless/batchJs.jsp"/>
<jsp:include page="/WEB-INF/pages/wfworksheet/netownershipwireless/listsendUndoJS.jsp"/>
<%} %>
<bean:define id="url" value="netownershipwireless.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
               export="true" requestURI="netownershipwireless.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.netownershipwireless.webapp.action.NetOwnershipwirelessDisplaytagDecoratorHelper">

    <display:column sortable="true" property="id" media="html"
                    headerClass="sortable" title=""/>
    <display:column property="netName" sortable="true"
                    headerClass="sortable" title="网元名称"/>
    <display:column property="netNameByEdis" sortable="true"
                    headerClass="sortable" title="综资网元名称"/>
    <display:column property="netType" sortable="true"
                    headerClass="sortable" title="网元类型"/>
    <display:column property="zhuanye" sortable="true"
                    headerClass="sortable" title="专业"/>
    <display:column property="city" sortable="true"
                    headerClass="sortable" title="地市"/>
    <display:column property="county" sortable="true"
                    headerClass="sortable" title="区县"/>
    <display:column property="ifAutotran" sortable="true" media="html"
                    headerClass="sortable" title="是否自动移交"/>
    <display:column property="teamRoleId" sortable="true"
                    headerClass="sortable" title="维护班组"/>
    <display:column property="ttRoleId" sortable="true"
                    headerClass="sortable" title="铁塔班组"/>
    <display:column property="ccObject" sortable="true"
                    headerClass="sortable" title="抄送对象"/>

    <display:column property="tttype" sortable="true"
                    headerClass="sortable" title="铁塔属性"/>
    <display:column property="ifgs" sortable="true"
                    headerClass="sortable" title="是否高山站"/>
    <display:column property="ifmz" sortable="true"
                    headerClass="sortable" title="是否免责站"/>
    <display:column property="ifgj" sortable="true"
                    headerClass="sortable" title="是否高等级站"/>
    <display:column property="workstatprop" sortable="true"
                    headerClass="sortable" title="代维工作站属性"/>
    <display:column property="classes" sortable="true"
                    headerClass="sortable" title="类别属性"/>
    <display:column property="deleted" sortable="true"
                    headerClass="sortable" title=""/>

    <display:setProperty name="export.rtf" value="false"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
</br>
<!-- <input type="button" value="批量删除" title="一并删除所选中的网元" onclick="deleteBatch()" />-->
<input type="button" value="批量修改" title="同时修改多个网元的维护班组和维护中心" onclick="edite()"/></br>


<fieldset id="link1">
    <legend>维护班组</legend>

    <eoms:chooser id="test22" type="role" roleId="8005106" flowId="051" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'teamRoleId',text:'选择',vtext:'请选择修改对象'}]"/>

</fieldset>
<fieldset id="link3">
    <legend>铁塔班组</legend>

    <eoms:chooser id="test33" type="role" roleId="8005106" flowId="051" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'ttRoleId',text:'选择',vtext:'请选择修改对象'}]"/>

</fieldset>

<fieldset id="link2">
    <legend>抄送对象</legend>

    <eoms:chooser id="test2" type="role" roleId="8005106" flowId="051" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'ccObject',text:'选择',vtext:'请选择修改对象'}]"/>

</fieldset>
<br/>
<div id="edit">
    <tr>
        <td class="label">是否自动移交</td>
        <td class="content" colspan="3">
            <select name="ifAutotran" id="ifAutotran">
                <option value="0">否</option>
                <option value="1">是</option>
            </select>
        </td>
    </tr>
    </br>
    <input type="hidden" name="saveEditFlag" id="saveEditFlag" value="">
    <input type="button" name="saveEdit" id="saveEdit" value="保存" onclick="editBatch()">
</div>
</div>

<script type="text/javascript">

    Ext.onReady(function () {
        eoms.$('link1').hide();
        eoms.$('link2').hide();
        eoms.$('link3').hide();
        eoms.$('edit').hide();
    });
</script>
<%@ include file="/common/footer_eoms.jsp" %>
