<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<style type="text/css">
    .panle {
        margin: 10px;
        border: 1px solid #eee;
        padding: 10px;
    }
</style>
<script type="text/javascript">
    var roleTree, userTree;

    Ext.onReady(function () {
        var v = new eoms.form.Validation({form: 'theform'});
        var treeAction = '${app}/xtree.do?method=dept';

        var config = {
            btnId: 'usert', dlgId: 'hello-dlg',
            treeDataUrl: treeAction, treeRootId: '-1', treeRootText: '部门树', treeChkMode: '', treeChkType: 'dept',
            showChkFldId: 'showd2', saveChkFldId: 'saved2', returnJSON: true
        };
        //userTree.onOutput = function(textList,dataList,jsonData){
        //	alert(textList);
        //}
        config.onLayout = function (cal, layout) {
            var config = {
                treeDataUrl: '${app}/xtree.do?method=userFromDept',
                treeRootId: '-1',
                treeRootText: '人员树',
                treeChkType: 'user'
            };
            layout.add('west', cal.newTreePanel(config));
            var config2 = {
                treeDataUrl: '${app}/xtree.do?method=subroleFromDept',
                treeRootId: '-1',
                treeRootText: '子角色树',
                treeChkType: 'subrole'
            };
            layout.add('west', cal.newTreePanel(config2));
        };
        config.onBeforeCheck = function (node, checked) {
            if (checked && this.gridData.getCount() > 0) {
                var r = this.gridData.getAt(0);
                if (r.json.nodeType != node.attributes.nodeType) {
                    alert('Only one nodeType allow');
                    return;
                }
            }
            return true;
        }
        //userTree = new xbox(config);


        var flowRoleSubroleTree = "${app}/test/wf.json"; //暂时使用测试地址；上传svn前要改为以下url
        //var flowRoleSubroleTree = "${app}/sheet/workflow/workflow.do?method=getAllWorkflow";
        var cfg = {
            btnId: 'frbtn',
            treeDataUrl: flowRoleSubroleTree, treeRootId: '-1', treeRootText: '流程角色树',
            treeChkMode: '', treeChkType: 'subrole', showChkFldId: 'frshow', saveChkFldId: 'frsave',
            baseAttrs: {
                loader: new Ext.tree.TreeLoader({
                    dataUrl: eoms.appPath + '/xtree.do?method=flowRoleSubrole',
                    listeners: {
                        beforeload: function (loader, node, callback) {
                            loader.baseParams['nodeType'] = node.attributes.nodeType || '';
                        }
                    }
                })
            }
        }
        var frTree = new xbox(cfg);
        frTree.defaultTree.loader.applyLoader = false;
    });
</script>

<form id="theform">

    <!-- xbox 1 -->
    <%--
    <div class="panle">
      <p>使用下拉框改变树图URL,例如：从人员树图->角色树图</p>
      <select onchange="javascript:xbox_roleTree.resetRoot(this.value);">
        <option value="">select one</option>
        <option value="user.json">user.json</option>
        <option value="dept.json">dept.json</option>
      </select>
      <input type="text" name="showd" id="showd" class="text"></input>
      <input type="text" name="saved" id="saved" class="text"/>
      <!--
      <p>使用下拉框改变树图URL的参数，例如：xtree.do?id=1 -> xtree.do?id=2</p>
      <select onchange="javascript:roleTree.setParam('id',this.value);">
        <option value="">select one</option>
        <option value="0">nav tree</option>
        <option value="1">dept user tree</option>
      </select>
      -->

      <eoms:xbox id="roleTree" dataUrl="${app}/xtree.do?method=dept" rootId="-1" rootText="Users" valueField="saved" handler="showd"
        textField="showd"
      ></eoms:xbox>
      --%>
    </div>

    <!-- xbox 2 -->
    <div class="panle">
        <input type="button" name="usert" id="usert" value="请选择部门" class="btn"/>
        <input type="text" name="showd2" id="showd2" value="" class="text" alt="allowBlank:false"/>
        <input type="hidden" name="saved2" id="saved2"/>
        <%--
        <eoms:xbox id="userTree" dataUrl="${app}/xtree.do?method=dept" rootId="-1" rootText="部门树" valueField="saved2" handler="usert"
          textField="showd2" viewer="true"
          data='[{"id":"2","name":"集团公司","nodeType":"dept"},{"id":"1","name":"省公司","nodeType":"dept"}]'
        ></eoms:xbox>
        --%>
        <eoms:xbox id="dictTree" dataUrl="${app}/xtree.do?method=dict" rootId="1011701" rootText="专业树"
                   valueField="saved2" handler="usert"
                   textField="showd2" viewer="true" mode="clearPathById"
                   data='[{"id":"1011701030101","name":"OMCR","nodeType":"dict",text:"无线-华为-OMCR"},
			   {"id":"10117010201","name":"中兴","nodeType":"dict",text:"智能网-中兴"}]'
        ></eoms:xbox>
        <script type="text/javascript">
            Ext.onReady(function () {
                xbox_dictTree.callback = function (json, list) {
                    Ext.Ajax.request({
                        url: eoms.appPath + '/scripts/adapter/test/testExtXbox.jsp',
                        params: "flowId=1&dictId=" + list
                    });
                }
            });
        </script>
    </div>

    <!-- xbox 3 -->
    <div class="panle">
        <input type="button" name="usert3" id="usert3" value="请" class="btn"
               onclick="javascript:userTree.show(this,'showd3','saved3')"/>
        <input type="text" name="showd3" id="showd3" class="text"/>
        <input type="hidden" name="saved3" id="saved3"/>
    </div>

    <!-- xbox 4 -->
    <div class="panle">
        <input type="button" name="tree4" id="tree4" value="请选择角色选择人员" class="btn"/>
        <input type="text" name="show4" id="show4" class="text">
        <input type="hidden" name="save4" id="save4"/>
        <%--
        <eoms:xbox id="x4" dataUrl="${app}/role/tawSystemRoles.do?method=xGetSubRoleNodesFromFlow" rootId="0" rootText="选择流程下的子角色"
          valueField="save4" handler="tree4" checktype="subrole"
          textField="show4" viewer="true" dlgTitle="请选择角色" returnJSON="true"
        ></eoms:xbox>


        <eoms:xbox id="x4" dataUrl="${app}/xtree.do?method=role" rootId="1" rootText="role" valueField="save4" handler="tree4"
          textField="show4" viewer="true" dlgTitle="请选择角色" returnJSON="true"
        ></eoms:xbox>
        --%>
    </div>

    <!-- xbox 5 -->
    <%--
   <div class="panle">
     <input type="button" name="tree5" id="tree5" value="请选择人员" class="btn"/>
     <input type="text" name="show5" id="show5" class="text">
     <input type="hidden" name="save5" id="save5"/>

     <c:set var="roles">
       <json:array>
         <json:object>
           <json:property name="id" value="108" />
           <json:property name="name" value="ceshi7" />
           <json:property name="nodeType" value="dept" />
         </json:object>
       </json:array>
     </c:set>

     <eoms:xbox id="x" dataUrl="${app}/xtree.do?method=userFromDept" rootId="-1" rootText="dept" valueField="save5" handler="tree5"
       checktype="user,dept" single="true" textField="show5"
       data="${roles}"
     />
   </div>
   --%>

    <!-- xbox 6 -->
    <div class="panle">
        <input type="button" name="frbtn" id="frbtn" value="请选择角色"/>
        <input type="text" name="frshow" id="frshow" class="text">
        <input type="hidden" name="frsave" id="frsave"/>
    </div>
</form>
<%@ include file="/common/footer_eoms.jsp" %>
