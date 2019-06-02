<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext_debug.jsp"%>
<!-- 
<script type="text/javascript" src="${app}/scripts/adapter/eos-adapter.js"></script>
 -->
<form id="frm">
	  <input type="button" name="usert" id="usert" value="请选择部门" class="btn"/>
	  <input type="text" name="showd2" id="showd2" value="" class="text" alt="allowBlank:false"/>
	  <input type="hidden" name="saved2" id="saved2"/>
	  <%--
	  <eoms:xbox id="userTree" dataUrl="${app}/xtree.do?method=dept" rootId="-1" rootText="部门树" valueField="saved2" handler="usert"
		textField="showd2" viewer="true" 
		data='[{"id":"2","name":"集团公司","nodeType":"dept"},{"id":"1","name":"省公司","nodeType":"dept"}]'
	  ></eoms:xbox>
	  --%>
	  <eoms:xbox id="dictTree" dataUrl="${app}/xtree.do?method=dict" rootId="1010104" rootText="专业树" valueField="saved2" handler="usert"
		textField="showd2" viewer="true" mode="clearPathById"
	  ></eoms:xbox>


<!-- 最简单的派发树，默认含有部门/人员树,可选择任意类型 -->
<eoms:chooser id="test0" category="[{id:'send1',text:'派发1',childType:'user,dept',limit:3,allowBlank:false},{id:'copy2',text:'copy2'}]"/>


<!-- 自定义面板的派发树 -->
<eoms:chooser id="test1"
	category="[{id:'send1',text:'派发',childType:'user,subrole',allowBlank:false,limit:-1,vtext:'请选择派发人'},{id:'c',text:'copy',limit:-1}]"
	panels="[
		{text:'当前角色1',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=245&flowId=53'},
		{text:'当前大角色2',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=245&flowId=53'},
		{text:'dept',dataUrl:'/xtree.do?method=userFromDept'}
	]"
/>

<%-- 
<!-- 派发树测试 -->
<eoms:chooser id="test1"
	category="[{id:'send1',text:'派发',limit:-1},{id:'c',text:'copy',limit:-1}]"
	panels="[
		{text:'子角色',dataUrl:'/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea&roleId=189'},
		{text:'树图节点图标样式',dataUrl:'/test/nodetypes.json'}
	]"
/>
--%>

<!-- 包含已选择数据的派发树 -->
<%--
<c:set var="id">common/role[@type=\'sdf\']/send2</c:set>
<eoms:chooser id="test2"  
	category="[{id:'${id}',text:'派发',allowBlank:false,vtext:'请选择派发人2'}]" 
	data="[
		{id:'liqiuye',nodeType:'user',categoryId:'${id}'}
	]"
/>
--%>

<!-- 工单选择子角色专用派发树，含过滤窗口，可设置组长 -->
<eoms:chooser id="test" type="role" roleId="245" flowId="53" config="{showLeader:true,mainPanelTitle:'可选择的子角色'}"
	category="[
	 {id:'send',text:'派发',allowBlank:false,vtext:'sss',limit:3}
	,{id:'copy',text:'抄送',childType:'user,subrole,dept',limit:2}
	,{id:'audit',text:'审核',childType:'user',limit:-1}
	]"
/>

<br/>

<script type="text/javascript">
	Ext.onReady(function(){
		var p = chooser_test1.west.getPanel(1);
		p.on('activate',function(){
			chooser_test1.category[0].hide();
		}); 	
		p.on('deactivate',function(){
			chooser_test1.category[0].show();
		});
	});
</script>

<br/><br/>
<input type="button" value="role 186" onclick="javascript:chooser_test.setRoleId(186)">
<input type="button" value="role 189" onclick="javascript:chooser_test.setRoleId(244)">
<input type="button" value="setDataUrl" onclick="javascript:chooser_test.setDataUrl('/xtree.do?method=userFromDept',1)">
<input type="button" value="hide1" onclick="javascript:chooser_test.category[1].hide();">
<input type="button" value="show1" onclick="javascript:chooser_test.category[1].show();">
<input type="button" value="0limitto2" onclick="javascript:chooser_test.category[0].attr('limit',2);chooser_test.reset();">
<br/><br/>
<input type="submit" value="submit" id="btn2" class="submit">
</form>
<%@ include file="/common/footer_eoms.jsp"%>
