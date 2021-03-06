/*
 * Compressed by JSA(www.xidea.org)
 */
Ext.ns("Q.comm");
Q.comm.CommModelEditWin = function(K) {
    var G = this;
    this.vpWin = K.vpWin;
    K = K || {};
    this.compArr = {};
    this.configVar = K;
    this.configTab = {};
    this.configTabPanel = {};
    var D = this.storeMain = new Ext.data.JsonStore({
        fields : [],
    });
    D.recordTypeTemp = {};
    var H = this.createTabsDtl = this.createTabsDtlFn((K.editWin.centerTab && K.editWin.centerTab.items) || [], "createTabsDtl"), N = this.southTabsDtl = this.createTabsDtlFn((K.editWin.southTab && K.editWin.southTab.items) || [], "southTabsDtl"), B = this.westTabsDtl = this.createTabsDtlFn((K.editWin.westTab && K.editWin.westTab.items) || [], "westTabsDtl"), O = this.eastTabsDtl = this.createTabsDtlFn((K.editWin.eastTab && K.editWin.eastTab.items) || [], "eastTabsDtl");
    this.hasWinTab = false;
    if (H.length > 0 || N.length > 0 || B.length > 0 || O.length > 0)
        this.hasWinTab = true;
    this.configTab["createTabsDtl"] = H;
    this.configTab["southTabsDtl"] = N;
    this.configTab["westTabsDtl"] = B;
    this.configTab["eastTabsDtl"] = O;
    this.hasFlag = false;
    var I = !Ext.isEmpty(K.accessControl) ? K.accessControl.hiddenTab : null, C = false;
    if (I != null) {
        Q.each(I, function($, _) {
            if (!$) {
                C = true;
                return
            }
        });
        if (!C)
            this.hasFlag = true
    }
    var E = this.formPanel = this.createFormPanel(K);
    this.getFormStore(E);
    var F = this.centerTabPanel = this.createTabPanelFn(H), M = this.southTabPanel = this.createTabPanelFn(N), A = this.westTabPanel = this.createTabPanelFn(B), L = this.eastTabPanel = this.createTabPanelFn(O);
    this.configTabPanel["createTabsDtl"] = F;
    this.configTabPanel["southTabsDtl"] = M;
    this.configTabPanel["westTabsDtl"] = A;
    this.configTabPanel["eastTabsDtl"] = L;
    var _ = this.panel = this.createPanelFn(F, M, A, L);
    this.nextBillState = K.editWin.nextBillState;
    var J = this.hiddenTabOut, K = Ext.apply({
        dealUrl : K.dealUrl,
        title : K.editWin.maximized ? "" : K.moduleName,
        width : 800,
        height : 450,
        maximized : false,
        closable : false,
        layout : "border",
        border : false,
        items : [ E, this.hasWinTab ? _ : {} ],
        buttons : K.editWin.maximized ? [] : [ {
            Qtext : "\u4fdd\u5b58",
            text : $("button.save"),
            name : "save",
            handler : function() {
                G.submitWin("save")
            }
        }, {
            text : $("button.confirm"),
            Qtext : "\u63d0\u4ea4",
            name : "submit",
            handler : function() {
                G.submitWin("audit")
            }
        }, {
            text : $("button.return"),
            Qtext : "\u8fd4\u56de",
            name : "return",
            handler : function() {
                G.resetWin()
            }
        } ]
    }, K);
    this.config = K;
    K.editWin = Ext.applyIf(K.editWin, K);
    this.addEvents("submit");
    this.addEvents("setFormValueAfter");
    Q.comm.CommModelEditWin.superclass.constructor.call(this, K.editWin)
};
Ext.extend(Q.comm.CommModelEditWin, Ext.ux.Window, {
    listeners : {
        "hide" : function() {
            this.hiddenTabOut = null;
            this.resetWin()
        },
        "show" : function() {
            var _ = this;
            Q.each(this.configTab, function(A, $) {
                if (A.length > 0)
                    if (!Ext.isEmpty(A)) {
                        var C = _.configTabPanel[$], B = C.items.length;
                        for (var D = B - 1; D > -1; D--)
                            C.setActiveTab(D)
                    }
            });
            if (_.btnType != "add" && _.btnType != "history")
                _.setDtlValue();
            var A = !Ext.isEmpty(this.config.accessControl) ? this.config.accessControl.hiddenTab : null;
            if (!Ext.isEmpty(this.hiddenTabOut))
                A = this.hiddenTabOut;
            if (!Ext.isEmpty(A)) {
                this.hiddenTabTemp = A;
                var $ = [];
                Q.each(A, function(A, B) {
                    if (A) {
                        $.push(B);
                        _.hideTabItem(B)
                    } else
                        _.showTabItem(B)
                });
                _.setActiveTab($)
            }
            _.doLayout()
        }
    },
    resetWin : function() {
        var $ = this;
        if (!this.hidden) {
            $.storeMain.removeAll();
            this.hide();
            Q.each($.configTab, function(A, _) {
                if (A.length > 0)
                    Q.each(A, function(_, A) {
                        Q.each(_.items, function(A, B) {
                            if (B == 0) {
                                var _ = A.getXType() || A.xtype;
                                if (_ == "uxgrid" || _ == "grid" || _ == "uxeditorgrid" || _ == "editorgrid")
                                    A.getStore().removeAll();
                                else if (_ == "formpanel" || _ == "form") {
                                    $.setFormArrReadOnlyFields(A.tabClassName, false);
                                    A.getForm().reset()
                                }
                                var C = typeof A.hideWinAndResetAfter != "undefined" && A.hideWinAndResetAfter;
                                if (Ext.isFunction(C))
                                    C(A)
                            } else if (!Ext.isEmpty(A.items) && A.items.length > 0)
                                Q.each(A.items.items, function(A, B) {
                                    var _ = A.getXType() || A.xtype;
                                    if (_ == "uxgrid" || _ == "grid" || _ == "uxeditorgrid" || _ == "editorgrid") {
                                        A.getStore().removeAll();
                                        A.storeTemp = {}
                                    } else if (_ == "formpanel" || _ == "form") {
                                        $.setFormArrReadOnlyFields(A.tabClassName, false);
                                        A.getForm().reset()
                                    }
                                    var C = typeof A.hideWinAndResetAfter != "undefined" && A.hideWinAndResetAfter;
                                    if (Ext.isFunction(C))
                                        C(A)
                                })
                        })
                    })
            });
            this.formPanel.getForm().reset();
            this.setFormArrReadOnlyFields("mainFormPanel", false);
            this.url = ""
        }
    },
    isValidWin : function($) {
        var _ = this, B = this.formPanel.form.isValid();
        if (!B)
            return false;
        var C = true, A = _.hiddenTabTemp;
        Q.each(_.configTab, function(E, D) {
            if (E.length > 0) {
                var B = false;
                Q.each(E, function(H, L) {
                    var C = H.items[0].getXType() || H.items[0].xtype, I = H.items[0];
                    if (!(!Ext.isEmpty(I.submitValue) && I.submitValue === false))
                        if (C == "uxgrid" || C == "grid" || C == "uxeditorgrid" || C == "editorgrid") {
                            var G = H.items[0];
                            if (!(!Ext.isEmpty(A) && !Ext.isEmpty(A[G.tabClassName]) && A[G.tabClassName])) {
                                _.configTabPanel[D].setActiveTab(L);
                                var E = _.gridTabValid(G, D, $);
                                B = E;
                                return E
                            } else
                                return true
                        } else {
                            var K = H.items[0], J = typeof K.isValidFn != "undefined" && K.isValidFn, F = true;
                            if (Ext.isFunction(J)) {
                                F = J();
                                if (Ext.isEmpty(F))
                                    F = true
                            }
                            if (!(!Ext.isEmpty(A) && !Ext.isEmpty(A[K.tabClassName]) && A[K.tabClassName]) && F) {
                                _.configTabPanel[D].setActiveTab(L);
                                return B = K.form.isValid()
                            } else
                                return true
                        }
                });
                C = B;
                return B
            }
        });
        return C
    },
    gridTabValid : function(D, B, C) {
        var E = this, _, A = D.getStore();
        if (!Ext.isEmpty(D.allowEmpty))
            if (typeof (D.allowEmpty) == "boolean") {
                if (!D.hideTabItem && !D.allowEmpty && A.getCount() < 1) {
                    Q.tips("<font color='red'>" + D.tabTitle + ":" + $("valid.mustHaveDetail") + "</font>");
                    return false
                }
            } else {
                var F = D.allowEmpty[C];
                if (!D.hideTabItem && !F && A.getCount() < 1) {
                    Q.tips("<font color='red'>" + D.tabTitle + ":" + $("valid.mustHaveDetail") + "</font>");
                    return false
                }
            }
        _ = E.tabValidDetailUtil(D, B, C);
        if (D.fireEvent("afterValid", D, function($) {
            _ = $
        }) === false)
            if (!_) {
                _ = "outVaild";
                return false
            }
        if (_) {
            if (_ != "outVaild") {
                Q.tips(_);
                D.getView().refresh()
            }
            return false
        }
        return true
    },
    tabValidDetailUtil : function(E, D) {
        var G = this, H = 1, B, F, A = E.validField || [], C = E.getStore(), _ = E.validStartIndex;
        C.each(function(I) {
            if (B)
                return true;
            E.getSelectionModel().clearSelections();
            E.getSelectionModel().selectRow(H - 1, true);
            var L = typeof E.validFieldFn != "undefined" && typeof E.validFieldFn != "undefined" && E.validFieldFn;
            if (Ext.isFunction(L)) {
                var M = L(E, I);
                if (!Ext.isEmpty(M) && Ext.isArray(M))
                    A = M
            }
            Q.each(A, function(A, C) {
                if (Ext.isEmpty(I.get(A))) {
                    I.cell = A;
                    B = "<font color='red'>[" + E.tabTitle + "]</font></br>";
                    B += "<font color='blur'>[" + $("message.number") + H + $("message.row") + E.fieldName[A] + $("message.cellisnotnull") + "]</font>";
                    F = "<font color='red'>[\u7b2c" + H + "\u884c\uff0c\u7b2c" + parseInt(C + _) + "\u5217]</font>\u4e0d\u80fd\u4e3a\u7a7a";
                    return false
                }
            });
            if (!B) {
                var K = G.hiddenTabTemp, C = G.configTabPanel[D].getActiveTab();
                if (C.items.last().items && C.items.last().items.length > 0) {
                    var J = C.items.last().items.items;
                    Q.each(J, function(F, L) {
                        var _ = F.getXType() || F.xtype;
                        if (_ != "formpanel" && _ != "form") {
                            var A = F.getStore();
                            if (!Ext.isEmpty(E.allowEmpty))
                                if (typeof (E.allowEmpty) == "boolean") {
                                    if (!F.hideTabItem && !F.allowEmpty && A.getCount() < 1) {
                                        B = "<font color='red'>" + F.tabTitle + ":" + $("valid.mustHaveDetail") + "</font>";
                                        return false
                                    }
                                } else {
                                    var J = E.allowEmpty[action];
                                    if (!F.hideTabItem && !F.allowEmpty && A.getCount() < 1) {
                                        B = "<font color='red'>" + F.tabTitle + ":" + $("valid.mustHaveDetail") + "</font>";
                                        return false
                                    }
                                }
                        }
                        if (F.noValidation === true)
                            return true;
                        C.items.last().setActiveTab(L);
                        if (_ != "formpanel" && _ != "form") {
                            if (!(!Ext.isEmpty(K) && !Ext.isEmpty(K[F.tabClassName]) && !K[F.tabClassName])) {
                                B = G.tabValidChildDetialUtil(F, I);
                                if (B) {
                                    F.getView().refresh();
                                    return false
                                }
                            }
                        } else {
                            var H = typeof F.isValidFn != "undefined" && F.isValidFn, D = true;
                            if (Ext.isFunction(H)) {
                                D = H();
                                if (Ext.isEmpty(D))
                                    D = true
                            }
                            if (!(!Ext.isEmpty(K) && !Ext.isEmpty(K[F.tabClassName]) && K[F.tabClassName]) && D)
                                if (!F.form.isValid()) {
                                    B = $("message.verify.failure");
                                    return false
                                }
                        }
                    })
                }
            }
            H++
        });
        return B
    },
    tabValidChildDetialUtil : function(D, H) {
        var F = this, G = 1, B, E, A = D.validField || [], C = D.getStore(), _ = D.validStartIndex;
        C.each(function(C) {
            if (B)
                return true;
            var F = typeof D.validFieldFn != "undefined" && typeof D.validFieldFn != "undefined" && D.validFieldFn;
            if (Ext.isFunction(F)) {
                var I = F(D, C);
                if (!Ext.isEmpty(I) && Ext.isArray(I))
                    A = I
            }
            Q.each(A, function(A, F) {
                if (Ext.isEmpty(C.get(A))) {
                    C.cell = A;
                    B = "<font color='red'>[" + D.tabTitle + "]</font></br>";
                    B += "<font color='blur'>[" + $("message.number") + G + $("message.rowandnumber") + "\uff0c" + D.fieldName[A] + $("message.cellisnotnull") + "]</font>";
                    E = "<font color='red'>[\u7b2c" + G + "\u884c\uff0c\u7b2c" + parseInt(F + _) + "\u5217]</font>\u4e0d\u80fd\u4e3a\u7a7a";
                    return false
                }
            });
            G++;
            if (D.fireEvent("afterValid", D, H, function($) {
                B = $
            }) === false)
                if (!B)
                    B = "outVaild"
        });
        return B
    },
    submitWin : function(_) {
        var A = this, B = typeof this.configVar.editWin != "undefined" && typeof this.configVar.editWin.submitBefore != "undefined" && this.configVar.editWin.submitBefore;
        if (Ext.isFunction(B))
            if (B(A.formPanel, _) === false)
                return;
        if (!A.isValidWin(_))
            return;
        var C = (this.url || this.dealUrl + "_save.action?");
        Q.confirm((_ == "submit" || _ == "audit") ? $("message.confirm.confirm") : $("message.save.confirm"), {
            ok : function() {
                var I = {
                    submitFlag : _,
                    nextBillState : A.nextBillState
                }, D = {};
                Q.each(A.configTab, function(B, _) {
                    var $ = A.assemblyParams(B, _);
                    D = Ext.apply(D, $.model)
                });
                var F = {
                    model : D
                };
                I = Ext.apply(I, F);
                var H = A.formPanel.findByType(Ext.form.DateField);
                Q.each(H, function($, _) {
                    if ($.lateNight === true)
                        I[$.name] = ($.getValue().format("Y-m-d") + " 23:59:59");
                    else if (!Ext.isEmpty($.getValue()) && $.format == "Y-m")
                        I[$.name] = $.getValue().format("Y-m-d") + " 00:00:00";
                    else if (($.disabled || $.readOnly) && !Ext.isEmpty($.getValue()))
                        I[$.name] = $.getValue().format("Y-m-d")
                });
                var E = A.formPanel.findByType(Ext.form.TimeField);
                Q.each(E, function($, _) {
                    I[$.name] = "1970-01-01 " + $.getValue()
                });
                var G = A.formPanel.findByType(Ext.form.TextArea);
                Q.each(G, function($, _) {
                    $.setValue($.getValue().replace(/\n/g, "<br/>"))
                });
                var J = A.formPanel.getForm().getValues(), $ = A.formPanel.findByType(Ext.form.ComboBox);
                Q.each($, function($, _) {
                    if (Ext.isEmpty(J[$.name]))
                        if ($.readOnly === true)
                            I[$.name] = $.getValue();
                        else if ($.disabled === true)
                            I[$.name] = $.getValue()
                });
                var B = A.formPanel.findByType(Ext.form.TriggerField);
                Q.each(B, function($, _) {
                    if (Ext.isEmpty(J[$.name]))
                        if (($.disabled || $.readOnly) && $.xtype == "uxtrigger")
                            I[$.name] = $.getValue()
                });
                var K = typeof A.configVar.editWin != "undefined" && typeof A.configVar.editWin.submitAfter != "undefined" && A.configVar.editWin.submitAfter;
                if (Ext.isFunction(K))
                    K(I, A.formPanel);
                K = typeof A.configVar.editWin != "undefined" && typeof A.configVar.editWin.overrideSubmit != "undefined" && A.configVar.editWin.overrideSubmit;
                if (Ext.isFunction(K))
                    K(C, I, A.formPanel, A);
                else
                    A.formSubmit(C, I)
            }
        })
    },
    formSubmit : function(B, A) {
        var _ = this;
        _.formPanel.getForm().submit({
            waitTitle : $("message.submit.data"),
            waitMsg : $("message.submit.wait"),
            url : B,
            method : "POST",
            timeout: 180000,
            params : Ext.encode(A) == "{}" ? A : Q.parseParams(A),
            success : function(C, D) {
                var B = D.result, F = B.info, E = typeof _.configVar.editWin != "undefined" && typeof _.configVar.editWin.submitSuccessAfter != "undefined" && _.configVar.editWin.submitSuccessAfter;
                if (Ext.isFunction(E))
                    E(C, D, Q.parseParams(A));
                _.resetWin();
                _.fireEvent("submit");
                Q.tips("<font color='blue'>" + $("message.save.success") + "</font>")
            },
            failure : function(_, A) {
                if (A && A.result)
                    Q.error(A.result.info || $("message.submit.failure"));
                else
                    Q.error($("message.submit.failure") + "<br/><br/>" + $("message.system.disconnect"))
            }
        })
    },
    assemblyParams : function(A, _) {
        var C = this, $ = this.formPanel.form.findField("model." + C.vpWin.store.idProperty).getValue(), B = {
            model : {}
        };
        if (A.length > 0)
            Q.each(A, function(F, G) {
                var H = F.items[0].getXType() || F.items[0].xtype, L = F.items[0];
                if (Ext.isEmpty(L.submitValue) || L.submitValue === true)
                    if (H == "uxgrid" || H == "grid" || H == "uxeditorgrid" || H == "editorgrid") {
                        var K = F.items[0];
                        C.configTabPanel[_].setActiveTab(G);
                        if (typeof K.foreignKey != "undefined")
                            B.model[K.tabClassName] = C.assemblyTabParams(K, $, B, G, _)
                    } else if (H == "formpanel" || H == "form") {
                        C.configTabPanel[_].setActiveTab(G);
                        var E = F.items[0];
                        if (typeof E.foreignKey != "undefined") {
                            var N = E.foreignKey.substring(0, E.foreignKey.indexOf("_")), D = E.getForm(), A = D.getValues();
                            Q.each(A, function(_, A) {
                                var $ = D.findField(A);
                                if ($.xtype == "Ext.form.DateField") {
                                    if ($.lateNight === true)
                                        _ = _.format("Y-m-d") + " 23:59:59";
                                    else if (!Ext.isEmpty(_) && $.xtype == "datefield" && $.format == "Y-m")
                                        _ = _ + "-01 00:00:00"
                                } else if ($.xtype == "Ext.form.TextArea")
                                    _ = _.replace(/\n/g, "<br/>");
                                else if ($.xtype == "Ext.form.Hidden") {
                                    if ($.name.indexOf("Time") > -1)
                                        _ = new Date(_).format("Y-m-d H:i:s");
                                    if ($.name.indexOf("Date") > -1 || $.name.indexOf("date") > -1)
                                        _ = new Date(_).format("Y-m-d")
                                }
                            });
                            var M = {};
                            M = A;
                            if (typeof E.belongParent != "undefined" && E.belongParent == true) {
                                var I = E.saveField;
                                Q.each(I, function(A, C) {
                                    var $ = D.findField(A), _ = $.getValue();
                                    if (!Ext.isEmpty(_) && $.xtype == "datefield")
                                        _ = _.format("Y-m-d") + " 00:00:00";
                                    if (!Ext.isEmpty(A) && A.substring(6))
                                        B.model[A.substring(6)] = _;
                                    else
                                        B.model[A] = _
                                })
                            } else {
                                var N = E.foreignKey.substring(0, E.foreignKey.indexOf("_")), J = [];
                                M[N] = {};
                                M[N][C.vpWin.store.idProperty] = $ || "";
                                J.push(M);
                                B.model[D.tabClassName] = J
                            }
                        }
                    }
            });
        return B
    },
    assemblyTabParams : function(E, $, G, D, B) {
        var F = this, A = E.getStore();
        if (typeof E.foreignKey != "undefined") {
            var H = E.foreignKey.substring(0, E.foreignKey.indexOf("_")), _ = E.saveField || [], C = [];
            A.each(function(A) {
                var D = {};
                Q.each(_, function($, _) {
                    if (Ext.isDate(A.get($)))
                        D[$] = A.get($).format("Y-m-d H:i:s");
                    else if (!Ext.isEmpty(A.get($)))
                        D[$] = A.get($)
                });
                D[H] = {};
                D[H][F.vpWin.store.idProperty] = $ || "";
                var I = F.configTabPanel[B].getActiveTab();
                if (I.items.last().items && I.items.last().items.length > 0) {
                    var G = I.items.last().items.items;
                    Q.each(G, function(B, C) {
                        var $ = B.getXType() || B.xtype, _ = F.getStoreTempKey(B, A);
                        if (typeof B.foreignKey != "undefined")
                            D[B.tabClassName] = F.assemblyChildDetailParams(_, B, E)
                    })
                }
                C.push(D)
            });
            return C
        }
    },
    assemblyChildDetailParams : function(B, C, E) {
        var D = this, A = [], _ = C.storeTemp[B], F = C.foreignKey.substring(0, C.foreignKey.indexOf("_"));
        if (!Ext.isEmpty(_)) {
            var $ = C.saveField || [];
            _.each(function(B) {
                var D = {};
                Q.each($, function($, _) {
                    if (Ext.isDate(B.get($)))
                        D[$] = B.get($).format("Y-m-d H:i:s");
                    else if (!Ext.isEmpty(B.get($)))
                        D[$] = B.get($)
                });
                var _ = B.get(C.store.idProperty);
                D[F] = {};
                D[F][E.store.idProperty] = _ || "";
                A.push(D)
            })
        }
        return A
    },
    createFormPanel : function(B) {
        var A = this, $ = B.editWin.form;
        Ext.apply($, {
            region : (A.hasWinTab && (A.hasFlag === false)) ? "north" : "center",
            tabClassName : "mainFormPanel",
            height : B.editWin.form.height || 130,
            labelWidth : B.editWin.form.labelWidth || 110,
            layout : "column",
            autoScroll : true,
            bodyStyle : "padding:10px",
            readOnlyFiledArr : [],
            defaults : {
                columnWidth : 1,
                layout : "form",
                border : false
            },
            tbar : B.displayTbar || A.dealTbar(B),
            items : A.dealFormItems(B)
        });
        var _ = new Ext.form.FormPanel($);
        A.compArr["mainFormPanel"] = _;
        A.initReadOnlyField(_);
        return _
    },
    insert : function(_, $) {
        var B = this.formPanel, A = B.lookupComponent(B.applyDefaults($)), A = B.insert(_, $);
        if (A.ownerCt == B && B.items.indexOf(A) < _)
            --_;
        if (B.fireEvent("beforeadd", this, A, _) !== false && B.onBeforeAdd(A) !== false) {
            B.items.insert(_, A);
            A.onAdded(B, _);
            B.onAdd(A);
            B.fireEvent("add", this, A, _)
        }
        B.doLayout()
    },
    dealFormItems : function(A) {
        Ext.applyIf(A.editWin.form, {
            items : [],
            columnWidth : 0.5
        });
        var _ = [], $ = A.editWin.form.items || [], B = this.formColumnWidth = A.editWin.form.columnWidth || 0.5;
        if ($) {
            var C = {};
            if (A.accessControl && A.accessControl.model)
                C = A.accessControl.model;
            Q.each($, function(A, D) {
                if (!(typeof A.fieldUnDisplay != "undefined" && A.fieldUnDisplay.indexOf("form") > -1)) {
                    Q.each(C, function($, _) {
                        Q.each($, function($, B) {
                            if (B == A.name)
                                A[_] = $
                        })
                    });
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : A.columnWidth || B,
                        items : [ A ]
                    };
                    _.push($)
                }
            })
        }
        return _
    },
    addFormItems : function(_) {
        var A = this.formPanel;
        if (_) {
            var B = this.formColumnWidth || 0.5;
            if (Ext.isArray(_))
                Q.each(_, function(_, C) {
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : _.columnWidth || B,
                        items : [ _ ]
                    };
                    Ext.applyIf(_, {
                        index : A.items.length || 0
                    });
                    A.insert(_.index, $)
                });
            else {
                var $ = {
                    defaults : {
                        xtype : "textfield",
                        anchor : "95%"
                    },
                    columnWidth : _.columnWidth || B,
                    items : [ _ ]
                };
                Ext.applyIf(_, {
                    index : A.items.length || 0
                });
                A.insert(_.index, $)
            }
        }
    },
    removeFormItems : function($) {
        var A = this.formPanel, _ = A.items.items;
        if ($)
            if (Ext.isArray($))
                Q.each($, function($, A) {
                    _.splice($, 1)
                });
            else
                _.splice($, 1);
        A.doLayout()
    },
    modifyFormItems : function(_) {
        var B = this.formPanel, A = B.items.items;
        if (_) {
            var D = this.formColumnWidth;
            if (Ext.isArray(_))
                Q.each(_, function(C, F) {
                    if (C.index) {
                        Ext.apply(A[C.index].initialConfig.items[0], C);
                        var E = A[_.index].initialConfig.items[0], $ = {
                            defaults : {
                                xtype : "textfield",
                                anchor : "95%"
                            },
                            columnWidth : C.columnWidth || D,
                            items : [ E ]
                        };
                        A.splice(C.index, 1);
                        B.insert(C.index, $)
                    }
                });
            else {
                Ext.apply(A[_.index].initialConfig.items[0], _);
                var C = A[_.index].initialConfig.items[0], $ = {
                    defaults : {
                        xtype : "textfield",
                        anchor : "95%"
                    },
                    columnWidth : _.columnWidth || D,
                    items : [ C ]
                };
                A.splice(_.index, 1);
                B.insert(_.index, $)
            }
        }
        B.doLayout()
    },
    dealTbar : function(D) {
        var C = this, B = [ {
            text : $("button.toPass"),
            name : "TOPASS",
            iconCls : "icon-toPass",
            hidden : true,
            handler : function() {
                D.vpWin.dealstate(this.name, this.text, true)
            }
        }, {
            text : $("button.toNoPass"),
            name : "TONOPASS",
            iconCls : "icon-toNoPass",
            hidden : true,
            handler : function() {
                D.vpWin.dealstate(this.name, this.text, true)
            }
        } ], _ = !Ext.isEmpty(D.vp) ? (D.vp.menuOverride || B) : B, A = [ {
            text : $("button.save"),
            name : "save",
            iconCls : "icon-save",
            handler : function() {
                C.submitWin("save")
            }
        }, {
            text : $("button.submit"),
            name : "submit",
            iconCls : "icon-audit",
            handler : function() {
                C.submitWin("audit")
            }
        }, {
            text : $("button.return"),
            name : "return",
            iconCls : "icon-return",
            handler : function() {
                C.resetWin();
                C.hide()
            }
        } ];
        Q.each(A, function($, A) {
            _.push($)
        });
        var E = D.editWin.addOtherBtn || [];
        if (!Ext.isEmpty(E))
            Q.each(E, function($, A) {
                _.splice($.index, 0, $)
            });
        return _
    },
    setFormValue : function(C, G, H, B, D, A) {
        var F = this;
        F.btnType = G;
        var _ = "";
        if (!Ext.isEmpty(C)) {
            this.url = this.dealUrl + ((Ext.isEmpty(B) || B == "" || Ext.isFunction(B)) ? "_update.action?" : "_" + B + ".action");
            if (typeof C == "object")
                _ = C.id;
            else
                _ = C
        }
        F.recordId = _;
        function E(_, A, D) {
            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && _.getXType() != "hidden" && _.getXType() != "textarea") {
                if (_.getXType() == "timefield") {
                    var E = A.substring(0, A.indexOf(".")), $ = Date.parseDate(E, "Y-m-d H:i:s");
                    _.setValue($)
                } else
                    _.setValue(Date.parseDate(A, "Y-m-d H:i:s"))
            } else if (_.getXType() == "checkboxgroup")
                _.setValue(A.replace(/\s/g, ""));
            else if (_.getXType() == "textarea")
                _.setValue(A.replace(/<br\/>/g, "\n"));
            else if (D == "model.uploadFileGroupId") {
                F.vpWin.renderUploadFile(A, "", "uploadFile4View", F.formPanel, "");
                _.setValue(A)
            } else if (D.indexOf("uploadFileGroupId") > -1 && D != "model.uploadFileGroupId") {
                var B = D.substr(6), G = B.indexOf("."), C = B.substr(0, G + 1);
                F.vpWin.renderUploadFile(A, "", C + "uploadFile4View", F.formPanel, "");
                _.setValue(A)
            } else if (_.getXType() == "combo" || _.getXType() == "uxcombo") {
                _.setValue(A);
                _.fireEvent("display", _, A)
            } else
                _.setValue(A)
        }
        F.setBtnState(G, D, A);
        if (F.configVar.isAudit == true)
            F.getEvents(C, G, D);
        F.setEditState(F.formPanel, G);
        Ext.getBody().loadMask();
        Ext.Ajax.request({
            url : F.dealUrl + ((Ext.isEmpty(H) || H == "" || Ext.isFunction(H)) ? "_edit.action?" : "_" + H + ".action"),
            params : {
                "id" : _
            },
            success : function(_) {
                var A = F.formPanel.getForm(), B = Ext.decode(_.responseText);
                if (false === B.success) {
                    Q.error(B.info || $("message.delete.failure") + "<br/><br/>" + $("message.system.error"));
                    return
                }
                var C = B.data;
                if (Ext.isEmpty(C))
                    C = B;
                F.storeMain.removeAll();
                F.storeMain.add(new F.storeMain.recordType(C));
                Q.each(C, function(_, B) {
                    if (!Ext.isEmpty(_) && typeof _ == "object")
                        Q.each(_, function(_, C) {
                            var D = "model." + B + "." + C, $ = A.findField(D);
                            if (!Ext.isEmpty($) && !Ext.isEmpty(_) && _ != null)
                                E($, _, D)
                        });
                    else {
                        var C = "model." + B, $ = A.findField(C);
                        if (!Ext.isEmpty($) && !Ext.isEmpty(_) && _ != null)
                            E($, _, C)
                    }
                });
                Ext.getBody().unmask();
                var D = typeof F.form.setFormValueAfter != "undefined" && F.form.setFormValueAfter;
                if (Ext.isFunction(D))
                    D(F.formPanel, G);
                Q.each(F.configTab, function(_, $) {
                    if (_.length > 0)
                        Q.each(_, function(D, E) {
                            var $ = D.items[0].getXType() || D.items[0].xtype;
                            if (!($ == "uxgrid" || $ == "grid" || $ == "uxeditorgrid" || $ == "editorgrid")) {
                                var A = D.items[0];
                                if (typeof A.belongParent != "undefined" && A.belongParent == true) {
                                    var B = F.storeMain.getAt(0).data;
                                    B = Ext.apply(B, {});
                                    var C = {}, _ = A.getForm();
                                    Q.each(B, function(A, D) {
                                        var $ = _.findField("model." + D);
                                        if (!Ext.isEmpty($) && !Ext.isEmpty(A)) {
                                            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && $.getXType() != "hidden") {
                                                if ($.getXType() == "timefield") {
                                                    var B = A.substring(0, A.indexOf("."));
                                                    A = Date.parseDate(B, "Y-m-d H:i:s")
                                                } else
                                                    A = Date.parseDate(A, "Y-m-d H:i:s")
                                            } else if ($.getXType() == "textarea")
                                                A = A.replace(/<br\/>/g, "\n");
                                            C["model." + D] = A
                                        }
                                    });
                                    _.setValues(C)
                                }
                            }
                        })
                });
                F.fireEvent("setFormValueAfter", G, F.formPanel)
            },
            failure : function(_) {
                var A = Ext.decode(_.responseText);
                Ext.getBody().unmask();
                if (A && A.data)
                    Q.error(A.data || $("message.load.failure"));
                else
                    Q.error($("message.load.failure") + "<br/><br/>" + $("message.system.disconnect"))
            },
            callback : function() {
                Ext.getBody().unmask()
            }
        });
        F.viewType = G
    },
    setFormDelValue : function(_, A, D) {
        if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && _.getXType() != "hidden" && _.getXType() != "textarea") {
            if (_.getXType() == "timefield") {
                var E = A.substring(0, A.indexOf(".")), $ = Date.parseDate(E, "Y-m-d H:i:s");
                _.setValue($)
            } else
                _.setValue(Date.parseDate(A, "Y-m-d H:i:s"))
        } else if (_.getXType() == "textarea")
            _.setValue(A.replace(/<br\/>/g, "\n"));
        else if (D == "uploadFileGroupId") {
            win.vpWin.renderUploadFile(A, "", "uploadFile4View", win.formPanel, "");
            _.setValue(A)
        } else if (D.indexOf("uploadFileGroupId") > -1 && D != "uploadFileGroupId") {
            var B = D.substr(6), F = B.indexOf("."), C = B.substr(0, F + 1);
            win.vpWin.renderUploadFile(A, "", C + "uploadFile4View", win.formPanel, "");
            _.setValue(A)
        } else
            _.setValue(A)
    },
    setDtlValue : function() {
        var _ = this, $ = _.recordId, A = _.viewType;
        Q.each(_.configTab, function(C, B) {
            if (C.length > 0) {
                if (!Ext.isEmpty(C)) {
                    var E = _.configTabPanel[B], D = E.items.length;
                    for (var F = D - 1; F > -1; F--)
                        E.setActiveTab(F)
                }
                Q.each(C, function(H, L) {
                    var B = H.items[0].getXType() || H.items[0].xtype;
                    if (B == "uxgrid" || B == "grid" || B == "uxeditorgrid" || B == "editorgrid") {
                        var G = H.items[0], D = G.getStore();
                        if (typeof G.foreignKey == "undefined")
                            return;
                        D.setBaseParam("filter_EQ_" + G.foreignKey, $);
                        var F = G.getColumnModel().findColumnIndex("uploadFile4View");
                        if (Ext.isEmpty(G.uploadFile) && F != -1)
                            G.uploadFile = [ "uploadFileGroupId" ];
                        if (!Ext.isEmpty(H.items[1])) {
                            var J = H.items[1], I = J.items.length;
                            for (var L = I - 1; L > -1; L--)
                                J.setActiveTab(L)
                        }
                        var K = typeof G.loadValueBefore != "undefined" && G.loadValueBefore;
                        if (Ext.isFunction(K))
                            K(G, D);
                        D.load({
                            callback : function($) {
                                Q.each($, function($, B) {
                                    if (!Ext.isEmpty(H.items[1])) {
                                        var A = _.getCurrentTabChildGrid(H.items[1]);
                                        _.loadDtlDtlStore(A, $)
                                    }
                                });
                                var A = typeof G.loadValueAfter != "undefined" && G.loadValueAfter;
                                if (Ext.isFunction(A))
                                    A(G, D, $)
                            }
                        });
                        _.setFormReadOnlyFields(G.formFieldReadyArr || [], true)
                    } else {
                        var E = H.items[0];
                        _.setEditState(E, A);
                        var C = E.getForm(), D = E.store;
                        if (!(typeof E.belongParent != "undefined" && E.belongParent == true)) {
                            if (typeof E.foreignKey == "undefined")
                                return;
                            D.setBaseParam("filter_EQ_" + E.foreignKey, $);
                            D.load({
                                callback : function($) {
                                    if ($.length > 0) {
                                        var A = $[0].data;
                                        A = Ext.apply(A, {});
                                        var B = {};
                                        Q.each(A, function(A, B) {
                                            var $ = C.findField(B);
                                            if (!Ext.isEmpty($) && !Ext.isEmpty(A) && A != null)
                                                _.setFormDelValue($, A, B)
                                        })
                                    } else
                                        C.reset()
                                }
                            })
                        }
                    }
                })
            }
        })
    },
    setHistoryRecordValue : function(C, I, D, J) {
        var H = this;
        H.btnType = "history";
        var _;
        if (!Ext.isEmpty(C) && typeof C == "object")
            _ = C.id;
        else
            _ = C;
        function G(_, A, D) {
            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && _.getXType() != "hidden" && _.getXType() != "textarea") {
                if (_.getXType() == "timefield") {
                    var E = A.substring(0, A.indexOf(".")), $ = Date.parseDate(E, "Y-m-d H:i:s");
                    _.setValue($)
                } else {
                    $ = Date.parseDate(A, "Y-m-d H:i:s");
                    _.setValue($)
                }
            } else if (_.getXType() == "textarea")
                _.setValue(A.replace(/<br\/>/g, "\n"));
            else if (D == "model.uploadFileGroupId") {
                H.vpWin.renderUploadFile(A, "", "uploadFile4View", H.formPanel, "");
                _.setValue(A)
            } else if (D.indexOf("uploadFileGroupId") > -1 && D != "model.uploadFileGroupId") {
                var B = D.substr(6), F = B.indexOf("."), C = B.substr(0, F + 1);
                H.vpWin.renderUploadFile(A, "", C + "uploadFile4View", H.formPanel, "");
                _.setValue(A)
            } else
                _.setValue(A)
        }
        var A = H.buttons;
        Q.each(A, function($, _) {
            if ($.name == "return" || (!(H.maximized)))
                $.show();
            else
                $.hide()
        });
        var F = H.formPanel.findByType(Ext.Button);
        Ext.each(F, function($, _) {
            $.setDisabled(true)
        });
        var E = H.formPanel.getTopToolbar();
        console.info(H.configVar.editWin.maximized);
        if (H.configVar.editWin.maximized)
            E.setVisible(true);
        else
            E.setVisible(false);
        var B = E.items;
        B.each(function($) {
            if ($.name == "return")
                $.show();
            else
                $.hide()
        });
        H.setEditState(H.formPanel, I);
        if (Ext.isEmpty(D))
            D = {};
        Ext.getBody().loadMask();
        H.formPanel.getForm().load({
            waitTitle : $("message.load.data"),
            waitMsg : $("message.load.wait"),
            url : H.dealUrl + ((Ext.isEmpty(J) || J == "" || Ext.isFunction(J)) ? "_findHistoryRecord.action" : "_" + J + ".action"),
            params : Ext.apply(D, {
                "filter_EQ_historyRecordId" : _
            }),
            success : function($, A) {
                var _ = A.result.data;
                H.storeMain.removeAll();
                H.storeMain.add(new H.storeMain.recordType(_));
                Q.each(_, function(A, B) {
                    if (!Ext.isEmpty(A) && typeof A == "object")
                        Q.each(A, function(A, C) {
                            var D = "model." + B + "." + C, _ = $.findField(D);
                            if (!Ext.isEmpty(_) && !Ext.isEmpty(A) && A != null)
                                G(_, A, D)
                        });
                    else {
                        var C = "model." + B, _ = $.findField(C);
                        if (!Ext.isEmpty(_) && !Ext.isEmpty(A) && A != null)
                            G(_, A, C)
                    }
                });
                Ext.getBody().unmask();
                var B = typeof H.form.setFormValueAfter != "undefined" && H.form.setFormValueAfter;
                if (Ext.isFunction(B))
                    B(H.formPanel);
                Q.each(H.configTab, function(A, $) {
                    if (A.length > 0)
                        Q.each(A, function(G, K) {
                            var M = G.items[0].getXType() || G.items[0].xtype;
                            if (!(M == "uxgrid" || M == "grid" || M == "uxeditorgrid" || M == "editorgrid")) {
                                var E = G.items[0], $ = E.getForm();
                                H.setEditState(E, I);
                                if (typeof E.belongParent != "undefined" && E.belongParent == true) {
                                    var D = H.storeMain.getAt(0).data;
                                    D = Ext.apply(D, {});
                                    var P = {};
                                    Q.each(D, function(A, C) {
                                        var _ = $.findField("model." + C);
                                        if (!Ext.isEmpty(_)) {
                                            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && _.getXType() != "hidden" && _.getXType() != "textarea") {
                                                if (_.getXType() == "timefield") {
                                                    var B = A.substring(0, A.indexOf("."));
                                                    A = Date.parseDate(B, "Y-m-d H:i:s")
                                                } else
                                                    A = Date.parseDate(A, "Y-m-d H:i:s")
                                            } else if (_.getXType() == "textarea")
                                                A = A.replace(/<br\/>/g, "\n");
                                            P["model." + C] = A
                                        }
                                    });
                                    $.setValues(P)
                                } else {
                                    E = G.items[0], $ = E.getForm();
                                    H.setEditState(E, I);
                                    D = Ext.apply(D, {});
                                    var S = _[G.tabClassName];
                                    if (!Ext.isEmpty(S) && S.length > 0) {
                                        var N = S[0], P = {};
                                        Q.each(N, function(A, C) {
                                            var _ = $.findField(C);
                                            if (!Ext.isEmpty(_)) {
                                                if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A) && _.getXType() != "hidden" && _.getXType() != "textarea") {
                                                    if (_.getXType() == "timefield") {
                                                        var B = A.substring(0, A.indexOf("."));
                                                        A = Date.parseDate(B, "Y-m-d H:i:s")
                                                    } else
                                                        A = Date.parseDate(A, "Y-m-d H:i:s")
                                                } else if (_.getXType() == "textarea")
                                                    A = A.replace(/<br\/>/g, "\n");
                                                P[C] = A
                                            }
                                        });
                                        $.setValues(P)
                                    }
                                }
                            } else {
                                var R = G.items[0], A = R.getStore(), J = false;
                                Q.each(R.getColumnModel().columns, function($, _) {
                                    if ($.dataIndex == "uploadFile4View")
                                        J = true
                                });
                                var B = _[R.tabClassName], T = H.getCompByTabClassName(R.tabClassName), L = T.getStore();
                                Q.each(B, function(_, A) {
                                    Q.each(_, function(A, $) {
                                        if (!Ext.isEmpty(A) && /((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(A))
                                            _[$] = Date.parseDate(A, "Y-m-d H:i:s")
                                    });
                                    var $ = new L.recordType(_);
                                    $.id = _[L.idProperty];
                                    L.add($)
                                });
                                if (!Ext.isEmpty(G.items[1])) {
                                    var F = _[R.tabClassName], O = H.getCurrentTabChildGrid(G.items[1]);
                                    Q.each(F, function($, _) {
                                        Q.each(O, function(G, J) {
                                            var A = G.getXType() || G.xtype;
                                            if (A != "formpanel" && A != "form") {
                                                H.setEditState(G, I);
                                                var F = new Ext.data.JsonStore({
                                                    fields : G.saveField,
                                                    autoLoad : false
                                                });
                                                if (typeof G.foreignKey == "undefined")
                                                    return;
                                                var _ = G.foreignKey.substring(G.foreignKey.indexOf("_") + 1), D = "" + $[_], B = $[G.tabClassName];
                                                Q.each(B, function($, _) {
                                                    Q.each($, function(_, $) {
                                                        if (!Ext.isEmpty(_) && /((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_))
                                                            C[$] = Date.parseDate(_, "Y-m-d H:i:s")
                                                    });
                                                    F.add(new F.recordType($))
                                                });
                                                G.storeTemp[D] = F
                                            } else {
                                                var E;
                                                if (Ext.isEmpty(E))
                                                    E = new Ext.data.JsonStore({
                                                        fields : G.saveField,
                                                        autoLoad : false
                                                    });
                                                if (typeof G.foreignKey == "undefined")
                                                    return;
                                                _ = G.foreignKey.substring(G.foreignKey.indexOf("_") + 1), D = "" + $[_], B = $[G.tabClassName];
                                                Q.each(B, function($, _) {
                                                    Q.each($, function(_, $) {
                                                        if (!Ext.isEmpty(_) && /((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_))
                                                            C[$] = Date.parseDate(_, "Y-m-d H:i:s")
                                                    });
                                                    E.add(new E.recordType($))
                                                });
                                                G.storeTemp[D] = E
                                            }
                                        })
                                    })
                                }
                                if (J)
                                    vp.renderUploadFile(rr.get("uploadFileGroupId"), rr, "uploadFile4View", "", R)
                            }
                        })
                })
            },
            failure : function(_, A) {
                Ext.getBody().unmask();
                if (A && A.result)
                    Q.error(A.result.info || $("message.load.failure"));
                else
                    Q.error($("message.load.failure") + "<br/><br/>" + $("message.system.disconnect"))
            }
        });
        H.viewType = I
    },
    loadDtlDtlStore : function(_, $) {
        Q.each(_, function(G, L) {
            var _ = "" + $.id, A = G.getXType() || G.xtype;
            if (A != "formpanel" && A != "form") {
                var J = G.getColumnModel().findColumnIndex("uploadFile4View"), D = G.getStore(), F = new Ext.data.JsonStore({
                    fields : G.saveField
                });
                if (typeof G.foreignKey == "undefined")
                    return;
                D.setBaseParam("filter_EQ_" + G.foreignKey, $.id);
                var I = D.url;
                D.proxy = new Ext.data.HttpProxy({
                    url : I
                });
                D.load({
                    callback : function($) {
                        Q.each($, function($, _) {
                            F.add($)
                        });
                        G.storeTemp[_] = F;
                        D.removeAll()
                    }
                })
            } else {
                var C = G.getForm(), D;
                if (C.el) {
                    var B = C.getValues(), K = [];
                    Q.each(B, function($, _) {
                        K.push(_)
                    });
                    var E = [], H = G.findByType("label");
                    Q.each(H, function($, _) {
                        E.push($.name);
                        K.push($.name)
                    });
                    if (Ext.isEmpty(D))
                        D = new Ext.data.JsonStore({
                            url : G.loadUrl,
                            fields : K,
                            autoLoad : false
                        });
                    if (!Ext.isEmpty(G.loadUrl)) {
                        if (typeof G.foreignKey != "undefined")
                            D.setBaseParam("filter_EQ_" + G.foreignKey, $.id);
                        D.load({
                            callback : function($) {
                                G.storeTemp[_] = D
                            }
                        })
                    }
                }
            }
        })
    },
    createTabsDtlFn : function(B, _) {
        var A = this, $ = [];
        Q.each(B, function(D, F) {
            if (D instanceof Array) {
                if (D.length > 0 && D.length == 1) {
                    D.index = F;
                    D.tabType = "parent";
                    var B = A.buildPanl(D);
                    $.push(B)
                } else if (D.length > 1) {
                    D[0].index = F;
                    D[0].tabType = "parent";
                    var B = A.buildPanl(D[0]), C = B.items[0].getXType() || B.items[0].xtype;
                    if (C != "formpanel" && C != "form")
                        A.initChangeEvent(B.items[0], _);
                    var E = new Ext.TabPanel({
                        activeTab : 0,
                        region : "east",
                        width : 480,
                        frame : true,
                        items : []
                    });
                    Q.each(D, function(_, B) {
                        if (B > 0) {
                            _.title = _.tabTitle;
                            _.index = B - 1;
                            _.tabType = "child";
                            var _ = A.dealAccessControl(_), $;
                            if (typeof _.xtype != "undefined" && (_.xtype == "uxgrid" || _.xtype == "grid")) {
                                A.dealSaveField(_);
                                _ = A.createGroupStore(_);
                                $ = new Ext.ux.grid.GridPanel(_)
                            } else if (typeof _.xtype != "undefined" && (_.xtype == "uxeditorgrid" || _.xtype == "editorgrid")) {
                                A.dealSaveField(_);
                                _ = A.createGroupStore(_);
                                $ = new Ext.ux.grid.EditorGridPanel(_)
                            } else if (typeof _.xtype != "undefined" && (_.xtype == "formpanel")) {
                                A.dealSaveField(_);
                                _.readOnlyFiledArr = [];
                                A.dealDtlDtlFormItems(_);
                                $ = new Ext.form.FormPanel(_);
                                $.store = A.getDetailFormStore($);
                                $.storeTemp = {};
                                A.initReadOnlyField($)
                            } else if (typeof _.xtype != "undefined" && (_.xtype == "panel")) {
                                A.dealSaveField(_);
                                $ = new Ext.Panel(_)
                            } else {
                                A.dealSaveField(_);
                                $ = _
                            }
                            $.storeTemp = {};
                            A.compArr[$.tabClassName] = $;
                            $.addEvents("afterValid");
                            E.add($)
                        }
                    });
                    B.items.push(E)
                }
                $.push(B)
            } else {
                D.index = F;
                D.tabType = "parent";
                B = A.buildPanl(D);
                $.push(B)
            }
        });
        return $
    },
    createTabPanelFn : function($) {
        var _ = this, A = this.tabPanel = new Ext.TabPanel({
            activeTab : 0,
            deferredRender : false,
            region : "center",
            border : false,
            items : $
        });
        return A
    },
    createPanelFn : function(D, B, E, _) {
        var A = this, C = A.configVar.editWin, $ = this.panel = new Ext.Panel({
            activeTab : 0,
            region : A.hasFlag ? "south" : "center",
            layout : "border",
            border : false,
            items : [ {
                region : "center",
                name : "center",
                border : false,
                defaults : {
                    style : "padding:0px 1px 0px 1px"
                },
                layout : "fit",
                collapsible : C.center && C.center.collapsible || false,
                items : D || []
            }, {
                region : "south",
                defaults : {
                    style : "padding:1px 0px 0px 0px"
                },
                name : "south",
                border : false,
                layout : "fit",
                collapsible : C.southTab && C.southTab.collapsible || false,
                width : C.southTab && C.southTab.width || 0,
                height : C.southTab && C.southTab.height || 0,
                items : B || []
            }, {
                region : "west",
                name : "west",
                layout : "fit",
                border : false,
                collapsible : C.westTab && C.westTab.collapsible || false,
                defaults : {
                    style : "padding:0px 1px 0px 0px"
                },
                width : C.westTab && C.westTab.width || 0,
                height : C.westTab && C.westTab.height || 0,
                items : E || []
            }, {
                region : "east",
                name : "east",
                border : false,
                layout : "fit",
                collapsible : C.eastTab && C.eastTab.collapsible || false,
                width : C.eastTab && C.eastTab.width || 0,
                height : C.eastTab && C.eastTab.height || 0,
                defaults : {
                    style : "padding:0px 0px 0px 1px"
                },
                items : _ || []
            } ]
        });
        return $
    },
    buildPanl : function(A) {
        var _, B = this, A = B.dealAccessControl(A);
        if (typeof A.xtype != "undefined" && (A.xtype == "uxgrid" || A.xtype == "grid")) {
            B.dealSaveField(A);
            if (!Ext.isEmpty(A.isGroupGrid) && A.isGroupGrid === true)
                A = B.createGroupStore(A);
            var C = {
                border : false,
                region : "center",
                height : 300,
                sm : A.sm || {
                    singleSelect : true
                },
                viewConfig : A.viewConfig || {
                    forceFit : true
                },
                pageSize : A.pageSize || 0
            };
            A.store.remoteSort = false, A = Ext.apply(C, A);
            _ = new Ext.ux.grid.GridPanel(A)
        } else if (typeof A.xtype != "undefined" && (A.xtype == "uxeditorgrid" || A.xtype == "editorgrid")) {
            B.dealSaveField(A);
            if (!Ext.isEmpty(A.isGroupGrid) && A.isGroupGrid === true)
                A = B.createGroupStore(A);
            A.cm;
            C = {
                border : false,
                region : "center",
                height : 300,
                sm : A.sm || {
                    singleSelect : true
                },
                viewConfig : A.viewConfig || {
                    forceFit : true
                },
                pageSize : A.pageSize || 0
            };
            A.store.remoteSort = false, A = Ext.apply(C, A);
            _ = new Ext.ux.grid.EditorGridPanel(A)
        } else if (typeof A.xtype != "undefined" && (A.xtype == "formpanel")) {
            B.dealDtlDtlFormItems(A);
            B.dealSaveField(A);
            A.title = "";
            A.readOnlyFiledArr = [];
            _ = new Ext.form.FormPanel(A);
            if (_.tabType == "parent")
                _.store = B.getFormStore(_);
            else
                _.store = B.getDetailFormStore(_);
            B.initReadOnlyField(_)
        } else if (typeof A.xtype != "undefined" && (A.xtype == "panel")) {
            B.dealSaveField(A);
            _ = new Ext.Panel(A)
        } else {
            B.dealSaveField(A);
            _ = A
        }
        B.compArr[_.tabClassName] = _;
        _.addEvents("afterValid");
        var $ = {
            xtype : "panel",
            index : 1,
            height : 300,
            title : _.tabTitle,
            layout : "border",
            items : [ _ ]
        };
        return $
    },
    dealAccessControl : function(A) {
        var C = this, B = C.configVar, _ = B.accessControl || {}, $;
        if (_[A.tabClassName])
            $ = _[A.tabClassName];
        if ($ && typeof A.xtype != "undefined" && (A.xtype == "uxgrid" || A.xtype == "grid" || A.xtype == "uxeditorgrid" || A.xtype == "editorgrid"))
            Q.each($, function($, _) {
                Q.each($, function($, C) {
                    var B = A.cm.columns;
                    Q.each(B, function(A, B) {
                        if (A.dataIndex == C)
                            A[_] = $
                    })
                })
            });
        else if ($ && typeof A.xtype != "undefined" && (A.xtype == "formpanel"))
            Q.each($, function($, _) {
                Q.each($, function($, C) {
                    var B = A.items;
                    Q.each(B, function(A, B) {
                        if (A.name == C)
                            A[_] = $
                    })
                })
            });
        C.dealfieldName(A);
        return A
    },
    dealfieldName : function(_) {
        var A = this, B = {};
        if (typeof _.xtype != "undefined" && (_.xtype == "uxgrid" || _.xtype == "grid" || _.xtype == "uxeditorgrid" || _.xtype == "editorgrid")) {
            var $ = _.cm.columns;
            Q.each($, function($, _) {
                if (!Ext.isEmpty($.dataIndex))
                    B[$.dataIndex] = $.header
            })
        }
        _.fieldName = B;
        return _
    },
    dealSaveField : function(A) {
        var _ = [], $ = A.xtype;
        if (typeof A.saveField != "undefined" && A.saveField.length > 0)
            ;
        else if ($ == "uxgrid" || $ == "grid" || $ == "uxeditorgrid" || $ == "editorgrid") {
            Q.each(A.cm.columns, function($, B) {
                if (typeof A.unSaveField != "undefined" && A.unSaveField.length > 0) {
                    if (A.unSaveField.indexOf($.dataIndex) == -1)
                        _.push($.dataIndex)
                } else
                    _.push($.dataIndex)
            });
            A.saveField = _
        }
    },
    dealDtlDtlFormItems : function(_) {
        var A = [], $ = _.items || [], B = this.formColumnWidth = _.columnWidth || 0.5;
        if ($)
            Q.each($, function(_, C) {
                if (!(typeof _.fieldUnDisplay != "undefined" && _.fieldUnDisplay.indexOf("form") > -1)) {
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : _.columnWidth || B,
                        items : [ _ ]
                    };
                    A.push($)
                }
            });
        _.height = _.height || 300;
        _.labelWidth = _.labelWidth || 110, _.layout = "column", _.region = _.region || "center", _.autoScroll = _.autoScroll || true, _.bodyStyle = "padding:5px", _.readOnlyFiledArr = [], _.defaults = {
            columnWidth : 1,
            layout : "form",
            border : false
        }, _.items = A
    },
    setBtnState : function(F, G, B) {
        var E = this;
        E.viewType = F;
        var _ = E.editFlag = ((typeof this.configVar.editFlag == "undefined" || F == "add" || F == "addEdit") ? true : this.configVar.editFlag) && F != "view";
        if (!Ext.isEmpty(B))
            _ = E.editFlag = B;
        var $ = E.buttons;
        Q.each($, function($, A) {
            if ($.name == "return" || (_ && !(E.maximized)))
                $.show();
            else
                $.hide()
        });
        var D = E.formPanel.findByType(Ext.Button);
        Ext.each(D, function($, A) {
            if (_ && !E.readOnlyBtnArr.contains($))
                $.setDisabled(false);
            else
                $.setDisabled(true)
        });
        var C = E.formPanel.getTopToolbar();
        if (E.configVar.editWin.maximized)
            C.setVisible(true);
        else
            C.setVisible(false);
        var A = C.items;
        A.each(function($) {
            if (F == "add" && ($.name == "TOPASS" || $.name == "TONOPASS"))
                $.hide();
            if (!Ext.isEmpty($.powerEffect) && (E.maximized)) {
                if (E.viewType != "add" && $.displayType && $.displayType.indexOf(E.viewType) > -1) {
                    if ($.powerEffect) {
                        if (!Ext.isEmpty(E.configVar.editBtnDisabled) && E.configVar.editBtnDisabled[$.name])
                            $.show();
                        else
                            $.hide()
                    } else if ($.displayType && $.displayType.indexOf(E.viewType) > -1)
                        $.show();
                    else
                        $.hide()
                } else if ($.displayType && $.displayType.indexOf(E.viewType) > -1)
                    $.show();
                else
                    $.hide()
            } else if (F == "view" && E.maximized) {
                if ($.name == "return")
                    $.show();
                else
                    $.hide()
            } else if ($.name == "save" || $.name == "submit" || $.name == "return")
                $.show()
        });
        if (!Ext.isEmpty(G) && G.length > 0) {
            Q.each($, function($, _) {
                if (G.indexOf($.name) > -1)
                    $.hide()
            });
            A.each(function($) {
                if (G.indexOf($.name) > -1)
                    $.hide()
            })
        }
        E.setDisabledChildTopToolbar(_);
        E.doLayout();
        E.formPanel.doLayout()
    },
    formTabInitHeight : "",
    readOnlyFiledArr : [],
    readOnlyBtnArr : [],
    initReadOnlyField : function($) {
        Array.prototype.contains = function($) {
            for (var _ = 0; _ < this.length; _++)
                if (this[_] == $)
                    return true;
            return false
        };
        var A = this, _ = $.findByType(Ext.form.Field);
        if ($.readOnlyFiledArr.length <= 0)
            Ext.each(_, function(_) {
                if (_.readOnly)
                    $.readOnlyFiledArr.push(_)
            })
    },
    setEditState : function(_, C) {
        var B = this, $ = B.editFlag, A = _.findByType(Ext.form.Field);
        Ext.each(A, function(A) {
            if ($ && !_.readOnlyFiledArr.contains(A)) {
                if (A.getXType() == "checkboxgroup" || A.getXType() == "datefield" || A.getXType() == "uxcombo" || A.getXType() == "uxtrigger")
                    A.setDisabled(false);
                else
                    A.setReadOnly(false)
            } else if (A.getXType() == "checkboxgroup" || A.getXType() == "datefield" || A.getXType() == "uxcombo" || A.getXType() == "uxtrigger")
                A.setDisabled(true);
            else
                A.setReadOnly(true)
        })
    },
    resetWinState : function($) {
        var _ = this;
        _.viewType = "add";
        _.btnType = "add";
        Q.each(_.compArr, function(B, C) {
            var A = B.getXType() || B.xtype;
            if (A == "formpanel" || A == "form") {
                _.setBtnState("add", $);
                _.setEditState(B, "add")
            }
        })
    },
    getEvents : function(A, E, C) {
        var $ = "";
        if (!Ext.isEmpty(A))
            if (typeof A == "object")
                $ = A.id;
            else
                $ = A;
        var D = this, B = D.formPanel, _ = B.getTopToolbar().items;
        _.each(function($) {
            if ($.name != "return" && Ext.isEmpty($.powerEffect)) {
                $.hide();
                if (E != "view")
                    if ($.name == "save" || $.name == "submit")
                        if (!Ext.isEmpty(C) && C.indexOf($.name))
                            $.hide();
                        else
                            $.show()
            }
        });
        if (Ext.isEmpty(A) || !D.configVar.isAudit)
            return;
        Ext.Ajax.request({
            url : this.dealUrl + "_getEvents.action",
            method : "post",
            params : {
                "id" : $
            },
            success : function($) {
                var A = Ext.decode($.responseText);
                Q.each(A, function(A, $) {
                    if (A.indexOf("#") > 0)
                        D.vpWin.flowInfo.flowInfoId = A.split("#")[1];
                    _.each(function($) {
                        if ($.name == A)
                            $.show()
                    })
                })
            }
        })
    },
    writeInfo : function(_, $) {
        if (!Ext.isIE)
            switch (_) {
            case "info":
                console.info($);
                break;
            case "warn":
                console.warn($);
                break;
            case "error":
                console.error($);
                break;
            case "debug":
                console.debug($);
                break
            }
    },
    setFormReadOnlyFields : function(_, $) {
        var A = this;
        if (_.length > 0)
            Q.each(_, function(_, B) {
                A.formPanel.form.findField(_).setReadOnly($)
            })
    },
    initChangeEvent : function(B, _) {
        var A = this, $ = B.getSelectionModel();
        $.on("selectionchange", function(C) {
            var $ = C.getSelected();
            if (Ext.isEmpty($))
                return;
            A.initSunGrid(B, $, false, _)
        })
    },
    initSunGrid : function(G, _, A, C) {
        var E = this, F = E.viewType, B = E.configTabPanel[C].getActiveTab(), D = B.items.last().items.items;
        Q.each(D, function(C, D) {
            var A = C.getXType() || C.xtype;
            if (A != "formpanel" && A != "form")
                $(G, C, _);
            else {
                var B = C;
                E.setEditState(B, F);
                I(G, B, _)
            }
        });
        function $(F, E, B) {
            var C = E.getStore(), _;
            _ = "" + B.id;
            var $ = B.get(F.store.idProperty);
            if (typeof E.foreignKey == "undefined")
                return;
            C.baseParams["filter_EQ_" + E.foreignKey] = $;
            if (Ext.isEmpty(E.storeTemp[_])) {
                if (Ext.isEmpty($)) {
                    var D = new Ext.data.JsonStore({
                        fields : E.saveField
                    });
                    E.storeTemp[_] = D;
                    C.removeAll()
                } else {
                    var G = E.getColumnModel().findColumnIndex("uploadFile4View");
                    C.load({
                        callback : function(B) {
                            var $ = new Ext.data.JsonStore({
                                fields : E.saveField
                            });
                            Q.each(B, function(_, A) {
                                $.add(_)
                            });
                            E.storeTemp[_] = $;
                            if (A)
                                C.removeAll()
                        }
                    })
                }
            } else {
                C.removeAll();
                var H = E.storeTemp[_];
                H.each(function($, _) {
                    C.add($)
                })
            }
        }
        function H($, _, A, D) {
            if (_) {
                var B = _.data;
                B = Ext.apply(B, {});
                var C = {};
                Q.each(B, function($, _) {
                    C[_] = $;
                    if (!Ext.isEmpty(A) && A.length > 0 && A.indexOf(_) != -1)
                        Q.each(D, function(A, B) {
                            if (A.name == _)
                                A.setText($)
                        })
                });
                $.setValues(C)
            } else
                $.reset()
        }
        function I(J, F, A) {
            var C = F.store, B = F.getForm(), _;
            _ = "" + A.id;
            var $ = A.get(J.store.idProperty);
            if (typeof F.foreignKey == "undefined")
                return;
            C.baseParams["filter_EQ_" + F.foreignKey] = $;
            if (Ext.isEmpty(F.storeTemp[_])) {
                if (Ext.isEmpty($)) {
                    var G = E.getDetailFormStore(F);
                    G.add(new G.recordType(G.recordTypeTemp));
                    F.storeTemp[_] = G;
                    C.removeAll();
                    B.reset();
                    var D = [], I = F.findByType("label");
                    Q.each(I, function($, _) {
                        D.push($.name)
                    });
                    G.each(function($, _) {
                        H(B, $, D, I)
                    })
                } else if (Ext.isEmpty(F.loadUrl)) {
                    G = E.getDetailFormStore(F);
                    G.add(new G.recordType(G.recordTypeTemp));
                    G.each(function($, _) {
                        H(B, $)
                    });
                    F.storeTemp[_] = G
                } else
                    C.load({
                        callback : function(D) {
                            var A = E.getDetailFormStore(F), $ = [], C = F.findByType("label");
                            Q.each(C, function(_, A) {
                                $.push(_.name)
                            });
                            Q.each(D, function(_, D) {
                                A.add(_);
                                H(B, _, $, C)
                            });
                            F.storeTemp[_] = A
                        }
                    })
            } else {
                C.removeAll();
                var K = F.storeTemp[_], D = [], I = F.findByType("label");
                Q.each(I, function($, _) {
                    D.push($.name)
                });
                K.each(function($, _) {
                    C.add($);
                    H(B, $, D, I)
                })
            }
        }
    },
    getParentGrid : function(A) {
        var B = this, D, $ = A.findParentByType(Ext.TabPanel), C = $.findParentByType(Ext.TabPanel);
        if (Ext.isEmpty(C))
            return null;
        var _ = C.getActiveTab();
        D = _.items.first();
        return D
    },
    addDetail : function(D, C) {
        var F = this, A = D.getStore(), G = A.getCount() + 1, E = false, $, _ = D.getColumnModel().findColumnIndex("uploadFile4View");
        if (_ != -1) {
            var B = {
                "rowNo" : G,
                "uploadFileGroupId" : "",
                "uploadFile4View" : ""
            };
            if (C)
                Ext.apply(B, C);
            $ = new A.recordType(B)
        } else {
            B = {
                "rowNo" : G
            };
            if (C)
                Ext.apply(B, C);
            $ = new A.recordType(B)
        }
        A.add($);
        F.setFormReadOnlyFields(D.formFieldReadyArr || [], true)
    },
    deleteDetail : function(E) {
        var F = this, D = E.getSelectionModel().getSelections(), A = E.getStore();
        if (D.length < 1)
            Q.tips("<font color='blue'>" + $("message.delete.select") + "</font>");
        var _ = E.findParentByType(Ext.TabPanel), B = _.getActiveTab();
        if (B.items.last().items && B.items.last().items.length > 0) {
            var C = B.items.last().items.items;
            Q.each(C, function(_, A) {
                var $ = _.getXType() || _.xtype;
                if ($ != "formpanel" && $ != "form") {
                    _.getStore().removeAll();
                    Q.each(D, function(A, B) {
                        var $ = A, C = F.getStoreTempKey(_, $);
                        delete _.storeTemp[C]
                    })
                }
            })
        }
        A.remove(D);
        A.each(function($) {
            $.set("rowNo", A.indexOf($) + 1)
        });
        A.commitChanges();
        E.getView().refresh();
        if (A.getCount() == 0)
            F.setFormReadOnlyFields(E.formFieldReadyArr || [], false)
    },
    deleteChildDetail : function(D) {
        var E = this, C = D.getSelectionModel().getSelections(), B = D.getStore();
        if (C.length < 1)
            Q.tips("<font color='blue'>" + $("message.delete.select") + "</font>");
        B.remove(C);
        B.each(function($) {
            $.set("rowNo", B.indexOf($) + 1)
        });
        B.commitChanges();
        D.getView().refresh();
        var F = E.getParentGrid(D), A = F.getSelectionModel().getSelected(), _ = E.getStoreTemp(D, A);
        _.remove(C);
        _.each(function($) {
            $.set("rowNo", _.indexOf($) + 1)
        });
        _.commitChanges()
    },
    addChildDetail : function(H, F) {
        var J = this, K = J.getParentGrid(H), G = K.getSelectionModel().getSelections();
        if (G.length != 1) {
            Q.tips("<font color='blue'>" + $("message.pleaseSelect") + "</font>");
            return false
        }
        var C = H.getStore(), B;
        if (!Ext.isEmpty(C.getAt(C.getCount() - 1)))
            B = C.getAt(C.getCount() - 1).get("rowNo");
        else
            B = 0;
        var E = parseInt(B) + 1, I = false, _;
        Q.each(H.getColumnModel().columns, function($, _) {
            if ($.dataIndex == "uploadFile4View") {
                I = true;
                return false
            }
        });
        if (I) {
            var D = {
                "rowNo" : E,
                "uploadFileGroupId" : "",
                "uploadFile4View" : ""
            };
            if (F)
                Ext.apply(D, F);
            _ = new C.recordType(D)
        } else {
            D = {
                "rowNo" : E
            };
            if (F)
                Ext.apply(D, F);
            _ = new C.recordType(D)
        }
        C.add(_);
        var A = J.getStoreTemp(H, G[0]);
        A.add(_);
        H.getView().refresh()
    },
    uploadDoc : function(D, A) {
        var E = this, C = D.getSelectionModel().getSelections(), _ = D.getStore();
        if (C.length != 1) {
            Q.tips("<font color='blue'>" + $("message.onlySelect") + "</font>");
            return
        }
        if (Ext.isEmpty(A)) {
            var B = C[0].get("uploadFileGroupId");
            E.vpWin.openUploadWindows(C[0], B, "uploadFile4View", "", D)
        } else
            E.vpWin.openUploadWindows(C[0], A, A + "View", "", D)
    },
    afterEditChildDetail : function(C) {
        var E = this, $ = C.record, D = C.grid, F = E.getParentGrid(D), B = F.getSelectionModel().getSelected(), A = E.getStoreTemp(D, B);
        if (!Ext.isEmpty(A)) {
            var _ = A.indexOf($);
            if (_ > -1)
                A.insert(_, $)
        }
        C.grid.getView().refresh()
    },
    beforeedit : function($) {
        var _ = this;
        return _.editFlag ? true : false
    },
    getCurrentTabChildGrid : function($) {
        var _;
        _ = $.items.items;
        return _
    },
    getAllParent : function() {
        var _ = this, $ = [];
        Q.each(_.configTab, function(A, _) {
            if (A.length > 0)
                Q.each(A, function(_, A) {
                    Q.each(_.items, function(_, A) {
                        if (A == 0)
                            $.push(_)
                    })
                })
        });
        return $
    },
    getAllChild : function() {
        var _ = this, $ = [];
        Q.each(_.configTab, function(A, _) {
            if (A.length > 0)
                Q.each(A, function(_, A) {
                    Q.each(_.items, function(_, A) {
                        if (A > 0)
                            if (!Ext.isEmpty(_.items) && _.items.length > 0)
                                Q.each(_.items.items, function(_, A) {
                                    $.push(_)
                                })
                    })
                })
        });
        return $
    },
    getDtlAllChild : function(_) {
        var A = this, $ = [];
        if (_.length > 0)
            Q.each(_, function(_, A) {
                Q.each(_.items, function(_, A) {
                    if (A > 0)
                        if (!Ext.isEmpty(_.items) && _.items.length > 0)
                            Q.each(_.items.items, function(_, A) {
                                $.push(_)
                            })
                })
            });
        return $
    },
    setDisabledChildTopToolbar : function($) {
        var _ = this, B = _.getAllParent(), A = _.getAllChild();
        Q.each(B, function(A, B) {
            var _ = A.getXType() || A.xtype;
            if (_ == "uxgrid" || _ == "grid" || _ == "uxeditorgrid" || _ == "editorgrid") {
                if (!Ext.isEmpty(A.getTopToolbar()))
                    A.getTopToolbar().setDisabled(!$)
            } else if (_ == "formpanel" || _ == "form")
                if (!Ext.isEmpty(A.getTopToolbar()))
                    A.getTopToolbar().setDisabled(!$)
        });
        Q.each(A, function(A, B) {
            var _ = A.getXType() || A.xtype;
            if (_ == "uxgrid" || _ == "grid" || _ == "uxeditorgrid" || _ == "editorgrid") {
                if (!Ext.isEmpty(A.getTopToolbar()))
                    A.getTopToolbar().setDisabled(!$)
            } else if (_ == "formpanel" || _ == "form")
                if (!Ext.isEmpty(A.getTopToolbar()))
                    A.getTopToolbar().setDisabled(!$)
        })
    },
    getDetailFormStore : function(C) {
        var F = this, A, G = [], E = {};
        function B(_) {
            Q.each(_, function(_, A) {
                G.push(_.name);
                E[_.name] = "";
                _.on("change", function(H, E, G) {
                    var D = F.getParentGrid(C), B;
                    if (!Ext.isEmpty(D)) {
                        B = D.getSelectionModel().getSelected();
                        if (Ext.isEmpty(B)) {
                            Q.tips("<font color ='blue'>" + $("message.pleaseSelectDtl") + "</font>");
                            _.setValue(G);
                            return
                        }
                        var A = F.getStoreTemp(C, B);
                        A.each(function($) {
                            $.set(H.getName(), E)
                        })
                    }
                })
            })
        }
        var _ = C.findByType(Ext.form.Field);
        B(_);
        var D = C.findByType("label");
        B(D);
        if (Ext.isEmpty(A))
            A = new Ext.data.JsonStore({
                url : C.loadUrl || "",
                fields : G,
                autoLoad : false
            });
        C.saveField = G;
        A.recordTypeTemp = E;
        return A
    },
    getFormStore : function(C) {
        var F = this, _, G = [], E = {}, A = F.storeMain;
        function B($) {
            Q.each($, function($, _) {
                G.push($.name);
                E[$.name] = "";
                if (C.tabClassName == "mainFormPanel" || (!Ext.isEmpty(C.belongParent) && C.belongParent))
                    if (!Ext.isEmpty($.name) && A.fields.items.indexOf(($.name).substring(6)) == -1) {
                        A.fields.items.push(($.name).substring(6));
                        A.recordTypeTemp[($.name).substring(6)] = ""
                    }
            })
        }
        var $ = C.findByType(Ext.form.Field);
        B($);
        var D = C.findByType("label");
        B(D);
        if (Ext.isEmpty(_))
            _ = new Ext.data.JsonStore({
                url : C.loadUrl || "",
                fields : G,
                autoLoad : false
            });
        C.saveField = G;
        _.recordTypeTemp = E;
        return _
    },
    getCompByTabClassName : function($) {
        var A = this, B = A.compArr, _;
        Q.each(B, function(A, B) {
            if (A.tabClassName == $) {
                _ = A;
                return false
            }
        });
        return _
    },
    setActiveTab : function(A) {
        var B = this, D = [], C, _ = [], E;
        Q.each(A, function($, F) {
            var A = B.getCompByTabClassName($);
            if (A && A.tabType == "parent") {
                if (Ext.isEmpty(C))
                    C = A.findParentByType(Ext.TabPanel);
                D.push($)
            } else if (A && A.tabType == "child") {
                if (Ext.isEmpty(C))
                    E = A.findParentByType(Ext.TabPanel);
                _.push($)
            }
        });
        var $ = B.compArr;
        if (!Ext.isEmpty($) && !Ext.isEmpty(D) && D.length > 0)
            Q.each($, function($, B) {
                var _ = $.tabClassName, A = $.findParentByType(Ext.TabPanel);
                if ($ && $.tabType == "parent" && D.indexOf(_) == -1 && A.getId() == C.getId()) {
                    C.setActiveTab($.index);
                    return false
                }
            });
        if (!Ext.isEmpty($) && !Ext.isEmpty(_) && _.length > 0)
            Q.each($, function($, C) {
                var A = $.tabClassName, B = $.findParentByType(Ext.TabPanel);
                if ($ && $.tabType == "child" && _.indexOf(A) == -1 && B.getId() == E.getId()) {
                    E.setActiveTab($.index);
                    return false
                }
            })
    },
    hideTabItem : function(_) {
        var B = this, A = B.getCompByTabClassName(_);
        if (A) {
            var $ = A.findParentByType(Ext.TabPanel);
            A.hide();
            $.hideTabStripItem(A.index);
            A.hideTabItem = true
        }
    },
    showTabItem : function(_) {
        var B = this, A = B.getCompByTabClassName(_);
        if (A) {
            var $ = A.findParentByType(Ext.TabPanel);
            A.show();
            $.unhideTabStripItem(A.index);
            A.hideTabItem = false
        }
    },
    getStoreTemp : function(_, B) {
        var $, A;
        $ = "" + B.id;
        A = _.storeTemp[$];
        return A
    },
    getStoreTempKey : function(_, B) {
        var $, A;
        $ = "" + B.id;
        return $
    },
    getChildStoreAll : function(_) {
        var A = this, C = [], B = A.getParentGrid(_), $ = B.getStore();
        $.each(function(B) {
            var $ = A.getStoreTemp(_, B);
            $.each(function($) {
                C.push($)
            })
        });
        return C
    },
    getChildStoreByRecord : function(A, _) {
        var B = this, C = [], $ = B.getStoreTemp(A, _);
        $.each(function($) {
            C.push($)
        });
        return C
    },
    hiddenTab : function($) {
        var _ = this;
        _.hiddenTabOut = $
    },
    setFormArrReadOnlyFields : function(_, $, C) {
        if (Ext.isEmpty(C))
            C = false;
        var B = this, A = [];
        if (typeof _ == "string")
            A.push(_);
        else
            A = _;
        if (A.length > 0)
            Q.each(A, function(A, F) {
                var E = B.getCompByTabClassName(A);
                if (!Ext.isEmpty(E)) {
                    var _ = E.getXType() || E.xtype;
                    if ((_ == "formpanel" || _ == "form")) {
                        var D = E.findByType(Ext.form.Field);
                        Ext.each(D, function(_) {
                            if (!E.readOnlyFiledArr.contains(_) || C)
                                if (_.getXType() == "checkboxgroup" || _.getXType() == "datefield" || _.getXType() == "uxcombo" || _.getXType() == "uxtrigger") {
                                    if (!$) {
                                        if (_.readOnly === true)
                                            _.setReadOnly($);
                                        else if (_.disabled === true)
                                            _.setDisabled($)
                                    } else
                                        _.setDisabled($)
                                } else
                                    _.setReadOnly($)
                        })
                    }
                }
            })
    },
    setFieldArrReadOnly : function(C, A, _) {
        var E = this, B = [];
        if (typeof A == "string")
            B.push(A);
        else
            B = A;
        if (B.length > 0) {
            var D = E.getCompByTabClassName(C), $ = D.getXType() || D.xtype;
            if (!Ext.isEmpty(D))
                if (($ == "formpanel" || $ == "form"))
                    Q.each(B, function(A, B) {
                        var $ = D.getForm().findField(A);
                        if ($.getXType() == "checkboxgroup" || $.getXType() == "datefield" || $.getXType() == "uxcombo" || $.getXType() == "uxtrigger") {
                            if (!_) {
                                if ($.readOnly === true)
                                    $.setReadOnly(_);
                                else if ($.disabled === true)
                                    $.setDisabled(_)
                            } else
                                $.setDisabled(_)
                        } else
                            $.setReadOnly(_)
                    })
        }
    },
    createGroupStore : function(A) {
        var C = this, D = C.getGridFields(A), _ = new Ext.data.JsonReader({
            fields : D
        }), B = {
            reader : _
        };
        B = Ext.apply(B, A.store);
        var $ = new Ext.data.GroupingStore(B);
        A.store = $;
        A.view = new Ext.grid.GroupingView(A.view);
        return A
    },
    getGridFields : function(_) {
        var A = [], $ = _.cm.columns;
        Q.each($, function(_, B) {
            var $ = {};
            $.name = _.dataIndex;
            A.push($)
        });
        return A
    },
    dealGridDynamicColumn : function(D, C, A) {
        var _ = new Ext.grid.CheckboxSelectionModel(), F = [];
        if (!Ext.isEmpty(D.isGroupGrid) && D.isGroupGrid === true) {
            var G = [];
            Q.each(C, function($, _) {
                field = {};
                field.name = $.dataIndex;
                G.push(field);
                $ = Ext.applyIf($, {
                    menuDisabled : true
                });
                F.push($)
            });
            var $ = new Ext.data.JsonReader({
                fields : G
            }), A = Ext.apply(A, {
                reader : $
            }), B = new Ext.data.GroupingStore(A);
            D.reconfigure(B, new Ext.grid.ColumnModel(C))
        } else {
            G = [];
            Q.each(C, function($, _) {
                G.push($.dataIndex);
                $ = Ext.applyIf($, {
                    menuDisabled : true
                });
                F.push($)
            });
            var E = Ext.apply(A, {
                fields : G
            }), B = new Ext.data.JsonStore(E);
            D.reconfigure(B, new Ext.grid.ColumnModel(C))
        }
    },
    getDataComparisonRecord : function(_, A) {
        if (_ instanceof Array)
            Q.each(function(_, B) {
                Ext.Ajax.request({
                    url : win.dealUrl + ((Ext.isEmpty(A) || A == "" || Ext.isFunction(A)) ? "_findHistoryRecord.action" : "_" + A + ".action"),
                    params : {
                        "id" : _
                    },
                    success : function(A) {
                        var B = Ext.decode(A.responseText);
                        if (false === B.success) {
                            Q.error(B.info || $("message.delete.failure") + "<br/><br/>" + $("message.system.error"));
                            return
                        }
                        var C = B.data;
                        win["record" + _] = C
                    },
                    failure : function(_) {
                        var A = Ext.decode(_.responseText);
                        Ext.getBody().unmask();
                        if (A && A.data)
                            Q.error(A.data || $("message.load.failure"));
                        else
                            Q.error($("message.load.failure") + "<br/><br/>" + $("message.system.disconnect"))
                    },
                    callback : function() {
                        Ext.getBody().unmask()
                    }
                })
            });
        else
            Ext.Ajax.request({
                url : win.dealUrl + ((Ext.isEmpty(A) || A == "" || Ext.isFunction(A)) ? "_findHistoryRecord.action" : "_" + A + ".action"),
                params : {
                    "id" : _
                },
                success : function(A) {
                    var B = Ext.decode(A.responseText);
                    if (false === B.success) {
                        Q.error(B.info || $("message.delete.failure") + "<br/><br/>" + $("message.system.error"));
                        return
                    }
                    var C = B.data;
                    win["record" + _] = C;
                    console.info(win["record" + _])
                },
                failure : function(_) {
                    var A = Ext.decode(_.responseText);
                    Ext.getBody().unmask();
                    if (A && A.data)
                        Q.error(A.data || $("message.load.failure"));
                    else
                        Q.error($("message.load.failure") + "<br/><br/>" + $("message.system.disconnect"))
                },
                callback : function() {
                    Ext.getBody().unmask()
                }
            })
    },
    dataComparison : function() {
    },
    customAddBtn : function($, B) {
        var _ = this;
        _.resetWinState(B);
        _.show();
        var A = typeof $ != "undefined" && $;
        if (Ext.isFunction(A))
            A(_, _.formPanel)
    },
    customAddEditBtn : function($, F, C, A, B, _) {
        var D = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(F)) {
            C = F;
            F = ""
        }
        D.setFormValue($, "addEdit", F, A, B, _);
        D.show();
        var E = typeof C != "undefined" && C;
        if (Ext.isFunction(E))
            E(D, D.formPanel)
    },
    customEditBtn : function($, F, C, A, B, _) {
        var D = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(F)) {
            C = F;
            F = ""
        }
        D.setFormValue($, "edit", F, A, B, _);
        D.show();
        var E = typeof C != "undefined" && C;
        if (Ext.isFunction(E))
            E(D, D.formPanel)
    },
    customViewBtn : function($, C, _) {
        var A = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(C)) {
            _ = C;
            C = ""
        }
        A.setFormValue($, "view", C);
        A.show();
        var B = typeof _ != "undefined" && _;
        if (Ext.isFunction(B))
            B(A, A.formPanel)
    },
    customHistoryBtn : function($, B, D, _) {
        var A = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (typeof B != "object" || Ext.isFunction(C)) {
            Q.error("\u9519\u8bef\u8d4b\u503c\uff0c\u53c2\u6570\u5e94\u4e3ajson\u683c\u5f0f\u7684\u5bf9\u8c61");
            return
        }
        if (Ext.isFunction(D)) {
            _ = D;
            D = ""
        }
        A.setHistoryRecordValue($, "view", B, D);
        A.show();
        var C = typeof _ != "undefined" && _;
        if (Ext.isFunction(C))
            C(A, A.formPanel)
    },
    getImportWin : function(D) {
        var C = this, A = C.vpWin;
        D = D || {};
        D = Ext.apply({
            url : A.dealUrl + "_importExcel.action",
        }, D);
        var B = new Ext.ux.Window({
            title : $("message.tip"),
            closable : false,
            width : 550,
            height : 350,
            items : {
                xtype : "form",
                autoScroll : true,
                bodyStyle : "padding:5px 5px 0",
                items : [ {
                    xtype : "textarea",
                    fieldLabel : $("message.errorinfo"),
                    readOnly : true,
                    height : 265,
                    border : false,
                    anchor : "98%"
                } ],
                buttons : [ {
                    text : $("button.close"),
                    handler : function() {
                        B.close()
                    }
                } ]
            }
        }), _, E = new Ext.ux.Window({
            title : $("message.import"),
            closable : false,
            width : 450,
            height : 200,
            items : {
                xtype : "form",
                labelWidth : 90,
                bodyStyle : "padding:5px 5px 0",
                fileUpload : true,
                items : [ {
                    xtype : "fileuploadfield",
                    emptyText : $("message.selectafile"),
                    fieldLabel : $("label.detail"),
                    name : "excelFile",
                    anchor : "90%",
                    allowBlank : false,
                    blankText : $("message.selectafile"),
                    buttonText : "",
                    buttonCfg : {
                        iconCls : "icon-excel"
                    },
                    listeners : {
                        "fileselected" : function(_, A) {
                            if (!/\.xls$/.test(A)) {
                                Q.error($("message.uploadFileSuffixMustBe") + "\uff1a<font color='red'>.xls</font>");
                                _.reset()
                            }
                        }
                    }
                }, {
                    border : false,
                    bodyStyle : "padding:5px;",
                    html : "<div style='color:blue;'>* " + $("message.plaseWriteTemplateFile") + "</div>"
                } ],
                buttons : [ {
                    text : $("button.upload"),
                    handler : function(A) {
                        if (!_.isValid())
                            return;
                        var C = {};
                        E.fireEvent("sumbitBefore", C);
                        _.submit({
                            url : D.url,
                            waitTitle : $("message.fielupload"),
                            waitMsg : $("message.import.wait"),
                            method : "POST",
                            params : Ext.encode(C) == "{}" ? C : Q.parseParams(C),
                            success : function(_, C) {
                                var A = C.result.jsondata;
                                if (!Ext.isEmpty(A) && C.result.success == false) {
                                    var _ = B.find("xtype", "form")[0].getForm();
                                    _.items.items[0].setValue(C.result.info);
                                    B.show();
                                    return false
                                }
                                E.hide();
                                Q.tips($("message.import.success"));
                                E.fireEvent("sumbitSuccess", _, C)
                            },
                            failure : function($, A) {
                                var _ = A.response.responseText;
                                if (!Ext.isEmpty(_)) {
                                    var $ = B.find("xtype", "form")[0].getForm();
                                    $.items.items[0].setValue(A.result.info);
                                    B.show()
                                }
                                E.hide();
                                E.fireEvent("sumbitFailure", $, A);
                                return
                            }
                        })
                    }
                }, {
                    text : $("button.close"),
                    handler : function() {
                        E.close()
                    }
                } ],
                listeners : {
                    "afterrender" : function() {
                        _ = E.find("xtype", "form")[0].getForm()
                    }
                }
            },
            listeners : {
                hide : function() {
                    _.reset()
                }
            }
        });
        return E
    }
});
Ext.ns("Q.comm");
var createGroupStore = function(A) {
    var C = {};
    C = Ext.apply(C, A);
    var D = getGridFields(C), _ = new Ext.data.JsonReader({
        fields : D
    }), B = {
        reader : _
    };
    B = Ext.apply(B, C.store);
    var $ = new Ext.data.GroupingStore(B);
    C.store = $;
    C.view = new Ext.grid.GroupingView(C.view);
    return C
}, getGridFields = function(_) {
    var A = [], $ = _.cm.columns;
    Q.each($, function(_, B) {
        var $ = {};
        $.name = _.dataIndex;
        A.push($)
    });
    return A
};
Q.comm.CommModelVpList = function(G) {
    G = G || {};
    if (typeof G.vp != "undefined" && typeof G.vp.subTab != "undefined") {
        var F = [];
        Q.each(G.vp.subTab, function(_, B) {
            if (typeof _ == "string")
                F.push(_);
            else if (_ instanceof Array) {
                var A = [];
                Q.each(_, function(B, _) {
                    if (B.xtype == "uxeditorgrid" && !Ext.isEmpty(B.isGroupGrid) && B.isGroupGrid === true) {
                        var $ = this.createGroupStore(B);
                        A.push(Ext.apply({}, $))
                    } else
                        A.push(Ext.apply({}, B))
                });
                F.push(A)
            } else if (_.xtype == "uxeditorgrid" && !Ext.isEmpty(_.isGroupGrid) && _.isGroupGrid === true) {
                var $ = createGroupStore(_);
                F.push(Ext.apply({}, $))
            } else
                F.push(Ext.apply({}, _))
        });
        G.vp.subTab = F
    }
    this.configVar = G;
    Ext.applyIf(G, {
        isAudit : false
    });
    var H = [];
    if (typeof G.playListMode != "undefined" && G.playListMode == "panel") {
        if (!cfg.searchWin.searchUrl && !cfg.vp.reportName && !cfg.vp.exportUrl && !cfg.vp.jasperFile && !cfg.vp.reportFileType)
            Q.error("\u53c2\u6570\u914d\u7f6e\u9519\u8bef", "vp.searchWin.searchUrl,vp.reportName,vp.exportUrl,vp.jasperFile,vp.reportFileType");
        var $ = this.panel = this.createPanel(G);
        H.push($)
    } else {
        var C = {
            dealUrl : true,
            moduleName : true,
            playListMode : true
        }, A = {
            column : true,
            store : true,
            subTab : true,
            listEditStateFn : true
        }, B = {
            form : true
        }, K = {
            items : true
        }, E = {
            form : true
        }, _ = {
            items : true
        };
        function D($, A, _) {
            Q.each($, function($, B) {
                if ($)
                    if (typeof A[B] == "undefined") {
                        Q.error("\u9519\u8bef\u6d88\u606f!", "\u914d\u7f6e\u53c2\u6570(" + _ + "." + B + ")\u672a\u5b9a\u4e49\u6216\u67d0\u4e2a\u914d\u7f6e<font color='red'>\u53c2\u6570\u503c\u4e0d\u5b58\u5728\uff01</font>");
                        return false
                    }
            })
        }
        D(C, G, "cfg");
        D(A, G.vp, "vp");
        if (typeof G.editWin != "undefined") {
            D(B, G.editWin, "editWin");
            D(K, G.editWin.form, "editWin.form")
        }
        if (typeof G.searchWin != "undefined") {
            D(E, G.searchWin, "searchWin");
            D(_, G.searchWin.form, "searchWin.form")
        }
        var I = this.grid = this.createGrid(G);
        this.grid.isAudit = G.isAudit;
        this.comps = {};
        var J = this.tabPanel = G.vp.hideSubTab ? [] : this.createTabPanel(G);
        H.push(I);
        H.push(J)
    }
    G = Ext.apply({
        layout : "border",
        id : "sp_viewport",
        dealUrl : G.dealUrl,
        items : H
    }, G);
    this.config = G;
    G.vp = Ext.applyIf(G.vp, G);
    Q.comm.CommModelVpList.superclass.constructor.call(this, G.vp);
    if (typeof G.playListMode != "undefined" && G.playListMode != "panel")
        this.dealstate = this.dealstate();
    this.addEvents("vpInstanceAfert");
    this.addEvents("vpClickAfter")
};
Ext.extend(Q.comm.CommModelVpList, Ext.Viewport, {
    listeners : {
        "vpInstanceAfert" : function() {
            var $;
            if (typeof this.config.playListMode != "undefined" && this.config.playListMode == "panel") {
                $ = this.panel.getSearchWin(this.configVar);
                this.searchWin = $;
                var B = typeof this.config.vpInstanceAfert != "undefined" && this.config.vpInstanceAfert;
                if (Ext.isFunction(B))
                    B();
                return
            }
            var A = this.grid.getEditWin(this.configVar);
            this.editWin = A;
            B = typeof this.config.vpInstanceAfert != "undefined" && this.config.vpInstanceAfert;
            if (Ext.isFunction(B))
                B();
            B = typeof this.config.vpAfterRender != "undefined" && this.config.vpAfterRender;
            if (Ext.isFunction(B)) {
                var _ = B(A);
                if (!Ext.isEmpty(_) && _ != null && _ != "")
                    if (recordId != "null" && recordId != "" && !Ext.isEmpty(recordId) && recordId != null)
                        setTimeout(function() {
                            A.setFormValue(recordId, _);
                            A.show()
                        }, 1000)
            }
        },
        "vpClickAfter" : function() {
            if (typeof this.config.playListMode != "undefined" && this.config.playListMode == "panel")
                return;
            var $ = this.grid, _ = this.grid.getEditWin(this.configVar);
            this.editWin = _;
            var A = typeof this.config.vpClickAfter != "undefined" && this.config.vpClickAfter;
            if (Ext.isFunction(A))
                A($)
        }
    },
    dealTbar : function(F) {
        var E = this, C = F.vp.renameBtnName || {}, _ = [ {
            name : "add",
            Qtext : "\u65b0\u5efa",
            text : Ext.isEmpty(C["add"]) ? $("button.new") : C["add"],
            build : typeof power["add"] != "undefined" && power["add"] && (typeof F.editWin != "undefined"),
            iconCls : "icon-add",
            handler : function(B) {
                var $ = this.findParentByType(Ext.grid.GridPanel), A = typeof vp.configVar.vp.addBefore != "undefined" && vp.configVar.vp.addBefore;
                if (Ext.isFunction(A))
                    A($);
                var _ = $.getEditWin(F);
                E.editWin = _;
                var C = !Ext.isEmpty(F.eidtWin) ? null : F.editWin.hiddenBtn;
                _.resetWinState(C);
                _.show();
                A = typeof vp.configVar.vp.addAfter != "undefined" && vp.configVar.vp.addAfter;
                if (Ext.isFunction(A))
                    A($, _)
            }
        }, {
            name : "edit",
            Qtext : "\u7f16\u8f91",
            text : Ext.isEmpty(C["edit"]) ? $("button.edit") : C["edit"],
            build : typeof power["edit"] != "undefined" && power["edit"] && (typeof F.editWin != "undefined"),
            iconCls : "icon-edit",
            handler : function(D) {
                var A = this.findParentByType(Ext.grid.GridPanel), _ = A.getSelectionModel().getSelections(), C = typeof vp.configVar.vp.editBefore != "undefined" && vp.configVar.vp.editBefore;
                if (Ext.isFunction(C))
                    C(A, _);
                if (_.length <= 0) {
                    Q.tips("<font color='red'>" + $("message.pleaseSelect") + A.moduleName + "\uff01</font>");
                    return
                } else if (_.length > 1) {
                    Q.warning($("message.onlySelect"));
                    return
                }
                var B = A.getEditWin(F);
                E.editWin = B;
                var G = !Ext.isEmpty(F.eidtWin) ? null : F.editWin.hiddenBtn;
                B.setFormValue(_[0], this.name, null, null, G);
                B.show();
                C = typeof vp.configVar.vp.editAfter != "undefined" && vp.configVar.vp.editAfter;
                if (Ext.isFunction(C))
                    C(A, _, B)
            }
        }, {
            name : "view",
            text : Ext.isEmpty(C["view"]) ? $("button.view") : C["view"],
            build : typeof power["list"] != "undefined" && power["list"] && (typeof F.editWin != "undefined"),
            iconCls : "icon-view",
            handler : function(D) {
                var A = this.findParentByType(Ext.grid.GridPanel), _ = A.getSelectionModel().getSelections(), C = typeof vp.configVar.vp.viewBefore != "undefined" && vp.configVar.vp.viewBefore;
                if (Ext.isFunction(C))
                    C(A, _, B);
                if (_.length <= 0) {
                    Q.tips("<font color='red'>" + $("message.pleaseSelect") + A.moduleName + "\uff01</font>");
                    return
                } else if (_.length > 1) {
                    Q.warning($("message.onlySelect"));
                    return
                }
                var B = A.getEditWin(F);
                E.editWin = B;
                B.setFormValue(_[0], this.name);
                B.show();
                C = typeof vp.configVar.vp.viewAfter != "undefined" && vp.configVar.vp.viewAfter;
                if (Ext.isFunction(C))
                    C(A, _, B)
            }
        }, {
            name : "history",
            text : Ext.isEmpty(C["history"]) ? $("button.history") : C["history"],
            build : typeof power["history"] != "undefined" && power["history"] && (typeof F.editWin != "undefined"),
            iconCls : "icon-view",
            handler : function(G) {
                var A = this.findParentByType(Ext.grid.GridPanel), _ = A.getSelectionModel().getSelections();
                if (_.length <= 0) {
                    Q.tips("<font color='red'>" + $("message.pleaseSelect") + A.moduleName + "\uff01</font>");
                    return
                } else if (_.length > 1) {
                    Q.warning($("message.onlySelect"));
                    return
                }
                var C = A.getEditWin(F);
                E.editWin = C;
                var D = typeof vp.configVar.vp.historyBefore != "undefined" && vp.configVar.vp.historyBefore, B = {};
                if (Ext.isFunction(D)) {
                    B = D();
                    if (typeof B != "object") {
                        Q.error("\u9519\u8bef\u8d4b\u503c\uff0c\u53c2\u6570\u5e94\u4e3ajson\u683c\u5f0f\u7684\u5bf9\u8c61");
                        return
                    }
                }
                C.setHistoryRecordValue(_[0], "view", B);
                C.show();
                D = typeof vp.configVar.vp.historyAfter != "undefined" && vp.configVar.vp.historyAfter;
                if (Ext.isFunction(D))
                    D(A, _, C)
            }
        }, {
            name : "delete",
            Qtext : "\u5220\u9664",
            text : Ext.isEmpty(C["delete"]) ? $("button.delete") : C["delete"],
            build : typeof power["delete"] != "undefined" && power["delete"],
            iconCls : "icon-delete",
            handler : function() {
                var B = this.findParentByType(Ext.grid.GridPanel), A = B.getSelectionModel().getSelections();
                if (A.length <= 0) {
                    Q.tips("<font color='red'>" + $("message.pleaseSelect") + B.moduleName + "\uff01</font>");
                    return
                }
                var _ = [];
                for (var C = 0; C < A.length; C++)
                    _.push(A[C].id);
                Q.confirm($("message.delete.confirm"), {
                    ok : function() {
                        Ext.getBody().submitMask();
                        Ext.Ajax.request({
                            url : F.dealUrl + "_delete.action",
                            params : {
                                "ids" : _
                            },
                            success : function(_) {
                                var A = Ext.decode(_.responseText);
                                if (false === A.success) {
                                    Q.error(A.info || $("message.delete.failure") + "<br/><br/>" + $("message.system.error"));
                                    return
                                }
                                Q.tips("<font color='blue'>" + $("message.delete.success") + "</font>");
                                var C = typeof vp.configVar.vp.deleteAfter != "undefined" && vp.configVar.vp.deleteAfter;
                                if (Ext.isFunction(C))
                                    C(B, A);
                                B.getStore().reload()
                            },
                            failure : function(_) {
                                Q.error(B.moduleName + $("message.delete.failure") + "<br/><br/>" + $("message.system.disconnect"))
                            },
                            callback : function() {
                                Ext.getBody().unmask()
                            }
                        })
                    }
                })
            }
        }, {
            Qtext : "\u67e5\u8be2",
            text : Ext.isEmpty(C["search"]) ? $("button.search") : C["search"],
            name : "search",
            build : typeof power["search"] != "undefined" && power["search"] && (typeof F.searchWin != "undefined"),
            iconCls : "icon-search",
            handler : function() {
                var _ = this.findParentByType(Ext.grid.GridPanel), A = _.getTopToolbar().find("name", "clearsearch")[0], B = new _.getSearchWin(F), $ = _.getStore();
                B.on("search", function(C) {
                    Q.each(C, function($, _) {
                        if (_.indexOf("LIKE") > -1 && !Ext.isEmpty($))
                            C[_] = "%" + $ + "%"
                    });
                    Ext.apply($.baseParams, C);
                    var E = typeof vp.configVar.searchWin.searchLoadBefore != "undefined" && vp.configVar.searchWin.searchLoadBefore;
                    if (Ext.isFunction(E))
                        if (E($, $.baseParams, C) === false)
                            return;
                    B.hide();
                    var D = F.vp.hideBtn || [], _ = D.indexOf("clearsearch") > -1;
                    if (!Ext.isEmpty(A) && !_)
                        A.show();
                    if (!Ext.isEmpty($.lastOptions))
                        $.load({
                            params : {
                                start : 0,
                                limit : $.lastOptions.params.limit
                            }
                        });
                    else
                        $.load({
                            params : {
                                start : 0,
                                limit : 20
                            }
                        })
                });
                E.searchWin = B;
                B.show();
                this.handler = function() {
                    B.show()
                }
            }
        }, {
            Qtext : "\u6e05\u7a7a\u67e5\u8be2",
            text : $("button.clearsearch"),
            name : "clearsearch",
            iconCls : "icon-toCancel",
            hidden : true,
            handler : function() {
                var A = this.findParentByType(Ext.grid.GridPanel), $ = A.getTopToolbar().find("name", "viewTypeSetter")[0], _ = A.getStore();
                if (E.config.playListMode == "audit")
                    _.baseParams = {
                        dealFlag : $.checkValue,
                        billFlag : "unAudit",
                        billType : F.vp.billTypeCode,
                        initStates : initStatesStr
                    };
                else if (F.playListMode == "undeal")
                    _.baseParams = {
                        billFlag : "unDeal",
                        initStates : initStatesStr
                    };
                else
                    _.baseParams = {
                        initStates : initStatesStr
                    };
                _.reload();
                var B = A.getTopToolbar().find("name", "clearsearch")[0];
                if (!Ext.isEmpty(B))
                    B.hide()
            }
        }, {
            xtype : "tbfill"
        }, {
            text : $("button.viewTypeSetter"),
            name : "viewTypeSetter",
            build : F.playListMode == "audit",
            iconCls : "none",
            checkValue : "",
            menu : {
                items : [ {
                    text : $("button.myUnAudit"),
                    checked : true,
                    group : "dealFlag",
                    value : 0,
                    checkHandler : D
                }, {
                    text : $("button.myAllBill"),
                    checked : false,
                    group : "dealFlag",
                    value : "",
                    checkHandler : D
                } ]
            }
        } ];
        function D(A, D) {
            if (D) {
                var B = A.value, E = "icon-" + true, C = this.findParentByType(Ext.grid.GridPanel), _ = C.getStore();
                _.baseParams = {
                    dealFlag : B,
                    billFlag : "unAudit",
                    billType : F.vp.billTypeCode,
                    initStates : initStatesStr
                };
                _.load({
                    params : {
                        start : 0,
                        limit : _.lastOptions.params.limit
                    }
                });
                var $ = C.getTopToolbar().find("name", "viewTypeSetter")[0];
                $.setIconClass(E);
                $.checkValue = B
            }
        }
        var G = F.vp.hideBtn;
        Q.each(_, function($, _) {
            if ((G && G.indexOf($.name) > -1))
                $.hidden = true
        });
        var B = F.vp.addOtherBtn, A = typeof F.editWin != "undefined" ? F.editWin.addOtherBtn : "";
        if (!Ext.isEmpty(A))
            Q.each(A, function($, A) {
                $.hidden = true;
                _.splice($.index, 0, $)
            });
        var H = F.vp.addOtherBtn;
        if (!Ext.isEmpty(H))
            Q.each(H, function($, A) {
                _.splice($.index, 0, $)
            });
        return _
    },
    removeTbarUtil : function(_) {
        var $ = this.grid.getTopToolbar().items.items;
        if (_)
            if (typeof (_) == "object")
                Q.each(_, function(_, A) {
                    $[_].hide()
                });
            else
                $[_].hide()
    },
    addTbarUtil : function(_) {
        var $ = this.grid.getTopToolbar();
        if (!Ext.isEmpty(_)) {
            Q.each(_, function(_, A) {
                $.insert(_.index, _)
            });
            $.doLayout()
        }
    },
    modifyTbarUtil : function(_) {
        var $ = this.grid.getTopToolbar();
        $.getObj = function($) {
            return this.find("name", $)[0]
        };
        if (!Ext.isEmpty(_)) {
            Q.each(_, function(_, A) {
                $.getObj(_.name).handler = _.handler
            });
            $.doLayout()
        }
    },
    dealColumn : function(B) {
        var A = B.vp.column;
        if (B.accessControl && B.accessControl.mainGrid) {
            var C = B.accessControl.mainGrid;
            Q.each(C, function($, _) {
                Q.each($, function($, B) {
                    Q.each(A, function(A, C) {
                        if (B == A.dataIndex)
                            A[_] = $
                    })
                })
            })
        }
        var _ = B.vp.disabledColumn;
        if (_)
            if (typeof (_) == "object")
                Q.each(_, function($, _) {
                    A[$].disabled = true
                });
            else
                A[_].disabled = true;
        var $ = B.vp.addOtherColumn;
        if (!Ext.isEmpty($))
            Q.each($, function($, _) {
                A.splice($.index, 0, $)
            });
        return A
    },
    disabledColumnUtil : function($) {
        var _ = this.grid.getColumnModel();
        if ($)
            if (typeof ($) == "object")
                Q.each($, function($, A) {
                    _[$].disabled = true
                });
            else
                _[$].disabled = true
    },
    addColumnUtil : function($) {
        var A = this.grid.getColumnModel(), _ = A.columns;
        if (!Ext.isEmpty($)) {
            Q.each($, function($, A) {
                _.splice($.index, 0, $)
            });
            A.setConfig(_)
        }
    },
    createGrid : function(cfg) {
        var win = this, tempStore = cfg.vp.store, playListMode = cfg.playListMode || "normal", baseParams = {};
        if (cfg.playListMode == "audit")
            baseParams = {
                dealFlag : 0,
                billFlag : "unAudit",
                billType : cfg.vp.billTypeCode,
                initStates : initStatesStr
            };
        else if (cfg.playListMode == "undeal")
            baseParams = {
                billFlag : "unDeal",
                initStates : initStatesStr
            };
        else
            baseParams = {
                initStates : initStatesStr
            };
        baseParams = Ext.apply(baseParams, cfg.vp.baseParams);
        tempStore.baseParams = baseParams;
        var grid = new Ext.ux.grid.GridPanel({
            sm : cfg.vp.sm || {
                singleSelect : true
            },
            moduleName : cfg.moduleName,
            cm : {
                defaults : {
                    width : 100
                },
                columns : win.dealColumn(cfg)
            },
            store : tempStore,
            viewConfig : cfg.vp.viewConfig || {
                forceFit : true
            },
            cfg : cfg,
            getEditWin : function(cfg) {
                cfg.vpWin = win;
                if (typeof cfg.editWin == "undefined")
                    return;
                var createEditWin = cfg.editWin.createEditWin || eval("Q.comm.CommModelEditWin"), winG = new createEditWin(cfg);
                winG.on("submit", function() {
                    grid.key = "";
                    grid.getStore().reload()
                });
                this.getEditWin = function() {
                    return winG
                };
                return winG
            },
            getSearchWin : function(cfg) {
                var createSearchWin = cfg.searchWin.createSearchWin || eval("Q.comm.CommSearchWin"), winG = new createSearchWin(cfg);
                this.getSearchWin = function() {
                    return winG
                };
                return winG
            },
            tbar : win.dealTbar(cfg),
            setBtnIsAble : function(E) {
                var A = grid.getSelectionModel().getSelected(), F = grid.getSelectionModel().getSelections(), $ = grid.getTopToolbar();
                $.getObj = function($) {
                    return this.find("name", $)[0]
                };
                var B = [], _ = [], D = E;
                if (D)
                    Q.each(D, function($, A) {
                        Q.each($, function($, A) {
                            B.push(A);
                            _.push($)
                        })
                    });
                if (Ext.isEmpty(A))
                    Q.each(B, function(_, A) {
                        if (!Ext.isEmpty($.getObj(_)))
                            $.getObj(_).setDisabled(true)
                    });
                else {
                    var C = {};
                    Q.each(B, function(A, B) {
                        if (!Ext.isEmpty($.getObj(A)))
                            Q.each(F, function(D, F) {
                                var E = typeof (_[B]) == "function" ? _[B](D) : _[B];
                                if (A == "edit")
                                    cfg.editFlag = E;
                                if (!E) {
                                    $.getObj(A).setDisabled(true);
                                    return
                                } else
                                    $.getObj(A).setDisabled(false);
                                C[A] = E
                            })
                    });
                    cfg.editBtnDisabled = C
                }
            },
            listeners : {
                "afterrender" : function($) {
                    var _ = $.getSelectionModel();
                    $.getStore().on("load", function() {
                        _.clearSelections()
                    })
                },
                "rowdblclick" : function(_, C) {
                    var B = cfg.vp.rowdblclickFn, B = typeof cfg.vp.rowdblclickFn != "undefined" && cfg.vp.rowdblclickFn, A;
                    if (Ext.isFunction(B))
                        A = B();
                    if (!Ext.isEmpty(A) && A === false)
                        return A;
                    var $ = this.getTopToolbar().find("name", "view")[0];
                    if (typeof cfg.editWin != "undefined" && typeof $.build != "undefined" && $.build)
                        $.handler()
                },
                "click" : function($) {
                    var _ = this, A = cfg.vp.listEditStateFn;
                    this.setBtnIsAble(A);
                    if (typeof (_.getSelectionModel().getSelected()) != "undefined") {
                        var B = _.getSelectionModel().getSelected().id;
                        if (_.key != B) {
                            _.key = B;
                            win.clearAllGridOrForm();
                            win.fireEvent("vpClickAfter")
                        }
                    }
                }
            }
        });
        return grid
    },
    addEditFormItems : function(_) {
        var A = this, $ = A.grid.getEditWin(A.config);
        $.addFormItems(_)
    },
    removeEditFormItems : function(_) {
        var A = this, $ = A.grid.getEditWin(A.config);
        $.removeFormItems(_)
    },
    modifyEditFormItems : function(_) {
        var A = this, $ = A.grid.getEditWin(A.config);
        $.modifyFormItems(_)
    },
    createFormPanel : function(_) {
        var $ = this;
        return new Ext.form.FormPanel({
            region : "center",
            title : _.moduleName,
            height : 120,
            labelWidth : 120,
            layout : "column",
            autoScroll : _.autoScroll || true,
            bodyStyle : "padding:10px",
            defaults : {
                columnWidth : 1,
                layout : "form",
                border : false
            },
            items : $.dealFormItems(_) || {},
            onActivate : function(E, _) {
                if (Ext.isEmpty(E))
                    return;
                var B = $.grid.getSelectionModel().getSelected(), A = _.form;
                if (B) {
                    var C = B.data;
                    C = Ext.apply(C, {});
                    var D = {};
                    Q.each(C, function(_, C) {
                        var $ = A.findField("model." + C.replace(/\_/g, "."));
                        if (!Ext.isEmpty($) && !Ext.isEmpty(_)) {
                            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_) && $.getXType() != "hidden") {
                                if (!(_ instanceof Date))
                                    if ($.getXType() == "timefield") {
                                        var B = _.substring(0, _.indexOf("."));
                                        _ = Date.parseDate(B, "Y-m-d H:i:s")
                                    } else
                                        _ = Date.parseDate(_, "Y-m-d H:i:s")
                            } else if ($.getXType() == "textarea")
                                _ = _.replace(/<br\/>/g, "\n");
                            $.setValue(_)
                        }
                        D["model." + C.replace(/\_/g, ".")] = _
                    });
                    A.setValues(D)
                } else
                    A.reset()
            }
        })
    },
    insert : function(_, $) {
        var B = this.formPanel, A = B.lookupComponent(B.applyDefaults($)), A = B.insert(_, $);
        if (A.ownerCt == B && B.items.indexOf(A) < _)
            --_;
        if (B.fireEvent("beforeadd", this, A, _) !== false && B.onBeforeAdd(A) !== false) {
            B.items.insert(_, A);
            A.onAdded(B, _);
            B.onAdd(A);
            B.fireEvent("add", this, A, _)
        }
        B.doLayout()
    },
    dealFormItems : function(A) {
        if (typeof A.editWin == "undefined")
            return;
        Ext.applyIf(A.editWin.form, {
            items : [],
            columnWidth : 0.5
        });
        var _ = [], $ = A.editWin.form.items || [], B = this.formColumnWidth = A.editWin.form.columnWidth || 0.5;
        if ($)
            Q.each($, function(A, C) {
                if (!(typeof A.fieldUnDisplay != "undefined" && A.fieldUnDisplay.indexOf("tab") > -1)) {
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : A.columnWidth || B,
                        items : [ A ]
                    };
                    _.push($)
                }
            });
        return _
    },
    addFormItems : function(_) {
        var A = this.formPanel;
        if (_) {
            var B = this.formColumnWidth || 0.5;
            if (Ext.isArray(_))
                Q.each(_, function(_, C) {
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : _.columnWidth || B,
                        items : [ _ ]
                    };
                    Ext.applyIf(_, {
                        index : A.items.length || 0
                    });
                    A.insert(_.index, $)
                });
            else {
                var $ = {
                    defaults : {
                        xtype : "textfield",
                        anchor : "95%"
                    },
                    columnWidth : _.columnWidth || B,
                    items : [ _ ]
                };
                Ext.applyIf(_, {
                    index : A.items.length || 0
                });
                A.insert(_.index, $)
            }
        }
    },
    removeFormItems : function($) {
        var A = this.formPanel, _ = A.items.items;
        if ($)
            if (Ext.isArray($))
                Q.each($, function($, A) {
                    _.splice($, 1)
                });
            else
                _.splice($, 1);
        A.doLayout()
    },
    modifyFormItems : function(_) {
        var B = this.formPanel, A = B.items.items;
        if (_) {
            var D = this.formColumnWidth || 0.5;
            if (Ext.isArray(_))
                Q.each(_, function(C, F) {
                    if (C.index) {
                        Ext.apply(A[C.index].initialConfig.items[0], C);
                        var E = A[_.index].initialConfig.items[0], $ = {
                            defaults : {
                                xtype : "textfield",
                                anchor : "95%"
                            },
                            columnWidth : C.columnWidth || D,
                            items : [ E ]
                        };
                        A.splice(C.index, 1);
                        B.insert(C.index, $)
                    }
                });
            else {
                Ext.apply(A[_.index].initialConfig.items[0], _);
                var C = A[_.index].initialConfig.items[0], $ = {
                    defaults : {
                        xtype : "textfield",
                        anchor : "95%"
                    },
                    columnWidth : _.columnWidth || D,
                    items : [ C ]
                };
                A.splice(_.index, 1);
                B.insert(_.index, $)
            }
        }
        B.doLayout()
    },
    createEventMenu : function() {
        var C = this, _ = C.configVar.vp.menuOverride || [ {
            text : $("button.toPass"),
            name : "TOPASS",
            iconCls : "icon-toPass",
            hidden : true,
            handler : function() {
                C.dealstate(this.name, this.text, true)
            }
        }, {
            text : $("button.toNoPass"),
            name : "TONOPASS",
            iconCls : "icon-toNoPass",
            hidden : true,
            handler : function() {
                C.dealstate(this.name, this.text, true)
            }
        } ], B = [ {
            text : $("button.toConfirm"),
            name : "TOCONFIRM",
            iconCls : "icon-toConfirm",
            hidden : true,
            handler : function() {
                C.dealstate(this.name, this.text, false)
            }
        }, {
            text : $("button.noOperation"),
            id : "unstate",
            iconCls : "icon-unstate",
            hidden : true
        } ];
        Q.each(_, function($, _) {
            B.push($)
        });
        var A = new Ext.menu.Menu({
            items : B
        });
        return A
    },
    dealstate : function(C, _, E, $) {
        var D = this, B = D.createEventMenu(), A = new Q.FlowProcess({
            grid : D.grid,
            menu : B,
            classPath : D.dealUrl,
            eventMethod : "getEvents"
        });
        D.flowInfo = A;
        return A.getDealstate(C, _, E, $)
    },
    createTabPanel : function(E) {
        var D = this, C = D.createFormPanel(E), A = C.findByType(Ext.form.Field);
        Ext.each(A, function($) {
            $.setReadOnly(true)
        });
        var _ = new Q.ViewLogItemOrmessageComm({
            grid : D.grid,
            logModuleCode : E.vp.logModuleCode || E.vp.billTypeCode,
            billTypeCode : E.vp.billTypeCode
        }), $ = [];
        if (typeof E.vp.subTab != "undefined") {
            var B = [], G = !Ext.isEmpty(D.configVar.accessControl) ? D.configVar.accessControl.hiddenTab : {};
            Q.each(E.vp.subTab, function(C, F) {
                if (C instanceof Array) {
                    var E = [];
                    if (C.length > 0) {
                        Q.each(C, function(A, C) {
                            if (A.xtype == "formpanel") {
                                var B = D.dealDetailForm(A);
                                D.comps[B.tabClassName] = B;
                                if (!G[B.tabClassName])
                                    E.push(B)
                            } else {
                                var _ = [];
                                Q.each(A.cm.columns, function($, B) {
                                    var A = Ext.applyIf({
                                        id : Ext.id(),
                                        rendererColor : false
                                    }, $);
                                    _.push(A)
                                });
                                if (C == 0) {
                                    var $ = A.tabTitle;
                                    grid = D.dealDetailGrid(A, _, true);
                                    grid.tabTitle = $
                                } else
                                    grid = D.dealDetailGrid(A, _, false);
                                if (!G[grid.tabClassName])
                                    E.push(grid)
                            }
                        });
                        var _ = D.createTabsDtlFn(E);
                        B.push(_)
                    }
                } else if (typeof C != "string")
                    if (C.xtype == "formpanel") {
                        var A = D.dealDetailForm(C);
                        D.comps[A.tabClassName] = A;
                        if (!G[A.tabClassName])
                            B.push(A)
                    } else if (typeof C.xtype != "undefined" && (C.xtype == "uxeditorgrid" || C.xtype == "editorgrid" || C.xtype == "uxgrid" || C.xtype == "grid")) {
                        var $ = [];
                        Q.each(C.cm.columns, function(_, B) {
                            var A = Ext.applyIf({
                                id : Ext.id(),
                                rendererColor : false
                            }, _);
                            $.push(A)
                        });
                        grid = D.dealDetailGrid(C, $, true);
                        grid = new Ext.ux.grid.GridPanel(grid);
                        D.comps[grid.tabClassName] = grid;
                        if (!G[grid.tabClassName])
                            B.push(grid)
                    } else
                        B.push(C)
            });
            if (E.vp.subTab.indexOf("subTab") > -1 && E.vp.subTab.indexOf("logTab") == -1 && E.vp.subTab.indexOf("msgTab") == -1)
                $ = [ C ].concat(B);
            else if (E.vp.subTab.indexOf("subTab") > -1)
                $ = [ C ].concat(B);
            else
                $ = B
        }
        if (E.vp.subTab.length == 0) {
            $.push(_.logItem);
            $.push(_.msgItem)
        } else {
            if (!G["logTab"])
                if (E.vp.subTab.indexOf("logTab") > -1)
                    $.push(_.logItem);
            if (!G["msgTab"])
                if (E.vp.subTab.indexOf("msgTab") > -1)
                    $.push(_.msgItem)
        }
        _.logItem.tabClassName = "logTab";
        _.msgItem.tabClassName = "msgTab";
        C.tabClassName = "subTab";
        D.comps["logTab"] = _.logItem;
        D.comps["msgTab"] = _.msgItem;
        D.comps["subTab"] = C;
        if (!Ext.isEmpty($) && $.length > 0) {
            var F = getSplitTab({
                tabHeight : E.vp.tabHeight || 200,
                gridPanel : D.grid,
                container : "sp_viewport",
                column : E.vp.triggerField,
                activeTab : E.vp.activeTab || 0
            }, [ $ ]);
            return F
        } else
            return []
    },
    dealDetailGrid : function(B, _, $) {
        var C = this, A = {
            border : false,
            title : B.tabTitle,
            region : B.region || "center",
            height : B.height || 300,
            xtype : "uxgrid",
            sm : B.sm || {
                singleSelect : true
            },
            foreignKey : B.foreignKey,
            tabClassName : B.tabClassName,
            isGroupGrid : B.isGroupGrid,
            cm : {
                rn : B.cm.rn || true,
                defaultSortable : B.cm.defaultSortable || true,
                defaults : B.cm.defaults || {
                    menuDisabled : true
                },
                columns : _
            },
            viewConfig : B.viewConfig || {
                forceFit : true
            },
            pageSize : B.pageSize || 0,
            store : B.store,
            onActivate : function(E, $) {
                if (Ext.isEmpty(E))
                    return;
                var C = vp.grid.getSelectionModel().getSelected(), _ = this.getStore();
                _.setBaseParam("filter_EQ_" + B.foreignKey, C.id);
                var D = false;
                Q.each(B.cm.columns, function($, _) {
                    if ($.dataIndex == "uploadFile4View")
                        D = true
                });
                _.load({
                    params : {
                        start : 0,
                        limit : A.pageSize == 0 ? 10000 : A.pageSize
                    },
                    callback : function($) {
                        if (D)
                            Q.each($, function($, _) {
                                vp.renderUploadFile($.get("uploadFileGroupId"), $, "uploadFile4View", "", A)
                            })
                    }
                })
            }
        };
        if (!Ext.isEmpty(B) && B.isGroupGrid === true)
            A = Ext.apply(A, {
                view : B.view
            });
        if (B.vpShowTbar)
            A.tbar = B.tbar;
        return A
    },
    dealDetailForm : function(_) {
        var D = this, B = D.clone(_), A, $;
        B.onActivate = function(I, _) {
            var E = D.grid.getSelectionModel().getSelected(), F = this, C = F.getForm();
            if (typeof F.belongParent != "undefined" && F.belongParent) {
                if (E) {
                    var G = E.data;
                    G = Ext.apply(G, {});
                    var H = {};
                    Q.each(G, function(_, B) {
                        var $ = C.findField("model." + B.replace(/\_/g, "."));
                        if (!Ext.isEmpty($))
                            if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_) && $.getXType() != "hidden") {
                                if ($.getXType() == "timefield") {
                                    var A = _.substring(0, _.indexOf("."));
                                    _ = Date.parseDate(A, "Y-m-d H:i:s")
                                } else if (!(_ instanceof Date))
                                    _ = Date.parseDate(_, "Y-m-d H:i:s")
                            } else if ($.getXType() == "textarea")
                                _ = _.replace(/<br\/>/g, "\n");
                        H["model." + B.replace(/\_/g, ".")] = _
                    });
                    C.setValues(H)
                } else
                    C.reset()
            } else {
                var A = C.getValues(), J = [];
                Q.each(A, function($, _) {
                    J.push(_)
                });
                if (Ext.isEmpty($))
                    $ = new Ext.data.JsonStore({
                        url : B.loadUrl,
                        fields : J,
                        autoLoad : false
                    });
                if (Ext.isEmpty(I))
                    return;
                E = D.grid.getSelectionModel().getSelected();
                $.setBaseParam("filter_EQ_" + B.foreignKey, E.id);
                $.load({
                    callback : function($) {
                        if ($.length > 0) {
                            var _ = $[0].data;
                            _ = Ext.apply(_, {});
                            var A = {};
                            Q.each(_, function(_, D) {
                                var $ = C.findField(D.replace(/\_/g, "."));
                                if (!Ext.isEmpty($))
                                    if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_) && $.getXType() != "hidden") {
                                        if ($.getXType() == "timefield") {
                                            var B = _.substring(0, _.indexOf("."));
                                            _ = Date.parseDate(B, "Y-m-d H:i:s")
                                        } else
                                            _ = Date.parseDate(_, "Y-m-d H:i:s")
                                    } else if ($.getXType() == "textarea")
                                        _ = _.replace(/<br\/>/g, "\n");
                                A[D] = _
                            });
                            C.setValues(A)
                        } else
                            C.reset()
                    }
                })
            }
        };
        B.title = B.tabTitle;
        this.dealDtlDtlFormItems(B);
        A = new Ext.form.FormPanel(B);
        A.store = $;
        var C = A.findByType(Ext.form.Field);
        Ext.each(C, function($) {
            $.setReadOnly(true)
        });
        return A
    },
    createTabsDtlFn : function(C) {
        var B = this;
        if (C.length > 0 && C.length == 1) {
            var $ = B.buildPanl(C);
            return $
        } else if (C.length > 1) {
            C[0].title = "";
            var $ = B.buildPanl(C[0]), A = C[0], _ = false;
            $.onActivate = function(I, $, E) {
                if (Ext.isEmpty(I))
                    return;
                var C = vp.grid.getSelectionModel().getSelected(), E = $.items.items[0], B = E.getStore();
                B.setBaseParam("filter_EQ_" + E.foreignKey, C.id);
                var H = false;
                Q.each(E.getColumnModel().columns, function($, _) {
                    if ($.dataIndex == "uploadFile4View")
                        H = true
                });
                var D = E.getColumnModel().findColumnIndex("uploadFile4View");
                B.load({
                    callback : function($) {
                        if (D != -1)
                            Q.each($, function($, _) {
                                vp.renderUploadFile($.get("uploadFileGroupId"), $, "uploadFile4View", "", E)
                            })
                    }
                });
                var A = E.getSelectionModel();
                if (!_) {
                    var G = $.items.items[1], F = G.items.length;
                    for (var J = F - 1; J > -1; J--)
                        G.setActiveTab(J);
                    _ = true;
                    A.on("selectionchange", function(_) {
                        var A = _.getSelected();
                        if (Ext.isEmpty(A))
                            return;
                        var B = $.items.items[1].items.items;
                        Q.each(B, function(F, J) {
                            var $ = F.getXType() || F.xtype;
                            if ($ != "formpanel" && $ != "form") {
                                var C = F.getStore(), H = F.getColumnModel().findColumnIndex("uploadFile4View");
                                C.setBaseParam("filter_EQ_" + F.foreignKey, A.id);
                                C.load({
                                    callback : function($) {
                                        if (H != -1)
                                            Q.each($, function($, _) {
                                                vp.renderUploadFile($.get("uploadFileGroupId"), $, "uploadFile4View", "", E)
                                            })
                                    }
                                })
                            } else {
                                var B = F.getForm(), _ = B.getValues(), G = F.findByType("label"), I = [];
                                Q.each(_, function($, _) {
                                    I.push(_)
                                });
                                var D = [];
                                Q.each(G, function($, _) {
                                    D.push($.name);
                                    I.push($.name)
                                });
                                if (Ext.isEmpty(C))
                                    C = new Ext.data.JsonStore({
                                        url : F.loadUrl,
                                        fields : I,
                                        autoLoad : false
                                    });
                                if (typeof F.loadUrl == "undefined")
                                    return;
                                C.setBaseParam("filter_EQ_" + F.foreignKey, A.id);
                                C.load({
                                    callback : function($) {
                                        if ($.length > 0) {
                                            var _ = $[0].data;
                                            _ = Ext.apply(_, {});
                                            var A = {};
                                            Q.each(_, function(_, E) {
                                                var $ = B.findField(E.replace(/\_/g, "."));
                                                if (!Ext.isEmpty($))
                                                    if (/((2[0-3])|([0-1]?\d)):[0-5]?\d(:[0-5]?\d)/.test(_) && $.getXType() != "hidden") {
                                                        if ($.getXType() == "timefield") {
                                                            var C = _.substring(0, _.indexOf("."));
                                                            _ = Date.parseDate(C, "Y-m-d H:i:s")
                                                        } else
                                                            _ = Date.parseDate(_, "Y-m-d H:i:s")
                                                    } else if ($.getXType() == "textarea")
                                                        _ = _.replace(/<br\/>/g, "\n");
                                                if (!Ext.isEmpty(G) && G.length > 0 && D.indexOf(E) != -1)
                                                    Q.each(G, function($, A) {
                                                        if ($.name == E)
                                                            $.setText(_)
                                                    });
                                                A[E] = _
                                            });
                                            B.setValues(A)
                                        } else
                                            B.reset()
                                    }
                                })
                            }
                        })
                    })
                }
            };
            var D = new Ext.TabPanel({
                activeTab : 0,
                region : "east",
                width : 480,
                frame : true,
                items : []
            });
            Q.each(C, function(_, A) {
                if (A > 0) {
                    var $;
                    if (typeof _.xtype != "undefined" && (_.xtype == "uxgrid" || _.xtype == "grid"))
                        $ = new Ext.ux.grid.GridPanel(_);
                    else if (typeof _.xtype != "undefined" && (_.xtype == "uxeditorgrid" || _.xtype == "editorgrid"))
                        $ = new Ext.ux.grid.EditorGridPanel(_);
                    else
                        $ = _;
                    B.comps[$.tabClassName] = $;
                    $.storeTemp = {};
                    D.add($)
                }
            });
            $.items.push(D)
        }
        return $
    },
    buildPanl : function(A) {
        win = this;
        var _;
        if (typeof A.xtype != "undefined" && (A.xtype == "uxgrid" || A.xtype == "grid"))
            _ = new Ext.ux.grid.GridPanel(A);
        else if (typeof A.xtype != "undefined" && (A.xtype == "uxeditorgrid" || A.xtype == "editorgrid"))
            _ = new Ext.ux.grid.EditorGridPanel(A);
        else
            _ = A;
        win.comps[_.tabClassName] = _;
        var $ = {
            region : "center",
            xtype : "panel",
            height : win.tabHeight || 300,
            title : _.tabTitle || "",
            layout : "border",
            items : [ _ ]
        };
        return $
    },
    setBgColor : function(B, _, A, $) {
        if ($ !== false) {
            B.attr = "style='background-color:#FBF1BB'";
            if (_.cell == A) {
                delete _.cell;
                B.attr = "style='background-color:#aabbff'"
            }
        }
    },
    openUploadWindows : function(_, $, A, B, C, F) {
        var E = this;
        var dialog = new Ext.ux.UploadDialog.Dialog({   
                          autoCreate: true,   
                          closable: true,   
                          collapsible: false,   
                          draggable: true,   
                          minWidth: 400,         
                          minHeight: 200,   
                          width: 400,   
                          height: 350, 
                          permitted_extensions:['doc','docx','xls','xlsx','ppt','pptx','pdf'],
                          proxyDrag: true,   
                          resizable: true,   
                          constraintoviewport: true,   
                          title: '文件上传',   
                          uploadUrl: path+'/File_upload.action?s_userid='+s_userid,   
                          downLoadUrl : path+'/File_download.action?fileCode=',
                  deleteUrl : path+'/File_delete.action',
                  getUrl:path+'/File_getAll.action',
                          reset_on_hide: false,   
                          allow_close_on_upload: true,
                          base_params:{"fileGroupId":$}
                       });   
                       dialog.show(); 
                       dialog.grid_panel.getStore().on("beforeload",function(store,other){
                       other.params["fileGroupId"] = $ ;
                       });
                       dialog.grid_panel.getStore().load();
                       dialog.on("hide",function( ){ 
                        var fileGroupId = dialog.base_params["fileGroupId"];
                       if (!Ext.isEmpty(_))
        _.set("uploadFileGroupId",fileGroupId);
        else if (Ext.isEmpty(F))
        B.find("name", "model.uploadFileGroupId")[0]
        .setValue(fileGroupId);
        else
        F.setValue(fileGroupId);
        E.renderUploadFile(fileGroupId, _, A, B, C);
                       });
                      
                       E.fielUploadWin = dialog;
        },
        renderUploadFile : function(A, D, B, C, E) {
            var _ = new Ext.data.Store({
                proxy : new Ext.data.HttpProxy({
                    url : path + "/File_getAll.action"  // edit by wyj
                }),
                reader : new Ext.data.JsonReader({
                    root : "rows"
                }, [ "fileId", "fileName", "fileCode" ])
            });
            _.setBaseParam("fileGroupId", A);
            _.reload();
            _.on("load", function() {
                var A = "";
                _.each(function(_) {
                    A += "[<a style=\"cursor:pointer;color:blue\" onclick=\"document.location='" + path + "/File_download.action?fileCode=" + _.data.fileCode + "'\" title=\"" + $("label.ClickToDownload") + "\">" + _.data.fileName + "</a>] "
                });
                if (!Ext.isEmpty(D)) {
                    D.set(B, A || $("label.NoAttachments"));
                    if (E.getView instanceof Function)
                        E.getView().refresh();
                    var F = typeof E.uploadSuccessFn != "undefined" && E.uploadSuccessFn;
                    if (Ext.isFunction(F)) {
                        var G = _.getRange(0, _.getCount());
                        F(G, E, D)
                    }
                } else {
                    F = typeof C.uploadSuccessFn != "undefined" && C.uploadSuccessFn;
                    if (Ext.isFunction(F)) {
                        G = _.getRange(0, _.getCount());
                        F(G, C)
                    }
                    if (typeof B == "string")
                        C.find("name", B)[0].setValue(A || $("label.NoAttachments") + "\u3002");
                    else
                        B.setValue(A || $("label.NoAttachments") + "\u3002")
                }
            })
        },
    getGridByTabClassName : function($) {
        var _ = this;
        return _.comps[$]
    },
    clearAllGridOrForm : function() {
        var $ = this;
        Q.each($.comps, function(A, _) {
            var $ = A.xtype || A.getXType();
            if ($ != "formpanel" && $ != "form")
                A.getStore().removeAll();
            else
                A.getForm().reset()
        })
    },
    changeBillTypeState : function(B, C, _) {
        var A = B.getSelectionModel().getSelections();
        if (A.length <= 0) {
            Q.tips("<font color='red'>" + $("message.pleaseSelect") + B.moduleName + "\uff01</font>");
            return
        } else if (A.length > 1) {
            Q.warning($("message.onlySelect"));
            return
        }
        Q.confirm($("message.confirm") + _ + B.moduleName + "\uff1f", {
            ok : function() {
                Ext.getBody().submitMask();
                var D = A[0].id;
                Ext.Ajax.request({
                    url : path + C,
                    params : {
                        "id" : D
                    },
                    success : function(A) {
                        var C = Ext.decode(A.responseText);
                        if (false === C.success) {
                            Q.error(C.info || B.moduleName + _ + $("message.operator.failure") + "<br/><br/>" + $("message.system.error"));
                            return
                        }
                        Q.tips("<font color='blue'>" + B.moduleName + _ + $("message.operator.success") + "</font>");
                        B.getStore().reload()
                    },
                    failure : function(A) {
                        Q.error(B.moduleName + _ + $("message.operator.failure") + "<br/><br/>" + $("message.system.disconnect"))
                    },
                    callback : function() {
                        Ext.getBody().unmask()
                    }
                })
            }
        })
    },
    dealDtlDtlFormItems : function(_) {
        var A = [], $ = _.items || [], B = this.formColumnWidth = _.columnWidth || 0.5;
        if ($)
            Q.each($, function(_, C) {
                if (!(typeof _.fieldUnDisplay != "undefined" && _.fieldUnDisplay.indexOf("tab") > -1)) {
                    var $ = {
                        defaults : {
                            xtype : "textfield",
                            anchor : "95%"
                        },
                        columnWidth : _.columnWidth || B,
                        items : [ _ ]
                    };
                    A.push($)
                }
            });
        _.height = _.height || 300;
        _.labelWidth = _.labelWidth || 110, _.layout = "column", _.region = _.region || "center", _.autoScroll = _.autoScroll || true, _.bodyStyle = "padding:5px", _.readOnlyFiledArr = [], _.defaults = {
            columnWidth : 1,
            layout : "form",
            border : false
        }, _.items = A
    },
    createPanel : function(cfg) {
        var win = this, panel = new Ext.Panel({
            autoScroll : true,
            region : "center",
            bodyStyle : "width:100%",
            margins : "2",
            html : "<iframe  id='mainFrame22' frameborder='0' width='100%' height='100%' src='' />",
            getSearchWin : function(cfg) {
                var createSearchWin = cfg.searchWin.createSearchWin || eval("Q.comm.CommSearchWin"), winG = new createSearchWin(cfg);
                this.getSearchWin = function() {
                    return winG
                };
                return winG
            },
            tbar : win.dealTbarPanel(cfg)
        });
        return panel
    },
    dealTbarPanel : function(D) {
        var B = this, C, _ = [ {
            Qtext : "\u67e5\u8be2",
            text : $("button.search"),
            build : power.search,
            name : "search",
            iconCls : "icon-search",
            handler : function() {
                var $ = B.panel, A = $.getTopToolbar().find("name", "export")[0], _ = new $.getSearchWin(D);
                _.on("search", function(G) {
                    C = G;
                    var E = D.searchWin.searchUrl, B = D.vp.jasperFile, $ = {
                        exportUrl : E,
                        jasperFile : B
                    }, H = typeof vp.configVar.searchWin.searchReportSubmitBefore != "undefined" && vp.configVar.searchWin.searchReportSubmitBefore;
                    if (Ext.isFunction(H))
                        if (H($, G) === false)
                            return;
                    var F = $.exportUrl + "?reportFileType=html&jasperFile=" + $.jasperFile + "&" + Ext.urlEncode(G) + "&" + new Date();
                    Ext.get("mainFrame22").dom.src = F;
                    _.hide();
                    A.setDisabled(false)
                });
                B.searchWin = _;
                _.show();
                this.handler = function() {
                    _.show()
                }
            }
        }, {
            text : $("button.exportExcel"),
            name : "export",
            iconCls : "icon-excel",
            build : power["export"],
            disabled : true,
            handler : function() {
                var E = D.vp.reportName, A = D.vp.exportUrl, _ = D.vp.jasperFile, F = D.vp.reportFileType, $ = {
                    exportUrl : A,
                    jasperFile : _,
                    reportFileType : F,
                    reportName : E
                }, B = typeof vp.configVar.searchWin.searchReportSubmitBefore != "undefined" && vp.configVar.searchWin.searchReportSubmitBefore;
                if (Ext.isFunction(B))
                    if (B($) === false)
                        return;
                window.open(A + "?reportFileType=" + $.reportFileType + "&jasperFile=" + $.jasperFile + "&" + Ext.urlEncode(C) + "&reportName=" + encodeURI($.reportName))
            }
        } ], F = D.vp.addOtherBtn;
        if (!Ext.isEmpty(F))
            Q.each(F, function($, A) {
                _.splice($.index, 0, $)
            });
        var E = D.vp.hideBtn;
        Q.each(_, function($, _) {
            if (E && E.indexOf($.name) > -1)
                $.hidden = true
        });
        var A = D.editWin.addOtherBtn;
        if (!Ext.isEmpty(A))
            Q.each(A, function($, A) {
                $.hidden = true;
                _.splice($.index, 0, $)
            });
        return _
    },
    clone : function($) {
        return $
    },
    customAddBtn : function(_, C) {
        var A = this, $ = A.grid.getEditWin(A.configVar);
        A.editWin = $;
        $.resetWinState(C);
        $.show();
        var B = typeof _ != "undefined" && _;
        if (Ext.isFunction(B))
            B($, $.formPanel)
    },
    customAddEditBtn : function($, G, D, A, C, _) {
        var E = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(G)) {
            D = G;
            G = ""
        }
        var B = E.grid.getEditWin(E.configVar);
        E.editWin = B;
        B.setFormValue($, "addEdit", G, A, C, _);
        B.show();
        var F = typeof D != "undefined" && D;
        if (Ext.isFunction(F))
            F(B, B.formPanel)
    },
    customEditBtn : function($, G, D, A, C, _) {
        var E = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(G)) {
            D = G;
            G = ""
        }
        var B = E.grid.getEditWin(E.configVar);
        E.editWin = B;
        B.setFormValue($, "edit", G, A, C, _);
        B.show();
        var F = typeof D != "undefined" && D;
        if (Ext.isFunction(F))
            F(B, B.formPanel)
    },
    customViewBtn : function($, D, A) {
        var B = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        if (Ext.isFunction(D)) {
            A = D;
            D = ""
        }
        var _ = B.grid.getEditWin(B.configVar);
        B.editWin = _;
        _.setFormValue($, "view", D);
        _.show();
        var C = typeof A != "undefined" && A;
        if (Ext.isFunction(C))
            C(_, _.formPanel)
    },
    customHistoryBtn : function($, C, E, A) {
        var B = this;
        if (Ext.isEmpty($) || Ext.isFunction($)) {
            Q.error("\u8bf7\u4f20\u9700\u8981\u67e5\u770b\u7684\u5355\u636eid");
            return
        }
        var _ = B.grid.getEditWin(B.configVar);
        B.editWin = _;
        if (typeof C != "object" || Ext.isFunction(D)) {
            Q.error("\u9519\u8bef\u8d4b\u503c\uff0c\u53c2\u6570\u5e94\u4e3ajson\u683c\u5f0f\u7684\u5bf9\u8c61");
            return
        }
        if (Ext.isFunction(E)) {
            A = E;
            E = ""
        }
        _.setHistoryRecordValue($, "view", C, E);
        _.show();
        var D = typeof A != "undefined" && A;
        if (Ext.isFunction(D))
            D(_, _.formPanel)
    },
    dealGridDynamicColumn : function(D, C, A) {
        var _ = new Ext.grid.CheckboxSelectionModel(), F = [];
        if (!Ext.isEmpty(D.isGroupGrid) && D.isGroupGrid === true) {
            var G = [];
            Q.each(C, function($, _) {
                field = {};
                field.name = $.dataIndex;
                G.push(field);
                $ = Ext.applyIf($, {
                    menuDisabled : true
                });
                F.push($)
            });
            var $ = new Ext.data.JsonReader({
                fields : G
            }), A = Ext.apply(A, {
                reader : $
            }), B = new Ext.data.GroupingStore(A);
            D.reconfigure(B, new Ext.grid.ColumnModel(C))
        } else {
            G = [];
            Q.each(C, function($, _) {
                G.push($.dataIndex);
                $ = Ext.applyIf($, {
                    menuDisabled : true
                });
                F.push($)
            });
            var E = Ext.apply(A, {
                fields : G
            }), B = new Ext.data.JsonStore(E);
            D.reconfigure(B, new Ext.grid.ColumnModel(C))
        }
    },
    getImportWin : function(C) {
        var B = this, D = B.configVar;
        C = C || {};
        C = Ext.apply({
            url : D.dealUrl + "_importExcel.action",
        }, C);
        var A = new Ext.ux.Window({
            title : $("message.tip"),
            closable : false,
            width : 550,
            height : 350,
            items : {
                xtype : "form",
                autoScroll : true,
                bodyStyle : "padding:5px 5px 0",
                items : [ {
                    xtype : "textarea",
                    fieldLabel : $("message.errorinfo"),
                    readOnly : true,
                    height : 265,
                    border : false,
                    anchor : "98%"
                } ],
                buttons : [ {
                    text : $("button.close"),
                    handler : function() {
                        A.close()
                    }
                } ]
            }
        }), _, E = new Ext.ux.Window({
            title : $("message.import"),
            closable : false,
            width : 450,
            height : 200,
            items : {
                xtype : "form",
                labelWidth : 90,
                bodyStyle : "padding:5px 5px 0",
                fileUpload : true,
                items : [ {
                    xtype : "fileuploadfield",
                    emptyText : $("message.selectafile"),
                    fieldLabel : $("label.detail"),
                    name : "excelFile",
                    anchor : "90%",
                    allowBlank : false,
                    blankText : $("message.selectafile"),
                    buttonText : "",
                    buttonCfg : {
                        iconCls : "icon-excel"
                    },
                    listeners : {
                        "fileselected" : function(_, A) {
                            if (!/\.xls$/.test(A)) {
                                Q.error($("message.uploadFileSuffixMustBe") + "\uff1a<font color='red'>.xls</font>");
                                _.reset()
                            }
                        }
                    }
                }, {
                    border : false,
                    bodyStyle : "padding:5px;",
                    html : "<div style='color:blue;'>* " + $("message.plaseWriteTemplateFile") + "</div>"
                } ],
                buttons : [ {
                    text : $("button.upload"),
                    handler : function(B) {
                        if (!_.isValid())
                            return;
                        E.fireEvent("sumbitBefore", _, action);
                        _.submit({
                            url : C.url,
                            waitTitle : $("message.fielupload"),
                            waitMsg : $("message.import.wait"),
                            method : "POST",
                            success : function(_, C) {
                                var B = C.result.jsondata;
                                if (!Ext.isEmpty(B) && C.result.success == false) {
                                    var _ = A.find("xtype", "form")[0].getForm();
                                    _.items.items[0].setValue(C.result.info);
                                    A.show();
                                    return false
                                }
                                E.hide();
                                Q.tips($("message.import.success"));
                                E.fireEvent("sumbitSuccess", _, C)
                            },
                            failure : function($, B) {
                                var _ = B.response.responseText;
                                if (!Ext.isEmpty(_)) {
                                    var $ = A.find("xtype", "form")[0].getForm();
                                    $.items.items[0].setValue(B.result.info);
                                    A.show()
                                }
                                E.hide();
                                E.fireEvent("sumbitFailure", $, B);
                                return
                            }
                        })
                    }
                }, {
                    text : $("button.close"),
                    handler : function() {
                        E.close()
                    }
                } ],
                listeners : {
                    "afterrender" : function() {
                        _ = E.find("xtype", "form")[0].getForm()
                    }
                }
            },
            listeners : {
                hide : function() {
                    _.reset()
                }
            }
        });
        return E
    }
});
Ext.ns("Q.comm");
Q.comm.CommSearchWin = function(A) {
    var _ = this.createFormPanel(A);
    this.formPanel = _;
    A = Ext.apply({
        title : A.moduleName + $("button.search"),
        Qtitle : A.moduleName,
        layout : "border",
        items : _,
        width : 700,
        height : 200,
        listeners : {
            "hide" : function() {
                _.form.reset()
            }
        }
    }, A);
    A.searchWin = Ext.applyIf(A.searchWin, A);
    Q.comm.CommSearchWin.superclass.constructor.call(this, A.searchWin);
    this.addEvents("search")
};
Ext.extend(Q.comm.CommSearchWin, Ext.ux.Window, {
    searchData : function() {
        var B = this, _ = this.formPanel.getForm();
        if (!_.isValid())
            return;
        var $ = _.getValues(), A = {};
        Q.each($, function(D, G) {
            var C = _.findField(G);
            if (C.xtype == "datefield")
                if (!Ext.isEmpty(D) && C.xtype == "datefield" && C.format == "Y-m" && G.indexOf("LE") != -1) {
                    var J = _.findField(G).getValue(), H = B.getLastDay(J.getFullYear(), J.getMonth() + 1);
                    A[G] = H + " 23:59:59"
                } else if (!Ext.isEmpty(D) && C.xtype == "datefield" && C.format == "Y-m" && G.indexOf("GE") != -1)
                    A[G] = D + "-01 00:00:00";
                else if (!Ext.isEmpty(D) && C.xtype == "datefield" && C.format == "Y-m" && G.indexOf("EQ") != -1) {
                    var F = G.split("_");
                    if (F.length > 0) {
                        var I = "filter_GE_" + F[2];
                        A[I] = D + "-01 00:00:00";
                        var E = "filter_LE_" + F[2], J = _.findField(G).getValue(), H = B.getLastDay(J.getFullYear(), J.getMonth() + 1);
                        A[E] = H + " 23:59:59"
                    }
                    delete $[G]
                }
        });
        $ = Ext.apply($, A);
        if (false === this.fireEvent("search", $))
            return
    },
    getLastDay : function(A, _) {
        var $ = A, B = _++;
        if (_ > 12) {
            B -= 12;
            $++
        }
        var D = new Date($, B, 1), C = (new Date(D.getTime() - 1000 * 60 * 60 * 24));
        return C.format("Y-m-d")
    },
    dealFormItems : function(D) {
        Ext.applyIf(D.searchWin.form, {
            items : [],
            columnWith : 0.5
        });
        var C = [], A = D.searchWin.form.items || [], E = this.formColumnWidth = D.searchWin.form.columnWidth || 0.5, _ = D.searchWin.isShowStatus == false ? true : false, B = D.searchWin.isShowSynStatus == false ? true : false;
        if (A) {
            if (!_) {
                if (!Ext.isEmpty(D.searchWin.checkboxgroup))
                    A.push({
                        fieldLabel : $("label.billStatus"),
                        anchor : "100%",
                        xtype : "checkboxgroup",
                        items : D.searchWin.checkboxgroup
                    });
                if (!Ext.isEmpty(D.searchWin.checkboxgroup1))
                    A.push({
                        fieldLabel : !Ext.isEmpty(D.searchWin.checkboxgroup) ? "" : $("label.billStatus"),
                        anchor : "100%",
                        xtype : "checkboxgroup",
                        hidden : D.searchWin.checkboxgroup1.length > 0 ? false : true,
                        items : D.searchWin.checkboxgroup1.length > 0 ? D.searchWin.checkboxgroup1 : [ {} ]
                    })
            }
            if (!B) {
                if (!Ext.isEmpty(D.searchWin.checkboxgroupSyn1))
                    A.push({
                        fieldLabel : $("label.erpSyn"),
                        anchor : "100%",
                        xtype : "checkboxgroup",
                        items : D.searchWin.checkboxgroupSyn1
                    });
                if (!Ext.isEmpty(D.searchWin.checkboxgroupSyn2))
                    A.push({
                        fieldLabel : !Ext.isEmpty(D.searchWin.checkboxgroup) ? "" : $("label.erpSyn"),
                        anchor : "100%",
                        xtype : "checkboxgroup",
                        hidden : D.searchWin.checkboxgroupSyn2.length > 0 ? false : true,
                        items : D.searchWin.checkboxgroupSyn2.length > 0 ? D.searchWin.checkboxgroupSyn2 : [ {} ]
                    })
            }
            Q.each(A, function(_, A) {
                var $ = {
                    defaults : {
                        xtype : "textfield",
                        anchor : "95%"
                    },
                    columnWidth : _.xtype == "checkboxgroup" ? 1 : _.columnWidth || E,
                    items : [ _ ]
                };
                C.push($)
            })
        }
        return C
    },
    createFormPanel : function(B) {
        var A = this, _ = new Ext.form.FormPanel({
            region : "center",
            labelWidth : B.searchWin.form.labelWidth || 100,
            layout : "column",
            border : true,
            bodyStyle : "padding:10px",
            defaults : {
                columnWidth : 1,
                layout : "form",
                border : false
            },
            items : A.dealFormItems(B),
            buttons : [ {
                text : $("button.search"),
                Qtext : "\u67e5\u8be2",
                handler : function() {
                    A.searchData()
                }
            }, {
                text : $("button.return"),
                Qtext : "\u8fd4\u56de",
                handler : function() {
                    A.hide()
                }
            } ]
        });
        return _
    }
})