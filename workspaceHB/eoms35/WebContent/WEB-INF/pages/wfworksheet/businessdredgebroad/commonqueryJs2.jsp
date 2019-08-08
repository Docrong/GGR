<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    Ext.onReady(function () {
        //地域
        var showAreaId = document.getElementById("showArea");
        if (showAreaId != null) {
            var areatreeAction = '${app}/xtree.do?method=areaTree';
            deptetree = new xbox({
                btnId: 'showArea',
                treeDataUrl: areatreeAction, treeRootId: '-1', treeRootText: '地市', treeChkMode: '', treeChkType: 'area',
                showChkFldId: 'showArea', saveChkFldId: 'toAreaId'
            });
        }

        var treeId = "sendrole";
        var flowId = 1;
        var RoleTreeAction = '${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromFlow';
        var DptTreeAction = '${app}/xtree.do?method=dept';
        var UserTreeAction = '${app}/xtree.do?method=userFromDept';

        Viewer = new Ext.JsonView("viewer",
            '<div class="viewlistitem-{nodeType}">{name};</div>',
            {emptyText: '<div>没有选择项目</div>'}
        );

        Viewer2 = new Ext.JsonView("viewer-deal",
            '<div class="viewlistitem-{nodeType}">{name};</div>',
            {emptyText: '<div>没有选择项目</div>'}
        );
        //派单角色
        var sendcfg = {
            btnId: 'showSendRole',
            treeDataUrl: RoleTreeAction,
            treeRootId: flowId,
            treeRootText: '角色',
            treeChkMode: '',
            treeChkType: 'subrole',
            saveChkFldId: 'toSendRoleId',
            viewer: Viewer
        };
        sendRoletree = new xbox(sendcfg);

        var dealcfg = {
            btnId: 'showDealRole',
            treeDataUrl: RoleTreeAction,
            treeRootId: flowId,
            treeRootText: '角色',
            treeChkMode: '',
            treeChkType: 'subrole',
            saveChkFldId: 'toDealRoleId',
            viewer: Viewer2
        };
        dealRoletree = new xbox(dealcfg);

        Ext.get("showSendRole").setDisplayed(false);
        Ext.get("showSendDpt").setDisplayed(false);
        Ext.get("showSendUser").setDisplayed(false);
        Ext.get("showDealRole").setDisplayed(false);
        Ext.get("showDealDpt").setDisplayed(false);
        Ext.get("showDealUser").setDisplayed(false);

        function sendroletree() {
            sendRoletree.defaultTree.root.id = flowId;
            sendRoletree.defaultTree.root.setText("角色");
            sendRoletree.defaultPanel.setTitle("角色");
            sendRoletree.defaultTree.checktype = "subrole";
            sendRoletree.resetRoot(RoleTreeAction);
            sendRoletree.reset();
            Ext.getDom("toSendUserId").value = "";
            Ext.getDom("toSendDptId").value = "";
            Ext.get("showSendRole").setDisplayed(true);
            Ext.get("showSendDpt").setDisplayed(false);
            Ext.get("showSendUser").setDisplayed(false);
        };

        function senddepttree() {
            sendRoletree.defaultTree.root.id = "-1";
            sendRoletree.defaultTree.root.setText("部门");
            sendRoletree.defaultPanel.setTitle("部门");
            sendRoletree.defaultTree.checktype = "dept";
            sendRoletree.resetRoot(DptTreeAction);
            sendRoletree.reset();
            Ext.getDom("toSendUserId").value = "";
            Ext.getDom("toSendRoleId").value = "";
            Ext.get("showSendRole").setDisplayed(false);
            Ext.get("showSendDpt").setDisplayed(true);
            Ext.get("showSendUser").setDisplayed(false);
        };

        function sendusertree() {
            sendRoletree.defaultTree.root.id = flowId;
            sendRoletree.defaultTree.root.setText("人员");
            sendRoletree.defaultPanel.setTitle("人员");
            sendRoletree.defaultTree.checktype = "user";
            sendRoletree.resetRoot(UserTreeAction);
            sendRoletree.reset();
            Ext.getDom("toSendDptId").value = "";
            Ext.getDom("toSendRoleId").value = "";
            Ext.get("showSendRole").setDisplayed(false);
            Ext.get("showSendDpt").setDisplayed(false);
            Ext.get("showSendUser").setDisplayed(true);
        };

        function dealroletree() {
            dealRoletree.defaultTree.root.id = flowId;
            dealRoletree.defaultTree.root.setText("角色");
            dealRoletree.defaultPanel.setTitle("角色");
            dealRoletree.defaultTree.checktype = "subrole";
            dealRoletree.resetRoot(RoleTreeAction);
            dealRoletree.reset();
            Ext.getDom("toDealDptId").value = "";
            Ext.getDom("toDealUserId").value = "";
            Ext.get("showDealRole").setDisplayed(true);
            Ext.get("showDealDpt").setDisplayed(false);
            Ext.get("showDealUser").setDisplayed(false);
        };

        function dealdepttree() {
            dealRoletree.defaultTree.root.id = "-1";
            dealRoletree.defaultTree.root.setText("部门");
            dealRoletree.defaultPanel.setTitle("部门");
            dealRoletree.defaultTree.checktype = "dept";
            dealRoletree.resetRoot(DptTreeAction);
            dealRoletree.reset();
            Ext.getDom("toDealRoleId").value = "";
            Ext.getDom("toDealUserId").value = "";
            Ext.get("showDealRole").setDisplayed(false);
            Ext.get("showDealDpt").setDisplayed(true);
            Ext.get("showDealUser").setDisplayed(false);
        };

        function dealusertree() {
            dealRoletree.defaultTree.root.id = flowId;
            dealRoletree.defaultTree.root.setText("人员");
            dealRoletree.defaultPanel.setTitle("人员");
            dealRoletree.defaultTree.checktype = "user";
            dealRoletree.resetRoot(UserTreeAction);
            dealRoletree.reset();
            Ext.getDom("toDealDptId").value = "";
            Ext.getDom("toDealRoleId").value = "";
            Ext.get("showDealRole").setDisplayed(false);
            Ext.get("showDealDpt").setDisplayed(false);
            Ext.get("showDealUser").setDisplayed(true);
        };
        Ext.get("sendsel").on("change", function (event, el) {
            switch (el.value) {
                case "role":
                    sendroletree();
                    break;
                case "dept":
                    senddepttree();
                    break;
                case "user":
                    sendusertree();
                    break;
                default:
                    sendRoletree.reset();
                    Ext.getDom("toSendRoleId").value = "";
                    Ext.getDom("toSendDptId").value = "";
                    Ext.getDom("toSendUserId").value = "";
                    Ext.get("showSendRole").setDisplayed(false);
                    Ext.get("showSendDpt").setDisplayed(false);
                    Ext.get("showSendUser").setDisplayed(false);

            }

        });

        Ext.get("dealsel").on("change", function (event, el) {
            switch (el.value) {
                case "role":
                    dealroletree();
                    break;
                case "dept":
                    dealdepttree();
                    break;
                case "user":
                    dealusertree();
                    break;
                default:
                    dealRoletree.reset();
                    Ext.getDom("toDealDptId").value = "";
                    Ext.getDom("toDealUserId").value = "";
                    Ext.getDom("toDealRoleId").value = "";
                    Ext.get("showDealRole").setDisplayed(false);
                    Ext.get("showDealDpt").setDisplayed(false);
                    Ext.get("showDealUser").setDisplayed(false);
            }

        });
        Ext.get("showSendRole").on("click", function () {
            sendRoletree.show(this, null, 'toSendRoleId');
        });
        //派单部门
        Ext.get("showSendDpt").on("click", function () {
            sendRoletree.show(this, null, 'toSendDptId');
        });
        //派单人员
        Ext.get("showSendUser").on("click", function () {
            sendRoletree.show(this, null, 'toSendUserId');
        });
        //处理角色
        Ext.get("showDealRole").on("click", function () {
            dealRoletree.show(this, null, 'toDealRoleId');
        });
        //处理部门
        Ext.get("showDealDpt").on("click", function () {
            dealRoletree.show(this, null, 'toDealDptId');
        });
        //处理人员
        Ext.get("showDealUser").on("click", function () {
            dealRoletree.show(this, null, 'toDealUserId');
        });

    });

    function selectStep(statusChoice) {
        var stepId = document.getElementById("stepId");
        if (statusChoice.value == "0")
            stepId.disabled = false;
        else {
            stepId.selectedIndex = 0;
            stepId.disabled = true;

        }
    }
</script>

<%@ include file="/common/footer_eoms.jsp" %>
