Q.common = {

    // 字典表-下拉框store：通过"dictcode"获取
    selDict : function(dictCode, _autoLoad) {
        return new Ext.data.JsonStore({
            url : path + "/Dict_selDict.action?dictCode=" + dictCode,
            fields : [ "name", "value" ],
            autoLoad : (_autoLoad == undefined ? true : !!_autoLoad)
        });
    },

    // 字典表-下拉框store：通过"dictcode"获取(合同管理专用)
    selDictContract : function(dictCode, _autoLoad) {
        return new Ext.data.JsonStore({
            url : path + "/Dict_selDictContract.action?dictCode=" + dictCode,
            fields : [ "name", "value" ],
            autoLoad : (_autoLoad == undefined ? true : !!_autoLoad)
        });
    },

    // 字典表-下拉框store：通过"parentId"获取
    selDictByParentId : function(parentId, autoLoad) {
        return new Ext.data.JsonStore({
            url : path + "/Dict_selDictByParentId.action?parentId=" + parentId,
            fields : [ "name", "value" ],
            autoLoad : (_autoLoad == undefined ? true : !!_autoLoad)
        });
    },

    // 通过值查获取对应的文本：字典表、行政区域
    getSelText : function(store, val, displayField, valueField) {
        // 省略相应的参数时默认使用字典表字段
        displayField = displayField || 'name';
        valueField = valueField || 'value';

        var k = store.find(valueField, val);
        if (k > -1) {
            return store.getAt(k).get(displayField);
        } else {
            return val;
        }
    },

    yesOrNotStore : new Ext.data.ArrayStore({
        fields : [ 'value', 'text' ],
        data : [ [ '1', '是' ], [ '0', '否' ] ]
    }),

    yesOrNotRenderer : function(v) {
        return v == 1 ? "是" : "否";
    },

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // 月份渲染
    monthRenderer : function(v) {
        if (!!v) {
            if (v instanceof Date) {
                return v.format("Y-m");
            } else {
                return new Date(Date.parse(v.replace(/-/g, "/"))).format("Y-m");
            }

        }
    },

    // 日期渲染
    dateRenderer : function(v) {
        if (!!v) {
            if (v instanceof Date) {
                return v.format("Y-m-d");
            } else {
                return new Date(Date.parse(v.replace(/-/g, "/"))).format("Y-m-d");
            }

        }
    },

    // 时间渲染
    timeRenderer : function(v) {
        if (!!v) {
            if (v instanceof Date) {
                return v.format("Y-m-d");
            } else {
                return new Date(Date.parse(v.replace(/-/g, "/"))).format("Y-m-d H:i:s");
            }
        }
    },

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // 年份
    yearStore : function() {
        var year = new Date().getFullYear();
        return new Ext.data.ArrayStore({
            fields : [ 'value', 'text' ],
            data : [ [ year - 5, year - 5 ], [ year - 4, year - 4 ], [ year - 3, year - 3 ], [ year - 2, year - 2 ], [ year - 1, year - 1 ], [ year, year ], [ year + 1, year + 1 ], [ year + 2, year + 2 ] ]
        });
    }

};