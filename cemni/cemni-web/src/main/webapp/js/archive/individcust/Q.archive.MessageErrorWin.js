Ext.ns("Q.archive");
/**
 * <p>Description: 日志查询页</p>
 *
 * @author chensw 
 *
 * 时间 2014/12/26
 */
Q.archive.MessageErrorWin = function(config){
    config=config||{};
    //查询表单
    var formPanel = this.createFormPanel();
    this.formPanel = formPanel;

   config = Ext.apply({
 		width: 520,
        height:330,
        layout:"border",
        items:formPanel,
        listeners:{
            hide: function(){
                formPanel.form.reset();
            }
        }
    },config);
    
     /**
     * @event search 表单查询
     * @param {Ext.form.BasicForm} form 表单
     * @param {int} key 表单主键
     * @param {object} data 加载所得的数据
     * @return {boolean} 返回false则不关闭查询表单
     */
    this.addEvents("search");
    
    Q.archive.MessageErrorWin.superclass.constructor.call(this, config);

};

Ext.extend(Q.archive.MessageErrorWin, Ext.ux.Window, {
    //创建维护表单
    createFormPanel: function(){
        var win = this;
        var formPanel = new Ext.form.FormPanel({
            region: "center",
           // height: 270,
            labelWidth:120,
            layout:"column",
            border:true,
            bodyStyle:"padding:10px",
            defaults:{columnWidth:1,layout:"form",border:false},
            items:[{
                defaults:{xtype:"textfield",anchor: "90%"},
                columnWidth:1,
                items:[{
                    fieldLabel:"错误信息",//"系统模块", 
                    name:"filter_LIKE_location",
                    value:sb,
                    readOnly:true,
                    xtype:"textarea",
                    height:240
                }]
            }],
            buttons:[{
                text: $("button.return"),
                handler: function(){
                    win.hide();
                }
            }]
        });
        return formPanel;
    }
});

