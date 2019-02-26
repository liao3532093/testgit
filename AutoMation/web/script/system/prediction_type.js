/**
 * Created by vsofo on 2017/4/25.
 */
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
/***
 *  平台信息操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.isEnable) {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updatePrediction(' + row.id + ')">修改</a>';
    } else {
        return '<a href="###" style="color: blue" onclick="openClose(' + row.id + ')">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" style="color: blue" onclick="updatePrediction(' + row.id + ')">修改</a>';
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
/***
 *  添加预测类型
 */
function addPrediction() {
    var name = $('#pred_name').val();
    if (stringUtils(name)) {
        alert('请输入预测类型');
        return;
    }
    var url = projectName + '/prediction/savePredictionInfo.asp';
    $('#prediction_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                $('#pred_name').val('');
                $('#prediction_id').val('-1');
                updaBtnStyle('#pred_install', '#pred_update');
                $('#prediction_table').datagrid('reload');  //刷新列表
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
 *  修改预测类型
 */
function updatePrediction(id) {
    $('#prediction_id').val(id);
    updaBtnStyle('#pred_update', '#pred_install');
    var url = projectName + '/prediction/selectPredictionById.asp';
    $.post(url, {id: id}, function (result) {
        var obj = result.obj;
        $('#pred_name').val(obj.title);
    });
}
/**
 *  更换按钮样式
 * @param name1
 * @param name2
 */
function updaBtnStyle(name1, name2) {
    $(name1).attr('disabled', false);
    $(name1).css('background', '#2288CC');
    $(name2).attr('disabled', true);
    $(name2).css('background', '#888888');
}
/**
 *  启用禁用
 * @param id
 */
function openClose(id) {
    var url = projectName + '/prediction/openPredictionById.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#prediction_table').datagrid('reload');  //刷新列表
        }
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
    if (str == '' || str.length <= 0) {
        return true;
    } else {
        return false;
    }
}

