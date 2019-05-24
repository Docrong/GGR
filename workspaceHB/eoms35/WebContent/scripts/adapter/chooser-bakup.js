/**
 * @author mios
 */
/************************************
 * 用于选择角色的弹出窗口
 ************************************/
var Chooser = function(config){
	var dlg,layout,grid,gridCModel,gridData;
	this.pageSize = 10;
	this.pageIndexTo = 0;
    this.paramChanged = false;
    this.chosen = new Ext.util.MixedCollection();
    this.onFilterMode = true;
    this.filterElements = new Ext.util.MixedCollection();
    this.gridAction = eoms.appPath+"/role/tawSystemRoles.do?method=xGetSubRoleList";
	
	Ext.apply(this,config);
	
	this.showBtn = Ext.get(this.btnId);
	
	//filterParams:{'class1':'main','class2':'second'}
	for(var p in this.filterParams){
		if(Ext.get(this.filterParams[p])){
			var el = Ext.get(this.filterParams[p]);
			this.filterElements.add(p,el);
			el.on('change',this.onParamChange,this);		
		}
	}
	
	//选择框
	dlg = new Ext.LayoutDialog(Ext.id(), {
    	autoCreate:true,modal:true,width:600,height:500,
    	shadow:false,minWidth:300,minHeight:300,proxyDrag: true,
		south:{
			initialSize: 100,
			autoScroll:true,
			titlebar: true
		},
		center: {
			autoScroll:false,
			titlebar: true
		}
    });
    
	dlg.setTitle('选择框');
	dlg.addKeyListener(27, dlg.hide, dlg);
    this.ok = dlg.addButton('确定', this.output, this);
    dlg.addButton('清空', this.reset, this);
    dlg.setDefaultButton(dlg.addButton('关闭', dlg.hide, dlg));
    this.dlg = dlg;
	var layout = dlg.getLayout();
	
	//过滤器
	this.tb = new Ext.Toolbar(this.dlg.body.createChild({tag:'div'}));
	if(this.customDept){
		this.sortSelect = Ext.DomHelper.append(this.dlg.body.dom, {
			tag:'select',
			disabled:'disabled',
			children:[{
				tag:'option',selected:'selected',
				value:this.customDept.value,
				html:this.customDept.text
			}]
		}, true);		
	}
	else if(this.linkDepts){
		this.sortSelect = Ext.DomHelper.append(this.dlg.body.dom, {
			tag:'select', 
			children:[{
				tag:'option',selected:'selected',
				value:'',
				html:'请选择部门'
			}]
		}, true);
		Ext.each(this.linkDepts,function(d){
			Options.add(this.sortSelect.dom.id,d.text,d.value);
//			Ext.DomHelper.append(this.sortSelect.dom, {
//				tag:'option',
//				value:d.value,
//				html:d.text
//			}, true);
		},this);
	}
	this.sortSelect.on('change', this.load, this, true);
	
	this.tb.add('派往部门:', this.sortSelect.dom);
	
	this.typeFilter = Ext.DomHelper.append(this.dlg.body.dom, {
			tag: "input", type: 'checkbox', checked:'checked', autocomplete: "off"
	}, true);
	this.typeFilter.on('click', this.doFilter, this, true);
	this.tb.add('-',this.typeFilter.dom,'选中此项用页面上的设置过滤列表');
	
	//列表
    gridEl = dlg.body.createChild({tag:'div'});

	function renderTopic(value, p, record){
		var html = '<em>'+value+'</em><br/>人员：';
		var userList = record.data['userList'];
		if(userList.length>0){
		  Ext.each(userList,function(user){
			html += ''+user.username+'; ';
		  });
		}
		else{
			html += '(空)';
		}
		return html;
	}
	gridCModel = new Ext.grid.ColumnModel([{
		id:'subRoleName',
		header: "角色名称",
		dataIndex: 'subRoleName',
		renderer:renderTopic,
		width:400,
		css: 'white-space:normal;'
	}]);
	
	gridData = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
    		url: this.gridAction
		}),

		reader: new Ext.data.JsonReader({
    		root: 'rows',
    		totalProperty: 'total',
    		id: 'id'
		}, ['id','subRoleName','type1','userList']),

		remoteSort: true
	});
	
	gridData.on('beforeload', function() {
        gridData.baseParams = {
			isShowUser:'true',
            pageIndex:this.pageIndexTo,
            pageSize:this.pageSize,
            deptId:this.sortSelect.dom.value,
            id:this.roleId
        };
        if(this.onFilterMode){
        	for(var p in this.filterParams){
        		if(Ext.getDom(this.filterParams[p])){
        			gridData.baseParams[p] = Ext.getDom(this.filterParams[p]).value;
        		}
        	}
		}
    },this);
    
	grid = new Ext.grid.Grid(gridEl, {
		ds: gridData,
		cm: gridCModel,
		autoExpandColumn: 'subRoleName',
		trackMouseOver: true,
		loadMask: true
	});

	grid.render();
	var gridFoot = grid.getView().getFooterPanel(true);

    //分页
    var paging = new Ext.PagingToolbar(gridFoot, gridData, {
        pageSize: this.pageSize,
        displayInfo: true,
        displayMsg: '显示 {0} - {1}项 共 {2}项',
        emptyMsg: "没有找到记录"
    });
     
    paging.next.on('click',function(){
    	this.pageIndexTo=paging.getPageData().activePage+1;
    },this);
    paging.prev.on('click',function(){
    	this.pageIndexTo=paging.getPageData().activePage-1;
    },this);
    paging.first.on('click',function(){
    	this.pageIndexTo=1;
    },this);
    paging.last.on('click',function(){
    	this.pageIndexTo=paging.getPageData().pages;
    },this);
    paging.field.on('keyup',function(){
    	this.pageIndexTo=this.dom.value;
    },this);
    
    this.gridCModel = gridCModel;
    this.gridData = gridData;
    this.grid = grid;
    this.grid.on('rowdblclick', this.doChoose, this);
             
    layout = dlg.getLayout();
    layout.beginUpdate();
    gridPanel = layout.add('center', new Ext.GridPanel(grid, {
    	title: '可派发角色列表',
    	toolbar: this.tb,
    	fitToFrame:true
    }));
    this.chosenPanel = layout.add('south', new Ext.ContentPanel(Ext.id(), {autoCreate : true,title: '已选择:'}));
    layout.endUpdate();
    
    this.chosenEl = this.chosenPanel.getEl();
    this.chosenView = new Ext.JsonView(this.chosenEl,
    	'<div id="role-{id}" class="viewlistitem-role">{name};</div>',
    	{ 
    		singleSelect: true,
    		emptyText : '<div>(空)</div>'
    	}
	);
	this.chosenView.jsonData = [];
	this.chosenView.on('dblclick', this.remove, this);
    this.showBtn.on('click', this.show, this);
    this.loaded = false;
}
Chooser.prototype = {
	show:function(){
		this.dlg.show(this.showBtn.dom);
		if(this.onFilterMode && this.paramChanged) this.load();
		else if(this.customDept && !this.loaded)this.load();
	},
	
	load:function(){
		if(this.sortSelect.dom.value=='')return;
		this.gridData.load({
			params:{start:0,limit:this.pageSize},
			callback:this.onLoad.createDelegate(this)
		});
	},
	
	onLoad:function(){
		this.loaded = true;
		this.paramChanged = false;
	},
	
	onParamChange:function(){
		this.paramChanged = true;
		this.setHeader();
	},
	setHeader:function(){
		if(!this.onFilterMode){
			this.gridCModel.setColumnHeader(0,'角色名称');
		}
		else {
			var s='(按部门';
			this.filterElements.each(function(el){
				var select = el.dom;
				if(select.value!=''){
					s+='-'+select.options[select.selectedIndex].text
				}
			},this);
			s+='查询)';
			this.gridCModel.setColumnHeader(0,'角色名称'+s);		
		}	
	},
	doChoose : function(grid, rowIndex, e){
		var record = this.gridData.getAt(rowIndex);
		var id = record.get('id');
		var name = record.get('subRoleName');
		if(this.chosen.containsKey(id)){
			alert('该角色已被选择了。');
		}
		else{
			this.chosenView.jsonData.push({id:id,name:name});
			this.chosen.add(id,record);
			this.refresh();
		}
	},
	
	output : function(){
		var showEl = Ext.get(this.showElId);
		showEl.dom.innerHTML = this.chosenEl.dom.innerHTML;
		var saveEl = Ext.getDom(this.saveElId);
		var idString = [];
		this.chosen.each(function(c){
			idString.push(c.get('id'));
		});
		saveEl.value = idString.toString();
		this.dlg.hide();
	},
	remove : function(vw, index, node, e){
		this.chosenView.jsonData.splice(index, 1);
		this.chosen.removeAt(index);	
		this.refresh();
	},
	reset : function(){
		this.chosenView.jsonData = [];
		this.chosen.clear();
		this.refresh();
	},
	clear : function(){
		this.reset();
		this.gridData.removeAll();
		var showEl = Ext.get(this.showElId);
		showEl.dom.innerHTML = "<div>(空)</div>";
		var saveEl = Ext.getDom(this.saveElId);
		saveEl.value = "";
	},
	refresh : function(){
		this.chosenView.refresh();
		this.chosenPanel.setTitle("已选择："+this.chosen.getCount()+"个角色");		
	},
	doFilter : function(e,obj){
		this.onFilterMode = obj.checked;
		this.setHeader();
		this.load();
	},
	setSortSelect : function(arrDepts){
		if(arrDepts.length>0){
			Options.loadJSON(this.sortSelect.dom.id, arrDepts, true);		
			this.clear();
		}
	}
};