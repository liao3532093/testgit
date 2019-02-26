/**
 * Created by vsofo on 2017/5/9.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$(document).ready(function () {
    //改变每页数据条数,例如改为50
    $("#log_type_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
});
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    return '<a href="###" onclick="update_log_type(' + row.id + ')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;' +
        '<a href="###" onclick="delete_log_type(' + row.id + ')">删除</a>';
}
/**
 *  保存日志信息
 */
function saveLogType() {
    var name = $('#log_name').val();
    var key = $('#log_key').val();
    if (stringUtils(name) || stringUtils(key)) {
        alert('请填写完整内容!');
        return;
    }
    var url = projectName + '/logtype/saveLogTypeInfo.asp';
    $('#log_type_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            $('#log_name').val('');
            $('#log_key').val('');
            $('#log_id').val('-1');
            $('#log_btn').val('添加');
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('关键字重复!');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  修改
 * @param id
 */
function update_log_type(id) {
    var url = projectName + '/logtype/selectLogTypeById.asp';
    $.post(url, {id: id}, function (result) {
        var obj = result.obj;
        $('#log_name').val(obj.title);
        $('#log_key').val(obj.logKey);
        $('#log_id').val(obj.id);
        $('#log_btn').val('修改');
    });
}
/**
 *  删除
 * @param id
 */
function delete_log_type(id) {
    $.messager.alert('提示', '是否要删除这条日志!', 'warning', function () {
        var url = projectName + '/logtype/deleteLogTypeById.asp';
        $.post(url, {id: id}, function (result) {
            if (1 == result) {
                $('#log_type_table').datagrid('reload');  //刷新列表
            }
        });
    });
}
/**
 *  将对象转成json
 * @param obj
 * @returns {string|*}
 */
function getJson(obj) {
    return $.toJSON(obj);
}
/***
 *  判断是否为空
 * @param str
 * @returns {boolean}
 */
function stringUtils(str) {
    if (str == null || str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
}

