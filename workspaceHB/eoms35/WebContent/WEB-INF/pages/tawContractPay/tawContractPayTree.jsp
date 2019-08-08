<jsp:directive.page import="com.boco.eoms.parter.baseinfo.contract.util.*"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<fmt:bundle basename="config/applicationResource-tawcontractpay">

    <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
    <script type="text/javascript">
        var config = {
            /**************
             * Tree Configs
             **************/
            treeGetNodeUrl: "${app}/tawContractPay/tawContractPays.do?method=getNodes",
            treeRootId: '<%=TawContractConstants.TREE_ROOT_ID%>',
            treeRootText: '<fmt:message key="tawContractPay.tree.rootText"/>',
            pageFrameId: 'formPanel-page',
            onClick: {url: "${app}/tawContractPay/tawContractPays.do?method=edit", text: ""},
            ctxMenu: [
                {
                    id: 'newnode',
                    text: '<fmt:message key="tawContractPay.tree.add"/>',
                    cls: 'new-mi',
                    url: '${app}/tawContractPay/tawContractPays.do?method=add&nodeId='
                },
                {
                    id: 'edtnode',
                    text: '<fmt:message key="tawContractPay.tree.edit"/>',
                    cls: 'edit-mi',
                    url: '${app}/tawContractPay/tawContractPays.do?method=edit&nodeId='
                },
                {
                    id: 'delnode',
                    isDelete: true,
                    text: '<fmt:message key="tawContractPay.tree.delete"/>',
                    cls: 'remove-mi',
                    url: '${app}/tawContractPay/tawContractPays.do?method=remove&nodeId='
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
        <h1><fmt:message key="tawContractPay.tree.header"/></h1>
    </div>
    <div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
        <dl>
            <fmt:message key="tawContractPay.help"/>
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