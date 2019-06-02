<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@page import="com.boco.eoms.commons.system.dict.util.DictMgrLocator"%>
<%@page import="com.boco.eoms.commons.system.dict.service.IDictService"%>
<%@page import="com.boco.eoms.commons.system.dict.util.Util"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">

<%
	IDictService dictService=	DictMgrLocator.getDictService();
	//获取专业类型
	List specialTypes=dictService.getDictItems(Util.constituteDictId("dict-major-type","majorType"));
	request.setAttribute("specialTypes",specialTypes);
%>
var config = { 
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/sheet/special/tawSheetSpecials.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有专业')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建子专业')}"},
			delNode:{text:"${eoms:a2u('删除这个专业')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/sheet/special/tawSheetSpecials.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parspeid","id"); 
				}
			},
			getNode : {
				url:"${app}/sheet/special/tawSheetSpecials.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/sheet/special/tawSheetSpecials.do?method=xedit"
			},
			delNode : {
				url:"${app}/sheet/special/tawSheetSpecials.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
   fields : [
        new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSheetSpecialForm.specialname"/>',
            name: 'specialname',
            allowBlank:false,
            width:150
        }),
       new Ext.form.TextField({
            fieldLabel: '<fmt:message key="tawSheetSpecialForm.refuserid"/>',
            id: 'refuserid',
            name: 'refuserid',
            allowBlank:true,
            width:150
        }),
        new Ext.form.SimpleSelect({
            fieldLabel: "<fmt:message key='tawSheetSpecialForm.specialtype'/>",
            name: 'specialtype',
            hiddenName:'specialtype',
            values : [
            			<logic:notEmpty name="specialTypes">
            				<logic:iterate id="item" name="specialTypes">
        				['<bean:write name='item' property='itemName'/>', '<bean:write name='item' property='itemId'/>'],
        					</logic:iterate>
        				</logic:notEmpty>
        			   ],
            allowBlank:false,
            value:'1',
            width:150
        }),
     //    new Ext.form.SimpleSelect({
     //       fieldLabel: '<fmt:message key='tawSheetSpecialForm.style'/>',
     //       hiddenName:'style',
     //       values : [
     //   				['ERICSSON', '0'],
     //   				['SIEMENS', '1'],
     //   				['HUAWEI', '2'],
      //  				['MOTO', '3'],
      //  				['ZTE', '4'],
      //  				['FIBERHOME','3']
      //  			   ],
      //      allowBlank:true,
      //      value:'1',
      //      width:150
     //   }),
    //    new Ext.form.TextField({
     //       fieldLabel: '<fmt:message key="tawSheetSpecialForm.specialtype"/>',
       //     name: 'specialtype',
      ///      width:150
        ///}),
        new Ext.form.TextArea({
            fieldLabel: '<fmt:message key="tawSheetSpecialForm.remark"/>',
            name: 'remark',
            width:150,
            grow: true,
            preventScrollbars:true
        }),
        new Ext.form.HiddenField({name: 'leaf',id:'leaf'}),
        new Ext.form.HiddenField({name: 'speid',id:'speid'}),
        new Ext.form.HiddenField({name: 'id',id:'id'}),
        new Ext.form.HiddenField({name: 'parspeid',id:'parspeid'}),
        new Ext.form.HiddenField({name: 'ordercode',id:'ordercode'}),
         new Ext.form.HiddenField({name: 'newuserid',id:'newuserid'})
    ], // end of fs
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
			var	treeAction2='${app}/xtree.do?method=userFromDept';
			new xbox({
		        btnId:'refuserid',dlgId:'hello-dlg2',
		        treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'Users',treeChkMode:'',treeChkType:'user',
				showChkFldId:'refuserid',saveChkFldId:'newuserid'
	      });
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>
<style type="text/css">
     
body{
	 background-image:none;
}
</style>
<div id="headerPanel" class="app-header"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('添加一个子专业')}</dt>
		<dd>${eoms:a2u('在树图中的部门上点击右键，并选择"新建子专业"')}</dd>
		<dt>${eoms:a2u('修改一个专业的信息')}</dt>
		<dd>${eoms:a2u('在树图中的专业上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除专业')}</dt>
		<dd>${eoms:a2u('在树图中的专业上点击右键，并选择"删除"')}</dd>
	</dl>
</div>
<div id="treePanel">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>
<div id="formPanel">
	<div id="formPanel-body" class="app-panel"></div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>