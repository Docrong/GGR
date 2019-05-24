<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/layout/AppSimpleTree.js"></script>
<script type="text/javascript">
	var config = {
			
		/**************
		* Tree Configs
		**************/
		treeGetNodeUrl:"${app}/priv/tawSystemPrivOperations.do?method=getNodes",
		treeRootId:'-1',
		treeRootText:"${eoms:a2u('当前菜单')}",
		ctxMenu:{
			newNode:{text:"${eoms:a2u('新建下级菜单项')}"},
			delNode:{text:"${eoms:a2u('删除这个菜单项')}"}
		},//end of ctxMenu
		
		/**************
		* Form Configs
		**************/
		actions:{
			newNode : {
				btnText:"${eoms:a2u('保存')}",
				url:"${app}/priv/saveTawSystemPrivOperation.do?requestMode=ajax",
				init:function(){
					AppSimpleTree.setField("parentcode","code");
				}
			},
			getNode : {
				url:"${app}/priv/tawSystemPrivOperations.do?method=xget"
			},
			edtNode : {
				btnText:"${eoms:a2u('保存修改')}",
				url:"${app}/priv/tawSystemPrivOperations.do?method=xedit"
			},
			delNode : {
				url:"${app}/priv/tawSystemPrivOperations.do?method=xdelete"
			}
		},
		fieldOptions : {
			width:150
		},
		fields : [
	        new Ext.form.TextField({
	            fieldLabel: '<fmt:message key="tawSystemPrivOperationForm.name"/>',
	            name: 'name',
	            allowBlank:false
	        }),
	        
	        new Ext.form.TextField({
	            fieldLabel: '<fmt:message key="tawSystemPrivOperationForm.orderby"/>',
	            name: 'orderby',
	            allowBlank:false
	        }),
	
			new Ext.form.ComboBox({
	            fieldLabel: '<fmt:message key="tawSystemPrivOperationForm.isApp"/>',
	            hiddenName:'isApp',
	            store: new Ext.data.SimpleStore({
	                fields: ['typevalue', 'type'],
	                data : [
	        				['0', 'Module'],
	        				['1', 'Function'],
	        				['2', 'Button']
	        			   ]
	            }),
	            displayField:'type',
	            valueField:'typevalue',
	            editable:false,
	            mode: 'local',
	            triggerAction: 'all',
	            emptyText:'Please Select',
	            selectOnFocus:true,
	            allowBlank:false,
	            value:'1'
	        }),
	        // 新增按照某种方式登陆，0为eoms，1为wap
	        new Ext.form.ComboBox({
	            fieldLabel: '<fmt:message key="tawSystemPrivOperationForm.loginType"/>',
	            hiddenName:'loginType',
	            store: new Ext.data.SimpleStore({
	                fields: ['typevalue', 'type'],
	                data : [
	        				['0', 'EOMS'],
	        				['1', 'WAP']
	        			   ]
	            }),
	            displayField:'type',
	            valueField:'typevalue',
	            editable:false,
	            mode: 'local',
	            triggerAction: 'all',
	            emptyText:'Please Select',
	            selectOnFocus:true,
	            allowBlank:false,
	            value:'0'
	        }),
	        new Ext.form.TextField({
	            fieldLabel: "<fmt:message key='tawSystemPrivOperationForm.url'/>",
	            name: 'url'
	        }),
	new Ext.form.ComboBox({
            fieldLabel: '<fmt:message key="tawSheetSpecialForm.deleted"/>',
            hiddenName:'hided',
            store: new Ext.data.SimpleStore({
                fields: ['typevalue', 'type'],
                data : [
        				['0', '${eoms:a2u('显示菜单')}'],
        				['1', '${eoms:a2u('不显示菜单')}']
        			   ]
            }),
            displayField:'type',
            valueField:'typevalue',
            editable:false,
            mode: 'local',
            triggerAction: 'all',
            emptyText:'Please Select',
            selectOnFocus:true,
            allowBlank:false,
            value:'0',
            width:150
        }),
	        new Ext.form.TextArea({
	            fieldLabel: "<fmt:message key='tawSystemPrivOperationForm.remark'/>",
	            name: 'remark',
	            grow: true,
	            preventScrollbars:true
	        }),
	        /* Hidden Field
	        */
	        new Ext.form.HiddenField({name: 'id',id:'id'}),
	        new Ext.form.HiddenField({name: 'code',id:'code'}),
	        new Ext.form.HiddenField({name: 'parentcode',id:'parentcode'})
		], // end of fields
		
		/************************
		* Custom onLoad Functions
		*************************/	
		onLoadFunctions:function(){
		}
	}; // end of config

	AppSimpleTree.doDelNode = function(nodeData,node){
    		var params = "id="+nodeData.id;
    		if(typeof config.actions.delNode.customData == "function"){
    			params = config.actions.delNode.customData(nodeData);
    		}
    		
	    	//弹出一个确认框
	    	Ext.MessageBox.confirm("${eoms:a2u('确认')}", "${eoms:a2u('您所要删除的内容下可能含有子结点等重要信息，您确定删除吗？')}",
	    		function(btn){
	    			if(btn=="yes"){
				    	Ext.Ajax.request({
				    		method : 'post',
				    		url : config.actions.delNode.url,
				    		scope : AppSimpleTree,
				    		success: function(){
								if(node){
	            					//treesm.selectPrevious();
	            					node.parentNode.removeChild(node);
	        					}
	        					this.initRegion('east','helpPanel');
	        					this.reloadStatus.deleted = true;
	        					Ext.MessageBox.alert('提示',"${eoms:a2u('您成功删除了一个节点。')}");
				    		},
				    		failure: function() {
						    	Ext.MessageBox.alert('提示',"${eoms:a2u('删除失败')}");
							},
				    		params:params
				    	});   				
	    			}
	    		}
	    	);
    	};
	Ext.onReady(AppSimpleTree.init, AppSimpleTree);
</script>
<style type="text/css">
body{
	 background-image:none;
}
</style>
<div id="headerPanel" class="app-header"><img src="${app}/styles/${theme}/images/header-menu.gif"></div>
<div id="helpPanel" class="app-panel">
	<dl>
		<dt>${eoms:a2u('添加一个下级菜单项')}</dt>
		<dd>${eoms:a2u('在树图中的菜单项上点击右键，并选择"新建下级菜单项"')}</dd>
		<dt>${eoms:a2u('修改一个菜单项的信息')}</dt>
		<dd>${eoms:a2u('在树图中的菜单项上点击右键，并选择"修改"')}</dd>
		<dt>${eoms:a2u('删除菜单项')}</dt>
		<dd>${eoms:a2u('在树图中的菜单项上点击右键，并选择"删除这个菜单项"')}</dd>
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
