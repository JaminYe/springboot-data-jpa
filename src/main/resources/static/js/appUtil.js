var appUtil = function () {
    var loadingLayerIndex;
    return {


        constant: {
            url: {
                user: {
                    listUrl: ctx + "users/pageList",
                    addUrl: ctx + "users/add",
                    saveUrl: ctx + "users/save",
                    delUrl: ctx + "users/del/",
                    editUrl: ctx + "users/edit/"
                },
                nationality:{
                    listUrl: ctx + "nationality/pageList",

                }
            },
            hobby: [{id: '1', name: '篮球'}, {id: '2', name: '羽毛球'}, {id: '3', name: '排球'},
                {id: '6', name: '跳绳'}, {id: '4', name: '跳远'}, {id: "5", name: '足球'}],
            webStatus: {
                FAIL: 0,
                SUCCESS: 1,
                WARNING: 2
            },
            modalStaus: {
                SUCCESS: "success",
                FAIL: "erro",
                WARN: "warning"
            },
            icon: function (type) {
                var icon = "";
                if (appUtil.constant.modalStaus.SUCCESS == type) {
                    icon = 0;
                } else if (appUtil.constant.modalStaus.WARN == type) {
                    icon = 1
                } else if (appUtil.constant.modalStaus.FAIL == type) {
                    icon = 2
                } else {
                    icon = 3
                }
                return icon;
            }
        },
        //弹窗
        layer: {
            open: function (title, url, yesFunction, cancelFunction) {
                layer.open({
                    type: 2,
                    title: title,
                    area: ['700px', '400px'],
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    content: url,
                    yes: yesFunction,
                    cancel: cancelFunction
                })
            }
        },
        //ajax请求
        ajaxHandle: {
            saveModel: function (url, data, callback) {
                var config = {
                    url: url,
                    type: 'post',
                    contentType: "application/json",
                    dataType: 'json',
                    data, data,
                    beforeSend: function () {
                        appUtil.model.loading("正在处理,请稍候......")
                    },
                    success: function (result) {
                        if (appUtil.constant.webStatus.SUCCESS == result.code) {
                            console.log(result.code);
                            appUtil.model.alterSuccess(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }
                            })
                        } else if (this.webStatus.WARNING == result.code) {
                            appUtil.model.alterWarning(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }
                            })
                        } else {
                            appUtil.model.alterErro(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }

                            })
                        }
                        appUtil.model.closeLoading();
                    }
                };
                $.ajax(config)
            },
            submit: function (url, type, dataType, data, callback) {
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    beforeSend: function () {
                        appUtil.model.loading("正在处理,请稍候......")
                    },
                    success: function (result) {
                        if (result.code == appUtil.constant.webStatus.SUCCESS) {
                            appUtil.model.alterSuccess(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }
                            });
                        } else if (appUtil.constant.webStatus.WARNING == result.code) {
                            appUtil.model.alterWarning(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }
                            });
                        } else {
                            appUtil.model.alterErro(result.msg, function (index) {
                                if (typeof callback == "function") {
                                    callback(result);
                                }
                            });
                        }
                        appUtil.model.closeLoading();
                    }
                };
                $.ajax(config);
            },
            get: function (url, callback) {
                this.submit(url, 'get', 'json', '', callback);
            },
            removeModel: function (id, name, url, callback) {
                appUtil.model.confirm("您确认要删除该条" + name + "的数据吗?", function () {
                    var path = url + id;
                    appUtil.ajaxHandle.get(path, callback);
                })
            }
        },
        //模块
        model: {
            loading: function (message) {
                if (message) {
                    loadingLayerIndex = parent.layer.msg(message, {time: 0, icon: 6, shade: 0.01});
                } else {
                    loadingLayerIndex = parent.layer.msg("数据正在加载中.....", {time: 0, icon: 6, shade: 0.01})
                }
            },
            closeLoading: function () {
                if (loadingLayerIndex) {
                    parent.layer.close(loadingLayerIndex);
                }
            }
            ,
            close: function () {
                var index = parent.layer.getFrameIndex(window.name);
                console.log(index);
                parent.layer.close(index);
            }
            ,
            confirm: function (content, callback) {
                layer.confirm(content, {icon: 3, title: "系统提示", btn: ['确认', '取消']}, function (index) {
                    layer.close(index);
                    callback(index);
                })
            }
            ,
            alert: function (content, type, callback) {
                layer.alert(content, {
                    icon: appUtil.constant.icon(type),
                    title: "系统确认",
                    btn: ['确认'],
                    btnClass: ['btn btn-primary']
                }, function (index) {
                    if (callback) {
                        callback(index);
                    }
                    layer.close(index);
                })
            }
            ,
            alterSuccess: function (content, callback) {
                appUtil.model.alert(content, appUtil.constant.modalStaus.SUCCESS, callback);
            }
            ,
            alterWarning: function (content, callback) {
                appUtil.model.alert(content, appUtil.constant.modalStaus.WARN, callback);
            }
            ,
            alterErro: function (content, callback) {
                appUtil.model.alert(content, appUtil.constant.modalStaus.FAIL, callback);
            }
            ,

        }
    }
}();