<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree_search.js"></script>
<script type="text/javascript">
	var config = {
		hideSearchPanel : false,
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/sysuser/tawSystemUsers.do?method=getNodesNoPartner",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('所有部门')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建人员')}"},
			delNode:{text:"${eoms:a2u('删除此人员')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/sysuser/tawSystemUsers.do?method=xsave",
				init:function(){
					AppSimpleTree.setField("deptid","id");
					AppSimpleTree.setField("deptname","text");
					var sel = AppSimpleTree.selected;
					if(sel.isPartners && sel.isPartners=="1"){
						toggleDWFields(true);
						Ext.getCmp("isPartners").setValue(1);
					}
					else{
						toggleDWFields(false);
						Ext.getCmp("isPartners").setValue(0);
					}
				}
			},
			getNode : {
				url:"${app}/sysuser/tawSystemUsers.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/sysuser/tawSystemUsers.do?method=xedit",
				init : function(){
					var parentDept = AppSimpleTree.selectedNode.parentNode.attributes;
					if(parentDept.isPartners && parentDept.isPartners=="1"){
						toggleDWFields(true);
						Ext.getCmp("isPartners").setValue(1);
					}
					else{
						toggleDWFields(false);
						Ext.getCmp("isPartners").setValue(0);
					}
				}
			},
			delNode : {
				url:"${app}/sysuser/tawSystemUsers.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.username'/>",
	            name: 'username',
	            allowBlank:false
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.userid'/>",
	            name: 'userid',
	            id:'userid',
	            allowBlank:false,
	            validationEvent:'blur',
	            vtype:'unique'
	        }),
	         new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.passwd'/>",
	            name: 'newPassword',
	            allowBlank:true
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "${eoms:a2u('所属部门')}",
	            name: 'deptname',
	            id:'deptname',
	            readOnly:'true'
	        }),
	          new Ext.form.SimpleSelect({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.accountLocked'/>",
	            hiddenName:'accountLocked',
	            values : [
	        				['true', 'true'],
	        				['false', 'false']
	        			   ],
	            allowBlank:false,
	            value:'false'
	        }),
	         new Ext.form.SimpleSelect({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.enabled'/>",
	            hiddenName:'enabled',
	            values : [
	        				['true', 'true'],
	        				['false', 'false']
	        			   ],
	            allowBlank:false,
	            value:'true'
	        }),
	        /**new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.rolename'/>",
	            name: 'portalrolename',
	            allowBlank:true
	        }),**/
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.cptroomname'/>",
	            id:'cptroomname',
	            name: 'cptroomname',
	            readOnly:'true'
	        }),   
			    
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.email'/>",
	            name: 'email',
	            vtype:'email'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.familyaddress'/>",
	            name: 'familyaddress'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.fax'/>",
	            name: 'fax',
	            vtype:'number'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.mobile'/>",
	            name: 'mobile'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.phone'/>",
	            name: 'phone'
	        }),	      
	        new Ext.form.SimpleSelect({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.sex'/>",
	            hiddenName:'sex',
	            values : [
	        				['${eoms:a2u('男')}', '1'],
	        				['${eoms:a2u('女')}', '0']
	        			   ],
	            allowBlank:false,
	            value:'1'
	        }),
	         new Ext.form.SimpleSelect({
            fieldLabel: "<fmt:message key='tawSystemUserForm.userdegree'/>",
            hiddenName:'userdegree',
            values : [
        				['${eoms:a2u('是')}', '1'],
        				['${eoms:a2u('否')}', '0']
        			   ],
            allowBlank:false,
            value:'0',
            width:150
        }),
        new Ext.form.SimpleSelect({
            fieldLabel: "<fmt:message key='tawSystemUserForm.isfullemploy'/>",
            hiddenName:'isfullemploy',
            values : [
        				['${eoms:a2u('是')}', '1'],
        				['${eoms:a2u('否')}', '0']
        			   ],
            allowBlank:false,
            value:'1',
            width:150
        }),
        
        new Ext.form.SimpleSelect({
            fieldLabel: "<fmt:message key='tawSystemUserForm.isrest'/>",
            hiddenName:'isrest',
            values : [
        				['${eoms:a2u('是')}', '1'],
        				['${eoms:a2u('否')}', '0']
        			   ],
            allowBlank:false,
            value:'1',
            width:150
        }),
	        new Ext.form.TextArea({
	            fieldLabel: "<fmt:message key='tawSystemUserForm.remark'/>",
	            name: 'remark',
	            grow: true,
	            preventScrollbars:true
	        }),
	        /* Hidden Field
	        */
	      	new Ext.form.HiddenField({name: 'id'}),
	      	new Ext.form.HiddenField({name: 'olduserid'}),
	      	new Ext.form.HiddenField({name: 'password'}),
	        new Ext.form.HiddenField({name: 'updatetime'}),
	        new Ext.form.HiddenField({name: 'savetime'}),
	        new Ext.form.HiddenField({name: 'deleted'}),
	        new Ext.form.HiddenField({name: 'operremotip'}),
	        new Ext.form.HiddenField({name: 'operuserid'}),
	        new Ext.form.HiddenField({name: 'deptid',id:'deptid'}),
	        //new Ext.form.HiddenField({name: 'enabled'}),
	        new Ext.form.HiddenField({name: 'cptroomid',id:'cptroomid'})
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
			var treeAction2='${app}/xtree.do?method=getCptroomTree';
		    var deptAction = '${app}/xtree.do?method=dept';
		    var userTree = '${app}/xtree.do?method=userFromDept';
		    var subRoleTree = '${app}/role/tawSystemSubRoles.do?method=xgetAllRolesAndSubRoles'
			new xbox({
				btnId:'cptroomname',dlgId:'hello-dlg1',
				treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'${eoms:a2u('所有机房')}',treeChkMode:'',treeChkType:'cptroom',
				showChkFldId:'cptroomname',saveChkFldId:'cptroomid'
			});
			new xbox({
				btnId:'deptname',dlgId:'dlg-dept',dlgTitle:'${eoms:a2u('请选择该人员所属部门')}',
				treeDataUrl:deptAction,treeRootId:'-1',treeRootText:'${eoms:a2u('所有部门')}',treeChkMode:'single',treeChkType:'dept',
				showChkFldId:'deptname',saveChkFldId:'deptid'
			});
			// subrole moveto user select tree
			new xbox({
				btnId:'selMoveTgt',dlgId:'dlg-user',dlgTitle:'${eoms:a2u('请选择移交人员')}',
				treeDataUrl:userTree,treeRootId:'-1',treeRootText:'${eoms:a2u('所有人员')}',treeChkMode:'',treeChkType:'user',
				showChkFldId:'moveToName',saveChkFldId:'userId'
			});
			// adjust subrole select tree
			new xbox({
				btnId:'selSubRole',dlgId:'dlg-subrole',dlgTitle:'${eoms:a2u('请选子角色')}',
				treeDataUrl:subRoleTree,treeRootId:'-1',treeRootText:'${eoms:a2u('所有角色')}',treeChkMode:'',treeChkType:'subrole',
				showChkFldId:'subRoles',saveChkFldId:'subRoleId'
			});
			 
		}
	}; // end of config
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);


	
	// custom menuitem
	AppSimpleTree.addmenu('user',{
		id:'subroleList-mi', text:'${eoms:a2u('复制子角色')}',cls:'edit-mi',
		handler:function(){
			subroleList(AppSimpleTree.selected);
		}
	});
	
	//subroleList dialog	
	var dlg;	
	var t = new Ext.Template(
    	'<tr>',
        	'<td class=\"icon\"><input type=\"checkbox\" checked=\"true\" class=\"checkbox\" name=\"subRoleIdArray\" value=\"{id}\"></td>',
        	'<td>{text}</td>',
    	'</tr>'
	);
	var choose = new Ext.Template(
    	'<tr>',
        	'<td class=\"icon\"><input type=\"checkbox\" class=\"checkbox\" onclick=\"javascript:chose();\"></td>',
        	'<td>{text}</td>',
    	'</tr>'
	);
		
	function subroleList(nodeData){
		$('moveToName').update();
		$('userId').update();
		var userId = nodeData.id;
		var table = $('subroleList-table');
		while(table.rows.length>0)
    	{
        	table.deleteRow(0);
    	} 
		Ext.Ajax.request({
			method:'post',
			url: 'tawSystemUserRefRoles.do?method=xgetSubRolsByUser&id='+userId,
			success: function(x){
				var data = eoms.JSONDecode(x.responseText);
				choose.append('subroleList-table',0);
				Ext.each(data,function(d){
					t.append('subroleList-table',d);
				});
				eoms.util.colorRows('subroleList-table',0);
				dlg.show();
			}
        });		
	}
	
	// custom menuitem
	AppSimpleTree.addmenu('user',{
		id:'adjustsubrole-mi', text:'${eoms:a2u('调整子角色')}',cls:'edit-mi',
		handler:function(){
			adjustSubRole(AppSimpleTree.selected);
		}
	});
	// adjustSubRole dialog
	var dlg_adjustSubRole;	
	var template = new Ext.Template(
    	'<tr>',
        	'<td class=\"icon\"><input type=\"checkbox\" checked=\"true\" class=\"checkbox\" name=\"subRoleIds\" value=\"{id}\"></td>',
        	'<td>{text}</td>',
    	'</tr>'
	);
	function adjustSubRole(nodeData){
		$('subRoles').update();
		$('subRoleId').update();
		var userId = nodeData.id;
		var table = $('adjustSubRole-table');
		while(table.rows.length>0)
    	{
        	table.deleteRow(0);
    	} 
		Ext.Ajax.request({
			method:'post',
			url: 'tawSystemUserRefRoles.do?method=xgetSubRolsByUser&id='+userId,
			success: function(x){
				var data = eoms.JSONDecode(x.responseText);
				Ext.each(data,function(d){
					template.append('adjustSubRole-table',d);
				});
				eoms.util.colorRows('adjustSubRole-table',0);
				dlg_adjustSubRole.show();
			}
        });		
	}

	
	//search
	var searcher;
	var	searchConfig = {
		url:'tawSystemUsers.do?method=xsearchNoPartner',
		paramMapping : {username:'sUserName',post:'sDeptName'},
		fields : ['id',{name: 'name', mapping: 'username'},{name: 'text', mapping: 'username'},'deptname','mobile','phone','post'],
		cm : [
				{header : "${eoms:a2u('用户名')}",	dataIndex : 'name'},
				{header : "${eoms:a2u('所属部门')}",	dataIndex : 'deptname'},
				{header : "${eoms:a2u('手机')}",		dataIndex : 'mobile'},
				{header : "${eoms:a2u('电话')}",		dataIndex : 'phone'}
			]
		};
		
	Ext.onReady(function(){
		searcher = new AppSearch();
		searcher.init(searchConfig);
		
		//subroleList dialog
		dlg = new Ext.BasicDialog("subroleList-dlg", {
    		height: 350,
    		width: 400,
    		minHeight: 300,
    		minWidth: 300,
    		autoTabs:true,
    		modal: false,
    		proxyDrag: false,
    		shadow: false
		});
		dlg.addKeyListener(27, dlg.hide, dlg);
		dlg.addButton('${eoms:a2u('执行复制')}', function(){
			doCopySubRoles('subroleList-form');
		});
		dlg.addButton('${eoms:a2u('关闭')}', dlg.hide, dlg);
		
		// adjustSubRole dialog
		dlg_adjustSubRole = new Ext.BasicDialog("adjustSubRole-dlg", {
    		height: 350,
    		width: 400,
    		minHeight: 300,
    		minWidth: 300,
    		autoTabs:true,
    		modal: false,
    		proxyDrag: false,
    		shadow: false
		});
		dlg_adjustSubRole.addKeyListener(27, dlg_adjustSubRole.hide, dlg_adjustSubRole);
		dlg_adjustSubRole.addButton('${eoms:a2u('保存调整')}', function(){
			doAdjustSubRoles('adjustSubRole-form');
		});
		dlg_adjustSubRole.addButton('${eoms:a2u('关闭')}', dlg_adjustSubRole.hide, dlg_adjustSubRole);
		
		// subrole viewer
		subRoleViewer = new Ext.JsonView("subRoles",
		'<div id="role-user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>${eoms:a2u("尚未选择任何子角色。")}</div>'
		}
		);
		var s = '[]';
		subRoleViewer.jsonData = eoms.JSONDecode(s);
		subRoleViewer.refresh();

	});
	
	function doSearch(){
		if($('sUserName').value.trim()==""){
			alert("${eoms:a2u('请输入用户名关键字')}");
			return;
		}
		searcher.load();
	}
	function doCopySubRoles(form){
		Ext.Ajax.request({
			method:'post',
			url: 'tawSystemUserRefRoles.do?method=xsaveUserRefRole',
			success: function(x){
				eoms.log(x.responseText);
				var rt = eoms.JSONDecode(x.responseText);
				alert(rt.message);
				dlg.hide();
			},
			params: Ext.Ajax.serializeForm(form)
        });		
	}
	function doAdjustSubRoles(form){
		Ext.Ajax.request({
			method:'post',
			url: 'tawSystemUserRefRoles.do?method=xsaveAdjustSubRoles&id=' + AppSimpleTree.selected.id,
			success: function(x){
				eoms.log(x.responseText);
				var rt = eoms.JSONDecode(x.responseText);
				// a judgement needed at this place for output rt.message when fail
				// refresh dialog after save
				adjustSubRole(AppSimpleTree.selected);
			},
			params: Ext.Ajax.serializeForm(form)
        });		
	}
	
	//代维部门的字段
	dwFields = ['inCompany','inCity','inDistrict','education','birthday'
	,'techTitle','IDNumber','postState','photoInfo','staffLabel'
	,'isMarried_text','jobType','post','graduateSchool','specialties'
	,'politicalStatus','workPeriod','personDiscription','startWorkTime','toDeptTime'
	,'certificationInfo'];
	
	//显示或隐藏代维部门字段
	function toggleDWFields(isShow){
		Ext.each(dwFields,function(id){
			if(isShow){
				Ext.getCmp(id).hide();
			}
			else{
				Ext.getCmp(id).hide();
			}
		});
	}
	
	var checkflag = "false";
	function chose(){
		var objs = document.getElementsByName("subRoleIdArray");
		if(checkflag=="false"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = true;
      			checkflag="true";
			}
		}else if(checkflag=="true"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = false;
      			checkflag="false"
			}
		}
	}
</script>

<div id="headerPanel" class="app-header">
	<img src="${app}/styles/default/images/header-user.gif" align="left">
	<div style="padding-top:6px">
	<form onsubmit="javascript:doSearch();return false;">
	${eoms:a2u('查询用户名')}: <input type="text" name="username" id="sUserName" class="text">
	${eoms:a2u('查询部门名称')}: <input type="text" name="post" id="sDeptName" class="text">
	<input type="button" value="${eoms:a2u('查询')}" class="button" onclick="javascript:doSearch();" style="vertical-align:middle">
	</form>
	</div>
</div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>
			${eoms:a2u('功能说明')}
		</dt>
		<dd>
			${eoms:a2u('管理用户的基本信息，以及配置该用户所属部门、机房等；同时可分配或修改用户所拥有的权限。超级管理员和二级管理员有权限对用户管理操作。')}
		</dd>
	</dl>
	<br/>
	<dl>
		<dt>
			${eoms:a2u('在部门下添加一个人员')}
		</dt>
		<dd>
			${eoms:a2u('在树图中的部门上点击右键，并选择"新建人员"')}
		</dd>
		<dt>
			${eoms:a2u('修改一个人员的信息')}
		</dt>
		<dd>  
			${eoms:a2u('在树图中的人员上点击右键，并选择"修改"')}
		</dd>
		<dt>
			${eoms:a2u('删除人员')}
		</dt>
		<dd>
			${eoms:a2u('在树图中的人员上点击右键，并选择"删除此人员"')}
		</dd>
	</dl>
</div>

<div id="searchPanel"></div>
<div id="searchGrid"></div>

<div id="treePanel">
	<div id="treePanel-tb" class="tb"></div>
	<div id="treePanel-body"></div>
</div>

<div id="formPanel">
	<div id="formPanel-body" class="app-panel"></div>
</div>

<div id="subroleList-dlg" style="visibility:hidden;">
    <div class="x-dlg-hd">${eoms:a2u('将子角色复制给其他人员')}</div>
    <div class="x-dlg-bd">
        <div class="x-dlg-tab" title="${eoms:a2u('所属子角色列表')}">
            <div class="inner-tab">
            	<form id="subroleList-form" onsubmit="javascript:doCopySubRoles(this);return false;">
            	<br/>
            	${eoms:a2u('复制给')} : <input type="text" name="moveToName" class="text" id="moveToName" readonly/>
        		<input type="hidden" name="userId" id="userId"/>
        		<input type="button" class="button" id="selMoveTgt" value="${eoms:a2u('选择目标人员')}"/><br/><br/>
            	  <table class="listTable" id="subroleList-table">
            	    <tbody></tbody>
            	  </table>
            	</form>
            </div>
        </div>

    </div>
</div>

<div id="adjustSubRole-dlg" style="visibility:hidden;">
    <div class="x-dlg-hd">${eoms:a2u('调整人员所属子角色')}</div>
    <div class="x-dlg-bd">
        <div class="x-dlg-tab" title="${eoms:a2u('所属子角色列表')}">
            <div class="inner-tab">
            	<form id="adjustSubRole-form" onsubmit="javascript:doAdjustSubRoles(this);return false;">
            	<br/>
            	${eoms:a2u('需要添加的子角色列表')} : <div id="subRoles" class="viewer-list"></div>
        		<input type="hidden" name="subRoleId" id="subRoleId"/>
        		<input type="button" class="button" id="selSubRole" value="${eoms:a2u('选择子角色')}"/><br/><br/>
            	  <table class="listTable" id="adjustSubRole-table">
            	    <tbody></tbody>
            	  </table>
            	</form>
            </div>
        </div>

    </div>
</div>


<%@ include file="/common/footer_eoms.jsp"%>
