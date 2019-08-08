<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.partner.baseinfo.util.AreaDeptTreeConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

    <script type="text/javascript" src="${app}/scripts/layout/AppFrameTree.js"></script>
    <script type="text/javascript">
        var config = {
            /**************
             * Tree Configs
             **************/
            treeGetNodeUrl: "${app}/partner/baseinfo/areaDeptTrees.do?method=getNodes",
            treeRootId: '<%=AreaDeptTreeConstants.TREE_ROOT_ID%>',
            treeRootText: '<fmt:message key="areaDeptTree.tree.rootText"/>',
            pageFrameId: 'formPanel-page',
//	onClick:{url:"${app}/partner/baseinfo/areaDeptTrees.do?method=edit",text:""},
            ctxMenu: [
                {
                    id: 'newnode',
                    text: '<fmt:message key="areaDeptTree.tree.add"/>',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/areaDeptTrees.do?method=add&nodeId='
                },
                {
                    id: 'NewFactory',
                    text: '<fmt:message key="areaDeptTree.tree.NewFactory"/>',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/partnerDepts.do?method=add&nodeId='
                },
                {
                    id: 'ListFactory',
                    text: '查询合作伙伴',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/partnerDepts.do?method=search&nodeId='
                },
                {
                    id: 'EditFactory',
                    text: '编辑合作伙伴',
                    cls: 'edit-mi',
                    url: '${app}/partner/baseinfo/partnerDepts.do?method=edit&nodeId='
                },
                {
                    id: 'DelFactory',
                    text: '删除合作伙伴',
                    cls: 'remove-mi',
                    url: '${app}/partner/baseinfo/partnerDepts.do?method=remove&nodeId='
                },
                {
                    id: 'NewUser',
                    text: '新增人力信息',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/partnerUsers.do?method=add&nodeId='
                },
                {
                    id: 'SearchUser',
                    text: '查询人力信息',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/partnerUsers.do?method=search&nodeId='
                },
                {
                    id: 'NewInstrument',
                    text: '新增仪器仪表',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/tawApparatuss.do?method=add&nodeId='
                },
                {
                    id: 'SearchInstrument',
                    text: '查询仪器仪表',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/tawApparatuss.do?method=search&nodeId='
                },
                {
                    id: 'NewCar',
                    text: '新增车辆管理',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/tawPartnerCars.do?method=add&nodeId='
                },
                {
                    id: 'SearchCar',
                    text: '查询车辆管理',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/tawPartnerCars.do?method=search&nodeId='
                },
                {
                    id: 'NewOil',
                    text: '新增油机',
                    cls: 'new-mi',
                    url: '${app}/partner/baseinfo/tawPartnerOils.do?method=add&nodeId='
                },
                {
                    id: 'SearchOil',
                    text: '查询油机',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/tawPartnerOils.do?method=search&nodeId='
                },
                {
                    id: 'edtnode',
                    text: '<fmt:message key="areaDeptTree.tree.edit"/>',
                    cls: 'edit-mi',
                    url: '${app}/partner/baseinfo/areaDeptTrees.do?method=edit&nodeId='
                },
                {
                    id: 'delnode',
                    isDelete: true,
                    text: '<fmt:message key="areaDeptTree.tree.delete"/>',
                    cls: 'remove-mi',
                    url: '${app}/partner/baseinfo/areaDeptTrees.do?method=remove&nodeId='
                },
                {
                    id: 'SearchInProvince',
                    text: '查询',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/partnerUsers.do?method=search&in=province&nodeId='
                },
                {
                    id: 'SearchInArea',
                    text: '查询',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/partnerUsers.do?method=search&in=area&nodeId='
                },
                {
                    id: 'SearchInFactory',
                    text: '查询',
                    cls: 'list-mi',
                    url: '${app}/partner/baseinfo/partnerUsers.do?method=search&in=factory&nodeId='
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
        <h1><fmt:message key="areaDeptTree.tree.header"/></h1>
    </div>
    <div id="helpPanel" style="padding:10px;" class="x-layout-inactive-content">
        <dl>
            <fmt:message key="areaDeptTree.help"/>
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