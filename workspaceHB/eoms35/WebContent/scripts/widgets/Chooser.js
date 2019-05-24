/**
 * 用于选择派发对象的弹出窗口
 * @class
 * @requires Ext
 * @param {Object} config 设置参数对象
 * @cfg {Srting} id 控件id
 * @cfg {Array} category 选择分类的设置
 * @cfg {Array} [panels] 树图面板的设置
 * @cfg {Array} [data] 已选数据
 * @cfg {Object} [config] 特殊设置
 * @example
 *  Ext.onReady(function() {
 *    chooser_test = new Chooser({
 *      id : "test",
 *      category : [
 *        {id: "send", text: "派发", childType: "user,dept", limit: 3,"allowBlank": false}
 *      ]
 *    });
 *  });
 * @property {String} type 控件的类型,设置为"role",则为工单派发选择子角色专用类型
 * @property {Object} category 
 *   树图的分类节点设置,如派发、抄送等,每个分类含有如下属性和方法:
 *   <ul class="list">
 *     <li> category[n].node 第n个分类的树图节点</li>
 *     <li> category[n].btn 第n个分类的选择按钮</li>
 *     <li> category[n].hidden 第n个分类是否隐藏</li>
 *     <li> category[n].show() 显示第n个分类</li>
 *     <li> category[n].hide() 隐藏第n个分类</li>
 *     <li> category[n].attr() 获取或设置第n个分类的属性值<br/>
 *          如设置第1个分类的最大可选可数为2 ： chooser_test.category[0].attr('limit',2);</li>
 *   </ul>
 */
var Chooser = function(config) {
	var xt = Ext.tree;
	var CP = Ext.ContentPanel;
	/** 是否显示条件过滤(主面板)
	 * @type Boolean */
	this.showFilter = false;
	/** 是否返回JSON数据格式
	 * @type Boolean */
	this.returnJSON = false;
	/** 是否设置组长
	 * @type Boolean */
	this.showLeader = false;
	/** 已选择项,保存已选择节点的属性,即node.attributes 
	 * @type Ext.util.MixedCollection */
	this.chosen = new Ext.util.MixedCollection();
	this.defaultChildType = ''; //默认不限节点类型
	Ext.apply(this, config, config.config);
	/** 树图面板集合
	 * @type Array */
	this.panels = this.panels || [{text : '部门与人员',dataUrl : '/xtree.do?method=userFromDept'}];
	/** 控件容器元素
	 * @type Ext.Element */
	this.body = Ext.get(this.id);	
	this.body.down("input[type='button']").on('click', this.show, this);

	// 主选择框
	var dlg = new Ext.LayoutDialog(this.id+'-chooser', {
		title: this.title || '选择派发对象',
		width : 750,
		height : 450,
		modal : false,
		shadow : false,
		collapsible : false,
		proxyDrag : false,
		resizable : false
	});
	
	// 创建中间选择按钮容器
	var mainBtnEl = dlg.body.createChild({
		tag : 'table',
		style : 'width:100%;height:100%',
		cn : {
			tag : 'tr',
			cn : {
				tag : 'td',
				align : 'center',
				id : this.id + '-chooser-centercell'
			}
		}
	});

	// 设置布局	
	dlg.layout = Ext.BorderLayout.create({
		west : {
			initialSize : 340,
			autoScroll : true,
			titlebar : true
		},
		center : {
			autoScroll : false,
			titlebar : false,
			panels : [new CP(mainBtnEl)]
		},
		east : {
			autoScroll : true,
			titlebar : true,
			tabPosition : 'top',
			initialSize : 340,
			panels : [
				new CP(this.id + 'chsTree', {
					autoCreate : true,
					title : '已选择项目:'
				})
			]
		}
	
	},dlg.body.dom);
	
	dlg.addKeyListener(27, this.hide, this);
	dlg.addButton('确定', this.output, this);
	dlg.setDefaultButton(dlg.addButton('关闭', this.hide, this));
	/** 控件窗口对象
	 * @type Ext.LayoutDialog */
	this.dlg = dlg;
	/** 控件布局对象
	 * @type Ext.BorderLayout */
	this.layout = dlg.layout;
	/** 控件左侧布局对象
	 * @type Ext.LayoutRegion */
	this.west = this.layout.getRegion("west");
	/** 控件中间布局对象
	 * @type Ext.LayoutRegion */
	this.center = this.layout.getRegion("center");
	/** 控件右侧布局对象
	 * @type Ext.LayoutRegion */
	this.east = this.layout.getRegion("east");
	
	// 修正选择按钮区域的样式
	this.center.el.addClass("chooser-bg");	
	
	if(this.type=="role"){
		this.defaultChildType = 'subrole';
		this.panels.unshift({
			type : 'rolePanel',
			text : '可选子角色',
			dataUrl : '/role/tawSystemRoles.do?method=xGetSubRoleNodesFromArea',
			params : {roleId:this.roleId}
		});
		this.panels.push({
			type:'search',
			text : '搜索子角色',
			dataUrl : '/role/tawSystemRoles.do?method=xSearchSubRoleNodes',
			params : {roleId:this.roleId},
			hidden : true
		});
	}
	// 通过panels创建备选面板
	Ext.each(this.panels,function(p){
		var newp = this.west.addTreePanel(p);
		newp.tree.on('dblclick',this.doChoose,this);
		if(p.type=="search"){
			this.searchPanel = newp;
		}
		if(p.type=="rolePanel"){
			this.rolePanel = newp;
		}
	},this);
	this.west.showPanel(0);
	
	// 创建右侧已选择树图
	this.category.tree = new xt.TreePanel(this.id + 'chsTree', { 
		animate : true,
		enableDD : false,
		containerScroll : true,
		lines : false,
		rootVisible : false
	});
	this.category.root = new xt.TreeNode({nodeType : 'root', expanded:true});
	this.category.tree.setRootNode(this.category.root);
	this.category.tree.render();
	
	this.chosenCtxMenu = new Ext.menu.Menu();
	this.category.tree.on('contextmenu', this.onChosenCtx, this);
	this.category.tree.el.swallowEvent('contextmenu', true);
	this.category.tree.getSelectionModel().on('selectionchange', function(sm,node) {
		this.clearSelction('west');
		if(node)node.select();
	}, this);
	this.category.tree.on('dblclick', this.remove, this);
	this.categoryTreeSM = this.category.tree.getSelectionModel();

	// 根据category创建已选择项目分组的树图节点,创建选择按钮
	
	/** 右侧已选项目分类的树图节点集合
	 * @type Object */
	this.categoryNodes = {};
	var showCate = function(c){
		if(c.hidden === false)return;
		c.btn.show();			
		c.node = this.createCNode(c);	
		var refnode = this.category[c.index+1] ? this.category[c.index+1].node : null;
		this.category.root.insertBefore(c.node, refnode);
		this.categoryNodes[c.id] = c.node;
		c.hidden = false;
	};
	var hideCate = function(c){		
		if(c.hidden == true || this.category.root.childNodes.length<=1)return;
		c.btn.hide();
		this.category.root.removeChild(c.node);		
		this.categoryNodes[c.id] = null;
		c.node = null;
		c.hidden = true;
	};
	var attrCate = function(c,attr,value){
		if(arguments.length==3){
			c.node.attributes[attr] = value;
		}
		else{
			return c.node.attributes[attr];
		}	
	};
	Ext.each(this.category, function(item,index) {
		var node = this.createCNode(item);
		
		var cell = Ext.get(this.id + '-chooser-centercell');
		var btn = cell.createChild({
			tag : 'input',
			type : 'button',
			value : item.text,
			style : 'margin:20 0;display:block',
			cls : 'button'
		});
		btn.on('click', function() {
			this.doChoose(item.id)
		}, this);
		
		//chooser所属的页面form
		this.pageForm = btn.dom.form;
		
		item.node = node;
		item.btn = btn;
		item.index = index;
		item.show = showCate.createDelegate(this,[item]);
		item.hide = hideCate.createDelegate(this,[item]);
		item.attr = attrCate.createDelegate(this,[item],0);
		item.hidden = false;
		
		//根据category创建隐藏域
		var alt = item.allowBlank==false ? "allowBlank:"+item.allowBlank+",vtext:'"+item.vtext+"'" : "";
		item['hiddenEl'] = this.body.createChild({tag:'input',type:'hidden',alt:alt,name:item.id});
		item['hiddenEl_nodeType'] = this.body.createChild({tag:'input',type:'hidden',name:item.id+'Type'});
		item['hiddenEl_leaderId'] = this.body.createChild({tag:'input',type:'hidden',name:item.id+'Leader'});
		item['hiddenEl_JSON'] = this.body.createChild({tag:'input',type:'hidden',name:item.id+'JSON'});
	}, this);
	
	/** 已选择项目确定后在父页面上的显示
	 * @type Ext.JsonView */
	this.chosenView = new Ext.JsonView(
			this.id + '-chooser-show',
			'<div>{categoryName}:<div class="viewlistitem-{nodeType}" style="display:inline">{text} {info}</div></div>',
			{
				singleSelect : true,
				selectedClass : "",
				emptyText : '<div>未选择项目</div>',
				scope : this
			});
	this.chosenView.jsonData = [];
	this.chosenView.refresh();

	if(this.type=="role"){
		this.showFilter = true;
		if(this.showFilter)this.initFilterDlg(); //过滤条件窗口
		if(this.showLeader)this.initSetLeader(); //设置组长
	}

};
Chooser.prototype = {
	/** 显示选择窗口 */
	show : function() {
		this.dlg.show();
	},
	/** 隐藏选择窗口 */
	hide : function() {
		this.dlg.hide();
		if(this.showFilter){
			this.filterDlg.hide();		
		}
	},
	/** 隐藏按钮 */
	hideBtn : function(){
		this.body.down("input[type='button']").setDisplayed(false);
	},
	/** 屏蔽并隐藏整个UI,包括隐藏域,按钮和viewer */
	disable : function(){
		eoms.form.disableArea(this.id,true);		
	},
	/** 激活并显示整个UI,包括隐藏域,按钮和viewer */
	enable : function(){
		eoms.form.enableArea(this.id,true);
	},
	clearSelction : function(region){
		var re = this.layout.getRegion(region)
		var ap = re.getActivePanel();
		ap.tree.getSelectionModel().clearSelections();
	},
	/**
	 * 设置面板的树图url地址，并刷新
	 * @param {String} url 树图action地址
	 * @param {String} [rootId=-1] 树图根节点id
	 * @param {Number} [index=0] 面板序数
	 */
	setDataUrl : function(url,rootId,index){
		if(!index) index = 0;
		if(!rootId) rootId = -1;
		var tree = this.west.getPanel(index).tree;
		var treeLoader = tree.getLoader();
		var root = tree.getRootNode();
		if((eoms.appPath+url)==treeLoader.dataUrl && root.id==rootId){
			return;
		}
		treeLoader.dataUrl = eoms.appPath + url;
		this.reset();
		tree.getRootNode().id = rootId;
		tree.getRootNode().reload();
	},
	/**
	 * 已选择项目的右键菜单
	 * @param {Ext.Node} node 树图节点
	 * @param {Event} e 右键事件对象
	 */
	onChosenCtx : function(node, e) {
		node.select();
		//设置组长
		if (this.showLeader && node.attributes.nodeType == 'user' && node.parentNode.attributes.nodeType == 'subrole') {
			this.chosenCtxMenu.showAt(e.getXY());
		}
	},
	getKey : function(o){
		return o.nodeType + ':' + o.id;
	},
	/**
	 * 根据设置创建一个分类节点
	 * @param {Object} c 分类节点设置
	 * @return {Ext.Node} node
	 */
	createCNode : function(c){	
		var nodeText, childType = c.childType || this.defaultChildType, childTypeText = '';

		if(childType==''){ // 不限节点类型
			nodeText = c.text;
		}
		else{
			var childTypeArr = [];
			Ext.each(childType.split(","),function(item){
				childTypeArr.push(Local.nodeType[item]);
			})
			childTypeText = childTypeArr.join("或");
			nodeText = c.text + ' (只能选择' + childTypeText + ')';		
		}

		var node =  new Ext.tree.TreeNode({
			categoryId : c.id,
			categoryName : c.text,
			text : nodeText,
			iconCls : 'folder',
			cls : 'cmp',
			expanded : true,
			nodeType : 'category',
			childType : childType,
			childTypeText : childTypeText,
			limit : c.limit || 1
		});	
		
		this.category.root.appendChild(node);
		this.categoryNodes[c.id] = node;
		return node;
	},
	//private 执行选择动作
	doChoose : function(categoryId) {
		var r = this.layout.getRegion("west").getActivePanel();
		var selectNode = r.tree.getSelectionModel().selNode;
		var node = selectNode || this.categoryTreeSM.selNode;
		if(node==null)return;
		//删除
		if(node.getOwnerTree()==this.category.tree){
		  this.remove(node);
		  return;
		}
		//添加
		var nodeInfo = Ext.apply({},node.attributes);
		if (nodeInfo.nodeType=='root') {
			return;
		}		
		if (nodeInfo.nodeType=='subrole' && nodeInfo.user && nodeInfo.user.length == 0) {
			alert('该角色下没有人员，请选择其他角色。');
			return;
		}
		if (this.chosen.containsKey(this.getKey(nodeInfo))) {
			alert('该项目已被选择了。');
			return;
		}
		
		var c = this.categoryNodes[categoryId] || this.category.root.firstChild;
		var cAttr = c.attributes;
		
		if(cAttr.limit !== 'none' && cAttr.limit !== -1 && c.childNodes.length >= parseInt(cAttr.limit)){		
			alert(cAttr.categoryName+"中最多可以选择"+cAttr.limit+"个");
			return;
		}
		
		if (cAttr.childType != '' && !cAttr.childType.hasSubString(nodeInfo.nodeType,",")) {
			alert(cAttr.categoryName+"中只能选择"+cAttr.childTypeText);
			return;
		}
		
		nodeInfo['categoryId'] = cAttr.categoryId;
		nodeInfo['categoryName'] = cAttr.categoryName;
		
		this.add(nodeInfo,this.category.root.firstChild, true);
		this.refresh();
	},
	/**
	 * 添加已选节点数据
	 * @param {Object/Array} nodeInfos 一个节点的信息或多个节点的信息数组
	 * @param {Ext TreeNode} [defaultCtgNode] 缺省的categoryNode
	 * @param {Boolean} [isAsync] 是否创建动态节点
	 */
	add : function(nodeInfos, defaultCtgNode,isAsync){	
		Ext.each([].concat(nodeInfos),function(nodeInfo){
			if(!this.categoryNodes[nodeInfo.categoryId] && !defaultCtgNode){
				return;
			}
			var cnode = this.categoryNodes[nodeInfo.categoryId] || defaultCtgNode;
			nodeInfo.categoryName = cnode.attributes.categoryName;
			nodeInfo.iconCls = nodeInfo.iconCls || nodeInfo.nodeType;
			var newNode = (isAsync && !nodeInfo.leaf) ? new Ext.tree.AsyncTreeNode(nodeInfo) : new Ext.tree.TreeNode(nodeInfo);
			if(nodeInfo.user){
				Ext.each(nodeInfo.user,function(child){
					newNode.appendChild(new Ext.tree.TreeNode(child));
				});
			}
			cnode.appendChild(newNode);
			cnode.expand();
			this.chosen.add(this.getKey(nodeInfo), nodeInfo);
		},this);
	},
	/**
	 * 删除一个已选节点
	 * @param {Ext.tree.TreeNode} node
	 */
	remove : function(node) {
		if (node.parentNode.attributes.nodeType == 'category') {
			node.parentNode.removeChild(node);
			this.chosen.removeKey(this.getKey(node.attributes));
			this.refresh();
		}
	},
	refresh : function() {
		// this.chosenView.refresh();
		// this.chosenPanel.setTitle("已选择："+this.chosen.getCount()+"个角色
		// (双击删除,右键指定组长)");
	},
	/**
	 * 将选中的结果更新到页面上，并把选中的值填入隐藏域
	 */
	update : function(){
		this.chosenView.jsonData = [];
		this.chosen.each(function(nodeInfo) {
			//如果未指定组长，则设定leaderId为选中对象的id
			if(!nodeInfo.leaderId || nodeInfo.leaderId==""){
				nodeInfo.leaderId = nodeInfo.id;
			}
			//如果有leaderName，则info为组长信息
			nodeInfo.info = nodeInfo.leaderName ? '(组长:'+nodeInfo.leaderName+')' : '';

			this.chosenView.jsonData.push(nodeInfo);
		},this);

		this.chosenView.refresh();
		
		function toStr(o,isShowLeader){
			var str = '{'
				+'id:\''+o.id+'\''
				+',nodeType:\''+o.nodeType+'\''
			if(o.nodeType=='subrole' && isShowLeader && o.leaderId){
				str += ',leaderId:\''+o.leaderId+'\'';
			}
			str += '}';
			return str;
		}
		
		Ext.each(this.category, function(c) {
			var arrId = [],arrNodeType = [], arrLeaderId = [], arrJSON = [];
			this.chosen.each(function(nodeInfo) {
				if (nodeInfo.categoryId == c.id) {
					arrId.push(nodeInfo.id);
					arrNodeType.push(nodeInfo.nodeType);
					arrLeaderId.push(nodeInfo.leaderId);
					if(this.returnJSON){
						arrJSON.push(toStr(nodeInfo,this.showLeader));				
					}
				}
			}, this);
			
			c['hiddenEl'].dom.value = arrId.toString();
			c['hiddenEl_nodeType'].dom.value = arrNodeType.toString();
			c['hiddenEl_leaderId'].dom.value = arrLeaderId.toString();
			if(this.returnJSON){
				c['hiddenEl_JSON'].dom.value = "[" + arrJSON.toString() + "]";
			}
					
		}, this);	
	},
	//private 向父页面输出选择结果
	output : function() {
		var valid = true;
		this.chosen.each(function(i){
			//显示组长 且为虚拟组 则 必选一个组长
//			if(this.showLeader && this.isVirtual && i.nodeType=="subrole" && (!i.leaderId || i.leaderId=="")){
//				 alert("未指定组长！\n请为 "+i.name+" 指定一个组长：\n在该组的人员上单击右键，选择‘指定组长’");
//				 valid = false;
//				 return;
//			}
		},this);
		
		if(!valid) return;
		
		this.update();
		this.hide();
	},
	//private 初始化设置组长
	initSetLeader : function(){
		this.chosenCtxMenu.add({
			id : 'newnode',
			text : '指定组长',
			cls : 'new-mi',
			scope : this,
			handler : this.setLeader
		});
		
		//this.chosenView.leaderStore 在view中保存已设置的组长信息：
		//{
		//   '子角色1id':{'id':'组长id','name':'组长name'},
		//   '子角色2id':{'id':'组长id','name':'组长name'},
		//   ...
		//}
		this.chosenView.leaderStore = {};
		this.chosenView.prepareData = function(data) {
			switch (data.nodeType) {
				case 'subrole':
					Ext.applyIf(data,this.leaderStore[data.id]);
					break;
				default:
					break;
			}
			
			return data;
		};
	},
	//private 设置组长
	setLeader : function() {
		var userNode = this.categoryTreeSM.selNode;
		var subroleNode = userNode.parentNode;
		var subroleNodeId = subroleNode.attributes.id;
		var key = this.getKey(subroleNode.attributes);
		
		if(userNode.attributes.nodeType!='user' || subroleNode.attributes.nodeType!='subrole')return;
		
		this.chosenView.leaderStore[subroleNodeId] = {
			leaderId : userNode.attributes.id,
			leaderName : userNode.attributes.name
		};
		
		Ext.apply(this.chosen.get(key),this.chosenView.leaderStore[subroleNodeId]);
		
		//刷新人员列表组长状态
		subroleNode.eachChild(function(node) {
			node.ui.iconNode.className ="x-tree-node-icon user";
		});
		userNode.ui.iconNode.className ="x-tree-node-icon leader";
	},
	/**
	 * type="role" 模式下重新设置大角色roleId,刷新可选子角色
	 * @param {String} roleId
	 */ 
	setRoleId : function(roleId){
		if(this.type != 'role' || this.roleId==roleId)return;
		this.roleId = roleId;
		if(this.rolePanel){
			this.rolePanel.tree.getLoader().baseParams.roleId = roleId;
			this.rolePanel.tree.root.reload();
		}
		if(this.searchPanel){
			this.searchPanel.tree.getLoader().baseParams.roleId = roleId;
		}
		Ext.Ajax.request({
			method : 'post',
			url : eoms.appPath+"/role/tawSystemRoles.do?method=resetChooserRoleId",
			success : function(x) {
			  	var obj = eoms.JSONDecode(x.responseText);
				Ext.getDom(this.id+"-chooser-deptList").innerHTML = obj.filterHTML;
			},
			scope:this,
			params : "chooserId=" + this.id + "&roleId=" + this.roleId
		});
		this.reset();
	},
	/** 重置整个控件 */
	reset : function(){
		if(this.searchPanel){
			this.west.hidePanel(this.searchPanel);
		}
		this.west.showPanel(0);
        Ext.each(this.category.root.childNodes,function(cnode){
        	while(cnode.firstChild){
            	cnode.removeChild(cnode.firstChild);
        	}
        });
		this.chosen.clear();
		this.chosenView.jsonData = [];
		this.chosenView.refresh();
		Ext.select("input[type=hidden]",false,this.id).each(function(f){
			f.dom.value="";
		});
		this.refresh();
	},
	//private 初始化过滤器
	initFilterDlg : function() {
		var filterBtn = this.west.createToolButton('x-layout-search',this.showFilterDlg,this,'搜索');
		
		if (!this.filterDlg) {
			this.filterDlg = new Ext.BasicDialog(this.id + "-chooser-filter-dlg", {
				shadow : false,
				modal : false,
				autoTabs : true,
				resizable : true,
				collapsible : false,
				width : 400,
				height : 450
			});
			this.filterDlg.addKeyListener(27, this.filterDlg.hide,
					this.filterDlg);
			this.filterDlg.addButton('查找', this.doFilter, this);
			this.filterDlg.addButton('关闭', this.filterDlg.hide, this.filterDlg);
		}
	},
	//private 显示过滤器窗口
	showFilterDlg : function() {
		this.filterDlg.show();
		this.filterDlg.toFront();
	},
	//private 执行查询
	doFilter : function() {
		var p = {};
		Ext.select("input,select",false,this.id + "-chooser-filter-dlg").each(function(f){
			if(f.dom.type=="checkbox" && f.dom.checked){
				p[f.dom.name] = true;
			}
			else if(f.dom.tagName=="SELECT"){
				p[f.dom.name] = f.dom.value;
			}
		});
		this.west.showPanel(this.searchPanel);
		var st = this.searchPanel.tree;
		Ext.apply(st.getLoader().baseParams,p);
		st.root.reload();
		this.filterDlg.hide();
	}
};