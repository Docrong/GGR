<jsp:directive.page import="com.boco.eoms.operuser.util.OperuserConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<fmt:bundle basename="config/applicationResource-operuser">
    <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>

    <script type="text/javascript">
        var config = {
            /**************
             * Tree Configs
             **************/
            treeGetNodeUrl: "${app}/operuser/operusers.do?method=getNodes",
            treeRootId: '-1',
            //'<%=OperuserConstants.TREE_ROOT_ID%>',
            treeRootText: '<fmt:message key="operuser.tree.rootText"/>',
            pageFrameId: 'formPanel-page',
//	onClick:{url:"",text:""},//${app}/operuser/operusers.do?method=edit
            ctxMenu: [
                {
                    id: 'newnode',
                    text: '<fmt:message key="operuser.tree.add"/>',
                    cls: 'new-mi',
                    url: '${app}/operuser/operusers.do?method=add&id='
                },
                {
                    id: 'edtnode',
                    text: '<fmt:message key="operuser.tree.edit"/>',
                    cls: 'edit-mi',
                    url: '${app}/operuser/operusers.do?method=edit&id='
                },
                {
                    id: 'delnode',
                    isDelete: true,
                    text: '<fmt:message key="operuser.tree.delete"/>',
                    cls: 'remove-mi',
                    url: '${app}/operuser/operusers.do?method=remove&id='
                }
            ],//end of ctxMenu
            /************************
             * Custom onLoad Functions
             *************************/
            onLoadFunctions: function () {
            }
        }; // end of config
        Ext.onReady(AppFrameTree.init, AppFrameTree);
    </script>
    <div id="headerPanel" class="x-layout-inactive-content">
        <h1><fmt:message key="operuser.tree.header"/></h1>
    </div>
    <div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
        <dl>
            <fmt:message key="operuser.help"/>
        </dl>
    </div>
    <div id="treePanel" class="x-layout-inactive-content">
        <div id="treePanel-tb" class="tb"></div>
        <div id="treePanel-body"></div>
    </div>
    <div id="formPanel" class="x-layout-inactive-content">
        <iframe id="formPanel-page" name="formPanel-page" frameborder="no" style="width:100%;height:100%"
                src=""></iframe>
    </div>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>