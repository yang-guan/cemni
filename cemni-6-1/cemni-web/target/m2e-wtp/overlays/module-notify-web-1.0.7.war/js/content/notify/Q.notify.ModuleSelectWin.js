Ext.ns('Q.notify');

/**
 * 菜单选择窗口
 */
Q.notify.ModuleSelectWin = function(cfg) {
	cfg = cfg || {};
	var tree = this.tree = this.createTree();
	Q.notify.ModuleSelectWin.superclass.constructor.call(this, {
		title:'菜单选择窗口',
		layout:"border",
		width:300,
		height:600,
		items:[tree],
		listeners:{
			hide:function() {
				this.destroy();
			}
		}
	});

	this.addEvents("select");

	if (Ext.isFunction(cfg.select)) {
		this.on("select", cfg.select);
	}
};

Ext.extend(Q.notify.ModuleSelectWin, Ext.ux.Window, {
	createTree:function() {
		var win = this;
		var tree = new Ext.ux.tree.TreePanel({
			id:'100001',
			region:'center',
			rootVisible:false,
			width:180,
			url:path_core + '/sys/Module_getPartTree.action?key={0}',
			treeType:TreeType.INFO,
			nodeName:'filter_EQ_module_moduleId',
			tbar:[{
					text:'选择',
					iconCls:'icon-add',
					handler:function() {
						var node = tree.getSelectionModel().getSelectedNode();
						if (node.isLeaf()) {
							win.fireEvent('select', node.id, node.text)
						} else {
							Q.tips(Q.color('请选择有效的菜单！'));
						}
					}
				}, {
					text:'取消',
					iconCls:'icon-cancel',
					handler:function() {
						win.hide();
					}
				}]
		});
		return tree;
	}
});
