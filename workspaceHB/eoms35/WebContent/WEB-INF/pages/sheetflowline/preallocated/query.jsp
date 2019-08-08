<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript" src="../../sheetfllowline/css/functions.js"></script>


<script language="javascript">

    Ext.onReady(function () {

        var userTreeAction = '${app}/xtree.do?method=userFromDept';
        userViewer = new Ext.JsonView("user-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div>请选择用户</div>'
            }
        );

        userTree = new xbox({
            btnId: 'userTreeBtn1', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '用户', treeChkMode: '', treeChkType: 'user',
            viewer: userViewer, saveChkFldId: 'dutyUserId'
        });
        var leaderViewer = new Ext.JsonView("leader-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div>请选择用户</div>'
            }
        );


        userTree = new xbox({
            btnId: 'userTreeBtn2', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '用户', treeChkMode: '', treeChkType: 'user',
            viewer: leaderViewer, saveChkFldId: 'dutyLeader'
        });
    });


</script>

<form name="addform" method="post" action="../sheetflowline/preAllocated.do?method=queryList">


    <table width="500" class="formTable">
        <caption>预分配规则查询</caption>
        <tr>
            <td class="label">
                开始时间
            </td>
            <td>
                <input type="text" class="text" name="startTime" readonly="readonly"
                       id="startTime"
                       onclick="popUpCalendar(this, this,'',0,0,true,'-1',0)"/>
            </td>
            <td class="label">
                结束时间
            </td>
            <td>
                <input type="text" class="text" name="endTime" readonly="readonly"
                       id="endTime"
                       onclick="popUpCalendar(this, this,'',0,0,true,'-1',0)"/>
            </td>
        </tr>
        <tr>

            <td class="label">
                <input type="button" name="userTreeBtn2" id="userTreeBtn2" value="值班长" class="button"/>

            </td>
            <td width="400">
                <div id="leader-list" class="viewer-list"></div>
                <input type="hidden" name="dutyLeader" id="dutyLeader" class="hidden"/>
            </td>
            <td class="label">
                <input type="button" name="userTreeBtn1" id="userTreeBtn1" value="人员" class="button"/>
            </td>
            <td>

                <div id="user-list" class="viewer-list"></div>
                <input type="hidden" class="hidden" name="dutyUserId" id="dutyUserId"/>
            </td>
        </tr>


        <tr>
            <td class="label">
                网络分类一级
            </td>
            <td>
                <eoms:comboBox name="netTypeOne" id="netTypeOne" sub="netTypeTwo"
                               initDicId="1010104"/>
            </td>
            <td class="label">
                网络分类二级
            </td>
            <td>
                <eoms:comboBox name="netTypeTwo" id="netTypeTwo" sub="netTypeThree"
                               initDicId="${object.netTypeOne}" defaultValue="${object.netTypeTwo}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                网络分类三级
            </td>
            <td>
                <eoms:comboBox name="netTypeThree" id="netTypeThree"
                               initDicId="${object.netTypeTwo}" defaultValue="${object.netTypeThree}"/>
            </td>
            <td class="label">
                厂商
            </td>
            <td>
                <eoms:comboBox name="vendor" id="vendor" initDicId="1010103" defaultValue="${object.vendor}"/>
            </td>
        </tr>
        <tr>

            <td class="label">
                故障响应级别
            </td>
            <td>
                <eoms:comboBox name="faultResponseLevel" id="faultResponseLevel" initDicId="1010304"/>
            </td>
            <td class="label">
                分配数量
            </td>
            <td>
                <input type="text" class="text" name="count" id="count" onkeyup="value=value.replace(/[^0-9]/g,'');"
                       value="${object.count}"/>
                张
                <div style="display:inline;"><font color="red" size="2">（请输入数字）</font></div>
            </td>
        </tr>
        <tr>

            <td class="label">
                开关
            </td>
            <td>
                <select id="isopen" name="isopen">
                    <option value="1" selected="selected">开</option>
                    <option value="0">关</option>
                </select>
            </td>
            <td class="label">

            </td>
            <td>

            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="查询" name="B1" Class="submit">


</form>

<%@ include file="/common/footer_eoms.jsp" %>
    	
