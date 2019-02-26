/**
 * Created by vsofo on 2017/6/6.
 */
var pathName = window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var url = '';
$(function () {
    isListPermission([], null, 'chanCaseList');
});
$(document).ready(function () {
    initPlatData();
});
/**
 *  加载平台信息
 */
function initPlatData() {
    initSelectList('#pil_exa_state', [0, 1], ['禁用', '启用']);
    url = projectName + '/PManage/selectPlatformManageByName.asp';
    $('#plat_id').html('');
    $('#plat_id').append($('<option value="-1">请选择</option>'));
    $.post(url, {type: 1}, function (result) {
        var obj = result.rows;
        for (var i = 0; i < obj.length; i++) {
            $('#plat_id').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
        }
        locationInfo();
        //initRequestData();
    });
}
// /**
//  *  加载请求信息
//  */
// function initRequestData() {
//     $('#request_id').html('');
//     $('#request_id').append($('<option value="-1">请选择</option>'));
//     url = projectName + '/PManage/selectPlatRequestByNames.asp';
//     $.post(url, {type: 1}, function (result) {
//         var obj = result.rows;
//         for (var i = 0; i < obj.length; i++) {
//             $('#request_id').append($('<option value=' + obj[i].id + '>' + obj[i].title + '</option>'));
//         }
//         locationInfo();
//     });
// }
function locationInfo() {
    var json = $.session.get('pilexample');
    if (!stringUtils(json)) {
        var obj = jsonToObj(json);
        $('#plat_id').val(obj.platId);
        $('#pil_exa_name').val(obj.pilExaName);
        $('#piling_model_exp_id').val(obj.id);
        //$('#request_id').val(obj.requestId);
        $('#pil_exa_state').val(obj.state);
    }
}
/**
 *  保存渠道用例信息
 */
function savePilingExample() {
    var platId = $('#plat_id').val();
    var pilExaName = $('#pil_exa_name').val();
    //var requestId = $('#request_id').val();
    var state = $('#pil_exa_state').val();
    if (-1 == platId || stringUtils(pilExaName) || -1 == state) {
        LOG_I('请填写完整信息...');
        return;
    }
    url = projectName + '/pilexample/savePilExample.asp';
    $('#piling_exa_form').ajaxSubmit({
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