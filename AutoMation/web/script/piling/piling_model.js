/**
 * Created by vsofo on 2017/6/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isListPermission(['#chan_model_install'], '#piling_model_table', 'chanModelList');
});
$(document).ready(function () {
    initData();
});
/**
 *  状态
 * @param value
 * @param row
 * @param index
 */
function operstate(value, row, index) {
    if (1 == row.state) {
        return '<font color="green">启用</font>';
    } else {
        return '<font color="red">禁用</font>';
    }
}
/**
 *  操作
 * @param value
 * @param row
 * @param index
 */
function operation(value, row, index) {
    if (1 == row.state) {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">禁用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingModel(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="expResult(' + row.id + ')" style="color: blue;">预期结果配置</a>';
    } else {
        return '<a href="###" onclick="openClose(' + row.id + ')" style="color: blue;">启用</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="updatePilingModel(' + row.id + ')" style="color: blue;">修改</a>&nbsp;|&nbsp;' +
            '<a href="###" onclick="expResult(' + row.id + ')" style="color: blue;">预期结果配置</a>';
    }
}
/**
 *  模块类型
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function opermodelType(value, row, index) {
    switch (row.modelType) {
        case 1:
            return '商户下单';
        case 2:
            return '渠道返回';
        case 3:
            return '渠道回调';
    }
}
/**
 *  请求地址
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function operrequestUrl(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.requestUrl + '" class="easyui-tooltip">' + row.requestUrl + '</span>';
}
/**
 *  返回/请求参数
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function operresponseItem(value, row, index) {
    return '<span style="word-wrap: break-word;" title="' + row.responseItem + '" class="easyui-tooltip">' + row.responseItem + '</span>';
}
/*初始化数据*/
function initData() {
    loadTable50Line('#piling_model_table');
    initSelectData(projectName + '/PManage/selectPlatformManageByName.asp', '#plat_name', 1);
    initSelectList('#piling_model_type', [1, 2, 3], ['商户下单', '渠道返回', '渠道回调']);
}
/**
 * 初始化下拉列表信息
 * @param url 服务器地址
 * @param selectid 下拉列表id
 * @param type 是否已启用
 */
function initSelectData(url, selectid, type) {
    $(selectid).html('');
    $(selectid).append($('<option value="-1">请选择</option>'));
    $.post(url, {type: type}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $(selectid).append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
    });
}
/**
 *  添加模块
 */
function add_piling_model(title) {
    url = projectName + '/main/piling/add_piling_model.jsp';
    art.dialog.open(url, {id: "tip", height: 450, width: 700, title: title, lock: true}, true); //打开子窗体
}
/**
 *  启用禁用
 */
function openClose(id) {
    url = projectName + '/piling/updateOpenCloseModel.asp';
    $.post(url, {id: id}, function (result) {
        if (1 == result) {
            $('#piling_model_table').datagrid('reload');  //刷新列表
        }
    });
}
/**
 *  修改
 * @param id
 */
function updatePilingModel(id) {
    url = projectName + '/piling/selectPilingModelById.asp';
    $.post(url, {id: id, state: 0}, function (result) {
        //alert(zip(encodeURIComponent(getJson(result.obj))));
        $.session.set('pilmodel', zip(encodeURIComponent(getJson(result.obj))));
        //$.session.set('pilmodel', getJson(result.obj));
        add_piling_model('修改模块');
    });
}
/**
 *  添加预期结果
 * @param id
 */
function expResult(id) {
    url = projectName + '/piling/selectPilingModelById.asp';
    $.post(url, {id: id, state: 0}, function (result) {
        $.session.set('pilmodel', zip(encodeURIComponent(getJson(result.obj))));
        location.href = projectName + '/main/piling/piling_model_expresult.jsp';
    });
}
/***
 *  搜索
 */
function searchPilModel() {
    var platId = $('#plat_name').val();
    var modelType = $('#piling_model_type').val();
    var modelName = $('#detail_name').val();
    $('#piling_model_table').datagrid('load', {'platId': platId, 'modelName': modelName, 'modelType': modelType});
}
/*
 * var url = url = projectName + '/main/platform/add_plat_check.jsp';
 art.dialog.open(url, {id: "tip", height: 380, width: 600, title: '新增脚本', lock: true}, true); //打开子窗体
 * */