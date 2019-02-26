/**
 * Created by vsofo on 2017/4/25.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/**
 *  方法描述
 * @param value
 * @param row
 * @param index
 */
function operdetail(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.detail + '" class="easyui-tooltip">' + row.detail + '</span>';
}
/***
 *  平台信息操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;&nbsp;|' +
            '&nbsp;&nbsp;<a href="###" style="color: blue" onclick="updateScriptMethod(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;&nbsp;|' +
            '&nbsp;&nbsp;<a href="###" style="color: blue" onclick="updateScriptMethod(' + row.id + ')">修改</a>';
    }
}
/***
 *  平台信息状态
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function operstate(value, row, index) {
    if (1 == row.isEnable) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/**
 *  初始化信息
 */
function init(id) {
    var sid = id;
    var url = projectName + '/script/selectScriptMethodById.asp';
    $.post(url, {id: sid}, function (result) {
        var obj = result.obj;
        $('#script_method_name').val(obj.title);
        $('#script_method_detail').val(obj.detail);
        $('#script_method_type').val(obj.isEnable);
        $('#script_method_id').val(obj.id);
    });
    $.session.remove('id');
}
/**
 *  添加脚本方法
 */
function addScriptMethod() {
    $.session.remove('id');
    var url = url = projectName + '/main/system/add_script_method.jsp';
    art.dialog.open(url, {id: "tip", height: 180, width: 400, title: '添加脚本方法', lock: true}, true); //打开子窗体
}
/**
 *  保存脚本方法
 */
function saveScriptMethod() {
    var name = $('#script_method_name').val();
    var detail = $('#script_method_detail').val();
    var type = $('#script_method_type').val();
    if (-1 == type || stringUtils(name) || stringUtils(detail)) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/script/saveScriptMethod.asp';
    $('#add_script_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('平台名称已存在!');
            }
        },
        error: function (error) {
            alert('添加失败!');
        },
        async: true
    });
}
/**
 *  修改脚本方法
 * @param id
 */
function updateScriptMethod(id) {
    $.session.set('id', id);
    var url = projectName + '/main/system/add_script_method.jsp';
    art.dialog.open(url, {id: "tip", height: 180, width: 400, title: '添加脚本方法', lock: true}, true); //打开子窗体
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/script/openCloseScriptMethodById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#script_method_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  搜索脚本信息
 */
function searchScriptMethod() {
    var name = $('#script_name').val();
    var detail = $('#script_detail').val();
    $('#script_method_table').datagrid('load', {'title': name, 'detail': detail});
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
    if (str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
}

