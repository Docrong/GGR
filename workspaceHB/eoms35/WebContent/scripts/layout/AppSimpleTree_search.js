/**
 * 搜索
 * @version 0.1.2 080917
 */

var AppSearch = function() {
	// private
	var config, cm, ds, grid;

	// public
	return {
		init : function(cfg) {
			config = cfg;
			cm = new Ext.grid.ColumnModel(config.cm);

			ds = new Ext.data.JsonStore({
				url : config.url,
				root : 'rows',
				fields : config.fields
			});

			gridSM = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});
			grid = new Ext.grid.Grid('searchGrid', {
				ds : ds,
				cm : cm,
				enableColLock : true,
				autoWidth : true,
				selModel : gridSM
			});

			var layout = Ext.BorderLayout.create({
				center : {
					margins : {
						left : 3,
						top : 3,
						right : 3,
						bottom : 3
					},
					panels : [new Ext.GridPanel(grid)]
				}
			}, 'searchPanel');

			grid.on('dblclick', this.onDblClick, AppSimpleTree);
			grid.on('contextmenu', this.onCtxMenu, this);
			grid.getGridEl().swallowEvent('contextmenu', true);
			grid.render();
		},
		load : function() {
			AppSimpleTree.openPanelOfRegion('center', 'searchPanel');
			var params = {};
			for (var p in config.paramMapping) {
				params[p] = Ext.getDom(config.paramMapping[p]).value || '';
			}
			ds.load({params : params});
		},
		onCtxMenu : function(e) {
			AppSimpleTree.selected = gridSM.getSelected().data;
			var menu = AppSimpleTree.treeCtxMenu;
			var mis = menu.items;
			mis.get('newnode').hide();
			menu.showAt(e.getXY());
		},
		onDblClick : function(e) {
			var data = gridSM.getSelected().data;
			AppSimpleTree.doEdtNode(data);
		}
	}
};
