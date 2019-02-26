/**
 * Created by vsofo on 2017/6/8.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isListPermission([], null, 'chanExecList');
});
$(document).ready(function () {
    loadTable50Line('#piling_implement_table');
    initPlatInfo();
});
/**
 * 状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (0 == row.state) {
        return '<font color="#bc8f8f">等待</font>';
    } else if (1 == row.state) {
        return '<font color="red">失败</font>';
    } else if (2 == row.state) {
        return '<font color="green">成功</font>';
    } else if (3 == row.state) {
        return '<font color="red">脚本异常</font>';
    }
}
/**
 *  用例标题
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function opertitle(value, row, index) {
    return '<a href="###" style="color: green;word-wrap: break-word;" onclick="open_piling_execute_result(' + row.id + ',' + row.exaId + ')" class="easyui-tooltip" title="' + row.exaTitle + '">' + row.exaTitle + '</a>';
}
/**
 * 初始化平台信息
 */
function initPlatInfo() {
    url = projectName + '/PManage/selectPlatformManageByName.asp';
    $('#plat_id').html('');
    $('#plat_id').append($('<option value="-1">请选择</option>'));
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#plat_id').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
        initSelectList('#state', [0, 1, 2, 3], ['等待', '失败', '成功', '脚本异常']);
        $('#implement_old_date').val(next_dataTime(7));
        $('#implement_new_date').val(initInDate(23, 59, 59));
    });
}
/**
 *  搜索
 */
function search_piling_implement() {
    var platId = $('#plat_id').val();
    var old_date = $('#implement_old_date').val();
    var new_date = $('#implement_new_date').val();
    var detail = $('#detail_name').val();
    var state = $('#state').val();
    $('#piling_implement_table').datagrid('load', {
        'exaId': platId,
        'oldTime': old_date,
        'newTime': new_date,
        'exaTitle': detail,
        'state': state
    });
}
/**
 *  打开渠道用例执行结果
 */
function open_piling_execute_result(id, exaId) {
    url = projectName + '/pilingImpl/findPilExecuteInfo.asp';
    $.post(url, {id: id, exaId: exaId}, function (result) {
        if (1 == result) {
            url = projectName + '/main/piling/piling_execute_result.jsp';
            art.dialog.open(url, {id: "tip", height: 600, width: 800, title: '用例执行详情', lock: true}, true); //打开子窗体
        }
    });
}