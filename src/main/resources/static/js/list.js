layui.use('table', function () {
    var table = layui.table;
    // layer.load(2);
    table.render({
        elem: '#test'
        , url: appUtil.constant.url.user.listUrl
        , parseData: function (data) {
            if (data.code == 1) {
                code = 0;
            }
            return {
                "code": code,
                "msg": data.msg,
                "data": data.data.content,
                "count": data.data.totalElements
            };

        }
        , title: '用户数据表'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'id', width: 120, fixed: 'left', unresize: true, sort: true}
            , {field: 'name', title: '用户名', width: 120, edit: 'text'}
            , {field: 'age', title: '年龄', width: 120, edit: 'text', sort: true}
            , {field: 'phoneNumber', title: '手机号', width: 120, edit: 'text', sort: true}
            , {field: 'nationality.name', title: '国籍', width: 120, edit: 'text', sort: true}
            , {
                field: 'hobby', title: '爱好', width: 120, edit: 'text', sort: true, template: function (d) {
                    var text = '';
                    $.each(appUtil.constant.hobby, function (key, value) {
                        if (value.id == d) {
                            text = value.name;
                        }
                    });
                    return text;
                }
            }
            , {toolbar: '#tools', minWidth: '120', align: 'center', title: '操作', fixed: 'right'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 20, 50]
        , done: function () {
            layer.closeAll('loading');
        }

    });
    //查询重载
    $("#search").click(
        function () {
            table.reload('test', {
                page: {
                    curr: 1
                }
                , where: {
                    name: $("#searchKeyword").val()
                }
            }, 'data');
        }
    );


    //add
    $("#add").click(function () {
        appUtil.layer.open("添加", appUtil.constant.url.user.addUrl,
            function (index, layero) {
                var iframeWin = layero.find('iframe')[0];
                console.log(iframeWin);
                iframeWin.contentWindow.submitHandler(index, layero);
            },
            function (index) {
                return true;

            });
    });


    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            appUtil.ajaxHandle.removeModel(data.id, data.name, appUtil.constant.url.user.delUrl, function (result) {
                if (result.code == appUtil.constant.webStatus.SUCCESS) {
                    window.location.reload();
                }
            });
        } else if (obj.event === 'edit') {
            appUtil.layer.open("添加", appUtil.constant.url.user.editUrl + data.id,
                function (index, layero) {
                    var iframeWin = layero.find('iframe')[0];
                    console.log(iframeWin);
                    iframeWin.contentWindow.submitHandler(index, layero);
                },
                function (index) {
                    return true;

                });
        }
    });
});