Ext.ns('Q.actment');
Q.actment.actualCostWin = function(config) {
	var _formPanel = this.formPanel = this.createFormPanel();
	this.addEvents("submit");

	var _config = this.config = Ext.apply({
		title : "实际花费",
		layout : "fit",
		items : [ _formPanel ],
		width : 300,
		height : 120
	}, config);

	Q.actment.actualCostWin.superclass.constructor.call(this,

	_config);
};

Ext.extend(Q.actment.actualCostWin, Ext.ux.Window, {
	listeners : {
		hide : function() {
			this.formPanel.getForm().reset();
		}
	},
	createFormPanel : function() {
		var _win = this;
		return new Ext.form.FormPanel({
			items : [ {
				xtype : "fieldset",
				layout : "column",
				border : false,
				defaults : {
					layout : "form",
					border : false,
					columnWidth : 1
				},
				items : [ {
					items : {
						fieldLabel : '实际花费<font color="red">*</font>',
						name : 'actualCost',
						xtype : "numberfield",
						allowBlank : false,
						anchor : '88%'
					}
				} ]
			} ],
			buttons : [ {
				text : $("button.save"),
				handler : function() {
					_win.doSubmit();
				}
			}, {
				text : $("button.return"),
				handler : function() {
					_win.hide();
				}
			} ]
		});
	},
	doSubmit : function() {
		var isValid = this.formPanel.form.isValid();
		if (!isValid) {
			return;
		}
		var _win = this;
		Q.confirm($("message.save.confirm"), {
			ok : function() {
				Ext.getBody().submitMask();
				Ext.Ajax.request({
					url : _win.config.url,
					params : {
						actualCost : _win.formPanel.getForm

						().findField("actualCost").getValue()
					},
					success : function(response) {
						_win.hide();
						_win.fireEvent("submit");
						Q.tips(Q.color($("message.save.success"),

						"red"));
					},
					failure : function(response) {
						Q.error($("message.submit.failure"));
					},
					callback : function() {
						Ext.getBody().unmask();
					}
				});
			}
		});
	}
});