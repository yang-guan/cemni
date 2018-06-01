/**
 * 获取分栏tabPanel
 * @params object json
 *      gridPanel : gridPanel, //主单grid
 *      column : 列的dataIndex，缺省为undefined，如果设置该项，则将只有单击该值对应的列时，才显示分栏tab
 *
 *      //因为viewport在创建完后再add不起作用，所以splitTab放在前面创建
 *      //因为splitTab在viewport创建前创建，所以只能传id来解决函数getSplitTab内的容器引用
 *      container : "p_viewport", //容器对象或容器id，当无法直接传入容器对象时，可以选择传入容器id
 *      activeTab : 0 //0：订单信息，1：订单明细，2：审核意见，3：系统日志，4：交互信息 *
 * @params object/array 分栏中包含的子集对象，可以是单个组件，也可以是组件数组，或者是组件配置信息
 *      激活当前组件时，会执行组件的[onActivate]方法，添加组件的onActivate方法（函数）， *          该函数有两个参数，一个是key，一个是当前激活的panel，当key为空时代表主列表grid没有改变选中记录，否则key为主列表grid选中记录的id
 */
function getSplitTab(json, items){
    var gridPanel = json.gridPanel; //对应的主列表
    var column = json.column;
    var container = json.container;
    var activeTab = (json.activeTab || 0); //默认为第0个    var tabHeight = (json.tabHeight || 200); //默认高度

    var tabPanel = new Ext.TabPanel({
        activeTab: activeTab,
        hidden:true,
        height: tabHeight,
        region:"south",
        //frame:true,
        border: false,
        tabPosition: "bottom",
        items: items,
        listeners:{
            "show": function(self){
                var currentPanel = self.getActiveTab();
                currentPanel.fireEvent("activate", currentPanel);
                getContainer().doLayout();
            },
            "hide": function(){
                getContainer().doLayout();
            },
            "afterrender": function(tp){
                this.createMaximizeBtns();
                //为子组件添加activate事件
                for(var i=0, len=tabPanel.items.length; i<len; i++){
                    var p = tabPanel.get(i);
                    p.on("activate", function(panel){
                        var key = getKey(panel); //如果没有改变选中的记录，返回的key将为null
                        if(Ext.isFunction(panel.onActivate)){
                            panel.onActivate(key, panel);
                        }
                    });
                }
            }
        },
        //创建最大化/默认大小显示按钮功能
        createMaximizeBtns : function(){
            this.pos = this.tabPosition=='bottom' ? this.footer : this.header;
            this.pos.addClass('x-tab-scrolling');
            this.pos.addClass('x-tab-scrolling-' + this.tabPosition);

            var wrap = this.stripWrap;
            var wd = wrap.dom;
            if(Ext.isAir || Ext.isWebKit){
                wd.style.marginLeft = '18px';
                wd.style.marginRight = '18px';
            }
            var h = 24;//按钮高度，设置成与tab差不多高

            // left
            var sl = this.pos.insertFirst({
                cls:'x-tab-scroller-left',
                title:"设置最大化/默认大小显示",
                html:"<img src='"+path+"/images/icon-maximize.gif' style='margin:5px 0 0 1px;' />"
            });
            sl.setHeight(h);
            //sl.addClassOnOver('x-tab-scroller-left-over');
            var tab = this, pad=32; //32是win等容器的标题栏、及其它填充占用的高度            var c = getContainer(); //容器
            if(!c.header){ //如果容器没有header，比如viewport或者没有header的panel，没有header且最大化的window等                pad=0;
            }
            sl.on("click", function(){
                if((tab.getHeight()+pad) != c.getHeight()){
                    tab.setHeight(c.getHeight() - pad); //去除tabpanel的tab显示占用
                }else{
                    tab.setHeight(tabHeight || 200);
                }
                c.doLayout();
            });
        }
    });

    //刷新grid时，隐藏分栏
    gridPanel.getStore().on("beforeload", function(){
        tabPanel.hide();
        reset(); //重置条件，不能在hide中处理    });


    //未设置column时，直接显示分栏
    if(Ext.isEmpty(column)){
        gridPanel.on("click", function(){
            var record = gridPanel.getSelectionModel().getSelected();
            //未选中任何行时，隐藏下栏            if(Ext.isEmpty(record)){
                tabPanel.hide();
                return;
            }
            tabPanel.show(); //在需要触发显示的时候执行tabPanel.show()
        });
    }else{
        //只有单击某个字段时，才显示分栏        gridPanel.on("cellclick", function(self, row, col, e){
            var fieldName = self.getColumnModel().getDataIndex(col);
            if(column == fieldName){
                tabPanel.show();
            }else{
                tabPanel.hide();
            }
        });
    }

    //取得当前tabPanel所属的容器
    function getContainer(){
        //如果container为字符串（一般为初始传值），则其代表的值为容器的id
        if("string" == Ext.type(container)){
            container = Ext.getCmp(container);
        }
        return container;
    }

    //返回单据key
    //根据当前panel与grid判断是否有选中、是否有更新，若未选中记录或记录没有更新则返回null
    function getKey(panel){

        var record = gridPanel.getSelectionModel().getSelected();
        if(record == null){
            return null;
        }
        var key = record.id;
        if(key == panel.key){
            return null;
        }else{
            panel.key = key;
        }
        return key;
    }

    //重置分栏存储的key
    function reset(){
        for(var i=0, len=tabPanel.items.length; i<len; i++){
            tabPanel.get(i).key="";
        }
    }

    return tabPanel;
}