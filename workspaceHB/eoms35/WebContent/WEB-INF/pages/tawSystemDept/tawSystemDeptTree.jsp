<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext_debug.jsp" %>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree_search.js"></script>
<script type="text/javascript">

    //覆盖"修改"动作
    AppSimpleTree.doEdtNode = function (nodeData) {
        toggleDWFields(nodeData.isPartners == "1" ? true : false); //切换代维部门相关表单域
        this.openPanel('formPanel', '${eoms:a2u("修改")}' + nodeData.text + '${eoms:a2u("的信息")}');
        this.form.url = config.actions.edtNode.url;
        this.sbmBtn.setText(config.actions.edtNode.btnText);
        try {
            config.actions.edtNode.init();
        } catch (e) {
        }
        ;
        this.formData.load({params: {id: nodeData.id}});
    };

    var config = {
        hideSearchPanel: false,

        /**************
         * Tree Configs
         **************/
        treeGetNodeUrl: "${app}/dept/tawSystemDepts.do?method=getNodes",
        treeRootId: '-1',
        treeRootText: "${eoms:a2u('所有部门')}",
        ctxMenu: {
            newNode: {text: "${eoms:a2u('新建下级部门')}"},
            delNode: {text: "${eoms:a2u('删除这个部门')}"}
        },//end of ctxMenu

        /**************
         * Form Configs
         **************/
        actions: {
            newNode: {
                btnText: "${eoms:a2u('保存')}",
                url: "${app}/dept/tawSystemDepts.do?method=xsave",
                init: function () {
                    AppSimpleTree.setField("parentDeptid", "id");
                    AppSimpleTree.setField("parentDeptName", "text");
                    toggleDWFields(false);
                }
            },
            getNode: {
                url: "${app}/dept/tawSystemDepts.do?method=xget"
            },
            edtNode: {
                btnText: "${eoms:a2u('保存修改')}",
                url: "${app}/dept/tawSystemDepts.do?method=xedit",
                success: function () {
                    AppSimpleTree.refreshTree();
                }
            },
            delNode: {
                url: "${app}/dept/tawSystemDepts.do?method=xdelete",
                customData: function (selNode) {
                    return "id=" + selNode.id + "&parentDeptid=" + selNode.parentDeptid;
                }
            }
        },
        fieldOptions: {
            width: 150
        },
        fields: [
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptName'/>",
                name: 'deptName',
                allowBlank: false
            }),
            new Ext.form.TextField({
                fieldLabel: "${eoms:a2u('父部门')}",
                name: 'parentDeptName',
                id: 'parentDeptName',
                readOnly: 'true'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptmanager'/>",
                name: 'deptmanager', id: 'deptmanager',
                readOnly: 'true'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.tmporaryManager'/>",
                name: 'tmporaryManager', id: 'tmporaryManager',
                readOnly: 'true'
            }),
            new Ext.form.DateField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.tmporarybegintime'/>",
                name: 'tmporarybegintime',
                format: 'Y-m-d'
            }),

            new Ext.form.DateField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.tmporarystopTime'/>",
                name: 'tmporarystopTime',
                format: 'Y-m-d'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptemail'/>",
                name: 'deptemail',
                vtype: 'email'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptfax'/>",
                name: 'deptfax',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptmobile'/>",
                name: 'deptmobile',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.deptphone'/>",
                name: 'deptphone',
                vtype: 'number'
            }),

            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.regionflag'/>",
                name: 'areaid', id: 'areaid',
                readOnly: 'true'
                //allowBlank:false
            }),
            new Ext.form.TextArea({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.remark'/>",
                id: 'remark',
                name: 'remark',
                grow: true,
                preventScrollbars: true
            }),
            /* Hidden Field
            */
            new Ext.form.HiddenField({name: 'id'}),
            new Ext.form.HiddenField({name: 'deptId'}),
            new Ext.form.HiddenField({name: 'linkid'}),
            new Ext.form.HiddenField({name: 'opertime'}),
            new Ext.form.HiddenField({name: 'operuserid'}),
            new Ext.form.HiddenField({name: 'parentDeptid', id: 'parentDeptid'}),
            new Ext.form.HiddenField({name: 'ordercode'}),
            new Ext.form.HiddenField({name: 'leaf'}),
            new Ext.form.HiddenField({name: 'tempid', id: 'tempid'}),
            new Ext.form.HiddenField({name: 'newAreaId', id: 'newAreaId'}),
            new Ext.form.HiddenField({name: 'tempid2', id: 'tempid2'}),
            new Ext.form.HiddenField({name: 'regionflag', id: 'regionflag'}),

            // dw fields
            new Ext.form.SimpleSelect({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.qualification'/>",
                hiddenName: 'qualification', id: 'qualification_text',
                values: [
                    ['1', '1'],
                    ['0', '0']
                ],
                value: '1'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.registerFund'/>",
                name: 'registerFund', id: 'registerFund'
            }),
            new Ext.form.SimpleSelect({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.companyType'/>",
                hiddenName: 'companyType', id: 'companyType_text',
                values: [
                    ['1', '1'],
                    ['0', '0']
                ],
                value: '1'
            }),
            new Ext.form.DateField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.setupTime'/>",
                name: 'setupTime', id: 'setupTime',
                format: 'Y-m-d'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.fixAsset'/>",
                name: 'fixAsset', id: 'fixAsset'
            }),
            new Ext.form.HiddenField({name: 'isPartners', id: 'isPartners'}),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.peopleNumber'/>",
                name: 'peopleNumber', id: 'peopleNumber',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.companyWeb'/>",
                name: 'companyWeb', id: 'companyWeb',
                vtype: 'url'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.contacter'/>",
                name: 'contacter', id: 'contacter'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.businessLicense'/>",
                name: 'businessLicense', id: 'businessLicense'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.certificationDept'/>",
                name: 'certificationDept', id: 'certificationDept'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.professionalLevel'/>",
                name: 'professionalLevel', id: 'professionalLevel'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.isUnicomAssociation'/>",
                name: 'isUnicomAssociation', id: 'isUnicomAssociation'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.managePeople'/>",
                name: 'managePeople', id: 'managePeople'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.seniorTitle'/>",
                name: 'seniorTitle', id: 'seniorTitle',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.intermediateTitle'/>",
                name: 'intermediateTitle', id: 'intermediateTitle',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.juniorTitle'/>",
                name: 'juniorTitle', id: 'juniorTitle',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.workers'/>",
                name: 'workers', id: 'workers',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.ownCars'/>",
                name: 'ownCars', id: 'ownCars',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.ownInstrument'/>",
                name: 'ownInstrument', id: 'ownInstrument',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.lines'/>",
                name: 'lines', id: 'lines',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.baseStations'/>",
                name: 'baseStations', id: 'baseStations',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.towers'/>",
                name: 'towers', id: 'towers',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.microwaves'/>",
                name: 'microwaves', id: 'microwaves',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.powerAndSet'/>",
                name: 'powerAndSet', id: 'powerAndSet',
                vtype: 'number'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.qualificationValidity'/>",
                name: 'qualificationValidity', id: 'qualificationValidity'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.bankAccount'/>",
                name: 'bankAccount', id: 'bankAccount'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.thirdServiceContract'/>",
                name: 'thirdServiceContract', id: 'thirdServiceContract'
            }),
            new Ext.form.TextField({
                fieldLabel: "<fmt:message key='tawSystemDeptForm.attachName'/>",
                name: 'attachName', id: 'attachName'
            })
        ], // end of fields

        /************************
         * Custom onLoad Functions
         *************************/
        onLoadFunctions: function () {
            var treeAction2 = '${app}/xtree.do?method=userFromDept';
            var treeArea = '${app}/xtree.do?method=areaTree';
            var treeDept = '${app}/xtree.do?method=dept';
            new xbox({
                btnId: 'deptmanager',
                dlgId: 'hello-dlg2',
                treeDataUrl: treeAction2,
                treeRootId: '-1',
                treeRootText: '${eoms:a2u('所有人员')}',
                treeChkMode: 'single',
                treeChkType: 'user',
                showChkFldId: 'deptmanager',
                saveChkFldId: 'tempid'
            });
            new xbox({
                btnId: 'tmporaryManager',
                dlgId: 'hello-dlg',
                treeDataUrl: treeAction2,
                treeRootId: '-1',
                treeRootText: '${eoms:a2u('所有人员')}',
                treeChkMode: 'single',
                treeChkType: 'user',
                showChkFldId: 'tmporaryManager',
                saveChkFldId: 'tempid2'
            });
            new xbox({
                btnId: 'areaid',
                dlgId: 'hello-dlg3',
                treeDataUrl: treeArea,
                treeRootId: '-1',
                treeRootText: '${eoms:a2u('所有地市')}',
                treeChkMode: 'single',
                treeChkType: 'area',
                showChkFldId: 'areaid',
                saveChkFldId: 'newAreaId'
            });
            new xbox({
                btnId: 'parentDeptName',
                dlgId: 'dlg-dept',
                dlgTitle: '${eoms:a2u('请选择新的父部门')}',
                treeDataUrl: treeDept,
                treeRootId: '-1',
                treeRootText: '${eoms:a2u('所有部门')}',
                treeChkMode: 'single',
                treeChkType: 'dept',
                showChkFldId: 'parentDeptName',
                saveChkFldId: 'parentDeptid'
            });
        }
    }; // end of config
    Ext.onReady(AppSimpleTree.init, AppSimpleTree);

    //search
    var searcher;
    var searchConfig = {
        url: 'tawSystemDepts.do?method=xsearch',
        paramMapping: {deptName: 'sDeptName'},
        fields: [{name: 'id', mapping: 'deptId'}, {name: 'text', mapping: 'deptName'}, {
            name: 'name',
            mapping: 'deptName'
        }, 'parentDeptid', 'deptmanager', 'deptphone'],
        cm: [
            {header: "${eoms:a2u('部门名称')}", dataIndex: 'name'},
            {header: "${eoms:a2u('部门负责人')}", dataIndex: 'deptmanager'},
            {header: "${eoms:a2u('电话')}", dataIndex: 'deptphone'}
        ]
    };

    function doSearch() {
        if ($('sDeptName').value.trim() == "") {
            alert("${eoms:a2u('请输入搜索关键字')}");
            return;
        }
        searcher.load();
    }

    //代维部门的字段
    dwFields = ['qualification_text', 'registerFund', 'companyType_text', 'setupTime', 'fixAsset'
        , 'peopleNumber', 'companyWeb', 'contacter', 'businessLicense', 'certificationDept'
        , 'professionalLevel', 'isUnicomAssociation', 'managePeople', 'seniorTitle', 'intermediateTitle'
        , 'juniorTitle', 'workers', 'ownCars', 'ownInstrument', 'lines'
        , 'baseStations', 'towers', 'microwaves', 'powerAndSet', 'qualificationValidity'
        , 'bankAccount', 'thirdServiceContract', 'attachName'];

    //显示或隐藏代维部门字段
    function toggleDWFields(isShow) {
        Ext.getCmp("isPartners").setValue(isShow ? 1 : 0);
        Ext.each(dwFields, function (id) {
            if (isShow) {
                Ext.getCmp(id).show();
            } else {
                Ext.getCmp(id).hide();
            }
        });
    }

    Ext.onReady(function () {
        searcher = new AppSearch();
        searcher.init(searchConfig);
    });
</script>

<div id="headerPanel" class="app-header">
    <img src="${app}/styles/default/images/header-dept.gif" align="left">
    <div style="padding-top:6px">
        <form onsubmit="javascript:doSearch();return false;">
            ${eoms:a2u('查询部门名称')}: <input type="text" name="deptName" id="sDeptName" class="text">
            <input type="button" value="${eoms:a2u('查询')}" class="button" onclick="javascript:doSearch();"
                   style="vertical-align:middle">
        </form>
    </div>
</div>
<div id="helpPanel" class="app-panel">
    <dl>
        <dt>${eoms:a2u('功能说明')}</dt>
        <dd>
            ${eoms:a2u('管理公司中的所有部门，呈树状结构，可实现增删改查等功能。只有超级管理员有此操作的权限。')}
        </dd>
    </dl>
    <br/>
    <dl>
        <dt>${eoms:a2u('添加一个下级部门')}</dt>
        <dd>${eoms:a2u('在树图中的部门上点击右键，并选择"新建下级部门"')}</dd>
        <dt>${eoms:a2u('修改一个部门的信息')}</dt>
        <dd>${eoms:a2u('在树图中的部门上点击右键，并选择"修改"')}</dd>
        <dt>${eoms:a2u('删除部门')}</dt>
        <dd>${eoms:a2u('在树图中的部门上点击右键，并选择"删除"')}</dd>
    </dl>
</div>
<div id="treePanel">
    <div id="treePanel-tb" class="tb"></div>
    <div id="treePanel-body"></div>
</div>
<div id="formPanel">
    <div id="formPanel-body" class="app-panel"></div>
</div>

<div id="searchPanel"></div>
<div id="searchGrid"></div>
<%@ include file="/common/footer_eoms.jsp" %>
