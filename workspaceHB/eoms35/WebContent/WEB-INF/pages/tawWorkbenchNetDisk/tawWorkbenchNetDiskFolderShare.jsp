<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String shareToUserIds = request.getAttribute("shareToUserIds").toString();
    String folderMappingId = request.getAttribute("folderMappingId").toString();
%>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
        var userTreeAction = '${app}/xtree.do?method=userFromDept&noself=true';
        userViewer = new Ext.JsonView("user-list",
            '<div id="role-user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div>${eoms:a2u("此文件夹目前没有共享给其他用户。")}</div>'
            }
        );
        var s = '<%=shareToUserIds%>';
        userViewer.jsonData = eoms.JSONDecode(s);
        userViewer.refresh();

        roleUserTree = new xbox({
            btnId: 'role-userTreeBtn',
            dlgId: 'dlg-role-user',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("用户")}',
            treeChkMode: '',
            treeChkType: 'user',
            viewer: userViewer,
            saveChkFldId: 'userId'
        });
    })
</script>
<form action="${app}/workbench/netdisk/tawWorkbenchFolders.do?method=saveFolderShareInfo"
      name="tawWorkbenchFolderShareForm" method="post">
    ${eoms:a2u('请选择接受共享的用户：')}
    <input type="button" value="${eoms:a2u('更改人员列表')}" id="role-userTreeBtn" class="btn"/>
    <div id="user-list" class="viewer-list"></div>
    <input type="hidden" id="userId" name="userId"/>
    <input type="hidden" id="shareType" name="shareType" value="selectUsers"/>
    <input type="hidden" id="folderMappingId" name="folderMappingId" value="<%=folderMappingId%>"/>
    <input type="submit" value="${eoms:a2u('共享给以上用户')}" class="btn"/>
    <input type="submit" value="${eoms:a2u('共享给所有人')}" class="btn"
           onclick="javascript:document.forms[0].shareType.value='allUsers';"/>
</form>
<iframe id='fileList' name='fileList' frameborder=0
        src='${app}/workbench/netdisk/tawWorkbenchFiles.do?method=listFiles'>
</iframe>
<%@ include file="/common/footer_eoms.jsp" %>