<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String jsonString1 = (String) request.getAttribute("jsonString1");
    String jsonString2 = (String) request.getAttribute("jsonString2");
%>
<script type="text/javascript">
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

    function onsubmitCheck() {
        var learderName = document.getElementById("leader-list").innerText;
        var userName = document.getElementById("user-list").innerText;
        document.getElementById("dutyLeaderName").value = learderName;
        document.getElementById("dutyUserName").value = userName;
        return true;

    }


    Ext.onReady(function () {

        var userTreeAction = '${app}/xtree.do?method=userFromDept';
        userViewer = new Ext.JsonView("user-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true
            }
        );
        var l = '<%=jsonString1%>';
        userViewer.jsonData = eoms.JSONDecode(l);
        userViewer.refresh();
        userTree = new xbox({
            btnId: 'userTreeBtn1', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '用户', treeChkMode: '', treeChkType: 'user',
            viewer: userViewer, saveChkFldId: 'dutyUserId'
        });
        var leaderViewer = new Ext.JsonView("leader-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true
            }
        );
        var s = '<%=jsonString2%>';
        leaderViewer.jsonData = eoms.JSONDecode(s);
        leaderViewer.refresh();

        userTree = new xbox({
            btnId: 'userTreeBtn2', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '用户', treeChkMode: '', treeChkType: 'user',
            viewer: leaderViewer, saveChkFldId: 'dutyLeader'
        });
    });


</script>
<div style="border:1px solid #98c0f4;padding:5px;width:98%;" class="x-layout-panel-hd">
    工具栏：
    <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
    <span id="openQuery" style="cursor:pointer" onclick="openQuery(this);">打开快速查询</span>
</div>

<div id="listQueryObject"
     style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
    <form name="queryform" method="post" action="../sheetflowline/preAllocated.do?method=queryList"
          onsubmit="return onsubmitCheck();">

        <table width="100%" class="formTable">
            <tr>

                <td class="label">
                    <input type="button" name="userTreeBtn2" id="userTreeBtn2" value="值班长" class="button"/>

                </td>
                <td width="400">
                    <div id="leader-list" class="viewer-list"></div>
                    <input type="hidden" name="dutyLeader" id="dutyLeader" class="hidden"/>
                    <input type="hidden" name="dutyLeaderName" id="dutyLeaderName" class="hidden"/>
                </td>
                <td class="label">
                    <input type="button" name="userTreeBtn1" id="userTreeBtn1" value="人员" class="button"/>
                </td>
                <td>

                    <div id="user-list" class="viewer-list"></div>
                    <input type="hidden" class="hidden" name="dutyUserId" id="dutyUserId"/>
                    <input type="hidden" class="hidden" name="dutyUserName" id="dutyUserName"/>
                </td>
            </tr>


            <tr>
                <td class="label">
                    开始时间
                </td>
                <td>
                    <input type="text" class="text" name="startTime" readonly="readonly"
                           id="startTime" value="${eoms:date2String(object.startTime) }"
                           onclick="popUpCalendar(this, this,'',0,0,true,'-1',0)"/>
                </td>
                <td class="label">
                    结束时间
                </td>
                <td>
                    <input type="text" class="text" name="endTime" readonly="readonly"
                           id="endTime" value="${eoms:date2String(object.endTime) }"
                           onclick="popUpCalendar(this, this,'',0,0,true,'-1',0)"/>
                </td>
            </tr>


            <tr>

                <td colspan="4">
                    <input type="submit" value="查询" class="submit"/>
                    <input type="reset" value="重置" class="button"/>
                </td>
            </tr>

        </table>

    </form>


</div>
