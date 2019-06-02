<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext_debug.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree_search.js"></script>
<script type="text/javascript">

	//覆盖"修改"动作
	AppSimpleTree.doEdtNode = function(nodeData){
    	toggleDWFields(nodeData.isPartners == "1" ? true : false); //切换代维部门相关表单域
    	this.openPanel('formPanel','${eoms:a2u("修改")}'+nodeData.text+'${eoms:a2u("的信息")}');
    	this.form.url = config.actions.edtNode.url;
    	this.sbmBtn.setText(config.actions.edtNode.btnText);
    	try{config.actions.edtNode.init();}catch(e){};
    	this.formData.load({params:{id:nodeData.id}});
	};
	
	var config = {
		hideSearchPanel : false,
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/dept/tawSystemDepts.do?method=getNodesPartner",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有部门')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建下级部门')}"},
			delNode:{text:"${eoms:a2u('删除这个部门')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/dept/tawSystemDepts.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("parentDeptid","id");
					AppSimpleTree.setField("parentDeptName","text");
					toggleDWFields(false);
				}
			},
			getNode : {
				url:"${app}/dept/tawSystemDepts.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/dept/tawSystemDepts.do?method=xedit",
				success : function(){
					AppSimpleTree.refreshTree();
				}
			},
			delNode : {
				url:"${app}/dept/tawSystemDepts.do?method=xdelete",
				customData:function(selNode){
					return "id="+selNode.id+"&parentDeptid="+selNode.parentDeptid;
				}
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('合作伙伴名称')}",
		        name: 'deptName',
		        allowBlank:false
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('父部门')}",
		        name: 'parentDeptName',
		        id: 'parentDeptName',
	            readOnly:'true'
		    }),
		    new Ext.form.SimpleSelect({
		        fieldLabel: "${eoms:a2u('合作伙伴企业性质')}",
		        hiddenName: 'companyType',id:'companyType_text',
	            values :  ${eoms:subDict2Array("1120101")},
	            value:'1',allowBlank:false
		    }),
		    new Ext.form.SimpleSelect({
		        fieldLabel: "${eoms:a2u('合作伙伴资质')}",
		        hiddenName: 'tmporarybegintime',id:'qualification_text',
	            values : ${eoms:subDict2Array("1120102")},
	            value:'1',allowBlank:false
		    }),
		    new Ext.form.DateField({
		        fieldLabel: "${eoms:a2u('资质有效期')}",
		        name: 'tmporarystopTime',id:'tmporarystopTime',
		        format:'Y-m-d',allowBlank:false
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('合作伙伴法人')}",
		        name: 'deptmanager',id:'deptmanager',allowBlank:false
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('所属地市')}",
		        name: 'areaid',id:'areaid',
		        readOnly:'true',allowBlank:false
		        //allowBlank:false
		    }),
		  	new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('注册资金(万)')}",
		        name: 'registerFund',id:'registerFund'
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('联系电话')}",
		        name: 'deptphone', 
		        vtype:'number',allowBlank:false
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('公司地址')}",
		        name: 'deptmobile', id:'deptmobile'
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('传真号码')}",
		        name: 'deptfax',
		        vtype:'number'
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "${eoms:a2u('开户银行')}",
		        name: 'remark',id:'remark'
		    }),
		    new Ext.form.TextField({
		        fieldLabel: "<fmt:message key='tawSystemDeptForm.bankAccount'/>",
		        name: 'tmporaryManager',id:'tmporaryManager',allowBlank:false
		    }),	  
		    new Ext.form.TextField({
		        fieldLabel: "<fmt:message key='tawSystemDeptForm.thirdServiceContract'/>",
		        name: 'thirdServiceContract',id:'thirdServiceContract'
		    }),
		  	new Ext.form.TextField({
		        fieldLabel: "<fmt:message key='tawSystemDeptForm.attachName'/>",
		        name: 'attachName',id:'attachName'
		    }),
		    
		    
		    new Ext.form.HiddenField({name: 'isPartners',id:'isPartners'}),
	 
		    /* Hidden Field
		    */
		    new Ext.form.HiddenField({name: 'id'}),
		    new Ext.form.HiddenField({name: 'deptId'}),
		    new Ext.form.HiddenField({name: 'linkid'}),
		    new Ext.form.HiddenField({name: 'opertime'}),
		    new Ext.form.HiddenField({name: 'operuserid'}),
		    new Ext.form.HiddenField({name: 'parentDeptid',id:'parentDeptid'}),
		    new Ext.form.HiddenField({name: 'ordercode'}),
		    new Ext.form.HiddenField({name: 'leaf'}),
		    new Ext.form.HiddenField({name: 'tempid',id:'tempid'}),
		    new Ext.form.HiddenField({name: 'newAreaId',id:'newAreaId'}),
		    new Ext.form.HiddenField({name: 'tempid2',id:'tempid2'}),
		    new Ext.form.HiddenField({name: 'regionflag',id:'regionflag'}),
		    new Ext.form.HiddenField({name: 'deptemail'})
		  
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
			var	treeAction2='${app}/xtree.do?method=userFromDept';
			var treeArea='${app}/xtree.do?method=areaTree';
			var treeDept = '${app}/xtree.do?method=dept';
			

			new xbox({
				btnId:'areaid',dlgId:'hello-dlg3',
				treeDataUrl:treeArea,treeRootId:'-1',treeRootText:'${eoms:a2u('所有地市')}',treeChkMode:'single',treeChkType:'area',
				showChkFldId:'areaid',saveChkFldId:'newAreaId'
			});
			new xbox({
				btnId:'parentDeptName',dlgId:'dlg-dept',dlgTitle:'${eoms:a2u('请选择新的父部门')}',
				treeDataUrl:treeDept,treeRootId:'-1',treeRootText:'${eoms:a2u('所有部门')}',treeChkMode:'single',treeChkType:'dept',
				showChkFldId:'parentDeptName',saveChkFldId:'parentDeptid'
			});
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
	
	//search
	var searcher;
	var	searchConfig = {
		url:'tawSystemDepts.do?method=xsearchPartner',
		paramMapping : {deptName:'sDeptName',deptmanager:'sDeptmanager',deptphone:'sDeptphone'},
		fields : [
			{name: 'id', mapping: 'deptId'},
			{name: 'text', mapping: 'deptName'},
			{name: 'name', mapping: 'deptName'},
			'parentDeptid','deptmanager','deptphone','isPartners','deptemail',
			'areaid','tmporarybegintime','tmporarystopTime','tmporaryManager','remark','tmpdeptid'
		],
		cm : [
				{header : "${eoms:a2u('合作伙伴名称')}",	dataIndex : 'name'},
				{header : "${eoms:a2u('合作伙伴法人')}",dataIndex : 'deptmanager'},
				{header : "${eoms:a2u('所属地市')}",dataIndex : 'deptemail'},
				{header : "${eoms:a2u('联系电话')}",		dataIndex : 'deptphone'},
				{header : "${eoms:a2u('合作伙伴资质')}",	dataIndex : 'tmpdeptid'},
				{header : "${eoms:a2u('资质有效期')}",dataIndex : 'tmporarystopTime'},
				{header : "${eoms:a2u('开户行')}",dataIndex : 'remark'},
				{header : "${eoms:a2u('银行账号')}",		dataIndex : 'tmporaryManager'}
			]
		};
		
	function doSearch(){
		
		searcher.load();
	}
	
	//代维部门的字段
	dwFields = ['registerFund','companyType_text','deptmobile','qualification_text',
				'thirdServiceContract','attachName'];
	
	//显示或隐藏代维部门字段
	function toggleDWFields(isShow){
		Ext.getCmp("isPartners").setValue(isShow ? 1 : 0);
		Ext.each(dwFields,function(id){
			if(isShow){
				Ext.getCmp(id).show();
			}
			else{
				Ext.getCmp(id).hide();
			}
		});
	}
	
	Ext.onReady(function(){
		searcher = new AppSearch();
		searcher.init(searchConfig);
			
		// custom menuitem 
		AppSimpleTree.addmenu('',{
			text:'${eoms:a2u('新建代维部门')}',cls:'new-mi',
			handler:function(){
				AppSimpleTree.doNewNode();
				toggleDWFields(true);
			}
		},1);
		
		var menu = AppSimpleTree.treeCtxMenu.items;
		menu.get('newnode').nodeTypeMapping = "hide";
		menu.get('edtnode').nodeTypeMapping = "partner-dept";
		menu.get('delnode').nodeTypeMapping = "partner-dept";
	});	
</script>

<div id="headerPanel" class="app-header">
	<img src="${app}/styles/${theme}/images/header-dept.gif" align="left">
	<div style="padding-top:6px">
	<form onsubmit="javascript:doSearch();return false;">
	${eoms:a2u('名称')}: <input type="text" name="deptName" id="sDeptName" class="text" style="width:80px;">&nbsp;
	${eoms:a2u('法人')}: <input type="text" name="deptmanager" id="sDeptmanager" class="text" style="width:80px;">&nbsp;
	${eoms:a2u('电话')}: <input type="text" name="deptphone" id="sDeptphone" class="text" style="width:80px;">&nbsp;
	<input type="button" value="${eoms:a2u('查询')}" class="button" onclick="javascript:doSearch();" style="vertical-align:middle">
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
<%@ include file="/common/footer_eoms.jsp"%>
