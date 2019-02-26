/**
 * Created by vsofo on 2017/6/6.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isListPermission([], null, 'chanModelList');
});
$(document).ready(function () {
    initData();
});
/**
 *  操作
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function operation(value, row, index) {
    if (1 == row.state) {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingModelExp(' + row.id + ')" style="color: blue;">修改</a>';
    } else {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingModelExp(' + row.id + ')" style="color: blue;">修改</a>';
    }
}
/**
 *  状态
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function operstate(value, row, index) {
    if (1 == row.state) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/**
 *  预期值
 * @param value
 * @param row
 * @param index
 */
function operexaval(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.eValue + '" class="easyui-tooltip">' + row.eValue + '</span>'
}
/**
 *  执行脚本
 * @param value
 * @param row
 * @param index
 */
function opersql(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.sqlName + '" class="easyui-tooltip">' + row.sqlName + '</span>'
}
/**
 *  预期结果详情
 * @param value
 * @param row
 * @param index
 */
function operdetails(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.details + '" class="easyui-tooltip">' + row.details + '</span>'
}
/**
 *  初始化
 */
function initData() {
    loadTable50Line('#piling_model_exp_table');
    var json = decodeURIComponent(unzip($.session.get('pilmodel')));
    var obj = jsonToObj(json);
    $.session.remove('pilmodel');
    $.session.set('model_exp_id', obj.id);
    $('.piling_model_name').text(obj.modelName);
}
/**
 *  添加
 * @param title
 */
function goToModelExpResult(title) {
    url = projectName + '/main/piling/add_model_expresult.jsp';
    art.dialog.open(url, {id: "tip", height: 350, width: 450, title: title, lock: true}, true); //打开子窗体
}
/**
 *  禁用和启用
 * @param id
 */
function openClose(id) {
    url = projectName + '/piling/updateModelOpenClose.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#piling_model_exp_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改
 * @param id
 */
function updatePilingModelExp(id) {
    url = projectName + '/piling/selectPilModelExpResultById.asp';
    $.post(url, {id: id}, function (result) {
        $.session.set('pilmodelexp', getJson(result.obj));
        goToModelExpResult('修改预测结果');
    });
}