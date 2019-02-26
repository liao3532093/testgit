/**
 * Created by vsofo on 2017/6/5.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = "";
$(function () {
    isListPermission([], null, 'chanModelList');
});
$(document).ready(function () {
    initSelectData(projectName + '/PManage/selectPlatformManageByName.asp', '#plat_name', 1);
});
/*初始化数据*/
function initData() {
    initSelectList('#piling_model_type', [1, 2, 3], ['商户下单', '渠道返回', '渠道回调']);
    initSelectList('#piling_model_state', [0, 1], ['禁用', '启用']);
    initSelectList('#piling_request_type', [0, 1], ['GET', 'POST']);
    // initSelectList('#piling_request_type', [], []);
    //isModelShow(0);
    updateData();
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
        initData();
    });
}
// /**
//  *  显示
//  * @param type  显示方式
//  */
// function isModelShow(type) {
//     if (0 == type) {
//         $('.piling_order').show();
//         $('.piling_listener').hide();
//         initSelectList('#piling_request_type', [], []);
//     } else if (1 == type) {
//         initSelectList('#piling_request_type', [0, 1], ['GET', 'POST']);
//         $('.piling_listener').show();
//         $('.piling_order').hide();
//     }
// }
/**
 * 加载修改信息
 */
function updateData() {
    //alert(decodeURIComponent(unzip($.session.get('pilmodel'))));
    var result = decodeURIComponent(unzip($.session.get('pilmodel')));
    if (!stringUtils(result)) {
        var obj = jsonToObj(result);
        $.session.remove('pilmodel');
        $('#plat_name').val(obj.platId);
        $('#piling_model_type').val(obj.modelType);
        $('#piling_model_id').val(obj.id);
        $('#piling_model_name').val(obj.modelName);
        $('#piling_model_state').val(obj.state);
        // if (0 == obj.modelType) {
        //     //isModelShow(0);
        //     $('#piling_result_item').val(obj.responseItem);
        // } else if (1 == obj.modelType) {
        //     //isModelShow(1);
        //     $('#piling_request_type').val(obj.requestType == 'GET' ? 0 : 1);
        //     $('#piling_request_url').val(obj.requestUrl);
        //     $('#piling_request_item').val(obj.responseItem);
        // }
        $('#piling_request_type').val(obj.requestType == 'GET' ? 0 : 1);
        $('#piling_request_url').val(obj.requestUrl);
        $('#piling_result_item').val(obj.responseItem);
    }
}
// /**
//  *  下拉事件
//  */
// function loadPilingModel() {
//     var id = $('#piling_model_type').children('option:selected').val();
//     isModelShow(id)
// }
/**
 *  保存模块信息
 */
function savePilingModel() {
    var platId = $('#plat_name').val();
    var modelType = $('#piling_model_type').val();
    var modelName = $('#piling_model_name').val();
    var responseItem = $('#piling_result_item').val();
    var requestType = $('#piling_request_type').val();
    //var requestItem = $('#piling_request_item').val();
    var state = $('#piling_model_state').val();
    // if (-1 == modelType) {
    //     alert('请选择模块类型...');
    //     return;
    // } else {
    //     if (0 == modelType && (-1 == platId || stringUtils(modelName) || stringUtils(responseItem) || -1 == state)) {
    //         alert('请填写完整信息...');
    //         return;
    //     } else if (1 == modelType && (-1 == platId || stringUtils(modelName) || -1 == requestType || stringUtils(requestUrl) || stringUtils(requestItem))) {
    //         alert('请填写完整信息...');
    //         return;
    //     }
    // }
    if (-1 == platId || stringUtils(modelName) || stringUtils(responseItem) || -1 == requestType || -1 == state || -1 == modelType) {
        LOG_I('请填写完整信息...');
        return;
    }
    url = projectName + '/piling/savePilingModelInfo.asp';
    $('#piling_model_form').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: url,
        success: function (result) {
            if (1 == result) {
                setTimeout(function () {
                    art.dialog.open.origin.location.reload()
                }, 500);
            }
        },
        error: function (error) {
            LOG_I('保存失败');
        },
        async: true
    });
}