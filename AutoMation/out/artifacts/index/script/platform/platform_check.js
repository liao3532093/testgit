/**
 * Created by vsofo on 2017/4/26.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/***
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="startClose(' + row.id + ')">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateCheck(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="startClose(' + row.id + ')">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updateCheck(' + row.id + ')">修改</a>';
    }
}
/***
 *  状态
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
 *  脚本信息
 * @param value
 * @param row
 * @param index
 */
function operscript(value, row, index) {
    return '<span style="word-wrap: break-word;" class="easyui-tooltip" title="' + row.executeSql + '">' + row.executeSql + '</span>';
}
/**
 *  初始化信息
 * @param id
 */
function init(type, selectid, url) {
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    $.post(url, {type: type}, function (result) {
        for (var i = 0; i < result.rows.length; i++) {
            var obj = result.rows;
            for (var i = 0; i < obj.length; i++) {
                $(selectid).append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
            }
        }
    });
}
/**
 *  修改时加载数据
 */
function loadInfo() {
    var id = $.session.get('id');
    if (undefined != id) {
        var path = projectName + '/check/selectPlatCheckById.asp';
        $.post(path, {id: id}, function (result) {
            var obj = result.obj;
            $('#plat_name').val(obj.platID);
            $('#pread_name').val(obj.expectTypeID);
            $('#plat_check_id').val(obj.id);
            $('#script_txt').val(obj.title);
            $('#script').val(obj.executeSql);
            $('#expect_value').val(obj.expectValue);
        });
    }
}
/**
 *  添加
 */
function addPlatCheck() {
    $.session.clear();
    var url = url = projectName + '/main/platform/add_plat_check.jsp';
    art.dialog.open(url, {id: "tip", height: 380, width: 600, title: '新增脚本', lock: true}, true); //打开子窗体
}
/**
 *  保存
 */
function savePlatCheck() {
    var platId = $('#plat_name').val();
    var preadId = $('#pread_name').val();
    if (-1 == platId || -1 == preadId) {
        alert('请填写完整信息!');
        return;
    }
    var url = projectName + '/check/savePlatCheckInfo.asp';
    $('#plat_check_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            } else if (-1 == result) {
                alert('只能写入SELECT 语句，其他DELETE，Update 等语句暂不可写入');
            }
        },
        error: function (error) {
            alert('保存失败');
        },
        async: true
    });
}
/**
 *  搜索
 */
function searchCheck() {
    var platId = $('#plat_name').val();
    var predId = $('#pred_name').val();
    var sc_name = $('#sc_name').val();
    $('#check_table').datagrid('load', {'platID': platId, 'expectTypeID': predId, 'title': sc_name});
}
/**
 *  启用禁用
 * @param id
 */
function startClose(id) {
    var url = projectName + '/check/startClosePlatCheck.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#check_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改
 * @param id
 */
function updateCheck(id) {
    $.session.set('id', id);
    var url = url = projectName + '/main/platform/add_plat_check.jsp';
    art.dialog.open(url, {id: "tip", height: 350, width: 600, title: '修改脚本', lock: true}, true); //打开子窗体
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