function submitHandler() {
    var sumbit = $("#sumbitBtn");
    sumbit.trigger('click');
}

layui.use(['form', 'tableSelect', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect;

    // 表格查询下拉框
    function initTestTableSelect() {
        var tableSelect = layui.tableSelect;
        tableSelect.render({
            elem: '#nationalityName',
            checkedKey: 'id',
            searchKey: 'name',
            searchPlaceholader: '国籍搜索',
            width: 400,
            height: 200,
            table: {
                url: appUtil.constant.url.nationality.listUrl,
                parseData: function (res) {
                    var code = 1;
                    if (res.code == 1) {
                        code = 0
                    }
                    return {
                        code: code,
                        data: res.data.content,
                        msg: res.msg,
                        count: res.data.totalElements
                    }
                },
                cols: [[
                    {type: 'radio', "width": 50},
                    {'field': 'id', "width": 50, "title": "id"},
                    {'field': 'name', "width": 100, "title": '国籍'},
                ]]
            },
            //elem:input对象,data:返回的数据
            done: function (elem, data) {
                if (data && data.data && data.data.length > 0) {
                    $("#nationalityId").val(data.data[0].id);
                    $("#nationalityName").val(data.data[0].name);
                } else {
                    $("#nationalityId").val('');
                    $("#nationalityName").val('');
                }
            }

        })
    }

    initTestTableSelect();
    $.each(appUtil.constant.hobby, function (key, value) {
        var option = $("<option>").val(value.id).text(value.name);
        $("#hobby").append(option);
    });
    form.render('select');
    form.verify({
        name: function (value, item) {
            if (value.length > 10) {
                return '姓名不得大于10';
            }
        },
        phoneNumber: function (value, item) {
            if (!new RegExp("^1[3456789]\\d{9}$").test(value)) {
                return "输入正确的手机号";
            }
        },
        age: function (value, item) {
            if (value > 100) {
                return "请输入合理的年龄"
            }
        }
    });
    form.on('submit(submitFilter)', function (data) {
        var postData = Object.assign({}, data.field);
        console.log(postData);
        appUtil.ajaxHandle.saveModel(appUtil.constant.url.user.saveUrl, JSON.stringify(postData), function (result) {
            console.log(result.code);
            console.log(appUtil.constant.webStatus.SUCCESS);
            if (appUtil.constant.webStatus.SUCCESS == result.code) {
                console.log("CCCCC");
                appUtil.model.close();
                parent.location.reload();
            }
        });
        return false;

    })


});