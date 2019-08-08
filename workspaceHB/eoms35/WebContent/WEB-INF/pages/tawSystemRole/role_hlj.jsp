<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<style type="text/css">
    select.select-class {
        width: 180px;
    }

    .preHeader {
        font-size: 15px;
    }

    .preHelp {
        background: #EFFAFF;
        border: 1px solid #8EACCF;
        margin: 5px 0pt;
        padding: 5px;
        vertical-align: top;
        line-height: 1.6em;
    }
</style>
<!-- loading -->
<div id="loading">
    <div class="loading-indicator">Loading...</div>
</div>
<script type="text/javascript" src="${app}/scripts/form/combobox.js"></script>
<script type="text/javascript">
    /* Static Defines
    */
    var treeRootId = '1';
    var treeRootText = '${eoms:a2u("所有角色")}';
    var treeAction = '${app}/role/tawSystemRoles.do?method=getNodes';
    var treeAction_new = '${app}/role/tawSystemRoles.do?method=xsave';
    var treeAction_get = '${app}/role/tawSystemRoles.do?method=xget&id=';
    var treeAction_edt = '${app}/role/tawSystemRoles.do?method=xedit&id=';
    var treeAction_del = '${app}/role/tawSystemRoles.do?method=xdel&id=';

    var treeAction_getUsers = '${app}/role/tawSystemSubRoles.do?method=xGetUsers&id=';
    var treeAction_saveUsers = '${app}/role/tawSystemSubRoles.do?method=xUpdateUsers';

    var gridAction = '${app}/role/tawSystemRoles.do?method=xGetSubRoleList';
    var gridAction_getUsers = '${app}/role/tawSystemSubRoles.do?method=xGetUsers&id=';
    var gridAction_saveUsers = '${app}/role/tawSystemSubRoles.do?method=xUpdateUsers';
    var gridAction_del = '${app}/role/tawSystemSubRoles.do?method=xDelete&subRoleIds=';

    var crtSubRolesAction = '${app}/role/tawSystemSubRoles.do?method=xsave';
    var roleType_role = 2;
    var roleType_subRole = 1;
    /* Create Form
    */
    var form, fs;
    var pageSize = 15;

    var gridColumns = ['id', 'subRoleName', 'deptId', 'deptName'];
    var columnModel = new Ext.grid.ColumnModel([
        {id: 'name', header: "${eoms:a2u('子角色名称(双击查看人员)')}", dataIndex: 'subRoleName'},
        {header: "${eoms:a2u('所属部门')}", dataIndex: 'deptName'}
    ]);

    var subRolesPrestore, subRoleGrid;

    Ext.onReady(function () {

        // form for create a role or modify a role
        form = new Ext.form.Form({
            labelWidth: 50, labelAlign: 'top', buttonAlign: 'left'
        });

        fs = form.fieldset(
            {id: "legend", legend: 'New Role', style: 'margin:5px'},
            new Ext.form.TextField({
                fieldLabel: '${eoms:a2u("角色名称")}',
                id: 'roleName',
                name: 'roleName',
                allowBlank: false,
                width: 150
            }),
            new Ext.form.SimpleSelect({
                fieldLabel: '${eoms:a2u("角色类型")}',
                hiddenName: 'roleTypeId',
                values: [
                    ['${eoms:a2u("流程角色")}', '1'],
                    ['${eoms:a2u("系统角色")}', '2']
                ],
                allowBlank: false,
                value: '1',
                width: 150
            }),
            new Ext.form.SimpleSelect({
                fieldLabel: '${eoms:a2u("所属流程")}',
                hiddenName: 'workflowFlag',
                values: [
                    ['${eoms:a2u("任务工单流程")}', '1']
                    //['${eoms:a2u("应急与事件管理流程")}', '1'],
                    //['${eoms:a2u("网络优化流程")}', '2'],
                    //['${eoms:a2u("供应商管理流程")}', '3'],
                    //['${eoms:a2u("新业务开通流程")}', '4'],
                    //['${eoms:a2u("性能管理流程")}', '5'],
                    //['${eoms:a2u("安全管理流程")}', '6'],
                    //['${eoms:a2u("故障管理流程")}', '7'],
                    //['${eoms:a2u("投诉管理流程")}', '8'],
                    //['${eoms:a2u("网络配置流程")}', '9'],
                    //['${eoms:a2u("变更管理流程")}', '10'],
                    //['${eoms:a2u("作业计划流程")}', '11'],
                    //['${eoms:a2u("技术支援流程")}', '12']
                ],
                allowBlank: false,
                value: '1',
                width: 150
            }),
            /* Hidden Fields
            */
            new Ext.form.HiddenField({name: 'leaf', id: 'leaf', value: '1'}),
            new Ext.form.HiddenField({name: 'deleted', id: 'deleted', value: '0'}),
            new Ext.form.HiddenField({name: 'roleId', id: 'roleId'}),
            new Ext.form.HiddenField({name: 'parentId', id: 'parentId'})
        );  // end of fs


        //dept tree
        var deptTreeAction = '${app}/xtree.do?method=dept';
        deptTree = new xbox({
            btnId: 'depttree',
            dlgId: 'dlg-dept',
            treeDataUrl: deptTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("部门")}',
            treeChkMode: '',
            treeChkType: 'dept',
            showChkFldId: 'deptname',
            saveChkFldId: 'deptid'
        });
        //user tree
        var userTreeAction = '${app}/xtree.do?method=userFromDept';

        //Role User Viewer
        roleUserViewer = new Ext.JsonView("role-user-list",
            '<div id="role-user-{userid}" class="viewlistitem-user">{username}</div>',
            {
                multiSelect: true,
                emptyText: '<div>${eoms:a2u("此角色下没有分配用户。")}</div>'
            }
        );

        //Role User Tree
        roleUserTree = new xbox({
            btnId: 'role-userTreeBtn',
            dlgId: 'dlg-role-user',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("用户")}',
            treeChkMode: '',
            treeChkType: 'user',
            callback: function (json, ids) {
                Ext.lib.Ajax.request(
                    "post",
                    gridAction_saveUsers,
                    {
                        success: function () {
                            roleUserViewer.load(gridAction_getUsers + selectNode.attributes.id);
                        },
                        failure: function () {
                        }
                    },
                    "id=" + selectNode.attributes.id + "&roleType=" + roleType_role + "&userId=" + ids
                );
            }
        });

        //SubRole User Tree
        subRoleUserTree = new xbox({
            btnId: 'userTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("用户")}',
            treeChkMode: '',
            treeChkType: 'user',
            callback: function (json, ids) {
                Ext.lib.Ajax.request(
                    "post",
                    gridAction_saveUsers,
                    {
                        success: function () {
                            userViewer.load(gridAction_getUsers + Ext.get('subRoleId').dom.value);
                        },
                        failure: function () {
                        }
                    },
                    "id=" + Ext.get('subRoleId').dom.value + "&userId=" + ids
                );
            }
        });

        subRolesPrestore = new Ext.data.JsonStore({
            fields: ['dept', 'class1', 'class2', 'class3', 'deptName', 'subRoleName']
        });
        var subRolesPreCM = new Ext.grid.ColumnModel([
            {header: "${eoms:a2u('所属部门')}", dataIndex: 'deptName', sortable: true},
            {id: 'subrolename', header: "${eoms:a2u('子角色名称')}", dataIndex: 'subRoleName', sortable: true}
        ]);
        subRoleGrid = new Ext.grid.Grid('subpregrid', {
            ds: subRolesPrestore,
            cm: subRolesPreCM,
            autoExpandColumn: 'subrolename'
        });
        subRoleGrid.on('keypress', function (e) {
            if (e.getKey() == e.DELETE) {
                var rows = subRoleGrid.getSelectionModel().getSelections();
                Ext.each(rows, function (r) {
                    subRoleGrid.dataSource.remove(r);
                });
            }

        });

        subRoleGrid.render();

    }); // end of Ext.onReady

    /* init hidden fields for menu actions
     *
     */

    function afterSelect(a) {
        switch (a) {
            case "newSubNode":
                form.findField("parentId").setValue(selectNode.attributes.id);
                break;
            case "subRoleList":
                Ext.get('subRoleUsers').hide();
                break;
            case "newSubRole":
                resetDistincter();
                break;
            default:
                break;
        }
    }

</script>
<script type="text/javascript" src="${app}/scripts/layout/tree-grid.js"></script>
<script type="text/javascript">
    /*****************************
     * Create Temp SubRoles Grid
     *****************************/
    var totalRecords = [];
    var tempRecords = [];
    var dupeRecords = [];

    function pregrid() {
        tempRecords.length = 0;
        dupeRecords.length = 0;
        var roleName = Ext.get('subRolesForm-roleName').dom.value;
        var dept_ids = Ext.get('deptid').dom.value.split(',');
        var dept_names = Ext.get('deptname').dom.value.split(',');
        var _class1 = [], _class2 = [], _class3 = [];
        if (Ext.get('class1')) _class1 = options2arr(Ext.get('class1').dom.options);
        if (Ext.get('class2')) _class2 = options2arr(Ext.get('class2').dom.options);
        if (Ext.get('class3')) _class3 = options2arr(Ext.get('class3').dom.options);

        if (Ext.get('deptid').dom.value == "") {
            Ext.MessageBox.alert('${eoms:a2u("提示")}:', '${eoms:a2u("请选择部门")}');
            return;
        }

        for (var c0 = 0; c0 < dept_ids.length; c0++) {
            if (_class1.length > 0) {
                for (var c1 = 0; c1 < _class1.length; c1++) {
                    if (_class2.length > 0) {
                        for (var c2 = 0; c2 < _class2.length; c2++) {
                            if (_class3.length > 0) {
                                for (var c3 = 0; c3 < _class3.length; c3++) {
                                    var subRoleName = roleName + "(" + dept_names[c0] + "-" + _class1[c1].text + "-" + _class2[c2].text + "-" + _class3[c3].text + ")";
                                    addSubRoleRecord(subRoleName, dept_ids[c0], dept_names[c0], _class1[c1].value, _class2[c2].value, _class3[c3].value);
                                }
                            } else {
                                var subRoleName = roleName + "(" + dept_names[c0] + "-" + _class1[c1].text + "-" + _class2[c2].text + ")";
                                addSubRoleRecord(subRoleName, dept_ids[c0], dept_names[c0], _class1[c1].value, _class2[c2].value);
                            }
                        }
                    } else {
                        var subRoleName = roleName + "(" + dept_names[c0] + "-" + _class1[c1].text + ")";
                        addSubRoleRecord(subRoleName, dept_ids[c0], dept_names[c0], _class1[c1].value);
                    }
                }
            } else {
                var subRoleName = roleName + "(" + dept_names[c0] + ")";
                addSubRoleRecord(subRoleName, dept_ids[c0], dept_names[c0]);
            }
        }


        if (tempRecords.length > 0) {
            //update grid, true to append data
            subRolesPrestore.loadData(Ext.util.JSON.decode("[" + tempRecords.toString() + "]"), true);
            totalRecords = totalRecords.concat(tempRecords);
        }
        if (dupeRecords.length > 0) {
            Ext.MessageBox.alert('提示', dupeRecords.length + '${eoms:a2u("条重复的记录已被覆盖:")}' + dupeRecords.toString());
        }

        Ext.get("submitBtn").setDisplayed('block');

        //Ext.get("delBtn").setDisplayed('block');

        function options2arr(options) {
            try {
                var arr = [];
                for (var i = 0; i < options.length; i++) {
                    if (options[i].selected && options[i].value != "") arr.push({
                        "value": options[i].value,
                        "text": options[i].text
                    });
                }
                ;
            } catch (e) {
                return []
            }
            ;

            return arr;
        }
    }

    /*****************************
     * Add one SubRole Record
     *****************************/
    function addSubRoleRecord(subRoleName, deptId, deptName, class1, class2, class3) {
        var _temp = '{subRoleName:\"' + subRoleName + '\",dept:\"' + deptId + '\",deptName:\"' + deptName + '\"';
        if (class1) _temp += ',class1:\"' + class1 + '\"';
        if (class2) _temp += ',class2:\"' + class2 + '\"';
        if (class3) _temp += ',class3:\"' + class3 + '\"';
        _temp += '}';

        if (totalRecords.indexOf(_temp) < 0) {
            tempRecords.push(_temp);
        } else {
            dupeRecords.push("<br/>" + subRoleName);
        }
    }

    /*****************************
     * Submit SubRoles
     *****************************/
    function crtSubRoles() {
        if (subRolesPrestore.data.items.length == 0) {
            Ext.MessageBox.alert('提示', "${eoms:a2u('请先生成你要创建的子角色预览，再提交。')}");
            return;
        } else {
            var json = subRolesPrestore.getJSON();
            Ext.get("subRoles").dom.value = json;
        }

        var data = Ext.lib.Ajax.serializeForm("subRolesForm");
        layout.el.mask('${eoms:a2u("保存数据中，请稍候。")}', 'x-mask-loading');
        var unmask = layout.el.unmask.createDelegate(layout.el);
        Ext.lib.Ajax.request(
            "post",
            "tawSystemSubRoles.do?method=jsonSubRoles",
            {
                success: function () {
                    unmask();
                    Ext.MessageBox.alert('提示', "${eoms:a2u('子角色提交成功')}");
                    openPanel('gridPanel', selectNode.text + '${eoms:a2u("的子角色列表")}');
                },
                failure: function () {
                    unmask();
                    Ext.MessageBox.alert('提示', "${eoms:a2u('提交失败')}")
                }
            },
            data
        );
    }

    /***********************************
     * Delete SubRoles from SubRole List Panel
     ***********************************/
    function delSubRole() {
        Ext.MessageBox.confirm('${eoms:a2u("确认:")}', '${eoms:a2u("您确定删除这个项目吗？")}', function (btn) {
            if (btn == "yes") {
                var tempArr = [];
                var rows = grid.getSelectionModel().getSelections();
                Ext.each(rows, function (r) {
                    tempArr.push(r.id);
                });
                Ext.lib.Ajax.request(
                    "get",
                    gridAction_del + tempArr.toString(),
                    {
                        success: function () {
                            gridds.reload();
                            Ext.MessageBox.alert('提示', "${eoms:a2u('删除子角色成功。')}");
                        },
                        failure: function () {
                            alert("fail")
                        }
                    }
                );
            }
        });
    }

    var maxDisterNum = 2;
    var curDister = 1;

    function doselect() {
        if ($('selDistincter').value == "") return;
        for (var i = 1; i <= maxDisterNum; i++) $('distinction' + i).innerHTML = "";
        curDister = 1;
        var tstr = $('selDistincter').value.split(' ');
        for (var i = 0; i < tstr.length; i++) {
            insertDistincter(tstr[i]);
        }
        //Options.del('selDistincter');
    }

    function insertDistincter(id) {
        if (curDister > maxDisterNum) return;
        var t = new Ext.Template.from(id);
        t.append('distinction' + curDister, {id: 'class' + curDister, sub: 'class' + (curDister + 1)});
        curDister++;
    }

    function resetDistincter() {
        for (var i = 1; i <= maxDisterNum; i++) $('distinction' + i).innerHTML = "";
        curDister = 1;
        $('selDistincter').length = 0;
        Options.add('selDistincter', "${eoms:a2u('请选择')}", "");
        Options.add('selDistincter', "${eoms:a2u('新业务开通流程')}", "t1 t3");
        Options.add('selDistincter', "${eoms:a2u('供应商管理流程')}", "t2 t3");
        Ext.get("subRolesForm").dom.reset();
        Ext.get("subRolesForm-roleId").dom.value = selectNode.attributes.id;
        Ext.get("subRolesForm-roleName").dom.value = selectNode.attributes.text;
        Ext.get('deptid').dom.value = "";
        Ext.get('deptname').dom.value = "";
        $('selDistincter').disabled = "";
        $('addDistincter').disabled = "";
        subRolesPrestore.removeAll();
        totalRecords.length = 0;
        Ext.get("submitBtn").setDisplayed('none');
        //Ext.get("delBtn").setDisplayed('none');
    }

    function defaultDistincter(obj) {
        if (obj.checked) {
            $('selDistincter').disabled = "true";
            $('addDistincter').disabled = "true";
            insertDistincter('t2');
            insertDistincter('t3');
            $('distinction3').innerHTML = "";
        } else {
            $('selDistincter').disabled = "";
            $('addDistincter').disabled = "";
        }
    }

    function delPreRoles() {
        var rows = subRoleGrid.getSelectionModel().getSelections();
        Ext.each(rows, function (r) {
            subRoleGrid.dataSource.remove(r);
        });
    }
</script>

<div id="headerPanel" class="x-layout-inactive-content">
    <img src="${app}/styles/default/images/header-role.gif">
</div>

<div id="rolePanel" class="x-layout-inactive-content panel">
    <div id="rolePanel-form"></div>
    <div id="rolePanel-users" class="viewer-box">
        <div id="rolePanel-users-title" class="viewlistitem-role"></div>
        ${eoms:a2u('的人员列表:')}
        <div id="role-user-list" class="viewer-list"></div>
        <input type="button" value="${eoms:a2u('更改人员列表')}" id="role-userTreeBtn" class="btn"/>
    </div>
</div>

<div id="treePanel">
    <div id="treePanel-tb" class="tb"></div>
    <div id="treePanel-body"></div>
</div>

<div id="helpPanel" class="panel">
    <dl>
        <dt>${eoms:a2u('添加一个下级角色')}</dt>
        <dd>${eoms:a2u('在树图中的角色上点击右键，并选择"新建子节点"')}</dd>
        <dt>${eoms:a2u('修改一个角色的信息')}</dt>
        <dd>${eoms:a2u('在树图中的角色上点击右键，并选择"修改"')}</dd>
        <dt>${eoms:a2u('删除角色')}</dt>
        <dd>${eoms:a2u('在树图中的角色上点击右键，并选择"删除"')}</dd>
        <dt>${eoms:a2u('查看角色的子角色')}</dt>
        <dd>${eoms:a2u('在树图中的角色上点击右键，并选择"子角色列表"')}</dd>
        <dt>${eoms:a2u('批量添加子角色')}</dt>
        <dd>${eoms:a2u('在树图中的角色上点击右键，并选择"添加子角色"')}</dd>
        <dt>${eoms:a2u('给角色添加人员')}</dt>
        <dd>${eoms:a2u('如果是大角色，在树图中的角色上点击右键，并选择"修改"，如果是子角色，打开子角色列表，双击你要添加人员的子角色')}</dd>
    </dl>
</div>

<div id="gridPanel" class="x-layout-inactive-content panel">
    <div id="subRoleList" class="gridPanel-grid"></div>
    <input type="button" value="${eoms:a2u('删除选中的子角色')}" id="delSubRoleBtn"
           class="btn" onclick="javascript:delSubRole();">

    <div id="subRoleUsers" class="viewer-box">
        <input type="hidden" id="subRoleId" name="subRoleId">
        <div id="user-subrolename" class="viewlistitem-role"></div>
        ${eoms:a2u('的人员列表:')}
        <div id="user-list" class="viewer-list"></div>
        <input type="button" value="${eoms:a2u('更改人员列表')}" id="userTreeBtn" class="btn">
    </div>
</div>

<div id="crtSubRolesPanel" class="x-layout-inactive-content panel">
    <form action="/role/tawSystemSubRoles.do?method=jsonSubRoles" id="subRolesForm" name="subRolesForm">
        <input type="hidden" name="roleId" id="subRolesForm-roleId"/>
        <input type="hidden" name="roleName" id="subRolesForm-roleName"/>
        <input type="hidden" name="subRoles" id="subRoles"/>
        <div class="preHelp">
            <div class="preHeader">${eoms:a2u('1. 请选择流程:')}</div>
            <select class="select-class" id="selDistincter" onchange="javascript:doselect();">
                <option value="">${eoms:a2u('请选择')}</option>
                <option value="t1 t3">${eoms:a2u('新业务开通流程')}</option>
                <option value="t2 t3">${eoms:a2u('供应商管理流程')}</option>
            </select>

            <input style="display:none" id="addDistincter" type="button" value="${eoms:a2u('添加')}"
                   onclick="javascript:doselect();"/>
            <input style="display:none" type="button" value="${eoms:a2u('重置')}"
                   onclick="javascript:resetDistincter();"/>
            <br/>
            <input style="display:none" type="checkbox" value="" onclick="javascript:defaultDistincter(this)"/>
            <!--${eoms:a2u('使用系统默认的区分度')}-->

        </div>
        <table width="100%" cellspacing="3" border="1">
            <tr>
                <td class="preHelp">
                    <div class="preHeader">${eoms:a2u('2. 选择区分度:')}</div>
                    <div><label>${eoms:a2u('区分度 1')}</label>
                        <div id="distinction0">
                            <label>${eoms:a2u('选择部门')}:</label>
                            <table width="100%" cellspacing="3" border="1">
                                <tr>
                                    <td width="180">
                                        <textarea class="ta" readonly="readonly" style="height: 60px; width: 180px;"
                                                  name="deptname" id="deptname"></textarea>
                                        <input type="hidden" name="deptid" id="deptid"/>
                                    </td>
                                    <td valign="top" align="left">
                                        <input type="button" name="depttree" id="depttree" value="${eoms:a2u('部门树')}"
                                               class="btn"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div style="margin-bottom:10px"><label>${eoms:a2u('区分度 2')}</label>
                        <div id="distinction1"></div>
                    </div>
                    <div style="margin-bottom:10px"><label>${eoms:a2u('区分度 3')}</label>
                        <div id="distinction2"></div>
                    </div>
                    <!--<div style="margin-bottom:10px"><label>${eoms:a2u('区分度 4')}</label><div id="distinction3"></div></div>-->

                    <div id="templates" style="display:none">

                        <div id="t1"><label>${eoms:a2u('选择专业')}:</label><br/>
                            <eoms:comboBox name="class1m" id="{id}" initDicId="10103" sub="{sub}"
                                           styleClass="select-class"/>
                        </div>

                        <div id="t2"><label>${eoms:a2u('选择专业')}:</label><br/>
                            <eoms:comboBox name="class1" id="{id}" initDicId="10110" sub="{sub}"
                                           styleClass="select-class"/>
                        </div>

                        <div id="t3"><label>${eoms:a2u('选择二级专业')}:</label><br/>
                            <select id="{id}" multiple="multiple" size="8" class="select-class"></select>
                        </div>

                        <div id="t4"><label>${eoms:a2u('选择级别')}:</label><br/>
                            <eoms:comboBox name="jibie" id="{id}" initDicId="10102" multiple="multiple" size="4"
                                           styleClass="select-class"/>
                        </div>

                    </div>


                </td>
                <td class="preHelp">
                    <div class="preHeader">${eoms:a2u('3. 生成子角色:')}</div>
                    <input type="button" value="${eoms:a2u('生成预览')}" onclick="javascript:pregrid();" class="btn"/>
                    <div id="subpregrid"></div>
                    <input type="button" style="display:none" value="${eoms:a2u('提交')}" id="submitBtn"
                           onclick="javascript:crtSubRoles();" class="btn"/>
                    <!--
			<input type="button" style="display:none" value="${eoms:a2u('删除选中的记录')}" id="delBtn" onclick="javascript:delPreRoles();" class="btn"/>
			-->
                </td>
            </tr>
        </table>
    </form>
</div>
<%@ include file="/common/footer_eoms.jsp" %>
