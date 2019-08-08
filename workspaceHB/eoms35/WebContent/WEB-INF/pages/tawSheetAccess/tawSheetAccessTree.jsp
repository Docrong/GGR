<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
    var config = {
        /**************
         * Tree Configs
         **************/
        treeGetNodeUrl: "tawSheetAccesss.do?method=xGetChildNodes",
        treeRootId: '-1',
        treeRootText: "${eoms:a2u('�?��」�?')}",
        ctxMenu: {
            newNode: {text: "${eoms:a2u('�板缓')}"},
            delNode: {text: "${eoms:a2u('���')}"}
        },//end of ctxMenu
        /**************
         * Form Configs
         **************/
        actions: {
            newNode: {
                btnText: "${eoms:a2u('淇��')}",
                url: "tawSheetAccesss.do?method=xsave",
                init: function () {
                    AppSimpleTree.setField("parentId", "id");
                }
            },
            getNode: {
                url: "tawSheetAccesss.do?method=xget"
            },
            edtNode: {
                btnText: "${eoms:a2u('淇��淇��')}",
                url: "tawSheetAccesss.do?method=xedit"
            },
            delNode: {
                url: "tawSheetAccesss.do?method=xdelete"
            }
        },
        fieldOptions: {
            width: 150
        },
        fields: [

            , new Ext.form.TextField({
                fieldLabel: '<fmt:message key="tawSheetAccessForm.access"/>',
                name: 'accesss',
                allowBlank: false
            })

            /* Hidden Field
             */
            , new Ext.form.HiddenField({name: 'id'})

            /* Hidden Field
             */
            , new Ext.form.HiddenField({name: 'accessid'})
            , new Ext.form.HiddenField({name: 'leaf'})
            , new Ext.form.HiddenField({name: 'ordercode'})
            , new Ext.form.HiddenField({name: 'parAccessid'})
            , new Ext.form.TextField({
                fieldLabel: '<fmt:message key="tawSheetAccessForm.processname"/>',
                name: 'processname',
                allowBlank: false
            })

            , new Ext.form.TextField({
                fieldLabel: '<fmt:message key="tawSheetAccessForm.taskname"/>',
                name: 'taskname',
                allowBlank: false
            })

            , new Ext.form.TextField({
                fieldLabel: '<fmt:message key="tawSheetAccessForm.remark"/>',
                name: 'remark',
                allowBlank: false
            })

        ], // end of fields
        /************************
         * Custom onLoad Functions
         *************************/
        onLoadFunctions: function () {
        }
    }; // end of config
    Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>

<div id="headerPanel" class="app-header"><h1>Title</h1></div>
<div id="helpPanel" class="app-panel">
    <dl>
        <dt>${eoms:a2u('娣诲�')}</dt>
        <dd></dd>
        <dt>${eoms:a2u('淇��')}</dt>
        <dd></dd>
        <dt>${eoms:a2u('���')}</dt>
        <dd></dd>
    </dl>
</div>
<div id="treePanel">
    <div id="treePanel-tb" class="tb"></div>
    <div id="treePanel-body"></div>
</div>
<div id="formPanel">
    <div id="formPanel-body" class="app-panel"></div>
</div>

<%@ include file="/common/footer_eoms.jsp" %>

