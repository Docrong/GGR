<%@ include file="/common/taglibs.jsp" %>
<%@page import="java.util.List" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager" %>
<%@page import="com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<style type="text/css">
    select.menuBox {
        width: 150px;
    }

    select.menuBox2 {
        width: 300px;
    }

    .targetName {
        font-weight: bold;
    }
</style>
<script type="text/javascript" src="${app}/scripts/form/Options.js"></script>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/layout/PrivAssigner.js"></script>
<script type="text/javascript">
    var tabs;
    var config = {
        pageFrameId: 'regionPanel-page',
        actions: {
            roleTree: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=getRoleTreeNodes"
            },
            userTree: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=getUserTreeNodes"
            },
            edtNode: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=xsave&deptId="
            },
            addPrivAsg: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=xsave"
            },
            getPrivAsgd: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=getAssign"
            },
            delPrivAsg: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=xdelete"
            },
            saveCptRoomForm: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=xSaveCptRoomsForUser"
            },
            getCptRoomForm: {
                url: "${app}/priv/tawSystemPrivAssigns.do?method=xGetCptRoomsForUser"
            }
        },
        ctxMenu: [
            {
                id: 'cm-priv', text: "${eoms:a2u('分配权限')}", cls: 'edit-mi', handler: function () {
                    var node = PrivAssigner.getSelNode();
                    var nodeType = node.attributes.nodeType;
                    eoms.log(node.attributes.nodeType);

                    tabs.tabPanel.activate(0);
                    if (nodeType == "subrole") {
                        tabs.tabPanel.hideTab(1);
                        tabs.tabPanel.hideTab(2);
                        tabs.tabPanel.hideTab(3);
                        tabs.tabPanel.hideTab(4);
                    } else {
                        tabs.tabPanel.unhideTab(1);
                        tabs.tabPanel.unhideTab(2);
                        tabs.tabPanel.unhideTab(3);
                        tabs.tabPanel.unhideTab(4);
                    }
                    PrivAssigner.doEdtNode();
                }
            },
            {id: 'cm-cptroom', text: "${eoms:a2u('分配机房域')}", cls: 'edit-mi', url: '${app}/priv/cptRoomRegion.do'},
            {id: 'cm-dept', text: "${eoms:a2u('分配部门域')}", cls: 'edit-mi', url: '${app}/priv/deptRegion.do'}
        ],
        onLoadFunctions: function () {

            var tabConfig = {
                items: [{
                    id: 'tab-asg',
                    text: '${eoms:a2u("分配权限")}'
                }, {
                    id: 'tab-roleAsg',
                    text: '${eoms:a2u("所属角色权限")}'
                }, {
                    id: 'tab-deptAsg',
                    text: '${eoms:a2u("所属部门权限")}'
                }, {
                    id: 'tab-alldeptAsg',
                    text: '${eoms:a2u("上级部门权限")}'
                }, {
                    id: 'tab-allAsg',
                    text: '${eoms:a2u("已分配所有权限")}'
                }]
            };
            tabs = new eoms.TabPanel('tab', tabConfig);

        },
        deptCtxMenu: [
            {
                text: "${eoms:a2u('分配部门权限')}", cls: 'edit-mi', handler: function () {
                    var node = PrivAssigner.getSelNode();
                    var nodeType = node.attributes.nodeType;
                    eoms.log(node.attributes.nodeType);

                    tabs.tabPanel.activate(0);
                    tabs.tabPanel.hideTab(1);
                    tabs.tabPanel.hideTab(2);
                    tabs.tabPanel.hideTab(3);
                    tabs.tabPanel.hideTab(4);
                    PrivAssigner.doEdtNode();
                }
            }
        ]
    }; // end of config
    Ext.onReady(PrivAssigner.init, PrivAssigner);

    function onCptRoomFormSubmit() {
        var formData = Ext.Ajax.serializeForm('cptRoomForm');
        Ext.lib.Ajax.request(
            "post",
            config.actions.saveCptRoomForm.url,
            {
                success: function (x) {
                    var r = Ext.decode(x.responseText);
                    Ext.MessageBox.alert('提示', r.message);
                },
                failure: function () {
                    Ext.MessageBox.alert('提示', "${eoms:a2u('保存失败，请检查。')}");
                }
            },
            formData
        );
    }
</script>
<style type="text/css">
    body {
        background-image: none;
    }
</style>
<!-- header-->
<div id="headerPanel" class="app-header">
    <h1>${eoms:a2u('权限分配')}</h1>
</div>

<!-- help-->
<div id="helpPanel" class="app-panel">
    <dl>
        <dt>${eoms:a2u('功能说明')}</dt>
        <dd>${eoms:a2u('为子角色或人员分配权限。即将创建的菜单方案直接分配给角色或者用户。管理员可以对所有权限进行分配，普通用户只能根据自身权限集合进行分配，用户可以将权限分配给任何人员。')}</dd>
        <dd>${eoms:a2u('角色的权限的来源：由操作直接赋予该角色。')}</dd>
        <dd>${eoms:a2u('用户的权限的来源：由权限直接赋予该用户，或者由角色直接赋予该用户，或者由其他用户代理来的权限。')}</dd>
    </dl>
    <br/>
    <dl>
        <dt>${eoms:a2u('分配权限')}</dt>
        <dd>${eoms:a2u('目前可以给子角色或人员分配权限。')}</dd>
        <dd>${eoms:a2u('在树图中的菜单项上点击右键，并选择"分配权限"')}</dd>
        <dt>${eoms:a2u('分配一个菜单方案')}</dt>
        <dd>${eoms:a2u('在"可分配的菜单方案"下拉框中选中您要分配的菜单方案，并点击"添加"')}</dd>
        <dt>${eoms:a2u('删除已分配的菜单方案')}</dt>
        <dd>${eoms:a2u('在"已分配的菜单方案"下拉框中选中您要删除的菜单方案，并点击"删除"')}</dd>
    </dl>
</div>

<!-- role tree-->
<div id="roleTreePanel"></div>

<!-- user tree-->
<div id="userTreePanel"></div>

<!-- form -->
<div id="formPanel">
    <div id="formPanel-body" class="app-panel">
        <div id="curItemInfo"></div>
        <div id="tab" style="height:400px;">
            <div id="tab-asg" class="tabContent">
                <table width="%95">
                    <!--
		<tr>
	
			<td colspan="3">
				<span class="textHeader">${eoms:a2u("继承自所属上级的菜单方案(不可删除):")}</span><br/>
				<select id="extendAsg"></select>
				<br/><br/>
			</td>
		</tr>
		 -->
                    <tr>
                        <td class="textHeader">${eoms:a2u("可分配的菜单方案")}</td>
                        <td></td>
                        <td class="textHeader">${eoms:a2u("已分配的菜单方案")}</td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <select id="privSource" size="10" class="menuBox">
                                <c:forEach items="${tawSystemPrivMenuList}" var="list">
                                    <option value="${list.privid}">${list.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="button" value="${eoms:a2u('添加')}&gt;&gt;"
                                   onclick="javascript:PrivAssigner.addPriv();" class="button"/>
                            <br/><br/>
                            <input type="button" value="&lt;&lt;${eoms:a2u('删除')}"
                                   onclick="javascript:PrivAssigner.delPriv();" class="button"/>
                        </td>
                        <td valign="top">
                            <select id="privAsgd" size="10" class="menuBox">
                            </select>
                        </td>
                    </tr>
                </table>

            </div>
            <div id="tab-roleAsg" class="tabContent"><select id="roleAsg" size="10" class="menuBox2"></select></div>
            <div id="tab-deptAsg" class="tabContent"><select id="deptAsg" size="10" class="menuBox"></select></div>
            <div id="tab-alldeptAsg" class="tabContent"><select id="alldeptAsg" size="10" class="menuBox"></select>
            </div>
            <div id="tab-allAsg" class="tabContent"><select id="allAsg" size="10" class="menuBox"></select></div>
        </div>


    </div>
</div>

<!-- region config -->
<div id="regionPanel" class="app-panel">
    <iframe id="regionPanel-page" name="regionPanel-page" frameborder="no" style="width:100%;height:100%"
            src=""></iframe>
</div>
<%@ include file="/common/footer_eoms.jsp" %>
